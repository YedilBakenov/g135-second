package kz.test.g135secondproject.repository;

import kz.test.g135secondproject.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomBookRepository{

    List<Book> findBooksMoreCost(int cost);

    List<Book> findByNameOrCost(String name, Integer cost);

    List<Book> sortBooksByName();
}
