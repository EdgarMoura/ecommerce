package br.com.EdgarMoura.ecommerce.domain.model;

import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.OrderItemEntity;
import br.com.EdgarMoura.ecommerce.domain.model.Enum.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private LocalDateTime dateTime;
    private Status status;
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();
}
