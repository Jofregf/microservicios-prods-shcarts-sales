package app.portfoliojofregf.vercel.shoppingcartsservice.model;

import app.portfoliojofregf.vercel.shoppingcartsservice.dto.ProductDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Shoppingcart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_shc;
    private Double total_price;
    @ElementCollection
    private List<ProductDto> productsList;
}