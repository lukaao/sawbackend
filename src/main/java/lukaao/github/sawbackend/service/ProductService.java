package lukaao.github.sawbackend.service;

import lukaao.github.sawbackend.dto.ProductDTO;
import lukaao.github.sawbackend.model.Product;
import lukaao.github.sawbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service class for managing products.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Creates a new product.
     *
     * @param productDTO the product data transfer object containing product details
     * @return the created product as a DTO
     */
    public ProductDTO createProduct(ProductDTO productDTO) {
        String uuid = UUID.randomUUID().toString();
        Product product = productDTO.toProduct();
        product.setId(uuid);
        product.setCreatedAt(Date.from(Instant.now()));
        product.setUpdatedAt(Date.from(Instant.now()));


        Product savedProduct = productRepository.save(product);
        return savedProduct.toDto();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the ID of the product
     * @return the product DTO
     * @throws RuntimeException if the product is not found
     */
    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return product.toDto();
    }

    /**
     * Retrieves the total number of products.
     *
     * @return the count of products
     */
    public Long getProductsCount() {
        return productRepository.count();
    }

    /**
     * Updates an existing product.
     *
     * @param id         the ID of the product to update
     * @param productDTO the updated product details
     * @return the updated product as a DTO
     * @throws RuntimeException if the product is not found
     */
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setStock(productDTO.getStock());
        product.setUpdatedAt(Date.from(Instant.now()));

        Product updatedProduct = productRepository.save(product);
        return updatedProduct.toDto();
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete
     * @throws RuntimeException if the product is not found
     */
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }

    /**
     * Retrieves filtered products based on category, price range, and sorting criteria.
     *
     * @param category the category of the products
     * @param minPrice the minimum price of the products
     * @param maxPrice the maximum price of the products
     * @param sortBy   the field to sort by
     * @param order    the sorting order (asc/desc)
     * @param pageable the pagination information
     * @return a list of filtered product DTOs
     */
    public List<ProductDTO> getFilteredProducts(String category, BigDecimal minPrice, BigDecimal maxPrice,
                                                String sortBy, String order, Pageable pageable) {
        List<Product> products;

        if (category != null && minPrice != null && maxPrice != null) {
            products = productRepository.findByCategoryAndPriceBetween(category, minPrice, maxPrice, pageable);
        } else if (category != null && minPrice != null) {
            products = productRepository.findByCategoryAndPriceGreaterThanEqual(category, minPrice, pageable);
        } else if (category != null && maxPrice != null) {
            products = productRepository.findByCategoryAndPriceLessThanEqual(category, maxPrice, pageable);
        } else if (category != null) {
            products = productRepository.findByCategory(category, pageable);
        } else if (minPrice != null && maxPrice != null) {
            products = productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
        } else {
            products = productRepository.findAll(pageable).getContent();
        }

        return products.stream()
                .map(Product::toDto)
                .collect(Collectors.toList());
    }
}
