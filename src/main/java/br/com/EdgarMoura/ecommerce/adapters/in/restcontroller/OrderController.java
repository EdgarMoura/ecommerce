package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller;

import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.OrderRequest;
import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.StatusRequest;
import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.response.OrderResponse;
import br.com.EdgarMoura.ecommerce.adapters.mapper.OrderMapper;
import br.com.EdgarMoura.ecommerce.domain.exceptions.NotFoundException;
import br.com.EdgarMoura.ecommerce.domain.model.Enum.Status;
import br.com.EdgarMoura.ecommerce.domain.model.Order;
import br.com.EdgarMoura.ecommerce.domain.model.OrderByIdWithItems;
import br.com.EdgarMoura.ecommerce.domain.model.OrderFindById;
import br.com.EdgarMoura.ecommerce.domain.ports.in.OrderServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServicePort orderServicePort;

    @Autowired
    private OrderMapper orderMapper;

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderResponse>> listAllOrdersByCustomerId(@PathVariable @NotNull Long id) {
        List<Order> orderList = orderServicePort.findAll(id);
        return ResponseEntity.ok(orderMapper.listOrdersToListOrderResponse(orderList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> listById(@PathVariable @NotNull Long id) {
        try {
            OrderFindById order = orderServicePort.findById(id);
            return ResponseEntity.ok(orderMapper.orderFindByIdToOrderResponse(order));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody @Valid OrderRequest orderRequest, UriComponentsBuilder uriBuilder) {
        try {
            Order orderPlaced = orderServicePort.createOrder(orderMapper.orderRequestToOrder(orderRequest));
            URI address = uriBuilder.path("/orders/{id}").buildAndExpand(orderPlaced.getId()).toUri();
            return ResponseEntity.created(address).body(orderMapper.orderToOrderResponse(orderPlaced));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateStatus(@PathVariable Long id, @RequestBody StatusRequest statusRequest) {
        try {
            OrderByIdWithItems order = orderServicePort.updateStatus(id, statusRequest.getStatus());
            return ResponseEntity.ok(orderMapper.orderByIdWithItemsToOrderResponse(order));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}/paid")
    public ResponseEntity<Void> approvePayment(@PathVariable @NotNull Long id) {
        try {
            orderServicePort.approvePaymentRequest(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
