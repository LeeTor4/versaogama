package versaogama.dao.bancodao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import versaogama.conexao.Pool;
import versaogama.model.system.banco.Metadados;

public class BancoDAO {

	private Pool pool;
	
	
	public BancoDAO(Pool pool) {
		this.pool = pool;
	}
	
	
	public Metadados dadosDoBanco(String nomeBD, String tipoTabela, String nomeTabela){
		
		Metadados retorno = null;
		String sql = "SELECT * FROM information_schema.tables where table_schema = ? and table_type = ? and table_name = ?";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setString(1, nomeBD);
			stmt.setString(2, tipoTabela);
			stmt.setString(3, nomeTabela);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				
				while(rs.next()) {		
					
					retorno = new Metadados();
					retorno.setNomeBanco(rs.getString("TABLE_SCHEMA"));
					retorno.setNomeTabela(rs.getString("TABLE_NAME"));
					retorno.setLinhaTabela(rs.getLong("TABLE_ROWS"));
					retorno.setAutoIncremento(rs.getLong("AUTO_INCREMENT"));
					
					
				}
				
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return retorno;
	}
	
	public void metadados() {
		Connection con = pool.getConnection();
		
		try {
			ResultSet schemas = con.getMetaData().getCatalogs();
			while(schemas.next()) {		
			
			  System.out.println(schemas.getString(1));	
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
	}
}
