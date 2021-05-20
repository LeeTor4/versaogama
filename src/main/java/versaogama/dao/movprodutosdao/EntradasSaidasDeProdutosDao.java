package versaogama.dao.movprodutosdao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.movprodutos.EntradasSaidasDeProdutos;
import versaogama.model.system.movprodutos.ModelInventarioDeclarado;
import versaogama.model.system.movprodutos.ModelSaldoInicial;
import versaogama.util.UtilsEConverters;

public class EntradasSaidasDeProdutosDao {

	private final Pool pool;
	
	public EntradasSaidasDeProdutosDao(Pool pool) {
		this.pool = pool;
	}
	
	public List<EntradasSaidasDeProdutos> retornaCadastroMovProdutosPorAno(String ano) throws SQLException {
		
		List<EntradasSaidasDeProdutos> retorno = new ArrayList<EntradasSaidasDeProdutos>();
	   
		String sql = "SELECT * FROM mov_prod_anual WHERE ano = ?"; 
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setString(1, ano);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				
				while(rs.next()) {		
					
					EntradasSaidasDeProdutos entsai = rsCadastroMovProdutosAnual(rs);
					retorno.add(entsai);
				}
				
			}
			
		}
		pool.liberarConnection(con);
		return retorno;
	}
	
	public List<EntradasSaidasDeProdutos> retornaCadastroMovProdutosPorId(Long id) throws SQLException {
		
		List<EntradasSaidasDeProdutos> retorno = new ArrayList<EntradasSaidasDeProdutos>();
	   
		String sql = "SELECT * FROM mov_prod_mensal where id = ?"; 
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setLong(1, id);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				
				while(rs.next()) {		
					
					EntradasSaidasDeProdutos entsai = rsCadastroMovProdutosMensal(rs);
					retorno.add(entsai);
				}
				
			}
			
		}
		pool.liberarConnection(con);
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
    
    public List<ModelInventarioDeclarado> getInventarioDeclarado(String codItem,String codAntItem, String cnpj, String ano) throws SQLException{
    	
    	List<ModelInventarioDeclarado> retorno = new ArrayList<ModelInventarioDeclarado>();
    	
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
					ModelInventarioDeclarado inv = new ModelInventarioDeclarado();
					inv.setCnpj(rs.getString("cnpj"));
					inv.setAno(rs.getString("ano"));
					inv.setCodItem(rs.getString("cod_item"));
					inv.setCodItemAnt(rs.getString("cod_ant_item"));
					inv.setQtde(rs.getDouble("qtde"));
					inv.setVlUnit(rs.getDouble("vl_unit"));
					inv.setVlItem(rs.getDouble("vl_item"));
				    inv.setDescricao(rs.getString("descricao"));
				    inv.setDtInv(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_inv")));
					
					retorno.add(inv);
				}
			}
			
		}
		pool.liberarConnection(con);
    	return retorno;
    }
	
	public ModelSaldoInicial getSaldoInicialEnt(String codItem, String codAntItem,String ano,String cnpj) throws SQLException{
		
		ModelSaldoInicial obj = new ModelSaldoInicial();
		String sql = "SELECT cnpj,ano,cod_item,sum(tot_qtde) as saldo FROM tb_totalizadorporitem_ent where cod_item in (?,?) and ano = ? and cnpj = ? group by ano;";
		Connection con = pool.getConnection();
    	if(!con.isClosed()) {    		
    		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
    			stmt.setString(1, codItem);
    			stmt.setString(2, codAntItem);
    			stmt.setString(3, ano);
    			stmt.setString(4, cnpj);
    			stmt.execute();
    			ResultSet rs = stmt.executeQuery();
    		 	while(rs.next()) {    		 		
    		         
    		 		obj.setAno(rs.getString("ano"));
    		 		obj.setCodItem(rs.getString("cod_item"));
    		 		obj.setSaldo(rs.getDouble("saldo"));

    		 	}
    		}
    		pool.liberarConnection(con);
    	}else {
			con.close();
		}
				
		return obj;
	}
	
	public ModelSaldoInicial getSaldoInicialSai(String codItem, String codAntItem,String ano,String cnpj) throws SQLException{
		
		ModelSaldoInicial obj = new ModelSaldoInicial();
		String sql = "SELECT cnpj,ano,cod_item,sum(tot_qtde) as saldo FROM tb_totalizadorporitem_sai where cod_item in (?,?) and ano = ? and cnpj = ? group by ano;";
		Connection con = pool.getConnection();
    	if(!con.isClosed()) {    		
    		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
    			stmt.setString(1, codItem);
    			stmt.setString(2, codAntItem);
    			stmt.setString(3, ano);
    			stmt.setString(4, cnpj);
    			stmt.execute();
    			ResultSet rs = stmt.executeQuery();
    		 	while(rs.next()) {    		 		
    		         
    		 		obj.setAno(rs.getString("ano"));
    		 		obj.setCodItem(rs.getString("cod_item"));
    		 		obj.setSaldo(rs.getDouble("saldo"));

    		 	}
    		}
    		pool.liberarConnection(con);
    	}else {
			con.close();
		}
				
		return obj;
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
	
	
	private EntradasSaidasDeProdutos rsCadastroMovProdutosMensal(ResultSet rs) throws SQLException {
	
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
	
	private EntradasSaidasDeProdutos rsCadastroMovProdutosAnual(ResultSet rs) throws SQLException {
		
		EntradasSaidasDeProdutos retorno = new EntradasSaidasDeProdutos();
		retorno.setId(rs.getLong("id"));
		retorno.setIdCodItem(rs.getLong("id_cod_item"));
		retorno.setCnpj(rs.getString("cnpj"));
		retorno.setDescricao(rs.getString("descricao"));
		retorno.setUnd(rs.getString("und"));
		retorno.setAno(rs.getString("ano"));
		//retorno.setMes(rs.getString("mes"));
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
	

	  
    public List<EntradasSaidasDeProdutos> getSaldoItensJan(String cod_item,String cod_ant_item,String ano) throws SQLException{
    	List<EntradasSaidasDeProdutos> retorno = new ArrayList<>();
    	
    	String sql = "{call sp_ent_sai_jan(?,?,?)}";
    	
    	Connection con = pool.getConnection();
    	try(CallableStatement stmt =  con.prepareCall(sql)){	
    		stmt.setString(1, cod_item);
    		stmt.setString(2, cod_ant_item);
    		stmt.setString(3, ano);
    		ResultSet rs = stmt.executeQuery();

			resultsetSaldo(retorno, rs);
	
    	}
    	pool.liberarConnection(con);
    	return retorno;
    }
    
    public List<EntradasSaidasDeProdutos> getSaldoItensFev(String cod_item,String cod_ant_item,String ano) throws SQLException{
    	List<EntradasSaidasDeProdutos> retorno = new ArrayList<>();
    	
    	String sql = "{call sp_ent_sai_fev(?,?,?)}";
    	
    	Connection con = pool.getConnection();
    	try(CallableStatement stmt =  con.prepareCall(sql)){	
    		stmt.setString(1, cod_item);
    		stmt.setString(2, cod_ant_item);
    		stmt.setString(3, ano);
    		ResultSet rs = stmt.executeQuery();

			resultsetSaldo(retorno, rs);
	
    	}
    	pool.liberarConnection(con);
    	return retorno;
    }
    
    public List<EntradasSaidasDeProdutos> getSaldoItensMar(String cod_item,String cod_ant_item,String ano) throws SQLException{
    	List<EntradasSaidasDeProdutos> retorno = new ArrayList<>();
    	
    	String sql = "{call sp_ent_sai_mar(?,?,?)}";
    	
    	Connection con = pool.getConnection();
    	try(CallableStatement stmt =  con.prepareCall(sql)){	
    		stmt.setString(1, cod_item);
    		stmt.setString(2, cod_ant_item);
    		stmt.setString(3, ano);
    		ResultSet rs = stmt.executeQuery();

			resultsetSaldo(retorno, rs);
	
    	}
    	pool.liberarConnection(con);
    	return retorno;
    }
    
    public List<EntradasSaidasDeProdutos> getSaldoItensAbr(String cod_item,String cod_ant_item,String ano) throws SQLException{
    	List<EntradasSaidasDeProdutos> retorno = new ArrayList<>();
    	
    	String sql = "{call sp_ent_sai_abr(?,?,?)}";
    	
    	Connection con = pool.getConnection();
    	try(CallableStatement stmt =  con.prepareCall(sql)){	
    		stmt.setString(1, cod_item);
    		stmt.setString(2, cod_ant_item);
    		stmt.setString(3, ano);
    		ResultSet rs = stmt.executeQuery();

			resultsetSaldo(retorno, rs);
	
    	}
    	pool.liberarConnection(con);
    	return retorno;
    }
    
    public List<EntradasSaidasDeProdutos> getSaldoItensMai(String cod_item,String cod_ant_item,String ano) throws SQLException{
    	List<EntradasSaidasDeProdutos> retorno = new ArrayList<>();
    	
    	String sql = "{call sp_ent_sai_mai(?,?,?)}";
    	
    	Connection con = pool.getConnection();
    	try(CallableStatement stmt =  con.prepareCall(sql)){	
    		stmt.setString(1, cod_item);
    		stmt.setString(2, cod_ant_item);
    		stmt.setString(3, ano);
    		ResultSet rs = stmt.executeQuery();

			resultsetSaldo(retorno, rs);
	
    	}
    	pool.liberarConnection(con);
    	return retorno;
    }
    
    public List<EntradasSaidasDeProdutos> getSaldoItensJun(String cod_item,String cod_ant_item,String ano) throws SQLException{
    	List<EntradasSaidasDeProdutos> retorno = new ArrayList<>();
    	
    	String sql = "{call sp_ent_sai_jun(?,?,?)}";
    	
    	Connection con = pool.getConnection();
    	try(CallableStatement stmt =  con.prepareCall(sql)){	
    		stmt.setString(1, cod_item);
    		stmt.setString(2, cod_ant_item);
    		stmt.setString(3, ano);
    		ResultSet rs = stmt.executeQuery();

			resultsetSaldo(retorno, rs);
	
    	}
    	pool.liberarConnection(con);
    	return retorno;
    }
    
    public List<EntradasSaidasDeProdutos> getSaldoItensJul(String cod_item,String cod_ant_item,String ano) throws SQLException{
    	List<EntradasSaidasDeProdutos> retorno = new ArrayList<>();
    	
    	String sql = "{call sp_ent_sai_jul(?,?,?)}";
    	
    	Connection con = pool.getConnection();
    	try(CallableStatement stmt =  con.prepareCall(sql)){	
    		stmt.setString(1, cod_item);
    		stmt.setString(2, cod_ant_item);
    		stmt.setString(3, ano);
    		ResultSet rs = stmt.executeQuery();

			resultsetSaldo(retorno, rs);
	
    	}
    	pool.liberarConnection(con);
    	return retorno;
    }
    
    public List<EntradasSaidasDeProdutos> getSaldoItensAgo(String cod_item,String cod_ant_item,String ano) throws SQLException{
    	List<EntradasSaidasDeProdutos> retorno = new ArrayList<>();
    	
    	String sql = "{call sp_ent_sai_ago(?,?,?)}";
    	
    	Connection con = pool.getConnection();
    	try(CallableStatement stmt =  con.prepareCall(sql)){	
    		stmt.setString(1, cod_item);
    		stmt.setString(2, cod_ant_item);
    		stmt.setString(3, ano);
    		ResultSet rs = stmt.executeQuery();

			resultsetSaldo(retorno, rs);
	
    	}
    	pool.liberarConnection(con);
    	return retorno;
    }
    
    public List<EntradasSaidasDeProdutos> getSaldoItensSet(String cod_item,String cod_ant_item,String ano) throws SQLException{
    	List<EntradasSaidasDeProdutos> retorno = new ArrayList<>();
    	
    	String sql = "{call sp_ent_sai_set(?,?,?)}";
    	
    	Connection con = pool.getConnection();
    	try(CallableStatement stmt =  con.prepareCall(sql)){	
    		stmt.setString(1, cod_item);
    		stmt.setString(2, cod_ant_item);
    		stmt.setString(3, ano);
    		ResultSet rs = stmt.executeQuery();

			resultsetSaldo(retorno, rs);
	
    	}
    	pool.liberarConnection(con);
    	return retorno;
    }
    
    public List<EntradasSaidasDeProdutos> getSaldoItensOut(String cod_item,String cod_ant_item,String ano) throws SQLException{
    	List<EntradasSaidasDeProdutos> retorno = new ArrayList<>();
    	
    	String sql = "{call sp_ent_sai_out(?,?,?)}";
    	
    	Connection con = pool.getConnection();
    	try(CallableStatement stmt =  con.prepareCall(sql)){	
    		stmt.setString(1, cod_item);
    		stmt.setString(2, cod_ant_item);
    		stmt.setString(3, ano);
    		ResultSet rs = stmt.executeQuery();

			resultsetSaldo(retorno, rs);
	
    	}
    	pool.liberarConnection(con);
    	return retorno;
    }
	
    public List<EntradasSaidasDeProdutos> getSaldoItensNov(String cod_item,String cod_ant_item,String ano) throws SQLException{
    	List<EntradasSaidasDeProdutos> retorno = new ArrayList<>();
    	
    	String sql = "{call sp_ent_sai_nov(?,?,?)}";
    	
    	Connection con = pool.getConnection();
    	try(CallableStatement stmt =  con.prepareCall(sql)){	
    		stmt.setString(1, cod_item);
    		stmt.setString(2, cod_ant_item);
    		stmt.setString(3, ano);
    		ResultSet rs = stmt.executeQuery();

			resultsetSaldo(retorno, rs);
	
    	}
    	pool.liberarConnection(con);
    	return retorno;
    }
	
    public List<EntradasSaidasDeProdutos> getSaldoItensDez(String cod_item,String cod_ant_item,String ano) throws SQLException{
    	List<EntradasSaidasDeProdutos> retorno = new ArrayList<>();
    	
    	String sql = "{call sp_ent_sai_dez(?,?,?)}";
    	
    	Connection con = pool.getConnection();
    	try(CallableStatement stmt =  con.prepareCall(sql)){	
    		stmt.setString(1, cod_item);
    		stmt.setString(2, cod_ant_item);
    		stmt.setString(3, ano);
    		ResultSet rs = stmt.executeQuery();

			resultsetSaldo(retorno, rs);
	
    	}
    	pool.liberarConnection(con);
    	return retorno;
    }

    
    public List<EntradasSaidasDeProdutos> getSaldoAnualV3(String codItem, String codAntItem,String ano, String cnpj) throws SQLException{
    	
    	 List<EntradasSaidasDeProdutos> retorno = new ArrayList<EntradasSaidasDeProdutos>();
    	
    	String sql = "SELECT * FROM v_saldo_anual WHERE cod_item in (?,?) AND ano = ? AND cnpj = ?";
    	
    	Connection con = pool.getConnection();
    	try(CallableStatement stmt =  con.prepareCall(sql)){	
    		stmt.setString(1, codItem);
    		stmt.setString(2, codAntItem);
    		stmt.setString(3, ano);
    		stmt.setString(4, cnpj);
			ResultSet rs = stmt.executeQuery();

			while(rs.next()) {	
				EntradasSaidasDeProdutos obj = new EntradasSaidasDeProdutos();
				
				obj.setIdCodItem(rs.getLong("id_cod_item"));
				obj.setOperacao(rs.getString("operacao"));
				obj.setCnpj(rs.getString("cnpj"));
				obj.setDescricao(rs.getString("descricao"));
				obj.setAno(rs.getString("ano"));
				obj.setCodItem(rs.getString("cod_item"));
				obj.setCodAntItem(rs.getString("codigo_ant_item"));
				obj.setTotQtdeEnt(rs.getDouble("tot_qtde"));
				obj.setTotVlItemEnt(rs.getDouble("vl_tot_item"));

               retorno.add(obj);
			}
    	}
    	//con.close();
    	pool.liberarConnection(con);
    	return retorno;
    }
	private void resultsetSaldo(List<EntradasSaidasDeProdutos> retorno, ResultSet rs) throws SQLException {
		while(rs.next()) {	
			
			EntradasSaidasDeProdutos saldo = new  EntradasSaidasDeProdutos();
			saldo.setId(rs.getLong("id"));
			saldo.setMes(rs.getString("mes"));
			saldo.setTotQtdeEnt(rs.getDouble("tot_qtde_ent"));
			saldo.setTotVlItemEnt(rs.getDouble("vl_tot_item_ent"));
			saldo.setTotQtdeSai(rs.getDouble("tot_qtde_sai"));
			saldo.setTotVlItemSai(rs.getDouble("vl_tot_item_sai"));
			retorno.add(saldo);
		}
	}

}
