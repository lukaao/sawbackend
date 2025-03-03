package lukaao.github.sawbackend.service;

import lukaao.github.sawbackend.dto.ProductDTO;
import lukaao.github.sawbackend.model.Product;
import lukaao.github.sawbackend.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    @Autowired
    private ProductService productService;

    private ProductDTO productDTO;
    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup sample ProductDTO and Product
        productDTO = new ProductDTO();
        productDTO.setName("Test Product");
        productDTO.setDescription("Test Description");
        productDTO.setPrice(BigDecimal.valueOf(100.0));
        productDTO.setCategory("Test Category");
        productDTO.setStock(10);

        product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(BigDecimal.valueOf(100.0));
        product.setCategory("Test Category");
        product.setStock(10);
    }

    @Test
    public void testCreateProduct() {
        System.out.println("Testing createProduct...");

        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO result = productService.createProduct(productDTO);

        System.out.println("Product created with name: " + result.getName());
        assertNotNull(result);
        assertEquals(productDTO.getName(), result.getName());
        verify(productRepository, times(1)).save(any(Product.class));

        System.out.println("createProduct test passed!");
    }

    @Test
    public void testGetProductById() {
        System.out.println("Testing getProductById...");

        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));

        ProductDTO result = productService.getProductById(product.getId());

        System.out.println("Product retrieved with ID: " + result.getId());
        assertNotNull(result);
        assertEquals(product.getId(), result.getId());
        verify(productRepository, times(1)).findById(anyString());

        System.out.println("getProductById test passed!");
    }

    @Test
    public void testUpdateProduct() {
        System.out.println("Testing updateProduct...");

        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        productDTO.setName("Updated Product");
        ProductDTO result = productService.updateProduct(product.getId(), productDTO);

        System.out.println("Product updated to: " + result.getName());
        assertNotNull(result);
        assertEquals("Updated Product", result.getName());
        verify(productRepository, times(1)).save(any(Product.class));

        System.out.println("updateProduct test passed!");
    }

    @Test
    public void testDeleteProduct() {
        System.out.println("Testing deleteProduct...");

        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));

        productService.deleteProduct(product.getId());

        System.out.println("Product deleted with ID: " + product.getId());
        verify(productRepository, times(1)).delete(any(Product.class));

        System.out.println("deleteProduct test passed!");
    }

    @Test
    public void testGetFilteredProducts() {
        System.out.println("Testing getFilteredProducts...");

        when(productRepository.findByCategoryAndPriceBetween(anyString(), any(BigDecimal.class), any(BigDecimal.class), any(PageRequest.class)))
                .thenReturn(List.of(product));

        var result = productService.getFilteredProducts("Test Category", BigDecimal.valueOf(50), BigDecimal.valueOf(150), "name", "asc", PageRequest.of(0, 10));

        System.out.println("Filtered products retrieved: " + result.size());
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(productRepository, times(1)).findByCategoryAndPriceBetween(anyString(), any(BigDecimal.class), any(BigDecimal.class), any(PageRequest.class));

        System.out.println("getFilteredProducts test passed!");
    }
}
