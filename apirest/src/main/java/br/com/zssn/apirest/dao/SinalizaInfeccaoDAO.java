package br.com.zssn.apirest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.zssn.apirest.config.BDConfig;
import br.com.zssn.apirest.models.SinalizaInfeccao;
import br.com.zssn.apirest.vos.SinalizaInfeccaoVO;

public class SinalizaInfeccaoDAO {

	public List<SinalizaInfeccaoVO> listarSinalizaInfeccaoVO() throws Exception {

		List<SinalizaInfeccaoVO> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "SELECT a.id, idsobrevivente, b.nome, quantidade\r\n" + "  FROM sinaliza_infeccao as a  \r\n"
				+ "  INNER JOIN sobrevivente as b ON a.idsobrevivente=b.id";

		PreparedStatement statement = conexao.prepareStatement(sql);

		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			SinalizaInfeccaoVO sinalizainfeccaoVO = new SinalizaInfeccaoVO();

			sinalizainfeccaoVO.setId(rs.getInt("id"));
			sinalizainfeccaoVO.setIdsobrevivente(rs.getInt("idsobrevivente"));
			sinalizainfeccaoVO.setNomesobrevivente(rs.getString("nome"));
			sinalizainfeccaoVO.setQuantidade(rs.getInt("quantidade"));

		}
		return lista;
	}

	public void addSinalizaInfeccao(SinalizaInfeccao sinalizainfeccao) throws Exception {

		Connection conexao = BDConfig.getConnection();

		String sql1 = "SELECT a.id, idsobrevivente, b.nome, quantidade\r\n" + "  FROM sinaliza_infeccao as a  \r\n"
				+ "  INNER JOIN sobrevivente as b ON a.idsobrevivente=b.id " + " where idsobrevivente = ?";

		PreparedStatement statement1 = conexao.prepareStatement(sql1);

		statement1.setInt(1, sinalizainfeccao.getIdsobrevivente());

		ResultSet rs1 = statement1.executeQuery();

		if (rs1.next()) {

			String sql = "update sinaliza_infeccao set quantidade = ? where idsobrevivente = ?";

			PreparedStatement statement = conexao.prepareStatement(sql);

			statement.setInt(1, rs1.getInt("quantidade") + 1);
			statement.setInt(2, sinalizainfeccao.getIdsobrevivente());

			statement.executeUpdate();

			String sql2 = "update sobrevivente set infectado = true where id = ? "
					+ " and exists (select * from sinaliza_infeccao as a where idsobrevivente = ? and quantidade>=3) ";

			PreparedStatement statement3 = conexao.prepareStatement(sql2);

			statement3.setInt(1, sinalizainfeccao.getIdsobrevivente());
			statement3.setInt(2, sinalizainfeccao.getIdsobrevivente());

			statement3.executeUpdate();

		} else {

			String sql = "insert into sinaliza_infeccao (idsobrevivente, quantidade) values (?, ?)";

			PreparedStatement statement2 = conexao.prepareStatement(sql);

			statement2.setInt(1, sinalizainfeccao.getIdsobrevivente());
			statement2.setInt(2, 1);

			statement2.execute();
		}
	}

}
