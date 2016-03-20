package com.counterapi.rest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.counterapi.rest.constants.CounterApiConstants;
import com.counterapi.rest.dto.JsonParameter;
import com.counterapi.rest.dto.JsonResponse;
import com.counterapi.rest.dto.WordAndCountDto;
import com.counterapi.rest.model.StringCounterModel;
import com.counterapi.rest.reader.DataReader;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class WordsCounterController {

	private static final Logger logger = LoggerFactory.getLogger(WordsCounterController.class);

	private DataReader dataReader;

	public DataReader getDataReader() {
		return dataReader;
	}

	@Autowired
	@Qualifier("fileReader")
	public void setDataReader(DataReader dataReader) {
		this.dataReader = dataReader;
	}

	@RequestMapping(value = CounterApiConstants.GET_WORD_COUNT, method = RequestMethod.POST, headers = {
			"Content-Type=application/json" })
	public @ResponseBody JsonResponse findWordsCount(@RequestBody String searchString) {
		logger.info("Entered method getWordCounts");
		List<StringCounterModel> wordCountsList = new ArrayList<StringCounterModel>();
		try {
			ObjectMapper mapper = new ObjectMapper();

			JsonParameter searchStrWrap = mapper.readValue(searchString, JsonParameter.class);

			Map<String, StringCounterModel> wordsMap = getDataReader().readData();
			List<String> matchedWords = new ArrayList<String>();
			
			for (String mapKey : wordsMap.keySet()) {
				
				for (String searchStr : searchStrWrap.getSearchString()) {
					if (mapKey.equalsIgnoreCase(searchStr) || mapKey.toLowerCase().contains(searchStr.toLowerCase())) {
						wordCountsList.add(wordsMap.get(mapKey));
						matchedWords.add(searchStr);
					}
					
				}

			}
			List<String> searchWords = searchStrWrap.getSearchString();
			if(matchedWords.size() < searchWords.size()){
				searchWords.removeAll(matchedWords);
			}
			for(String unMatchedWord : searchWords){
				StringCounterModel model = new StringCounterModel();
				model.setWord(unMatchedWord);
				model.setCount(0);
				wordCountsList.add(model);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		JsonResponse response = new JsonResponse();
		response.setCounts(wordCountsList);
		logger.info("Exiting method getWordCounts");
		return response;
	}

	
	@RequestMapping(value = CounterApiConstants.GET_TOP_WORDS, method = RequestMethod.GET, produces = {"text/csv"})
	public @ResponseBody String deleteEmployee(@PathVariable("howMany") int howMany) {

		logger.info("Entered method getWordCounts");
		List<StringCounterModel> modelList = getDataReader().getTopRatedWords(howMany);
		List<String> strList = new ArrayList<String>();
		for(StringCounterModel model:modelList){
			strList.add(model.toString());
		}
		logger.info("Exiting method deleteEmployee");
		
		return StringUtils.collectionToCommaDelimitedString(strList);
	}

}
