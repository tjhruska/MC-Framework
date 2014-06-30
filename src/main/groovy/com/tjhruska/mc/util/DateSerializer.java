package com.tjhruska.mc.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.StdDateFormat;

@SuppressWarnings("rawtypes")
public class DateSerializer extends JsonSerializer {

	@Override
	public void serialize(Object value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		
		StdDateFormat formatter = new StdDateFormat();
		String date = formatter.format(value);
		jgen.writeString(date);

	}

}
