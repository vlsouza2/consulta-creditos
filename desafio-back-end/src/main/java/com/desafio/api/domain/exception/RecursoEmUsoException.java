package com.desafio.api.domain.exception;

public class RecursoEmUsoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecursoEmUsoException(String mensagem) {
		super(mensagem);
	}
	
}
