package kz.test.g135secondproject.repository2;

import jakarta.transaction.Transactional;
import kz.test.g135secondproject.model2.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
