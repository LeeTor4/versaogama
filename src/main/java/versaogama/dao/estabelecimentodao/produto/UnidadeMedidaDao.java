package versaogama.dao.estabelecimentodao.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.produto.UnidadeMedida;

public class UnidadeMedidaDao implements UnidadeMedidaDaoInterface{

	private Pool pool;
	
	public UnidadeMedidaDao(Pool pool) {
		this.pool = pool;
	}
	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_unidade_medida WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( "Unidade Medida excluída com sucesso!!");
		pool.liberarConnection(con);
		
	}

	@Override
	public void salvar(UnidadeMedida undMed) throws SQLException {
		if(undMed.getId() !=null && undMed.getId() > 0){
			atualizar(undMed);
		}else {
			cadastrar(undMed);
		}
		
	}

	@Override
	public void cadastrar(UnidadeMedida undMed) throws SQLException {
		String sql = "INSERT INTO tb_unidade_medida(id_pai_emp,id_pai_est,unidade,descricao,id) VALUES(?,?,?,?,?)";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtUnidadeMedida(undMed, stmt);
			stmt.execute();
		}
		System.out.println( "Unidade Medida " +undMed.getUnidade()+ " cadastrada com sucesso!!");
		pool.liberarConnection(con);
	}
	

	@Override
	public void atualizar(UnidadeMedida undMed) throws SQLException {
		String sql = "UPDATE tb_unidade_medida SET id_pai_emp=?,id_pai_est=?,unidade=?,descricao=? WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmtUnidadeMedida(undMed, stmt);
			stmt.execute();
		}
		System.out.println( "Unidade Medida " +undMed.getUnidade()+ " alterada com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public UnidadeMedida getUnidadeMedida(Integer codigo) throws SQLException {
		UnidadeMedida und = new UnidadeMedida();
		String sql = "SELECT * FROM tb_unidade_medida WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {						
					 und = rsUnidadeMedida(rs);
				}
			}
		}
		
		return und;
	}

	@Override
	public List<UnidadeMedida> getUnidadesMedidas() throws SQLException {
		List<UnidadeMedida> retorno = new ArrayList<UnidadeMedida>();
		String sql = "SELECT * FROM tb_unidade_medida";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {
					UnidadeMedida und = new UnidadeMedida();
					und = rsUnidadeMedida(rs);
					retorno.add(und);
				}
			}
		}
		return retorno;
	}

	public void stmtUnidadeMedida(UnidadeMedida und, PreparedStatement stmt) throws SQLException{
		
		stmt.setLong(1, und.getIdPaiEmp());
		stmt.setLong(2,und.getIdPaiEst());
		stmt.setString(3, und.getUnidade());
		stmt.setString(4, und.getDescricao());
		if(und.getId() != null && und.getId() > 0) {
			stmt.setLong(5, und.getId());
		}else {
			stmt.setLong(5, 0);
		}
	}
	
	public UnidadeMedida rsUnidadeMedida(ResultSet rs) throws SQLException {
		UnidadeMedida und = new UnidadeMedida();		
		und.setId(rs.getLong("id"));
		und.setIdPaiEmp(rs.getLong("id_pai_emp"));
		und.setIdPaiEst(rs.getLong("id_pai_est"));
		und.setUnidade(rs.getString("unidade"));
		und.setDescricao(rs.getString("descricao"));
		return und;
	}
}
