package versaogama.dao.estabelecimentodao.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import versaogama.conexao.Pool;
import versaogama.model.system.produto.Produto;

public class ProdutoDao implements ProdutoDaoInterface{

	private final Pool pool;
	
	public ProdutoDao(Pool pool) {
		this.pool = pool;
	}
	
	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_produto WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( " Estabelecimento excluído  com sucesso!!");
		pool.liberarConnection(con);
		
	}

	@Override
	public void salvar(Produto prod) throws SQLException {

		if(prod.getId() != null && prod.getId() > 0) {
			atualizar(prod);
			
		}else {
			cadastrar(prod);
		}	

		
	}

	private void stmtProduto(Produto prod, PreparedStatement stmt) throws SQLException {
	  
		stmt.setLong(1, prod.getIdPaiEmp());
		stmt.setLong(2, prod.getIdPaiEst());
		stmt.setLong(3, 0); //TODO retornar a esse assunto do grupo dos produtos
		stmt.setString(4, prod.getTipoItem());
		stmt.setString(5, prod.getCodUtilizEmp());
		stmt.setString(6, prod.getDescricao());
		stmt.setString(7, prod.getNcm());
		stmt.setString(8, prod.getUndPadrao());
		stmt.setString(9, prod.getCodBarras());
		stmt.setString(10, prod.getCest());
		stmt.setString(11, (prod.getProdEspec()));
		stmt.setString(12, (prod.getTipoMed()));
		if(prod.getId() != null && prod.getId() > 0) {
			stmt.setLong(13, prod.getId());
		}else {
			stmt.setLong(13, 0);
		}
	}

	@Override
	public void cadastrar(Produto prod) throws SQLException {
		String sql = "INSERT INTO tb_produto("
				+ "id_pai_emp,"
				+ "id_pai_est,"
				+ "id_grp_prod,"
				+ "tipo_item,"
				+ "cod_utiliz_emp,"
				+ "descricao,"
				+ "ncm,"
				+ "und_padrao,"
				+ "cod_barras,"
				+ "cest,"
				+ "prod_espec,"
				+ "tipo_medicamento,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtProduto(prod, stmt);
			stmt.execute();
			
		}
		System.out.println("Produto " + prod.getDescricao() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public void atualizar(Produto prod) throws SQLException {
		String sql = "UPDATE tb_produto SET id_pai_emp=?,id_pai_est=?, tipo_item=?,id_grp_prod=?,cod_utiliz_emp=?,descricao=?,ncm=?,und_padrao=?,cest=?,prod_espec=?,tipo_medicamento=?"
				+ "WHERE id= ?";

		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
		    stmtProduto(prod, stmt);
			stmt.execute();
		}
		
		System.out.println("Produto " + prod.getDescricao() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public Produto getProduto(Integer codigo) throws SQLException {
		Produto prod = new Produto();
		String sql = "SELECT * FROM tb_produto WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setLong(1, codigo);
			stmt.execute();			
			try(ResultSet rs = stmt.getResultSet()){		
				while(rs.next()) {	
					prod =  rsProd(rs);
				}	
			}	
		}
		pool.liberarConnection(con);
		return prod;
	}

	@Override
	public List<Produto> getProdutos() throws SQLException {
		List<Produto> retorno = new ArrayList<>();
		String sql = "SELECT * FROM tb_produto";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){			
				
				while(rs.next()) {
					
					Produto prod = rsProd(rs);
                    retorno.add(prod);
				}
			}
		}
		pool.liberarConnection(con);
		return retorno;
	}

	public Map<String,Produto> getMpProdutos(Long id_pai_emp,Long id_pai_est) throws SQLException {
		 Map<String,Produto> retorno = new HashMap<String, Produto>();
		String sql = "SELECT * FROM tb_produto where id_pai_emp=? and id_pai_est=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setLong(1, id_pai_emp);
			stmt.setLong(2, id_pai_est);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){			
				
				while(rs.next()) {
					
					Produto prod = rsProd(rs);
					retorno.put(prod.getCodUtilizEmp(), prod);
				}
			}
		}
		pool.liberarConnection(con);
		return retorno;
	}
	private Produto rsProd(ResultSet rs) throws SQLException {
		Produto prod = new Produto();
		
		prod.setId(rs.getLong("id"));
		prod.setIdPaiEmp(rs.getLong("id_pai_emp"));
		prod.setIdPaiEst(rs.getLong("id_pai_est"));
		prod.setIdGrp(rs.getLong("id_grp_prod"));
		prod.setTipoItem(rs.getString("tipo_item"));
		prod.setCodUtilizEmp(rs.getString("cod_utiliz_emp"));
		prod.setDescricao(rs.getString("descricao"));
		prod.setNcm(rs.getString("ncm"));
		prod.setUndPadrao(rs.getString("und_padrao"));
		prod.setCodBarras(rs.getString("cod_barras"));
		prod.setCest(rs.getString("cest"));
		prod.setProdEspec(rs.getString("prod_espec"));
		prod.setTipoMed(rs.getString("tipo_medicamento"));

		return prod;
	}

	public Long getIncProd() {
		Long idRetorno = null;
		Pool pool = new Pool();
		String sql = "SELECT AUTO_INCREMENT AS ID FROM information_schema.tables WHERE table_name = 'tb_produto' AND table_schema = 'versaogamadb' ;";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){					
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){		
				while(rs.next()) {	
					idRetorno =  rs.getLong("ID");			
				}				
			}			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		pool.liberarConnection(con);
		return idRetorno;
	}

}
