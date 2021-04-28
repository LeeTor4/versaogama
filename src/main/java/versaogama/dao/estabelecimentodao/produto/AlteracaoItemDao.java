package versaogama.dao.estabelecimentodao.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.produto.AlteracaoItem;
import versaogama.util.UtilsEConverters;

public class AlteracaoItemDao implements AlteracaoItemDaoInterface{

	private Pool pool;
	
	public AlteracaoItemDao(Pool pool) {
		this.pool = pool;
	}
	
	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_alteracao_item WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( "Alteração Item excluída  com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public void salvar(AlteracaoItem alt) throws SQLException {
	  
		 if(alt.getId() != null && alt.getId() > 0){
			 atualizar(alt);
		 }else {
			 cadastrar(alt);
		 }
		
	}

	@Override
	public void cadastrar(AlteracaoItem alt) throws SQLException {
		String sql = "INSERT INTO tb_alteracao_item (id_pai_emp,id_pai_est,id_pai,descr_ant_item,dt_inicial,dt_final,codigo_ant_item,id) VALUES(?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtAlteraItem(alt, stmt);
            stmt.execute();
		}
		System.out.println("Alteracao do  item " + alt.getCodAntItem() + ", cadastrada com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public void atualizar(AlteracaoItem alt) throws SQLException {
		String sql = "UPDATE tb_alteracao_item SET (id_pai_emp=?,id_pai_est=?,id_pai=?,descr_ant_item=?,dt_inicial=?,dt_final=?,cod_ant_item=?) WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmtAlteraItem(alt, stmt);
            stmt.execute();
		}
		System.out.println("Alteracao do  item " + alt.getCodAntItem() + ", alterada com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public AlteracaoItem getAlteracaoItem(Integer codigo) throws SQLException {
		AlteracaoItem alt  = new AlteracaoItem();
		String sql = "SELECT * FROM tb_alteracao_item WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setLong(1, codigo);
			stmt.execute();			
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {						
					alt = rsAlteraItem(rs);
				}	
			}	
			
		}
		pool.liberarConnection(con);
		return alt;
	}

	@Override
	public List<AlteracaoItem> getAltItens() throws SQLException {
		List<AlteracaoItem> retorno = new ArrayList<AlteracaoItem>();
		String sql = "SELECT * FROM tb_alteracao_item";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){				
			stmt.execute();			
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {						
					AlteracaoItem alt = rsAlteraItem(rs);
					retorno.add(alt);
				}	
			}	
		}
		pool.liberarConnection(con);
		return retorno;
	}

	private void stmtAlteraItem(AlteracaoItem alt, PreparedStatement stmt) throws SQLException {
		
		stmt.setLong(1, alt.getIdPaiEmp());
		stmt.setLong(2, alt.getIdPaiEst());
		stmt.setLong(3, alt.getIdPai());
		stmt.setString(4, alt.getDescrAntItem());
		stmt.setDate(5, UtilsEConverters.getLocalDateParaDateSQL(alt.getDtInicial()));
		stmt.setDate(6, UtilsEConverters.getLocalDateParaDateSQL(alt.getDtFinal()));
		stmt.setString(7, alt.getCodAntItem());
		if(alt.getId() != null && alt.getId() > 0) {
			stmt.setLong(8, alt.getId());
		}else {
			stmt.setLong(8, 0);
		}
	}
	
	private AlteracaoItem rsAlteraItem(ResultSet rs) throws SQLException {
		
		AlteracaoItem alt = new AlteracaoItem();
		
		alt.setId(rs.getLong("id"));
		alt.setIdPaiEmp(rs.getLong("id_pai_emp"));
		alt.setIdPaiEst(rs.getLong("id_pai_est"));
		alt.setIdPai(rs.getLong("id_pai"));
		alt.setDescrAntItem(rs.getString("descr_ant_item"));
		alt.setDtInicial(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_inicial")));
		alt.setDtFinal(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_final")));
		alt.setCodAntItem(rs.getString("codigo_ant_item"));
		
		
		return alt;
	}
	
}
