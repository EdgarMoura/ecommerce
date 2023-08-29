package br.com.EdgarMoura.ecommerce.adapters.in.restcontroller.handler;

public class ErrorFormRequestDTO {
	
	private String field;
	private String error;
	
	public ErrorFormRequestDTO(String field, String error) {
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}
	
	

}
