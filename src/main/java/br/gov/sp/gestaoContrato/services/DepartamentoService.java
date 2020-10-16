package br.gov.sp.gestaoContrato.services;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.gov.sp.gestaoContrato.exceptionhandler.exceptions.DepartamentoNegocioException;
import br.gov.sp.gestaoContrato.models.Departamento;
import br.gov.sp.gestaoContrato.repository.DepartamentoRepository;

@Service
public class DepartamentoService {
	
	
	
	
	private static final String ID_NAO_ENCONTRADO = "Não foi possível encontrar um departamento com o id %s.";
	private DepartamentoRepository repo;
	private static final Logger logger = LoggerFactory.getLogger(DepartamentoService.class);
	
	
	
	

	public DepartamentoService(DepartamentoRepository repo) {
		this.repo = repo;
	}
	
	
	
	
	// cadastrar departemaneto
	public Departamento cadastrar(Departamento d) {
		
		
		if (repo.existsByNome(d.getNome())) {
				throw new DepartamentoNegocioException("O nome do departamento já existe!!!");
		}
		
		try {
		
			return repo.save(d);
			
		} catch (Exception e) {
			logger.error(String.format("Ocorreu o seguinte erro ao tentar cadastrar um departamento: %s", e.getMessage()));
			return null;
		}
		
		
	}// fecha cadastrar
	
	
	
	
	
	


	// alterar departamento
	public Departamento alterar(Departamento dep, Integer id) {
		
		// verificar se existe um departamento com o id informado
		Departamento depBanco = repo.findById(id)
				                           .orElseThrow(() -> 
				                           new DepartamentoNegocioException(String.format(ID_NAO_ENCONTRADO, id)));
		
		// verificar se o nome do departamento ja pertence a outro no banco de dados
		if (repo.existsByNomeAndIdNot(dep.getNome(), id)) {
			
			throw new DepartamentoNegocioException(String.format("Já existe um departamento com nome %s cadastrado no banco de dados.", dep.getNome()));
			
		}
		
	    try {
	    	
	    	// copiar modificações ignorar id
			BeanUtils.copyProperties(dep, depBanco, "id");
			
			// gravar e retornar o departamento salvo
			return repo.save(depBanco);
			
		} catch (Exception e) {
			logger.error(String.format("Ocorreu o seguinte erro ao tentar alterar um departamento: %s", e.getMessage()));
			return null;
		}
		
	}// fecha alterar departemanto
	
	
	
	
	// deletar departamento
	public void deletar(Integer id) {
		
		// verificar se existe um departamento com o id informado
		Departamento depBanco = repo.findById(id)
						            .orElseThrow(() -> 
						            new DepartamentoNegocioException(String.format(ID_NAO_ENCONTRADO, id)));
		try {
			
			repo.delete(depBanco);
			
		} catch (ConstraintViolationException c) {
			throw new DepartamentoNegocioException(String.format("O departamento de id %s não pode ser excluído porque esta vinculado a outra entidade.", id));
		} catch (Exception e) {
			logger.error(String.format("Ocorreu o seguinte erro ao tentar excluir um departamento: %s", e.getMessage()));
		}
		
		
	}// fecha deletar departamento
	
	
	
	
	
	// get by id
	
	
	
	
	
	// get all
	
	
	
	
	
	

}//fecha classe
