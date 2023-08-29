package br.com.EdgarMoura.ecommerce.adapters.out.datastore.repository;

import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {


}
