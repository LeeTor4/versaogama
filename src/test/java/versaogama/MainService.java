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
import versaogama.service.estabelecimento.ExportaRelacaoInventario;
import versaogama.service.estabelecimento.ExportaTotalizadorAnual;
import versaogama.service.estabelecimento.ImportaEntradasSaidasProdutosPorLote;
import versaogama.service.estabelecimento.ImportaSaldoInicialEstoqueMensal;
import versaogama.service.estabelecimento.LoteImportacaoSpedFiscalService;

public class MainService {
	
	
	public static void main(String[] args) throws Exception {
		
		String ano = "2019"; 
		String cnpj = "05329222000842";
		String emp = "SELLENE";
		String estab = "HARMONY";
		
		String anomes1 = ano.concat("01").concat(".txt");
		String anomes2 = ano.concat("02").concat(".txt");
		String anomes3 = ano.concat("03").concat(".txt");
		String anomes4 = ano.concat("04").concat(".txt");
		String anomes5 = ano.concat("05").concat(".txt");
		String anomes6 = ano.concat("06").concat(".txt");
		String anomes7 = ano.concat("07").concat(".txt");
		String anomes8 = ano.concat("08").concat(".txt");
		String anomes9 = ano.concat("09").concat(".txt");
		String anomes10 = ano.concat("10").concat(".txt");
		String anomes11 = ano.concat("11").concat(".txt");
		String anomes12 = ano.concat("12").concat(".txt");
		
		Pool pool = new Pool();
		//Path pXml = Paths.get("D:\\XML");
		//Path p = Paths.get("D:\\ORTOGENESE\\SPED\\2014\\SpedEFD-05329222000419-063882345-Remessa de arquivo original-dez2020.txt");
		
		 Path x1 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\XML").concat("\\jan"));
	     Path p1 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\").concat(anomes1));
		
	     Path x2 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\XML").concat("\\fev"));
	     Path p2 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\").concat(anomes2));
		
	     Path x3 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\XML").concat("\\mar"));
	     Path p3 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\").concat(anomes3));
	     
	     Path x4 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\XML").concat("\\abr"));
	     Path p4 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\").concat(anomes4));
	     
	     Path x5 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\XML").concat("\\mai"));
	     Path p5 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\").concat(anomes5));
	     
	     Path x6 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\XML").concat("\\jun"));
	     Path p6 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\").concat(anomes6));
	     
	     Path x7 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\XML").concat("\\jul"));
	     Path p7 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\").concat(anomes7));
	     
	     Path x8 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\XML").concat("\\ago"));
	     Path p8 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\").concat(anomes8));
	     
	     Path x9 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\XML").concat("\\set"));
	     Path p9 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\").concat(anomes9));
	     
	     Path x10 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\XML").concat("\\out"));
	     Path p10 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\").concat(anomes10));
	     
	     Path x11 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\XML").concat("\\nov"));
	     Path p11 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\").concat(anomes11));
	     
	     Path x12 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\XML").concat("\\dez"));
	     Path p12 = Paths.get("D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\SPED").concat("\\").concat(ano).concat("\\").concat(anomes12));
		
	     
		String dirPlanilha          = "D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\CONTROLE_ESTOQUE_".concat(cnpj).concat("_").concat(ano).concat(".csv"));
		String dirPlanReprocessado  = "D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\CONTROLE_ESTOQUE_".concat(cnpj).concat("_").concat(ano).concat("_").concat("reprocessado").concat(".csv"));
		String dirPlanInv           = "D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\INVENTARIO_".concat(cnpj).concat("_").concat(ano).concat(".csv"));
		
		String dirTotal     = "D:/ORTOGENESE/TOTALIZADORES_ESTOQUE_2017.CSV";

		LeitorTxtSpedFiscal leitor = new LeitorTxtSpedFiscal();
		LeitorXML logica = new LeitorXML();	

		XMLReader readerCF = XMLReaderFactory.createXMLReader();
	  
		ExportaQuantitativoEstoque exporta = new ExportaQuantitativoEstoque();
		
//		Path p = p12;
//		Path x = x12;	
//		
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
		ExportaRelacaoInventario relacaoInv = new ExportaRelacaoInventario();
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
		
		String dirSaldoInicial = "D:\\EMPRESAS".concat("\\").concat(emp).concat("\\").concat(estab).concat("\\itensRetroativos2019".concat(".csv"));
		 
		 
	    exporta.exportaControleQuantitativos(dirSaldoInicial,dirPlanReprocessado, dirPlanilha, ano,cnpj);
		
		//relacaoInv.exportRelacaoInventario(dirPlanInv,cnpj, ano);
		//totalizadorFinanceiro.exportaTotalizadorFinanceiroEstoque(dirTotal,  "2018","05329222000176");
        
		
		
		  String dirPlanHistorico = "D:/ORTOGENESE/fichas_estoques/2019/";
	      String dirPlanHistorico2 = "D:/EMPRESAS/SELLENE/MEGAFARMA/fichas_estoques/";
	      String dirListaProds = "D:/ORTOGENESE/ListaCodItem2.csv";
	      
	      //saldoInicial.importaSaldoInicialEstoqueMensal(dirSaldoInicial, ano,cnpj);

	       //hist.exportarHistoricoItem(dirPlanHistorico2, "2017", "05329222000680", "00000120","");
	      //hist.exportarHistoricoItensComLista(dirListaProds, dirPlanHistorico2,  "2019", "05329222000419");
	}

}
