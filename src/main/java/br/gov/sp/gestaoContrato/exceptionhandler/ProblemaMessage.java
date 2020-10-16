package br.gov.sp.gestaoContrato.exceptionhandler;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemaMessage {
	
	private LocalDateTime dataHora;
	private String menssagem;

}//fecha classe
