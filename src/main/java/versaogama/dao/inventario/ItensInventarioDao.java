package versaogama.dao.inventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.inventario.ItensInventario;

public class ItensInventarioDao implements ItensInventarioDaoInterface{
	
	private final Pool pool;
	
	public ItensInventarioDao(Pool pool) {
		this.pool = pool;
	}

	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_inventario WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( "Itens Inventário excluído  com sucesso!!");
		pool.liberarConnection(con);
		
	}

	@Override
	public void salvar(ItensInventario itn) throws SQLException {
		if(itn.getId() !=null && itn.getId() > 0) {
			atualizar(itn);
		}else {
			cadastrar(itn);
		}
		
	}

	@Override
	public void cadastrar(ItensInventario itn) throws SQLException {
		String sql = "INSERT INTO tb_inventario(id_pai,cod_item,unid,qtde,vl_unit,vl_item,ind_prop,cod_part,txt_part,cod_CTA,vl_item_IR,id)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmtItensInventario(stmt, itn);
			stmt.execute();
		}
		
		System.out.println("Itens Inventário " + itn.getId() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public void atualizar(ItensInventario itn) throws SQLException {
		String sql = "UPDATE tb_inventario SET id_pai=?,cod_item=?,unid=?,qtde=?,vl_unit=?,vl_item=?,ind_prop=?,cod_part=?,txt_part=?,cod_CTA=?,vl_item_IR=? WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmtItensInventario(stmt, itn);
			stmt.execute();
		}
		
		System.out.println("Itens Inventário " + itn.getId() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public ItensInventario getItensIventario(Integer codigo) throws SQLException {
		ItensInventario itn = new ItensInventario();
		String sql = "SELECT * FROM tb_inventario WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {							
					itn = rsItnInventario(rs);
				}
			}
		}
		pool.liberarConnection(con);		
		return itn;
	}

	@Override
	public List<ItensInventario> getItensInventarios() throws SQLException {
		List<ItensInventario> retorno = new ArrayList<ItensInventario>();
		String sql = "SELECT * FROM tb_inventario";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {		
					
					ItensInventario itn = rsItnInventario(rs);
					retorno.add(itn);
				}
			}
		}
		pool.liberarConnection(con);
		return retorno;
	}
	
	public void stmtItensInventario(PreparedStatement stmt, ItensInventario itn) throws SQLException {
		
		stmt.setLong(1, itn.getIdPai());
		stmt.setString(2, itn.getCodItem());
		stmt.setString(3, itn.getUnd());
		stmt.setDouble(4, itn.getQtde());
		stmt.setDouble(5, itn.getVlUnit());
		stmt.setDouble(6, itn.getVlItem());
		stmt.setString(7, itn.getIndProp());
		stmt.setString(8, itn.getCodPart());
		stmt.setString(9, itn.getTxtCompl());
		stmt.setString(10, itn.getCodCta());
		stmt.setDouble(11,itn.getVlItemIr());
		if(itn.getId() != null && itn.getId() > 0) {
			stmt.setLong(12, itn.getId());
		}else {
			stmt.setLong(12, 0);
		}
	}
	
	public ItensInventario rsItnInventario(ResultSet rs) throws SQLException {
		ItensInventario itn = new ItensInventario();		
		itn.setId(rs.getLong("id"));
		itn.setIdPai(rs.getLong("id_pai"));
		itn.setCodItem(rs.getString("cod_item"));
		itn.setUnd(rs.getString("unid"));
		itn.setQtde(rs.getDouble("qtde"));
		itn.setVlUnit(rs.getDouble("vl_unit"));
		itn.setVlItem(rs.getDouble("vl_item"));
		itn.setIndProp(rs.getString("ind_prop"));
		itn.setCodPart(rs.getString("cod_part"));
		itn.setTxtCompl(rs.getString("txt_part"));
		itn.setCodCta(rs.getString("cod_CTA"));
		itn.setVlItemIr(rs.getDouble("vl_item_IR"));
		
		return itn;
	}

}
