package app.portfoliojofregf.vercel.shoppingcartsservice.dto;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class ProductDto {

    private String name;
    private Double price;
}