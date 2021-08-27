package com.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaguides.springboot.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	public List<Book> findByIsbn(Integer isbn);
	
	public List<Book> findByTitle(String title);
	
	public List<Book> findByAuthors(String authors);

}
