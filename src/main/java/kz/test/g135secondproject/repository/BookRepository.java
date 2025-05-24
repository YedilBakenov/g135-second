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

    @Query("SELECT book FROM Book book " +
            "WHERE book.name ilike concat('%', :word, '%') " +
            "OR book.author ilike concat('%', :word, '%')")
    List<Book>findByOptionWord(String word);
}
