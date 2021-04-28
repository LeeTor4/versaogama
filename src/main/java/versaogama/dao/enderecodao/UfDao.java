package versaogama.dao.enderecodao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.estabelecimento.Uf;

public class UfDao implements UfDaoInterface{

	private final Pool pool;
	
	public UfDao(Pool pool) {
		this.pool = pool;
	}
	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_uf WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
			
		}
		pool.liberarConnection(con);
	}

	@Override
	public void cadastrar(Uf uf) throws SQLException {
		String sql = "INSERT INTO tb_uf (nome,sigla,id) VALUES (?,?,?)";
	
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtUf(uf, stmt);
			stmt.execute();
		}
		
		pool.liberarConnection(con);
	}

	private void stmtUf(Uf uf, PreparedStatement stmt) throws SQLException {
		
		stmt.setString(1, uf.getNome());
		stmt.setString(2, uf.getSigla());
		if(uf.getId() != null && uf.getId() > 0) {
			stmt.setLong(3, uf.getId());
		}else {
			stmt.setLong(3, 0);
		}
	}
	@Override
	public void salvar(Uf uf) throws SQLException {
		
		if(uf.getId()!= null && uf.getId() > 0){
			atualizar(uf);
		}else {
			cadastrar(uf);
		}
		
	}

	@Override
	public void atualizar(Uf uf) throws SQLException {
		String sql = "UPDATE tb_uf SET nome=?, sigla=? WHERE id = ?";
		 Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtUf(uf, stmt);
			stmt.execute();
		}
		
		pool.liberarConnection(con);
	}

	@Override
	public Uf getUf(Integer codigo) throws SQLException {
		Uf uf = new Uf();
		String sql = "SELECT * FROM Uf WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){				
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){					
				while(rs.next()) {					
					uf = rsUf(rs);
				}
			}			
		}
		pool.liberarConnection(con);
		return uf;
	}

	private Uf rsUf(ResultSet rs) throws SQLException {
		Uf uf = new Uf();
		uf.setId(rs.getLong("id"));
		uf.setNome(rs.getString("nome"));
		uf.setSigla(rs.getString("sigla"));
		return uf;
	}
	@Override
	public List<Uf> getUfs() throws SQLException {
		List<Uf> retorno = new ArrayList<>();
		
		String sql = "SELECT * FROM tb_uf";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){				
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){					
				while(rs.next()) {					
				  Uf uf = rsUf(rs);
				  retorno.add(uf);
				}
			}			
		}
		pool.liberarConnection(con);
		
		return retorno;
	}
	
	 

}
