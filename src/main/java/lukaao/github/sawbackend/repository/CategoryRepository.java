package lukaao.github.sawbackend.repository;

import lukaao.github.sawbackend.model.Category;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}




