package lukaao.github.sawbackend.repository;

import lukaao.github.sawbackend.model.UserApp;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<UserApp, String> {
    Optional<UserApp> findByName(String name);
}
