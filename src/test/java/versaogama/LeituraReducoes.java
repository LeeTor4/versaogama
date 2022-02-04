package versaogama;

import java.nio.file.Path;
import java.nio.file.Paths;

import versaogama.conexao.Pool;
import versaogama.dao.bancodao.BancoDAO;
import versaogama.managersped.LeitorTxtSpedFiscal;
import versaogama.model.system.banco.Metadados;

public class LeituraReducoes {

	public static void main(String[] args) throws Exception {
		
		String ano = "2017";
		String cnpj = "05329222000680";
		String emp = "SELLENE";
		String estab = "MEGAFARMA";
		
		String anomes1 = ano.concat("01").concat(".txt");
		
		Path x1 = Paths.get("E:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\XML").concat("\\jan"));
	    Path p1 = Paths.get("E:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\").concat(anomes1));
	     
		Path p = p1;
		Path x = x1;	
		
		 Pool pool = new Pool();
		LeitorTxtSpedFiscal leitor = new LeitorTxtSpedFiscal();
		leitor.leitorSpedFiscal(p,pool);
		
//		for (int i = 0; i < leitor.getRegsC400().size(); i++) {
//
//			for (int y = 0; y < leitor.getRegsC400().get(i).getRegsC405().size(); y++) {
//
//				for (int w = 0; w < leitor.getRegsC400().get(i).getRegsC405().get(y).getRegsC420().size(); w++) {
//
//					for (int z = 0; z < leitor.getRegsC400().get(i).getRegsC405().get(y).getRegsC420().get(w)
//							.getRegsC425().size(); z++) {
//						
//						if(leitor.getRegsC400().get(i).getRegsC405().get(y).getRegsC420().get(w).getRegsC425()
//								.get(z).getCodItem().equals("00002646")) {
//							
//							System.out.println(leitor.getRegsC400().get(i).getNumSerieFabECF() + "|"
//									+ leitor.getRegsC400().get(i).getNumCaixaECF() + "|"
//									+ leitor.getRegsC400().get(i).getRegsC405().get(y).getPosicaoRDZ() + "|"
//									+ leitor.getRegsC400().get(i).getRegsC405().get(y).getDtDoc() + "|"
//									+ leitor.getRegsC400().get(i).getRegsC405().get(y).getRegsC420().get(w).getCodTotPar()
//									+ "|"
//									+ leitor.getRegsC400().get(i).getRegsC405().get(y).getRegsC420().get(w).getVlAcumTot()
//									+ "|" +
//
//									leitor.getRegsC400().get(i).getRegsC405().get(y).getRegsC420().get(w).getRegsC425()
//											.get(z).getCodItem()
//									+ "|"
//									+ leitor.getRegsC400().get(i).getRegsC405().get(y).getRegsC420().get(w).getRegsC425()
//											.get(z).getQtd()
//									+ "|" + leitor.getRegsC400().get(i).getRegsC405().get(y).getRegsC420().get(w)
//											.getRegsC425().get(z).getVlItem());
//						}
//
//
//
//					}
//
//				}
//
//			}
//
//		}
		
	    BancoDAO bancoDao;
		bancoDao = new BancoDAO(pool);
		Metadados dadosDoBanco = bancoDao.dadosDoBanco("tb_lote_import_sped_icms");
		
		System.out.println(dadosDoBanco.getAutoIncremento());
		
		System.out.println(leitor.incLoteImportacao(pool));
		
		
		

	}

}
