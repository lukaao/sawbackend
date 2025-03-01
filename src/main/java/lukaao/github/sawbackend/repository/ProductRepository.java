package lukaao.github.sawbackend.repository;

import lukaao.github.sawbackend.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository interface for managing Product entities in MongoDB.
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    /**
     * Finds products by category.
     *
     * @param category the category to filter by
     * @return a list of products in the specified category
     */
    List<Product> findByCategory(String category);

    /**
     * Finds products with a price less than or equal to the specified maximum price.
     *
     * @param maxPrice the maximum price
     * @return a list of products matching the criteria
     */
    List<Product> findByPriceLessThanEqual(double maxPrice);

    /**
     * Finds products whose names match a given regex pattern.
     *
     * @param regex the regex pattern to match product names
     * @return a list of matching products
     */
    List<Product> findByNameRegex(String regex);

    /**
     * Finds products by category and price range with pagination.
     *
     * @param category the category to filter by
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @param pageable pagination details
     * @return a paginated list of matching products
     */
    List<Product> findByCategoryAndPriceBetween(String category, BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    /**
     * Finds products by category with a minimum price and pagination.
     *
     * @param category the category to filter by
     * @param minPrice the minimum price
     * @param pageable pagination details
     * @return a paginated list of matching products
     */
    List<Product> findByCategoryAndPriceGreaterThanEqual(String category, BigDecimal minPrice, Pageable pageable);

    /**
     * Finds products by category with a maximum price and pagination.
     *
     * @param category the category to filter by
     * @param maxPrice the maximum price
     * @param pageable pagination details
     * @return a paginated list of matching products
     */
    List<Product> findByCategoryAndPriceLessThanEqual(String category, BigDecimal maxPrice, Pageable pageable);

    /**
     * Finds products by category with pagination.
     *
     * @param category the category to filter by
     * @param pageable pagination details
     * @return a paginated list of matching products
     */
    List<Product> findByCategory(String category, Pageable pageable);

    /**
     * Finds products within a specified price range with pagination.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @param pageable pagination details
     * @return a paginated list of matching products
     */
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
}