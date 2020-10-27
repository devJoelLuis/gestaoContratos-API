package br.gov.sp.gestaoContrato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.gestaoContrato.models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	boolean existsByNome(String nome);

	boolean existsByNomeAndIdNot(String nome, Integer id);

	List<Empresa> findAllByOrderByNomeAsc();

}// fecha classe
