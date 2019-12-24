package br.com.lassulfi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lassulfi.converter.DozerConverter;
import br.com.lassulfi.converter.custom.PersonConverter;
import br.com.lassulfi.data.model.Person;
import br.com.lassulfi.data.vo.PersonVO;
import br.com.lassulfi.data.vo.v2.PersonVOv2;
import br.com.lassulfi.exceptions.ResourceNotFoundException;
import br.com.lassulfi.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonConverter personConverter;
	
	public PersonVO create(PersonVO person) {
		var personEntity = DozerConverter.parseObject(person, Person.class);
		var personVO = DozerConverter.parseObject(personRepository.save(personEntity), PersonVO.class);
		
		return personVO;
	}
	
	public PersonVOv2 createv2(PersonVOv2 person) {
		var personEntity = personConverter.convertVOtoEntity(person);
		var personVOv2 = personConverter.convertEntityToVO(personRepository.save(personEntity));
		
		return personVOv2;
	}
	
	public PersonVO update(PersonVO person) {
		Person personEntity = personRepository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		personEntity.setFirstName(person.getFirstName());
		personEntity.setLastName(person.getLastName());
		personEntity.setAddress(person.getAddress());
		personEntity.setGender(person.getGender());
		
		var personVO = DozerConverter.parseObject(personRepository.save(personEntity), PersonVO.class);
		
		return personVO;
	}
	
	public void delete(Long id) {
		Person personObj = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		personRepository.delete(personObj);
	}
	
	public PersonVO findById(Long id) {	
		var personEntity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		return DozerConverter.parseObject(personEntity, PersonVO.class);
	}
	
	public Page<PersonVO> findAll(Pageable pageable) {
		var page = personRepository.findAll(pageable);
		
		return page.map(this::convertToPersonVO);
	}
	
	private PersonVO convertToPersonVO(Person entity) {
		
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	@Transactional
	public PersonVO disablePerson(Long id) {
		this.personRepository.disablePersons(id);
		
		var personEntity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		return DozerConverter.parseObject(personEntity, PersonVO.class);
	}
}
