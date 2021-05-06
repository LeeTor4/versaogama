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
	
	public void importacaoDosItensDeEntradasESaidasDeProdutos(Long lote) throws SQLException {
		
		  Double totQtdeEnt=0.0;
		  Double vlItemEnt=0.0;
		  Double totQtdeSai=0.0;
		  Double vlItemSai=0.0;
		  for(EntradasSaidasDeProdutos es :   entsai.retornaCadastroMovProdutosPorId(lote)){
			
			  TotalizadoresPorItem  resE = entDao.getTotalizadoresItensPorMesAnoEnt(es.getCnpj(), es.getCodItem(), es.getAno(), es.getMes());
			  TotalizadoresPorItem  resS = saiDao.getTotalizadoresItensPorMesAnoSai(es.getCnpj(), es.getCodItem(), es.getAno(), es.getMes());
			  totQtdeEnt = (resE.getVlTotQtde()==null ? 0.0 : resE.getVlTotQtde());
			  vlItemEnt  = (resE.getVlTotItem()==null ? 0.0 : resE.getVlTotItem());
			  totQtdeSai = (resS.getVlTotQtde()==null ? 0.0 : resS.getVlTotQtde());
			  vlItemSai  = (resS.getVlTotItem()==null ? 0.0 : resS.getVlTotItem());
			  EntradasSaidasDeProdutos obj = new EntradasSaidasDeProdutos(
					  es.getId(), es.getIdCodItem(), es.getCnpj(), es.getDescricao(), 
					  es.getAno(), 
					  es.getMes(),
					  es.getCodItem(), 
					  es.getCodAntItem(),
					  totQtdeEnt,
					  vlItemEnt,
					  totQtdeSai,
					  vlItemSai);
			  
			  switch (es.getMes()) {
				case "1":
					entsai.cadastrarTabEntSaiJan(obj);
					break;
				case "2":
					entsai.cadastrarTabEntSaiFev(obj);
					break;
				case "3":
					entsai.cadastrarTabEntSaiMar(obj);
					break;
				case "4":
					entsai.cadastrarTabEntSaiAbr(obj);
					break;
				case "5":
					entsai.cadastrarTabEntSaiMai(obj);
					break;
				case "6":
					entsai.cadastrarTabEntSaiJun(obj);
					break;
				case "7":
					entsai.cadastrarTabEntSaiJul(obj);
					break;
				case "8":
					entsai.cadastrarTabEntSaiAgo(obj);
					break;
				case "9":
					entsai.cadastrarTabEntSaiSet(obj);
					break;
				case "10":
					entsai.cadastrarTabEntSaiOut(obj);
					break;
				case "11":
					entsai.cadastrarTabEntSaiNov(obj);
					break;
				case "12":
					entsai.cadastrarTabEntSaiDez(obj);
					break;
				}
				 
		  }
	}
	
}
