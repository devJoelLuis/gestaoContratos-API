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

import br.gov.sp.gestaoContrato.models.Departamento;
import br.gov.sp.gestaoContrato.services.DepartamentoService;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

	private DepartamentoService service;
	
	
	
	public DepartamentoController(DepartamentoService service) {
		this.service = service;
	}


	
    // cadastrar departamento 
	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody Departamento d) {
			d = service.cadastrar(d);
			return ResponseEntity.ok().body(d);
	}
	
	
	// alterar departamento
	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(
	 @RequestBody Departamento dep,
	 @PathVariable Integer id
			) {
		dep = service.alterar(dep, id);
		return ResponseEntity.ok(dep);
	}
	
	
	
	// deletar departamento
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	
	// get departamento id
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		Departamento dep = service.getById(id);
		return ResponseEntity.ok(dep);
	}
	
	
	// get all departamento
	@GetMapping
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(service.getAll());
	}
	
	
}//fecha classe
