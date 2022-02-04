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
	
	
	public Metadados dadosDoBanco(String nomeTabela){
		
		Metadados retorno = null;
		//String sql = "SELECT * FROM information_schema.tables where table_schema = ? and table_type = ? and table_name = ?";
		String sql = "select coalesce( max( id ), 0) as id from " + nomeTabela;
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			//stmt.setString(1, nomeTabela);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				
				while(rs.next()) {		
					
					retorno = new Metadados();
					retorno.setAutoIncremento(rs.getLong("id"));
						
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
