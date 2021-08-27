package com.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaguides.springboot.exception.ResourceNotFoundException;
import com.javaguides.springboot.model.Book;
import com.javaguides.springboot.repository.BookRepository;

@CrossOrigin(origins = "http://localhost:4200") //To enable CORS on the server
@RestController
@RequestMapping("/api/v1")
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	
	//get all books
	@GetMapping("/books")
	public List<Book> getAllBooks()
	{
		return bookRepository.findAll();
	}
	
	
	@GetMapping("/books/isbn/{isbn}")
	public ResponseEntity<List<Book>> findIsbn(@PathVariable int isbn)
	{
		return new ResponseEntity<List<Book>>(bookRepository.findByIsbn(isbn), HttpStatus.OK);
		
	}
	
	@GetMapping("/books/title/{title}")
	public ResponseEntity<List<Book>> findTitle(@PathVariable String title)
	{
		return new ResponseEntity<List<Book>>(bookRepository.findByTitle(title), HttpStatus.OK);
		
	}
	
	@GetMapping("/books/author/{authors}")
	public ResponseEntity<List<Book>> findAuthor(@PathVariable String authors)
	{
		return new ResponseEntity<List<Book>>(bookRepository.findByAuthors(authors), HttpStatus.OK);
		
	}
	
	
	// create book rest api
	@PostMapping("/books")
	public Book createBook(@RequestBody Book book)
	{
		return bookRepository.save(book);
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id)
	{
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book Not found with id" + id));

		return ResponseEntity.ok(book);
		
	}
	
	// update book rest api
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id,@RequestBody Book bookDetails)
	{
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book Not found with id" + id));
		book.setTitle(bookDetails.getTitle());
		book.setAuthors(bookDetails.getAuthors());
		book.setYear(bookDetails.getYear());
		book.setPrice(bookDetails.getPrice());
		
		Book updatedBook = bookRepository.save(book);
		return ResponseEntity.ok(updatedBook);
	}
	
	
	//delete book rest api
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteBook(@PathVariable Long id)
	{
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book Not found with id" + id));
		bookRepository.delete(book);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
