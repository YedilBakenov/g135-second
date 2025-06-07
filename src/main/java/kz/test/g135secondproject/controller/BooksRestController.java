package kz.test.g135secondproject.controller;


import kz.test.g135secondproject.dto.BookDto;
import kz.test.g135secondproject.mapper.BookMapper;
import kz.test.g135secondproject.model.Book;
import kz.test.g135secondproject.repository.CustomBookRepository;
import kz.test.g135secondproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rest/")
@RequiredArgsConstructor
public class BooksRestController {
    // get post put path option head delete

    private final CustomBookRepository customBookRepository;
    private final BookMapper bookMapper;

    @Autowired
    @Qualifier("primary")
    private BookService bookService;

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookMapper.toDtoList(bookService.findAllBooks());
    }

    @GetMapping(value = "/more-cost")
    public List<BookDto> getBooksMoreCost(@RequestParam int cost) {

        return bookMapper.toDtoList(customBookRepository.findBooksMoreCost(cost));
    }

    @GetMapping(value = "/name-or-cost")
    public List<BookDto> getBooksByCostOrName(@RequestParam(required = false) String name,
                                           @RequestParam(required = false) Integer cost) {

        return bookMapper.toDtoList(customBookRepository.findByNameOrCost(name, cost));
    }

    @GetMapping(value = "/sort")
    public List<BookDto> sortedMethodByName() {

        return bookMapper.toDtoList(customBookRepository.sortBooksByName());
    }

    @GetMapping(value = "/search-by-word")
    public List<BookDto> getBooksByWord(@RequestParam String word) {
        return bookMapper.toDtoList(bookService.searchByWord(word));
    }


    @GetMapping(value = "/details-book/{id}")
    public BookDto getBookById(@PathVariable long id) {
        return bookMapper.toDto(bookService.findBookById(id));
    }

    @GetMapping(value = "/get-by-name/{name}")
    public BookDto getByName(@PathVariable String name) {
        return bookMapper.toDto(bookService.findByName(name));
    }

    @GetMapping(value = "/by-author/{author}")
    public List<BookDto> getByAuthor(@PathVariable String author) {
        return bookMapper.toDtoList(bookService.findByAuthor(author));
    }

    @PutMapping(value = "/update-book")
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }

    @PostMapping(value = "/add-book")
    public void addBook(@RequestBody Book book) {
        bookService.saveBook(book);
    }

    @DeleteMapping(value = "/delete-book")
    public void deleteBook(@RequestParam long id) {
        bookService.deleteBookById(id);
    }

    @GetMapping(value = "/all-books-pagination")
    public Page<Book> getAllBooksByPagination(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "2") int size,
                                              @RequestParam(defaultValue = "id") String parameter,
                                              @RequestParam(defaultValue = "asc") String direction){
        Sort sort = direction.equalsIgnoreCase("desc")? Sort.by(parameter).descending(): Sort.by(parameter).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return bookService.getAllBooksByPagination(pageable);
    }

    @GetMapping(value = "/books-by-cost-pagination")
    public Page<Book> getBooksByCostPagination(@RequestParam int cost,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "1") int size,
                                               @RequestParam(defaultValue = "cost") String parameter,
                                               @RequestParam(defaultValue = "desc") String direction){

        Sort sort = direction.equalsIgnoreCase("asc")? Sort.by(parameter).ascending(): Sort.by(parameter).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return bookService.getBooksByCostAndPagination(cost, pageable);

    }


}
