package br.gov.sp.gestaoContrato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.gestaoContrato.models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	boolean existsByNome(String nome);

	boolean existsByNomeAndNotId(String nome, Integer id);

}// fecha classe
