package br.com.EdgarMoura.ecommerce.adapters.mapper;

import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.OrderItemRequest;
import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.OrderRequest;
import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.response.OrderItemResponse;
import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.response.OrderResponse;
import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.OrderEntity;
import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.OrderItemEntity;
import br.com.EdgarMoura.ecommerce.domain.model.Order;
import br.com.EdgarMoura.ecommerce.domain.model.OrderItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItem orderItemRequestToOrderItem(OrderItemRequest orderItemRequest);

    OrderItem orderItemEntityToOrderItem(OrderItemEntity orderItemEntity);

    OrderItemEntity orderItemToOrderItemEntity(OrderItem orderItem);

    List<OrderItem> listOrderItemEntityToListOrderItem(List<OrderEntity> source);

    List<OrderItemResponse> listOrderItemToListOrderItemResponse(List<OrderItem> source);
}
