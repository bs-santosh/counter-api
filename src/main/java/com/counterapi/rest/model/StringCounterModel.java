package com.counterapi.rest.model;

import java.io.Serializable;

public class StringCounterModel implements Serializable{

	private static final long serialVersionUID = 1L;
	String word;
	int count;
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public String toString(){
		return word+"|"+count;
	}
}
