package versaogama.dao.estabelecimentodao.equipcfe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.cfe.ItensMovDiarioCFe;

public class ItensMovDiarioCFeDao implements ItensMovDiarioCFeDaoInterface{
	
	private Pool pool;
	
	public ItensMovDiarioCFeDao(Pool pool) {
		this.pool = pool;
	}

	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE * FROM tb_itens_mov_cfe WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		System.out.println( " Itens CFe excluído  com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public void salvar(ItensMovDiarioCFe item) throws SQLException {
		if(item.getId() != null && item.getId() > 0){
			atualizar(item);
		}else {
			cadastrar(item);
		}
	}

	@Override
	public void cadastrar(ItensMovDiarioCFe item) throws SQLException {
		String sql = "INSERT INTO tb_itens_mov_cfe (id_pai_emp,id_pai_est,idPai,num_cfe,num_item,codItem,qtde,unid,vlUnit,vl_prod,vldesc,vlItem,cst,cfop,chave_cfe,id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtItensMovDiarioCFe(stmt, item);
			stmt.execute();
		}
		System.out.println("Item Sat " + item.getNumCFe() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public void atualizar(ItensMovDiarioCFe item) throws SQLException {
		String sql = "UPDATE tb_itens_mov_cfe SET id_pai_emp=?,id_pai_est=?,idPai=?,num_cfe=?,num_item=?,codItem=?,qtde=?,unid=?,vlUnit=?,vl_prod=?,vldesc=?,vlitem=?,cst=?,cfop=?,chave_cfe=? WHERE id=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtItensMovDiarioCFe(stmt, item);
			stmt.execute();
		}
		System.out.println("Item Sat " + item.getNumCFe() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public ItensMovDiarioCFe getItemMovDiario(Integer codigo) throws SQLException {
		ItensMovDiarioCFe retorno = new ItensMovDiarioCFe();
		String sql = "SELECT * FROM tb_itens_mov_cfe WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				
				while(rs.next()) {	
					
					retorno = rsItemMovDiarioCFe(rs);
				}
				
			}
		}
		
		pool.liberarConnection(con);
		return retorno;
	}

	@Override
	public List<ItensMovDiarioCFe> getItensMovDiario() throws SQLException {
		List<ItensMovDiarioCFe> retorno = new ArrayList<ItensMovDiarioCFe>();
		String sql = "SELECT * FROM tb_itens_mov_cfe";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				while(rs.next()) {	
				
					ItensMovDiarioCFe item  = rsItemMovDiarioCFe(rs);
					retorno.add(item);
				}
				
			}
		}
		
		pool.liberarConnection(con);
		return retorno;
	}
	
	public void stmtItensMovDiarioCFe(PreparedStatement stmt, ItensMovDiarioCFe item) throws SQLException {
		
		stmt.setLong(1, item.getIdPaiEmp());
		stmt.setLong(2,item.getIdPaiEst());
		stmt.setLong(3, item.getIdPai());
		stmt.setString(4,item.getNumCFe() );
		stmt.setString(5, item.getNumItem());
		stmt.setString(6, item.getCodItem());
		stmt.setDouble(7, item.getQtde());
		stmt.setString(8, item.getUnd());
		stmt.setDouble(9, item.getVlUnit());
		stmt.setDouble(10, (item.getVlProd() == null ? 0.0 :item.getVlProd()));
		stmt.setDouble(11, (item.getVlDesc() == null ? 0.0 :item.getVlDesc() ));
		stmt.setDouble(12, (item.getVlItem()==null ? 0.0 : item.getVlItem()));
		stmt.setString(13, item.getCstIcms());
		stmt.setString(14,item.getCfop());
		stmt.setString(15, item.getChaveCFe());
		if(item.getId() != null && item.getId() > 0){
			stmt.setDouble(16,item.getId());
		}else {
			stmt.setDouble(16,0);
		}
		
	}
	
	public ItensMovDiarioCFe rsItemMovDiarioCFe(ResultSet rs) throws SQLException {
		ItensMovDiarioCFe item = new ItensMovDiarioCFe();
		
		item.setId(rs.getLong("id"));
		item.setIdPaiEmp(rs.getLong("id_pai_emp"));
		item.setIdPaiEst(rs.getLong("id_pai_est"));
		item.setIdPai(rs.getLong("idPai"));
		item.setNumCFe(rs.getString("num_cfe"));
		item.setNumItem(rs.getString("num_item"));
		item.setCodItem(rs.getString("cod_item"));
		item.setQtde(rs.getDouble("qtde"));
		item.setUnd(rs.getString("unid"));
		item.setVlUnit(rs.getDouble("vlUnit"));
		item.setVlProd(rs.getDouble("vl_prod"));
		item.setVlDesc(rs.getDouble("vldesc"));
		item.setVlItem(rs.getDouble("vlItem"));
		item.setCstIcms(rs.getString("cst"));
		item.setCfop(rs.getString("cfop"));
		item.setChaveCFe(rs.getString("chave_cfe"));
		
		return item;
	}

}
