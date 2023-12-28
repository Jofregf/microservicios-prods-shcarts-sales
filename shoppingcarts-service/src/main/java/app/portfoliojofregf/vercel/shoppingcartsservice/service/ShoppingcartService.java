package app.portfoliojofregf.vercel.shoppingcartsservice.service;
import app.portfoliojofregf.vercel.shoppingcartsservice.dto.ProductDto;
import app.portfoliojofregf.vercel.shoppingcartsservice.exception.ResourceNotFoundException;
import app.portfoliojofregf.vercel.shoppingcartsservice.model.Shoppingcart;
import app.portfoliojofregf.vercel.shoppingcartsservice.repository.IProductAPI;
import app.portfoliojofregf.vercel.shoppingcartsservice.repository.IShoppingcartRepository;
import app.portfoliojofregf.vercel.shoppingcartsservice.utils.PriceCalculator;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShoppingcartService implements IShoppingcartService{
    @Autowired
    private IProductAPI productAPI;
    @Autowired
    private IShoppingcartRepository shoppingcartRepository;

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallbackGetProductsName")
    @Retry(name="products-service")
    @Transactional

    public Shoppingcart addShoppingcart(List<String> productsName, Shoppingcart shoppingcart) throws ResourceNotFoundException {

        List<ProductDto> productsList = productAPI.getProductsAndPricesByNames(productsName);

        if (productsList.isEmpty()) {
            throw new ResourceNotFoundException("The shoppingcart is empty");
        }

        Double totalPrice = PriceCalculator.calculateTotalPrice(productsList);

        shoppingcart.setTotal_price(totalPrice);
        shoppingcart.setProductsList(productsList);

        return shoppingcartRepository.save(shoppingcart);
    }

    private Shoppingcart fallbackGetProductsName(Throwable throwable){
        return new Shoppingcart(null, null, null);
    }

    @Override
    public List<Shoppingcart> getAllShoppingcart() {
        return shoppingcartRepository.findAll();
    }

    @Override
    public Shoppingcart getShoppingcartById(Long id_shoppingcart) throws ResourceNotFoundException {
        return shoppingcartRepository.findById(id_shoppingcart)
                .orElseThrow(() -> new ResourceNotFoundException("Shoppingcart not found with ID: " + id_shoppingcart));
    }

    @Override
    public String deleteShoppingcartById(Long id_shoppingcart) {
        shoppingcartRepository.deleteById(id_shoppingcart);
        return "Shoppingcart id: " + id_shoppingcart + " successfully removed";
    }

    @Override
    public Shoppingcart editShoppingcart(Long id_shoppingcart, Shoppingcart shoppingcart, List<String> productName) throws ResourceNotFoundException {

        Shoppingcart shp = this.getShoppingcartById(id_shoppingcart);

        List<ProductDto> productsList = productAPI.getProductsAndPricesByNames(productName);

        if (productsList.isEmpty()) {
            throw new ResourceNotFoundException("The shoppingcart is empty");
        }

        Double totalPrice = PriceCalculator.calculateTotalPrice(productsList);
        shp.setTotal_price(totalPrice);
        shp.setProductsList(productsList);

        return shoppingcartRepository.save(shp);
    }
}
