package kz.test.g135secondproject.service;

import kz.test.g135secondproject.dto.BookDto;
import kz.test.g135secondproject.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    List<Book> searchByWord(String word);

     List<Book> findAllBooks();

    Book findBookById(Long id);

    Book findByName(String name);

    List<Book> findByAuthor(String author);

    void updateBook(Book book);

    void saveBook(Book book);

    void deleteBookById(Long id);

    Page<Book>getAllBooksByPagination(Pageable p);

    Page<Book> getBooksByCostAndPagination(int cost, Pageable p);

//    BookDto toDto(Book book);
//
//    List<BookDto> toDtoList();
}
