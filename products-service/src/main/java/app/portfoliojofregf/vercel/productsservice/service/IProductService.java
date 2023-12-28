package app.portfoliojofregf.vercel.productsservice.service;

import app.portfoliojofregf.vercel.productsservice.dto.ProductDto;
import app.portfoliojofregf.vercel.productsservice.model.Product;

import java.util.List;

public interface IProductService {

    public Product addProduct(Product product);
    public List<Product> getAllProduct();
    public Product getProductById(Long id_product);
    public String deleteProductById(Long id_product);
    public Product editProduct(Long id_product, Product product);
    public List<ProductDto> getProductsByProductNames(List<String> names);
}
