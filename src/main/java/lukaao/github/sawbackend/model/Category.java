package lukaao.github.sawbackend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lukaao.github.sawbackend.dto.CategoryDTO;
import lukaao.github.sawbackend.dto.ProductDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@Data
@Document(collection = "categories")
public class Category {

    @Id
    private String id;
    private String name;

    private Date createdAt;
    private Date updatedAt;

    public Category(String id, String name, Date createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }


    public CategoryDTO toDto() {
        return new CategoryDTO(
                this.id,
                this.name,
                this.createdAt,
                this.updatedAt
        );
    }
}
