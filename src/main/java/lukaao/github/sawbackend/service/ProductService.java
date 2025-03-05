package lukaao.github.sawbackend.service;

import lukaao.github.sawbackend.dto.ProductDTO;
import lukaao.github.sawbackend.model.Product;
import lukaao.github.sawbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Product product = productDTO.toProduct();
        product.setId(UUID.randomUUID().toString());
        product.setCreatedAt(Date.from(Instant.now()));

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
    public long getProductsCount(String category, BigDecimal minPrice, BigDecimal maxPrice) {
        if (category != null && minPrice != null && maxPrice != null) {
            return productRepository.countByCategoryAndPriceBetween(category, minPrice, maxPrice);
        } else if (category != null && minPrice != null) {
            return productRepository.countByCategoryAndPriceGreaterThanEqual(category, minPrice);
        } else if (category != null && maxPrice != null) {
            return productRepository.countByCategoryAndPriceLessThanEqual(category, maxPrice);
        } else if (category != null) {
            return productRepository.countByCategory(category);
        } else if (minPrice != null && maxPrice != null) {
            return productRepository.countByPriceBetween(minPrice, maxPrice);
        } else if (minPrice != null) {
            return productRepository.countByPriceGreaterThanEqual(minPrice);
        } else if (maxPrice != null) {
            return productRepository.countByPriceLessThanEqual(maxPrice);
        } else {
            return productRepository.count();
        }
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
     * Retrieves a list of filtered products based on the provided criteria.
     * The products can be filtered by category, price range, and sorted by the specified field and order.
     *
     * @param category   The category of the products to be filtered. Can be null to ignore category filtering.
     * @param minPrice   The minimum price of the products to be included. Can be null to ignore minimum price filtering.
     * @param maxPrice   The maximum price of the products to be included. Can be null to ignore maximum price filtering.
     * @param sortBy     The field by which the results should be sorted. Valid values are "name", "price", and "createdAt".
     *                   Defaults to "id" if invalid or null.
     * @param order      The order in which the results should be sorted. Can be "asc" for ascending or "desc" for descending.
     *                   Defaults to ascending if null or invalid.
     * @param pageable   Pagination information such as the page number and page size.
     *
     * @return A list of ProductDTO objects representing the filtered and sorted products.
     */
    public List<ProductDTO> getFilteredProducts(String category, BigDecimal minPrice, BigDecimal maxPrice,
                                                String sortBy, String order, Pageable pageable) {
        // Determine the sorting direction based on the provided order parameter.
        Sort.Direction direction = (order != null && order.equalsIgnoreCase("desc")) ? Sort.Direction.DESC : Sort.Direction.ASC;

        // List of valid fields for sorting.
        List<String> validSortFields = List.of("name", "price", "createdAt");

        // Determine the sorting field based on the provided sortBy parameter. Defaults to "id" if invalid or null.
        String sortField = (sortBy != null && validSortFields.contains(sortBy.toLowerCase())) ? sortBy : "id";

        // Create a Pageable object with the specified sorting parameters.
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(direction, sortField));

        // List to store the filtered products.
        List<Product> products;


        // Filter products based on price and category if provided.
        if (minPrice != null && maxPrice != null) {
            products = (category != null)
                    ? productRepository.findByCategoryAndPriceBetween(category, minPrice, maxPrice, sortedPageable)
                    : productRepository.findByPriceBetween(minPrice, maxPrice, sortedPageable);
        } else if (minPrice != null) {
            products = (category != null)
                    ? productRepository.findByCategoryAndPriceGreaterThanEqual(category, minPrice, sortedPageable)
                    : productRepository.findByPriceGreaterThanEqual(minPrice, sortedPageable);
        } else if (maxPrice != null) {
            products = (category != null)
                    ? productRepository.findByCategoryAndPriceLessThanEqual(category, maxPrice, sortedPageable)
                    : productRepository.findByPriceLessThanEqual(maxPrice, sortedPageable);
        } else {
            products = (category != null)
                    ? productRepository.findByCategory(category, sortedPageable)
                    : productRepository.findAll(sortedPageable).getContent();
        }

        // Convert the list of Product entities to ProductDTOs and return the result.
        return products.stream()
                .map(Product::toDto)
                .collect(Collectors.toList());
    }




}
