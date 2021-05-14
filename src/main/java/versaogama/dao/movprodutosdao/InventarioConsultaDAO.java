package versaogama.dao.movprodutosdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.movprodutos.ModelInventarioDeclarado;
import versaogama.util.UtilsEConverters;

public class InventarioConsultaDAO {

    private final Pool pool;
	
	public InventarioConsultaDAO(Pool pool) {
		this.pool = pool;
	}
	
	
    public List<ModelInventarioDeclarado> getInventarioDeclarado() throws SQLException{
    	
    	List<ModelInventarioDeclarado> retorno = new ArrayList<>();
    	
    	String sql = "SELECT * FROM v_movimentacao_inv";
    	
    	Connection con = pool.getConnection();
    	
    	if(!con.isClosed()) {
    		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
    			
    			stmt.execute();
				try(ResultSet rs = stmt.getResultSet()){
					
					while(rs.next()) {
						
						ModelInventarioDeclarado inv = new  ModelInventarioDeclarado();
						inv.setCnpj(rs.getString("cnpj"));
						inv.setAno(rs.getString("ano"));
						inv.setCodItem(rs.getString("cod_item"));
						inv.setCodItemAnt(rs.getString("cod_ant_item"));
						inv.setQtde(rs.getDouble("qtde"));
						inv.setVlUnit(rs.getDouble("vl_unit"));
						inv.setVlItem(rs.getDouble("vl_item"));
						inv.setDescricao(rs.getString("descricao"));
						
						
						retorno.add(inv);
					}
					
				}
    			
    		}
    		
    		pool.liberarConnection(con);
    	}else {
			con.close();
		}
    	

    	return retorno;
    	
    }
    
    public List<ModelInventarioDeclarado> getInventarioDeclarado(String ano) throws SQLException{
    	
    	List<ModelInventarioDeclarado> retorno = new ArrayList<ModelInventarioDeclarado>();
    	
    	String sql = "SELECT * FROM v_movimentacao_inv where ano = ?";
    	
    	Connection con = pool.getConnection();
    	
    	if(!con.isClosed()) {
    		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
    			stmt.setString(1, ano);
    			stmt.execute();
				try(ResultSet rs = stmt.getResultSet()){
					
					while(rs.next()) {
						
						ModelInventarioDeclarado inv = new  ModelInventarioDeclarado();
						inv.setCnpj(rs.getString("cnpj"));
						inv.setAno(rs.getString("ano"));
						inv.setCodItem(rs.getString("cod_item"));
						inv.setCodItemAnt(rs.getString("cod_ant_item"));
						inv.setQtde(rs.getDouble("qtde"));
						inv.setVlUnit(rs.getDouble("vl_unit"));
						inv.setVlItem(rs.getDouble("vl_item"));
						inv.setDescricao(rs.getString("descricao"));
						
						
						retorno.add(inv);
					}
					
				}
    			
    		}
    		
    		pool.liberarConnection(con);
    	}else {
			con.close();
		}
    	

    	return retorno;
    	
    }
    
    public ModelInventarioDeclarado getInventarioDec(String codItem,String codAntItem, String cnpj, String ano) throws SQLException{
    	
    	ModelInventarioDeclarado inv = null;
    	
    	String sql = "SELECT * FROM v_movimentacao_inv where cod_item in (?,?) and cnpj = ? and ano = ?";
    	
    	Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql)){	
			stmt.setString(1, codItem);
			stmt.setString(2, codAntItem);
			stmt.setString(3, cnpj);
			stmt.setString(4, ano);
			stmt.executeQuery();
			try(ResultSet rs = stmt.getResultSet()){		
				while(rs.next()) {	
				    inv = new ModelInventarioDeclarado();
					inv.setCnpj(rs.getString("cnpj"));
					inv.setAno(rs.getString("ano"));
					inv.setCodItem(rs.getString("cod_item"));
					inv.setCodItemAnt(rs.getString("cod_ant_item"));
					inv.setQtde(rs.getDouble("qtde"));
					inv.setVlUnit(rs.getDouble("vl_unit"));
					inv.setVlItem(rs.getDouble("vl_item"));
				    inv.setDescricao(rs.getString("descricao"));
				    inv.setDtInv(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_inv")));
					
					
				}
			}
			
		}
		pool.liberarConnection(con);
    	return inv;
    }
    
}
