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

import br.com.zssn.apirest.dao.InventarioDAO;

import br.com.zssn.apirest.models.Inventario;

import br.com.zssn.apirest.repository.InventarioRepository;
import br.com.zssn.apirest.repository.ItensComerciaisRepository;
import br.com.zssn.apirest.repository.SobreviventeRepository;
import br.com.zssn.apirest.vos.InventarioVO;


@RestController
@RequestMapping(value = "/api")
public class InventarioResource {

	private InventarioDAO inventarioDAO;
	
	@PostConstruct
	private void init() {
		inventarioDAO = new InventarioDAO();
	}
	
	
	@Autowired
	InventarioRepository inventarioRepository;

	@Autowired
	SobreviventeRepository sobreviventeRepository;

	@Autowired
	ItensComerciaisRepository itensComerciaisRepository;

	@GetMapping(path = "/inventarios")
	/*public List<Inventario> listaInventarios() {

		return inventarioRepository.findAll();
	}*/
	
	public Optional<List<InventarioVO>>  listaInventarioVO(){
		List<InventarioVO> lista = null;
		try {
			lista = inventarioDAO.listarInventarioVO();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(lista);
	}

	@GetMapping(path = "/inventario/{idsobrevivente}")
	public Optional<List<InventarioVO>> buscarInventarioPorIdSobrevivente(@PathVariable(value = "idsobrevivente") Long idsobrevivente) {
			List<InventarioVO> lista = null;
			
			try {
				
				lista = inventarioDAO.listarInventarioVOPorIdSobrevivente(idsobrevivente);
				
			}catch (Exception e){
				e.printStackTrace();
			}
			return Optional.ofNullable(lista);
	}
	/*public Optional<List<InventarioVO>> buscarInventarioPorId(
			@PathVariable(value = "idsobrevivente") Long idsobrevivente) {
		List<InventarioVO> listVO = new ArrayList<InventarioVO>();
		List<Inventario> list = inventarioRepository.findAll();
		List<Inventario> list2 = list.stream().filter(x -> x.getIdsobrevivente() == idsobrevivente)
				.collect(Collectors.toList());
		for (Inventario inventario : list2) {
			InventarioVO vo = new InventarioVO();
			Inventario inv = inventarioRepository.findById(inventario.getId()).get();
			Sobrevivente sob = sobreviventeRepository.findById(inventario.getIdsobrevivente()).get();
			ItensComerciais itc = itensComerciaisRepository.findById(inventario.getIditemcomercial()).get();
			vo.setDescricao(itc.getDescricao());
			vo.setNomeSobrevivente(sob.getNome());
			vo.setPontuacao(itc.getPontuacao());
			vo.setQuantidade(inv.getQuantidade());
			vo.setId(inventario.getId());
			vo.setIditemcomercial(inventario.getIditemcomercial());
			vo.setIdsobrevivente(inventario.getIdsobrevivente());
			listVO.add(vo);
		}

		return Optional.ofNullable(listVO);
	}*/

	@PostMapping(path = "inventario/salvar")
	public Inventario salvaInventario(@RequestBody Inventario inventario) {
		return inventarioRepository.save(inventario);

	}

	@DeleteMapping("inventario/deletar")
	public void deletaInventario(@RequestBody Inventario inventario) {
		inventarioRepository.delete(inventario);
	}

	@PutMapping("inventario/alterar")
	public Inventario alteraInventario(@RequestBody Inventario inventario) {
		return inventarioRepository.save(inventario);
	}
}
