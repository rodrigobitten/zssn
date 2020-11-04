package br.com.zssn.apirest.resources;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zssn.apirest.dao.SobreviventeDAO;
import br.com.zssn.apirest.models.Sobrevivente;
import br.com.zssn.apirest.repository.InventarioRepository;
import br.com.zssn.apirest.repository.SobreviventeRepository;

@RestController
@RequestMapping(value = "/api")
public class SobreviventeResource {

	private SobreviventeDAO sobreviventeDAO;
	
	@PostConstruct
	private void init() {
		sobreviventeDAO = new SobreviventeDAO();
	}
	
	@Autowired
	SobreviventeRepository sobreviventeRepository;
	
	@Autowired
	InventarioRepository inventarioRepository;

	//Lista todos os sobreviventes cadastrados
	@GetMapping(path = "/sobreviventes")
	/*public List<Sobrevivente> listaSobreviventes(){
		return sobreviventeRepository.findAll();
	}*/
	public Optional<List<Sobrevivente>>  listaSobreviventes(){
		List<Sobrevivente> lista = null;
		try {
			lista = sobreviventeDAO.listarSobreviventes();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(lista);
	}

	//Atualiza localizacao do sobrevivente
	@PutMapping(path = "/sobrevivente/atualizalocalizacao")
	public void atualizaLocalizacao(@RequestBody Sobrevivente sobrevivente) {
		try {
			sobreviventeDAO.atualizarLocalizacao(sobrevivente);
		}
		catch (Exception e ){
			e.printStackTrace();
		}
	}
	
	//Busca um determinado sobrevivente atraves do seu ID
	@GetMapping(path = "/sobrevivente/{id}")
	public Optional<Sobrevivente> buscarSobreviventePorId(@PathVariable(value="id") Long id){
		return sobreviventeRepository.findById(id);
	}
	
	//Cadastra um sobrevivente
	@PostMapping(path = "sobrevivente/salvar")
	public Sobrevivente salvaSobrevivente(@RequestBody Sobrevivente sobrevivente) {
		return sobreviventeRepository.save(sobrevivente);
		
	}
	
	//Exclui um sobrevivente
	@DeleteMapping("sobrevivente/deletar")
	public void deletaSobrevivente(@RequestBody Sobrevivente sobrevivente) {
		sobreviventeRepository.delete(sobrevivente);
	}
	
	//Atualiza dados de um sobrevivente
	@PutMapping("sobrevivente/alterar")
	public Sobrevivente alteraSobrevivente(@RequestBody Sobrevivente sobrevivente) {
		return sobreviventeRepository.save(sobrevivente);
	}
}
