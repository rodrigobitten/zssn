package br.com.zssn.apirest.resources;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zssn.apirest.dao.InventarioDAO;
import br.com.zssn.apirest.dao.SobreviventeDAO;
import br.com.zssn.apirest.models.Inventario;
import br.com.zssn.apirest.models.ItensComerciais;
import br.com.zssn.apirest.models.Sobrevivente;
import br.com.zssn.apirest.repository.InventarioRepository;
import br.com.zssn.apirest.repository.ItensComerciaisRepository;
import br.com.zssn.apirest.repository.SobreviventeRepository;
import br.com.zssn.apirest.vos.ItensTrocaVO;
import br.com.zssn.apirest.vos.MessageVO;
import br.com.zssn.apirest.vos.TrocaVO;

@RestController
@RequestMapping(value = "/api")
public class TrocaResource {

	@Autowired
	SobreviventeRepository sobreviventeRepository;

	@Autowired
	InventarioRepository inventarioRepository;

	@Autowired
	ItensComerciaisRepository itensComerciaisRepository;

	private InventarioDAO inventarioDAO;

	@PostConstruct
	private void init1() {
		inventarioDAO = new InventarioDAO();
	}

	private SobreviventeDAO sobreviventeDAO;

	@PostConstruct
	private void init() {
		sobreviventeDAO = new SobreviventeDAO();
	}

	// Metodo para realizacao da troca de itens entre sobreviventes
	@PostMapping(path = "/realizartroca")
	public MessageVO realizarTroca(@RequestBody TrocaVO trocaVO) throws Exception {
		for (ItensTrocaVO itemVO : trocaVO.getItens()) {

			// Verifica se o sobrevivente origem existe
			Sobrevivente sobreviventeOrigem = sobreviventeRepository.findById(itemVO.getIdsobreviventeOrigem());
			if (sobreviventeOrigem == null) {
				return new MessageVO("Nao existe o sobrevivente de origem informado!");
			}
			// Verifica se o sobrevivente destino existe
			Sobrevivente sobreviventeDestino = sobreviventeRepository.findById(itemVO.getIdsobreviventeDestino());
			if (sobreviventeDestino == null) {
				return new MessageVO("Nao existe o sobrevivente de destino informado!");
			}

			// Verifica se o sobrevivente origem esta infectado
			Sobrevivente sobreviventeInfectadoOrigem = sobreviventeDAO
					.existeSobreviventeInfectado(((int) itemVO.getIdsobreviventeOrigem()));

			if (sobreviventeInfectadoOrigem == null) {
				return new MessageVO("Sobrevivente infectado, troca nao permitida!");
			}
			// Verifica se o sobrevivente destino esta infectado
			Sobrevivente sobreviventeInfectadoDestino = sobreviventeDAO
					.existeSobreviventeInfectado(((int) itemVO.getIdsobreviventeDestino()));

			if (sobreviventeInfectadoDestino == null) {
				return new MessageVO("Sobrevivente infectado, troca nao permitida!");
			}

			// Verifica se os itens da troca do sobrevivente origem existem
			ItensComerciais itensComerciaisOrigem = itensComerciaisRepository.findById(itemVO.getIditemOrigem());

			if (itensComerciaisOrigem == null) {
				return new MessageVO("Nao existe o item cadastrado no inventario origem!");
			}
			// Verifica se os itens da troca do sobrevivente destino existem
			ItensComerciais itensComerciaisDestino = itensComerciaisRepository.findById(itemVO.getIditemDestino());

			if (itensComerciaisDestino == null) {
				return new MessageVO("Nao existe o item cadastrado no inventario destino!");
			}

			Inventario inventarioOrigemAtual = inventarioDAO.possuiQuantidadeInvetario(itemVO.getIdsobreviventeOrigem(),
					itemVO.getIditemOrigem(), itemVO.getQuantidadeOrigem());

			// teste que verifica se existe quantidade no inventario de origem
			if (inventarioOrigemAtual.getId() == 0l) {
				return new MessageVO(
						"Nao existe quantidade sificiente do item cadastrado no inventario origem para realizar a troca!");
			}

			Inventario inventarioDestinoAtual = inventarioDAO.possuiQuantidadeInvetario(
					itemVO.getIdsobreviventeDestino(), itemVO.getIditemDestino(), itemVO.getQuantidadeDestino());

			// teste que verifica se existe quantidade no inventario de destino
			if (inventarioDestinoAtual.getId() == 0l) {
				return new MessageVO(
						"Nao existe quantidade sificiente do item cadastrado no inventario destino para realizar a troca!");
			}

			// 1 água 4 pontos
			// 1 comida 3 pontos
			// 1 medicação 2 pontos
			// 1 munição 1 ponto

			// verifica a quantidade de troca de origem com relacao ao destino
			int pontuacaoTotalOrigem = itensComerciaisOrigem.getPontuacao() * itemVO.getQuantidadeOrigem();
			int pontuacaoTotalDestino = itensComerciaisDestino.getPontuacao() * itemVO.getQuantidadeDestino();

			// Testa se a pontuacao e compativel para troca. Ela precisa ser igual
			if (pontuacaoTotalOrigem != pontuacaoTotalDestino) {
				return new MessageVO("A pontuacao nao satifaz a condicao para troca dos itens!");
			}

			// verifica se destino possui o item enviado pela origem, se sim atualiza o
			// inventario, caso contratio inclui
			Inventario itemInventarioPossuiDestino = inventarioDAO
					.possuiItemInvetario(itemVO.getIdsobreviventeDestino(), itemVO.getIditemOrigem());
			if (itemInventarioPossuiDestino.getId() > 0l) {
				int quantidadeDestinoCalculada = itemInventarioPossuiDestino.getQuantidade()
						+ itemVO.getQuantidadeOrigem();
				inventarioDAO.atualizarItemInvetario(itemVO.getIdsobreviventeDestino(), itemVO.getIditemOrigem(),
						quantidadeDestinoCalculada);
			} else {
				Inventario inventarioDestino = new Inventario();
				inventarioDestino.setIditemcomercial((long) (itemVO.getIditemOrigem()));
				inventarioDestino.setIdsobrevivente((long) (itemVO.getIdsobreviventeDestino()));
				inventarioDestino.setQuantidade(itemVO.getQuantidadeOrigem());
				inventarioRepository.save(inventarioDestino);
			}

			// verifica se origem possui o item enviado pela destino, se sim atualiza o
			// inventario, caso contratio inclui
			Inventario itemInventarioPossuiOrigem = inventarioDAO.possuiItemInvetario(itemVO.getIdsobreviventeOrigem(),
					itemVO.getIditemDestino());
			if (itemInventarioPossuiOrigem.getId() > 0l) {
				int quantidadeOrigemCalculada = itemInventarioPossuiOrigem.getQuantidade()
						+ itemVO.getQuantidadeDestino();
				inventarioDAO.atualizarItemInvetario(itemVO.getIdsobreviventeOrigem(), itemVO.getIditemDestino(),
						quantidadeOrigemCalculada);
			} else {
				Inventario inventarioOrigem = new Inventario();
				inventarioOrigem.setIditemcomercial((long) (itemVO.getIditemDestino()));
				inventarioOrigem.setIdsobrevivente((long) (itemVO.getIdsobreviventeOrigem()));
				inventarioOrigem.setQuantidade(itemVO.getQuantidadeOrigem());
				inventarioRepository.save(inventarioOrigem);
			}
		}
		return new MessageVO("Message: Sucesso!");

	}

}
