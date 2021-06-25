package versaogama;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import versaogama.conexao.Pool;
import versaogama.managersped.LeitorTxtSpedFiscal;
import versaogama.managerxml.LeitorXML;
import versaogama.model.sped.RegC100;
import versaogama.model.system.LoteImportacaoSpedFiscal;
import versaogama.model.system.cfe.EquipamentoCFe;
import versaogama.model.system.cfe.ItensMovDiarioCFe;
import versaogama.model.system.equipecf.EquipamentoECF;
import versaogama.model.system.equipecf.ItensMovDiario;
import versaogama.model.system.equipecf.ReducaoZ;
import versaogama.model.system.equipecf.TotParciaisRDZ;
import versaogama.model.system.equipecf.TotalizadorDiarioCuponsFiscais;
import versaogama.model.system.estabelecimento.Participante;
import versaogama.model.system.inventario.Inventario;
import versaogama.model.system.inventario.ItensInventario;
import versaogama.model.system.movprodutos.ModelInventarioDeclarado;
import versaogama.model.system.notafiscal.NotaFiscal;
import versaogama.model.system.notafiscal.ProdutoNota;
import versaogama.model.system.produto.AlteracaoItem;
import versaogama.model.system.produto.OutrasUnid;
import versaogama.model.system.produto.Produto;
import versaogama.service.estabelecimento.ExportaHisoricoItem;
import versaogama.service.estabelecimento.ExportaQuantitativoEstoque;
import versaogama.service.estabelecimento.ExportaTotalizadorAnual;
import versaogama.service.estabelecimento.ImportaEntradasSaidasProdutosPorLote;
import versaogama.service.estabelecimento.ImportaSaldoInicialEstoqueMensal;
import versaogama.service.estabelecimento.LoteImportacaoSpedFiscalService;

