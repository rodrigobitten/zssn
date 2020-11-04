package br.com.zssn.apirest.resources;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zssn.apirest.dao.SinalizaInfeccaoDAO;

import br.com.zssn.apirest.models.SinalizaInfeccao;

import br.com.zssn.apirest.repository.SinalizaInfeccaoRepository;

import br.com.zssn.apirest.vos.SinalizaInfeccaoVO;

@RestController
@RequestMapping(value = "/api")
public class SinalizarInfeccaoResource {

	@Autowired
	SinalizaInfeccaoRepository sinalizainfeccaoRepository;
	
	private SinalizaInfeccaoDAO sinalizainfeccaoDAO;
	
	@PostConstruct
	private void init() {
		sinalizainfeccaoDAO = new SinalizaInfeccaoDAO();
	}
	
	//Lsta todos os sobreviventes relatados como infectados
	@GetMapping(path = "/sobrevivente/sinalizainfeccao/list")
	public Optional<List<SinalizaInfeccaoVO>>  listaSinalizaInfeccaoVO(){
		List<SinalizaInfeccaoVO> lista = null;
		try {
			
			lista = sinalizainfeccaoDAO.listarSinalizaInfeccaoVO();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(lista);
	}
	
	//Cadastra um sobrevivente relatado como infectado
	@PostMapping(path = "sobrevivente/sinalizainfeccao/salvar")
	/*public SinalizaInfeccao salvaSinalizaInfeccao(@RequestBody SinalizaInfeccao sinalizainfeccao) {
		return sinalizainfeccaoRepository.save(sinalizainfeccao);
		
	}*/
	public void salvaSinalizaInfeccao(@RequestBody SinalizaInfeccao sinalizainfeccao) {
		try {
			
			sinalizainfeccaoDAO.addSinalizaInfeccao(sinalizainfeccao);;
			
		}
		catch (Exception e ){
			
			e.printStackTrace();
		}
	}
	
}
