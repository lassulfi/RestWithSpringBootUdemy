package br.com.lassulfi.converter;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O, D> D parseObject(O origin, Class<D> destination) {
		return mapper.map(origin, destination);
	}
	
	public static <O, D> List<D> parseListOfObjects(List<O> origin, Class<D> destination) {
		List<D> destinationObjectsList = new ArrayList<>();
		for (Object o : origin) {
			destinationObjectsList.add(mapper.map(o, destination));
		}
		
		return destinationObjectsList;
	}
}
