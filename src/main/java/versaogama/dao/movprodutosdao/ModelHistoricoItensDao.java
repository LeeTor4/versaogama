package versaogama.dao.movprodutosdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import versaogama.conexao.Pool;
import versaogama.model.system.movprodutos.ModelHistoricoItens;
import versaogama.util.UtilsEConverters;

public class ModelHistoricoItensDao {

	 private final Pool pool;
	 
	public ModelHistoricoItensDao(Pool pool) {
		this.pool = pool;
	}
	
	
	public void cadastrarHistoricoItem(ModelHistoricoItens hist) throws SQLException {
		
		String sql = "INSERT INTO tb_historico_item(ID_PAI,"
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
				+ "CNPJCPF,ID) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection con = pool.getConnection();
		try(PreparedStatement stmt =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
			
			stmtHistoricoItem(stmt, hist);
			stmt.execute();
		}
		System.out.println("Historico do prod " + hist.getCodItem() + ", cadastrado com sucesso!!");
		pool.liberarConnection(con);
		
	}
	
	private void stmtHistoricoItem(PreparedStatement stmt, ModelHistoricoItens hist) throws SQLException {
		
		stmt.setLong(1,hist.getIdPai());
		stmt.setString(2, hist.getEmpresa());
		stmt.setString(3, hist.getOperacao());
		stmt.setString(4, hist.getEcfCx());
		stmt.setDate(5, UtilsEConverters.getLocalDateParaDateSQL( hist.getDtDoc()));
		stmt.setString(6, hist.getCodItem());
		stmt.setDouble(7, hist.getQtde());
		stmt.setString(8,hist.getUnd());
		stmt.setDouble(9, hist.getVlUnit());
		stmt.setDouble(10, hist.getVlBruto());
		stmt.setDouble(11, hist.getDesconto());
		stmt.setDouble(12, hist.getVlLiq());
		stmt.setString(13, hist.getCst());
		stmt.setString(14, hist.getCfop());
		stmt.setDouble(15, (hist.getAliqIcms() ==null ? 0.0 : hist.getAliqIcms()));
		stmt.setString(16, hist.getCodMod());
		stmt.setString(17, hist.getDescricao());
		stmt.setString(18, hist.getNumDoc());
		stmt.setString(19, hist.getChaveDoc());
		stmt.setString(20, hist.getNome());
		stmt.setString(21, hist.getCpfCnpj());
		if(hist.getId() != null && hist.getId() > 0) {
			stmt.setLong(22, hist.getId());
		}else {
			stmt.setLong(22, 0);
		}
		
	}
}
