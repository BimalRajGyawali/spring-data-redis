package com.practice.springdataredis.controllers;

import com.practice.springdataredis.models.Book;
import com.practice.springdataredis.repositories.BookRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/")
    public void save(@RequestBody Book book){
         bookRepository.save(book);
    }

    @GetMapping("/")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "Product")
    public Book findById(@PathVariable String id){
        return bookRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "Product")
    public void delete(@PathVariable String id){
        bookRepository.delete(id);
    }

}
