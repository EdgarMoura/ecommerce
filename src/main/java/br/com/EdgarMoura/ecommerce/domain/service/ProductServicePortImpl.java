package br.com.EdgarMoura.ecommerce.domain.service;

import br.com.EdgarMoura.ecommerce.domain.exceptions.ExistingDataException;
import br.com.EdgarMoura.ecommerce.domain.exceptions.NotFoundException;
import br.com.EdgarMoura.ecommerce.domain.model.Customer;
import br.com.EdgarMoura.ecommerce.domain.model.Product;
import br.com.EdgarMoura.ecommerce.domain.ports.in.ProductServicePort;
import br.com.EdgarMoura.ecommerce.domain.ports.out.ProductDataStorePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ProductServicePortImpl implements ProductServicePort {

    private final ProductDataStorePort productDataStorePort;

    @Override
    public Product insert(Product product) throws ExistingDataException {
        if(productDataStorePort.findByProductNameAndCategory(product)) {
            throw new ExistingDataException("Produto já existente.");
        }
        return productDataStorePort.salveProduct(product);
    }

    @Override
    public List<Product> findAll() {
        return productDataStorePort.findAllProduct();
    }

    @Override
    public Product findById(Long id) throws NotFoundException {
        Product productFound = productDataStorePort.findByIdProduct(id);
        if(productFound == null) {
            throw new NotFoundException("Produto não encontrado.");
        }
        return productFound;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws NotFoundException {
        Product productFound = productDataStorePort.findByIdProduct(id);
        if(productFound == null) {
            throw new NotFoundException("Produto não encontrado para atualização.");
        }
        productFound.setProductName(product.getProductName());
        if(productFound.getDetails() != null) productFound.setDetails(product.getDetails());
        productFound.setCategory(product.getCategory());
        return productDataStorePort.salveProduct(productFound);
    }

    @Override
    public void deleteByIdProduct(Long id) throws NotFoundException {
        if(!productDataStorePort.deleteByIdProduct(id)) {
            throw new NotFoundException("Produto não encontrado para exclusão.");
        }
    }
}
