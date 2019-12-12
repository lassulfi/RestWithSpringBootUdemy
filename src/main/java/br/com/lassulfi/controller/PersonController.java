package br.com.lassulfi.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lassulfi.data.vo.PersonVO;
import br.com.lassulfi.service.PersonService;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {
	
	@Autowired
	private PersonService personService;

	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO create(@RequestBody PersonVO person) {
		
		return personService.create(person);
	}
	
//	@PostMapping("/v2")
//	public PersonVOv2 createv2(@RequestBody PersonVOv2 person) {
//		
//		return personService.createv2(person);
//	}
	
	@PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, 
			consumes = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO update(@RequestBody PersonVO person) {
		
		return personService.update(person);
	}
	
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO personVO = personService.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		
		return personVO;
	}
		
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		
		personService.delete(id);
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public List<PersonVO> findAll() {
		
		return personService.findAll();
	}
}
