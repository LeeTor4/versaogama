package versaogama;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import versaogama.conexao.Pool;
import versaogama.controller.TableBean;
import versaogama.dao.estabelecimentodao.equipcfe.EquipamentoCFeDao;
import versaogama.dao.estabelecimentodao.importacaospedfical.LoteImportacaoSpedFiscalDao;
import versaogama.dao.estabelecimentodao.importacaospedfical.TotalizadorePorItemEntDAO;
import versaogama.dao.estabelecimentodao.importacaospedfical.TotalizadorePorItemSaiDAO;
import versaogama.dao.estabelecimentodao.notafiscal.ProdutoNotaDao;
import versaogama.dao.estabelecimentodao.produto.ProdutoDao;
import versaogama.dao.movprodutosdao.EntradasSaidasDeProdutosDao;
import versaogama.managersped.LeitorTxtSpedFiscal;
import versaogama.managerxml.LeitorXML;

import versaogama.model.sped.Reg0000;
import versaogama.model.sped.Reg0150;
import versaogama.model.sped.Reg0200;
import versaogama.model.sped.Reg0220;
import versaogama.model.sped.RegC100;
import versaogama.model.sped.RegC170;
import versaogama.model.sped.RegC400;
import versaogama.model.sped.RegC860;
import versaogama.model.system.LoteImportacaoSpedFiscal;
import versaogama.model.system.TotalizadoresPorItem;
import versaogama.model.system.movprodutos.EntradasSaidasDeProdutos;
import versaogama.model.system.notafiscal.ProdutoNota;
import versaogama.model.system.produto.Produto;
import versaogama.model.system.produto.SituacaoGrpProdutoSpedFiscal;
import versaogama.model.xml.ProdutoCupomFiscalXml;
import versaogama.model.xml.ProdutoNotaXmlProprio;

public class Main2 {
	

