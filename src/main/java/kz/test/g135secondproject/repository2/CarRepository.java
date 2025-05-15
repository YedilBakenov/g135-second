package kz.test.g135secondproject.repository2;

import jakarta.transaction.Transactional;
import kz.test.g135secondproject.model2.Author;
import kz.test.g135secondproject.model2.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car, Long> {
}
