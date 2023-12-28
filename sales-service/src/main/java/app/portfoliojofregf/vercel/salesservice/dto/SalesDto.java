package app.portfoliojofregf.vercel.salesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class SalesDto {
    private Long id_sales;
    private Date date_sale;
    private Boolean sent;
    private long id_shoppingcart;
    private ShoppingcartDto shoppingcart;
}
