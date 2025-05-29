package kz.test.g135secondproject.controller;


import kz.test.g135secondproject.model.Book;
import kz.test.g135secondproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rest/")
@RequiredArgsConstructor
public class BooksRestController {
    // get post put path option head delete

    @Autowired
    @Qualifier("primary")
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.findAllBooks();
    }

    @GetMapping(value = "/search-by-word")
    public List<Book> getBooksByWord(@RequestParam String word){
        return bookService.searchByWord(word);
    }

    @GetMapping(value = "/details-book/{id}")
    public Book getBookById(@PathVariable long id){
        return bookService.findBookById(id);
    }

    @GetMapping(value = "/get-by-name/{name}")
    public Book getByName(@PathVariable String name){
        return bookService.findByName(name);
    }

    @GetMapping(value = "/by-author/{author}")
    public List<Book>getByAuthor(@PathVariable String author){
        return bookService.findByAuthor(author);
    }

    @PutMapping(value = "/update-book")
    public void updateBook(@RequestBody Book book){
        bookService.updateBook(book);
    }

    @PostMapping(value = "/add-book")
    public void addBook(@RequestBody Book book){
        bookService.saveBook(book);
    }

    @DeleteMapping(value = "/delete-book")
    public void deleteBook(@RequestParam long id){
        bookService.deleteBookById(id);
    }






}
