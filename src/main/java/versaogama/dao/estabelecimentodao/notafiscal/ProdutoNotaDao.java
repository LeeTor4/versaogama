package versaogama.dao.estabelecimentodao.notafiscal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import versaogama.conexao.Pool;
import versaogama.model.system.notafiscal.ProdutoNota;

public class ProdutoNotaDao implements ProdutoNotaDaoInterface{

	private Pool pool;
	
	public ProdutoNotaDao(Pool pool) {
		this.pool = pool;
	}
	
	@Override
	public void excluir(Integer codigo) throws SQLException {

		String sql = "DELETE FROM tb_prod_nf WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( " Nota Fiscal excluída  com sucesso!!");
		pool.liberarConnection(con);
		
	}

	@Override
	public void salvar(ProdutoNota prodNF) throws SQLException {
		if(prodNF.getId() != null && prodNF.getId() > 0){
			atualizar(prodNF);
		}else {
			cadastrar(prodNF);
		}
		
	}

	@Override
	public void cadastrar(ProdutoNota prodNF) throws SQLException {
		
		String sql = "INSERT INTO tb_prod_nf (id_pai,num_item,cod_item,qtde,und,vl_unit_item,vl_prod,vl_desc,vl_item,cst_icms,cfop,id)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtProdNF(prodNF,stmt);
			stmt.execute();
		}
	   	
		System.out.println("Prod NF " + prodNF.getCodItem() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
		
	}

	private void stmtProdNF(ProdutoNota prodNF, PreparedStatement stmt) throws SQLException {
		
		stmt.setLong(1,   prodNF.getIdPai());
		stmt.setString(2, prodNF.getNumItem());
		stmt.setString(3, prodNF.getCodItem());
		stmt.setDouble(4, (prodNF.getQtde() == null ? 0.0 : prodNF.getQtde()));
		stmt.setString(5, prodNF.getUnd());
		stmt.setDouble(6, (prodNF.getVlUnitItem() == null ? 0.0 : prodNF.getVlUnitItem()));
		stmt.setDouble(7,  (prodNF.getVlProd() == null ? 0.0 : prodNF.getVlProd()));
		stmt.setDouble(8,  (prodNF.getVlDesc() == null ? 0.0 : prodNF.getVlDesc()));
        stmt.setDouble(9, (prodNF.getVlItem() == null ? 0.0 : prodNF.getVlItem()));
        stmt.setString(10, prodNF.getCstIcms());
        stmt.setString(11, prodNF.getCfop());
        if(prodNF.getId() != null && prodNF.getId() > 0) {
        	
        	stmt.setLong(12, prodNF.getId());
        }else {
        	stmt.setLong(12, 0);
        }
	}

	@Override
	public void atualizar(ProdutoNota prodNF) throws SQLException {
		
		String sql = "UPDATE tb_prod_nf SET id_pai=?, num_item=?, cod_item=?, qtde=?, und=?, vl_unit_item =?,vl_prod=?,vl_desc=?, vl_item=?, cst_icms=?,cfop=?"
				+ "WHERE id =?";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtProdNF(prodNF,stmt);
			stmt.execute();
		}
	   	
		System.out.println("Produto " + prodNF.getCodItem() + ", alterado com sucesso!!");
		pool.liberarConnection(con);
		
	}

	@Override
	public ProdutoNota getProdNFe(Integer codigo) throws SQLException {
		ProdutoNota prodNF = new ProdutoNota();
		String sql = "SELECT * FROM tb_prod_nf  WHERE id = ?";		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){						
				while(rs.next()) {					 
					prodNF = rsProdNFe(rs);										
				}
			}
		}
		pool.liberarConnection(con);	
		return prodNF;
	}

	@Override
	public List<ProdutoNota> getProdsNFe() throws SQLException {
		List<ProdutoNota> retorno = new ArrayList<ProdutoNota>();
		String sql = "SELECT * FROM tb_prod_nf";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){						
				while(rs.next()) {					 
					ProdutoNota prodNF = rsProdNFe(rs);					
					retorno.add(prodNF);
				}
			}
		}
		pool.liberarConnection(con);		
		return retorno;
	}
	
	private ProdutoNota rsProdNFe(ResultSet rs) throws SQLException{
		ProdutoNota prod = new ProdutoNota();
		
		prod.setId(rs.getLong("id"));
		prod.setIdPai(rs.getLong("id_pai"));
		prod.setNumItem(rs.getString("num_item"));
		prod.setCodItem(rs.getString("cod_item"));
		prod.setQtde(rs.getDouble("qtde"));
		prod.setUnd(rs.getString("und")); 
		prod.setVlUnitItem(rs.getDouble("vl_unit_item"));
		prod.setVlProd(rs.getDouble("vl_prod"));
		prod.setVlDesc(rs.getDouble("vl_desc"));
		prod.setVlItem(rs.getDouble("vl_item"));
		prod.setCstIcms(rs.getString("cst_icms"));
		prod.setCfop(rs.getString("cfop"));
		
		return prod;
		
	}

}
