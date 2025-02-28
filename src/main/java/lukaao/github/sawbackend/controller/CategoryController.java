package lukaao.github.sawbackend.controller;
import lombok.AllArgsConstructor;
import lukaao.github.sawbackend.model.Category;
import lukaao.github.sawbackend.service.CategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@Validated
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();

    }
}
