package lukaao.github.sawbackend.repository;

import lukaao.github.sawbackend.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findByCategory(String category);

    List<Product> findByPriceLessThanEqual(double maxPrice);

    List<Product> findByNameRegex(String regex);

    List<Product> findByCategoryAndPriceBetween(String category, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    List<Product> findByCategoryAndPriceGreaterThanEqual(String category, BigDecimal minPrice, Pageable pageable);

    List<Product> findByCategoryAndPriceLessThanEqual(String category, BigDecimal maxPrice, Pageable pageable);

    List<Product> findByCategory(String category, Pageable pageable);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
}




