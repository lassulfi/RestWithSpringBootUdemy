package br.com.lassulfi.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lassulfi.data.vo.BookVO;
import br.com.lassulfi.service.BookService;

@RestController
@RequestMapping("/api/books/v1")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO create(@RequestBody BookVO book) {
		BookVO bookVO = bookService.create(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		
		return bookVO;
	}
	
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO update(@RequestBody BookVO book) {
		BookVO bookVO = bookService.update(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		
		return bookVO;
	}
	
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO findById(@PathVariable("id") Long id) {
		BookVO bookVO = bookService.findById(id);
		bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		
		return bookVO;
	}
	
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<BookVO> findAll() {
		List<BookVO> books = bookService.findAll();
		books.forEach(bookVO -> bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel()));
		
		return books;
	}
}
