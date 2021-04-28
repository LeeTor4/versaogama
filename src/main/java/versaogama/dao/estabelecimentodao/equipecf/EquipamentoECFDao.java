package versaogama.dao.estabelecimentodao.equipecf;

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
import versaogama.model.system.equipecf.EquipamentoECF;

public class EquipamentoECFDao implements EquipamentoECFDaoInterface{
	
	private Pool pool;
	
	public EquipamentoECFDao(Pool pool) {
		this.pool = pool;
	}

	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_equip_ecf WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( " ECF excluído  com sucesso!!");
		pool.liberarConnection(con);
		
	}

	@Override
	public Long salvar(EquipamentoECF equipEcf) throws SQLException {
		if(equipEcf.getId() != null && equipEcf.getId() > 0){
			atualizar(equipEcf);
		}else {
			cadastrar(equipEcf);
		}
		return 0L;
	}

	@Override
	public Long cadastrar(EquipamentoECF equipEcf) throws SQLException {
		Long id = 0L;
		String sql = "INSERT INTO tb_equip_ecf(id_pai_emp,id_pai_est,cod_mod_doc,mod_equip,num_fab_ecf,num_ecf,id) VALUES(?,?,?,?,?,?,?)";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtEquip(equipEcf, stmt);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {				
				id = rs.getLong(1);
			}
		}
		System.out.println("Equipamento " + equipEcf.getNumECF() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
		return id;
	}

	@Override
	public void atualizar(EquipamentoECF equipEcf) throws SQLException {
		String sql = "UPDATE tb_equip_ecf SET id_pai_emp=?,id_pai_est=?,cod_mod_doc=?,mod_equip=?,num_fab_ecf=?,num_ecf=? WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmtEquip(equipEcf, stmt);
			stmt.execute();
			
		}
		
		System.out.println("Equipamento " + equipEcf.getNumECF() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public EquipamentoECF getEquipamentoFiscal(Integer codigo) throws SQLException {
		EquipamentoECF ecf = new EquipamentoECF();
		String sql = "SELECT * FROM tb_equip_ecf WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {		
					ecf = rsEquipECF(rs);
				}
			}
		}
		pool.liberarConnection(con);		
		return ecf;
	}
	
	public EquipamentoECF getEquipamentoFiscal(String numFab) throws SQLException {
		EquipamentoECF ecf = new EquipamentoECF();
		String sql = "SELECT * FROM tb_equip_ecf WHERE num_fab_ecf=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setString(1, numFab);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {		
					ecf = rsEquipECF(rs);
				}
			}
		}
		pool.liberarConnection(con);		
		return ecf;
	}

	@Override
	public List<EquipamentoECF> getEquipamentosFiscais() throws SQLException {
		List<EquipamentoECF>  retorno = new ArrayList<EquipamentoECF>();
		String sql = "SELECT * FROM tb_equip_ecf";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {		
					EquipamentoECF ecf  = rsEquipECF(rs);
					retorno.add(ecf);
				}
			}
		}
		pool.liberarConnection(con);		
		return retorno;
	}
	
	public Map<String,EquipamentoECF> getEquipamentosFiscaisPorNumFab() throws SQLException {
		Map<String,EquipamentoECF>  retorno = new HashMap<String,EquipamentoECF>();
		String sql = "SELECT * FROM tb_equip_ecf";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {		
					EquipamentoECF ecf  = rsEquipECF(rs);
					retorno.put(ecf.getNumSerieFabECF(),ecf);
				}
			}
		}
		pool.liberarConnection(con);		
		return retorno;
	}
	
	public void stmtEquip(EquipamentoECF equipEcf,PreparedStatement stmt) throws SQLException{
		
		stmt.setLong(1, equipEcf.getId_pai_emp());
		stmt.setLong(2, equipEcf.getId_pai_est());
		stmt.setString(3, equipEcf.getCodModDocFiscal());
		stmt.setString(4, equipEcf.getModeloEquip());
		stmt.setString(5, equipEcf.getNumSerieFabECF());
		stmt.setString(6, equipEcf.getNumECF());
		if(equipEcf.getId() != null && equipEcf.getId() > 0){
			stmt.setLong(7,equipEcf.getId());
		}else {
			stmt.setLong(7,0);
		}
		
	}
	
	public EquipamentoECF rsEquipECF(ResultSet rs) throws SQLException {
		EquipamentoECF ecf = new EquipamentoECF();
		
		ecf.setId(rs.getLong("id"));
		ecf.setId_pai_emp(rs.getLong("id_pai_emp"));
        ecf.setCodModDocFiscal(rs.getString("cod_mod_doc"));
        ecf.setModeloEquip(rs.getString("mod_equip"));
        ecf.setNumSerieFabECF(rs.getString("num_fab_ecf"));
        ecf.setNumECF(rs.getString("num_ecf"));
		
		return ecf;
	}

}
