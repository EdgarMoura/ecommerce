package br.com.EdgarMoura.ecommerce.domain.ports.out;

import br.com.EdgarMoura.ecommerce.domain.model.Enum.Status;
import br.com.EdgarMoura.ecommerce.domain.model.Order;
import br.com.EdgarMoura.ecommerce.domain.model.OrderByIdWithItems;
import br.com.EdgarMoura.ecommerce.domain.model.OrderFindById;

import java.util.List;

public interface OrderDataStorePort {

    List<Order> findAllOrdersByIdCustomer(Long idCustomer);

    OrderFindById findByIdOrder(Long id);

    Order saveOrder(Order order);

    OrderByIdWithItems updateStatus(OrderByIdWithItems order, Status status);

    OrderByIdWithItems byIdWithItems(Long id);
}
