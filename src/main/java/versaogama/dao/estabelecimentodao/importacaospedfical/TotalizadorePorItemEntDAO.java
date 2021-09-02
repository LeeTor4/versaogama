package versaogama.dao.estabelecimentodao.importacaospedfical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.TotalizadoresPorItem;
import versaogama.model.system.movprodutos.ModelValorUnitarioDoProduto;

public class TotalizadorePorItemEntDAO implements TotalizadorPorItemInterface{

	private final Pool pool;
	
	
	public TotalizadorePorItemEntDAO(Pool pool) {
		this.pool = pool;
	}
	
	@Override
	public void excluir(Integer codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void salvar(TotalizadoresPorItem totItem) throws SQLException {
		String sql = "INSERT INTO tb_totalizadorporitem_ent("
				+ "num_lote,"
				+ "cnpj,"
				+ "ano,"
				+ "mes,"
				+ "cod_item,"
				+ "count_frequencia,"
				+ "tot_qtde,"
				+ "vl_tot_item) VALUES (?,?,?,?,?,?,?,?)";
		
	
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmt.setLong(1, totItem.getIdLote());
			stmt.setString(2, totItem.getCnpj());
			stmt.setInt(3, totItem.getAno());
			stmt.setInt(4, totItem.getMes());
			stmt.setString(5, totItem.getCodItem());
			stmt.setLong(6, totItem.getFrequencia());
			stmt.setDouble(7, totItem.getVlTotQtde());
			stmt.setDouble(8, totItem.getVlTotItem());
			stmt.execute();
			try(ResultSet rs = stmt.getGeneratedKeys()){
				
			}
		}

        System.out.println("Totalizadores Entrada Itens salvos com Sucesso!!!");
		
		pool.liberarConnection(con);
		
	}

	@Override
	public void atualizar(TotalizadoresPorItem totItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TotalizadoresPorItem getTotItem(Integer codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TotalizadoresPorItem> getTotItens() throws SQLException {
		List<TotalizadoresPorItem> retorno = new ArrayList<>();
		String sql = "SELECT * FROM tb_totalizadorporitem_ent ORDER BY cod_item,num_lote";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
	
           stmt.executeQuery();
	       try(ResultSet rs = stmt.getResultSet()){		
	    		while(rs.next()) {		
	    			
	    			TotalizadoresPorItem tot = rsListTotalizadores(rs);
	    			retorno.add(tot);
	    			
	    		}
	       }
			
			
		}
		
		return retorno;
	}
	
	
	public TotalizadoresPorItem getTotalizadoresItensPorMesAnoEnt(String cnpj, String codItem, String ano, String mes) throws SQLException {
		TotalizadoresPorItem retorno = new TotalizadoresPorItem();
		String sql = "SELECT * FROM tb_totalizadorporitem_ent where  cnpj = ? and cod_item = ? and ano = ? and mes = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setString(1, cnpj);
			stmt.setString(2, codItem);
			stmt.setString(3, ano);
			stmt.setString(4, mes);
            stmt.executeQuery();
            try(ResultSet rs = stmt.getResultSet()){		
            	while(rs.next()) {	
            		
            		retorno = rsListTotalizadores(rs);
            	}
            	
            }
		}
		pool.liberarConnection(con);	
		return retorno;
	}
	
	
	public ModelValorUnitarioDoProduto ultimoRegistroDoItem(String cnpj,  String codItem,String ano) throws SQLException {
		ModelValorUnitarioDoProduto retorno = null;
		String sql = "SELECT ano,mes,cod_item, tot_qtde, (vl_tot_item/tot_qtde) as vl_unit, vl_tot_item FROM  (SELECT (@id:=@id+1) as id,ano,mes,cod_item,tot_qtde,vl_tot_item\r\n" + 
				"					FROM tb_totalizadorporitem_ent \r\n" + 
				"					CROSS join (SELECT @id:=0)  as seq\r\n" + 
				"					WHERE cnpj = ? and cod_item = ? and ano < ? \r\n" + 
				"					ORDER BY ano,mes) AS tabinv\r\n" + 
				"ORDER BY id desc limit 1;";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setString(1, cnpj);
			stmt.setString(2, codItem);
			stmt.setString(3, ano);
			stmt.executeQuery();
	        try(ResultSet rs = stmt.getResultSet()){	
	           while(rs.next()) {	
	        	   retorno = new ModelValorUnitarioDoProduto();
	        	   	
	        	   retorno.setAno(rs.getString("ano"));
	        	   retorno.setMes(rs.getString("mes"));
	        	   retorno.setCodItem(rs.getString("cod_item"));
	        	   retorno.setTotQtde(rs.getDouble("tot_qtde"));
	        	   retorno.setVlUnit(rs.getDouble("vl_unit"));
	        	   retorno.setVlTotItem(rs.getDouble("vl_tot_item"));
	           }
	        }
			
		}
		pool.liberarConnection(con);	
		return retorno;
	}
	
	
	public ModelValorUnitarioDoProduto ultimoRegistroDoItemCafeteria(String cnpj,  String codItem,String ano) throws SQLException {
		ModelValorUnitarioDoProduto retorno = null;
		String sql = "SELECT id,operacao,ano,mes,cod_item, qtde,  vl_unit \r\n" + 
				"       FROM  (SELECT (@id:=@id+1) as id,operacao,year(dt_doc) as ano, month(dt_doc) as mes,cod_item,qtde,vl_unit \r\n" + 
				"									FROM tb_historico_item  \r\n" + 
				"									CROSS join (SELECT @id:=0)  as seq \r\n" + 
				"									WHERE emp = ? and cod_item = ? and year(dt_doc) < ? \r\n" + 
				"									ORDER BY ano,mes) AS tabinv ORDER BY id desc limit 1;";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setString(1, cnpj);
			stmt.setString(2, codItem);
			stmt.setString(3, ano);
			stmt.executeQuery();
	        try(ResultSet rs = stmt.getResultSet()){	
	           while(rs.next()) {	
	        	   retorno = new ModelValorUnitarioDoProduto();
	        	   	
	        	   retorno.setAno(rs.getString("ano"));
	        	   retorno.setMes(rs.getString("mes"));
	        	   retorno.setCodItem(rs.getString("cod_item"));
	        	   retorno.setTotQtde(rs.getDouble("qtde"));
	        	   retorno.setVlUnit(rs.getDouble("vl_unit"));
	        	   //retorno.setVlTotItem(rs.getDouble("vl_tot_item"));
	           }
	        }
			
		}
		pool.liberarConnection(con);	
		return retorno;
	}
	private TotalizadoresPorItem rsListTotalizadores(ResultSet rs) throws SQLException {
		TotalizadoresPorItem totItem = new TotalizadoresPorItem();
		totItem.setId(rs.getLong("id"));
		totItem.setIdLote(rs.getLong("num_lote"));
		totItem.setCnpj(rs.getString("cnpj"));
		totItem.setAno(rs.getInt("ano"));
		totItem.setMes(rs.getInt("mes"));
		totItem.setCodItem(rs.getString("cod_item"));
		totItem.setFrequencia(rs.getLong("count_frequencia"));
		totItem.setVlTotQtde(rs.getDouble("tot_qtde"));
		totItem.setVlTotItem(rs.getDouble("vl_tot_item"));
		
		return totItem;
	}
}
