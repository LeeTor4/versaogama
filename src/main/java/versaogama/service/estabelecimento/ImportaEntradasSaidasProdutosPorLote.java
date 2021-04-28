package versaogama.service.estabelecimento;

import java.sql.SQLException;

import versaogama.conexao.Pool;
import versaogama.dao.estabelecimentodao.importacaospedfical.TotalizadorePorItemEntDAO;
import versaogama.dao.estabelecimentodao.importacaospedfical.TotalizadorePorItemSaiDAO;
import versaogama.dao.movprodutosdao.EntradasSaidasDeProdutosDao;
import versaogama.model.system.TotalizadoresPorItem;
import versaogama.model.system.movprodutos.EntradasSaidasDeProdutos;

public class ImportaEntradasSaidasProdutosPorLote {


	private EntradasSaidasDeProdutosDao entsai;
	private TotalizadorePorItemEntDAO entDao;
	private TotalizadorePorItemSaiDAO saiDao;
	
	public ImportaEntradasSaidasProdutosPorLote() {
		Pool pool = new Pool();
		
		entsai = new EntradasSaidasDeProdutosDao(pool);
		entDao = new TotalizadorePorItemEntDAO(pool);
		saiDao = new TotalizadorePorItemSaiDAO(pool);
	}
	
	public void importacaoDosItensDeEntradasESaidasDeProdutos() throws SQLException {
		
		for(EntradasSaidasDeProdutos es :   entsai.retornaCadastroMovProdutos("12")){
			
			  TotalizadoresPorItem  resE = entDao.getTotalizadoresItensPorMesAnoEnt(es.getCnpj(), es.getCodItem(), es.getAno(), es.getMes());
			  TotalizadoresPorItem  resS = saiDao.getTotalizadoresItensPorMesAnoSai(es.getCnpj(), es.getCodItem(), es.getAno(), es.getMes());
		  
		
			  System.out.println(resE.getIdLote() +"|" +resE.getCodItem()+"|"+resE.getVlTotQtde()+"|"+resE.getVlTotItem()
			           +"|"
					  +resS.getIdLote() +"|" +resS.getCodItem()+"|"+resS.getVlTotQtde()+"|"+resS.getVlTotItem());
			 
			
	  }
	}
	
}
