package versaogama.service.estabelecimento;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Scanner;

import versaogama.conexao.Pool;
import versaogama.dao.movprodutosdao.SaldoInicialControleEstoqueDao;
import versaogama.model.system.movprodutos.ModeloSaldoInicialControleEstoque;
import versaogama.util.UtilsEConverters;

public class ImportaSaldoInicialEstoqueMensal {

	private SaldoInicialControleEstoqueDao dao;
	
	
	public ImportaSaldoInicialEstoqueMensal() {
		Pool pool = new Pool();
		
		dao = new SaldoInicialControleEstoqueDao(pool);
	}
	
	
	public void importaSaldoInicialEstoqueMensal(String caminho, String ano, String cnpj) throws SQLException {
		 File arquivoCSV = new File(caminho);
		 
		 try {
			 
			 String linhaDoArquivo = new String();
			 
			 @SuppressWarnings("resource")
			 Scanner leitor = new Scanner(arquivoCSV);
			 leitor.nextLine();
			 while(leitor.hasNext()){
				 ModeloSaldoInicialControleEstoque saldo = new ModeloSaldoInicialControleEstoque();
				 linhaDoArquivo = leitor.nextLine();
				 String[] valoresEntreVirgula = linhaDoArquivo.split("\\;");	
				
				 for(int i=0;i<valoresEntreVirgula.length;i++){
					 saldo.setAno(ano);
					 saldo.setCnpj(cnpj);
					 if(i==0){
						 
						 saldo.setCodItem(UtilsEConverters.preencheZerosAEsquerda(valoresEntreVirgula[0]));
					 }
                     if(i==1){
						 
						 saldo.setCodAntItem(UtilsEConverters.preencheZerosAEsquerda(valoresEntreVirgula[1]));
					 }
                     if(i==2){
						 
						 saldo.setDescricao(valoresEntreVirgula[2]);
					 }
                     if(i==3){
                    	 Double qtde = 0.0;
                    	 if(Double.valueOf(valoresEntreVirgula[3]) < 0) {
                    		 qtde = Math.abs(Double.valueOf(valoresEntreVirgula[3]));
                    	 }else {
                    		 qtde = Double.valueOf(valoresEntreVirgula[3]); 
                    	 }
                    	 
                    	 saldo.setQtdeInicial(qtde);
                     }
					 
				 }
				 
				 dao.importarSaldoInicialEstoque(saldo);
			 }
		 }catch (FileNotFoundException e) {
				
				e.printStackTrace();
		 }
	}
}