package br.com.lassulfi.serialization.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public final class YAMLJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

	public YAMLJackson2HttpMessageConverter() {
		super(new YAMLMapper().setSerializationInclusion(Include.NON_NULL), 
				MediaType.parseMediaType("application/x-yaml"));
	}	
}
