package br.gov.sp.gestaoContrato.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "O nome do usuário é obrigatório.")
	private String nome;
	
	@NotBlank(message = "A senha do usuário é obrigatória.")
	private String senha;
	
	private String obs;
	
	@NotBlank(message = "O e-mail do usuário é obrigatório.")
	@Email(message = "E-mail inválido.")
	private String email;
	
	@ManyToOne
	@JoinColumn(name="departamento_id")
	private Departamento departamento;

}// fecha classe
