package br.com.lassulfi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lassulfi.exceptions.ResourceNotFoundException;
import br.com.lassulfi.model.Person;
import br.com.lassulfi.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public Person create(Person person) {
		return personRepository.save(person);
	}
	
	public Person update(Person person) {
		Person personObj = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		personObj.setFirstName(person.getFirstName());
		personObj.setLastName(person.getLastName());
		personObj.setAddress(person.getAddress());
		personObj.setGender(person.getGender());
		
		return personRepository.save(personObj);
	}
	
	public void delete(Long id) {
		Person personObj = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		personRepository.delete(personObj);
	}
	
	public Person findById(Long id) {
		
		return personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}
	
	public List<Person> findAll() {
		return personRepository.findAll();
	}
}
