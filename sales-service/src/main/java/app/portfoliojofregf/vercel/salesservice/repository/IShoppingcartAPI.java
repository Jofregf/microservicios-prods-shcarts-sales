package app.portfoliojofregf.vercel.salesservice.repository;

import app.portfoliojofregf.vercel.salesservice.dto.ShoppingcartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "shoppingcarts-service")
public interface IShoppingcartAPI {
    @GetMapping("/api/shoppingcart/{id_shc}")
    public ShoppingcartDto getShoppingcartById(@PathVariable Long id_shc);

    @GetMapping("/api/shoppingcart/getall")
    public List<ShoppingcartDto> getAllShoppingcart();

}
