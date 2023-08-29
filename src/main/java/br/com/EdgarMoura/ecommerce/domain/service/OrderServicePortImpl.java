package br.com.EdgarMoura.ecommerce.domain.service;

import br.com.EdgarMoura.ecommerce.domain.exceptions.NotFoundException;
import br.com.EdgarMoura.ecommerce.domain.model.*;
import br.com.EdgarMoura.ecommerce.domain.model.Enum.Status;
import br.com.EdgarMoura.ecommerce.domain.ports.in.OrderServicePort;
import br.com.EdgarMoura.ecommerce.domain.ports.out.CustomerDataStorePort;
import br.com.EdgarMoura.ecommerce.domain.ports.out.OrderDataStorePort;
import br.com.EdgarMoura.ecommerce.domain.ports.out.ProductDataStorePort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class OrderServicePortImpl implements OrderServicePort {

    private final OrderDataStorePort orderDataStorePort;

    private final CustomerDataStorePort customerDataStorePort;

    private final ProductDataStorePort productDataStorePort;

    public List<Order> findAll(Long idCustomer) {
        return orderDataStorePort.findAllOrdersByIdCustomer(idCustomer);
    }

    public OrderFindById findById(Long id) throws NotFoundException {
        OrderFindById orderFound = orderDataStorePort.findByIdOrder(id);
        if (orderFound == null) {
            throw new NotFoundException("Pedido não encontrado.");
        }
        return orderFound;
    }

    public Order createOrder(Order order) throws NotFoundException {
        Customer customer = customerDataStorePort.findByIdCustomer(order.getCustomer().getCustomerId());
        if (customer == null) {
            throw new NotFoundException("Cliente não encontrado.");
        }
        Long productId = order.getItems().stream().map(i -> i.getProduct().getProductId()).findFirst().orElseThrow();
        Product product = productDataStorePort.findByIdProduct(productId);
        if (product == null) {
            throw new NotFoundException("Produto não encontrado.");
        }

        order.setCustomer(customer);
        order.setDateTime(LocalDateTime.now());
        order.setStatus(Status.REALIZADO);
        order.getItems().forEach(item -> item.setProduct(product));
        return orderDataStorePort.saveOrder(order);
    }

    public OrderByIdWithItems updateStatus(Long id, Status status) throws NotFoundException {
        OrderByIdWithItems order = orderDataStorePort.byIdWithItems(id);

        if (order == null) {
            throw new NotFoundException("Pedido não encontrado.");
        }


        order.setStatus(status);
        orderDataStorePort.updateStatus(order, status);
        return order;
    }

    public void approvePaymentRequest(Long id) throws NotFoundException {
        OrderByIdWithItems order = orderDataStorePort.byIdWithItems(id);

        if (order == null) {
            throw new NotFoundException("Pedido não encontrado.");
        }

        order.setStatus(Status.PAGO);
        orderDataStorePort.updateStatus(order, order.getStatus());
    }
}
