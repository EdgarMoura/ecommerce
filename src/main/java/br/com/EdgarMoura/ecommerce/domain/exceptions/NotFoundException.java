package br.com.EdgarMoura.ecommerce.domain.exceptions;

public class NotFoundException extends BusinessException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
