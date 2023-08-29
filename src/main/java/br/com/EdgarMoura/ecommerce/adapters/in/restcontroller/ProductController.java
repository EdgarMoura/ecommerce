package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller;

import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.ProductRequest;
import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.response.ProductResponse;
import br.com.EdgarMoura.ecommerce.adapters.mapper.ProductMapper;
import br.com.EdgarMoura.ecommerce.domain.exceptions.ExistingDataException;
import br.com.EdgarMoura.ecommerce.domain.exceptions.NotFoundException;
import br.com.EdgarMoura.ecommerce.domain.model.Product;
import br.com.EdgarMoura.ecommerce.domain.ports.in.ProductServicePort;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServicePort productServicePort;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<Product> products = productServicePort.findAll();
        return ResponseEntity.ok(productMapper.listProductsToListProductResponse(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        try {
           Product product = productServicePort.findById(id);
           return ResponseEntity.ok(productMapper.productToProductResponse(product));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductResponse> insertProduct(@Valid @RequestBody ProductRequest productRequest, UriComponentsBuilder uriBuilder) {
        try {
            Product product = productServicePort.insert(productMapper.productRequestToProduct(productRequest));
            URI address = uriBuilder.path("/products/{id}").buildAndExpand(product.getProductId()).toUri();
            return ResponseEntity.created(address).body(productMapper.productToProductResponse(product));
        } catch (ExistingDataException e) {
            log.error(e.getMessage());
           return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductRequest productRequest) {
        try {
            Product product = productServicePort.updateProduct(id, productMapper.productRequestToProduct(productRequest));
            return ResponseEntity.ok(productMapper.productToProductResponse(product));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            productServicePort.deleteByIdProduct(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
