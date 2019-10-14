package br.com.lassulfi.converter.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.lassulfi.data.model.Person;
import br.com.lassulfi.data.vo.v2.PersonVOv2;

@Service
public class PersonConverter {

	public PersonVOv2 convertEntityToVO(Person person) {
		PersonVOv2 vo = new PersonVOv2();
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		vo.setAddress(person.getAddress());
		vo.setBirthDate(new Date());
		
		return vo;
	}
	
	public Person convertVOtoEntity(PersonVOv2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		entity.setAddress(person.getAddress());
		
		return entity;
	}
}
