package kz.test.g135secondproject.service.impl;

import kz.test.g135secondproject.dto.BookDto;
import kz.test.g135secondproject.model.Book;
import kz.test.g135secondproject.repository.BookRepository;
import kz.test.g135secondproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("test")
@RequiredArgsConstructor
public class BookServiceTestImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> searchByWord(String word) {
        return bookRepository.searchByWord(word);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
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
    public Page<Book> getAllBooksByPagination(Pageable p) {
        return null;
    }

    @Override
    public Page<Book> getBooksByCostAndPagination(int cost, Pageable p) {
        return null;
    }
}
