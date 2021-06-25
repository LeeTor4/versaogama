package versaogama.service.estabelecimento;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import versaogama.conexao.Pool;
import versaogama.dao.movprodutosdao.EntradasSaidasDeProdutosDao;
import versaogama.dao.movprodutosdao.ModelHistoricoItensDao;
import versaogama.model.system.movprodutos.ModelHistoricoItens;
import versaogama.model.system.movprodutos.ModelInventarioDeclarado;
import versaogama.model.system.movprodutos.ModelPlanilhaHistoricoItem;
import versaogama.model.system.movprodutos.ModelProdutoDePara;
import versaogama.util.UtilsEConverters;

public class ExportaHisoricoItem {

	private ModelHistoricoItensDao dao;
	private EntradasSaidasDeProdutosDao daoInv;
	
	
 	public ExportaHisoricoItem() {
 		Pool pool  = new Pool();
 		dao    = new ModelHistoricoItensDao(pool); 
 		daoInv = new EntradasSaidasDeProdutosDao(pool); 
	}
 	
 	
 	public ModelInventarioDeclarado getInvInicial(String codItem,String codAntItem,String cnpj,String ano) throws SQLException {
		   return daoInv.getInventarioDec(codItem, codAntItem, cnpj, ano);
    }
 	public List<ModelHistoricoItens> getListHistoricoItem(String cnpj,String codItem,String codAntItem,String ano) throws SQLException{
		return dao.getHistoricoItens(cnpj,codItem,codAntItem, ano);
	}
 	
 	
 	public void exportarHistoricoItem(String file, String ano, String cnpj,String codItem,String codAntItem) throws SQLException {
 		
 		try {
 			
 			ModelPlanilhaHistoricoItem hist = new ModelPlanilhaHistoricoItem();
 			BufferedWriter writer = new BufferedWriter(new FileWriter(file.concat("FichaEstoque").concat(codItem).concat(".csv")));
			String linha = " ";

			linha = cabecalho();

			writer.write(linha);
			writer.newLine();
			Double qtdeSaldo = 0.0;
			 
			 ModelInventarioDeclarado invInicial = getInvInicial(codItem, codAntItem, cnpj, String.valueOf(Integer.valueOf(ano)-1));
			 if(invInicial != null) {
				
				 hist.setData(invInicial.getDtInv());

			     hist.setOperacao("INV");
			    
			     qtdeSaldo  = invInicial.getQtde();
			    
			     hist.setSaldoQtd(qtdeSaldo);
			     hist.setSaldoVrUnit(invInicial.getVlUnit());
			     hist.setSaldoVrTotal(invInicial.getVlItem());
			     
			     linha = formatacaoPlanilha(hist);
		         writer.write(linha);
		         writer.newLine();
			 }
 
			 
			 for(ModelHistoricoItens obj : getListHistoricoItem(cnpj, codItem, codAntItem, ano)){
				hist.setData(obj.getDtDoc());
				hist.setOperacao(obj.getOperacao());
				hist.setEcfCx(obj.getEcfCx());
				hist.setCFOP(obj.getCfop());
				

				
				if(obj.getOperacao().equals("E")){
					hist.setEntQtd(obj.getQtde());
					hist.setEntVrUnit(obj.getVlUnit());
					hist.setEntVrTotal(obj.getVlBruto());
					
					qtdeSaldo += obj.getQtde();
					hist.setSaldoVrUnit(obj.getVlUnit());
					hist.setSaldoQtd(qtdeSaldo);
					hist.setSaldoVrTotal(hist.getSaldoQtd()*hist.getSaldoVrUnit());
				}else
				if(obj.getOperacao().equals("S")){
					hist.setSaidaQtd(obj.getQtde());
					hist.setSaidaVrUnit(obj.getVlUnit());
					hist.setSaidaVrTotal(obj.getVlBruto());
					
					qtdeSaldo += obj.getQtde()*(-1);
					hist.setSaldoVrUnit(obj.getVlUnit());
					hist.setSaldoQtd(qtdeSaldo);
					hist.setSaldoVrTotal(hist.getSaldoQtd()*hist.getSaldoVrUnit());
				}
				
				
				hist.setModelo(obj.getCodMod());
				hist.setDecricaoItem(obj.getDescricao());
				hist.setNumero(obj.getNumDoc());
				hist.setCNPJCPF((obj.getCpfCnpj().isEmpty() ? "" : obj.getCpfCnpj().concat("_")));
				hist.setParticipante(obj.getNome());
				hist.setChaveDoc((obj.getChaveDoc().isEmpty() ? "" :obj.getChaveDoc().concat(".xml")));
				
				if(obj != null){
 
					 linha = formatacaoPlanilha(hist);
			         writer.write(linha);
			         writer.newLine(); 
				}
			}
						
			writer.close();	
				
			System.out.println("Histórico Exportado com Sucesso!!!");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
 		
 	}
 	
	private String formatacaoPlanilha(ModelPlanilhaHistoricoItem obj) {
		String linha = "";
		
		
		linha  = UtilsEConverters.getDataParaString2(obj.getData());
		linha += ";";
		linha += obj.getOperacao();
		linha += ";";
		linha += (obj.getEcfCx()==null ? "":obj.getEcfCx());
		linha += ";";
		linha += (obj.getCFOP()==null ? "":obj.getCFOP());
		
		if(obj.getOperacao().equals("S")) {
			obj.setEntQtd(0.0);
			obj.setEntVrUnit(0.0);
			obj.setEntVrTotal(0.0);
		}
		linha += ";";
		linha += (obj.getEntQtd()==null ? 0:obj.getEntQtd().toString().replace(".", ","));
		linha += ";";
		linha += (obj.getEntVrUnit()==null ? 0:obj.getEntVrUnit().toString().replace(".", ","));
		linha += ";";
		linha += (obj.getEntVrTotal()==null ? 0 : obj.getEntVrTotal().toString().replace(".", ","));
		
		if(obj.getOperacao().equals("E")) {
			obj.setSaidaQtd(0.0);
			obj.setSaidaVrUnit(0.0);
			obj.setSaidaVrTotal(0.0);
		}
		
		linha += ";";
		linha += (obj.getSaidaQtd()==null ? 0 : obj.getSaidaQtd().toString().replace(".", ","));
		linha += ";";
		linha += (obj.getSaidaVrUnit() == null ? 0 : obj.getSaidaVrUnit().toString().replace(".", ","));
		linha += ";";
		linha += (obj.getSaidaVrTotal() == null ? 0 : obj.getSaidaVrTotal().toString().replace(".", ","));
		
		linha += ";";
		linha += (obj.getSaldoQtd()== null ? 0 : obj.getSaldoQtd().toString().replace(".", ","));
		linha += ";";
		linha += (obj.getSaldoVrUnit()==null ? 0 : obj.getSaldoVrUnit().toString().replace(".", ","));
		linha += ";";
		linha += (obj.getSaldoVrTotal()==null ? 0 : obj.getSaldoVrTotal().toString().replace(".", ","));
		linha += ";";
		linha += (obj.getModelo()==null ?"":obj.getModelo());
		linha += ";";
		linha += (obj.getDecricaoItem()==null ? "" : obj.getDecricaoItem());
		linha += ";";
		linha += (obj.getNumero()==null ? "" : obj.getNumero());
		linha += ";";
		linha += (obj.getCNPJCPF()== null ? "" :obj.getCNPJCPF());
		linha += ";";
		linha += (obj.getParticipante()==null ? "" : obj.getParticipante());
		linha += ";";
		linha += (obj.getChaveDoc() == null ? "" :obj.getChaveDoc());
		
		
		
		return linha;
	}
 	
	private String cabecalho() {
		String linha;
		
		linha = "Data";
		linha += ";";
		linha += "Operacao";
		linha += ";";
		linha += "ECF_CX";
		linha += ";";
		linha += "CFOP";
		linha += ";";
		linha += "Entrada Qtd.";
		linha += ";";
		linha += "Entrada Vr. Unit.";
		linha += ";";
		linha += "Entrada Vr. Total";
		linha += ";";
		linha += "Saida Qtd.";
		linha += ";";
		linha += "Saida Vr. Unit.";
		linha += ";";
		linha += "Saida Vr. Total";
		linha += ";";
		linha += "Saldo Qtd.";
		linha += ";";
		linha += "Saldo Vr. Unit.";
		linha += ";";
		linha += "Saldo Vr. Total";
		linha += ";";
		linha += "Modelo";
		linha += ";";
		linha += "Decricao Item";
		linha += ";";
		linha += "Numero";
		linha += ";";
		linha += "CNPJ/CPF";
		linha += ";";
		linha += "Participante";
		linha += ";";
		linha += "Chave Doc";
		
		return linha;
	}
	
	
	
	
	   public List<ModelProdutoDePara> importaListaProdutos(String caminho) {
		   List<ModelProdutoDePara> retorno = new ArrayList<>();
		   File arquivoCSV = new File(caminho);
		  
		   try {
			
			     String linhaDoArquivo = new String();
				 
				 @SuppressWarnings("resource")
				 Scanner leitor = new Scanner(arquivoCSV);
				 //leitor.nextLine();
				 while(leitor.hasNext()){
					 ModelProdutoDePara prod = new ModelProdutoDePara();
					 linhaDoArquivo = leitor.nextLine();
					 String[] valoresEntreVirgula = linhaDoArquivo.split("\\;");	 
					 for(int i=0;i<valoresEntreVirgula.length;i++){
						  
						    if(i==0){
						    	 prod.setCodigo(valoresEntreVirgula[0]);
						    	
						    }
						    if(i==1) {
						    	 prod.setCodAntItem(valoresEntreVirgula[1]);
						    }
						    retorno.add(prod);  
					 }

				 }
			   
		   }catch (FileNotFoundException e) {
				
				e.printStackTrace();
		   }
		   
		   return retorno;
	   }
	   
	   public void exportarHistoricoItensComLista(String fileProdutos,String file, String ano, String cnpj) throws SQLException {
            int id = 0;
		    for(ModelProdutoDePara p : importaListaProdutos(fileProdutos)){  
		    	
		    	if(p.getCodigo() != null || p.getCodAntItem()  != null) {
		    		id++;
		    		//System.out.println(id + " - " + p.getCodigo() + " = " + p.getCodAntItem());

		    		exportarHistoricoItem(file , ano, cnpj, p.getCodigo(), p.getCodAntItem());
		    	}
		    
		    }
	   }
}
