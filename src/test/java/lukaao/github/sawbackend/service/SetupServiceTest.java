package lukaao.github.sawbackend.service;

import lukaao.github.sawbackend.model.Category;
import lukaao.github.sawbackend.model.UserApp;
import lukaao.github.sawbackend.repository.CategoryRepository;
import lukaao.github.sawbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.mockito.Mockito.*;

@SpringBootTest
public class SetupServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    @Autowired
    private SetupService setupService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCreateCategoriesWhenExists() {
        System.out.println("Testing category creation when categories exist...");

        // Mock category repository behavior
        when(categoryRepository.count()).thenReturn(3L);  // Categories exist

        setupService.setup();

        // Verify that saveAll was not called when categories exist
        verify(categoryRepository, never()).saveAll(anyList());
        System.out.println("No categories were created as they already exist.");

        System.out.println("Category creation test passed when categories exist!");
    }

    @Test
    public void testCreateUsersWhenExists() {
        System.out.println("Testing user creation when users exist...");

        // Mock user repository behavior
        when(userRepository.count()).thenReturn(2L);  // Users exist

        setupService.setup();

        // Verify that save was not called when users exist
        verify(userRepository, never()).save(any(UserApp.class));
        System.out.println("No users were created as they already exist.");

        System.out.println("User creation test passed when users exist!");
    }



}
