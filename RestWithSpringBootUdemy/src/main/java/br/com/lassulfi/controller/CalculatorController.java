package br.com.lassulfi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.lassulfi.service.CalculatorService;

@RestController
public class CalculatorController {

	@Autowired
	private CalculatorService service;
	
	@RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {
		
		return service.sum(numberOne, numberTwo);
	}
	
	@RequestMapping(value = "/subtract/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double substract(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {

		return service.subtract(numberOne, numberTwo);
	}
	
	@RequestMapping(value = "/multiply/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double multiply(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {

		return service.multiplication(numberOne, numberTwo);
	}
	
	@RequestMapping(value = "/divide/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double divide(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
			throws Exception {

		return service.division(numberOne, numberTwo);
	}
	
	@RequestMapping(value = "/average/{numberOne}/{numberTwo}", method = RequestMethod.GET)
	public Double average(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) 
			throws Exception {
		
		return service.average(numberOne, numberTwo);
	}
	
	@RequestMapping(value = "/squareRoot/{number}", method = RequestMethod.GET)
	public Double squareRoot(@PathVariable("number") String number) throws Exception {
		
		return service.squareRoot(number);
	}

}
