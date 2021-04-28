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
