package versaogama.dao.estabelecimentodao.equipcfe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.cfe.EquipamentoCFe;
import versaogama.util.UtilsEConverters;

public class EquipamentoCFeDao implements EquipamentoCFeDaoInterface{

	private Pool pool;
	
	public EquipamentoCFeDao(Pool pool) {
		this.pool = pool;
	}
	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_equip_sat WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( " CFe excluído  com sucesso!!");
		pool.liberarConnection(con);
		
	}

	@Override
	public void salvar(EquipamentoCFe equip) throws SQLException {
	    if(equip.getId() != null && equip.getId() > 0) {
	    	atualizar(equip);
	    }else {
	    	cadastrar(equip);
	    }
		
	}

	@Override
	public void cadastrar(EquipamentoCFe equip) throws SQLException {
		String sql = "INSERT INTO tb_equip_sat (id_pai_emp,id_pai_est,id_pai_lote,cod_modelo,num_sat,dt_emissao_doc,doc_ini,doc_fin,id) VALUES(?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtEquipamentoSat(stmt, equip);
			stmt.execute();
		}
		System.out.println("Equipamento Sat " + equip.getNumSerieEquipSat() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public void atualizar(EquipamentoCFe equip) throws SQLException {
		String sql = "UPDATE tb_equip_sat SET id_pai_emp=?,id_pai_est=?,id_pai_lote=?,cod_modelo=?,num_sat=?,dt_emissao_doc=?,doc_ini=?,doc_fin=? WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtEquipamentoSat(stmt, equip);
			stmt.execute();
		}
		System.out.println("Equipamento Sat " + equip.getNumSerieEquipSat() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public EquipamentoCFe getEquipamento(Integer codigo) throws SQLException {
		EquipamentoCFe equip = new EquipamentoCFe();
		String sql = "SELECT * FROM tb_equip_sat WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.execute();
			stmt.setLong(1, codigo);
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {		
					equip =  rsEquipamento(rs);
				}
			}
		}
		pool.liberarConnection(con);
		return equip;
	}
	

	@Override
	public List<EquipamentoCFe> getEquipamentos() throws SQLException {
	    List<EquipamentoCFe> retorno = new ArrayList<EquipamentoCFe>();
		String sql = "SELECT * FROM tb_equip_sat";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				
				while(rs.next()) {	
					
					EquipamentoCFe cfe = rsEquipamento(rs);
					retorno.add(cfe);
				}
			}
			
		}

		pool.liberarConnection(con);
		return retorno;
	}
	
	public List<EquipamentoCFe> getIdEquip(Long lote) throws SQLException {
	    List<EquipamentoCFe> retorno = new ArrayList<EquipamentoCFe>();
		String sql = "SELECT * FROM tb_equip_sat where id_pai_lote = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setLong(1, lote);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				
				while(rs.next()) {	
					
					EquipamentoCFe cfe = rsEquipamento(rs);
					retorno.add(cfe);
				}
			}
			
		}

		pool.liberarConnection(con);
		return retorno;
	}

	private void stmtEquipamentoSat(PreparedStatement stmt, EquipamentoCFe equip) throws SQLException {
		
		stmt.setLong(1, equip.getId_pai_emp());
		stmt.setLong(2, equip.getId_pai_est());
		stmt.setLong(3, equip.getIdPai());
		stmt.setString(4, equip.getCodModDocFiscal());
		stmt.setString(5, equip.getNumSerieEquipSat());
		stmt.setDate(6, UtilsEConverters.getLocalDateParaDateSQL(equip.getDtEmissao()));
		stmt.setString(7, equip.getDocInicial());
		stmt.setString(8, equip.getDocFinal());
		if(equip.getId() != null && equip.getId() > 0) {
			stmt.setLong(9, equip.getId());
		}else {
			stmt.setLong(9, 0);
		}
	}
	
	private EquipamentoCFe rsEquipamento(ResultSet rs) throws SQLException {
		EquipamentoCFe equip = new EquipamentoCFe();
		equip.setId(rs.getLong("id"));
		equip.setId_pai_emp(rs.getLong("id_pai_emp"));
		equip.setId_pai_est(rs.getLong("id_pai_est"));
		equip.setIdPai(rs.getLong("id_pai_lote"));
		equip.setCodModDocFiscal(rs.getString("cod_modelo"));
		equip.setNumSerieEquipSat(rs.getString("num_sat"));
		equip.setDtEmissao(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_emissao_doc")));
		equip.setDocInicial(rs.getString("doc_ini"));
		equip.setDocFinal(rs.getString("doc_fin"));
		return equip;
	}
	
	public  Long getIncEquipCFe() {
		Long idRetorno = null;
		Pool pool = new Pool();
		String sql = "SELECT AUTO_INCREMENT AS ID FROM information_schema.tables WHERE table_name = 'tb_equip_sat' AND table_schema = 'versaogamadb' ;";
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
