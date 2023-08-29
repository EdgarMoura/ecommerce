package br.com.EdgarMoura.ecommerce.domain.ports.in;

import br.com.EdgarMoura.ecommerce.domain.exceptions.NotFoundException;
import br.com.EdgarMoura.ecommerce.domain.model.Enum.Status;
import br.com.EdgarMoura.ecommerce.domain.model.Order;
import br.com.EdgarMoura.ecommerce.domain.model.OrderByIdWithItems;
import br.com.EdgarMoura.ecommerce.domain.model.OrderFindById;
import br.com.EdgarMoura.ecommerce.domain.model.OrderItem;

import java.util.List;

public interface OrderServicePort {

    List<Order> findAll(Long idCustomer);

    OrderFindById findById(Long id) throws NotFoundException;

    Order createOrder(Order order) throws NotFoundException;

    OrderByIdWithItems updateStatus(Long id, Status status) throws NotFoundException;

    void approvePaymentRequest(Long id) throws NotFoundException;
}
