package versaogama.dao.inventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.inventario.Inventario;
import versaogama.util.UtilsEConverters;

public class InventarioDao implements InventarioDaoInterface{

	private final Pool pool;
	
	public InventarioDao(Pool pool) {
		this.pool = pool;
	}
	
	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_inv_totais WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( " Inventário excluído  com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public void salvar(Inventario inv) throws SQLException {
		if(inv.getId() != null && inv.getId() > 0) {
		   atualizar(inv);	
		}else {
			cadastrar(inv);
		}
		
	}

	@Override
	public void cadastrar(Inventario inv) throws SQLException {
		String sql = "INSERT INTO tb_inv_totais (id_pai,dt_inv,vl_inv,mot_inv,id) VALUES (?,?,?,?,?)";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtInventario(stmt, inv);
			stmt.execute();
		}
		
		System.out.println("Inventário " + inv.getId() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public void atualizar(Inventario inv) throws SQLException {
		String sql = "UPDATE tb_inv_totais SET id_pai=?,dt_inv=?,vl_inv=?,mot_inv=? WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtInventario(stmt, inv);
			stmt.execute();
		}
		
		System.out.println("Inventário " + inv.getId() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public Inventario getInventario(Integer codigo) throws SQLException {
		Inventario retorno = new Inventario();
		String sql = "SELECT * FROM tb_inv_totais WHERE id=?";	
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {			
					retorno = rsInventario(rs);
					
				}	
			}
		}
		pool.liberarConnection(con);
		return retorno;
	}

	@Override
	public List<Inventario> getInventarios() throws SQLException {
		List<Inventario> retorno = new ArrayList<Inventario>();
		String sql = "SELECT * FROM tb_inv_totais";	
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				
				while(rs.next()) {			
					Inventario inv = rsInventario(rs);
					retorno.add(inv);
				}	
			}
		}
		pool.liberarConnection(con);
		return retorno;
	}
	
	public void stmtInventario(PreparedStatement stmt, Inventario inv) throws SQLException {
		
		stmt.setLong(1, inv.getIdPai());
		stmt.setDate(2, UtilsEConverters.getLocalDateParaDateSQL(inv.getDataInv()));
		stmt.setDouble(3, inv.getVlTotal());
		stmt.setString(4, inv.getMotivoInventario());
		if(inv.getId() != null && inv.getId() > 0) {
			stmt.setLong(5, inv.getId());
		}else {
			stmt.setLong(5, 0);
		}
		
	}
	
	public Inventario rsInventario(ResultSet rs) throws SQLException {
		Inventario inv = new Inventario();
		
		inv.setId(rs.getLong("id"));
		inv.setIdPai(rs.getLong("id_pai"));
		inv.setDataInv(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_ini")));
		inv.setVlTotal(rs.getDouble("vl_ini"));
		inv.setMotivoInventario(rs.getString("mot_inv"));

		return inv;
	}

}
