package versaogama.dao.movprodutosdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.model.system.movprodutos.ModelHistoricoItens;
import versaogama.util.UtilsEConverters;

public class ModelHistoricoItensDao {

	 private final Pool pool;
	 
	public ModelHistoricoItensDao(Pool pool) {
		this.pool = pool;
	}
	
	
	public void cadastrarHistoricoItem(ModelHistoricoItens hist) throws SQLException {
		
		String sql = "INSERT INTO tb_historico_item(ID_LOTE,ID_PAI,"
				+ "EMP,"
				+ "OPERACAO,"
				+ "ECF_CX,"
				+ "DT_DOC,"
				+ "COD_ITEM,"
				+ "QTDE,"
				+ "UNID,"
				+ "VL_UNIT,"
				+ "VL_BRUTO,"
				+ "DESCONTO,"
				+ "VL_LIQ,"
				+ "CST,"
				+ "CFOP,"
				+ "ALIQ_ICMS,"
				+ "COD_MOD,"
				+ "DESCR_ITEM,"
				+ "NUM_DOC,"
				+ "CHAVE,"
				+ "NOME,"
				+ "CNPJCPF,ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtHistoricoItem(stmt, hist);
			stmt.execute();
		}
		System.out.println("Historico do prod " + hist.getCodItem() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
		
	}
	
	private void stmtHistoricoItem(PreparedStatement stmt, ModelHistoricoItens hist) throws SQLException {
		
		stmt.setLong(1,hist.getIdPaiLote());
		stmt.setLong(2,hist.getIdPai());
		stmt.setString(3, hist.getEmpresa());
		stmt.setString(4, hist.getOperacao());
		stmt.setString(5, hist.getEcfCx());
		stmt.setDate(6, UtilsEConverters.getLocalDateParaDateSQL( hist.getDtDoc()));
		stmt.setString(7, hist.getCodItem());
		stmt.setDouble(8, hist.getQtde());
		stmt.setString(9,hist.getUnd());
		stmt.setDouble(10, hist.getVlUnit());
		stmt.setDouble(11, hist.getVlBruto());
		stmt.setDouble(12, hist.getDesconto());
		stmt.setDouble(13, hist.getVlLiq());
		stmt.setString(14, hist.getCst());
		stmt.setString(15, hist.getCfop());
		stmt.setDouble(16, (hist.getAliqIcms() ==null ? 0.0 : hist.getAliqIcms()));
		stmt.setString(17, hist.getCodMod());
		stmt.setString(18, hist.getDescricao());
		stmt.setString(19, hist.getNumDoc());
		stmt.setString(20, hist.getChaveDoc());
		stmt.setString(21, hist.getNome());
		stmt.setString(22, hist.getCpfCnpj());
		if(hist.getId() != null && hist.getId() > 0) {
			stmt.setLong(23, hist.getId());
		}else {
			stmt.setLong(23, 0);
		}
		
	}
	
	public List<ModelHistoricoItens> getHistoricoItens(String cnpj,String codItem, String codAntItem, String ano) throws SQLException{
		List<ModelHistoricoItens> retorno = new ArrayList<ModelHistoricoItens>();
		String sql = "SELECT * FROM tb_historico_item WHERE EMP = ? AND COD_ITEM IN(?,?) AND YEAR(DT_DOC)=? ORDER BY DT_DOC,COD_ITEM,OPERACAO";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			stmt.setString(1, cnpj);
			stmt.setString(2, codItem);
			stmt.setString(3, codAntItem);
			stmt.setString(4, ano);
			stmt.execute();
			try(ResultSet rs = stmt.getResultSet()){	
				
				while(rs.next()) {		
					
					ModelHistoricoItens historico = rsModelHistoricoItem(rs);
					
					retorno.add(historico);
				}
			}
			
		}
		
		return retorno;
		
	}
    private ModelHistoricoItens rsModelHistoricoItem(ResultSet rs) throws SQLException {
    	ModelHistoricoItens retorno = new ModelHistoricoItens();
    	
    	retorno.setId(rs.getLong("ID"));
    	retorno.setIdPaiLote(rs.getLong("ID_LOTE"));
    	retorno.setIdPai(rs.getLong("ID_PAI"));
    	retorno.setEmpresa(rs.getString("EMP"));
    	retorno.setOperacao(rs.getString("OPERACAO"));
    	retorno.setEcfCx(rs.getString("ECF_CX"));
    	retorno.setDtDoc(UtilsEConverters.getSQLParaLocalDate(rs.getDate("DT_DOC")));
    	retorno.setCodItem(rs.getString("COD_ITEM"));
    	retorno.setQtde(rs.getDouble("QTDE"));
    	retorno.setUnd(rs.getString("UNID"));
    	retorno.setVlUnit(rs.getDouble("VL_UNIT"));
    	retorno.setVlBruto(rs.getDouble("VL_BRUTO"));
    	retorno.setDesconto(rs.getDouble("DESCONTO"));
    	retorno.setVlLiq(rs.getDouble("VL_LIQ"));
    	retorno.setCst(rs.getString("CST"));
    	retorno.setCfop(rs.getString("CFOP"));
    	retorno.setAliqIcms(rs.getDouble("ALIQ_ICMS"));
    	retorno.setCodMod(rs.getString("COD_MOD"));
    	retorno.setDescricao(rs.getString("DESCR_ITEM"));
    	retorno.setNumDoc(rs.getString("NUM_DOC"));
    	retorno.setChaveDoc(rs.getString("CHAVE"));
    	retorno.setNome(rs.getString("NOME"));
    	retorno.setCpfCnpj(rs.getString("CNPJCPF"));
    	
    	return retorno;
    }
}
