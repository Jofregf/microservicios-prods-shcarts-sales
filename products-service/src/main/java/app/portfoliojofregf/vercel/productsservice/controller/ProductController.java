package app.portfoliojofregf.vercel.productsservice.controller;

import app.portfoliojofregf.vercel.productsservice.dto.ProductDto;
import app.portfoliojofregf.vercel.productsservice.model.Product;
import app.portfoliojofregf.vercel.productsservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> productsList = productService.getAllProduct();
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }

    @GetMapping("/{id_product}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id_product){
        Product product = productService.getProductById(id_product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id_product}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id_product){
        productService.deleteProductById(id_product);
        return new ResponseEntity<>("product successfully deleted", HttpStatus.OK);
    }

    @PutMapping("/edit/{id_product}")
    public ResponseEntity<Product> editProduct(@PathVariable Long id_product, @RequestBody Product produ){
        Product editedProduct = productService.editProduct(id_product, produ);
        return new ResponseEntity<>(editedProduct, HttpStatus.OK);
    }

    @GetMapping("/findByNames")
    public ResponseEntity<List<ProductDto>> findProductsByNames(@RequestParam("name") List<String> names) {
        List<ProductDto> productsList = productService.getProductsByProductNames(names);
        return new ResponseEntity<>(productsList, HttpStatus.OK);
    }
}
