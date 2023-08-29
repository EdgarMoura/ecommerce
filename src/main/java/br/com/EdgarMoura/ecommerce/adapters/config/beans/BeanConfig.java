package br.com.EdgarMoura.ecommerce.adapters.config.beans;

import br.com.EdgarMoura.ecommerce.domain.ports.in.CustomerServicePort;
import br.com.EdgarMoura.ecommerce.domain.ports.in.OrderServicePort;
import br.com.EdgarMoura.ecommerce.domain.ports.in.ProductServicePort;
import br.com.EdgarMoura.ecommerce.domain.ports.out.CustomerDataStorePort;
import br.com.EdgarMoura.ecommerce.domain.ports.out.OrderDataStorePort;
import br.com.EdgarMoura.ecommerce.domain.ports.out.ProductDataStorePort;
import br.com.EdgarMoura.ecommerce.domain.service.CustomerServicePortImpl;
import br.com.EdgarMoura.ecommerce.domain.service.OrderServicePortImpl;
import br.com.EdgarMoura.ecommerce.domain.service.ProductServicePortImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public CustomerServicePort customerServicePortBean(CustomerDataStorePort customerDataStorePort) {
        return new CustomerServicePortImpl(customerDataStorePort);
    }

    @Bean
    public ProductServicePort productServicePortBean(ProductDataStorePort productDataStorePort) {
        return new ProductServicePortImpl(productDataStorePort);
    }

    @Bean
    public OrderServicePort orderServicePortBean(OrderDataStorePort orderDataStorePort,
                                                 CustomerDataStorePort customerDataStorePort,
                                                 ProductDataStorePort productDataStorePort) {
        return new OrderServicePortImpl(orderDataStorePort, customerDataStorePort, productDataStorePort);
    }
}
