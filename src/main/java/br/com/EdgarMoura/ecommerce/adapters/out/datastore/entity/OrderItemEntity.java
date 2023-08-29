package br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;
    private Integer quantity;
    private BigDecimal price;

    @ManyToOne(optional=false)
    private OrderEntity order;
    @ManyToOne(optional=false)
    private ProductEntity product;
}
