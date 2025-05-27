package kz.test.g135secondproject.service;

import kz.test.g135secondproject.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Book> searchByWord(String word);

    List<Book> findAllBooks();

    Book findBookById(Long id);

    Book findByName(String name);

    Book findByAuthor(String author);

    void updateBook(Book book);

    void saveBook(Book book);

    void deleteBookById(Long id);
}
