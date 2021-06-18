package versaogama;

import java.nio.file.Path;
import java.nio.file.Paths;
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
import versaogama.dao.estabelecimentodao.produto.ProdutoDao;
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
import versaogama.model.system.movprodutos.ModelHistoricoItens;
import versaogama.model.system.produto.Produto;
import versaogama.model.system.produto.ProdutoNota;
import versaogama.model.system.produto.SituacaoGrpProdutoSpedFiscal;
import versaogama.model.xml.ProdutoCupomFiscalXml;
import versaogama.model.xml.ProdutoNotaXmlProprio;

public class Main {
	

	public static void main(String[] args) throws Exception {
		
		Pool pool = new Pool();
		ProdutoDao daoProd = new ProdutoDao(pool);
		
		Path pXml = Paths.get("D:\\XML");
		Path p = Paths.get("D:\\ORTOGENESE\\SPED\\2014\\SpedEFD-05329222000419-063882345-Remessa de arquivo original-jan2019.txt");
		
	    XMLReader reader = XMLReaderFactory.createXMLReader();
	    LeitorXML logica = new LeitorXML();	
	    reader.setContentHandler(logica); 
	    
		LeitorTxtSpedFiscal leitor = new LeitorTxtSpedFiscal();
		leitor.leitorSpedFiscal(p,pool);
		
		RegC100 nf = new RegC100();
		nf.getNotasFiscaisTxtSpedFiscal(leitor);
		  
	    ProdutoNota pNota = new ProdutoNota();
	    ModelHistoricoItens historico = new ModelHistoricoItens();
		//pNota.listaProdutosAgregadosSpedXml(p, pXml, reader, logica,pool);

		
//		//Relação das notas de entradas
//		for(ProdutoNota pNF :  pNota.getEntProds()) {
//	    	
//	    		System.out.println( pNF.getNumItem() + "|" + pNF.getCodItem() + "|" + pNF.getCfop() + "|" + pNF.getCst() + "|" +  pNF.getQtde()
//		    	+ "|" + pNF.getVlUnit() + "|" + pNF.getVlProd() + "|" + pNF.getVlDesc()+ "|" + pNF.getVlItem());
//	    }
		
	
		
		
		
//		Produto prod = new Produto();
		
//		for(Reg0000 reg :  leitor.getRegs0000()){
//			System.out.println(reg.getId() +"|"+reg.getReg()+"|"+reg.getCodVer() +"|"+reg.getCodFin());
//		}

//		for(Reg0150 reg : leitor.getRegs0150()){
//			System.out.println(reg.getId()+"|"+reg.getIdPai()+"|"+reg.getReg()+"|"+reg.getCodPart() +"|"+reg.getNome()  +"|"+reg.getEndereco());
//		}
		
        //SituacaoGrpProdutoSpedFiscal s = new SituacaoGrpProdutoSpedFiscal();
        //System.out.println(s.mpSitProdSpedFiscal().get("00").getId()+"|"+s.mpSitProdSpedFiscal().get("00").getDescricao());
        
//		Collections.sort(leitor.getRegs0200());
//		for(Reg0200 reg : leitor.getRegs0200()){
//		   System.out.println(reg.getId()+"|"+reg.getIdPai()+"|"+reg.getReg()+"|"+reg.getCodItem()+"|"+reg.getDescrItem()+"|"+reg.getTipoItem());
//		   //System.out.println(reg.getDescrItem()+"|"+reg.getCodItem());
//	    }
		
		
		
		
//		for(Reg0220 reg : leitor.getRegs0220()){
//		   System.out.println(reg.getId()+"|"+reg.getIdPai()+"|"+reg.getReg()+"|"+reg.getUndConv());
//	    }
			
//		for(int i = 0; i < leitor.getRegs0200().size();i++) {
//              System.out.println("0200 "+leitor.getRegs0200().get(i).getCodItem());  
//             
//              for(int x =0; x < leitor.getRegs0200().get(i).getOutrasUndMedidas().size();x++) {  
//            	  System.out.println("0220 " + leitor.getRegs0200().get(i).getOutrasUndMedidas().get(x).getUndConv());
//              }
//              for(int y = 0; y < leitor.getRegs0200().get(i).getAlteracaoItem().size();y++) {
//            	  System.out.println("0205 "+leitor.getRegs0200().get(i).getAlteracaoItem().get(y).getCodAntItem());
//              }
//		}
		
//		for(RegC100 reg : leitor.getRegsC100()){
//			   System.out.println(reg.getId()+"|"+reg.getIdPai()+"|"+reg.getReg()+"|"+ reg.getDtDoc()+"|"+reg.getNumDoc()+"|"+reg.getChvNfe());
//	    }
		
		
//		//Ordenação com Classe Anonima
//		Comparator<RegC100> ordenacaoNumDoc = new Comparator<RegC100>() {
//
//			@Override
//			public int compare(RegC100 o1, RegC100 o2) {
//				if(o1.getNumDoc().length() < o2.getNumDoc().length()){
//					return -1;
//				}
//				if(o1.getNumDoc().length() > o2.getNumDoc().length()){
//					return 1;
//				}
//				return 0;
//			}
//			
//		};		
		
		
//		//Ordenação com Lambda - 1
//		Comparator<RegC100> ordenacaoNumDoc = (o1, o2) -> {
//				if(o1.getNumDoc().length() < o2.getNumDoc().length()){
//					return -1;
//				}
//				if(o1.getNumDoc().length() > o2.getNumDoc().length()){
//					return 1;
//				}
//				return 0;			
//		};	
		
		
//		//Ordenação com Lambda - 2
//		Comparator<RegC100> ordenacaoNumDoc = (o1, o2) -> {
//              return Integer.compare(o1.getNumDoc().length() , o2.getNumDoc().length());	
//		};	
		
		//Ordenação com Lambda - 3
//		Comparator<RegC100> ordenacaoNumDoc = (o1, o2) -> Integer.compare(o1.getNumDoc().length() , o2.getNumDoc().length());
//		
//		Collections.sort(leitor.getRegsC100(),ordenacaoNumDoc);
		
		
		
//		//Percorrendo com classe anonima
//		leitor.getRegsC100().forEach(new Consumer<RegC100>() {
//
//			@Override
//			public void accept(RegC100 reg) {
//				System.out.println(reg.getId()+"|"+reg.getIdPai()+"|"+reg.getReg()+"|"+ reg.getDtDoc()+"|"+reg.getNumDoc()+"|"+reg.getChvNfe());
//				
//			}
//			
//		});
		
		//Adaptando a classe anonima com Lambda
//		leitor.getRegsC100().forEach(reg -> 
//		  System.out.println(reg.getId()+"|"+reg.getIdPai()+"|"+reg.getReg()+"|"+ reg.getDtDoc()+"|"+reg.getNumDoc()+"|"+reg.getChvNfe())
//		);
		
//		System.out.println(leitor.incNFe());
//		for(int i=0; i < leitor.getRegsC100().size();i++){
//			System.out.println(leitor.getRegsC100().get(i).getChvNfe());
//		}
	
		
//		for(RegC170 reg : leitor.getRegsC170()){
//			   System.out.println(reg.getId()+"|"+reg.getIdPai()+"|"+reg.getReg()+"|"+ reg.getNumItem()+"|"+ reg.getCodItem()+"|"+ reg.getCfop() +"|"+reg.getCstIcms()+"|"+reg.getQtd()
//			        +"|" + reg.getVlItem());
//	    }
		
		
		
		
		
		
//		leitor.getRegsC170().stream()
//		         .filter(reg -> reg.getCstIcms().equals("560"))
//		         .forEach(reg -> System.out.println(reg.getId()+"|"+reg.getIdPai()+"|"+reg.getReg()+"|"+ reg.getNumItem()+"|"+ reg.getCodItem()+"|"+ reg.getCfop() +"|"+reg.getCstIcms()+"|"+reg.getQtd()
//		         +"|" + reg.getVlItem()));
		
//		Double soma = leitor.getRegsC170().stream()
//					        .filter(reg -> reg.getCstIcms().equals("060"))
//					        .mapToDouble(RegC170::getVlItem)
//					        .sum();
//		
//		System.out.println(soma);
		
		
		
		  //Produtos agrupados
//		  for(ProdutoNota pr : pNota.getProdutos()){
//			  System.out.println(pr.getIdPai() + "|" + pr.getCodItem()+ "|" + pr.getCfop()+ "|" + pr.getCst() + "|" + pr.getQtde()
//			  + "|" + pr.getVlItem());
//		  }
		
		
//		  for(int i=0; i < leitor.getRegsH005().size(); i++) {
//			  System.out.println(leitor.getRegsH005().get(i).getId()+"|"+leitor.getRegsH005().get(i).getIdPai()+
//					  leitor.getRegsH005().get(i).getDataInv()); 
//		  
//			  for(int x=0; x < leitor.getRegsH005().get(i).getListaItensInv().size() ; x++) {
//				   System.out.println(leitor.getRegsH005().get(i).getListaItensInv().get(x).getId()+
//						   "|"+leitor.getRegsH005().get(i).getListaItensInv().get(x).getIdPai()+
//						   "|"+leitor.getRegsH005().get(i).getListaItensInv().get(x).getCodItem()+
//						   "|"+leitor.getRegsH005().get(i).getListaItensInv().get(x).getQtde()+
//						   "|"+leitor.getRegsH005().get(i).getListaItensInv().get(x).getVlUnit()+
//						   "|"+leitor.getRegsH005().get(i).getListaItensInv().get(x).getVlItem()+
//						   "|"+leitor.getRegsH005().get(i).getListaItensInv().get(x).getIndProp());  
//			  }
//		  }
		
		
		
		
		
		
		
//        O código abaixo ler os registro dos ECF´s		
		
//		  for (int i = 0; i < leitor.getRegsC400().size(); i++) { // Equipamento ECF
//			 
//				  System.out.println(leitor.getRegsC400().get(i).getId()+"|"+leitor.getRegsC400().get(i).getNumCaixaECF());
//				  for (int x = 0; x < leitor.getRegsC400().get(i).getRegsC405().size(); x++) { //Reduções Z
//					  
//					  System.out.println(leitor.getRegsC400().get(i).getRegsC405().get(x).getId()+"|"+
//				              leitor.getRegsC400().get(i).getRegsC405().get(x).getIdPai()+"|"+
//							  leitor.getRegsC400().get(i).getRegsC405().get(x).getPosicaoRDZ()+"|"+ leitor.getRegsC400().get(i).getRegsC405().get(x).getIdPaiECF());
//					  for (int y = 0; y < leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().size(); y++) {//Tot Parciais
//						  
//						  System.out.println(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getId()+"|"+
//								  leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getIdPai()+"|"+
//								  leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getCodTotPar()+"|"+
//								  leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getVlAcumTot());
//					  
//						  for (int w = 0; w < leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().size(); w++) {
//							  
//							   System.out.println(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getId()+"|"+
//									   leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getIdPai()+"|"+
//									   leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getIdPaiRedZ()+"|"+
//									   leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getCodItem()+"|"+
//									   leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getQtd()+"|"+
//									   leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getUnd()+"|"+
//									   leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getVlItem());
//							  
//						  }
//	
//					  }
//					  
//					   for (int z = 0; z < leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC490().size(); z++) {
//						   
//						     System.out.println(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC490().get(z).getId()+"|"+
//						    		 leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC490().get(z).getIdPai()+"|"+
//						    		 leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC490().get(z).getCstIcms()+"|"+
//						    		 leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC490().get(z).getCfop()+"|"+
//						    		 leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC490().get(z).getVlOperacao());
//						   
//					   }
//				  }
//			  
//		  }
		
		try {
			
			
			
			
			List<ProdutoNotaXmlProprio> produtosXMLHandler = leitor.getProdutosXMLHandler(pXml);
			
			for(ProdutoNotaXmlProprio pro : produtosXMLHandler){
				
			}

//			for(String cod :  logica.getListaDeProdutos()){
//				
//				double sum = produtosXMLHandler.parallelStream()
//			            .filter(prod -> prod.getCodItem().equals(cod)).mapToDouble(prod -> prod.getVlItem()).sum();
//			
//			
//			        System.out.println(cod +"|"+ sum);
//			}
			
//			Set<String> listaDeProdutos = leitor.getListaDeProdutos();
//			for(String cod :   listaDeProdutos) {		
//				
//				double sum = pNota.getSaiProds().parallelStream()
//			            .filter(prod -> prod.getCodItem().equals(cod)).mapToDouble(prod -> prod.getVlItem()).sum();
//
//			        System.out.println(cod +"|"+ sum);
//			}
			
			
//			for (ProdutoNotaXmlProprio cf : leitor.getProdutosXMLHandler(pXml, logica)) {
//				System.out.println(cf.getCodItem() +"|" +cf.getVlUnit()+"|" +cf.getVlProd()+"|" +cf.getVlDesc()+"|" +cf.getVlItem());
//			}
			
			
			
			
			
//			for (ProdutoCupomFiscalXml cf : leitor.getProdutosXMLHandlerCF(pXml, logica)) {
//				
//				System.out.println(cf.getCodItem() + "|" + cf.getVlUnit()+"|" +cf.getVlProd()+"|" +cf.getVlDesc()+"|" +cf.getVlItem());
//			}
			
			
			
//			for (int i=0; i < leitor.getRegsC860().size();i++) {
//	
//				if (Integer.parseInt(leitor.getRegsC860().get(i).getDocInicial()) >= 25184 && Integer.parseInt(leitor.getRegsC860().get(i).getDocFinal()) <= 25300) {
//	
//					for (ProdutoCupomFiscalXml cf : leitor.getProdutosXMLHandlerCF(pXml, logicaXML)) {
//	
//						if (Integer.parseInt(cf.getNumDoc()) >= Integer.parseInt(leitor.getRegsC860().get(i).getDocInicial())
//								&& Integer.parseInt(cf.getNumDoc()) <= Integer.parseInt(leitor.getRegsC860().get(i).getDocFinal())) {
//	
//							System.out.println(leitor.getRegsC860().get(i).getId() + "|" + cf.getChaveCF() + "|" + cf.getNumItem() + "|"
//									+ cf.getNumDoc() + "|" + cf.getCodItem() + "|" + cf.getQtde() + "|" + cf.getUnd() + "|"
//									+ cf.getCstIcms() + "|" + cf.getVlItem() + "|" + cf.getVlDesc());
//						}
//					}
//	
//				}
//			}
		}catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}

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


