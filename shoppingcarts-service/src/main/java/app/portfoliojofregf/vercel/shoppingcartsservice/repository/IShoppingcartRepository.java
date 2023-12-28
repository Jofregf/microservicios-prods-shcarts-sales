package app.portfoliojofregf.vercel.shoppingcartsservice.repository;

import app.portfoliojofregf.vercel.shoppingcartsservice.model.Shoppingcart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShoppingcartRepository extends JpaRepository<Shoppingcart, Long> {
}
