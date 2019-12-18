package br.com.lassulfi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lassulfi.converter.DozerConverter;
import br.com.lassulfi.data.model.Book;
import br.com.lassulfi.data.vo.BookVO;
import br.com.lassulfi.exceptions.ResourceNotFoundException;
import br.com.lassulfi.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	public BookVO create(BookVO book) {
		var bookEntity = DozerConverter.parseObject(book, Book.class);
		var bookVO = DozerConverter.parseObject(bookRepository.save(bookEntity), BookVO.class);
		
		return bookVO;
	}
	
	public BookVO update(BookVO book) {
		Book bookEntity = bookRepository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		bookEntity.setAuthor(book.getAuthor());
		bookEntity.setLaunchDate(book.getLaunchDate());
		bookEntity.setPrice(book.getPrice());
		bookEntity.setTitle(book.getTitle());
		
		var bookVO = DozerConverter.parseObject(bookRepository.save(bookEntity), BookVO.class);
		return bookVO;
	}
	
	public void delete(Long id) {
		Book bookObj = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		bookRepository.delete(bookObj);
	}
	
	public BookVO findById(Long id) {
		var bookEntity = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		return DozerConverter.parseObject(bookEntity, BookVO.class);
	}
	
	public List<BookVO> findAll() {
		return DozerConverter.parseListOfObjects(bookRepository.findAll(), BookVO.class);
	}
}
