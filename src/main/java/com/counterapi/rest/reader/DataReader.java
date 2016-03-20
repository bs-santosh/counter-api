package com.counterapi.rest.reader;

import java.util.List;
import java.util.Map;

import com.counterapi.rest.model.StringCounterModel;

public interface DataReader {
	public Map<String, StringCounterModel> readData();
	public List<StringCounterModel> getTopRatedWords(int howMany);
}
