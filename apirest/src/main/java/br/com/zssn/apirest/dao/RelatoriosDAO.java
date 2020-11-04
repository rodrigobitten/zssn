package br.com.zssn.apirest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.zssn.apirest.config.BDConfig;
import br.com.zssn.apirest.vos.RelatorioMediaItemSobreviventeVO;
import br.com.zssn.apirest.vos.RelatorioPontosPerdidosVO;
import br.com.zssn.apirest.vos.RelatorioSobreviventesInfectadosVO;

public class RelatoriosDAO {
	
	
public  List<RelatorioSobreviventesInfectadosVO> listarRelatorioSobreviventesInfectadosVO() throws Exception {
		
		List<RelatorioSobreviventesInfectadosVO> lista = new ArrayList<>();
		
		Connection conexao = BDConfig.getConnection();
		
		String sql = "select \r\n"
				+ " round(cast((select sum(case when infectado=true then 1 else 0 end) from sobrevivente where infectado=true) as Numeric(10,2))/cast(count(*) as Numeric(10,2))*100,2) as SIM\r\n"
				+ ",round(cast((select sum(case when infectado=false then 1 else 0 end) from sobrevivente where infectado=false) as Numeric(10,2))/cast(count(*) as Numeric(10,2))*100,2) as NAO\r\n"
				+ " from sobrevivente";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		
		ResultSet rs = statement.executeQuery();
		
		while (rs.next()) {
			
			RelatorioSobreviventesInfectadosVO relatoriosobreviventesinfectadosVO = new RelatorioSobreviventesInfectadosVO();
			
			relatoriosobreviventesinfectadosVO.setSim(rs.getBigDecimal("SIM"));
			relatoriosobreviventesinfectadosVO.setNao(rs.getBigDecimal("NAO"));
			
			lista.add(relatoriosobreviventesinfectadosVO);
		}
		return lista;
	}

public  List<RelatorioMediaItemSobreviventeVO> listarRelatorioMediaItemSobreviventeVO() throws Exception {
	
	List<RelatorioMediaItemSobreviventeVO> lista = new ArrayList<>();
	
	Connection conexao = BDConfig.getConnection();
	
	String sql = " select  c.descricao, \r\n"
			+ "cast(cast((sum(b.quantidade)) as numeric(10,2))/cast((select count(id) from sobrevivente as s where infectado= false) as numeric(10,2)) as Numeric(10,2)) Media_Itens\r\n"
			+ "from inventario as b\r\n"
			+ "inner join itens_comerciais as c on c.id=b.iditemcomercial \r\n"
			+ "  group by c.descricao ";
	
	PreparedStatement statement = conexao.prepareStatement(sql);
	
	ResultSet rs = statement.executeQuery();
	
	while (rs.next()) {
		
		RelatorioMediaItemSobreviventeVO relatorioMediaItemSobreviventeVO = new RelatorioMediaItemSobreviventeVO();
		
		relatorioMediaItemSobreviventeVO.setDescricao(rs.getString("descricao"));
		relatorioMediaItemSobreviventeVO.setMedia(rs.getBigDecimal("Media_Itens"));
		
		lista.add(relatorioMediaItemSobreviventeVO);
	}
	return lista;
}

public  List<RelatorioPontosPerdidosVO> listarRelatorioPontosPerdidosVO() throws Exception {
	
	List<RelatorioPontosPerdidosVO> lista = new ArrayList<>();
	
	Connection conexao = BDConfig.getConnection();
	
	String sql = " select   sum((a.quantidade * c.pontuacao)) as pontos_perdidos\r\n"
			+ "from inventario as a\r\n"
			+ "inner join sobrevivente as b on b.id=a.idsobrevivente\r\n"
			+ "inner join itens_comerciais as c on c.id=a.iditemcomercial \r\n"
			+ " where b.infectado = true ";
	
	PreparedStatement statement = conexao.prepareStatement(sql);
	
	ResultSet rs = statement.executeQuery();
	
	while (rs.next()) {
		
		RelatorioPontosPerdidosVO relatorioPontosPerdidosVO = new RelatorioPontosPerdidosVO();
		
		relatorioPontosPerdidosVO.setPontosPerdidos(rs.getInt("pontos_perdidos"));
		
		lista.add(relatorioPontosPerdidosVO);
	}
	return lista;
}

}
