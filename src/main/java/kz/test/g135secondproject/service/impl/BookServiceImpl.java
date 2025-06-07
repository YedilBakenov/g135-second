package kz.test.g135secondproject.service.impl;

import kz.test.g135secondproject.dto.BookDto;
import kz.test.g135secondproject.mapper.BookMapper;
import kz.test.g135secondproject.model.Book;
import kz.test.g135secondproject.repository.BookRepository;
import kz.test.g135secondproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("primary")
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<Book> searchByWord(String word) {
        return bookRepository.searchByWord(word);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public Book findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> getAllBooksByPagination(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<Book> getBooksByCostAndPagination(int cost, Pageable p) {
        return bookRepository.findByCostGreaterThan(cost, p);
    }

    //    public BookDto toDto(Book book){
//        BookDto bookDto = new BookDto();
//        bookDto.setId(book.getId());
//        bookDto.setImageBook(book.getImageBook());
//        bookDto.setSaveDateBook(book.getRegisterBook());
//        bookDto.setUpdateDateBook(book.getUpdateBook());
//        bookDto.setCost(book.getCost());
//        bookDto.setAuthor(book.getAuthor());
//        bookDto.setFullName(book.getName());
//        bookDto.setDescription(book.getDescription());
//
//        return bookDto;
//    }
//
//    @Override
//    public List<BookDto> toDtoList() {
//        List<Book> books = findAllBooks();
//        List<BookDto> bookDtos = new ArrayList<>();
//
//        for(Book book: books){
//            bookDtos.add(toDto(book));
//        }
//
//        return bookDtos;
//    }
}
