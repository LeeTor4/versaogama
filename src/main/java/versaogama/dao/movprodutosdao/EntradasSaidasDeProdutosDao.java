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
	
	
	public List<EntradasSaidasDeProdutos> retornaCadastroMovProdutos(Long id) throws SQLException {
		
		List<EntradasSaidasDeProdutos> retorno = new ArrayList<EntradasSaidasDeProdutos>();
	   
		String sql = "SELECT * FROM mov_prod_mensal where id = ?"; 
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setLong(1, id);
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
	
	
	public void cadastrarTabEntSaiJan(EntradasSaidasDeProdutos entradaSaida) throws SQLException {
		String sql = "INSERT INTO tb_ent_sai_jan(id_pai,id_cod_item,cnpj,descricao,ano,mes,cod_item,cod_ant_item,"
				+ "tot_qtde_ent,"
				+ "vl_tot_item_ent,"
				+ "tot_qtde_sai,"
				+ "vl_tot_item_sai,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtEntradaSaida(stmt, entradaSaida);
			stmt.execute();
		}
		System.out.println("Movimentação " + entradaSaida.getIdPai() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}
	
	public void cadastrarTabEntSaiFev(EntradasSaidasDeProdutos entradaSaida) throws SQLException {
		String sql = "INSERT INTO tb_ent_sai_fev(id_pai,id_cod_item,cnpj,descricao,ano,mes,cod_item,cod_ant_item,"
				+ "tot_qtde_ent,"
				+ "vl_tot_item_ent,"
				+ "tot_qtde_sai,"
				+ "vl_tot_item_sai,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtEntradaSaida(stmt, entradaSaida);
			stmt.execute();
		}
		System.out.println("Movimentação " + entradaSaida.getIdPai() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}
	
	public void cadastrarTabEntSaiMar(EntradasSaidasDeProdutos entradaSaida) throws SQLException {
		String sql = "INSERT INTO tb_ent_sai_mar(id_pai,id_cod_item,cnpj,descricao,ano,mes,cod_item,cod_ant_item,"
				+ "tot_qtde_ent,"
				+ "vl_tot_item_ent,"
				+ "tot_qtde_sai,"
				+ "vl_tot_item_sai,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtEntradaSaida(stmt, entradaSaida);
			stmt.execute();
		}
		System.out.println("Movimentação " + entradaSaida.getIdPai() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}
	
	public void cadastrarTabEntSaiAbr(EntradasSaidasDeProdutos entradaSaida) throws SQLException {
		String sql = "INSERT INTO tb_ent_sai_abr(id_pai,id_cod_item,cnpj,descricao,ano,mes,cod_item,cod_ant_item,"
				+ "tot_qtde_ent,"
				+ "vl_tot_item_ent,"
				+ "tot_qtde_sai,"
				+ "vl_tot_item_sai,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtEntradaSaida(stmt, entradaSaida);
			stmt.execute();
		}
		System.out.println("Movimentação " + entradaSaida.getIdPai() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}
	
	public void cadastrarTabEntSaiMai(EntradasSaidasDeProdutos entradaSaida) throws SQLException {
		String sql = "INSERT INTO tb_ent_sai_mai(id_pai,id_cod_item,cnpj,descricao,ano,mes,cod_item,cod_ant_item,"
				+ "tot_qtde_ent,"
				+ "vl_tot_item_ent,"
				+ "tot_qtde_sai,"
				+ "vl_tot_item_sai,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtEntradaSaida(stmt, entradaSaida);
			stmt.execute();
		}
		System.out.println("Movimentação " + entradaSaida.getIdPai() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}
	
	public void cadastrarTabEntSaiJun(EntradasSaidasDeProdutos entradaSaida) throws SQLException {
		String sql = "INSERT INTO tb_ent_sai_jun(id_pai,id_cod_item,cnpj,descricao,ano,mes,cod_item,cod_ant_item,"
				+ "tot_qtde_ent,"
				+ "vl_tot_item_ent,"
				+ "tot_qtde_sai,"
				+ "vl_tot_item_sai,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtEntradaSaida(stmt, entradaSaida);
			stmt.execute();
		}
		System.out.println("Movimentação " + entradaSaida.getIdPai() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}
	
	public void cadastrarTabEntSaiJul(EntradasSaidasDeProdutos entradaSaida) throws SQLException {
		String sql = "INSERT INTO tb_ent_sai_jul(id_pai,id_cod_item,cnpj,descricao,ano,mes,cod_item,cod_ant_item,"
				+ "tot_qtde_ent,"
				+ "vl_tot_item_ent,"
				+ "tot_qtde_sai,"
				+ "vl_tot_item_sai,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtEntradaSaida(stmt, entradaSaida);
			stmt.execute();
		}
		System.out.println("Movimentação " + entradaSaida.getIdPai() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}
	
	public void cadastrarTabEntSaiAgo(EntradasSaidasDeProdutos entradaSaida) throws SQLException {
		String sql = "INSERT INTO tb_ent_sai_ago(id_pai,id_cod_item,cnpj,descricao,ano,mes,cod_item,cod_ant_item,"
				+ "tot_qtde_ent,"
				+ "vl_tot_item_ent,"
				+ "tot_qtde_sai,"
				+ "vl_tot_item_sai,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtEntradaSaida(stmt, entradaSaida);
			stmt.execute();
		}
		System.out.println("Movimentação " + entradaSaida.getIdPai() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}
	
	public void cadastrarTabEntSaiSet(EntradasSaidasDeProdutos entradaSaida) throws SQLException {
		String sql = "INSERT INTO tb_ent_sai_set(id_pai,id_cod_item,cnpj,descricao,ano,mes,cod_item,cod_ant_item,"
				+ "tot_qtde_ent,"
				+ "vl_tot_item_ent,"
				+ "tot_qtde_sai,"
				+ "vl_tot_item_sai,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtEntradaSaida(stmt, entradaSaida);
			stmt.execute();
		}
		System.out.println("Movimentação " + entradaSaida.getIdPai() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}
	
	public void cadastrarTabEntSaiOut(EntradasSaidasDeProdutos entradaSaida) throws SQLException {
		String sql = "INSERT INTO tb_ent_sai_out(id_pai,id_cod_item,cnpj,descricao,ano,mes,cod_item,cod_ant_item,"
				+ "tot_qtde_ent,"
				+ "vl_tot_item_ent,"
				+ "tot_qtde_sai,"
				+ "vl_tot_item_sai,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtEntradaSaida(stmt, entradaSaida);
			stmt.execute();
		}
		System.out.println("Movimentação " + entradaSaida.getIdPai() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}
	
	public void cadastrarTabEntSaiNov(EntradasSaidasDeProdutos entradaSaida) throws SQLException {
		String sql = "INSERT INTO tb_ent_sai_nov(id_pai,id_cod_item,cnpj,descricao,ano,mes,cod_item,cod_ant_item,"
				+ "tot_qtde_ent,"
				+ "vl_tot_item_ent,"
				+ "tot_qtde_sai,"
				+ "vl_tot_item_sai,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtEntradaSaida(stmt, entradaSaida);
			stmt.execute();
		}
		System.out.println("Movimentação " + entradaSaida.getIdPai() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}
	
	
	public void cadastrarTabEntSaiDez(EntradasSaidasDeProdutos entradaSaida) throws SQLException {
		String sql = "INSERT INTO tb_ent_sai_dez(id_pai,id_cod_item,cnpj,descricao,ano,mes,cod_item,cod_ant_item,"
				+ "tot_qtde_ent,"
				+ "vl_tot_item_ent,"
				+ "tot_qtde_sai,"
				+ "vl_tot_item_sai,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
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
		retorno.setId(rs.getLong("id"));
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
