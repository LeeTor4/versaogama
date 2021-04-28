package versaogama.dao.estabelecimentodao.equipcfe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.cfe.ResumoDiarioCFe;

public class ResumoDiarioCFeDao implements ResumoDiarioCFeDaoInterface{
	
	private Pool pool;
	
	public ResumoDiarioCFeDao(Pool pool) {
		this.pool = pool;
	}

	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE * FROM tb_totalizador_cf WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( "Resumo Diario CFe excluída  com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public void salvar(ResumoDiarioCFe res) throws SQLException {
		
		if (res.getId() != null && res.getId() > 0) {
		   atualizar(res);	
		}else {
			cadastrar(res);
		}
		
	}

	@Override
	public void cadastrar(ResumoDiarioCFe res) throws SQLException {
		String sql = "INSERT INTO tb_totalizador_cf (id_pai_emp, id_pai_est,idPai,cst,cfop,vl_bc_icms,aliq_icms,vl_icms,vloper,obs,id) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtResumoDiarioCfe(stmt, res);
			stmt.execute();
		}
		System.out.println("Resumo Sat " + res.getIdPai() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public void atualizar(ResumoDiarioCFe res) throws SQLException {
		String sql = "UPDATE tb_totalizador_cf SET id_pai_emp=?, id_pai_est=?,idPai=?,cst=?,cfop=?,vl_bc_icms=?,aliq_icms=?,vl_icms=?,vloper=?,obs=? WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtResumoDiarioCfe(stmt, res);
			stmt.execute();
		}
		System.out.println("Resumo Sat " + res.getIdPai() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public ResumoDiarioCFe getResumoDiario(Integer res) throws SQLException {
		ResumoDiarioCFe resumo = new ResumoDiarioCFe();
		String sql = "SELECT * FROM tb_totalizador_cf WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				
				while(rs.next()) {	
					
					resumo = rsResumoDiarioCFe(rs);
				}
			}
			
		}
		pool.liberarConnection(con);
		return resumo;
	}

	@Override
	public List<ResumoDiarioCFe> getResunosDiarios() throws SQLException {
		List<ResumoDiarioCFe> retorno = new ArrayList<ResumoDiarioCFe>();
		String sql = "SELECT * FROM tb_totalizador_cf";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {	
					
					ResumoDiarioCFe resumo = rsResumoDiarioCFe(rs);
					retorno.add(resumo);
				}
			}
			
		}
		pool.liberarConnection(con);
		return retorno;
	}
	
	public void stmtResumoDiarioCfe(PreparedStatement stmt,ResumoDiarioCFe res ) throws SQLException {
		
		stmt.setLong(1,res.getIdPaiEmp());
		stmt.setLong(2, res.getIdPaiEst());
		stmt.setLong(3,res.getIdPai());
		stmt.setString(4, res.getCstIcms());
		stmt.setString(5, res.getCfop());
		stmt.setDouble(6, res.getBcIcms());
		stmt.setDouble(7, res.getAliqIcms());
		stmt.setDouble(8, res.getVlIcms());
		stmt.setDouble(9, res.getVlOper());
		stmt.setString(10, res.getObs());
		if(res.getId() != null && res.getId() > 0) {
			stmt.setLong(11, res.getId());
		}else {
			stmt.setLong(11, 0);
		}
		
	}

	public ResumoDiarioCFe rsResumoDiarioCFe(ResultSet rs) throws SQLException {
		
		ResumoDiarioCFe resCFe = new ResumoDiarioCFe();
		
		resCFe.setId(rs.getLong("id"));
		resCFe.setIdPaiEmp(rs.getLong("id_pai_emp"));
		resCFe.setIdPaiEst(rs.getLong("id_pai_est"));
		resCFe.setIdPai(rs.getLong("idPai"));
		resCFe.setCstIcms(rs.getString("cst"));
		resCFe.setCfop(rs.getString("cfop"));
		resCFe.setVlIcms(rs.getDouble("vl_bc_icms"));
		resCFe.setAliqIcms(rs.getDouble("aliq_icms"));
		resCFe.setVlIcms(rs.getDouble("vl_icms"));
		resCFe.setVlOper(rs.getDouble("vl_oper"));
		resCFe.setObs(rs.getString("obs"));
		
		return resCFe;
	}
}
