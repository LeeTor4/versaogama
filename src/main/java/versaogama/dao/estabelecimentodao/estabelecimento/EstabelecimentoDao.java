package versaogama.dao.estabelecimentodao.estabelecimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.estabelecimento.Estabelecimento;

public class EstabelecimentoDao implements EstabelecimentoDaoInterface{

	private final Pool pool;
	
	public EstabelecimentoDao(Pool pool) {
	   this.pool = pool;
	  
	}
	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_estabelecimento WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( " Estabelecimento excluído  com sucesso!!");
		pool.liberarConnection(con);
		
	}

	@Override
	public void salvar(Estabelecimento est) throws SQLException {
		if(est.getId() != null && est.getId() > 0) {
			atualizar(est);
		}else {
			cadastrar(est);
		}
		
	}

	@Override
	public void cadastrar(Estabelecimento est) throws SQLException {
		String sql = "INSERT INTO tb_estabelecimento(cnpj,ie,nome,nome_fantasia,id_pai_end,id_pai_emp,id)"
				+ "VALUES(?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtEstab(est, stmt);
			stmt.execute();
		}
		System.out.println("Estabelecimento " + est.getNome() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}

	private void stmtEstab(Estabelecimento est, PreparedStatement stmt) throws SQLException {
		stmt.setString(1, est.getCnpj());
		stmt.setString(2, est.getIe());
		stmt.setString(3, est.getNome());
		stmt.setString(4, est.getNmFantasia());
		stmt.setLong(5, (est.getIdPaiEnd()));
		stmt.setLong(6, est.getIdPai());
		if(est.getId() != null && est.getId() > 0){
			stmt.setLong(7, est.getId());
		}else {
			stmt.setLong(7, 0);
		}
	}
	@Override
	public void atualizar(Estabelecimento est) throws SQLException {
		String sql = "UPDATE tb_estabelecimento SET cnpj=?,ie=?,nome=?,nome_fantasia=?,id_pai_end=?,id_pai_emp=?"
				+"WHERE id = ?"; 
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtEstab(est, stmt);
			stmt.execute();
		}
		
		System.out.println("Estabelecimento " + est.getNome() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public Estabelecimento getEstabelecimento(Integer codigo) throws SQLException {
		Estabelecimento est = new Estabelecimento();
		String sql  = "SELECT * FROM tb_estabelecimento WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){					
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){					
				while(rs.next()) {		
					
					est = rsEst(rs);
				}	
			}
		}
		return est;
	}

	private Estabelecimento rsEst(ResultSet rs) throws SQLException {
		Estabelecimento est = new Estabelecimento();
		est.setId(rs.getLong("id"));
		est.setCnpj(rs.getString("cnpj"));
		est.setIe(rs.getString("ie"));
		est.setNome(rs.getString("nome"));
		est.setNmFantasia(rs.getString("nome_fantasia"));
		est.setIdPaiEnd(rs.getLong("ind_pai_end"));
		est.setIdPai(rs.getLong("ind_pai_emp"));
				
		return est;
	}
	
	@Override
	public List<Estabelecimento> getEstabelecimentos() throws SQLException {
		List<Estabelecimento> retorno = new ArrayList<>();
		String sql = "SELECT * FROM tb_estabelecimento";		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){				
				while(rs.next()) {		
					Estabelecimento est = rsEst(rs);
					retorno.add(est);
				}	
			}	
		}	
		pool.liberarConnection(con);
		return retorno;
	}



}
