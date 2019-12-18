package br.com.lassulfi.converter.mocks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.lassulfi.data.model.Book;
import br.com.lassulfi.data.vo.BookVO;

public class MockBook {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Book mockEntity() throws ParseException {
		return mockEntity(1);
	}
	
	public BookVO mockVO() throws ParseException {
		return mockVO(1);
	}
	
	public List<Book> mockEntityList() throws ParseException {
		List<Book> books = new ArrayList<>();
		for(int i = 0; i < 14; i++) {
			books.add(mockEntity(i));
		}
		
		return books;
	}
	
	public List<BookVO> mockVOList() throws ParseException {
		List<BookVO> books = new ArrayList<>();
		for(int i = 0; i < 14; i++) {
			books.add(mockVO(i));
		}
		
		return books;
	}
	
	private Book mockEntity(Integer number) throws ParseException {
		Book book = new Book();
		
		book.setAuthor("Author Test " + number);
		book.setId(Long.valueOf(number));
		book.setTitle("Book Title " + number);
		book.setLaunchDate(sdf.parse(number + "/01/2001"));
		book.setPrice(Float.valueOf(number));
		
		return book;
	}
	
	private BookVO mockVO(Integer number) throws ParseException {
		BookVO book = new BookVO();
		
		book.setAuthor("Author Test " + number);
		book.setKey(Long.valueOf(number));
		book.setTitle("Book Title " + number);
		book.setLaunchDate(sdf.parse(number + "/01/2001"));
		book.setPrice(Float.valueOf(number));
		
		return book;
	}
}
