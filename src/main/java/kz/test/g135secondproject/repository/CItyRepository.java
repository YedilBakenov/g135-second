package kz.test.g135secondproject.repository;

import jakarta.transaction.Transactional;
import kz.test.g135secondproject.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CItyRepository extends JpaRepository<City, Long> {
}
