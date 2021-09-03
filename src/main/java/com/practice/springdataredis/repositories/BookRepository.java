package com.practice.springdataredis.repositories;

import com.practice.springdataredis.models.Book;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    private final RedisTemplate<String, Book> bookRedisTemplate;
    private final String HASH = "Book";

    public BookRepository(RedisTemplate<String, Book> bookRedisTemplate) {
        this.bookRedisTemplate = bookRedisTemplate;
    }

    public void save(Book book) {
        bookRedisTemplate.opsForHash().put(HASH, book.getId(), book );
    }

    public List<Book> findAll(){
         return bookRedisTemplate.opsForHash().values(HASH)
                 .stream()
                 .map(book -> (Book)book)
                 .collect(Collectors.toList());
    }

    public Book findById(String id) {
        System.out.println("From DB");
        return (Book) bookRedisTemplate.opsForHash().get(HASH, id);
    }

    public void delete(String id) {
        bookRedisTemplate.opsForHash().delete(HASH, id);
    }
}
