package br.gov.sp.gestaoContrato.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "item")
@Data
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "A descrição do item é obrigatória.")
	private String descricao;
	
	@NotNull( message = "Informe a quantidade total do item.")
	@Column(name = "quantidade_total")
	private Integer quantidadeTotal;
	
	@Column(name = "qtd_restante")
	private Integer qtdRestante;
	
	@ManyToOne
	@JoinColumn(name="contrato_id")
    private Contrato contrato; 	
 
}// fecha classe
