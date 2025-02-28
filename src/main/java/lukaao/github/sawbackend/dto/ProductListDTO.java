package lukaao.github.sawbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class ProductListDTO {
    private List<ProductDTO> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;
}
