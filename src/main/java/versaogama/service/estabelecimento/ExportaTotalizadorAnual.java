package versaogama.service.estabelecimento;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import versaogama.conexao.Pool;
import versaogama.dao.movprodutosdao.EntradasSaidasDeProdutosDao;
import versaogama.dao.movprodutosdao.InventarioConsultaDAO;
import versaogama.model.system.movprodutos.EntradasSaidasDeProdutos;
import versaogama.model.system.movprodutos.ModelInventarioDeclarado;
import versaogama.model.system.movprodutos.ModelTotalizadoresAnual;

public class ExportaTotalizadorAnual {
	
	
	private Double qtdeMDV = 0.00;
	private Double custoMedioMDV = 0.00;
	private Double custoTotMDV = 0.00;

	private Double qtdeS_EF = 0.00;
	private Double precoMedio = 0.00;
	private Double precoTotal = 0.00;

	private Double qtde_OC = 0.00;
	private Double custoUnit_OC = 0.00;
	private Double vrTotal_OC = 0.00;
	
	private Double qtde_OV = 0.00;
	private Double custoUnit_OV = 0.00;
	private Double vrTotal_OV = 0.00;
	
	private EntradasSaidasDeProdutosDao dao;
	private  InventarioConsultaDAO daoInv;
	
	public ExportaTotalizadorAnual() {
		Pool pool  = new Pool();
		dao = new EntradasSaidasDeProdutosDao(pool);
		daoInv = new InventarioConsultaDAO(pool);
	}
	
	
	public Map<String,ModelInventarioDeclarado> getMpInventarioDeclaradoCodItem(String ano) throws SQLException{
		Map<String,ModelInventarioDeclarado> retorno = new HashMap<String,ModelInventarioDeclarado>();
		for(ModelInventarioDeclarado inv : daoInv.getInventarioDeclarado(ano)){
			retorno.put(inv.getCodItem(), inv);
		}
		return retorno;
	}
	
	public Map<String,ModelInventarioDeclarado> getMpInventarioDeclaradoCodAntItem(String ano) throws SQLException{
		Map<String,ModelInventarioDeclarado> retorno = new HashMap<String,ModelInventarioDeclarado>();
		for(ModelInventarioDeclarado inv : daoInv.getInventarioDeclarado(ano)){
			retorno.put(inv.getCodItemAnt(), inv);
		}
		return retorno;
	}
	
	protected ModelInventarioDeclarado invDeclarado(String codItem,String codAntItem ,String cnpj,String ano) throws SQLException {
		ModelInventarioDeclarado inv = new ModelInventarioDeclarado();
		 ModelInventarioDeclarado inventarioDec = daoInv.getInventarioDec(codItem, codAntItem, cnpj, ano);
		 if(inventarioDec != null) {
			 inv = inventarioDec;
		 }
		 return inv;
	}
	
