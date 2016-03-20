package com.counterapi.rest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchString {
	private List<SearchStringDto> searchStrings;

	public List<SearchStringDto> getSearchStrings() {
		return searchStrings;
	}

	public void setSearchStrings(List<SearchStringDto> searchStrings) {
		this.searchStrings = searchStrings;
	}
	
}
