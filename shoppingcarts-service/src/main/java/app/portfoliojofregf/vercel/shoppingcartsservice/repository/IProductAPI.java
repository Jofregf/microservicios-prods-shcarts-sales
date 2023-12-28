package app.portfoliojofregf.vercel.shoppingcartsservice.repository;
import app.portfoliojofregf.vercel.shoppingcartsservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@FeignClient(name="products-service")
public interface IProductAPI {
    @GetMapping("/api/products/findByNames")
    public List<ProductDto> getProductsAndPricesByNames(@RequestParam("name") List<String> names);
}