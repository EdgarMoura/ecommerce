package br.com.EdgarMoura.ecommerce.domain.model;

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
public class OrderByIdWithItems {

    private Long id;
    private LocalDateTime dateTime;
    private Status status;
    private List<OrderItemWithItem> items = new ArrayList<>();
}
