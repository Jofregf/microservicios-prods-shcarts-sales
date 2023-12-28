package app.portfoliojofregf.vercel.shoppingcartsservice.utils;
import app.portfoliojofregf.vercel.shoppingcartsservice.dto.ProductDto;
import java.util.List;

public class PriceCalculator {
    public static Double calculateTotalPrice(List<ProductDto> productList){
        return productList.stream().mapToDouble(ProductDto::getPrice).sum();
    }
}
