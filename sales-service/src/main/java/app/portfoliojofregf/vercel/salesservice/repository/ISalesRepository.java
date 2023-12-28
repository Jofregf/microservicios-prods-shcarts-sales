package app.portfoliojofregf.vercel.salesservice.repository;

import app.portfoliojofregf.vercel.salesservice.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalesRepository extends JpaRepository<Sales, Long> {
}
