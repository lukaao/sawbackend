package lukaao.github.sawbackend.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lukaao.github.sawbackend.dto.CategoryDTO;
import lukaao.github.sawbackend.model.Category;
import lukaao.github.sawbackend.service.CategoryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@Validated
@Tag(name = "Category")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Find Many")
    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();

    }
}
