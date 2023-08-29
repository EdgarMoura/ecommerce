package br.com.EdgarMoura.ecommerce.domain.ports.in;

import br.com.EdgarMoura.ecommerce.domain.exceptions.ExistingDataException;
import br.com.EdgarMoura.ecommerce.domain.exceptions.NotFoundException;
import br.com.EdgarMoura.ecommerce.domain.model.Customer;
import br.com.EdgarMoura.ecommerce.domain.model.Product;

import java.util.List;

public interface ProductServicePort {

   Product insert(Product product) throws ExistingDataException;

   List<Product> findAll();

   Product findById(Long id) throws NotFoundException;

   Product updateProduct(Long id, Product product) throws NotFoundException;

   void deleteByIdProduct(Long id) throws NotFoundException;
}
