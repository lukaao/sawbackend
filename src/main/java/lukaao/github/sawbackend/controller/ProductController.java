package lukaao.github.sawbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lukaao.github.sawbackend.dto.ProductDTO;
import lukaao.github.sawbackend.dto.ProductListDTO;
import lukaao.github.sawbackend.service.ProductService;
import lukaao.github.sawbackend.validation.ValidUUID;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller class for handling product-related API endpoints.
 */
@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@Validated
@Tag(name = "Products")
public class ProductController {

    private final ProductService productService;

    /**
     * Endpoint to create a new product.
     *
     * @param product The product data transfer object containing product details.
     * @return The created product as a DTO.
     */
    @PostMapping
    @Operation(summary = "Save")
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO product) {
        return productService.createProduct(product);
    }

    /**
     * Endpoint to get a product by its ID.
     *
     * @param id The product ID, validated as a UUID.
     * @return The requested product as a DTO.
     */
    @Operation(summary = "Find by id")
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable @ValidUUID String id) {
        return productService.getProductById(id);
    }

    /**
     * Endpoint to update an existing product.
     *
     * @param id      The product ID, validated as a UUID.
     * @param product The updated product data.
     * @return The updated product as a DTO.
     */

    @Operation(summary = "Update")
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable @ValidUUID String id, @Valid @RequestBody ProductDTO product) {
        return productService.updateProduct(id, product);
    }

    /**
     * Endpoint to delete a product by its ID.
     *
     * @param id The product ID.
     */

    @Operation(summary = "Delete")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    /**
     * Endpoint to retrieve a list of products with optional filtering and pagination.
     *
     * @param category The category to filter products by (optional).
     * @param minPrice The minimum price filter (optional).
     * @param maxPrice The maximum price filter (optional).
     * @param sortBy   The field to sort by (default: "name"). Accepted values: "name", "price", "createdAt".
     * @param order    The sorting order (default: "asc"). Accepted values: "asc", "desc".
     * @param page     The page number for pagination (default: 1, must be >= 1).
     * @param pageSize The number of products per page (default: 10, must be >= 1).
     * @return A paginated list of products with metadata.
     */
    @GetMapping
    @Operation(summary = "Find Many")
    public ProductListDTO getProducts(
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(value = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
            @RequestParam(value = "order", required = false, defaultValue = "asc") String order,
            @RequestParam(value = "page", required = false, defaultValue = "1") @Min(1) int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") @Min(1) int pageSize) {

        // Validate sorting field
        if (!sortBy.matches("^(name|price|createdAt)$")) {
            throw new IllegalArgumentException("Invalid sortBy value. Accepted values: name, price, createdAt.");
        }

        // Validate sorting order
        if (!order.matches("^(asc|desc)$")) {
            throw new IllegalArgumentException("Invalid order value. Accepted values: asc, desc.");
        }

        Pageable pageable = PageRequest.of(page - 1, pageSize);

        // Retrieve filtered products
        List<ProductDTO> products = productService.getFilteredProducts(category, minPrice, maxPrice, sortBy, order, pageable);

        // Retrieve product count and calculate total pages
        Long count = productService.getProductsCount();
        Long pagesCount = (count / pageSize) + 1;

        return new ProductListDTO(products, page, pageSize, Math.toIntExact(pagesCount), count);
    }
}