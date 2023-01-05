package com.springBoot.hospitalMngm.formatter;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.format.Formatter;

public class MyDateFormatter implements Formatter<String> {

	@Override
	public String print(String object, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String parse(String text, Locale locale) throws ParseException {
		String[] split = text.split("-");
		String substring = split[2].substring(0, split[2].indexOf('T'));
		String[] split2 = split[2].substring( split[2].indexOf('T')+1, split[2].length()).split(":");
		LocalDateTime selectedDateTime = LocalDateTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]),Integer.parseInt(substring), Integer.parseInt(split2[0]), Integer.parseInt(split2[1]));
		
		return selectedDateTime.format(DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm" ));
	}

}
