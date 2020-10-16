package br.gov.sp.gestaoContrato.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.gov.sp.gestaoContrato.exceptionhandler.exceptions.DepartamentoNegocioException;

@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(DepartamentoNegocioException.class)
	public ResponseEntity<?> tratarDepartamentoNegocioException(DepartamentoNegocioException e) {
		  ProblemaMessage p = new ProblemaMessage();
		  p.setMenssagem(e.getMessage());
		  p.setDataHora(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(p);  
	}
	

}//fecha classe
