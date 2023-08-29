package br.com.EdgarMoura.ecommerce.domain.exceptions;

public class BusinessException extends Exception {

    public BusinessException() {
        super();
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
