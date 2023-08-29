package br.com.EdgarMoura.ecommerce.adapters.mapper;

import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.OrderRequest;
import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.response.OrderResponse;
import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.OrderEntity;
import br.com.EdgarMoura.ecommerce.domain.model.Order;
import br.com.EdgarMoura.ecommerce.domain.model.OrderByIdWithItems;
import br.com.EdgarMoura.ecommerce.domain.model.OrderFindById;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order orderRequestToOrder(OrderRequest orderRequest);

    @Mapping(source = "orderStatus", target = "status")
    Order orderEntityToOrder(OrderEntity orderEntity);
    @Mapping(source = "orderStatus", target = "status")
    OrderByIdWithItems orderEntityToOrderByIdWithItems(OrderEntity orderEntity);

    @Mapping(source = "orderStatus", target = "status")
    OrderFindById orderEntityToOrderFindById(OrderEntity orderEntity);

    @Mapping(target = "orderStatus", source = "status")
    OrderEntity orderToOrderEntity(Order order);

    @Mapping(target = "orderStatus", source = "status")
    OrderEntity orderByIdWithItemsToOrderEntity(OrderByIdWithItems order);

    OrderResponse orderToOrderResponse(Order order);

    OrderResponse orderByIdWithItemsToOrderResponse(OrderByIdWithItems order);

    OrderResponse orderFindByIdToOrderResponse(OrderFindById order);

    List<Order> listOrderEntityToListOrder(List<OrderEntity> source);

    List<OrderResponse> listOrdersToListOrderResponse(List<Order> source);
}
