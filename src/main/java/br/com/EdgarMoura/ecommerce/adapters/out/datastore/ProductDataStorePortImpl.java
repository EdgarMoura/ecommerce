package br.com.EdgarMoura.ecommerce.adapters.out.datastore;

import br.com.EdgarMoura.ecommerce.adapters.mapper.ProductMapper;
import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.ProductEntity;
import br.com.EdgarMoura.ecommerce.adapters.out.datastore.repository.ProductRepository;
import br.com.EdgarMoura.ecommerce.domain.model.Product;
import br.com.EdgarMoura.ecommerce.domain.ports.out.ProductDataStorePort;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ProductDataStorePortImpl implements ProductDataStorePort {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product salveProduct(Product product) {
        ProductEntity productEntity = productMapper.productToProductEntity(product);
        ProductEntity productSalved = productRepository.save(productEntity);
        log.info("Salvo na tabela de produto: {}", productSalved.getProductId());
        return productMapper.productEntityToProduct(productSalved);
    }

    @Override
    public List<Product> findAllProduct() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<Product> products = productMapper.listProductEntityToListProduct(productEntityList);
        log.info("Quantidades de produtos encontados: {}", products.size());
        return products;
    }

    @Override
    public Product findByIdProduct(Long id) {
        Optional<ProductEntity> productEntityOptional = productRepository.findById(id);
        if(productEntityOptional.isEmpty()) {
            log.error("Produto com id {} não encontrado na base.", id);
            return null;
        }
        ProductEntity productEntity = productEntityOptional.get();
        log.info("Produto encontrado com id: {}", productEntity.getProductId());
        return productMapper.productEntityToProduct(productEntity);
    }

    @Override
    public boolean deleteByIdProduct(Long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            log.info("Produto excluído da base de dados com sucesso.");
            return true;
        }
        log.error("Produto com id {} não encontrado para exclusão.", id);
        return false;
    }

    @Override
    public boolean findByProductNameAndCategory(Product product) {
        if(productRepository.findByProductNameAndCategory(product.getProductName(), product.getCategory()).isPresent()){
            log.info("Produto encontrado com nome {} e categoria {}.", product.getProductName(), product.getCategory());
            return true;
        }
        log.error("Produto não encontrado com nome {} e categoria {}.", product.getProductName(), product.getCategory());
        return false;
    }
}
