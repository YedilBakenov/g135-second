package kz.test.g135secondproject.repository3;

import kz.test.g135secondproject.model3.Magazine;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MagazineRepository extends MongoRepository<Magazine, ObjectId> {
}
