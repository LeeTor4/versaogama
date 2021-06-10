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
import versaogama.service.estabelecimento.LoteImportacaoSpedFiscalService;

public class MainService {
	
	
	public static void main(String[] args) throws Exception {
		
		Pool pool = new Pool();
		Path pXml = Paths.get("D:\\XML");
		//Path p = Paths.get("D:\\ORTOGENESE\\SPED\\2014\\SpedEFD-05329222000419-063882345-Remessa de arquivo original-dez2020.txt");
		
		//Path x1 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\XML\\jan");
		//Path p1 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\SpedEFD-05329222000338-063037882-Remessa de arquivo substituto-jan2015.txt");
		
		//Path x2 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\XML\\fev");
		//Path p2 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\SpedEFD-05329222000338-063037882-Remessa de arquivo substituto-fev2015.txt");
		
		//Path x3 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\XML\\mar");
		//Path p3 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\SpedEFD-05329222000338-063037882-Remessa de arquivo substituto-mar2015.txt");
		
		//Path x4 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\XML\\abr");
		//Path p4 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\SpedEFD-05329222000338-063037882-Remessa de arquivo original-abr2015.txt");
		
		//Path x5 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\XML\\mai");
	    //Path p5 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\SpedEFD-05329222000338-063037882-Remessa de arquivo substituto-mai2015.txt");
		
		//Path x6 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\XML\\jun");
		//Path p6 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\SpedEFD-05329222000338-063037882-Remessa de arquivo substituto-jun2015.txt");
		
		//Path x7 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\XML\\jul");
		//Path p7 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\SpedEFD-05329222000338-063037882-Remessa de arquivo substituto-Jul2015.txt");
		
		//Path x8 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\XML\\ago");
		//Path p8 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\SpedEFD-05329222000338-063037882-Remessa de arquivo substituto-Ago2015.txt");
		
		//Path x9 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\XML\\set");
		//Path p9 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\SpedEFD-05329222000338-063037882-Remessa de arquivo substituto-Set2015.txt");
		
		 //Path x10 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\XML\\out");
		 //Path p10 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\FARMACIA SPED 201510 RET.txt");
		
		//Path x11 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\XML\\nov");
		//Path p11 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\FARMACIA SPED 201511 RET.txt");
		
		 Path x12 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\XML\\dez");
		 Path p12 = Paths.get("D:\\EMPRESAS\\SELLENE\\LOJA03\\SPED\\2015\\SpedEFD-05329222000338-063037882-Remessa de arquivo substituto-dez2015.txt");
		
		
		
		String dirPlanilha  = "D:/EMPRESAS/SELLENE/LOJA03/CONTROLE_ESTOQUE_2013_Loja03.csv";
		String dirTotal     = "D:/ORTOGENESE/TOTALIZADORES_ESTOQUE_2018.CSV";

		LeitorTxtSpedFiscal leitor = new LeitorTxtSpedFiscal();
		LeitorXML logica = new LeitorXML();	

		XMLReader readerCF = XMLReaderFactory.createXMLReader();
	  
		ExportaQuantitativoEstoque exporta = new ExportaQuantitativoEstoque();
		
		Path p = p12;
		Path x = x12;	
		
		RegC100 nf = new RegC100();
		leitor.leitorSpedFiscal(p,pool);


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
		
		
		Inventario inv = new Inventario();
		ItensInventario itnInv = new ItensInventario();
		
		
		Long numLote = lote.importandoLoteSpedFiscal(x,leitor,logica,readerCF,part,prod,outUnid,alt,nota,pNota,nf,ecf,rdz,totRDZ,itensCF,totDirCF,cfe,itemCfe,inv,itnInv);
       
		System.out.println("Lote externo " + numLote);
   		
		lote.inserindoMovimentacoesMensaisEntradasSaidasPorLote(lote.getListaCodigosProdutosNoLote(),
				lote.getTotalizaValoresPorItnEnt(lote.getTotaisEntradas(), numLote),
				lote.getTotalizaValoresPorItnSai(lote.getTotaisSaidas(), numLote), numLote);
		
		
		//movPorLote.importacaoDosItensDeEntradasESaidasDeProdutos(numLote);
		
		
		//exporta.exportaControleQuantitativos(dirPlanilha, "2013","05329222000338");
		
		//totalizadorFinanceiro.exportaTotalizadorFinanceiroEstoque(dirTotal,  "2018","05329222000176");
        
		
		  String dirPlanHistorico = "D:/ORTOGENESE/fichas_estoques/2019/";
	      String dirPlanHistorico2 = "D:/EMPRESAS/SELLENE/LOJA03/fichas_estoques/";
	      String dirListaProds = "D:/ORTOGENESE/ListaCodItem2.csv";
	      
	     // hist.exportarHistoricoItem(dirPlanHistorico2, "2012", "05329222000338", "00005617", "");
	     // hist.exportarHistoricoItensComLista(dirListaProds, dirPlanHistorico2,  "2019", "05329222000419");
	}

}
