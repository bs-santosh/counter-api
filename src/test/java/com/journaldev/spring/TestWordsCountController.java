package com.journaldev.spring;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.counterapi.rest.constants.CounterApiConstants;
import com.counterapi.rest.dto.JsonParameter;

public class TestWordsCountController {

	public static final String SERVER_URI = "http://localhost:9090/counter-api";
	
	public static void main(String args[]){
		
		testGetTopWords();
		System.out.println("*****");
		testSearchWords();
		System.out.println("*****");
	}

	private static void testGetTopWords(){
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap> topWords = restTemplate.getForObject(SERVER_URI+CounterApiConstants.GET_TOP_WORDS, List.class);
		System.out.println(topWords.size());
		for(LinkedHashMap map : topWords){
			System.out.println("Top matching words = "+map.get("counts"));;
		}
	}
	
	private static void testSearchWords() {
		RestTemplate restTemplate = new RestTemplate();
		JsonParameter param = new JsonParameter();
		List<String> wordsToSearch = new ArrayList<String>();
		wordsToSearch.add("Duis");
		wordsToSearch.add("Sed");
		wordsToSearch.add("123");
		
		List<LinkedHashMap> matchingWords = restTemplate.postForObject(SERVER_URI+CounterApiConstants.GET_WORD_COUNT, param, List.class);
		
		for(LinkedHashMap map : matchingWords){
			System.out.println("Matched words = "+map.get("counts"));;
		}
	}

}