	public void exportaTotalizadorFinanceiroEstoque(String caminho, String ano, String cnpj) throws SQLException {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(caminho)));
			
			String linha = " ";

			linha = cabecalho();

			writer.write(linha);
			writer.newLine();
			
		   for(EntradasSaidasDeProdutos p : dao.retornaCadastroMovProdutosPorAno(ano)) {
			   
			   ModelTotalizadoresAnual totAnual = new ModelTotalizadoresAnual();
				
				Double qtdeEnt1  = 0.0;
				Double vlTotEnt1 = 0.0;
			    Double vlUnitEnt = 0.0;
				
				Double qtdeSai1  = 0.0;
				Double vlTotSai1 = 0.0;
				Double vlUnitSai = 0.0;
				 
                totAnual.setQtdeEi(invDeclarado(p.getCodItem(),p.getCodAntItem(), cnpj, String.valueOf(Integer.valueOf(ano)-1)).getQtde());
                totAnual.setVrUnitEi(invDeclarado(p.getCodItem(),p.getCodAntItem(), cnpj, String.valueOf(Integer.valueOf(ano)-1)).getVlUnit());
                totAnual.setVrItemEi(invDeclarado(p.getCodItem(),p.getCodAntItem(), cnpj, String.valueOf(Integer.valueOf(ano)-1)).getVlItem());
				System.out.println("INV INI " + totAnual.getQtdeEi()+"|"+totAnual.getVrUnitEi()+"|"+totAnual.getVrItemEi());

			    for(EntradasSaidasDeProdutos  entsai : dao.getSaldoAnualV3(p.getCodItem(),p.getCodAntItem(), ano, cnpj)){
			     
			    	 if(entsai.getOperacao().equals("E")){
			    		 
			    		 qtdeEnt1   +=  entsai.getTotQtdeEnt();
			    		 vlTotEnt1  +=  entsai.getTotVlItemEnt();
			    	 }
			    	 
			    	 if(entsai.getOperacao().equals("S")){
			    		 
			    		 qtdeSai1  +=  entsai.getTotQtdeEnt();
			    		 vlTotSai1 +=  entsai.getTotVlItemEnt();
			    	 }
			    }
		   
                totAnual.setQtdeEnt(qtdeEnt1);						
			    totAnual.setVlItemEnt(vlTotEnt1);
			    vlUnitEnt = totAnual.getVlItemEnt()/totAnual.getQtdeEnt();
			    totAnual.setVlUnitEnt((vlUnitEnt.isNaN() ? 0.0 : vlUnitEnt )); 
			    System.out.println("MOV_ENT |" + totAnual.getQtdeEnt() +"|"+totAnual.getVlUnitEnt()+"|"+totAnual.getVlItemEnt());						    
						   
						  
								
			    totAnual.setQtdeSai( qtdeSai1);						
			    totAnual.setVlItemSai(vlTotSai1);
			    vlUnitSai = totAnual.getVlItemSai()/totAnual.getQtdeSai();
			    totAnual.setVlUnitSai((vlUnitSai.isNaN() ? 0.0 : vlUnitSai));
			    System.out.println("MOV_SAI |" + totAnual.getQtdeSai() +"|"+totAnual.getVlUnitSai()+"|"+totAnual.getVlItemSai());
							
						  
		
			    totAnual.setQtdeEf(invDeclarado(p.getCodItem(),p.getCodAntItem(), cnpj, ano).getQtde());
			    totAnual.setVrUnitEf(invDeclarado(p.getCodItem(),p.getCodAntItem(), cnpj, ano).getVlUnit());
			    totAnual.setVrItemEf(invDeclarado(p.getCodItem(),p.getCodAntItem(), cnpj, ano).getVlItem());
							
				System.out.println("INV_FIN |"+totAnual.getQtdeEf()+"|"+totAnual.getVrUnitEf()+"|"+totAnual.getVrItemEf());
		   
				if(totAnual != null) {
					totAnual.setCodItem(p.getCodItem());
					totAnual.setCodAntItem(p.getCodAntItem());
					totAnual.setDescricao(p.getDescricao());
					totAnual.setUnd(p.getUnd());
					
					if(!formatacaoPlanilha(totAnual).contains(";;0,00;0,00;0,00;;0,00;0,00;0,00;;0,00;0,00;0,00;;0,00;0,00;0,00;;0,00;0,00;0,00;;0,00;0,00;0,00;;0,00;0,0000;0,00;;0,00;0,0000;0,00")){
						System.out.println("Linha retornada : " + formatacaoPlanilha(totAnual));
						linha = formatacaoPlanilha(totAnual);
	
						writer.write(linha);
						writer.newLine();
					}
					
				}
		
		   }

			writer.close();
			System.out.println("Exportado com Sucesso!!!");

		}catch (IOException  e) {
			
		}
		
		
	}
	
	
	private String formatacaoPlanilha(ModelTotalizadoresAnual totAnual) throws SQLException {
		String linha;
		


		//custoTotMDV = vrItemInicialInv + vlItemEntrada;
		  qtdeMDV = calcMDV((totAnual.getQtdeEi()==null?0.0:totAnual.getQtdeEi()), (totAnual.getQtdeEnt()==null?0.0:totAnual.getQtdeEnt()));
		
		  custoTotMDV = custoTotMDV((totAnual.getVrItemEi()==null?0.0:totAnual.getVrItemEi()),(totAnual.getVlItemEnt()==null?0.0:totAnual.getVlItemEnt()));
		
			
		  custoMedioMDV =  custoTotMDV/qtdeMDV;
		  
		//precoTotal = vrItemFinalInv + vlItemSaida;
		
		   qtdeS_EF   =  qtdeS_EF((totAnual.getQtdeEf()==null?0.0:totAnual.getQtdeEf()),(totAnual.getQtdeSai()==null?0.0:totAnual.getQtdeSai()));
		
		   precoTotal = precoTotal((totAnual.getVrItemEf()==null?0.0:totAnual.getVrItemEf()), (totAnual.getVlItemSai()==null?0.0:totAnual.getVlItemSai()));
		
		   precoMedio = precoTotal/qtdeS_EF;
		   
		//  System.out.println("Calc Ent " + qtdeMDV + "|" + custoMedioMDV + "|"  +custoTotMDV 
		//		                  + " Calc Sai " + qtdeS_EF + "|" + precoTotal + "|" + precoMedio);
		// =========== Omissões de Vendas
		

		if (qtdeS_EF >= qtdeMDV) {

			qtde_OC = qtdeS_EF - qtdeMDV;
			custoUnit_OC = custoMedioMDV;
			vrTotal_OC = qtde_OC * custoMedioMDV;

		} else {

			qtde_OC = 0.0;
			custoUnit_OC = 0.0;
			vrTotal_OC = 0.0;

		}

		if (qtdeMDV.equals(0.0)) {

			qtde_OC = qtdeS_EF;
			custoUnit_OC = precoMedio;
			vrTotal_OC = precoTotal;
		}

		if (qtde_OC.equals(0.0)) {

			custoUnit_OC = 0.0;
		}

		// =========== Omissões de Vendas
		
		if (qtdeMDV  >= qtdeS_EF) {

			qtde_OV = qtdeMDV - qtdeS_EF;
			custoUnit_OV = precoMedio;
			vrTotal_OV = qtde_OV * custoUnit_OV;

		} else {

			qtde_OV = 0.0;
			custoUnit_OV = 0.0;
			vrTotal_OV = 0.0;

		}
		
		if (qtdeS_EF.equals(0.0)) {

			qtde_OV = qtdeS_EF;
			custoUnit_OV = precoMedio;
			vrTotal_OV = precoTotal;
		}

		if (qtde_OV.equals(0.0)) {

			custoUnit_OV = 0.0;
		}
		
		String invIniQtde = String.format("%.2f", (totAnual.getQtdeEi()==null?0.0:totAnual.getQtdeEi()));
		String vrUnitIniInv = String.format("%.2f",(totAnual.getVrUnitEi()==null?0.0:totAnual.getVrUnitEi()));
		String vrItemIniInv = String.format("%.2f", (totAnual.getVrItemEi()==null?0.0:totAnual.getVrItemEi()));

		String qtdeItnEnt = String.format("%.2f", (totAnual.getQtdeEnt()==null?0.0:totAnual.getQtdeEnt()));
		String vlUnitItnEnt = String.format("%.2f", (totAnual.getVlUnitEnt()==null?0.0:totAnual.getVlUnitEnt()));
		String vlItnEnt = String.format("%.2f", (totAnual.getVlItemEnt()==null?0.0:totAnual.getVlItemEnt()));

		String qtdeItnSai = String.format("%.2f",(totAnual.getQtdeSai()==null?0.0:totAnual.getQtdeSai()));
		String vlUnitItnSai = String.format("%.2f",(totAnual.getVlUnitSai()==null?0.0:totAnual.getVlUnitSai()));
		String vlItnSai = String.format("%.2f", (totAnual.getVlItemSai()==null?0.0:totAnual.getVlItemSai()));

		String invFinQtde = String.format("%.2f", (totAnual.getQtdeEf()==null?0.0:totAnual.getQtdeEf()));
		String vrUnitFinInv = String.format("%.2f",(totAnual.getVrUnitEf()==null?0.0:totAnual.getVrUnitEf()));
		String vrItemFinInv = String.format("%.2f",(totAnual.getVrItemEf()==null?0.0:totAnual.getVrItemEf()));

		String qtdMDV = String.format("%.2f", qtdeMDV);
		String custMedioMDV = String.format("%.2f", (custoMedioMDV.isNaN()?0.0:custoMedioMDV));
		String custTotMDV = String.format("%.2f", custoTotMDV);

		String qtd_S_EF = String.format("%.2f", qtdeS_EF);
		String precoMedio_S_EF = String.format("%.2f", (precoMedio.isNaN()?0.0:precoMedio));
		String precoTot_S_EF = String.format("%.2f", precoTotal);

		String qtd_OC = String.format("%.2f", qtde_OC);
		String custUnit_OC = String.format("%.4f", custoUnit_OC);
		String vlTot_OC = String.format("%.2f", vrTotal_OC);
		
		String qtd_OV = String.format("%.2f", qtde_OV);
		String custUnit_OV = String.format("%.4f", custoUnit_OV);
		String vlTot_OV = String.format("%.2f", vrTotal_OV);

		linha  = totAnual.getCodItem(); 
		linha += ";";
		linha +=  totAnual.getCodAntItem(); //(getMpProdutos().get(id) == null ? "" : getMpProdutos().get(id).getCodAntItem().toString());
		linha += ";";
		linha += totAnual.getDescricao();  //(getMpProdutos().get(id) == null ? "" :getMpProdutos().get(id).getDescricao());
		linha += ";";
		linha += totAnual.getUnd();
		linha += ";";

		linha += ";";
		linha += invIniQtde.replace(".", ",");
		linha += ";";
		linha += vrUnitIniInv.replace(".", ",");
		linha += ";";
		linha += vrItemIniInv.replace(".", ",");
		linha += ";";

		linha += ";";
		linha += qtdeItnEnt.replace(".", ",");
		linha += ";";
		linha += vlUnitItnEnt.replace(".", ",");
		linha += ";";
		linha += vlItnEnt.replace(".", ",");
		linha += ";";

		linha += ";";
		linha += qtdeItnSai.replace(".", ",");
		linha += ";";
		linha += vlUnitItnSai.replace(".", ",");
		linha += ";";
		linha += vlItnSai.replace(".", ",");
		linha += ";";

		linha += ";";
		linha += invFinQtde.replace(".", ",");
		linha += ";";
		linha += vrUnitFinInv.replace(".", ",");
		linha += ";";
		linha += vrItemFinInv.replace(".", ",");
		linha += ";";

		linha += ";";
		linha += qtdMDV.replace(".", ",");
		linha += ";";
		linha += custMedioMDV.replace(".", ",");
		linha += ";";
		linha += custTotMDV.replace(".", ",");
		linha += ";";

		linha += ";";
		linha += qtd_S_EF.replace(".", ",");
		linha += ";";
		linha += precoMedio_S_EF.replace(".", ",");
		linha += ";";
		linha += precoTot_S_EF.replace(".", ",");
		linha += ";";

		linha += ";";
		linha += qtd_OC.replace(".", ",");
		linha += ";";
		linha += custUnit_OC.replace(".", ",");
		linha += ";";
		linha += vlTot_OC.replace(".", ",");
		linha += ";";
		
		linha += ";";
		linha += qtd_OV.replace(".", ",");
		linha += ";";
		linha += custUnit_OV.replace(".", ",");
		linha += ";";
		linha += vlTot_OV.replace(".", ",");
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
		linha += "UND";
		linha += ";";

		linha += ";";
		linha += "QTDE_EI";
		linha += ";";
		linha += "VR_UNIT_EI";
		linha += ";";
		linha += "VR_ITEM_EI";
		linha += ";";

		linha += ";";
		linha += "QTDE_ENT";
		linha += ";";
		linha += "VL_UNIT_ENT";
		linha += ";";
		linha += "VL_ITEM_ENT";
		linha += ";";

		linha += ";";
		linha += "QTDE_SAI";
		linha += ";";
		linha += "VL_UNIT_SAI";
		linha += ";";
		linha += "VL_ITEM_SAI";
		linha += ";";

		linha += ";";
		linha += "QTDE_EF";
		linha += ";";
		linha += "VR_UNIT_EF";
		linha += ";";
		linha += "VR_ITEM_EF";
		linha += ";";

		linha += ";";
		linha += "QTDE_MDV";
		linha += ";";
		linha += "CUSTO_MEDIO";
		linha += ";";
		linha += "CUSTO TOTAL";
		linha += ";";

		linha += ";";
		linha += "QTDE_(S + EF)";
		linha += ";";
		linha += "PREÇO_MEDIO";
		linha += ";";
		linha += "PREÇO TOTAL";
		linha += ";";

		linha += ";";
		linha += "QTDE_OC";
		linha += ";";
		linha += "CUSTO_UNIT";
		linha += ";";
		linha += "VR_TOTAL";
		linha += ";";
		
		linha += ";";
		linha += "QTDE_OV";
		linha += ";";
		linha += "CUSTO_UNIT";
		linha += ";";
		linha += "VR_TOTAL";
		
		return linha;
	}
	
	private Double calcMDV(Double qteInicialInv , Double qteItemEntrada) {
		
		if(qteInicialInv==null) {
			qteInicialInv = 0.0;
		}
		
		if(qteItemEntrada==null){
			qteItemEntrada =0.0;
		}
		return qteInicialInv + qteItemEntrada;
	}
	
	private Double custoTotMDV(Double vrItemInicialInv , Double vlItemEntrada) {
		if(vrItemInicialInv == null) {
			vrItemInicialInv = 0.0;
		}
		
		if(vlItemEntrada == null) {
			vlItemEntrada = 0.0;
		}
		return vrItemInicialInv + vlItemEntrada;
	}
	
	private Double qtdeS_EF(Double qteFinalInv ,Double qteItemSaida) {
		if(qteFinalInv == null) {
			qteFinalInv = 0.0;
		}
		
		if(qteItemSaida == null){
			qteItemSaida = 0.0;
		}
		return qteFinalInv+qteItemSaida;
	}
	
	private Double precoTotal(Double vrItemFinalInv ,Double vlItemSaida) {
		if(vrItemFinalInv == null){
			vrItemFinalInv = 0.0;
		}
		
		if(vlItemSaida == null){
			vlItemSaida = 0.0;
		}
		return vrItemFinalInv + vlItemSaida;
	}

}
