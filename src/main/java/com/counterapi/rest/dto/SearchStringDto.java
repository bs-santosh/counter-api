package com.counterapi.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchStringDto {

	String wordsToSearch;

	public String getWordsToSearch() {
		return wordsToSearch;
	}

	public void setWordsToSearch(String wordsToSearch) {
		this.wordsToSearch = wordsToSearch;
	}
}
