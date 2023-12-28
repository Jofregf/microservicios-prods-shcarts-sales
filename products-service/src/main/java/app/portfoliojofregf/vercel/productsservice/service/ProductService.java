package app.portfoliojofregf.vercel.productsservice.service;

import app.portfoliojofregf.vercel.productsservice.dto.ProductDto;
import app.portfoliojofregf.vercel.productsservice.model.Product;
import app.portfoliojofregf.vercel.productsservice.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        Product newProduct = productRepository.save(product);
        return newProduct;
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id_product) {
        return productRepository.findById(id_product)
                .orElse(null);
    }

    @Override
    public String deleteProductById(Long id_product) {
        productRepository.deleteById(id_product);
        return "Product id: " + id_product + " successfully removed";
    }

    @Override
    public Product editProduct(Long id_product, Product product) {
        Product findProduct = this.getProductById(id_product);
        findProduct.setCode(product.getCode());
        findProduct.setName(product.getName());
        findProduct.setBrand(product.getBrand());
        findProduct.setPrice(product.getPrice());
        this.addProduct(findProduct);
        return findProduct;
    }

    @Override
    public List<ProductDto> getProductsByProductNames(List<String> names) {
        List<ProductDto> list = productRepository.findProductsByNames(names);
        return list;
    }
}
