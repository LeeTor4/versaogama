package versaogama.service.estabelecimento;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.dao.movprodutosdao.EntradasSaidasDeProdutosDao;
import versaogama.model.system.movprodutos.EntradasSaidasDeProdutos;
import versaogama.model.system.movprodutos.ModelInventarioDeclarado;
import versaogama.model.system.movprodutos.ModeloTotalizadoresMensais;


public class ExportaQuantitativoEstoque {
	
	
	private EntradasSaidasDeProdutosDao dao;
	
	public ExportaQuantitativoEstoque() {
		Pool pool = new Pool();
		dao = new EntradasSaidasDeProdutosDao(pool);
	}
	
   public List<ModelInventarioDeclarado> getMpInvDec(String codItem,String codAntItem,String cnpj,String ano) throws SQLException{	
	   List<ModelInventarioDeclarado> retorno = new ArrayList<>();	 	
		 for(ModelInventarioDeclarado obj :  dao.getInventarioDeclarado(codItem, codAntItem,cnpj, ano)){	
			 
			 retorno.add(obj);						
		 }
	   return retorno;
   }
   
	private Double invDeclarado(String codItem,String codAntItem ,String cnpj,String ano) throws SQLException {
		 Double qteInvDec = 0.0;
		 if(!getMpInvDec(codItem,codAntItem ,cnpj,ano).isEmpty()) {
			 for(ModelInventarioDeclarado obj :  getMpInvDec(codItem,codAntItem ,cnpj,ano)){				 	 
					 qteInvDec = obj.getQtde();		
					
			 }
		 }
		 
		 return qteInvDec;
	}
	
	   
	public void exportaControleQuantitativos(String file, String ano,String cnpj) throws SQLException {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)));
			String linha = " ";

			linha = cabecalho();

			writer.write(linha);
			writer.newLine();
            for(EntradasSaidasDeProdutos p : dao.retornaCadastroMovProdutosPorAno(ano)) {
            	Double sIniEnt = 0.0;
            	Double sIniSai = 0.0;
            	Double saldoIni = 0.0;
            	 ModeloTotalizadoresMensais totaisMensais = new ModeloTotalizadoresMensais();
            	 
                  totaisMensais.setQteInvDec(invDeclarado(p.getCodItem(), p.getCodAntItem(), cnpj, String.valueOf(Integer.valueOf(ano)-1)));	
                
                 if(dao.getSaldoInicialEnt(p.getCodItem(),p.getCodAntItem(), String.valueOf(Integer.valueOf(ano)), cnpj).getSaldo() != null) {
                	 sIniEnt = dao.getSaldoInicialEnt(p.getCodItem(),p.getCodAntItem(), String.valueOf(Integer.valueOf(ano)), cnpj).getSaldo();
                 }else {
                	 sIniEnt = 0.0;
                 }
                 
                 if(dao.getSaldoInicialSai(p.getCodItem(),p.getCodAntItem(), String.valueOf(Integer.valueOf(ano)), cnpj).getSaldo() != null) {
                	 sIniSai = dao.getSaldoInicialSai(p.getCodItem(),p.getCodAntItem(), String.valueOf(Integer.valueOf(ano)), cnpj).getSaldo();
                 }else {
                	 sIniSai = 0.0;
                 }

            	 saldoIni = sIniEnt - sIniSai;
            	 totaisMensais.setQteIniInv(saldoIni);
            	 
            	 for(EntradasSaidasDeProdutos saldos :  dao.getSaldoItensJan(p.getCodItem(),p.getCodAntItem(), ano,"1",cnpj)){
         			    totaisMensais.setQtdeEntJan(saldos.getTotQtdeEnt());
						totaisMensais.setVrEntJan(saldos.getTotVlItemEnt());
						totaisMensais.setQtdeSaiJan(saldos.getTotQtdeSai());
						totaisMensais.setVrSaiJan(saldos.getTotVlItemSai());
         	     }
            	 for(EntradasSaidasDeProdutos saldos :  dao.getSaldoItensFev(p.getCodItem(),p.getCodAntItem(), ano,"2",cnpj)){
         			    totaisMensais.setQtdeEntFev(saldos.getTotQtdeEnt());
						totaisMensais.setVrEntFev(saldos.getTotVlItemEnt());
						totaisMensais.setQtdeSaiFev(saldos.getTotQtdeSai());
						totaisMensais.setVrSaiFev(saldos.getTotVlItemSai());
         	     }
            	 for(EntradasSaidasDeProdutos saldos :  dao.getSaldoItensMar(p.getCodItem(),p.getCodAntItem(), ano,"3",cnpj)){
         			    totaisMensais.setQtdeEntMar(saldos.getTotQtdeEnt());
						totaisMensais.setVrEntMar(saldos.getTotVlItemEnt());
						totaisMensais.setQtdeSaiMar(saldos.getTotQtdeSai());
						totaisMensais.setVrSaiMar(saldos.getTotVlItemSai());
         	     }
            	 for(EntradasSaidasDeProdutos saldos :  dao.getSaldoItensAbr(p.getCodItem(),p.getCodAntItem(), ano,"4",cnpj)){
         			    totaisMensais.setQtdeEntAbr(saldos.getTotQtdeEnt());
						totaisMensais.setVrEntAbr(saldos.getTotVlItemEnt());
						totaisMensais.setQtdeSaiAbr(saldos.getTotQtdeSai());
						totaisMensais.setVrSaiAbr(saldos.getTotVlItemSai());
         	     }
            	 for(EntradasSaidasDeProdutos saldos :  dao.getSaldoItensMai(p.getCodItem(),p.getCodAntItem(), ano,"5",cnpj)){
         			    totaisMensais.setQtdeEntMai(saldos.getTotQtdeEnt());
						totaisMensais.setVrEntMai(saldos.getTotVlItemEnt());
						totaisMensais.setQtdeSaiMai(saldos.getTotQtdeSai());
						totaisMensais.setVrSaiMai(saldos.getTotVlItemSai());
         	     }
            	 for(EntradasSaidasDeProdutos saldos :  dao.getSaldoItensJun(p.getCodItem(),p.getCodAntItem(), ano,"6",cnpj)){
         			    totaisMensais.setQtdeEntJun(saldos.getTotQtdeEnt());
						totaisMensais.setVrEntJun(saldos.getTotVlItemEnt());
						totaisMensais.setQtdeSaiJun(saldos.getTotQtdeSai());
						totaisMensais.setVrSaiJun(saldos.getTotVlItemSai());
         	     }
            	 for(EntradasSaidasDeProdutos saldos :  dao.getSaldoItensJul(p.getCodItem(),p.getCodAntItem(), ano,"7",cnpj)){
         			    totaisMensais.setQtdeEntJul(saldos.getTotQtdeEnt());
						totaisMensais.setVrEntJul(saldos.getTotVlItemEnt());
						totaisMensais.setQtdeSaiJul(saldos.getTotQtdeSai());
						totaisMensais.setVrSaiJul(saldos.getTotVlItemSai());
         	     }
            	 for(EntradasSaidasDeProdutos saldos :  dao.getSaldoItensAgo(p.getCodItem(),p.getCodAntItem(), ano,"8",cnpj)){
         			    totaisMensais.setQtdeEntAgo(saldos.getTotQtdeEnt());
						totaisMensais.setVrEntAgo(saldos.getTotVlItemEnt());
						totaisMensais.setQtdeSaiAgo(saldos.getTotQtdeSai());
						totaisMensais.setVrSaiAgo(saldos.getTotVlItemSai());
         	     }
            	 for(EntradasSaidasDeProdutos saldos :  dao.getSaldoItensSet(p.getCodItem(),p.getCodAntItem(), ano,"9",cnpj)){
         			    totaisMensais.setQtdeEntSet(saldos.getTotQtdeEnt());
						totaisMensais.setVrEntSet(saldos.getTotVlItemEnt());
						totaisMensais.setQtdeSaiSet(saldos.getTotQtdeSai());
						totaisMensais.setVrSaiSet(saldos.getTotVlItemSai());
         	     }
            	 for(EntradasSaidasDeProdutos saldos :  dao.getSaldoItensOut(p.getCodItem(),p.getCodAntItem(), ano,"10",cnpj)){
         			    totaisMensais.setQtdeEntOut(saldos.getTotQtdeEnt());
						totaisMensais.setVrEntOut(saldos.getTotVlItemEnt());
						totaisMensais.setQtdeSaiOut(saldos.getTotQtdeSai());
						totaisMensais.setVrSaiOut(saldos.getTotVlItemSai());
         	     }
            	 for(EntradasSaidasDeProdutos saldos :  dao.getSaldoItensNov(p.getCodItem(),p.getCodAntItem(), ano,"11",cnpj)){
            			totaisMensais.setQtdeEntNov(saldos.getTotQtdeEnt());
						totaisMensais.setVrEntNov(saldos.getTotVlItemEnt());
						totaisMensais.setQtdeSaiNov(saldos.getTotQtdeSai());
						totaisMensais.setVrSaiNov(saldos.getTotVlItemSai());
            	 }
            	 for(EntradasSaidasDeProdutos saldos :  dao.getSaldoItensDez(p.getCodItem(),p.getCodAntItem(), ano,"12",cnpj)){
         			    totaisMensais.setQtdeEntDez(saldos.getTotQtdeEnt());
						totaisMensais.setVrEntDez(saldos.getTotVlItemEnt());
						totaisMensais.setQtdeSaiDez(saldos.getTotQtdeSai());
						totaisMensais.setVrSaiDez(saldos.getTotVlItemSai());
         	     }
            	 
            	 
            	 
            	 

    	         if(totaisMensais != null) {
    	        	 
    	        	 totaisMensais.setCodItem(p.getCodItem());
    	        	 totaisMensais.setCodAntItem(p.getCodAntItem());
    	        	 totaisMensais.setDescricao(p.getDescricao());
    	        	 totaisMensais.setUnidMedida(p.getUnd());
    	        	 
    	        	// System.out.println("Saldo Inicial "   +p.getCogigo()+"|"+p.getCodAntItem() + "|"+saldoInicial(p.getCogigo(), p.getCodAntItem(), cnpj, String.valueOf(Integer.valueOf(ano)-1)));
                    // System.out.println("Saldo Declarado "+String.valueOf(Integer.valueOf(ano)-1)+"| " +p.getCogigo()+"|"+p.getCodAntItem() + "|"+invDeclarado(p.getCogigo(), p.getCodAntItem(), cnpj, String.valueOf(Integer.valueOf(ano)-1)));
                      
    	        	 
    	        	 if(!formatacaoPlanilha(totaisMensais).contains(";0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00;0,00")) {

    	        			 System.out.println("linha " + formatacaoPlanilha(totaisMensais));
    	        			 linha = formatacaoPlanilha(totaisMensais);
    				         writer.write(linha);
    				         writer.newLine(); 
    	        	 }
    	        	
    	         }
         

        	}  	
		    writer.close();	
				
	        System.out.println("Exportado com Sucesso!!!");
			
		} catch (IOException e) {
			
		}
		
	}
	
	
	private String formatacaoPlanilha(ModeloTotalizadoresMensais totaisMensais) {
		String linha = "";
		
		//System.out.println(totaisMensais.getCodItem()+"|"+totaisMensais.getCodAntItem()+"|"+totaisMensais.getMes()+"|"+totaisMensais.getTotQtde());
          
        Double saldoJan=0.0;
		Double saldoFev=0.0;
		Double saldoMar=0.0;
		Double saldoAbr=0.0;
		Double saldoMai=0.0;
		Double saldoJun=0.0;
		Double saldoJul=0.0;
		Double saldoAgo=0.0;
		Double saldoSet=0.0;
		Double saldoOut=0.0;		
		Double saldoNov=0.0;
		Double saldoDez=0.0;
		
		
		saldoJan = totaisMensais.getQteIniInv() + totaisMensais.getQtdeEntJan() - totaisMensais.getQtdeSaiJan();
		
		saldoFev = saldoJan + totaisMensais.getQtdeEntFev() - totaisMensais.getQtdeSaiFev();
		saldoMar = saldoFev + totaisMensais.getQtdeEntMar() - totaisMensais.getQtdeSaiMar();
		saldoAbr = saldoMar + totaisMensais.getQtdeEntAbr() - totaisMensais.getQtdeSaiAbr();
		saldoMai = saldoAbr + totaisMensais.getQtdeEntMai() - totaisMensais.getQtdeSaiMai();
		saldoJun = saldoMai + totaisMensais.getQtdeEntJun() - totaisMensais.getQtdeSaiJun();
		saldoJul = saldoJun + totaisMensais.getQtdeEntJul() - totaisMensais.getQtdeSaiJul();
		saldoAgo = saldoJul + totaisMensais.getQtdeEntAgo() - totaisMensais.getQtdeSaiAgo();
		saldoSet = saldoAgo + totaisMensais.getQtdeEntSet() - totaisMensais.getQtdeSaiSet();
		saldoOut = saldoSet + totaisMensais.getQtdeEntOut() - totaisMensais.getQtdeSaiOut();
		
		saldoNov = saldoOut + totaisMensais.getQtdeEntNov() - totaisMensais.getQtdeSaiNov();
		saldoDez = saldoNov + totaisMensais.getQtdeEntDez() - totaisMensais.getQtdeSaiDez();

		
		
		 String invIni = String.format("%.2f", totaisMensais.getQteIniInv()); // Implementar depois		 
	 	 String invDec = String.format("%.2f", totaisMensais.getQteInvDec());	
		
		
		String qteEntJan = String.format("%.2f", totaisMensais.getQtdeEntJan());
		String vlEntJan = String.format("%.2f", totaisMensais.getVrEntJan());
		String qteSaiJan = String.format("%.2f", totaisMensais.getQtdeSaiJan());
		String vlSaiJan = String.format("%.2f", totaisMensais.getVrSaiJan());
		String saldJan = String.format("%.2f", saldoJan);
		
		String EntQtdeFev = String.format("%.2f", totaisMensais.getQtdeEntFev());
		String EntVlFev = String.format("%.2f", totaisMensais.getVrEntFev());
		String SaiQtdeFev = String.format("%.2f", totaisMensais.getQtdeSaiFev());
		String SaiVlFev = String.format("%.2f", totaisMensais.getVrSaiFev());
		String saldFev = String.format("%.2f", saldoFev);

		String EntQtdeMar = String.format("%.2f", totaisMensais.getQtdeEntMar());
		String EntVlMar = String.format("%.2f", totaisMensais.getVrEntMar());
		String SaiQtdeMar = String.format("%.2f", totaisMensais.getQtdeSaiMar());
		String SaiVlMar = String.format("%.2f",  totaisMensais.getVrSaiMar());
		String saldMar = String.format("%.2f", saldoMar);

		String EntQtdeAbr = String.format("%.2f", totaisMensais.getQtdeEntAbr());
		String EntVlAbr = String.format("%.2f", totaisMensais.getVrEntAbr());
		String SaiQtdeAbr = String.format("%.2f", totaisMensais.getQtdeSaiAbr());
		String SaiVLAbr = String.format("%.2f", totaisMensais.getVrSaiAbr());
		String saldAbr = String.format("%.2f", saldoAbr);

		String EntQtdeMai = String.format("%.2f", totaisMensais.getQtdeEntMai());
		String EntVlMai = String.format("%.2f", totaisMensais.getVrEntMai());
		String SaiQtdeMai = String.format("%.2f", totaisMensais.getQtdeSaiMai());
		String SaiVlMai = String.format("%.2f", totaisMensais.getVrSaiMai());
		String saldMai = String.format("%.2f", saldoMai);

		String EntQtdeJun = String.format("%.2f", totaisMensais.getQtdeEntJun());
		String EntVlJun = String.format("%.2f", totaisMensais.getVrEntJun());
		String SaiQtdeJun = String.format("%.2f", totaisMensais.getQtdeSaiJun());
		String SaiVlJun = String.format("%.2f", totaisMensais.getVrSaiJun());
		String saldJun = String.format("%.2f", saldoJun);

		String EntQtdeJul = String.format("%.2f",  totaisMensais.getQtdeEntJul());
		String EntVlJul = String.format("%.2f", totaisMensais.getVrEntJul());
		String SaiQtdeJul = String.format("%.2f",  totaisMensais.getQtdeSaiJul());
		String SaiVlJul = String.format("%.2f", totaisMensais.getVrSaiJul());
		String saldJul = String.format("%.2f", saldoJul);

		String EntQtdeAgo = String.format("%.2f",  totaisMensais.getQtdeEntAgo());
		String EntVlAgo = String.format("%.2f",  totaisMensais.getVrEntAgo());
		String SaiQtdeAgo = String.format("%.2f", totaisMensais.getQtdeSaiAgo());
		String SaiVlAgo = String.format("%.2f",  totaisMensais.getVrSaiAgo());
		String saldAgo = String.format("%.2f", saldoAgo);

		String EntQtdeSet = String.format("%.2f", totaisMensais.getQtdeEntSet());
		String EntVlSet = String.format("%.2f",  totaisMensais.getVrEntSet());
		String SaiQtdeSet = String.format("%.2f", totaisMensais.getQtdeSaiSet());
		String SaiVlSet = String.format("%.2f",  totaisMensais.getVrSaiSet());
		String saldSet = String.format("%.2f", saldoSet);

		String EntQtdeOut = String.format("%.2f", totaisMensais.getQtdeEntOut());
		String EntVlOut = String.format("%.2f", totaisMensais.getVrEntOut());
		String SaiQtdeOut = String.format("%.2f", totaisMensais.getQtdeSaiOut());
		String SaiVlOut = String.format("%.2f", totaisMensais.getVrSaiOut());
		String saldOut = String.format("%.2f", saldoOut);

		String EntQtdeNov = String.format("%.2f", totaisMensais.getQtdeEntNov());
		String EntVlNov = String.format("%.2f", totaisMensais.getVrEntNov());
		String SaiQtdeNov = String.format("%.2f", totaisMensais.getQtdeSaiNov());
		String SaiVlNov = String.format("%.2f", totaisMensais.getVrSaiNov());
		String saldNov = String.format("%.2f", saldoNov);

		String EntQtdeDez = String.format("%.2f", totaisMensais.getQtdeEntDez());
		String EntVlDez = String.format("%.2f", totaisMensais.getVrEntDez());
		String SaiQtdeDez = String.format("%.2f",  totaisMensais.getQtdeSaiDez());
		String SaiVlDez = String.format("%.2f", totaisMensais.getVrSaiDez());
		String saldDez = String.format("%.2f", saldoDez);

		linha  = totaisMensais.getCodItem();
		linha += ";";
		linha += totaisMensais.getCodAntItem();
		linha += ";";
		linha += totaisMensais.getDescricao();
		linha += ";";
		linha += invIni.replace(".", ",");
		linha += ";";
		linha += invDec.replace(".", ",");
		linha += ";";
		
		
		linha += qteEntJan.replace(".", ",");
		linha += ";";
		linha += vlEntJan.replace(".", ",");
		linha += ";";
		linha += qteSaiJan.replace(".", ",");
		linha += ";";
		linha += vlSaiJan.replace(".", ",");
		linha += ";";
		linha += saldJan.replace(".", ",");
		
		
		linha += ";";
		linha += EntQtdeFev.replace(".", ",");
		linha += ";";
		linha += EntVlFev.replace(".", ",");
		linha += ";";
		linha += SaiQtdeFev.replace(".", ",");
		linha += ";";
		linha += SaiVlFev.replace(".", ",");
		linha += ";";
		linha += saldFev.replace(".", ",");

		linha += ";";
		linha += EntQtdeMar.replace(".", ",");
		linha += ";";
		linha += EntVlMar.replace(".", ",");
		linha += ";";
		linha += SaiQtdeMar.replace(".", ",");
		linha += ";";
		linha += SaiVlMar.replace(".", ",");
		linha += ";";
		linha += saldMar.replace(".", ",");

		linha += ";";
		linha += EntQtdeAbr.replace(".", ",");
		linha += ";";
		linha += EntVlAbr.replace(".", ",");
		linha += ";";
		linha += SaiQtdeAbr.replace(".", ",");
		linha += ";";
		linha += SaiVLAbr.replace(".", ",");
		linha += ";";
		linha += saldAbr.replace(".", ",");

		linha += ";";
		linha += EntQtdeMai.replace(".", ",");
		linha += ";";
		linha += EntVlMai.replace(".", ",");
		linha += ";";
		linha += SaiQtdeMai.replace(".", ",");
		linha += ";";
		linha += SaiVlMai.replace(".", ",");
		linha += ";";
		linha += saldMai.replace(".", ",");

		linha += ";";
		linha += EntQtdeJun.replace(".", ",");
		linha += ";";
		linha += EntVlJun.replace(".", ",");
		linha += ";";
		linha += SaiQtdeJun.replace(".", ",");
		linha += ";";
		linha += SaiVlJun.replace(".", ",");
		linha += ";";
		linha += saldJun.replace(".", ",");

		linha += ";";
		linha += EntQtdeJul.replace(".", ",");
		linha += ";";
		linha += EntVlJul.replace(".", ",");
		linha += ";";
		linha += SaiQtdeJul.replace(".", ",");
		linha += ";";
		linha += SaiVlJul.replace(".", ",");
		linha += ";";
		linha += saldJul.replace(".", ",");

		linha += ";";
		linha += EntQtdeAgo.replace(".", ",");
		linha += ";";
		linha += EntVlAgo.replace(".", ",");
		linha += ";";
		linha += SaiQtdeAgo.replace(".", ",");
		linha += ";";
		linha += SaiVlAgo.replace(".", ",");
		linha += ";";
		linha += saldAgo.replace(".", ",");

		linha += ";";
		linha += EntQtdeSet.replace(".", ",");
		linha += ";";
		linha += EntVlSet.replace(".", ",");
		linha += ";";
		linha += SaiQtdeSet.replace(".", ",");
		linha += ";";
		linha += SaiVlSet.replace(".", ",");
		linha += ";";
		linha += saldSet.replace(".", ",");

		linha += ";";
		linha += EntQtdeOut.replace(".", ",");
		linha += ";";
		linha += EntVlOut.replace(".", ",");
		linha += ";";
		linha += SaiQtdeOut.replace(".", ",");
		linha += ";";
		linha += SaiVlOut.replace(".", ",");
		linha += ";";
		linha += saldOut.replace(".", ",");

		linha += ";";
        linha += EntQtdeNov.replace(".", ",");
		linha += ";";
		linha += EntVlNov.replace(".", ",");
		linha += ";";
		linha += SaiQtdeNov.replace(".", ",");
		linha += ";";
		linha += SaiVlNov.replace(".", ",");
		linha += ";";
		linha += saldNov.replace(".", ",");

		linha += ";";
		linha += EntQtdeDez.replace(".", ",");
		linha += ";";
		linha += EntVlDez.replace(".", ",");
		linha += ";";
		linha += SaiQtdeDez.replace(".", ",");
		linha += ";";
		linha += SaiVlDez.replace(".", ",");
		linha += ";";
		linha += saldDez.replace(".", ",");
		
		 return linha;
	}

	private String cabecalho() {
		String linha;
		linha  = "CÓDIGO ITEM";
		linha += ";";
		linha += "CÓDIGO ANTERIOR ITEM";
		linha += ";";
		linha += "DESCRIÇÃO";
		linha += ";";
		linha += "QTDE INV INICIAL/APURADO";
		linha += ";";
		linha += "QTDE INV DECLARADO";
		linha += ";";

		linha += "QTDE_ENTRADA_JAN";
		linha += ";";
		linha += "VALOR_ENTRADA_JAN";
		linha += ";";
		linha += "QTDE_SAIDA_JAN";
		linha += ";";
		linha += "VALOR_SAIDA_JAN";
		linha += ";";
		linha += "SALDO_QTDE_JAN";

		linha += ";";
		linha += "QTDE_ENTRADA_FEV";
		linha += ";";
		linha += "VALOR_ENTRADA_FEV";
		linha += ";";
		linha += "QTDE_SAIDA_FEV";
		linha += ";";
		linha += "VALOR_SAIDA_FEV";
		linha += ";";
		linha += "SALDO_QTDE_FEV";

		linha += ";";
		linha += "QTDE_ENTRADA_MAR";
		linha += ";";
		linha += "VALOR_ENTRADA_MAR";
		linha += ";";
		linha += "QTDE_SAIDA_MAR";
		linha += ";";
		linha += "VALOR_SAIDA_MAR";
		linha += ";";
		linha += "SALDO_QTDE_MAR";

		linha += ";";
		linha += "QTDE_ENTRADA_ABR";
		linha += ";";
		linha += "VALOR_ENTRADA_ABR";
		linha += ";";
		linha += "QTDE_SAIDA_ABR";
		linha += ";";
		linha += "VALOR_SAIDA_ABR";
		linha += ";";
		linha += "SALDO_QTDE_ABR";

		linha += ";";
		linha += "QTDE_ENTRADA_MAI";
		linha += ";";
		linha += "VALOR_ENTRADA_MAI";
		linha += ";";
		linha += "QTDE_SAIDA_MAI";
		linha += ";";
		linha += "VALOR_SAIDA_MAI";
		linha += ";";
		linha += "SALDO_QTDE_MAI";

		linha += ";";
		linha += "QTDE_ENTRADA_JUN";
		linha += ";";
		linha += "VALOR_ENTRADA_JUN";
		linha += ";";
		linha += "QTDE_SAIDA_JUN";
		linha += ";";
		linha += "VALOR_SAIDA_JUN";
		linha += ";";
		linha += "SALDO_QTDE_JUN";

		linha += ";";
		linha += "QTDE_ENTRADA_JUL";
		linha += ";";
		linha += "VALOR_ENTRADA_JUL";
		linha += ";";
		linha += "QTDE_SAIDA_JUL";
		linha += ";";
		linha += "VALOR_SAIDA_JUL";
		linha += ";";
		linha += "SALDO_QTDE_JUL";

		linha += ";";
		linha += "QTDE_ENTRADA_AGO";
		linha += ";";
		linha += "VALOR_ENTRADA_AGO";
		linha += ";";
		linha += "QTDE_SAIDA_AGO";
		linha += ";";
		linha += "VALOR_SAIDA_AGO";
		linha += ";";
		linha += "SALDO_QTDE_AGO";

		linha += ";";
		linha += "QTDE_ENTRADA_SET";
		linha += ";";
		linha += "VALOR_ENTRADA_SET";
		linha += ";";
		linha += "QTDE_SAIDA_SET";
		linha += ";";
		linha += "VALOR_SAIDA_SET";
		linha += ";";
		linha += "SALDO_QTDE_SET";

		linha += ";";
		linha += "QTDE_ENTRADA_OUT";
		linha += ";";
		linha += "VALOR_ENTRADA_OUT";
		linha += ";";
		linha += "QTDE_SAIDA_OUT";
		linha += ";";
		linha += "VALOR_SAIDA_OUT";
		linha += ";";
		linha += "SALDO_QTDE_OUT";

		linha += ";";
		linha += "QTDE_ENTRADA_NOV";
		linha += ";";
		linha += "VALOR_ENTRADA_NOV";
		linha += ";";
		linha += "QTDE_SAIDA_NOV";
		linha += ";";
		linha += "VALOR_SAIDA_NOV";
		linha += ";";
		linha += "SALDO_QTDE_NOV";

		linha += ";";
		linha += "QTDE_ENTRADA_DEZ";
		linha += ";";
		linha += "VALOR_ENTRADA_DEZ";
		linha += ";";
		linha += "QTDE_SAIDA_DEZ";
		linha += ";";
		linha += "VALOR_SAIDA_DEZ";
		linha += ";";
		linha += "SALDO_QTDE_DEZ";
		return linha;
	}

}
