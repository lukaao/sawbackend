package lukaao.github.sawbackend.service;

import lukaao.github.sawbackend.dto.CategoryDTO;
import lukaao.github.sawbackend.model.Category;
import lukaao.github.sawbackend.model.Product;
import lukaao.github.sawbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing categories.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Retrieves all categories from the repository.
     *
     * @return a list of all categories
     */
    public List<CategoryDTO> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(Category::toDto)
                .collect(Collectors.toList());
    }
}
