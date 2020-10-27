package br.gov.sp.gestaoContrato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.gestaoContrato.models.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {

}
