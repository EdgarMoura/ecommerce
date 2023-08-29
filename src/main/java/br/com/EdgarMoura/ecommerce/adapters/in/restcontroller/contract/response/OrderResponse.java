package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.response;

import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.OrderItemEntity;
import br.com.EdgarMoura.ecommerce.domain.model.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private LocalDateTime dateTime;
    private Status status;
    private List<OrderItemResponse> items = new ArrayList<>();
}
