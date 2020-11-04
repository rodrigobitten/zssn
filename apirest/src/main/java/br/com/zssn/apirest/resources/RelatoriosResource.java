package br.com.zssn.apirest.resources;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.zssn.apirest.dao.RelatoriosDAO;
import br.com.zssn.apirest.vos.RelatorioMediaItemSobreviventeVO;
import br.com.zssn.apirest.vos.RelatorioPontosPerdidosVO;
import br.com.zssn.apirest.vos.RelatorioSobreviventesInfectadosVO;

@RestController
@RequestMapping(value = "/api")
public class RelatoriosResource {
	
	private RelatoriosDAO relatoriosDAO;
	
	@PostConstruct
	private void init() {
		relatoriosDAO = new RelatoriosDAO();
	}

	//Relatorio de percentual de sobreviventes infectados
	@GetMapping(path = "relatorios/relatoriosobreviventesinfectados")
	public Optional<List<RelatorioSobreviventesInfectadosVO>>  listaRelatorioSobreviventesInfectadosVO(){
		List<RelatorioSobreviventesInfectadosVO> lista = null;
		try {
			lista = relatoriosDAO.listarRelatorioSobreviventesInfectadosVO();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(lista);
	}
	
	@GetMapping(path = "relatorios/relatoriomediaitemsobrevivente")
	public Optional<List<RelatorioMediaItemSobreviventeVO>>  listaRelatorioMediaItemSobreviventeVO(){
		List<RelatorioMediaItemSobreviventeVO> lista = null;
		try {
			lista = relatoriosDAO.listarRelatorioMediaItemSobreviventeVO();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(lista);
	}
	
	@GetMapping(path = "relatorios/relatoripontosperdidos")
	public Optional<List<RelatorioPontosPerdidosVO>>  listaRelatorioPontosPerdidosVO(){
		List<RelatorioPontosPerdidosVO> lista = null;
		try {
			lista = relatoriosDAO.listarRelatorioPontosPerdidosVO();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return Optional.ofNullable(lista);
	}
}
