package versaogama.dao.estabelecimentodao.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.produto.OutrasUnid;

public class OutrasUnidDao implements OutrasUnidDaoInterface{
	
	private Pool pool;
	
	public OutrasUnidDao(Pool pool) {
		this.pool = pool;
	}

	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_out_und_medida WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( " Outras Unidades excluída  com sucesso!!");
		pool.liberarConnection(con);
		
	}

	@Override
	public void salvar(OutrasUnid outUnd) throws SQLException {
   
		 if(outUnd.getId() != null && outUnd.getId() > 0){
			 atualizar(outUnd);
		 }else {
			 cadastrar(outUnd);
		 }
	}

	private void stmtOutUnd(OutrasUnid outUnd, PreparedStatement stmt) throws SQLException {
		stmt.setLong(1, outUnd.getIdPaiEmp());
		stmt.setLong(2, outUnd.getIdPaiEst());
		stmt.setLong(3,outUnd.getIdPai());
		stmt.setString(4, outUnd.getUndMed());
		stmt.setDouble(5, outUnd.getUndEquivPadrao());
		stmt.setString(6, outUnd.getCodBarras());
		if(outUnd.getId()!= null && outUnd.getId() > 0) {
			stmt.setLong(7, outUnd.getId());
		}else {
			stmt.setLong(7, 0);
		}
	}

	@Override
	public void cadastrar(OutrasUnid outUnd) throws SQLException {
		String sql = "INSERT INTO tb_out_und_medida("
				+ "id_pai_emp,"
				+ "id_pai_est,"
				+ "id_pai,"
				+ "und_medida,"
				+ "und_equiv_padrao,"
				+ "cod_barras,id) VALUES (?,?,?,?,?,?,?);";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
     
			stmtOutUnd(outUnd,stmt);
			stmt.execute();
		}
		System.out.println("Outras Unidades " + outUnd.getUndMed() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
		
	}
	@Override
	public void atualizar(OutrasUnid outUnd) throws SQLException {
		String sql = "UPDATE tb_out_und_medida SET id_pai_emp=?,id_pai_est=?,id_pai=?,und_medida=?,und_equiv_padrao=?,cod_barras=?"
				+ "WHERE id=?";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmtOutUnd(outUnd,stmt);
			stmt.execute();
		}
		System.out.println("Produto " + outUnd.getUndMed() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
	}
	

	@Override
	public OutrasUnid getOutUnid(Integer codigo) throws SQLException {
		OutrasUnid outUnd = new OutrasUnid();
		String sql = "SELECT * FROM tb_out_und_medida WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){		
				
				while(rs.next()) {	
					
					outUnd = rsOutUnd(rs);
					
				}
				
			}
		}
		pool.liberarConnection(con);
		return outUnd;
	}

	private OutrasUnid rsOutUnd(ResultSet rs) throws SQLException {
		OutrasUnid outUnd = new OutrasUnid();		
		outUnd.setId(rs.getLong("id"));
		outUnd.setIdPaiEmp(rs.getLong("id_pai_emp"));
		outUnd.setIdPaiEst(rs.getLong("id_pai_est"));
		outUnd.setIdPai(rs.getLong("id_pai"));
		outUnd.setUndMed(rs.getString("und_medida"));
		outUnd.setUndEquivPadrao(rs.getDouble("und_equiv_padrao"));
		outUnd.setCodBarras(rs.getString("cod_barras"));

		return outUnd;
	}

	@Override
	public List<OutrasUnid> getOutrasUnidsMed() throws SQLException {
		   List<OutrasUnid> retorno = new ArrayList<OutrasUnid>();
		    String sql = "SELECT * FROM tb_out_und_medida";
			Connection con = pool.getConnection();
			try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){				
				stmt.execute();
				try(ResultSet rs = stmt.getResultSet()){		
					
					while(rs.next()) {	
						OutrasUnid outUnd = new OutrasUnid();		
						outUnd = rsOutUnd(rs);
						retorno.add(outUnd);						
					}					
				}
			} 
		pool.liberarConnection(con);		
		return retorno;
	}
}
