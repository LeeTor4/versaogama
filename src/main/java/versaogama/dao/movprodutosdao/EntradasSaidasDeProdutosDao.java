package versaogama.dao.movprodutosdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.movprodutos.EntradasSaidasDeProdutos;

public class EntradasSaidasDeProdutosDao {

	private final Pool pool;
	
	public EntradasSaidasDeProdutosDao(Pool pool) {
		this.pool = pool;
	}
	
	
	public List<EntradasSaidasDeProdutos> retornaCadastroMovProdutos(String mes) throws SQLException {
		
		List<EntradasSaidasDeProdutos> retorno = new ArrayList<EntradasSaidasDeProdutos>();
	   
		String sql = "SELECT * FROM mov_prod_mensal where mes = ?"; 
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setString(1, mes);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				
				while(rs.next()) {		
					
					EntradasSaidasDeProdutos entsai = rsCadastroMovProdutos(rs);
					retorno.add(entsai);
				}
				
			}
			
		}
		pool.liberarConnection(con);
		return retorno;
	}
	
	
	
	
	public void cadastrarTabEntSaiDez(EntradasSaidasDeProdutos entradaSaida) throws SQLException {
		String sql = "INSERT INTO tb_ent_sai_dez(id_pai,id_cod_item,cnpj,descricao,ano,mes,cod_item,cod_ant_item,cod_ant_item"
				+ "tot_qtde_ent,"
				+ "vl_tot_item_ent,"
				+ "tot_qtde_sai,"
				+ "vl_tot_item_sai,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtEntradaSaida(stmt, entradaSaida);
			stmt.execute();
		}
		System.out.println("Movimentação " + entradaSaida.getIdPai() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}
	
	
	
	private EntradasSaidasDeProdutos rsCadastroMovProdutos(ResultSet rs) throws SQLException {
	
		EntradasSaidasDeProdutos retorno = new EntradasSaidasDeProdutos();
		
		retorno.setIdCodItem(rs.getLong("id_cod_item"));
		retorno.setCnpj(rs.getString("cnpj"));
		retorno.setDescricao(rs.getString("descricao"));
		retorno.setAno(rs.getString("ano"));
		retorno.setMes(rs.getString("mes"));
		retorno.setCodItem(rs.getString("cod_item"));
		retorno.setCodAntItem(rs.getString("codigo_ant_item"));

		return retorno;
	}
	
	
	
	private void stmtEntradaSaida(PreparedStatement stmt, EntradasSaidasDeProdutos entradaSaida) throws SQLException {
		
		stmt.setLong(1, entradaSaida.getIdPai());
		stmt.setLong(2, entradaSaida.getIdCodItem());
		stmt.setString(3, entradaSaida.getCnpj());
		stmt.setString(4, entradaSaida.getDescricao());
		stmt.setString(5, entradaSaida.getAno());
		stmt.setString(6, entradaSaida.getMes());
		stmt.setString(7, entradaSaida.getCodItem());
		stmt.setString(8, entradaSaida.getCodAntItem());
		stmt.setDouble(9, entradaSaida.getTotQtdeEnt());
		stmt.setDouble(10, entradaSaida.getTotVlItemEnt());
		stmt.setDouble(11, entradaSaida.getTotQtdeSai());
		stmt.setDouble(12, entradaSaida.getTotVlItemSai());
		if(entradaSaida.getId() != null && entradaSaida.getId() > 0) {
			stmt.setDouble(13, entradaSaida.getId());
		}else {
			stmt.setDouble(13, 0);
		}
		
	}
	
	private EntradasSaidasDeProdutos rsEntradaSaida(ResultSet rs) throws SQLException {
		EntradasSaidasDeProdutos retorno = new EntradasSaidasDeProdutos();
		
		retorno.setId(rs.getLong("id"));
		retorno.setIdPai(rs.getLong("id_pai"));
		retorno.setIdCodItem(rs.getLong("id_cod_item"));
		retorno.setCnpj(rs.getString("cnpj"));
		retorno.setDescricao(rs.getString("descricao"));
		retorno.setAno(rs.getString("ano"));
		retorno.setMes(rs.getString("mes"));
		retorno.setCodItem(rs.getString("cod_item"));
		retorno.setCodAntItem(rs.getString("cod_ant_item"));
		retorno.setTotQtdeEnt(rs.getDouble("tot_qtde_ent"));
		retorno.setTotVlItemEnt(rs.getDouble("vl_tot_item_ent"));
		retorno.setTotQtdeSai(rs.getDouble("tot_qtde_sai"));
		retorno.setTotVlItemSai(rs.getDouble("vl_tot_item_sai"));
		
		return retorno;
	}

}
