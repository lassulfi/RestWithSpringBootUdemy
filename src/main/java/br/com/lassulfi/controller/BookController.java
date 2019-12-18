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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Books endpoint", description = "Books resource operations", tags = { "Books" })
@RestController
@RequestMapping("/api/books/v1")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@ApiOperation(value = "creates a book entity")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO create(@RequestBody BookVO book) {
		BookVO bookVO = bookService.create(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		
		return bookVO;
	}
	
	@ApiOperation(value = "updates a book entity")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO update(@RequestBody BookVO book) {
		BookVO bookVO = bookService.update(book);
		bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());
		
		return bookVO;
	}
	
	@ApiOperation(value = "gets a book by its id")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO findById(@PathVariable("id") Long id) {
		BookVO bookVO = bookService.findById(id);
		bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		
		return bookVO;
	}
	
	@ApiOperation(value = "gets a list of all books")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<BookVO> findAll() {
		List<BookVO> books = bookService.findAll();
		books.forEach(bookVO -> bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel()));
		
		return books;
	}
}
