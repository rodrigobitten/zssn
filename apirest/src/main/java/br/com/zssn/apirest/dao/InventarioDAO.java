package br.com.zssn.apirest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.zssn.apirest.config.BDConfig;
import br.com.zssn.apirest.models.Inventario;
import br.com.zssn.apirest.vos.InventarioVO;

public class InventarioDAO {

	public List<InventarioVO> listarInventarioVO() throws Exception {

		List<InventarioVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "SELECT a.id, iditemcomercial, c.descricao, idsobrevivente, b.nome, quantidade, \r\n"
				+ " b.infectado,  c.pontuacao \r\n" + "FROM inventario as a \r\n"
				+ "INNER JOIN sobrevivente as b ON a.idsobrevivente=b.id\r\n"
				+ "INNER JOIN itens_comerciais as c ON a.iditemcomercial=c.id order by idsobrevivente";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet rs = statement.executeQuery();

		while (rs.next()) {

			InventarioVO inventarioVO = new InventarioVO();

			inventarioVO.setId(rs.getInt("id"));
			inventarioVO.setIdsobrevivente(rs.getLong("idsobrevivente"));
			inventarioVO.setNomeSobrevivente(rs.getString("nome"));
			inventarioVO.setIditemcomercial(rs.getLong("iditemcomercial"));
			inventarioVO.setDescricao(rs.getString("descricao"));
			inventarioVO.setQuantidade(rs.getInt("quantidade"));
			inventarioVO.setPontuacao(rs.getInt("pontuacao"));

			lista.add(inventarioVO);
		}
		return lista;
	}

	public List<InventarioVO> listarInventarioVOPorIdSobrevivente(Long idsobrevivente) throws Exception {

		List<InventarioVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "SELECT a.id, iditemcomercial, c.descricao, idsobrevivente, b.nome, quantidade, \r\n"
				+ " b.infectado,  c.pontuacao \r\n" + "FROM inventario as a \r\n"
				+ "INNER JOIN sobrevivente as b ON a.idsobrevivente=b.id\r\n"
				+ "INNER JOIN itens_comerciais as c ON a.iditemcomercial=c.id " + " where idsobrevivente = ?"
				+ "order by idsobrevivente";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setLong(1, idsobrevivente);

		ResultSet rs = statement.executeQuery();

		while (rs.next()) {

			InventarioVO inventarioVO = new InventarioVO();

			inventarioVO.setId(rs.getInt("id"));
			inventarioVO.setIdsobrevivente(rs.getLong("idsobrevivente"));
			inventarioVO.setNomeSobrevivente(rs.getString("nome"));
			inventarioVO.setIditemcomercial(rs.getLong("iditemcomercial"));
			inventarioVO.setDescricao(rs.getString("descricao"));
			inventarioVO.setQuantidade(rs.getInt("quantidade"));
			inventarioVO.setPontuacao(rs.getInt("pontuacao"));

			lista.add(inventarioVO);
		}
		return lista;
	}

	public Inventario possuiQuantidadeInvetario(int idsobrevivente, int iditemcomercial, int quantidade)
			throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "SELECT * FROM inventario as a "

				+ " where a.idsobrevivente = ? AND  a.iditemcomercial = ? AND a.quantidade >= ?"
				+ "order by idsobrevivente";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setLong(1, idsobrevivente);
		statement.setLong(2, iditemcomercial);
		statement.setInt(3, quantidade);

		ResultSet rs = statement.executeQuery();

		Inventario inventario = new Inventario();
		while (rs.next()) {
			inventario.setId(rs.getInt("id"));
			inventario.setIdsobrevivente(rs.getLong("idsobrevivente"));
			inventario.setIditemcomercial(rs.getLong("iditemcomercial"));
			inventario.setQuantidade(rs.getInt("quantidade"));
		}
		return inventario;
	}
	
	public Inventario possuiItemInvetario(int idsobrevivente, int iditemcomercial)
			throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql = "SELECT * FROM inventario as a "

				+ " where a.idsobrevivente = ? AND  a.iditemcomercial = ?"
				+ "order by idsobrevivente";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setLong(1, idsobrevivente);
		statement.setLong(2, iditemcomercial);

		ResultSet rs = statement.executeQuery();

		Inventario inventario = new Inventario();
		while (rs.next()) {
			inventario.setId(rs.getInt("id"));
			inventario.setIdsobrevivente(rs.getLong("idsobrevivente"));
			inventario.setIditemcomercial(rs.getLong("iditemcomercial"));
			inventario.setQuantidade(rs.getInt("quantidade"));
		}
		return inventario;
	}

	public boolean atualizarItemInvetario(int idsobrevivente, int iditemcomercial,
			int quantidadeCalculado) throws Exception {

		Connection conexao = BDConfig.getConnection();

		// atualiza inventario origem
		String sql = "update inventario set quantidade = ? where idsobrevivente = ? and iditemcomercial = ? ";

		PreparedStatement statement = conexao.prepareStatement(sql);

		statement.setInt(1, quantidadeCalculado);
		statement.setInt(2, idsobrevivente);
		statement.setInt(3, iditemcomercial);

		statement.executeUpdate();

		return true;
	}

}
