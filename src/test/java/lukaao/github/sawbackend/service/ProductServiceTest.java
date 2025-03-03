//package lukaao.github.sawbackend.service;
//
//import lukaao.github.sawbackend.dto.ProductDTO;
//import lukaao.github.sawbackend.model.Product;
//import lukaao.github.sawbackend.repository.ProductRepository;
//import lukaao.github.sawbackend.service.ProductService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import java.math.BigDecimal;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ProductServiceTest {
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @InjectMocks
//    private ProductService productService;
//
//    private ProductDTO productDTO;
//    private Product product;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        // Mock de dados
//        productDTO = new ProductDTO("1231231313","Product1", "Description", BigDecimal.valueOf(100), "Category1", 10);
//
//    }
//
//    @Test
//    void testCreateProduct() {
//        // Mock do repositório
//        when(productRepository.save(any(Product.class))).thenReturn(product);
//
//        // Chama o método a ser testado
//        ProductDTO result = productService.createProduct(productDTO);
//
//        // Verificação
//        assertNotNull(result);
//        assertEquals(productDTO.getName(), result.getName());
//        assertEquals(productDTO.getDescription(), result.getDescription());
//        assertEquals(productDTO.getPrice(), result.getPrice());
//        assertEquals(productDTO.getCategory(), result.getCategory());
//        assertEquals(productDTO.getStock(), result.getStock());
//
//        // Verifica se o repositório foi chamado corretamente
//        verify(productRepository).save(any(Product.class));
//    }
//
//    @Test
//    void testGetProductById() {
//        // Mock de findById
//        when(productRepository.findById("id")).thenReturn(Optional.of(product));
//
//        // Chama o método a ser testado
//        ProductDTO result = productService.getProductById("id");
//
//        // Verificação
//        assertNotNull(result);
//        assertEquals("Product1", result.getName());
//
//        // Verifica se o repositório foi chamado corretamente
//        verify(productRepository).findById("id");
//    }
//
//    @Test
//    void testGetProductByIdThrowsException() {
//        // Mock de findById retornando Optional.empty()
//        when(productRepository.findById("id")).thenReturn(Optional.empty());
//
//        // Testa exceção lançada
//        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
//            productService.getProductById("id");
//        });
//
//        assertEquals("Product not found", thrown.getMessage());
//    }
//
//    @Test
//    void testUpdateProduct() {
//        // Mock de findById e save
//        when(productRepository.findById("id")).thenReturn(Optional.of(product));
//        when(productRepository.save(any(Product.class))).thenReturn(product);
//
//        // Novo DTO com dados atualizados
//        ProductDTO updatedProductDTO = new ProductDTO("123123123","UpdatedName", "UpdatedDescription", BigDecimal.valueOf(200), "UpdatedCategory", 20);
//
//        // Chama o método a ser testado
//        ProductDTO result = productService.updateProduct("id", updatedProductDTO);
//
//        // Verificação
//        assertNotNull(result);
//        assertEquals("UpdatedName", result.getName());
//        assertEquals("UpdatedDescription", result.getDescription());
//        assertEquals(BigDecimal.valueOf(200), result.getPrice());
//        assertEquals("UpdatedCategory", result.getCategory());
//        assertEquals(20, result.getStock());
//
//        // Verifica se o repositório foi chamado corretamente
//        verify(productRepository).findById("id");
//        verify(productRepository).save(any(Product.class));
//    }
//}
