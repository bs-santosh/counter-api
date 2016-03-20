package com.counterapi.rest.dto;

import java.util.ArrayList;
import java.util.Collection;

import com.counterapi.rest.model.StringCounterModel;

public class WordAndCountDto extends ArrayList<StringCounterModel>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public WordAndCountDto(){}
	public WordAndCountDto(Collection<? extends StringCounterModel> c) {
        super(c);
    }
}
