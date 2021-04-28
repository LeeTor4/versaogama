package versaogama.dao.estabelecimentodao.equipecf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.equipecf.TotParciaisRDZ;

public class TotParciaisRDZDao implements TotParciaisRDZDaoInterface{
	
	private Pool pool;
	
	public TotParciaisRDZDao(Pool pool) {
		this.pool = pool;
	}

	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_tot_parc_rdz WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( " Tot Parc Rdz excluído  com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public Long salvar(TotParciaisRDZ totparRdz) throws SQLException {
		if(totparRdz.getId() != null && totparRdz.getId() > 0) {
			atualizar(totparRdz);
		}else {
			cadastrar(totparRdz);
		}
		return 0L;
	}

	@Override
	public Long cadastrar(TotParciaisRDZ totparRdz) throws SQLException {
		Long id = 0L;
		String sql = "INSERT INTO tb_tot_parc_rdz (id_pai_emp,id_pai_est,idPai,cod_tot,vl_acum_totrdz,num_tot,descr_num_tot,id) VALUES(?,?,?,?,?,?,?,?)";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmtTotParcRdz(totparRdz, stmt);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {
				
				id = rs.getLong(1);
			}
		}
		
		System.out.println("Totalizador " + totparRdz.getNumTotalizador() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
		return id;
	}

	@Override
	public void atualizar(TotParciaisRDZ totparRdz) throws SQLException {
		String sql = "UPDATE tb_tot_parc_rdz  SET id_pai_emp=?,id_pai_est=?,idPai=?,cod_tot=?,vl_acum_totrdz=?,num_tot=?,descr_num_tot=? WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtTotParcRdz(totparRdz, stmt);
			stmt.execute();
		}
		System.out.println("Totalizador " + totparRdz.getNumTotalizador() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public TotParciaisRDZ getNotaFiscal(Integer codigo) throws SQLException {
		TotParciaisRDZ tot = new TotParciaisRDZ();
		String sql = "SELECT * FROM tb_tot_parc_rdz WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){
				while(rs.next()) {	
					tot = rsTotParciaisRDZ(rs);
				}
			}
		}
		pool.liberarConnection(con);		
		return tot;
	}

	@Override
	public List<TotParciaisRDZ> getNotasFiscais() throws SQLException {
		List<TotParciaisRDZ> retorno = new ArrayList<TotParciaisRDZ>();
		String sql = "SELECT * FROM tb_tot_parc_rdz";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){
				
				while(rs.next()) {	
					
					TotParciaisRDZ tot = rsTotParciaisRDZ(rs);
					retorno.add(tot);
				}
			}
		}
		
		pool.liberarConnection(con);		
		return retorno;
	}
	
	public void stmtTotParcRdz(TotParciaisRDZ tot, PreparedStatement stmt) throws SQLException {
		
		stmt.setLong(1, tot.getId_pai_emp());
		stmt.setLong(2, tot.getId_pai_est());
		stmt.setLong(3, tot.getIdPai());
		stmt.setString(4, tot.getCodTotalizador());
		stmt.setDouble(5, tot.getVlAcumuladoTotRedZ());
		stmt.setString(6, tot.getNumTotalizador());
		stmt.setString(7, tot.getDescNumTotalizador());
		if(tot.getId() != null && tot.getId() > 0){
			stmt.setLong(8, tot.getId());
		}else {
			stmt.setLong(8, 0);
		}
	}
	
	public TotParciaisRDZ rsTotParciaisRDZ(ResultSet rs) throws SQLException {
		TotParciaisRDZ tot = new TotParciaisRDZ();
		tot.setId(rs.getLong("id"));
		tot.setId_pai_emp(rs.getLong("id_pai_emp"));
		tot.setId_pai_est(rs.getLong("id_pai_est"));
		tot.setIdPai(rs.getLong("idPai"));
		tot.setCodTotalizador(rs.getString("cod_tot"));
		tot.setVlAcumuladoTotRedZ(rs.getDouble("vl_acum_totrdz"));
		tot.setNumTotalizador(rs.getString("num_tot"));
		tot.setDescNumTotalizador(rs.getString("descr_num_tot"));
		
		return tot;
	}
	
	public  Long getIncTotParcRDZ() {
		Long idRetorno = null;
		Pool pool = new Pool();
		String sql = "SELECT AUTO_INCREMENT AS ID FROM information_schema.tables WHERE table_name = 'tb_tot_parc_rdz' AND table_schema = 'versaogamadb' ;";
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
		return idRetorno;
	}

}