	public static void main(String[] args) throws Exception {
		
		Pool pool = new Pool();
		ProdutoNotaDao daoProdNF  = new ProdutoNotaDao(pool);
		ProdutoDao     daoProd    = new ProdutoDao(pool);
		TotalizadorePorItemEntDAO entDao = new TotalizadorePorItemEntDAO(pool);
		TotalizadorePorItemSaiDAO saiDao = new TotalizadorePorItemSaiDAO(pool);
		EntradasSaidasDeProdutosDao entsai = new EntradasSaidasDeProdutosDao(pool);
		LoteImportacaoSpedFiscalDao lote =  new LoteImportacaoSpedFiscalDao(pool);
		
		Path pXml = Paths.get("D:\\XML");
		Path p = Paths.get("D:\\ORTOGENESE\\SPED\\2014\\SpedEFD-05329222000176-068147449-Remessa de arquivo original-jan2018_0205_ok.txt");
		
	    XMLReader reader = XMLReaderFactory.createXMLReader();
	    LeitorXML logica = new LeitorXML();	
	    reader.setContentHandler(logica); 
	    
		LeitorTxtSpedFiscal leitor = new LeitorTxtSpedFiscal();
		leitor.leitorSpedFiscal(p,pool);
		
		RegC100 nf = new RegC100();
		nf.getNotasFiscaisTxtSpedFiscal(leitor);
		  
	   // ProdutoNota pNota = new ProdutoNota();
	
		//pNota.listaProdutosAgregadosSpedXml(p, pXml, reader, logica,pool);
	
//		for(int i = 0; i < pNota.getEntProds().size(); i++){
//			System.out.println(pNota.getEntProds().get(i).getCodItem());
//		}
		
//		pNota.getProdutos().stream()
//		     .forEach(c -> System.out.println(c.getCodItem()+"|"+c.getCst()+"|"+c.getCfop()+"|"+c.getQtde()+"|"+c.getVlUnit()+"|"+c.getVlItem()));
		
	
//		 DecimalFormat df = new DecimalFormat("#,###.00");
//		 Double qtdeSum = daoProdNF.getProdsNFe().stream()
//              .filter(c -> c.getCodItem().equals("12777"))
//              .mapToDouble(ProdutoNota::getQtde)
//              .sum();
//		 
//		 Double vlItemSum = daoProdNF.getProdsNFe().stream()
//	              .filter(c -> c.getCodItem().equals("12777"))
//	              .mapToDouble(ProdutoNota::getVlItem)
//	              .sum();
//
//         System.out.println("Item 12777 => " + qtdeSum +"|"+ df.format(vlItemSum));
		
		
		  Double totQtdeEnt=0.0;
		  Double vlItemEnt=0.0;
		  Double totQtdeSai=0.0;
		  Double vlItemSai=0.0;
		  for(EntradasSaidasDeProdutos es :   entsai.retornaCadastroMovProdutosPorId(2L)){
			
			  TotalizadoresPorItem  resE = entDao.getTotalizadoresItensPorMesAnoEnt(es.getCnpj(), es.getCodItem(), es.getAno(), es.getMes());
			  TotalizadoresPorItem  resS = saiDao.getTotalizadoresItensPorMesAnoSai(es.getCnpj(), es.getCodItem(), es.getAno(), es.getMes());
			  totQtdeEnt = (resE.getVlTotQtde()==null ? 0.0 : resE.getVlTotQtde());
			  vlItemEnt  = (resE.getVlTotItem()==null ? 0.0 : resE.getVlTotItem());
			  totQtdeSai = (resS.getVlTotQtde()==null ? 0.0 : resS.getVlTotQtde());
			  vlItemSai  = (resS.getVlTotItem()==null ? 0.0 : resS.getVlTotItem());
			  EntradasSaidasDeProdutos obj = new EntradasSaidasDeProdutos(
					  es.getIdCodItem(), es.getCnpj(), es.getDescricao(), 
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
//					  System.out.println(obj.getIdPai() +"|" + obj.getCodItem() +"|"+ obj.getTotQtdeEnt() +"|"
//					  + obj.getTotVlItemEnt() +"|"+ obj.getTotQtdeSai() +"|"+ obj.getTotVlItemSai());
					break;
				case "2":
					entsai.cadastrarTabEntSaiFev(obj);
//					  System.out.println(obj.getIdPai() +"|" + obj.getCodItem() +"|"+ obj.getTotQtdeEnt() +"|"
//					  + obj.getTotVlItemEnt() +"|"+ obj.getTotQtdeSai() +"|"+ obj.getTotVlItemSai());
					break;
				case "3":
					entsai.cadastrarTabEntSaiMar(obj);
//					  System.out.println(obj.getIdPai() +"|" + obj.getCodItem() +"|"+ obj.getTotQtdeEnt() +"|"
//					  + obj.getTotVlItemEnt() +"|"+ obj.getTotQtdeSai() +"|"+ obj.getTotVlItemSai());
					break;
				case "4":
					entsai.cadastrarTabEntSaiAbr(obj);
//					  System.out.println(obj.getIdPai() +"|" + obj.getCodItem() +"|"+ obj.getTotQtdeEnt() +"|"
//					  + obj.getTotVlItemEnt() +"|"+ obj.getTotQtdeSai() +"|"+ obj.getTotVlItemSai());
					break;
				case "5":
					entsai.cadastrarTabEntSaiMai(obj);
//					  System.out.println(obj.getIdPai() +"|" + obj.getCodItem() +"|"+ obj.getTotQtdeEnt() +"|"
//					  + obj.getTotVlItemEnt() +"|"+ obj.getTotQtdeSai() +"|"+ obj.getTotVlItemSai());
					break;
				case "6":
					entsai.cadastrarTabEntSaiJun(obj);
//					  System.out.println(obj.getIdPai() +"|" + obj.getCodItem() +"|"+ obj.getTotQtdeEnt() +"|"
//					  + obj.getTotVlItemEnt() +"|"+ obj.getTotQtdeSai() +"|"+ obj.getTotVlItemSai());
					break;
				case "7":
					entsai.cadastrarTabEntSaiJul(obj);
//					  System.out.println(obj.getIdPai() +"|" + obj.getCodItem() +"|"+ obj.getTotQtdeEnt() +"|"
//					  + obj.getTotVlItemEnt() +"|"+ obj.getTotQtdeSai() +"|"+ obj.getTotVlItemSai());
					break;
				case "8":
					entsai.cadastrarTabEntSaiAgo(obj);
//					  System.out.println(obj.getIdPai() +"|" + obj.getCodItem() +"|"+ obj.getTotQtdeEnt() +"|"
//					  + obj.getTotVlItemEnt() +"|"+ obj.getTotQtdeSai() +"|"+ obj.getTotVlItemSai());
					break;
				case "9":
					entsai.cadastrarTabEntSaiSet(obj);
//					  System.out.println(obj.getIdPai() +"|" + obj.getCodItem() +"|"+ obj.getTotQtdeEnt() +"|"
//					  + obj.getTotVlItemEnt() +"|"+ obj.getTotQtdeSai() +"|"+ obj.getTotVlItemSai());
					break;
				case "10":
					entsai.cadastrarTabEntSaiOut(obj);
//					  System.out.println(obj.getIdPai() +"|" + obj.getCodItem() +"|"+ obj.getTotQtdeEnt() +"|"
//					  + obj.getTotVlItemEnt() +"|"+ obj.getTotQtdeSai() +"|"+ obj.getTotVlItemSai());
					break;
				case "11":
					entsai.cadastrarTabEntSaiNov(obj);
//					  System.out.println(obj.getIdPai() +"|" + obj.getCodItem() +"|"+ obj.getTotQtdeEnt() +"|"
//					  + obj.getTotVlItemEnt() +"|"+ obj.getTotQtdeSai() +"|"+ obj.getTotVlItemSai());
					break;
				case "12":
					entsai.cadastrarTabEntSaiDez(obj);
//					  System.out.println(obj.getIdPai() +"|" + obj.getCodItem() +"|"+ obj.getTotQtdeEnt() +"|"
//					  + obj.getTotVlItemEnt() +"|"+ obj.getTotQtdeSai() +"|"+ obj.getTotVlItemSai());
					break;
				}
				 
		  }
	
		 // System.out.println(lote.getLote(1).getDtIni()+"|"+lote.getLote(1).getDtFin());
	}
	

}



//class ListaRegC100 implements Consumer<RegC100>{
//
//	@Override
//	public void accept(RegC100 reg) {
//		System.out.println(reg.getId()+"|"+reg.getIdPai()+"|"+reg.getReg()+"|"+ reg.getDtDoc()+"|"+reg.getNumDoc()+"|"+reg.getChvNfe());
//		
//	}
//}

//class OrdenacaoPorNumDoc implements Comparator<RegC100>{
//	@Override
//	public int compare(RegC100 o1, RegC100 o2) {
//		if(o1.getNumDoc().length() < o2.getNumDoc().length()){
//			return -1;
//		}
//		if(o1.getNumDoc().length() > o2.getNumDoc().length()){
//			return 1;
//		}
//		return 0;
//	}
//}


