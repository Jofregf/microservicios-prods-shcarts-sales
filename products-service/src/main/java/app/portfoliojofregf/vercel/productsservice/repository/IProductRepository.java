package app.portfoliojofregf.vercel.productsservice.repository;

import app.portfoliojofregf.vercel.productsservice.dto.ProductDto;
import app.portfoliojofregf.vercel.productsservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT new app.portfoliojofregf.vercel.productsservice.dto.ProductDto(pro.name, pro.price) FROM Product pro WHERE pro.name IN :names")
    List<ProductDto> findProductsByNames(List<String> names);
}
