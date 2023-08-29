package br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "product_name")
    private String productName;
    private String details;
    private String category;
    @OneToMany(mappedBy="product")
    private List<OrderItemEntity> orderItems = new ArrayList<>();
}
