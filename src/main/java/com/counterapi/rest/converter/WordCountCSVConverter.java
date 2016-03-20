package com.counterapi.rest.converter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.counterapi.rest.dto.WordAndCountDto;
import com.counterapi.rest.model.StringCounterModel;

import au.com.bytecode.opencsv.CSVWriter;

public class WordCountCSVConverter extends AbstractHttpMessageConverter<WordAndCountDto> {

	public WordCountCSVConverter() {
	}

	public WordCountCSVConverter(MediaType mediaType) {
		super(mediaType);
	}

	public WordCountCSVConverter(MediaType... supportedMediaTypes) {
		super(supportedMediaTypes);
	}

	@Override
	protected WordAndCountDto readInternal(Class<? extends WordAndCountDto> arg0, HttpInputMessage arg1)
			throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean supports(Class<?> type) {
		return WordAndCountDto.class.equals(type);
	}

	@Override
	protected void writeInternal(WordAndCountDto wordAndCountDtos, HttpOutputMessage httpOutputMessage)
			throws IOException, HttpMessageNotWritableException {
		
		
		httpOutputMessage.getHeaders().setContentType(new MediaType("text", "csv", Charset.forName("utf-8")));
		httpOutputMessage.getHeaders().set("Content-Disposition", "attachment; filename=output.csv");
		
		CSVWriter writer = new CSVWriter(new OutputStreamWriter(httpOutputMessage.getBody()));
		for (StringCounterModel model : wordAndCountDtos) {
			writer.writeNext(new String[] { model.getWord(), model.getCount() + "" });
		}

		writer.close();
	}

}
