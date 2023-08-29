package br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    private String name;
    private String cpf;
    private String email;
    private String address;

    @OneToMany(mappedBy="customer")
    private List<OrderEntity> orders = new ArrayList<>();
}
