package versaogama.dao.estabelecimentodao.equipecf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.equipecf.TotalizadorDiarioCuponsFiscais;

public class TotalizadorDiarioCuponsFiscaisDao implements TotalizadorDiarioCuponsFiscaisDaoInterface{

	private Pool pool;
	
	public TotalizadorDiarioCuponsFiscaisDao(Pool pool) {
		this.pool = pool;
	}
	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_totalizador_cf WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( " Totalizador CF excluído  com sucesso!!");
		pool.liberarConnection(con);
		
	}

	@Override
	public Long salvar(TotalizadorDiarioCuponsFiscais totdircf) throws SQLException {
		if(totdircf.getId() != null && totdircf.getId() > 0) {
			atualizar(totdircf);
		}else {
			cadastrar(totdircf);
		}
		return 0L;
	}

	@Override
	public Long cadastrar(TotalizadorDiarioCuponsFiscais totdircf) throws SQLException {
		Long id = 0L;
		String sql = "INSERT INTO tb_totalizador_cf (id_pai_emp,id_pai_est,idPai,cst,cfop,vloper,id) VALUES (?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtTotDirCF(totdircf, stmt);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {
				
				id = rs.getLong(1);
			}
			
		}
		System.out.println("Totalizador CF " + totdircf.getVlOperacao() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
		return id;
	}

	@Override
	public void atualizar(TotalizadorDiarioCuponsFiscais totdircf) throws SQLException {
		String sql = "UPDATE tb_totalizador_cf SET id_pai_emp=?,id_pai_est=?,idPai=?,cst=?,cfop=?,vloper=? WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmtTotDirCF(totdircf, stmt);
			stmt.execute();
		}
		
		System.out.println("Nota Fiscal " + totdircf.getVlOperacao() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public TotalizadorDiarioCuponsFiscais getNotaFiscal(Integer codigo) throws SQLException {
		TotalizadorDiarioCuponsFiscais tot = new TotalizadorDiarioCuponsFiscais();
		String sql = "SELECT * FROM tb_totalizador_cf WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){		
				while(rs.next()) {				
					tot = rsTotDirCF(rs);
				}
			}
		}
		pool.liberarConnection(con);		
		return tot;
	}

	@Override
	public List<TotalizadorDiarioCuponsFiscais> getNotasFiscais() throws SQLException {
		List<TotalizadorDiarioCuponsFiscais> retorno = new ArrayList<TotalizadorDiarioCuponsFiscais>();
		String sql = "SELECT * FROM tb_totalizador_cf";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){		
				while(rs.next()) {				
					TotalizadorDiarioCuponsFiscais tot = rsTotDirCF(rs);
					retorno.add(tot);
				}
			}	
		}
		pool.liberarConnection(con);		
		return retorno;
	}

	public void stmtTotDirCF(TotalizadorDiarioCuponsFiscais tot, PreparedStatement stmt) throws SQLException {
		
		stmt.setLong(1, tot.getId_pai_emp());
		stmt.setLong(2, tot.getId_pai_est());
		stmt.setLong(3, tot.getIdPai());
		stmt.setString(4, tot.getCst());
		stmt.setString(5, tot.getCfop());
		stmt.setDouble(6, tot.getVlOperacao());
		if(tot.getId() != null && tot.getId() > 0) {
			stmt.setLong(7, tot.getId());
		}else {
			stmt.setLong(7, 0);
		}
		
	}
	
	public TotalizadorDiarioCuponsFiscais rsTotDirCF(ResultSet rs) throws SQLException {
		TotalizadorDiarioCuponsFiscais tot = new TotalizadorDiarioCuponsFiscais();
		
		tot.setId(rs.getLong("id"));
		tot.setId_pai_emp(rs.getLong("id_pai_emp"));
		tot.setId_pai_est(rs.getLong("id_pai_est"));
		tot.setIdPai(rs.getLong("idPai"));
		tot.setCst(rs.getString("cst"));
		tot.setCfop(rs.getString("cfop"));
		tot.setVlOperacao(rs.getDouble("vloper"));
		return tot;
		
	}
	

}
