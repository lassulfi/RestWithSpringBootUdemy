package br.com.lassulfi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.lassulfi.model.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();
	
	public Person create(Person person) {
		return person;
	}
	
	public Person update(Person person) {
		return person;
	}
	
	public void delete(String id) {
		
	}
	
	public Person findById(String id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Luis");
		person.setLastName("Daniel Assulfi");
		person.setAddress("Campinas, São Paulo, Brasil");
		person.setGender("male");		
		
		return person;
	}
	
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<>();
		
		for (int i = 0; i < 7; i++) {
			Person person = this.mockPerson(i);
			persons.add(person);
		}
		
		return persons;
	}

	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name " + i);
		person.setLastName("Last name " + i);
		person.setAddress("Somewhere in Brazil " + i);
		person.setGender(i % 2 == 0 ? "male" : "female");		
		
		return person;
	}
}
