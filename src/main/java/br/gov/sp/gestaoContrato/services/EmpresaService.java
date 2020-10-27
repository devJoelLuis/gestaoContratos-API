package br.gov.sp.gestaoContrato.services;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.gov.sp.gestaoContrato.exceptionhandler.exceptions.EmpresaNegocioException;
import br.gov.sp.gestaoContrato.models.Empresa;
import br.gov.sp.gestaoContrato.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	private static final String EMPRESA_NAO_ENCONTRADA_ID = "Não foi possível encontrar uma empresa com o id %s ";
	private static final String NOME_EMPRESA_JA_CADASTRADA = "a empresa %s já encontra-se cadastrada no banco de dados.";
	private EmpresaRepository repo;
	private static final Logger logger = LoggerFactory.getLogger(EmpresaService.class);
	

	// constructor forçando o spring a passar uma instância do repository
	public EmpresaService(EmpresaRepository repo) {
		this.repo = repo;
	}
	
	
	
	
	// cadastrar nova empresa
	public Empresa cadastrar(Empresa e) {
		try {
			/*
			 * LÓGICA
			 * 1- verificar se nome da empresa já está cadastrada.
			 * 2- salvar e retornar a empresa salva
			 */
			if (repo.existsByNome(e.getNome())) {
				throw new EmpresaNegocioException(
						String.format(NOME_EMPRESA_JA_CADASTRADA, e.getNome())
						);
			}
			
			return repo.save(e);
			
		} catch (Exception e2) {
			logger.error(String.format("Ocorreu o seguinte erro ao tentar cadastrar uma empresa: %s", e2.getMessage()));
			return null;
		}	
		
	}// fecha cadastrar empresa
	
	
	
	
	
	// alterar empresa
	public Empresa alterar(Integer id, Empresa e) {
		try {
			
			/*
			 * LÓGICA
			 * 1- verificar se o id da empresa recebido pela API existe, se existir buscar a entidade.
			 * 2- verificar se o nome da empresa recebido pela API já esta cadastrado em outro id.
			 * 3- copiar todas as informações da empresa recebida pela API para a empresaBanco.
			 * 4- salvar e retornar a empresa salva.
			 */
			
			Empresa empresaBanco = getEmpresaPorId(id);
			if (repo.existsByNomeAndIdNot(e.getNome(), id)) {
				throw new EmpresaNegocioException(
						String.format(NOME_EMPRESA_JA_CADASTRADA, e.getNome())
						);
			}
			
			// copiar informações e ignorar ID
			BeanUtils.copyProperties(e, empresaBanco, "id");
			return repo.save(empresaBanco);
			
		} catch (Exception e2) {
			logger.error(String.format("Ocorreu o seguinte erro ao tentar alterar uma empresa: %s", e2.getMessage()));
			return null;
		}
		
	}// fecha alterar empresa
	
	
	
	
	
	// remover empresa
	public void excluir(Integer id) {
		/*
		 * LÓGICA
		 * 1- verificar se existe uma empresa com o id recebido pela API, se existir buscar a entidade
		 * 2- tentar remover a entidade e verificar se vai haver erro de integridade. 
		 */
		try {
			Empresa empresaBanco = getEmpresaPorId(id);
			repo.delete(empresaBanco);
		} catch (ConstraintViolationException c) {
			throw new EmpresaNegocioException(
					String.format("A empresa de id %s não pode ser excluída porque esta vinculada a outra entidade.", id));
		} catch (Exception e) {
			logger.error(String.format("Ocorreu o seguinte erro ao tentar excluir uma empresa: %s", e.getMessage()));
		}
		
	}// fecha method excluir



	// get by id
	public Empresa getById(Integer id) {
		try {
			Empresa empresaBanco = getEmpresaPorId(id);
			return empresaBanco;
		} catch (Exception e) {
			logger.error(String.format("Ocorreu o seguinte erro ao tentar consultar uma empresa pelo id: %s", e.getMessage()));
			return null;
		}
	}




	private Empresa getEmpresaPorId(Integer id) {
		return repo.findById(id)
		           .orElseThrow(() -> new EmpresaNegocioException(
		        		   String.format(EMPRESA_NAO_ENCONTRADA_ID, id)
		        		   ));
	}
	
	
	
	
	
	// get all don´t pageable
	public List<Empresa> getAll() {
		try {
			return repo.findAllByOrderByNomeAsc();
		} catch (Exception e) {
			logger.error(String.format("Ocorreu o seguinte erro ao tentar consultar todas empresa: %s", e.getMessage()));
			return null;
		}
	}
	
	

}// fecha classe
