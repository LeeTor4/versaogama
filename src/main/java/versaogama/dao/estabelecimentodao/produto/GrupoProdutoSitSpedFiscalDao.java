package versaogama.dao.estabelecimentodao.produto;

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
import versaogama.model.system.produto.SituacaoGrpProdutoSpedFiscal;

public class GrupoProdutoSitSpedFiscalDao {
	
	private Pool pool;
	
	public GrupoProdutoSitSpedFiscalDao(Pool pool) {
		this.pool = pool;
	}

	
	public List<SituacaoGrpProdutoSpedFiscal> tiposItens() throws SQLException{		
		List<SituacaoGrpProdutoSpedFiscal> retorno = new ArrayList<SituacaoGrpProdutoSpedFiscal>();
		
		String sql = "SELECT * FROM tb_cod_sit_grp_prod_spedfiscal";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				
				while(rs.next()) {
					SituacaoGrpProdutoSpedFiscal sit = new SituacaoGrpProdutoSpedFiscal();					
					sit.setId(rs.getLong("id"));
					sit.setValor(rs.getString("valor"));
					sit.setDescricao(rs.getString("descricao"));
					retorno.add(sit);
				}
			}
			
		}
		
		pool.liberarConnection(con);
		return retorno;
	}
	
	public SituacaoGrpProdutoSpedFiscal tipoItem(String valor) throws SQLException{		
		SituacaoGrpProdutoSpedFiscal sit = new SituacaoGrpProdutoSpedFiscal();			
		String sql = "SELECT * FROM tb_cod_sit_grp_prod_spedfiscal WHERE valor=?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.setString(1, valor);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				
				while(rs.next()) {
							
					sit.setId(rs.getLong("id"));
					sit.setValor(rs.getString("valor"));
					sit.setDescricao(rs.getString("descricao"));
					
				}
			}
			
		}
		
		pool.liberarConnection(con);
		return sit;
	}
	
	public Map<String , SituacaoGrpProdutoSpedFiscal> mpTipoItem() throws SQLException{		
		Map<String , SituacaoGrpProdutoSpedFiscal> mpSit = new HashMap<String, SituacaoGrpProdutoSpedFiscal>();			
		String sql = "SELECT * FROM tb_cod_sit_grp_prod_spedfiscal";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){					
				while(rs.next()) {
					SituacaoGrpProdutoSpedFiscal sit = new SituacaoGrpProdutoSpedFiscal(); 		
					sit.setId(rs.getLong("id"));
					sit.setValor(rs.getString("valor"));
					sit.setDescricao(rs.getString("descricao"));
					mpSit.put(sit.getValor(), sit);
				}
			}	
		}
		pool.liberarConnection(con);
		return mpSit;
	}
}
