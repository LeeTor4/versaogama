package versaogama.dao.estabelecimentodao.equipecf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.equipecf.ReducaoZ;
import versaogama.util.UtilsEConverters;

public class ReducaoZDao implements ReducaoZDaoInterface{
  
	private Pool pool;
	
	public ReducaoZDao(Pool pool) {
		this.pool = pool;
	}
	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_reducaoz WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( " Redução Z excluída  com sucesso!!");
		pool.liberarConnection(con);
		
		
	}

	@Override
	public Long salvar(ReducaoZ rdz) throws SQLException {
		if(rdz.getId() != null && rdz.getId() > 0){
			atualizar(rdz);
		}else {
			cadastrar(rdz);
		}
		return 0L;
	}

	@Override
	public Long cadastrar(ReducaoZ rdz) throws SQLException {
		Long id = 0L;
		String sql = "INSERT INTO tb_reducaoz (id_pai_emp,id_pai_est,id_ecf,idpai,dtReducaoZ,posicaoCRO,posicaoRDZ,numCOO,vl_grd_tot,vl_vend_brut,id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtReducaoZ(rdz, stmt);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {
				
				id = rs.getLong(1);
			}
			
		}
		
		System.out.println("Redução Z " + rdz.getPosicaoRDZ() + ", cadastrada com sucesso!!");
		pool.liberarConnection(con);
		return id;
	}

	@Override
	public void atualizar(ReducaoZ rdz) throws SQLException {
		String sql = "UPDATE tb_reducaoz SET id_pai_emp=?,id_pai_est=?,id_ecf=?,idpai=?,dtReducaoZ=?,posicaoCRO=?,posicaoRDZ=?,numCOO=?,vl_grd_tot=?,vl_vend_brut=? WHERE id=?";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtReducaoZ(rdz, stmt);
			stmt.execute();
		}
	
	
		System.out.println("Redução Z " + rdz.getPosicaoRDZ() + ", alterada com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public ReducaoZ getNotaFiscal(Integer codigo) throws SQLException {
		ReducaoZ rdz = new ReducaoZ();
		String sql = "SELECT * FROM tb_reducaoz WHERE id=?";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {							
					 rdz = rsReducao(rs);
				}				
			}
		}
		pool.liberarConnection(con);		
		return rdz;
	}

	@Override
	public List<ReducaoZ> getNotasFiscais() throws SQLException {
		List<ReducaoZ> retorno = new ArrayList<ReducaoZ>();
		String sql = "SELECT * FROM tb_reducaoz";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {							
					ReducaoZ  rdz = rsReducao(rs);
					retorno.add(rdz);
				}				
			}
		}
		
		pool.liberarConnection(con);		
		return retorno;
	}
	
	public void stmtReducaoZ(ReducaoZ rdz,PreparedStatement stmt) throws SQLException {
		stmt.setLong(1, rdz.getId_pai_emp());
		stmt.setLong(2, rdz.getId_pai_est());
		stmt.setLong(3, rdz.getId_ecf());
		stmt.setLong(4,rdz.getIdPai());
		stmt.setDate(5, UtilsEConverters.getLocalDateParaDateSQL(rdz.getDtReducaoZ()));
		stmt.setString(6, rdz.getPosicaoCRO());
		stmt.setString(7, rdz.getPosicaoRDZ());
		stmt.setString(8, rdz.getNumCOO());
		stmt.setBigDecimal(9, rdz.getVlGrandeTotal());
		stmt.setBigDecimal(10, rdz.getVlVendaBruta());
		if(rdz.getId() != null && rdz.getId() > 0){
			stmt.setLong(11, rdz.getId());
		}else {
			stmt.setLong(11, 0);
		}
	}

	public ReducaoZ rsReducao(ResultSet rs) throws SQLException {
		ReducaoZ rdz = new ReducaoZ();
		rdz.setId(rs.getLong("id"));
		rdz.setId_pai_emp(rs.getLong("id_pai_emp"));
		rdz.setId_pai_est(rs.getLong("id_pai_est"));
		rdz.setId_ecf(rs.getLong("id_ecf"));
        rdz.setIdPai(rs.getLong("idpai"));	
        rdz.setDtReducaoZ(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dtReducaoZ")));
        rdz.setPosicaoCRO(rs.getString("posicaoCRO"));
        rdz.setPosicaoRDZ(rs.getString("posicaoRDZ"));
        rdz.setNumCOO(rs.getString("numCOO"));
        rdz.setVlGrandeTotal(rs.getBigDecimal("vl_grd_tot"));
        rdz.setVlVendaBruta(rs.getBigDecimal("vl_vend_brut"));
		
		return rdz;
	}
	
	public  Long getIncReducaoZ() {
		Long idRetorno = null;
		Pool pool = new Pool();
		String sql = "SELECT AUTO_INCREMENT AS ID FROM information_schema.tables WHERE table_name = 'tb_reducaoz' AND table_schema = 'versaogamadb' ;";
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
