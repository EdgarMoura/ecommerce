package br.com.EdgarMoura.ecommerce.domain.exceptions;

public class ExistingDataException extends BusinessException {

    public ExistingDataException() {
        super();
    }

    public ExistingDataException(String msg) {
        super(msg);
    }

    public ExistingDataException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
