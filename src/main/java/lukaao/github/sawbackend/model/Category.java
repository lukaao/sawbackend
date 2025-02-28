package lukaao.github.sawbackend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.OffsetDateTime;
import java.util.UUID;

@Setter
@Getter
@Data
@Document(collection = "categories")
public class Category {

    @Id
    private String id;
    private String name;

//    private OffsetDateTime createdAt;
//    private OffsetDateTime updatedAt;

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
