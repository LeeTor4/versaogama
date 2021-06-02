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
		Path p = Paths.get("D:\\ORTOGENESE\\SPED\\2014\\SpedEFD-05329222000419-063882345-Remessa de arquivo original-dez2020.txt");
		
		String dirPlanilha  = "D:/ORTOGENESE/CONTROLE_ESTOQUE_2012_Loja03.csv";
		String dirTotal     = "D:/ORTOGENESE/TOTALIZADORES_ESTOQUE_2018.CSV";

		LeitorTxtSpedFiscal leitor = new LeitorTxtSpedFiscal();
		LeitorXML logica = new LeitorXML();	

		XMLReader readerCF = XMLReaderFactory.createXMLReader();
	  
		ExportaQuantitativoEstoque exporta = new ExportaQuantitativoEstoque();
		
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
		
		
		Long numLote = lote.importandoLoteSpedFiscal(pXml,leitor,logica,readerCF,part,prod,outUnid,alt,nota,pNota,nf,ecf,rdz,totRDZ,itensCF,totDirCF,cfe,itemCfe,inv,itnInv);
       
		System.out.println("Lote externo " + numLote);
   		
		lote.inserindoMovimentacoesMensaisEntradasSaidasPorLote(lote.getListaCodigosProdutosNoLote(),
				lote.getTotalizaValoresPorItnEnt(lote.getTotaisEntradas(), numLote),
				lote.getTotalizaValoresPorItnSai(lote.getTotaisSaidas(), numLote), numLote);
		
		
		//movPorLote.importacaoDosItensDeEntradasESaidasDeProdutos(numLote);
		
		
		//exporta.exportaControleQuantitativos(dirPlanilha, "2012","05329222000338");
		
		// totalizadorFinanceiro.exportaTotalizadorFinanceiroEstoque(dirTotal,  "2018","05329222000176");
        
		
//		  String dirPlanHistorico = "D:/ORTOGENESE/fichas_estoques/2019/";
//	      String dirPlanHistorico2 = "D:/ORTOGENESE/fichas_estoques/";
//	      String dirListaProds = "D:/ORTOGENESE/ListaCodItem2.csv";
//	      
//	      hist.exportarHistoricoItem(dirPlanHistorico2, "2019", "05329222000419", "1022", "");
//	      hist.exportarHistoricoItensComLista(dirListaProds, dirPlanHistorico2,  "2019", "05329222000419");
	}

}
