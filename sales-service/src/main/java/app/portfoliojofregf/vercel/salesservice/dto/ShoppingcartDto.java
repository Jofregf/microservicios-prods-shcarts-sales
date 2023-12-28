package app.portfoliojofregf.vercel.salesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ShoppingcartDto {
    private Double total_price;
    private List<ProductDto> productsList;
}