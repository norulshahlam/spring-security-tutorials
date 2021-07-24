package shah.productservice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import shah.productservice.model.Product;


public interface ProductRepo extends JpaRepository<Product, Long> {

}
