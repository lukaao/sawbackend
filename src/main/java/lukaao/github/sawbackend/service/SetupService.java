package lukaao.github.sawbackend.service;

import jakarta.annotation.PostConstruct;
import lukaao.github.sawbackend.model.Category;
import lukaao.github.sawbackend.model.UserApp;
import lukaao.github.sawbackend.repository.CategoryRepository;
import lukaao.github.sawbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SetupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostConstruct
    public void setup() {
        createCategories();
        createUsers();
    }

    private void createCategories() {
        if (categoryRepository.count() == 0) {
            List<Category> categories = List.of(
                    new Category(UUID.randomUUID().toString(), "Electronics"),
                    new Category(UUID.randomUUID().toString(), "Clothes"),
                    new Category(UUID.randomUUID().toString(), "Food")
            );
            categoryRepository.saveAll(categories);
        }
    }

    private void createUsers() {

        if (userRepository.count() == 0) {
            UserApp admin = new UserApp(UUID.randomUUID().toString(), "admin", "admin@example.com", encoder.encode("admin"), "ADMIN");
            UserApp user = new UserApp(UUID.randomUUID().toString(), "user", "user@example.com", encoder.encode("user"), "USER");
            userRepository.save(admin);
            userRepository.save(user);
        }
    }
}
