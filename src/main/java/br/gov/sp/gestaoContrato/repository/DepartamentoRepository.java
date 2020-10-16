package br.gov.sp.gestaoContrato.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.gestaoContrato.models.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

	boolean existsByNome(String nome);

	boolean existsByNomeAndIdNot(String nome, Integer id);

	List<Departamento> findAllByOrderByNomeAsc();

}
