package versaogama.dao.estabelecimentodao.notafiscal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.notafiscal.NotaFiscal;
import versaogama.util.UtilsEConverters;

public class NotaFiscalDao implements NotaFiscalDaoInterface{
	
	private Pool pool;
	
	public NotaFiscalDao(Pool pool) {
		this.pool = pool;
	}

	@Override
	public void excluir(Integer codigo) throws SQLException {
		String sql = "DELETE FROM tb_notafiscal WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmt.setLong(1, codigo);
			stmt.execute();
		}
		
		System.out.println( " Nota Fiscal excluída  com sucesso!!");
		pool.liberarConnection(con);
	
	}

	@Override
	public Long salvar(NotaFiscal nota) throws SQLException {
		if(nota.getId() != null && nota.getId() > 0) {
			atualizar(nota);
		}else {
			return cadastrar(nota);
		}
		
		return 0L;
	}

	@Override
	public Long cadastrar(NotaFiscal nota) throws SQLException {
		Long id = 0L;
		String sql = "INSERT INTO tb_notafiscal(id_pai_emp,id_pai_est,id_pai,tipo_operacao,tipo_emissor,cod_part,especie_doc,num_doc,chave_doc,dt_emissao,dt_ent_sai,vl_tot_prod,vl_desc,vl_merc,id)"
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtNotaFiscal(nota,stmt);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {
				
				id = rs.getLong(1);
			}
		}
	   	
		System.out.println("Nota Fiscal " + nota.getNumeroDocumento() + ", cadastrada com sucesso!!");
		pool.liberarConnection(con);
		return id;
	}

	private void stmtNotaFiscal(NotaFiscal nota, PreparedStatement stmt) throws SQLException {
		stmt.setLong(1, nota.getIdPaiEmp());
		stmt.setLong(2, nota.getIdPaiEst());
		stmt.setLong(3, nota.getIdPai());
		stmt.setString(4, nota.getTipoOperacao());
		stmt.setString(5, nota.getTipoEmissor());
		stmt.setString(6, nota.getCodParticipante());
		stmt.setString(7,nota.getEspecieDoc());
		stmt.setString(8, nota.getNumeroDocumento());
		stmt.setString(9, nota.getChaveDocumento());
		stmt.setDate(10, UtilsEConverters.getLocalDateParaDateSQL(nota.getDataEmisaso()));
		stmt.setDate(11,UtilsEConverters.getLocalDateParaDateSQL(nota.getDataEntSai()));
		stmt.setDouble(12, (nota.getVlTotalProd()==null ? 0.0 : nota.getVlTotalProd()));
		stmt.setDouble(13, (nota.getVlDesconto()==null ? 0.0 : nota.getVlDesconto()));
		stmt.setDouble(14, (nota.getVlMercadorias()==null ? 0.0 : nota.getVlMercadorias()));
		if(nota.getId() != null && nota.getId() > 0) {
			stmt.setLong(15, nota.getId());
		}else {
			stmt.setLong(15, 0);
		}
		
	}

	@Override
	public void atualizar(NotaFiscal nota) throws SQLException {
		String sql = "UPDATE tb_notafiscal SET id_pai_emp=?,id_pai_est=?,id_pai=?,tipo_operacao=?,tipo_emissor=?,cod_part=?,especie_doc=?,num_doc=?,chave_doc=?,dt_emissao=?,dt_ent_sai=?,vl_tot_prod=?,vl_desc=?,vl_merc=?"
				+ "WHERE id = ?";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtNotaFiscal(nota,stmt);
			stmt.execute();
		}
		
		System.out.println("Nota Fiscal " + nota.getNumeroDocumento() + ", alterada com sucesso!!");
		pool.liberarConnection(con);
	}

	@Override
	public NotaFiscal getNotaFiscal(Integer codigo) throws SQLException {
		NotaFiscal nf = new NotaFiscal();
		String sql = "SELECT * FROM tb_notafiscal WHERE id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){		
			stmt.setLong(1, codigo);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){				
				while(rs.next()) {					
					nf = rsNotaFiscal(rs);							
				}
			}	
		}		
		pool.liberarConnection(con);		
		return nf;
	}

	@Override
	public List<NotaFiscal> getNotasFiscais() throws SQLException {
		List<NotaFiscal> retorno = new ArrayList<NotaFiscal>();
		String sql = "SELECT * FROM tb_notafiscal";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){			
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){				
				while(rs.next()) {
					
					NotaFiscal nf = rsNotaFiscal(rs);
					retorno.add(nf);					
				}

			}	
		}		
		pool.liberarConnection(con);
		return retorno;
	}

	private NotaFiscal rsNotaFiscal(ResultSet rs) throws SQLException {		
		NotaFiscal nf = new NotaFiscal();		
		nf.setId(rs.getLong("id"));
		nf.setIdPaiEmp(rs.getLong("id_pai_emp"));
		nf.setIdPaiEst(rs.getLong("id_pai_est"));
		nf.setIdPai(rs.getLong("idPai"));
		nf.setTipoOperacao(rs.getString("tipo_operacao"));
		nf.setTipoEmissor(rs.getString("tipo_emissor"));
		nf.setCodParticipante(rs.getString("cod_part"));
		nf.setEspecieDoc(rs.getString("especie_doc"));
		nf.setNumeroDocumento(rs.getString("num_doc"));
		nf.setChaveDocumento(rs.getString("chave_doc"));
		nf.setDataEmisaso(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_emissao")));
		nf.setDataEntSai(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_ent_sai")));
		nf.setVlTotalProd(rs.getDouble("vl_tot_prod"));
		nf.setVlDesconto(rs.getDouble("vl_desc"));
		nf.setVlMercadorias(rs.getDouble("vl_merc"));
		
		return nf;
	}
	
	
	public  Long getIncNFe() {
		Long idRetorno = null;
		Pool pool = new Pool();
		String sql = "SELECT AUTO_INCREMENT AS ID FROM information_schema.tables WHERE table_name = 'tb_notafiscal' AND table_schema = 'versaogamadb' ;";
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
