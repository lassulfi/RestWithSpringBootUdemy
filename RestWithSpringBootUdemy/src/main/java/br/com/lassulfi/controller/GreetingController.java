package br.com.lassulfi.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.lassulfi.data.model.Greeting;

@RestController
public class GreetingController {

	private static final String template = "Hello, %s!";
	
	private final AtomicLong counter = new AtomicLong();
	
	@RequestMapping("/greetings")
	public Greeting greetings(@RequestParam(value = "name", defaultValue = "World") String name) {
		
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}
