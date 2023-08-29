package br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity;

import br.com.EdgarMoura.ecommerce.domain.model.Enum.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private Status orderStatus;

    @ManyToOne(optional=false)
    private CustomerEntity customer;

    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="order")
    private List<OrderItemEntity> items = new ArrayList<>();
}
