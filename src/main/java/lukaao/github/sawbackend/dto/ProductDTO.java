package lukaao.github.sawbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import lukaao.github.sawbackend.model.Product;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {


    private String id;

    @NotEmpty(message = "The 'name' field is required.")
    @Size(min = 3, max = 100, message = "The 'name' must be between 3 and 100 characters.")
    private String name;

    @NotEmpty(message = "The 'description' field is required.")
    @Size(min = 10, max = 500, message = "The 'description' must be between 10 and 500 characters.")
    private String description;

    @DecimalMin(value = "0.01", message = "The 'price' must be at least 0.01.")
    @DecimalMax(value = "999999.99", message = "The 'price' must not exceed 999999.99.")
    private BigDecimal price;

    @NotEmpty(message = "The 'category' field is required.")
    @Pattern(regexp = "^(Electronics|Clothes|Food)$", message = "Category must be one of: Electronics, Clothes, or Food.")
    private String category;

    @PositiveOrZero(message = "The 'stock' must be a positive number or zero.")
    private Integer stock;

    public Product toProduct() {
        Product product = new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setCategory(this.category);
        product.setStock(this.stock);
        return product;
    }

}
