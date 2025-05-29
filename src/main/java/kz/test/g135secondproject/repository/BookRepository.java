package kz.test.g135secondproject.repository;

import jakarta.transaction.Transactional;
import kz.test.g135secondproject.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
    List<Book>findByAuthor(String author);
    Book findBookByAuthorAndCost(String author, int cost);
    Book findBookByAuthorOrCost(String author, int cost);

    @Query("SELECT b FROM Book b WHERE " +
            "b.name ilike concat('%', :word, '%') " +
            "OR b.author ilike concat('%', :word, '%')")
    List<Book>searchByWord(String word);
}
