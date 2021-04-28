package versaogama.dao.estabelecimentodao.importacaospedfical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import versaogama.conexao.Pool;
import versaogama.model.system.LoteImportacaoSpedFiscal;
import versaogama.util.UtilsEConverters;

public class LoteImportacaoSpedFiscalDao{

	private final Pool pool;
	
	public LoteImportacaoSpedFiscalDao(Pool pool) {
		super();
		this.pool = pool;
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
	}


	public void excluir(Integer codigo) {
		// TODO Auto-generated method stub

	}


	public Long salvar(LoteImportacaoSpedFiscal ltImport) throws SQLException {
		Long id = 0L;
		String sql = "INSERT INTO tb_lote_import_sped_icms(cod_ver,"
				+ "cod_fin,"
				+ "dt_ini,"
				+ "dt_fin,"
				+ "nome,"
				+ "cnpj,"
				+ "cpf,"
				+ "uf,"
				+ "ie,"
				+ "cod_mun,"
				+ "im,"
				+ "suframa,"
				+ "ind_perfil,"
				+ "ind_ativ,id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			
			stmtLoteImportacao(ltImport, stmt);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			while(rs.next()) {
				
				id = rs.getLong(1);
			}

			
		}
		pool.liberarConnection(con);	 
		return id;
	}


	private void stmtLoteImportacao(LoteImportacaoSpedFiscal ltImport, PreparedStatement stmt) throws SQLException {
		stmt.setString(1, ltImport.getCodVersao());
		stmt.setString(2, ltImport.getCodFinalidade());
		stmt.setDate(3, UtilsEConverters.getLocalDateParaDateSQL(ltImport.getDtIni()));
		stmt.setDate(4, UtilsEConverters.getLocalDateParaDateSQL(ltImport.getDtFin()));
		stmt.setString(5, ltImport.getNome());
		stmt.setString(6, ltImport.getCnpj());
		stmt.setString(7, ltImport.getCpf());
		stmt.setString(8, ltImport.getUf());
		stmt.setString(9, ltImport.getIe());
		stmt.setString(10, ltImport.getCodMun());
		stmt.setString(11, ltImport.getIM());
		stmt.setString(12, ltImport.getSuframa());
		stmt.setString(13, ltImport.getIndPerfil());
		stmt.setString(14, ltImport.getIndAtiv());
		if(ltImport.getId() != null && ltImport.getId() > 0) {
			stmt.setLong(15, ltImport.getId());
		}else {
			stmt.setLong(15, 0);
		}
	}


	public List<LoteImportacaoSpedFiscal> getLoteImports(String ano,String mes) throws SQLException {
		List<LoteImportacaoSpedFiscal> retorno = new ArrayList<>();
		String sql = "SELECT * FROM tb_lote_import_sped_icms where year(dt_ini)=? and month(dt_ini)=?";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setString(1, ano);
			stmt.setString(2, mes);
			stmt.executeQuery();
			try(ResultSet rs = stmt.getResultSet()){
	            while(rs.next()) {
	            	LoteImportacaoSpedFiscal ltImp = new LoteImportacaoSpedFiscal();
					
					ltImp.setId(rs.getLong("id"));
					ltImp.setCodVersao(rs.getString("cod_versao"));
					ltImp.setCodFinalidade(rs.getString("cod_finalidade"));
					ltImp.setDtIni(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_ini")));
					ltImp.setDtFin(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_fin")));
					ltImp.setNome(rs.getString("nome"));
					ltImp.setCnpj(rs.getString("cnpj"));
					ltImp.setCpf(rs.getString("cpf"));
					ltImp.setUf(rs.getString("uf"));
					ltImp.setIe(rs.getString("ie"));
					ltImp.setCodMun(rs.getString("cod_mun"));
					ltImp.setIM(rs.getString("im"));
					ltImp.setSuframa(rs.getString("suframa"));
					ltImp.setIndPerfil(rs.getString("ind_perfil"));
					ltImp.setIndAtiv(rs.getString("ind_ativ"));
					
					retorno.add(ltImp);
	            	
	            }
				
			}
		
			pool.liberarConnection(con);
		}
		
		return retorno;

	}


	public LoteImportacaoSpedFiscal getLote(Integer codigo) throws SQLException {
		LoteImportacaoSpedFiscal retorno = null;
		String sql = "SELECT * FROM tb_lote_import_sped_icms where id = ?";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql)){
			stmt.setLong(1, codigo);
			stmt.executeQuery();
			try(ResultSet rs = stmt.getResultSet()){				
				  while(rs.next()) {
					  retorno = new LoteImportacaoSpedFiscal();
					  retorno.setId(rs.getLong("id"));
					  retorno.setCodVersao(rs.getString("cod_ver"));
					  retorno.setCodFinalidade(rs.getString("cod_fin"));
					  retorno.setDtIni(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_ini")));
					  retorno.setDtFin(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_fin")));
					  retorno.setNome(rs.getString("nome"));
					  retorno.setCnpj(rs.getString("cnpj"));
					  retorno.setCpf(rs.getString("cpf"));
					  retorno.setUf(rs.getString("uf"));
					  retorno.setIe(rs.getString("ie"));
					  retorno.setCodMun(rs.getString("cod_mun"));
					  retorno.setIM(rs.getString("im"));
					  retorno.setSuframa(rs.getString("suframa"));
					  retorno.setIndPerfil(rs.getString("ind_perfil"));
					  retorno.setIndAtiv(rs.getString("ind_ativ"));  
				  }
			}
			pool.liberarConnection(con);
		}	
		
		return retorno;
	}


	public List<LoteImportacaoSpedFiscal> getLoteImports() throws SQLException {
		List<LoteImportacaoSpedFiscal> retorno = new ArrayList<>();
		String sql = "SELECT * FROM tb_lote_import_sped_icms";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){	
			stmt.executeQuery();
			try(ResultSet rs = stmt.getResultSet()){
	            while(rs.next()) {
	            	LoteImportacaoSpedFiscal ltImp = new LoteImportacaoSpedFiscal();
					
					ltImp.setId(rs.getLong("id"));
					ltImp.setCodVersao(rs.getString("cod_ver"));
					ltImp.setCodFinalidade(rs.getString("cod_fin"));
					ltImp.setDtIni(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_ini")));
					ltImp.setDtFin(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_fin")));
					ltImp.setNome(rs.getString("nome"));
					ltImp.setCnpj(rs.getString("cnpj"));
					ltImp.setCpf(rs.getString("cpf"));
					ltImp.setUf(rs.getString("uf"));
					ltImp.setIe(rs.getString("ie"));
					ltImp.setCodMun(rs.getString("cod_mun"));
					ltImp.setIM(rs.getString("im"));
					ltImp.setSuframa(rs.getString("suframa"));
					ltImp.setIndPerfil(rs.getString("ind_perfil"));
					ltImp.setIndAtiv(rs.getString("ind_ativ"));
					
					retorno.add(ltImp);
	            	
	            }
				
			}
		
			pool.liberarConnection(con);
		}
		
		return retorno;
	}


	public LoteImportacaoSpedFiscal getPrimeiroLoteImport() throws SQLException {	
		LoteImportacaoSpedFiscal retorno = null;
		String sql = "SELECT * FROM tb_lote_import_sped_icms limit 1";
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql)){
			stmt.executeQuery();
			try(ResultSet rs = stmt.getResultSet()){				
				  while(rs.next()) {
					  retorno = new LoteImportacaoSpedFiscal();
					  retorno.setId(rs.getLong("id"));
					  retorno.setCodVersao(rs.getString("cod_versao"));
					  retorno.setCodFinalidade(rs.getString("cod_finalidade"));
					  retorno.setDtIni(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_ini")));
					  retorno.setDtFin(UtilsEConverters.getSQLParaLocalDate(rs.getDate("dt_fin")));
					  retorno.setNome(rs.getString("nome"));
					  retorno.setCnpj(rs.getString("cnpj"));
					  retorno.setCpf(rs.getString("cpf"));
					  retorno.setUf(rs.getString("uf"));
					  retorno.setIe(rs.getString("ie"));
					  retorno.setCodMun(rs.getString("cod_mun"));
					  retorno.setIM(rs.getString("im"));
					  retorno.setSuframa(rs.getString("suframa"));
					  retorno.setIndPerfil(rs.getString("ind_perfil"));
					  retorno.setIndAtiv(rs.getString("ind_ativ"));  
				  }
			}
			pool.liberarConnection(con);
		}	
		
		return retorno;
	}

	public Long getIncLoteImp() {
		Long idRetorno = null;
		Pool pool = new Pool();
		String sql = "SELECT AUTO_INCREMENT AS ID FROM information_schema.tables WHERE table_name = 'tb_lote_import_sped_icms' AND table_schema = 'versaogamadb' ;";
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
