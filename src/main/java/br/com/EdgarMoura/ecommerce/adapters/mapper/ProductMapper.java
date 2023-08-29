package br.com.EdgarMoura.ecommerce.adapters.mapper;

import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.ProductIdRequest;
import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request.ProductRequest;
import br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.response.ProductResponse;
import br.com.EdgarMoura.ecommerce.adapters.out.datastore.entity.ProductEntity;
import br.com.EdgarMoura.ecommerce.domain.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product productRequestToProduct(ProductRequest productRequest);
    Product productIdRequestToProduct(ProductIdRequest productIdRequest);

    Product productEntityToProduct(ProductEntity productEntity);

    ProductEntity productToProductEntity(Product product);

    ProductResponse productToProductResponse(Product product);

    List<Product> listProductEntityToListProduct(List<ProductEntity> source);

    List<ProductResponse> listProductsToListProductResponse(List<Product> source);
}
