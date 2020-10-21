package br.gov.sp.gestaoContrato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name="empresa")
@Data
public class Empresa {
	/*
	id INT NOT NULL AUTO_INCREMENT,
	  nome VARCHAR(60) NOT NULL,
	  cnpj VARCHAR(45) NULL,
	  telefone VARCHAR(45) NULL,
	  email VARCHAR(45) NULL,
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O nome da empresa é obrigatório.")
	private String nome;
	private String cnpj;
	private String telefone;
	private String email;

}// fecha classe
