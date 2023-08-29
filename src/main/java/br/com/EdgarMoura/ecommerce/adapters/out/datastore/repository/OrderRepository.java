package br.com.EdgarMoura.ecommerce.adapters.out.datastore.repository;

import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.OrderEntity;
import br.com.EdgarMoura.ecommerce.domain.model.Enum.Status;
import br.com.EdgarMoura.ecommerce.domain.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update OrderEntity o set o.orderStatus = :status where o = :order")
    void updateStatus(Status status, OrderEntity order);

    @Query(value = "SELECT o from OrderEntity o LEFT JOIN o.items LEFT JOIN o.customer c where o.id = :id")
    OrderEntity byIdWithItems(Long id);

    @Query(value = "SELECT o from OrderEntity o where o.customer.customerId = :idCustomer")
    List<OrderEntity> findAllOrdersByIdCustomer(Long idCustomer);

    @Query(value = "SELECT o from OrderEntity o JOIN FETCH o.customer c JOIN FETCH o.items i where o.id = :id")
    Optional<OrderEntity> findByOrderId(Long id);
}
