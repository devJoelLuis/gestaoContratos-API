package br.gov.sp.gestaoContrato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.gestaoContrato.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