public class MainService {
	
	
	public static void main(String[] args) throws Exception {
		
		Pool pool = new Pool();
		//Path pXml = Paths.get("D:\\XML");
		//Path p = Paths.get("D:\\ORTOGENESE\\SPED\\2014\\SpedEFD-05329222000419-063882345-Remessa de arquivo original-dez2020.txt");
		
		//Path x1 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2021\\XML\\jan");
	    //Path p1 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2021\\SPED_LJ03_JAN2021.txt");
		
	    //Path x2 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2021\\XML\\fev");
		//Path p2 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2021\\SpedEFD-05329222000338-063037882-Remessa de arquivo original-fev2021.txt");
		
	    //Path x3 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2021\\XML\\mar");
	    //Path p3 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2021\\SpedEFD-05329222000338-063037882-Remessa de arquivo original-mar2021.txt");
		
	    //Path x4 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2021\\XML\\abr");
	    //Path p4 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2021\\SpedEFD-05329222000338-063037882-Remessa de arquivo original-abr2021.txt");
	
//	    Path x5 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\XML\\mai");
//        Path p5 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\05329222000338-063037882-20200501-20200531-0-D0F01251229A17153DDB78FBB3C98C7C6CB008BA-SPED-EFD.txt");
//	
//        Path x6 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\XML\\jun");
//        Path p6 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\05329222000338-063037882-20200601-20200630-0-8E57B91F3C253C67E0B4C8BDD15401F3ED02D185-SPED-EFD.txt");
//	
//	    Path x7 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\XML\\jul");
//        Path p7 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\LOJA03_CD_JULHO2020.txt");
//	
//	    Path x8 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\XML\\ago");
//	    Path p8 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\LOJA0320200801.txt");
//	
//	    Path x9 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\XML\\set");
//        Path p9 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\loja03_delivery_092020.txt");
//	
//        Path x10 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\XML\\out");
//        Path p10 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\Loja03_sped_102020.txt");
//	
//        Path x11 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\XML\\nov");
//	    Path p11 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\Loja03_nov2020.txt");
//	
//        Path x12 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\XML\\dez");
//        Path p12 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2020\\sped_loja03_dez2020.txt");
		
		
		
		String dirPlanilha  = "D:/EMPRESAS/SELLENE/SAO_MATEUS/CONTROLE_ESTOQUE_2017_saomateus_reprocessado.csv";
		String dirTotal     = "D:/ORTOGENESE/TOTALIZADORES_ESTOQUE_2017.CSV";

		LeitorTxtSpedFiscal leitor = new LeitorTxtSpedFiscal();
		LeitorXML logica = new LeitorXML();	

		XMLReader readerCF = XMLReaderFactory.createXMLReader();
	  
		ExportaQuantitativoEstoque exporta = new ExportaQuantitativoEstoque();
		
//		Path p = p1;
//		Path x = x1;	
//		
//		RegC100 nf = new RegC100();
//		leitor.leitorSpedFiscal(p,pool);


		Participante part = new Participante();
		Produto prod = new Produto();
		OutrasUnid outUnid     = new OutrasUnid();
		AlteracaoItem alt      = new AlteracaoItem();
		NotaFiscal nota        = new NotaFiscal();
		ProdutoNota pNota      = new ProdutoNota();
		EquipamentoECF ecf     = new EquipamentoECF();
		ReducaoZ rdz           = new ReducaoZ();
		TotParciaisRDZ totRDZ  = new TotParciaisRDZ();
		ItensMovDiario itensCF = new ItensMovDiario(); 
		EquipamentoCFe cfe     = new EquipamentoCFe();
		ItensMovDiarioCFe  itemCfe = new ItensMovDiarioCFe();
		TotalizadorDiarioCuponsFiscais totDirCF = new TotalizadorDiarioCuponsFiscais();
		LoteImportacaoSpedFiscalService lote    = new LoteImportacaoSpedFiscalService();
		ImportaEntradasSaidasProdutosPorLote movPorLote = new ImportaEntradasSaidasProdutosPorLote();
		ExportaHisoricoItem hist = new ExportaHisoricoItem();
		ExportaTotalizadorAnual totalizadorFinanceiro = new ExportaTotalizadorAnual();
		ImportaSaldoInicialEstoqueMensal saldoInicial = new ImportaSaldoInicialEstoqueMensal();
		
		Inventario inv = new Inventario();
		ItensInventario itnInv = new ItensInventario();
		
		
//		Long numLote = lote.importandoLoteSpedFiscal(x,leitor,logica,readerCF,part,prod,outUnid,alt,nota,pNota,nf,ecf,rdz,totRDZ,itensCF,totDirCF,cfe,itemCfe,inv,itnInv);
//       
//		System.out.println("Lote externo " + numLote);
//   		
//		lote.inserindoMovimentacoesMensaisEntradasSaidasPorLote(lote.getListaCodigosProdutosNoLote(),
//				lote.getTotalizaValoresPorItnEnt(lote.getTotaisEntradas(), numLote),
//				lote.getTotalizaValoresPorItnSai(lote.getTotaisSaidas(), numLote), numLote);
		
		
		//movPorLote.importacaoDosItensDeEntradasESaidasDeProdutos(numLote);
		
		
		  exporta.exportaControleQuantitativos(dirPlanilha, "2017","05329222000761");
		
		//totalizadorFinanceiro.exportaTotalizadorFinanceiroEstoque(dirTotal,  "2018","05329222000176");
        
		 String dirSaldoInicial = "D:/EMPRESAS/SELLENE/SAO_MATEUS/saldoInicial.csv";
		
		  String dirPlanHistorico = "D:/ORTOGENESE/fichas_estoques/2019/";
	      String dirPlanHistorico2 = "D:/EMPRESAS/SELLENE/MEGAFARMA/fichas_estoques/";
	      String dirListaProds = "D:/ORTOGENESE/ListaCodItem2.csv";
	      
	      //saldoInicial.importaSaldoInicialEstoqueMensal(dirSaldoInicial, "2017","05329222000761");
	      //hist.exportarHistoricoItem(dirPlanHistorico2, "2021", "05329222000680", "00003091","");
	      //hist.exportarHistoricoItensComLista(dirListaProds, dirPlanHistorico2,  "2019", "05329222000419");
	}

}
