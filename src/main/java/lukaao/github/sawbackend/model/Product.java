package lukaao.github.sawbackend.model;


import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lukaao.github.sawbackend.dto.ProductDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;


@Setter
@Getter
@Data
@Document(collection = "products")
public class Product {

    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private Integer stock;
   // private OffsetDateTime createdAt = OffsetDateTime.now();
 //  private OffsetDateTime updatedAt = OffsetDateTime.now();




    //    public OffsetDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(OffsetDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public OffsetDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(OffsetDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }


    public ProductDTO toDto() {
        return new ProductDTO(
                this.id,
                this.name,
                this.description,
                this.price,
                this.category,
                this.stock
        );
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", stock=" + stock +
//                ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
                '}';
    }
}
