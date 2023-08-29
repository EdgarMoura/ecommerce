package br.com.EdgarMoura.ecommerce.adapters.out.datastore.repository;

import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByCpf(String cpf);
}
