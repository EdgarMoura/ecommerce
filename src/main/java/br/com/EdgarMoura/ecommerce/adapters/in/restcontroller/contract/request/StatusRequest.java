package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.contract.request;

import br.com.EdgarMoura.ecommerce.domain.model.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusRequest {
    private Status status;
}
