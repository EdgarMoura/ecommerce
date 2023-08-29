package br.com.EdgarMoura.ecommerce.adapters.out.datastore.repository;

import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByProductNameAndCategory(String productName, String category);
}
