package app.portfoliojofregf.vercel.shoppingcartsservice.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ShoppingcartDto {
    private Double total_price;
    private List<ProductDto> productsList;
}
