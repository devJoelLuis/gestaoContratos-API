package br.gov.sp.gestaoContrato.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.gestaoContrato.models.Empresa;
import br.gov.sp.gestaoContrato.services.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
	
	private EmpresaService service;
	
	public EmpresaController(EmpresaService service) {
		this.service = service;
	}
	
	
	// cadastrar
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody Empresa empresa) {
		empresa = service.cadastrar(empresa);
		return ResponseEntity.status(HttpStatus.CREATED).body(empresa);
	}
	
	// alterar
	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(
		@RequestBody Empresa empresa,
		@PathVariable Integer id
			){
		empresa = service.alterar(id, empresa);
		return ResponseEntity.ok(empresa);
	}
	
	// deletar
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Integer id){
	   return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	// get by id
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		Empresa empresa = service.getById(id);
		return ResponseEntity.ok(empresa);
	}
	
	// get all sem paginação
	
	

}// fecha classe
