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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Person Endpoint", description = "Person resource operations", tags = { "Person" })
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	private PersonService personService;

	@ApiOperation(value = "creates a person entity")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person) {

		PersonVO personVO = personService.create(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());

		return personVO;
	}

//	@PostMapping("/v2")
//	public PersonVOv2 createv2(@ResquestBody PersonVOv2 person) {
//		
//		return personService.createv2(person);
//	}

	@ApiOperation(value = "updates a person entity")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) {

		PersonVO personVO = personService.update(person);
		personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel());

		return personVO;
	}

	@ApiOperation(value = "find a person by id")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO personVO = personService.findById(id);
		personVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

		return personVO;
	}

	@ApiOperation(value = "deletes a person entity based on its id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

		personService.delete(id);

		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "returns a list of all persons")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<PersonVO> findAll() {

		List<PersonVO> persons = personService.findAll();
		persons.forEach(personVO -> personVO.add(linkTo(methodOn(PersonController.class).findById(personVO.getKey())).withSelfRel()));
		
		return persons;
	}
}
