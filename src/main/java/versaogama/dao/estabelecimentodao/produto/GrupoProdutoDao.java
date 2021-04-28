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
import versaogama.model.system.produto.GrupoProduto;

public class GrupoProdutoDao implements GrupoProdutoDaoInterface{

	private Pool pool;
	
	public GrupoProdutoDao(Pool pool) {
		this.pool = pool;
	}
	
	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_grupo_produto WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( "Grupo produto excluído  com sucesso!!");
		pool.liberarConnection(con);
		
	}

	@Override
	public void salvar(GrupoProduto grpProd) throws SQLException {
		if(grpProd.getId() != null && grpProd.getId() > 0) {
			atualizar(grpProd);
		}else {
			cadastrar(grpProd);
		}
		
	}

	@Override
	public Long cadastrar(GrupoProduto grpProd) throws SQLException {
		Long id = 0L;
		String sql = "INSERT INTO tb_grupo_produto(id_pai_emp,id_pai_est,valor,descricao,id) VALUE (?,?,?,?,?)";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtGrupoProduto(grpProd, stmt);
			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {
				
				id = rs.getLong(1);
			}
		}
		System.out.println("Grupo Produto " + grpProd.getDescricao() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
		return id;
	}

	@Override
	public void atualizar(GrupoProduto grpProd) throws SQLException {
		String sql = "UPDATE tb_grupo_produto SET(id_pai_emp=?,id_pai_est=?,valor=?,descricao=? WHERE id=?)";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtGrupoProduto(grpProd, stmt);
			stmt.execute();
		}
		
		System.out.println("Grupo Produto " + grpProd.getDescricao() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public GrupoProduto getGrupoProd(Integer codigo) throws SQLException {
		GrupoProduto grp = new GrupoProduto();
		String sql = "SELECT * FROM tb_grupo_produto WHERE id= ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setLong(1, codigo);
			stmt.execute();			
			try(ResultSet rs = stmt.getResultSet()){		
				while(rs.next()) {	
					grp =  rsGrupoProduto(rs);
				}	
			}	
		}
		return grp;
	}
	
	public GrupoProduto getGrupoProd(String valor) throws SQLException {
		GrupoProduto grp = new GrupoProduto();
		String sql = "SELECT * FROM tb_grupo_produto WHERE valor= ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setString(1, valor);
			stmt.execute();			
			try(ResultSet rs = stmt.getResultSet()){		
				while(rs.next()) {	
					grp =  rsGrupoProduto(rs);
				}	
			}	
		}
		return grp;
	}
	public Map<String,GrupoProduto> getGrupoProd() throws SQLException {
		Map<String,GrupoProduto> retorno = new HashMap<String,GrupoProduto>();
		String sql = "SELECT * FROM tb_grupo_produto";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql)){
			stmt.execute();			
			try(ResultSet rs = stmt.getResultSet()){		
				while(rs.next()) {	
					GrupoProduto grp =  rsGrupoProduto(rs);
					retorno.put(grp.getValor(), grp);
				}	
			}	
		}
		return retorno;
	}
	
	@Override
	public List<GrupoProduto> getGruposProdutos() throws SQLException {
		List<GrupoProduto> retorno = new ArrayList<GrupoProduto>();
		String sql = "SELECT * FROM tb_grupo_produto";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.execute();			
			try(ResultSet rs = stmt.getResultSet()){		
				while(rs.next()) {	
					GrupoProduto grp = rsGrupoProduto(rs);
					retorno.add(grp);
				}	
			}	
		}
		return retorno;
	}
	
    private void stmtGrupoProduto(GrupoProduto grp, PreparedStatement stmt) throws SQLException {
    	
    	stmt.setLong(1, grp.getIdPaiEmp());
    	stmt.setLong(2, grp.getIdPaiEst());
    	stmt.setString(3, grp.getValor());
    	stmt.setString(4, grp.getDescricao());
    	if(grp.getId() != null && grp.getId() > 0){
    		stmt.setLong(5, grp.getId());
    	}else {
    		stmt.setLong(5, 0);
    	}
    }
    
    private GrupoProduto rsGrupoProduto(ResultSet rs) throws SQLException {
    	GrupoProduto grp = new GrupoProduto();
    	grp.setId(rs.getLong("id"));
    	grp.setIdPaiEmp(rs.getLong("id_pai_emp"));
    	grp.setIdPaiEst(rs.getLong("id_pai_est"));
    	grp.setValor(rs.getString("valor"));
    	grp.setDescricao(rs.getString("descricao"));
    	
    	return grp;
    }
	
}
