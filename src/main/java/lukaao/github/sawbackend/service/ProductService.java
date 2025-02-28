package lukaao.github.sawbackend.service;

import lukaao.github.sawbackend.dto.ProductDTO;
import lukaao.github.sawbackend.model.Product;
import lukaao.github.sawbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Method to create a new product
    public ProductDTO createProduct(ProductDTO productDTO) {
        // Convert the DTO to the Product entity
        Product product = productDTO.toProduct();

        // Save the product in the repository
        Product savedProduct = productRepository.save(product);

        // Convert the saved Product entity back to DTO and return it
        return savedProduct.toDto();
    }

    // Method to get a product by its ID
    public ProductDTO getProductById(String id) {
        // Find the product by its ID, throw exception if not found
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Convert the Product entity to DTO and return it
        return product.toDto();
    }


    public Long getProductsCount() {
        return productRepository.count();
    }

    // Method to update a product
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        // Find the product by its ID, throw exception if not found
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update the product fields with the values from the DTO
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setStock(productDTO.getStock());

        // Save the updated product
        Product updatedProduct = productRepository.save(product);

        // Convert the updated Product entity to DTO and return it
        return updatedProduct.toDto();
    }

    // Method to delete a product
    public void deleteProduct(String id) {
        // Find the product by its ID, throw exception if not found
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Delete the product from the repository
        productRepository.delete(product);
    }


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