package lukaao.github.sawbackend.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lukaao.github.sawbackend.dto.ProductDTO;
import lukaao.github.sawbackend.dto.ProductListDTO;
import lukaao.github.sawbackend.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    // Endpoint to create a new product
    @PostMapping
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO product) {
        return productService.createProduct(product);
    }

    // Endpoint to get a product by ID
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    // Endpoint to update an existing product
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable String id , @Valid @RequestBody ProductDTO product) {
        return productService.updateProduct(id, product);
    }

    // Endpoint to delete a product by ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }


    @GetMapping
    public ProductListDTO getProducts(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
            @RequestParam(value = "order", required = false, defaultValue = "asc") String order,
            @RequestParam(value = "page", required = false, defaultValue = "1") @Min(1) int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") @Min(1) int pageSize) {


        if (!sortBy.matches("^(name|price|createdAt)$")) {
            throw new IllegalArgumentException("Invalid sortBy value. Accepted values: name, price, createdAt.");
        }

        if (!order.matches("^(asc|desc)$")) {
            throw new IllegalArgumentException("Invalid order value. Accepted values: asc, desc.");
        }

        Pageable pageable = PageRequest.of(page - 1, pageSize);


        List<ProductDTO> products = productService.getFilteredProducts(category, minPrice, maxPrice, sortBy, order, pageable);

        Long count = productService.getProductsCount();
        Long pagesCount = (count / pageSize) + 1;


        return new ProductListDTO(products, page, pageSize, Math.toIntExact(pagesCount), count);
    }
}
