package versaogama.service.estabelecimento;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;

import versaogama.conexao.Pool;
import versaogama.dao.estabelecimentodao.importacaospedfical.TotalizadorePorItemEntDAO;
import versaogama.dao.estabelecimentodao.produto.ProdutoDao;
import versaogama.dao.movprodutosdao.ModelHistoricoItensDao;
import versaogama.dao.movprodutosdao.SaldoInicialControleEstoqueDao;
import versaogama.model.system.movprodutos.ModeloSaldoInicialControleEstoque;
import versaogama.util.UtilsEConverters;

public class ExportaRelacaoInventario {
	
    private TotalizadorePorItemEntDAO dao;
    private SaldoInicialControleEstoqueDao daoSaldo;
    private ProdutoDao daoProd;
   
	
    public ExportaRelacaoInventario() {
    	Pool pool = new Pool();
    	dao      = new TotalizadorePorItemEntDAO(pool);
    	daoSaldo = new SaldoInicialControleEstoqueDao(pool);
    	daoProd  = new ProdutoDao(pool);
	}
    
    
	public void exportRelacaoInventario(String file,String cnpj, String ano) throws SQLException {
		Double vl = 0.0;
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(file)));
		
			String linha = " ";

			linha = cabecalho();

			writer.write(linha);
			writer.newLine();
		
		
	        for(ModeloSaldoInicialControleEstoque mov : daoSaldo.getListaProdutosInventario(cnpj, ano)){
	        	
	        	if(dao.ultimoRegistroDoItem(cnpj, mov.getCodItem(), ano) == null) {
	        	   vl = 0.0;
	        	}else {
	        	   vl = dao.ultimoRegistroDoItem(cnpj, mov.getCodItem(), ano).getVlUnit();
	        	}
	        	
	     
//	        	if(mov.getQtdeInicial() != 0.0){
//	        		System.out.println(mov.getCodItem() +"|"+ daoProd.getProdutoPorCodUtiliz(mov.getCodItem()).getDescricao() 
//    				+"|"+ daoProd.getProdutoPorCodUtiliz(mov.getCodItem()).getNcm()
//    				+"|"+ daoProd.getProdutoPorCodUtiliz(mov.getCodItem()).getTipoItem()
//    				+"|"+ mov.getQtdeInicial() +"|"+ 
//    				daoProd.getProdutoPorCodUtiliz(mov.getCodItem()).getUndPadrao() +"|"+ 
//    				
//    				(vl) +"|"+
//    				
//    				(mov.getQtdeInicial()*vl));
//	        	}

                
                
                
	        	if(mov.getQtdeInicial() != 0.0){
	        	

	        		System.out.println(mov.getCodItem() +"|"+ daoProd.getProdutoPorCodUtiliz(mov.getCodItem()).getDescricao() 
	        				+"|"+ daoProd.getProdutoPorCodUtiliz(mov.getCodItem()).getNcm()
	        				+"|"+ daoProd.getProdutoPorCodUtiliz(mov.getCodItem()).getTipoItem()
	        				+"|"+ mov.getQtdeInicial() +"|"+ daoProd.getProdutoPorCodUtiliz(mov.getCodItem()).getUndPadrao() +"|"+ vl +"|"+(mov.getQtdeInicial()*vl));
		
			        		
			        		String tipo = (daoProd.getProdutoPorCodUtiliz(mov.getCodItem()).getTipoItem() == null ? "" : daoProd.getProdutoPorCodUtiliz(mov.getCodItem()).getTipoItem());
			        		
			        		linha  = cnpj;
			        		linha += ";";
			        		linha += ano;
			        		linha += ";";
			        		linha += mov.getCodItem();
			        		linha += ";";
			        		linha += daoProd.getProdutoPorCodUtiliz(mov.getCodItem()).getDescricao() ;
			        		linha += ";";
			        		linha += (daoProd.getProdutoPorCodUtiliz(mov.getCodItem()).getNcm()==null ? "" : daoProd.getProdutoPorCodUtiliz(mov.getCodItem()).getNcm());
			        		linha += ";";
			        		linha += UtilsEConverters.preencheZerosAEsquerdaDoisDigitos(tipo);
			        		linha += ";";
			        		linha += String.format("%,.2f",mov.getQtdeInicial()); 
			        		linha += ";";
			        		linha += daoProd.getProdutoPorCodUtiliz(mov.getCodItem()).getUndPadrao();
			        		linha += ";";
			        		linha += String.format("%,.2f",vl); 			        		
			        		linha += ";";		        		
			        		linha += String.format("%,.2f",(mov.getQtdeInicial()*vl));

			        	
	        	
		        	writer.write(linha);
	    			writer.newLine();
    			
	        	}
	        }
		     
	        writer.close();	
			
	        System.out.println("Exportado com Sucesso!!!");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}


	private String cabecalho() {
		String linha;
		
		linha  = "CNPJ";
		linha += ";";
		linha += "ANO";
		linha += ";";
		linha += "CÓDIGO ITEM";
		linha += ";";
		linha += "DESCRICAO ITEM";
		linha += ";";
		linha += "NCM";
		linha += ";";
		linha += "TIPO ITEM";
		linha += ";";
		linha += "QTDE";
		linha += ";";
		linha += "UND";
		linha += ";";
		linha += "VL_UNIT";
		linha += ";";
		linha += "VL_TOTAL";
		linha += ";";
		
		return linha;
	}

}
