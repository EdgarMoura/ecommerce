package br.com.EdgarMoura.ecommerce.adapters.out.datastore;

import br.com.EdgarMoura.ecommerce.adapters.mapper.OrderMapper;
import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.OrderEntity;
import br.com.EdgarMoura.ecommerce.adapters.out.datastore.repository.OrderRepository;
import br.com.EdgarMoura.ecommerce.domain.model.Enum.Status;
import br.com.EdgarMoura.ecommerce.domain.model.Order;
import br.com.EdgarMoura.ecommerce.domain.model.OrderByIdWithItems;
import br.com.EdgarMoura.ecommerce.domain.model.OrderFindById;
import br.com.EdgarMoura.ecommerce.domain.ports.out.OrderDataStorePort;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class OrderDataStorePortImpl implements OrderDataStorePort {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findAllOrdersByIdCustomer(Long idCustomer) {
        List<OrderEntity> orderEntityList = orderRepository.findAllOrdersByIdCustomer(idCustomer);
        log.info("Quantidades de produtos encontados: {}", orderEntityList.size());
        return orderMapper.listOrderEntityToListOrder(orderEntityList);
    }

    @Override
    public OrderFindById findByIdOrder(Long id) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findByOrderId(id);
        if(orderEntityOptional.isEmpty()) {
            log.error("Pedido com id {} não encontrado na base.", id);
            return null;
        }
        OrderEntity orderEntity = orderEntityOptional.get();
        log.info("Produto encontrado com id: {}", orderEntity.getId());
        return orderMapper.orderEntityToOrderFindById(orderEntity);
    }

    @Override
    public Order saveOrder(Order order) {
        OrderEntity orderEntity = orderMapper.orderToOrderEntity(order);
        OrderEntity orderEntitySaved = orderRepository.save(orderEntity);
        log.info("Salvo na tabela de pedidos: {}", orderEntitySaved.getId());

        return orderMapper.orderEntityToOrder(orderEntitySaved);
    }

    @Override
    public OrderByIdWithItems updateStatus(OrderByIdWithItems order, Status status) {
        OrderEntity orderEntity = orderMapper.orderByIdWithItemsToOrderEntity(order);
        orderRepository.updateStatus(status, orderEntity);
        log.info("Pedido com id {} atualizado com sucesso.", orderEntity.getId());
        return orderMapper.orderEntityToOrderByIdWithItems(orderEntity);
    }

    @Override
    public OrderByIdWithItems byIdWithItems(Long id) {
        OrderEntity orderEntity = orderRepository.byIdWithItems(id);
        if(orderEntity == null) {
            log.error("Pedido com id {} não encontrado na base.", id);
            return null;
        }

        return orderMapper.orderEntityToOrderByIdWithItems(orderEntity);
    }
}
