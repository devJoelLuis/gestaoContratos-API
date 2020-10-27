package br.gov.sp.gestaoContrato.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.gestaoContrato.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
