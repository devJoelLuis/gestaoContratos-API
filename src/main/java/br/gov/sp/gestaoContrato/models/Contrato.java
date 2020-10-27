package br.gov.sp.gestaoContrato.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "contrato")
@Data
public class Contrato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="data_inicio")
	@NotNull(message = "A data de início é obrigatória.")
	private LocalDate dataInicio;
	
	@Column(name="data_fim")
	@NotNull(message = "A data de final é obrigatória.")
	private LocalDate datafim;
	
	@Column(name="data_alerta")
	@NotNull(message = "A data de alerta é obrigatória.")
	private LocalDate dataAlerta;
	private int alerta = 0; 
	
	@NotBlank(message = "O nome é obrigatório.")
	private String descricao;
	
	private String obs;
	
	@Email(message = "E-mail inválido.")
	@Column(name="email_alerta")
	private String emailAlerta;
	
	@ManyToOne
	@JoinColumn(name="empresa_id")
	@NotNull(message = "Informe a empresa.")
	private Empresa empresa;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	@NotNull(message = "Informe o usuário.")
	private Usuario usuario;
	

}// fecha classe
