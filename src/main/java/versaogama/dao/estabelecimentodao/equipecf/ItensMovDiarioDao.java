package versaogama.dao.estabelecimentodao.equipecf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.equipecf.ItensMovDiario;

public class ItensMovDiarioDao implements ItensMovDiarioDaoInterface{
	
	private Pool pool;
	
	 public ItensMovDiarioDao(Pool pool) {
		this.pool = pool;
	}

	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_itens_mov_cf WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( " Itens Cupons excluído  com sucesso!!");
		pool.liberarConnection(con);
		
	}

	@Override
	public Long salvar(ItensMovDiario itnMovDirario) throws SQLException {
		if(itnMovDirario.getId() != null && itnMovDirario.getId() > 0){
			atualizar(itnMovDirario);
		}else {
			cadastrar(itnMovDirario);
		}
		return 0L;
	}

	@Override
	public Long cadastrar(ItensMovDiario itnMovDirario) throws SQLException {
		Long id = 0L;
		String sql = "INSERT INTO tb_itens_mov_cf (id_pai_emp,id_pai_est,idPai,id_pai_redz,cod_item,qtde,und,vl_item,vl_pis,vl_cofins,id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtItensCupons(itnMovDirario, stmt);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {
				
				id = rs.getLong(1);
			}
			
		}
		System.out.println("Item cupom fiscal " + itnMovDirario.getCodItem() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
		return id;
	}

	@Override
	public void atualizar(ItensMovDiario itnMovDirario) throws SQLException {
		String sql = "UPDATE tb_itens_mov_cf SET id_pai_emp=?,id_pai_est=?,idPai=?,id_pai_redz=?,cod_item=?,qtde=?,und=?,vl_item=?,vl_pis=?,vl_cofins=?  WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmtItensCupons(itnMovDirario, stmt);
			stmt.execute();
		}
		System.out.println("Item cupom fiscal " + itnMovDirario.getCodItem() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public ItensMovDiario getNotaFiscal(Integer codigo) throws SQLException {
		ItensMovDiario itn = new ItensMovDiario();
		String sql = "SELECT * FROM tb_itens_mov_cf WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){		
				while(rs.next()) {					
					itn = rsItensCupons(rs);
				}
			}
		}
		return itn;
	}

	@Override
	public List<ItensMovDiario> getNotasFiscais() throws SQLException {
		List<ItensMovDiario> retorno = new ArrayList<ItensMovDiario>();
		String sql = "SELECT * FROM tb_itens_mov_cf";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){		
				while(rs.next()) {					
					ItensMovDiario itn = rsItensCupons(rs);
					retorno.add(itn);
				}
			}
		}
		return retorno;
	}
	
	public void stmtItensCupons(ItensMovDiario itn, PreparedStatement stmt) throws SQLException {
		stmt.setLong(1, itn.getId_pai_emp());
		stmt.setLong(2, itn.getId_pai_est());
		stmt.setLong(3, itn.getIdPai());
		stmt.setLong(4,itn.getIdPaiRedZ());
		stmt.setString(5, itn.getCodItem());
		stmt.setDouble(6, itn.getQtde());
		stmt.setString(7, itn.getUnd());
		stmt.setDouble(8, itn.getVlItem());
		stmt.setDouble(9, (itn.getVlPis()==null ? 0.0 : itn.getVlPis()));
		stmt.setDouble(10, (itn.getVlCofins()==null ? 0.0 : itn.getVlCofins()));
		if(itn.getId() != null && itn.getId() > 0){
			stmt.setLong(11, itn.getId());
		}else {
			stmt.setLong(11,0);
		}
	}
	
	public ItensMovDiario rsItensCupons(ResultSet rs) throws SQLException {
		ItensMovDiario itn = new ItensMovDiario();
		
		itn.setId(rs.getLong("id"));
		itn.setId_pai_emp(rs.getLong("id_pai_emp"));
		itn.setId_pai_est(rs.getLong("id_pai_est"));
		itn.setIdPai(rs.getLong("idPai"));
		itn.setIdPaiRedZ(rs.getLong("id_pai_redz"));
		itn.setCodItem(rs.getString("cod_item"));
		itn.setQtde(rs.getDouble("qtde"));
		itn.setUnd(rs.getString("und"));
		itn.setVlItem(rs.getDouble("vl_item"));
		itn.setVlPis(rs.getDouble("vl_pis"));
		itn.setVlCofins(rs.getDouble("vl_cofins"));
		
		return itn;
	}

}
