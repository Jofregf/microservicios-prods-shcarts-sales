package app.portfoliojofregf.vercel.shoppingcartsservice.service;

import app.portfoliojofregf.vercel.shoppingcartsservice.exception.ResourceNotFoundException;
import app.portfoliojofregf.vercel.shoppingcartsservice.model.Shoppingcart;

import java.util.List;

public interface IShoppingcartService {
    public Shoppingcart addShoppingcart(List<String> productsName, Shoppingcart shoppingcart) throws ResourceNotFoundException;
    public List<Shoppingcart> getAllShoppingcart();
    public Shoppingcart getShoppingcartById(Long id_shoppingcart) throws ResourceNotFoundException;
    public String deleteShoppingcartById(Long id_shoppingcart);
    public Shoppingcart editShoppingcart(Long id_shoppingcart, Shoppingcart shoppingcart, List<String> productName) throws ResourceNotFoundException;
}
