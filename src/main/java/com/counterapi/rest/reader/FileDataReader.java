package com.counterapi.rest.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringTokenizer;

import com.counterapi.rest.model.StringCounterModel;

public class FileDataReader implements DataReader{

	private String filePath;
	Map<String, StringCounterModel> wordMap = new HashMap<String, StringCounterModel>();
	
	public Map<String, StringCounterModel> getWordMap() {
		return wordMap;
	}


	public void setWordMap(Map<String, StringCounterModel> wordMap) {
		this.wordMap = wordMap;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	@Override
	public Map<String, StringCounterModel> readData() {
		if(wordMap == null || wordMap.size() == 0){
			populateWordsMap();
		}
		return wordMap;
	}
	
	public List<StringCounterModel> getTopRatedWords(int howMany){
		List<StringCounterModel> wordsList = new ArrayList<StringCounterModel>();
		try{
			if(wordMap == null || wordMap.size() == 0){
				populateWordsMap();
			}
			else{
				System.out.println("Map is already set");
			}
			
			List<Entry<String, StringCounterModel>> list = new ArrayList<Entry<String, StringCounterModel>>(wordMap.entrySet());
			Collections.sort(list, new Comparator<Entry<String, StringCounterModel>>()
	        {
	            public int compare(Entry<String, StringCounterModel> o1,
	                    Entry<String, StringCounterModel> o2)
	            {
	            	return o2.getValue().getCount() - o1.getValue().getCount();
	            }
	        });
			if(howMany < list.size()){
				for(int i=0; i<howMany; i++){
					wordsList.add(list.get(i).getValue());
				}
			}
			else{
				for(int i=0; i<list.size(); i++){
					wordsList.add(list.get(i).getValue());
				}
			}
				
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return wordsList;
	}

	private void populateWordsMap(){
		BufferedReader br = null;
		try{
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource(getFilePath()).getFile());
			br = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = getOnlyStrings(br.readLine())) != null){
				StringTokenizer st = new StringTokenizer(line, " ");
				while(st.hasMoreElements()){
					String token = (String)st.nextElement();
					String tokenLC = token.toLowerCase();
					if(wordMap.containsKey(tokenLC)){
						StringCounterModel counterModel = wordMap.get(tokenLC);
						counterModel.setCount(counterModel.getCount() + 1);
						wordMap.put(tokenLC, counterModel);
					}
					else{
						StringCounterModel counterModel = new StringCounterModel();
						counterModel.setWord(token);
						counterModel.setCount(1);
						wordMap.put(tokenLC, counterModel);
					}
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
				
		}
	}
	
	private String getOnlyStrings(String s) {
		if(s != null){
		    Pattern pattern = Pattern.compile("[^a-z A-Z \\s \\n 1-9]");
		    Matcher matcher = pattern.matcher(s);
		    s = matcher.replaceAll("");
		}
	    return s;
	 }
}
