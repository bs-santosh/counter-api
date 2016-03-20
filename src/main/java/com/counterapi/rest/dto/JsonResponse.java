package com.counterapi.rest.dto;

import java.util.ArrayList;
import java.util.List;

import com.counterapi.rest.model.StringCounterModel;

public class JsonResponse {
	List<StringCounterModel> counts = new ArrayList<StringCounterModel>();

	public List<StringCounterModel> getCounts() {
		return counts;
	}

	public void setCounts(List<StringCounterModel> counts) {
		this.counts = counts;
	}

}
