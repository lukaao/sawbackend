package lukaao.github.sawbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;
import lukaao.github.sawbackend.model.Category;
import lukaao.github.sawbackend.model.Product;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private String id;
    private String name;

    private Date createdAt;
    private Date updatedAt;

    public CategoryDTO(String id, String name, Date createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

}
