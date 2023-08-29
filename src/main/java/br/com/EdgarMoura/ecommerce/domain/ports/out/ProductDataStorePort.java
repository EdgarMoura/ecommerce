package br.com.EdgarMoura.ecommerce.domain.ports.out;

import br.com.EdgarMoura.ecommerce.domain.model.Product;

import java.util.List;

public interface ProductDataStorePort {

    Product salveProduct(Product product);

    List<Product> findAllProduct();

    Product findByIdProduct(Long id);

    boolean deleteByIdProduct(Long id);

    boolean findByProductNameAndCategory(Product product);
}
