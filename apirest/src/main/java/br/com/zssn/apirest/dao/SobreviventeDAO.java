package br.com.zssn.apirest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.zssn.apirest.config.BDConfig;
import br.com.zssn.apirest.models.Sobrevivente;

public class SobreviventeDAO {
	
	public List<Sobrevivente> listarSobreviventes() throws Exception {
		
		List<Sobrevivente> lista = new ArrayList<>();
		
		Connection conexao = BDConfig.getConnection();
		
		String sql = "Select * from sobrevivente";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		ResultSet rs = statement.executeQuery();
		
		while (rs.next()) {
			
			Sobrevivente sobrevivente = new Sobrevivente();
			
			sobrevivente.setId(rs.getInt("id"));
			sobrevivente.setNome(rs.getString("nome"));
			sobrevivente.setDtnascimento(rs.getDate("dtnascimento"));
			sobrevivente.setSexo(rs.getString("sexo"));
			sobrevivente.setLatitude(rs.getBigDecimal("latitude"));
			sobrevivente.setLongitude(rs.getBigDecimal("longitude"));
			sobrevivente.setInfectado(rs.getBoolean("infectado"));
			
			lista.add(sobrevivente);
		}
		return lista;
	}
	
	public Sobrevivente buscarSobreviventePorId(int id) throws Exception {
		
		Sobrevivente sobrevivente = null;
		
		Connection conexao = BDConfig.getConnection();
		
		String sql = "Select * from sobrevivente where id = ?";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		statement.setInt(1, id);
		
		ResultSet rs = statement.executeQuery();
		
		if (rs.next()) {
			
			sobrevivente = new Sobrevivente();
			
			sobrevivente.setId(rs.getInt("id"));
			sobrevivente.setNome(rs.getString("nome"));
			sobrevivente.setSexo(rs.getString("sexo"));
			sobrevivente.setLatitude(rs.getBigDecimal("latitude"));
			sobrevivente.setLongitude(rs.getBigDecimal("longitude"));
			sobrevivente.setInfectado(rs.getBoolean("infectado"));
			
		}
		return sobrevivente;
	}
	
	public void addSobrevivente(Sobrevivente sobrevivente) throws Exception {
		
		Connection conexao = BDConfig.getConnection();
		
		String sql = "Insert into sobrevivente(nome, sexo, latitude, longitude, dtnascimento,infectado) values(?, ?, ?, ?, ?, ? )";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		statement.setString(1, sobrevivente.getNome());
		statement.setString(2, sobrevivente.getSexo());
		statement.setBigDecimal(3, sobrevivente.getLatitude());
		statement.setBigDecimal(4, sobrevivente.getLongitude());
		statement.setDate(5, sobrevivente.getDtnascimento());
		statement.setBoolean(6, sobrevivente.getInfectado());
		statement.execute();
		
	}
	
	public void atualizarLocalizacao(Sobrevivente sobrevivente) throws Exception{
		
		Connection conexao = BDConfig.getConnection();
		
		String sql = "update sobrevivente set latitude = ?, longitude = ? where id = ?";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		
		statement.setBigDecimal(1,sobrevivente.getLatitude());
		statement.setBigDecimal(2, sobrevivente.getLongitude());
		statement.setLong(3, sobrevivente.getId());
		
		statement.executeUpdate();
	}

public Sobrevivente existeSobreviventeInfectado(int idsobrevivente) throws Exception {
		
		Sobrevivente sobrevivente = null;
		
		Connection conexao = BDConfig.getConnection();
		
		String sql = "Select * from sobrevivente where id = ? and infectado = true";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		statement.setInt(1, idsobrevivente);
		
		ResultSet rs = statement.executeQuery();
		
		if (rs.next()) {
			
			sobrevivente = new Sobrevivente();

			sobrevivente.setInfectado(rs.getBoolean("infectado"));
			
		}
		return sobrevivente;
	}
}
