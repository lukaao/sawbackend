package lukaao.github.sawbackend.service;

import jakarta.annotation.PostConstruct;
import lukaao.github.sawbackend.model.Category;
import lukaao.github.sawbackend.model.User;
import lukaao.github.sawbackend.repository.CategoryRepository;
import lukaao.github.sawbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SetupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostConstruct
    public void setup() {
        createCategories();
        createUsers();
    }

    private void createCategories() {
        if (categoryRepository.count() == 0) {
            List<Category> categories = List.of(
                    new Category(UUID.randomUUID().toString(),"Electronics"),
                    new Category(UUID.randomUUID().toString(),"Clothes"),
                    new Category(UUID.randomUUID().toString(),"Food")
            );
            categoryRepository.saveAll(categories);
        }
    }

    private void createUsers() {
        if (userRepository.count() == 0) {
            User admin = new User(UUID.randomUUID().toString(), "Admin", "admin@example.com", "admin", "ADMIN");
            User user = new User(UUID.randomUUID().toString(), "User", "user@example.com", "user", "USER");
            userRepository.save(admin);
            userRepository.save(user);
        }
    }
}
