package versaogama.service.estabelecimento;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import org.xml.sax.XMLReader;

import versaogama.conexao.Pool;
import versaogama.dao.empresadao.EmpresaDAO;
import versaogama.dao.estabelecimentodao.equipcfe.EquipamentoCFeDao;
import versaogama.dao.estabelecimentodao.equipcfe.ItensMovDiarioCFeDao;
import versaogama.dao.estabelecimentodao.equipecf.EquipamentoECFDao;
import versaogama.dao.estabelecimentodao.equipecf.ItensMovDiarioDao;
import versaogama.dao.estabelecimentodao.equipecf.ReducaoZDao;
import versaogama.dao.estabelecimentodao.equipecf.TotParciaisRDZDao;
import versaogama.dao.estabelecimentodao.equipecf.TotalizadorDiarioCuponsFiscaisDao;
import versaogama.dao.estabelecimentodao.estabelecimento.EstabelecimentoDao;
import versaogama.dao.estabelecimentodao.importacaospedfical.LoteImportacaoSpedFiscalDao;
import versaogama.dao.estabelecimentodao.importacaospedfical.TotalizadorePorItemEntDAO;
import versaogama.dao.estabelecimentodao.importacaospedfical.TotalizadorePorItemSaiDAO;
import versaogama.dao.estabelecimentodao.notafiscal.NotaFiscalDao;
import versaogama.dao.estabelecimentodao.notafiscal.ProdutoNotaDao;
import versaogama.dao.estabelecimentodao.participantes.ParticipanteDao;
import versaogama.dao.estabelecimentodao.produto.AlteracaoItemDao;
import versaogama.dao.estabelecimentodao.produto.OutrasUnidDao;
import versaogama.dao.estabelecimentodao.produto.ProdutoDao;
import versaogama.dao.inventario.InventarioDao;
import versaogama.dao.inventario.ItensInventarioDao;
import versaogama.dao.movprodutosdao.EntradasSaidasDeProdutosDao;
import versaogama.dao.movprodutosdao.ModelHistoricoItensDao;
import versaogama.managersped.LeitorTxtSpedFiscal;
import versaogama.managerxml.LeitorXML;
import versaogama.model.sped.Reg0000;
import versaogama.model.sped.RegC100;
import versaogama.model.system.LoteImportacaoSpedFiscal;
import versaogama.model.system.TotalizadoresPorItem;
import versaogama.model.system.cfe.EquipamentoCFe;
import versaogama.model.system.cfe.ItensMovDiarioCFe;
import versaogama.model.system.empresa.Empresa;
import versaogama.model.system.equipecf.EquipamentoECF;
import versaogama.model.system.equipecf.ItensMovDiario;
import versaogama.model.system.equipecf.ReducaoZ;
import versaogama.model.system.equipecf.TotParciaisRDZ;
import versaogama.model.system.equipecf.TotalizadorDiarioCuponsFiscais;
import versaogama.model.system.estabelecimento.Estabelecimento;
import versaogama.model.system.estabelecimento.Participante;
import versaogama.model.system.inventario.Inventario;
import versaogama.model.system.inventario.ItensInventario;
import versaogama.model.system.movprodutos.EntradasSaidasDeProdutos;
import versaogama.model.system.movprodutos.ModelHistoricoItens;
import versaogama.model.system.notafiscal.NotaFiscal;
import versaogama.model.system.notafiscal.ProdutoNota;
import versaogama.model.system.produto.AlteracaoItem;
import versaogama.model.system.produto.OutrasUnid;
import versaogama.model.system.produto.Produto;
import versaogama.model.xml.ProdutoCupomFiscalXml;
import versaogama.model.xml.ProdutoNotaXmlProprio;
import versaogama.util.UtilsEConverters;

public class LoteImportacaoSpedFiscalService {

	protected Long id0000     = 0L;
	
	private EmpresaDAO daoEmp;
	private EstabelecimentoDao daoEst;
	private LoteImportacaoSpedFiscalDao daoLote;
	private ParticipanteDao daoPart;
	private ProdutoDao daoProd;
	private OutrasUnidDao daoOutUnd;
	private AlteracaoItemDao daoAltItem;
	private NotaFiscalDao daoNF;
	private ProdutoNotaDao daoProdNF;
    private EquipamentoECFDao daoECF;
    private ReducaoZDao daoRDZ;
    private TotParciaisRDZDao daoTotParRDZ;
    private ItensMovDiarioDao daoItensMovDiario;
    private TotalizadorDiarioCuponsFiscaisDao totDiarioCF;
    private EquipamentoCFeDao equipDaoCfe;
    private ItensMovDiarioCFeDao itensCFe;
    
    private ModelHistoricoItens histItens;
   
    private InventarioDao invDao;
    private ItensInventarioDao itnInvDao;
    private TotalizadorePorItemEntDAO totPorItensEntDAO;
    private TotalizadorePorItemSaiDAO totPorItensSaiDAO;
    private ModelHistoricoItensDao histItemDao;
    
    private EntradasSaidasDeProdutosDao entsai;
    
    private ArrayList<TotalizadoresPorItem> totaisEntradas = new ArrayList<TotalizadoresPorItem>();
    private ArrayList<TotalizadoresPorItem> totaisSaidas = new ArrayList<TotalizadoresPorItem>();
    private Set<String> listaCodigosProdutosNoLote = new LinkedHashSet<String>();
  
    public LoteImportacaoSpedFiscalService() throws Exception {
    	
    	Pool pool          = new Pool();
    	daoEmp             = new EmpresaDAO(pool);
    	daoEst             = new EstabelecimentoDao(pool);
    	daoLote            = new LoteImportacaoSpedFiscalDao(pool);	
    	daoPart            = new ParticipanteDao(pool);
    	daoOutUnd          = new OutrasUnidDao(pool);
    	daoAltItem         = new AlteracaoItemDao(pool); 
		daoProd            = new ProdutoDao(pool);
		daoNF              = new NotaFiscalDao(pool);
		daoProdNF          = new ProdutoNotaDao(pool);
    	daoECF             = new EquipamentoECFDao(pool);
    	daoRDZ             = new ReducaoZDao(pool);
    	daoTotParRDZ       = new TotParciaisRDZDao(pool);
    	daoItensMovDiario  = new ItensMovDiarioDao(pool);
    	totDiarioCF        = new TotalizadorDiarioCuponsFiscaisDao(pool);
    	equipDaoCfe        = new EquipamentoCFeDao(pool);
    	itensCFe           = new ItensMovDiarioCFeDao(pool);
    	invDao             = new InventarioDao(pool);
    	itnInvDao          = new ItensInventarioDao(pool);
    	totPorItensEntDAO  = new TotalizadorePorItemEntDAO(pool);
    	totPorItensSaiDAO  = new TotalizadorePorItemSaiDAO(pool);
    	histItemDao        = new ModelHistoricoItensDao(pool);
    	entsai             = new EntradasSaidasDeProdutosDao(pool);
    	histItens = new ModelHistoricoItens();
    }

    public Map<String,Empresa> getMpEmpresa() throws SQLException {
    	 Map<String,Empresa> emp = new HashMap<String, Empresa>();
    	 for(Empresa e : daoEmp.getEmpresas()){ 
    		 emp.put(e.getCnpjBase(), e);
    	 }    	
    	 return emp;
    }
    
	public Long importandoLoteSpedFiscal(
		 Path pXml,LeitorTxtSpedFiscal leitor,LeitorXML logica, XMLReader readerCF,
		 Participante part,Produto prod,OutrasUnid outUnid,AlteracaoItem alt ,NotaFiscal nota,ProdutoNota pNota,RegC100 nf,
		 EquipamentoECF ecf, ReducaoZ rdz,TotParciaisRDZ totRdz,ItensMovDiario itensCF,TotalizadorDiarioCuponsFiscais totDirCF,
		 EquipamentoCFe cfe,ItensMovDiarioCFe  itemCfe, Inventario inv, ItensInventario itnInv) {
		 Long id = 0L;
		try {

			    Reg0000 reg = leitor.getReg0000();
			    LoteImportacaoSpedFiscal lt = new LoteImportacaoSpedFiscal();
			    lt.setId(reg.getId());
				lt.setDtIni(reg.getDtIni());
				lt.setDtFin(reg.getDtFin());
				lt.setNome(reg.getNome());	
				lt.setCnpj(reg.getCnpj());
				lt.setCpf(reg.getCpf());
				lt.setUf(reg.getUf());
				lt.setIe(reg.getIe());
				lt.setCodMun(reg.getCodMun());
				lt.setSuframa(reg.getSuframa());
				lt.setIndPerfil(reg.getIndPerfil());
				lt.setIndAtiv(reg.getIndAtiv());
				
				System.out.println("Sistema " + lt.getId() + "|" + lt.getCnpj()+ "|" + lt.getNome() + "|" + lt.getDtIni() + "|" + lt.getDtFin());
				System.out.println("Arquivo " + reg.getId() + "|" + reg.getCnpj()+ "|" + reg.getNome() + "|" + reg.getDtIni() + "|" + reg.getDtFin());
			   
				if(!daoLote.getLoteImports().contains(lt)) {
					id = daoLote.salvar(lt);	
	                importandoParticipantes(leitor, part, 1L, 3L); // Campos preenchidos provisoriamente
	                importandoProdutos(leitor,prod,outUnid,alt,1L, 3L); // Campos preenchidos provisoriamente
	                importandoNotasFiscais(pXml,logica,nota,leitor,id,pNota,nf,1L,3L);// Campos preenchidos provisoriamente
	                importandoReducoesZ(leitor, ecf, rdz, totRdz, itensCF, totDirCF, id, 1L, 3L);
	                importandoEquipamentoCFe(leitor, cfe, id, 1L, 3L);
	                importandoItensCFe(pXml, leitor, itemCfe, id,1L, 3L);
	                importandoInventario(leitor, inv, itnInv);
			             
					    
				}else {
					JOptionPane.showMessageDialog(null,"Lote já Importado!!!");
					
				}
		} catch (Exception e) {
			// 
			e.printStackTrace();
		}
		
		return id;
	}

	private void importandoParticipantes(LeitorTxtSpedFiscal leitor,Participante part,Long idPaiEmp, Long idPaiEst) throws SQLException {
		
		for(int i=0; i < leitor.getRegs0150().size();i++) {	
			if(!daoPart.getParticipantes().contains(insereParticipantes(leitor, part, i, idPaiEmp, idPaiEst))) {
					daoPart.cadastrar(insereParticipantes(leitor, part, i, idPaiEmp, idPaiEst));		
			}
		}
		
	}
	private void importandoProdutos(LeitorTxtSpedFiscal leitor, Produto prod,OutrasUnid outUnd,AlteracaoItem altItem, Long idPaiEmp, Long idPaiEst) throws SQLException {
		for(int i = 0; i < leitor.getRegs0200().size();i++) {  
			listaCodigosProdutosNoLote.add(insereProdutos(leitor, prod, i, idPaiEmp, idPaiEst).getCodUtilizEmp());
			if(!daoProd.getProdutos().contains(insereProdutos(leitor, prod, i, idPaiEmp, idPaiEst))){
				daoProd.cadastrar(insereProdutos(leitor, prod, i, idPaiEmp, idPaiEst));
				
			}
            for(int x =0; x < leitor.getRegs0200().get(i).getOutrasUndMedidas().size();x++) {              	 
                if(!daoOutUnd.getOutrasUnidsMed().contains(insereOutUndMedidas(leitor, outUnd, i, x, idPaiEmp, idPaiEst))){
              	    daoOutUnd.cadastrar(insereOutUndMedidas(leitor, outUnd, i, x, idPaiEmp, idPaiEst));
                }              
            }
			for (int y = 0; y < leitor.getRegs0200().get(i).getAlteracaoItem().size();y++) {
				if (!daoAltItem.getAltItens().contains(insereAlteracaoItem(leitor, altItem, i, y, idPaiEmp, idPaiEst))) {
					daoAltItem.cadastrar(insereAlteracaoItem(leitor, altItem, i, y, idPaiEmp, idPaiEst));
				}
			}
			
		}		
	}	

	private void importandoNotasFiscais(Path pXml,LeitorXML logica,NotaFiscal nota,LeitorTxtSpedFiscal leitor,Long idPai,ProdutoNota pNota,RegC100 nf,Long idPaiEmp, Long idPaiEst) {       
		 try {
				for(int i=0; i < leitor.getRegsC100().size();i++){
					daoNF.cadastrar(insereNotaFiscal(leitor, nota, i,idPai,idPaiEmp,idPaiEst));	
				}			
				importandoProdutosSpedFiscal(leitor, pNota,idPaiEst);
				importandoProdutosXmlNotasPropria(pXml,leitor,pNota, nf,idPaiEmp,idPaiEst);
				
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	}

	
	private  void importandoProdutosSpedFiscal(LeitorTxtSpedFiscal leitor,ProdutoNota pNota,Long idPaiEst) throws SQLException {

		for(int i=0; i < leitor.getRegsC100().size();i++){
			for(int y=0; y < leitor.getRegsC100().get(i).getProdutosNota().size();y++) {
				   daoProdNF.cadastrar(insereProdutosSpedFiscal(leitor, pNota, i, y));
				 				   
				
				   if(insereProdutosSpedFiscal(leitor, pNota, i, y).getCfop().startsWith("1")
						   || insereProdutosSpedFiscal(leitor, pNota, i, y).getCfop().startsWith("2")) {
					   insereHistoricoItensEntradasTerceiros(leitor, pNota, i, y,idPaiEst);
					   totalizadoresItensEntradasTerceirosNF(leitor, pNota, i, y);
					   
				   }
				   if(insereProdutosSpedFiscal(leitor, pNota, i, y).getCfop().startsWith("5")
						   || insereProdutosSpedFiscal(leitor, pNota, i, y).getCfop().startsWith("6")) {
					   insereHistoricoItensSaidasDoArquivoSped(leitor, pNota, i, y,idPaiEst);
					   totalizadoresItensSaidasPropriasNF(leitor, pNota, i, y);
				   }
				  
			}
		}
		
	}

	private void totalizadoresItensSaidasPropriasNF(LeitorTxtSpedFiscal leitor, ProdutoNota pNota, int i, int y) throws SQLException {
		
			TotalizadoresPorItem tot = new TotalizadoresPorItem();  
			
			tot.setCodItem(insereProdutosSpedFiscalSaidas(leitor, pNota, i, y).getCodItem());
			tot.setVlTotQtde(insereProdutosSpedFiscalSaidas(leitor, pNota, i, y).getQtde());
			tot.setVlTotItem(insereProdutosSpedFiscalSaidas(leitor, pNota, i, y).getVlItem());
			totaisSaidas.add(tot);
	

	}

	private void insereHistoricoItensSaidasDoArquivoSped(LeitorTxtSpedFiscal leitor, ProdutoNota pNota, int i, int y,
			Long idPaiEst) throws SQLException {
	
		if(insereProdutosSpedFiscal(leitor, pNota, i, y).getCfop().startsWith("5") || 
				insereProdutosSpedFiscal(leitor, pNota, i, y).getCfop().startsWith("6") ) {
			   histItens.setIdPaiLote(leitor.getReg0000().getId());
			   histItens.setIdPai(insereProdutosSpedFiscal(leitor, pNota, i, y).getIdPai());
			   histItens.setEmpresa(leitor.getReg0000().getCnpj()); 
			   histItens.setOperacao("S");
			   histItens.setEcfCx("");
			   histItens.setDtDoc(leitor.getRegsC100().get(i).getDtEntSai());
			   histItens.setCodItem(insereProdutosSpedFiscal(leitor, pNota, i, y).getCodItem());
			   histItens.setQtde(insereProdutosSpedFiscal(leitor, pNota, i, y).getQtde());
			   histItens.setUnd(insereProdutosSpedFiscal(leitor, pNota, i, y).getUnd());
			   histItens.setVlUnit(insereProdutosSpedFiscal(leitor, pNota, i, y).getVlUnitItem());
			   histItens.setVlBruto(insereProdutosSpedFiscal(leitor, pNota, i, y).getVlProd());
			   histItens.setDesconto(insereProdutosSpedFiscal(leitor, pNota, i, y).getVlDesc());
			   histItens.setVlLiq(insereProdutosSpedFiscal(leitor, pNota, i, y).getVlItem());
			   histItens.setCst(insereProdutosSpedFiscal(leitor, pNota, i, y).getCstIcms());
			   histItens.setCfop(insereProdutosSpedFiscal(leitor, pNota, i, y).getCfop());
			   histItens.setAliqIcms(0.0);
			   histItens.setCodMod(leitor.getRegsC100().get(i).getCodMod());
			   histItens.setDescricao(leitor.getMpProdTerc().get(insereProdutosSpedFiscal(leitor, pNota, i, y).getCodItem()).getDescrItem());
			   histItens.setNumDoc(leitor.getRegsC100().get(i).getNumDoc());
			   histItens.setChaveDoc(leitor.getRegsC100().get(i).getChvNfe());
			   histItens.setNome(leitor.getMpParticipante().get(leitor.getRegsC100().get(i).getCodPart()).getNome());
			   histItens.setCpfCnpj(leitor.getMpParticipante().get(leitor.getRegsC100().get(i).getCodPart()).getCnpj());
			  
			   histItemDao.cadastrarHistoricoItem(histItens);
		}

		
	}

	private void insereHistoricoItensEntradasTerceiros(LeitorTxtSpedFiscal leitor, ProdutoNota pNota, int i, int y,Long idPaiEst)
			throws SQLException {
		
		   if(insereProdutosSpedFiscal(leitor, pNota, i, y).getCfop().startsWith("1") || 
				   insereProdutosSpedFiscal(leitor, pNota, i, y).getCfop().startsWith("2")) {
			   histItens.setIdPaiLote(leitor.getReg0000().getId());
			   histItens.setIdPai(insereProdutosSpedFiscal(leitor, pNota, i, y).getIdPai());
			   histItens.setEmpresa(leitor.getReg0000().getCnpj()); 
			   histItens.setOperacao("E");
			   histItens.setEcfCx("");
			   histItens.setDtDoc(leitor.getRegsC100().get(i).getDtEntSai());
			   histItens.setCodItem(insereProdutosSpedFiscal(leitor, pNota, i, y).getCodItem());
			   histItens.setQtde(insereProdutosSpedFiscal(leitor, pNota, i, y).getQtde());
			   histItens.setUnd(insereProdutosSpedFiscal(leitor, pNota, i, y).getUnd());
			   histItens.setVlUnit(insereProdutosSpedFiscal(leitor, pNota, i, y).getVlUnitItem());
			   histItens.setVlBruto(insereProdutosSpedFiscal(leitor, pNota, i, y).getVlProd());
			   histItens.setDesconto(insereProdutosSpedFiscal(leitor, pNota, i, y).getVlDesc());
			   histItens.setVlLiq(insereProdutosSpedFiscal(leitor, pNota, i, y).getVlItem());
			   histItens.setCst(insereProdutosSpedFiscal(leitor, pNota, i, y).getCstIcms());
			   histItens.setCfop(insereProdutosSpedFiscal(leitor, pNota, i, y).getCfop());
			   histItens.setAliqIcms(0.0);
			   histItens.setCodMod(leitor.getRegsC100().get(i).getCodMod());
			   
			   if(insereProdutosSpedFiscal(leitor, pNota, i, y).getCodItem() != null) {
				   if(leitor.getMpProdTerc().get(insereProdutosSpedFiscal(leitor, pNota, i, y).getCodItem()) != null) {
					   histItens.setDescricao(leitor.getMpProdTerc().get(insereProdutosSpedFiscal(leitor, pNota, i, y).getCodItem()).getDescrItem());
				   }
				 
			   }
			  
			   
			   histItens.setNumDoc(leitor.getRegsC100().get(i).getNumDoc());
			   histItens.setChaveDoc(leitor.getRegsC100().get(i).getChvNfe());
			   histItens.setNome(leitor.getMpParticipante().get(leitor.getRegsC100().get(i).getCodPart()).getNome());
			   histItens.setCpfCnpj(leitor.getMpParticipante().get(leitor.getRegsC100().get(i).getCodPart()).getCnpj());
			  
			   histItemDao.cadastrarHistoricoItem(histItens);
		   }

	}

	private void totalizadoresItensEntradasTerceirosNF(LeitorTxtSpedFiscal leitor, ProdutoNota pNota, int i, int y)
			throws SQLException {
		
		if(insereProdutosSpedFiscal(leitor, pNota, i, y).getCfop().startsWith("1")
				 || insereProdutosSpedFiscal(leitor, pNota, i, y).getCfop().startsWith("2")) {
			TotalizadoresPorItem tot = new TotalizadoresPorItem();  
			tot.setCodItem(insereProdutosSpedFiscal(leitor, pNota, i, y).getCodItem());
			tot.setVlTotQtde(insereProdutosSpedFiscal(leitor, pNota, i, y).getQtde());
			tot.setVlTotItem(insereProdutosSpedFiscal(leitor, pNota, i, y).getVlItem());
			totaisEntradas.add(tot);
		}

	}
	
	private void insereHistoricoItensEntradasProprias(ProdutoNotaXmlProprio nfP,LeitorTxtSpedFiscal leitor,RegC100 nf,Long idPaiEst) throws SQLException {
		   nf.getNotasFiscaisTxtSpedFiscal(leitor); 
		   histItens.setIdPaiLote(leitor.getReg0000().getId());
		
		   histItens.setEmpresa(leitor.getReg0000().getCnpj()); 
		   histItens.setOperacao("E");
		   histItens.setEcfCx("");
		 
		   histItens.setCodItem(UtilsEConverters.preencheZerosAEsquerda(nfP.getCodItem()));
		   histItens.setQtde(nfP.getQtde());
		   histItens.setUnd(nfP.getUnd());
		   histItens.setVlUnit(nfP.getVlUnit());
		   histItens.setVlBruto(nfP.getVlProd());
		   histItens.setDesconto(nfP.getVlDesc());
		   histItens.setVlLiq(nfP.getVlItem());
		   histItens.setCst(nfP.getCstIcms());
		   histItens.setCfop(nfP.getCfop());
		   histItens.setAliqIcms(0.0);
		
		   histItens.setDescricao(nfP.getDescricao());
		
		   
		   if(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()) != null) {
			   
			   histItens.setIdPai(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getId());
			   histItens.setDtDoc(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getDtEntSai());
			   histItens.setCodMod(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getCodMod());
			   histItens.setNumDoc(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getNumDoc());
			   histItens.setChaveDoc(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getChvNfe());
			   
			   if(leitor.getMpParticipante().get(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getCodPart()) != null) {
				   histItens.setNome(leitor.getMpParticipante().get(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getCodPart()).getNome());
				   histItens.setCpfCnpj(leitor.getMpParticipante().get(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getCodPart()).getCpf());
			  
				   histItemDao.cadastrarHistoricoItem(histItens);
			   }
		   }
		 
		  
		  
		  
	}
	
	
    private  void importandoProdutosXmlNotasPropria(Path pXml,LeitorTxtSpedFiscal leitor, ProdutoNota pNota,RegC100 nf,Long idPaiEmp, Long idPaiEst) throws Exception {
	    nf.getNotasFiscaisTxtSpedFiscal(leitor);

		for(ProdutoNotaXmlProprio nfP :    leitor.getProdutosXMLHandler(pXml)){
			listaCodigosProdutosNoLote.add(UtilsEConverters.preencheZerosAEsquerda(nfP.getCodItem()));
			Produto prod = new Produto(idPaiEmp, idPaiEst, UtilsEConverters.preencheZerosAEsquerda(nfP.getCodItem()), nfP.getDescricao(), nfP.getNcm(), nfP.getUnd());
			if(prod != null) {
				if(!daoProd.getProdutos().contains(prod)){
					daoProd.cadastrar(prod);
				}
			}
			
			if(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()) != null) {
				 daoProdNF.cadastrar(insereProdutosXmlNotasPropria(leitor, pNota,nfP, nf));		
				 
				 if(insereProdutosXmlNotasPropria(leitor, pNota,nfP, nf).getCfop().startsWith("1")
						 || insereProdutosXmlNotasPropria(leitor, pNota,nfP, nf).getCfop().startsWith("2")) {
					 insereHistoricoItensEntradasProprias(nfP,leitor,nf,idPaiEst);
					 totalizadoresItensEntradasPropriasNF(nfP);
				 }
				 
				 if(insereProdutosXmlNotasPropria(leitor, pNota,nfP, nf).getCfop().startsWith("5")
						 || insereProdutosXmlNotasPropria(leitor, pNota,nfP, nf).getCfop().startsWith("6")) {
					 insereHistoricoItensSaidas(nfP,leitor,nf,idPaiEst);
					 totalizadoresItensSaidasNF(nfP);
				 }
				 

			}
			
	   }
			
    }

	private void insereHistoricoItensSaidas(ProdutoNotaXmlProprio nfP, LeitorTxtSpedFiscal leitor, RegC100 nf,Long idPaiEst) throws SQLException {
		   nf.getNotasFiscaisTxtSpedFiscal(leitor); 
		   
		   
		   histItens.setIdPaiLote(leitor.getReg0000().getId());
		 
		   histItens.setEmpresa(leitor.getReg0000().getCnpj());
		   histItens.setOperacao("S");
		   histItens.setEcfCx("");
		
		   histItens.setCodItem(UtilsEConverters.preencheZerosAEsquerda(nfP.getCodItem()));
		   histItens.setQtde(nfP.getQtde());
		   histItens.setUnd(nfP.getUnd());
		   histItens.setVlUnit(nfP.getVlUnit());
		   histItens.setVlBruto(nfP.getVlProd());
		   histItens.setDesconto(nfP.getVlDesc());
		   histItens.setVlLiq(nfP.getVlItem());
		   histItens.setCst(nfP.getCstIcms());
		   histItens.setCfop(nfP.getCfop());
		   histItens.setAliqIcms(0.0);
		 
		   histItens.setDescricao(nfP.getDescricao());
		  
		 
		  
		  if(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()) != null) {
			   if(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getCodPart() != null
					   && leitor.getMpParticipante().get(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getCodPart()) != null) {
				  
				   histItens.setIdPai(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getId());
				   histItens.setDtDoc(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getDtEntSai());
				   histItens.setNumDoc(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getNumDoc());
				   histItens.setCodMod(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getCodMod());
				   histItens.setChaveDoc(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getChvNfe());
				   histItens.setNome(leitor.getMpParticipante().get(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getCodPart()).getNome());
				   histItens.setCpfCnpj(leitor.getMpParticipante().get(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getCodPart()).getCpf());  
			  
				   histItemDao.cadastrarHistoricoItem(histItens);
			   }
		  }

		 
		  
		  
		
	}

	private void totalizadoresItensEntradasPropriasNF(ProdutoNotaXmlProprio nfP) {
		TotalizadoresPorItem tot = new TotalizadoresPorItem();  
		tot.setCodItem(UtilsEConverters.preencheZerosAEsquerda(nfP.getCodItem()));
		tot.setVlTotQtde(nfP.getQtde());
		tot.setVlTotItem(nfP.getVlItem());
		totaisEntradas.add(tot);
	}

	private void totalizadoresItensSaidasNF(ProdutoNotaXmlProprio nfP) {
		TotalizadoresPorItem tot = new TotalizadoresPorItem();  
		tot.setCodItem(UtilsEConverters.preencheZerosAEsquerda(nfP.getCodItem()));
		tot.setVlTotQtde(nfP.getQtde());
		tot.setVlTotItem(nfP.getVlItem());
		totaisSaidas.add(tot);
	}  
   
   
	private void importandoReducoesZ(LeitorTxtSpedFiscal leitor, EquipamentoECF ecf, ReducaoZ rdz, TotParciaisRDZ totRdz,
			ItensMovDiario itensCF, TotalizadorDiarioCuponsFiscais totDirCF, Long id, Long idPaiEmp, Long idPaiEst) throws SQLException {
		for (int i = 0; i < leitor.getRegsC400().size(); i++) {

			 for (int x = 0; x < leitor.getRegsC400().get(i).getRegsC405().size(); x++) {
				    daoRDZ.cadastrar(insereReducoesZ(leitor, rdz, id, i,x, idPaiEmp, idPaiEst));
				  
			    for (int y = 0; y < leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().size(); y++) {
					 daoTotParRDZ.cadastrar(insereTotParcRDZ(leitor, totRdz, i, x,y, idPaiEmp,idPaiEst));
					for (int w = 0; w < leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y)
							.getRegsC425().size(); w++) {
						 totalizadoresItensCF(leitor, itensCF, idPaiEmp, idPaiEst, i, x, y, w);
						   daoItensMovDiario.cadastrar(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst));
						 
						   insereHistoticoItensCF(leitor, itensCF, idPaiEmp, idPaiEst, i, x, y, w);
					
					}
				}
				
			    for (int z = 0; z < leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC490().size(); z++) {
					totDiarioCF.cadastrar(insereTotalizadorDiarioCF(leitor, totDirCF, i, x, z,idPaiEmp, idPaiEst));
				}
			}
			 
			
		}
	}

	private void insereHistoticoItensCF(LeitorTxtSpedFiscal leitor, ItensMovDiario itensCF, Long idPaiEmp, Long idPaiEst, int i,
			int x, int y, int w) throws SQLException {
		   histItens.setIdPaiLote(leitor.getReg0000().getId());
		   histItens.setIdPai(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getIdPai());
		   histItens.setEmpresa(leitor.getReg0000().getCnpj()); // MOckado
		   histItens.setOperacao("S");
		   histItens.setEcfCx(leitor.getMpEquipEcf().get(leitor.getRegsC400().get(i).getId()).getNumCaixaECF());
		   histItens.setDtDoc(leitor.getMpReducao().get(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getIdPaiRedZ()).getDtDoc());
		   histItens.setCodItem(UtilsEConverters.preencheZerosAEsquerda(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getCodItem()));
		   histItens.setQtde(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getQtde());
		   histItens.setUnd(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getUnd());
		   histItens.setVlUnit(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getVlItem()/insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getQtde());
		   histItens.setVlBruto(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getVlItem());
		   histItens.setDesconto(0.0);
		   histItens.setVlLiq(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getVlItem());
		   
		   if(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getIdPaiRedZ() != null) {
			   histItens.setCst(leitor.getMpTotalizadorCF().get(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getIdPaiRedZ()).getCstIcms());
		   }
		 
		  
		   histItens.setCfop(leitor.getMpTotalizadorCF().get(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getIdPaiRedZ()).getCfop());
		   histItens.setCodMod(leitor.getMpEquipEcf().get(leitor.getRegsC400().get(i).getId()).getCodModelo());
		   histItens.setDescricao(leitor.getMpProdTerc().get(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getCodItem()).getDescrItem());
		   histItens.setNumDoc(leitor.getMpReducao().get(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getIdPaiRedZ()).getNumCOOFin());
		   histItens.setChaveDoc("");
		   histItens.setNome("");
		   histItens.setCpfCnpj("");
		   
		   histItemDao.cadastrarHistoricoItem(histItens);
	}

	private void totalizadoresItensCF(LeitorTxtSpedFiscal leitor, ItensMovDiario itensCF, Long idPaiEmp, Long idPaiEst,
			int i, int x, int y, int w) {
		TotalizadoresPorItem tot = new TotalizadoresPorItem(); 
		 tot.setCodItem(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getCodItem());
		 tot.setVlTotQtde(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getQtde());
		 tot.setVlTotItem(insereItensMovDiario(leitor, itensCF, i, x, y, w,idPaiEmp, idPaiEst).getVlItem());
		 totaisSaidas.add(tot);
	}
	
	
   private void importandoEquipamentoCFe(LeitorTxtSpedFiscal leitor,EquipamentoCFe cfe,Long idPai, Long idPaiEmp, Long idPaiEst) throws SQLException {
		for(int i = 0; i < leitor.getRegsC860().size();i++) {
			 
			 equipDaoCfe.cadastrar(insereEquipamentoCFe(leitor,cfe,i,idPai,idPaiEmp, idPaiEst));
		}
		
		
   }

   private void importandoItensCFe(Path pXml,LeitorTxtSpedFiscal leitor,ItensMovDiarioCFe itenCfe,Long lote,Long idPaiEmp, Long idPaiEst) throws Exception {

	   Map<String,ProdutoCupomFiscalXml> mpCFe = new HashMap<String, ProdutoCupomFiscalXml>();
		try {
			for (ProdutoCupomFiscalXml cf : leitor.getProdutosXMLHandlerCF(pXml)) {
				
				listaCodigosProdutosNoLote.add( UtilsEConverters.preencheZerosAEsquerda(cf.getCodItem()));
			    Produto prod = new Produto(idPaiEmp, idPaiEst, UtilsEConverters.preencheZerosAEsquerda(cf.getCodItem()), cf.getDescricao(), cf.getNcm(), cf.getUnd());
				if(prod != null) {
					if(!daoProd.getProdutos().contains(prod)){
						daoProd.cadastrar(prod);		
					}
				}
				   itensCFe.cadastrar(insereItensCFe(cf, itenCfe,lote, idPaiEmp, idPaiEst));
				   totalizadoresItensCFE(insereItensCFe(cf, itenCfe,lote,idPaiEmp, idPaiEst));
				   insereHistoricoItensCFe(leitor, itenCfe, lote, idPaiEmp, idPaiEst, mpCFe, cf);
				 
			}
		}catch (NumberFormatException e) {
			//System.out.println(e.getMessage());
		}	
   }


   
   private void insereHistoricoItensCFe(LeitorTxtSpedFiscal leitor, ItensMovDiarioCFe itenCfe,Long lote, Long idPaiEmp,
			Long idPaiEst, Map<String, ProdutoCupomFiscalXml> mpCFe, ProdutoCupomFiscalXml cf) throws SQLException {
		   histItens.setIdPaiLote(lote);
		   histItens.setIdPai(getIdEquipSat(Long.valueOf(cf.getNumDoc().toString()), lote));
		   histItens.setEmpresa(leitor.getReg0000().getCnpj()); // MOckado
		   histItens.setOperacao("S");
		   histItens.setEcfCx(leitor.getMpEquipCFe().get(getIdEquipSat(Long.valueOf(cf.getNumDoc().toString()), lote)).getNumSerieEquipSat());
		   histItens.setDtDoc(leitor.getMpEquipCFe().get(getIdEquipSat(Long.valueOf(cf.getNumDoc().toString()), lote)).getDtEmissao());
		   histItens.setCodItem( UtilsEConverters.preencheZerosAEsquerda(cf.getCodItem()));
		   histItens.setQtde(cf.getQtde());
		   histItens.setUnd(cf.getUnd());
		   histItens.setVlUnit(cf.getVlUnit());
		   histItens.setVlBruto(cf.getVlProd());
		   histItens.setDesconto(cf.getVlDesc());
		   histItens.setVlLiq(cf.getVlItem());
		   histItens.setCst(cf.getCstIcms());
		   histItens.setCfop(cf.getCfop());
		   histItens.setCodMod(leitor.getMpEquipCFe().get(getIdEquipSat(Long.valueOf(cf.getNumDoc().toString()), lote)).getCodModDocFiscal());
		  
		   if(mpCFe.get( UtilsEConverters.preencheZerosAEsquerda(cf.getCodItem())) != null) {
			   histItens.setDescricao(mpCFe.get( UtilsEConverters.preencheZerosAEsquerda(cf.getCodItem())).getDescricao());
		   }
		 
		   
		   histItens.setNumDoc(cf.getNumDoc());
		   histItens.setChaveDoc(cf.getChaveCF());
		   histItens.setNome("");
		   histItens.setCpfCnpj("");
		   
		   histItemDao.cadastrarHistoricoItem(histItens);
	}

	private void totalizadoresItensCFE(ItensMovDiarioCFe itnCfe) throws SQLException {
		 TotalizadoresPorItem tot = new TotalizadoresPorItem(); 
		 tot.setCodItem(itnCfe.getCodItem());
		 tot.setVlTotQtde(itnCfe.getQtde());
		 tot.setVlTotItem(itnCfe.getVlItem());
		 totaisSaidas.add(tot);
	}
   
   private void importandoInventario(LeitorTxtSpedFiscal leitor,Inventario inv, ItensInventario itnInv) throws SQLException {
	   
	   for(int i=0; i < leitor.getRegsH005().size(); i++) {
		      invDao.cadastrar(insereInventario(inv, leitor, i));
	   }
	   
	   importandoItensInventario(leitor, itnInv);
   }
   
   private void importandoItensInventario(LeitorTxtSpedFiscal leitor, ItensInventario itnInv) throws SQLException {
	   for(int i=0; i < leitor.getRegsH005().size(); i++) {
		   for(int x=0; x < leitor.getRegsH005().get(i).getListaItensInv().size() ; x++) {
			   
			   itnInvDao.cadastrar(insereItensInventario(itnInv, leitor, i, x));
		   }
	   }
   }

   private Participante insereParticipantes(LeitorTxtSpedFiscal leitor,Participante part,int i,Long idPaiEmp, Long idPaiEst) {
	   
	   part.setIdPaiEmp(idPaiEmp);
	   part.setIdpaiEst(idPaiEst);
	   part.setCodPart(leitor.getRegs0150().get(i).getCodPart());
	   part.setNome(leitor.getRegs0150().get(i).getNome());
	   part.setCodPais(leitor.getRegs0150().get(i).getCodPais());
	   part.setCnpjCpf(leitor.getRegs0150().get(i).getCnpj());
	   part.setCnpjCpf(leitor.getRegs0150().get(i).getCpf());
	   part.setInscEstadual(leitor.getRegs0150().get(i).getIe());
	   part.setCodMunicipio(leitor.getRegs0150().get(i).getCodMun());
	   part.getEndereco().setNmLogradouro(leitor.getRegs0150().get(i).getEndereco());
	   part.getEndereco().setNumLogradouro(leitor.getRegs0150().get(i).getNum());
	   part.getEndereco().setCompl(leitor.getRegs0150().get(i).getCompl());
	   part.getEndereco().setBairro(leitor.getRegs0150().get(i).getBairro());
	   
	   
	   
	   return part;
   }
    
	private Produto insereProdutos(LeitorTxtSpedFiscal leitor,Produto prod, int i,Long idPaiEmp, Long idPaiEst) {
		
		prod.setIdPaiEmp(idPaiEmp);
		prod.setIdPaiEst(idPaiEst);
		prod.setIdGrp(1L);//TODO campo mockado
		prod.setTipoItem(leitor.getRegs0200().get(i).getTipoItem()); 
		prod.setCodUtilizEmp(leitor.getRegs0200().get(i).getCodItem());
		prod.setDescricao(leitor.getRegs0200().get(i).getDescrItem());
		prod.setNcm(leitor.getRegs0200().get(i).getCodNcm());
		prod.setUndPadrao(leitor.getRegs0200().get(i).getUnidInv());
		prod.setCodBarras(leitor.getRegs0200().get(i).getCodBarra());
		prod.setCest(leitor.getRegs0200().get(i).getCest());
		prod.setProdEspec(""); //TODO mockado pois não tem no sped, faz parte da regra do sistema
		prod.setTipoMed(""); //TODO mockado pois não tem no sped, faz parte da regra do sistema
		
		
		return prod;
	}
	
	private OutrasUnid insereOutUndMedidas(LeitorTxtSpedFiscal leitor, OutrasUnid outUnd,int i,int x ,Long idPaiEmp, Long idPaiEst) {
		
	    outUnd.setIdPaiEmp(idPaiEmp);
	    outUnd.setIdPaiEst(idPaiEst);
	    
	    outUnd.setIdPai(idPaiReg0200(leitor.getRegs0200().get(i).getOutrasUndMedidas().get(x).getCodItem(),idPaiEmp,idPaiEst));
	    
	    outUnd.setUndMed(leitor.getRegs0200().get(i).getOutrasUndMedidas().get(x).getUndConv());
	    outUnd.setUndEquivPadrao(leitor.getRegs0200().get(i).getOutrasUndMedidas().get(x).getFatConv());
		
		return outUnd;
	}
	
	private AlteracaoItem insereAlteracaoItem(LeitorTxtSpedFiscal leitor, AlteracaoItem altItem,int i,int y,Long idPaiEmp, Long idPaiEst) {
		
		altItem.setIdPaiEmp(idPaiEmp);
		altItem.setIdPaiEst(idPaiEst);
        altItem.setIdPai(idPaiReg0200(leitor.getRegs0200().get(i).getAlteracaoItem().get(y).getCodItem(), idPaiEmp, idPaiEst));
		altItem.setDescrAntItem(leitor.getRegs0200().get(i).getAlteracaoItem().get(y).getDescrAntItem());
		altItem.setDtInicial(leitor.getRegs0200().get(i).getAlteracaoItem().get(y).getDtIni());
		altItem.setDtFinal(leitor.getRegs0200().get(i).getAlteracaoItem().get(y).getDtFim());
		
		if(leitor.getRegs0200().get(i).getAlteracaoItem().get(y).getCodAntItem() != null) {
			altItem.setCodAntItem(leitor.getRegs0200().get(i).getAlteracaoItem().get(y).getCodAntItem());
		}
		

		
		return altItem;
	}
	
	private NotaFiscal insereNotaFiscal(LeitorTxtSpedFiscal leitor, NotaFiscal nota, int i, Long idPai,Long idPaiEmp, Long idPaiEst) throws SQLException {
		nota.setIdPaiEmp(idPaiEmp);
		nota.setIdPaiEst(idPaiEst);
		nota.setIdPai(idPai);
		nota.setTipoOperacao(leitor.getRegsC100().get(i).getIndOper());
		nota.setTipoEmissor(leitor.getRegsC100().get(i).getIndEmit());
		nota.setCodParticipante(leitor.getRegsC100().get(i).getCodPart());
		nota.setNumeroDocumento(leitor.getRegsC100().get(i).getNumDoc());
		nota.setChaveDocumento(leitor.getRegsC100().get(i).getChvNfe());
		nota.setDataEmisaso(leitor.getRegsC100().get(i).getDtDoc());
		nota.setDataEntSai(leitor.getRegsC100().get(i).getDtEntSai());
		nota.setVlTotalProd(leitor.getRegsC100().get(i).getVlDoc());
		nota.setVlDesconto(leitor.getRegsC100().get(i).getVlDesc());
		nota.setVlMercadorias(leitor.getRegsC100().get(i).getVlMerc());
		
		return nota;
	}

	
	private ProdutoNota insereProdutosXmlNotasPropria(LeitorTxtSpedFiscal leitor, ProdutoNota pNota, ProdutoNotaXmlProprio nfP,RegC100 nf) throws SQLException {

		
		pNota.setIdPai(nf.buscaIDNotaFiscalPorChaveEletronica().get(nfP.getChaveNota()).getId());
		pNota.setNumItem(nfP.getNumItem());
		pNota.setCodItem(UtilsEConverters.preencheZerosAEsquerda(nfP.getCodItem()));
		pNota.setQtde(nfP.getQtde());
		pNota.setUnd(nfP.getUnd());
		pNota.setVlUnitItem(nfP.getVlUnit());
		pNota.setVlProd(nfP.getVlProd());
		pNota.setVlDesc(nfP.getVlDesc());
		pNota.setVlItem(nfP.getVlItem()); 
		pNota.setCstIcms(nfP.getCstIcms());
		pNota.setCfop(nfP.getCfop());
		return pNota;
	}

	
	
	
	private ProdutoNota insereProdutosSpedFiscal(LeitorTxtSpedFiscal leitor, ProdutoNota pNota, int i,int y) throws SQLException {
		Double desc = (leitor.getRegsC100().get(i).getProdutosNota().get(y).getVlDesc()==null ? 0.0 : leitor.getRegsC100().get(i).getProdutosNota().get(y).getVlDesc());
		
		Double vlUnit=0.0;
		if(leitor.getRegsC100().get(i).getProdutosNota().get(y).getVlItem() != 0.0 
				 && leitor.getRegsC100().get(i).getProdutosNota().get(y).getQtd() != 0.0) {
			
			vlUnit = leitor.getRegsC100().get(i).getProdutosNota().get(y).getVlItem()/leitor.getRegsC100().get(i).getProdutosNota().get(y).getQtd();
		}
		
		
		pNota.setIdPai(leitor.getRegsC100().get(i).getId());
		pNota.setNumItem(leitor.getRegsC100().get(i).getProdutosNota().get(y).getNumItem());
		pNota.setCodItem(leitor.getRegsC100().get(i).getProdutosNota().get(y).getCodItem());
		pNota.setQtde(leitor.getRegsC100().get(i).getProdutosNota().get(y).getQtd());
		pNota.setUnd(leitor.getRegsC100().get(i).getProdutosNota().get(y).getUnid());
		pNota.setVlProd(leitor.getRegsC100().get(i).getProdutosNota().get(y).getVlItem());
		pNota.setVlUnitItem((vlUnit==null ? 0.0 : vlUnit));
		pNota.setVlDesc(desc);
		pNota.setVlItem(leitor.getRegsC100().get(i).getProdutosNota().get(y).getVlItem()-
				desc);
		pNota.setCstIcms(leitor.getRegsC100().get(i).getProdutosNota().get(y).getCstIcms());
		pNota.setCfop(leitor.getRegsC100().get(i).getProdutosNota().get(y).getCfop());
		
		return pNota;
	}
	
	private ProdutoNota insereProdutosSpedFiscalSaidas(LeitorTxtSpedFiscal leitor, ProdutoNota pNota, int i,int y) throws SQLException {
        
		if(leitor.getRegsC100().get(i).getProdutosNota().get(y).getCfop().startsWith("5")
				|| leitor.getRegsC100().get(i).getProdutosNota().get(y).getCfop().startsWith("6")) {
			
			Double desc = (leitor.getRegsC100().get(i).getProdutosNota().get(y).getVlDesc()==null ? 0.0 : leitor.getRegsC100().get(i).getProdutosNota().get(y).getVlDesc());
			
			Double vlUnit=0.0;
			if(leitor.getRegsC100().get(i).getProdutosNota().get(y).getVlItem() != 0.0 
					 && leitor.getRegsC100().get(i).getProdutosNota().get(y).getQtd() != 0.0) {
				
				vlUnit = leitor.getRegsC100().get(i).getProdutosNota().get(y).getVlItem()/leitor.getRegsC100().get(i).getProdutosNota().get(y).getQtd();
			}
			
			
			pNota.setIdPai(leitor.getRegsC100().get(i).getId());
			pNota.setNumItem(leitor.getRegsC100().get(i).getProdutosNota().get(y).getNumItem());
			pNota.setCodItem(leitor.getRegsC100().get(i).getProdutosNota().get(y).getCodItem());
			pNota.setQtde(leitor.getRegsC100().get(i).getProdutosNota().get(y).getQtd());
			pNota.setUnd(leitor.getRegsC100().get(i).getProdutosNota().get(y).getUnid());
			pNota.setVlProd(leitor.getRegsC100().get(i).getProdutosNota().get(y).getVlItem());
			pNota.setVlUnitItem((vlUnit==null ? 0.0 : vlUnit));
			pNota.setVlDesc(desc);
			pNota.setVlItem(leitor.getRegsC100().get(i).getProdutosNota().get(y).getVlItem()-
					desc);
			pNota.setCstIcms(leitor.getRegsC100().get(i).getProdutosNota().get(y).getCstIcms());
			pNota.setCfop(leitor.getRegsC100().get(i).getProdutosNota().get(y).getCfop());
			
			
		}
		return pNota;
		
	}
	public Long idECFRegC405(String numFab,Pool pool) {
		Long id = 0L;
		daoECF = new EquipamentoECFDao(pool);
		try {
			id = daoECF.getEquipamentosFiscaisPorNumFab().get(numFab).getId();
		}catch (Exception e) {
			
		}
		return id;
	}
	
	private ReducaoZ insereReducoesZ(LeitorTxtSpedFiscal leitor,ReducaoZ rdz, Long idPai,int i,int x,Long idPaiEmp, Long idPaiEst) {
		
		rdz.setId_pai_emp(idPaiEmp);
		rdz.setId_pai_est(idPaiEst);
	    rdz.setIdPai(idPai);
		rdz.setId_ecf(leitor.getRegsC400().get(i).getRegsC405().get(x).getIdPaiECF());
		rdz.setDtReducaoZ(leitor.getRegsC400().get(i).getRegsC405().get(x).getDtDoc());
		rdz.setPosicaoCRO(leitor.getRegsC400().get(i).getRegsC405().get(x).getPosicaoCRO());
		rdz.setPosicaoRDZ(leitor.getRegsC400().get(i).getRegsC405().get(x).getPosicaoRDZ());
		rdz.setNumCOO(leitor.getRegsC400().get(i).getRegsC405().get(x).getNumCOOFin());
		rdz.setVlGrandeTotal(BigDecimal.valueOf(leitor.getRegsC400().get(i).getRegsC405().get(x).getVlGrandeTotalFinal()));
		rdz.setVlVendaBruta(BigDecimal.valueOf(leitor.getRegsC400().get(i).getRegsC405().get(x).getVlVendaBruta()));
		
		
		return rdz;
	}
	
	private TotParciaisRDZ insereTotParcRDZ(LeitorTxtSpedFiscal leitor,TotParciaisRDZ totRdz, int i,int x,int y,Long idPaiEmp, Long idPaiEst) { 
		
		totRdz.setId_pai_emp(idPaiEmp);
		totRdz.setId_pai_est(idPaiEst);
		totRdz.setIdPai(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getIdPai());
		totRdz.setCodTotalizador(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getCodTotPar());
		totRdz.setVlAcumuladoTotRedZ(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getVlAcumTot());
		totRdz.setNumTotalizador(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getNrTot());
		totRdz.setDescNumTotalizador(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getDescrNrTot());
		
		return totRdz;
	}
	
	private ItensMovDiario insereItensMovDiario(LeitorTxtSpedFiscal leitor,ItensMovDiario itensCF, int i,int x,int y,int w,Long idPaiEmp, Long idPaiEst) {
		
		itensCF.setId_pai_emp(idPaiEmp);
		itensCF.setId_pai_est(idPaiEst);
		itensCF.setIdPai( leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getIdPai());
		itensCF.setIdPaiRedZ( leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getIdPaiRedZ());
		itensCF.setCodItem(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getCodItem());
		itensCF.setQtde( leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getQtd());
		itensCF.setUnd(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getUnd());
		itensCF.setVlItem(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getVlItem());
		itensCF.setVlPis(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getVlPis());
		itensCF.setVlCofins(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC420().get(y).getRegsC425().get(w).getVlCofins());
		
		return itensCF;
	}
	
	private TotalizadorDiarioCuponsFiscais insereTotalizadorDiarioCF(LeitorTxtSpedFiscal leitor,TotalizadorDiarioCuponsFiscais totDirCF, int i,int x,int z,Long idPaiEmp, Long idPaiEst) {
		
		totDirCF.setId_pai_emp(idPaiEmp);
		totDirCF.setId_pai_est(idPaiEst);
		totDirCF.setIdPai( leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC490().get(z).getIdPai());
		totDirCF.setCfop(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC490().get(z).getCfop());
		totDirCF.setCst(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC490().get(z).getCstIcms());
		totDirCF.setVlOperacao(leitor.getRegsC400().get(i).getRegsC405().get(x).getRegsC490().get(z).getVlOperacao());
		
		return totDirCF; 
	}
	

	private EquipamentoCFe insereEquipamentoCFe(LeitorTxtSpedFiscal leitor, EquipamentoCFe cfe, int i,Long idPai, Long idPaiEmp,
				Long idPaiEst) {
			
		cfe.setId_pai_emp(idPaiEmp);
		cfe.setId_pai_est(idPaiEst);
		cfe.setIdPai(idPai);
		cfe.setCodModDocFiscal(leitor.getRegsC860().get(i).getCodModDocFiscal());
		cfe.setNumSerieEquipSat(leitor.getRegsC860().get(i).getNumSerieEquipSat());
		cfe.setDtEmissao(leitor.getRegsC860().get(i).getDtEmissao());
		cfe.setDocInicial(leitor.getRegsC860().get(i).getDocInicial());
		cfe.setDocFinal(leitor.getRegsC860().get(i).getDocFinal());
		
		
		
		return cfe;
	}
	
	private ItensMovDiarioCFe insereItensCFe(ProdutoCupomFiscalXml cf ,ItensMovDiarioCFe itenCfe,Long lote,Long idPaiEmp,
			Long idPaiEst) throws NumberFormatException, SQLException {
	
			    itenCfe.setIdPaiEmp(idPaiEmp);	
				itenCfe.setIdPaiEst(idPaiEst);
				itenCfe.setIdPai(getIdEquipSat(Long.valueOf(cf.getNumDoc().toString()), lote));
				itenCfe.setNumCFe(cf.getNumDoc());
				itenCfe.setNumItem(cf.getNumItem());
				itenCfe.setCodItem(UtilsEConverters.preencheZerosAEsquerda(cf.getCodItem()));
				itenCfe.setQtde(cf.getQtde());
				itenCfe.setUnd(cf.getUnd());
				itenCfe.setVlUnit(cf.getVlUnit());
				itenCfe.setVlProd(cf.getVlProd());
				itenCfe.setVlDesc(cf.getVlDesc());
				itenCfe.setVlItem(cf.getVlItem());
				itenCfe.setCstIcms(cf.getCstIcms());
				itenCfe.setCfop(cf.getCfop());
				itenCfe.setChaveCFe(cf.getChaveCF());

		return itenCfe;
	}
	
	private Long getIdEquipSat(Long numDoc, Long lote) throws SQLException {
		Long id = 0L;
		for(EquipamentoCFe cfe : equipDaoCfe.getIdEquip(lote)){			
			if(numDoc >= Long.valueOf(cfe.getDocInicial()) && numDoc <= Long.valueOf(cfe.getDocFinal())){
				id = cfe.getId();
			}
		}
		return id;
	}

	private Inventario insereInventario(Inventario inv,LeitorTxtSpedFiscal leitor,int i) {
		
	    inv.setIdPai(leitor.getRegsH005().get(i).getIdPai());
	    inv.setDataInv(leitor.getRegsH005().get(i).getDataInv());
	    inv.setVlTotal(leitor.getRegsH005().get(i).getVlTotEstoque());
	    inv.setMotivoInventario(leitor.getRegsH005().get(i).getMotivoInventario());
		
		return inv;
	}
	
	private ItensInventario insereItensInventario(ItensInventario itnInv,LeitorTxtSpedFiscal leitor,int i,int x ) {
		
		itnInv.setIdPai(leitor.getRegsH005().get(i).getListaItensInv().get(x).getIdPai());
		itnInv.setCodItem(leitor.getRegsH005().get(i).getListaItensInv().get(x).getCodItem());
		itnInv.setUnd(leitor.getRegsH005().get(i).getListaItensInv().get(x).getUnd());
		itnInv.setQtde(leitor.getRegsH005().get(i).getListaItensInv().get(x).getQtde());
		itnInv.setVlUnit(leitor.getRegsH005().get(i).getListaItensInv().get(x).getVlUnit());
		itnInv.setVlItem(leitor.getRegsH005().get(i).getListaItensInv().get(x).getVlItem());
		itnInv.setIndProp(leitor.getRegsH005().get(i).getListaItensInv().get(x).getIndProp());
		itnInv.setCodPart(leitor.getRegsH005().get(i).getListaItensInv().get(x).getCodPart());
		itnInv.setTxtCompl(leitor.getRegsH005().get(i).getListaItensInv().get(x).getTxtCompl());
		itnInv.setCodCta(leitor.getRegsH005().get(i).getListaItensInv().get(x).getCodCtda());
		itnInv.setVlItemIr(leitor.getRegsH005().get(i).getListaItensInv().get(x).getVlItemIR());
		
		return itnInv;
	}
    
	public Map<String,TotalizadoresPorItem> getTotalizaValoresPorItnEnt(ArrayList<TotalizadoresPorItem> c,Long lote) throws SQLException {
		Map<String,TotalizadoresPorItem> mpTotalizaValoresPorItnEnt = new HashMap<String, TotalizadoresPorItem>();
		while (c.size() != 0){
			 
		int count = 0;
		Long numlote = lote;
		int ano = daoLote.getLote(Integer.valueOf(lote.toString())).getDtIni().getYear();
		int mes = daoLote.getLote(Integer.valueOf(lote.toString())).getDtIni().getMonthValue();
		String cnpj = daoLote.getLote(Integer.valueOf(lote.toString())).getCnpj();
		Double totQtde = 0.0;
		Double totitem = 0.0;
		String codItem = c.get(0).getCodItem();
	
		        for (int i = 0; i < c.size();)
		        {
		           
		            if(codItem.equals(c.get(i).getCodItem()))
		            {
		                count++;
		                totQtde += c.get(i).getVlTotQtde();
		                totitem += c.get(i).getVlTotItem();
		                c.remove(i);
		            }
		            else { i++; }
		           
		        }
		        TotalizadoresPorItem ent = new TotalizadoresPorItem(numlote,cnpj,ano ,mes,Long.valueOf(count), codItem, totQtde, totitem);
		        mpTotalizaValoresPorItnEnt.put(codItem,ent);
		        totPorItensEntDAO.salvar(ent);
		        System.out.println("Lote: " + numlote + " Nome: " + codItem + " Qtde: " + totQtde + " Valor total " + totitem);
  	
	   }
		
		return mpTotalizaValoresPorItnEnt;
	}
	
	public Map<String,TotalizadoresPorItem> getTotalizaValoresPorItnSai(ArrayList<TotalizadoresPorItem> c,Long lote) throws SQLException {
		Map<String,TotalizadoresPorItem> mpTotalizaValoresPorItnSai = new HashMap<String, TotalizadoresPorItem>();
		while (c.size() != 0){
		int count = 0;
		Long numlote = lote;
		int ano = daoLote.getLote(Integer.valueOf(lote.toString())).getDtIni().getYear();
		int mes = daoLote.getLote(Integer.valueOf(lote.toString())).getDtIni().getMonthValue();
		String cnpj = daoLote.getLote(Integer.valueOf(lote.toString())).getCnpj();
		Double totQtde = 0.0;
		Double totitem = 0.0;
		String codItem = c.get(0).getCodItem();
		
		        for (int i = 0; i < c.size();)
		        {
		           
		        
			            if(codItem.equals(c.get(i).getCodItem()))
			            {
			                count++;
			               // numlote  = Long.valueOf(ltImp.getLotes().size());
			                totQtde += c.get(i).getVlTotQtde();
			                totitem += c.get(i).getVlTotItem();
			                c.remove(i);
			            }
			            else { i++;
			            
			            }
		        }
		        TotalizadoresPorItem sai = new TotalizadoresPorItem(numlote,cnpj,ano ,mes,Long.valueOf(count), codItem, totQtde, totitem);
		        mpTotalizaValoresPorItnSai.put(codItem,sai);
		        totPorItensSaiDAO.salvar(sai);
		    	 System.out.println("Lote: " + numlote + " Nome: " + codItem + " Qtde: " + totQtde + " Valor total " + totitem);
		   
		       
	   }
		
		return mpTotalizaValoresPorItnSai;
	}
	
	public void inserindoMovimentacoesMensaisEntradasSaidasPorLote(Set<String> codigosDosProdutos, 
			       Map<String,TotalizadoresPorItem> mpEntradas,  Map<String,TotalizadoresPorItem> mpSaidas, Long idLote) throws SQLException {
		

		for(String codigo : codigosDosProdutos) {

			EntradasSaidasDeProdutos obj = new EntradasSaidasDeProdutos();
			
			obj.setIdPai(idLote);
			obj.setIdCodItem(daoProd.getProdutoPorCodUtiliz(codigo).getId());
		    obj.setCnpj(daoLote.getLote(Integer.valueOf(idLote.toString())).getCnpj());
			obj.setDescricao(daoProd.getProdutoPorCodUtiliz(codigo).getDescricao());
		    obj.setAno(String.valueOf(daoLote.getLote(Integer.valueOf(idLote.toString())).getDtIni().getYear()));
		    obj.setMes(String.valueOf(daoLote.getLote(Integer.valueOf(idLote.toString())).getDtIni().getMonthValue()));
			obj.setCodItem(daoProd.getProdutoPorCodUtiliz(codigo).getCodUtilizEmp());

		    obj.setCodAntItem(daoAltItem.getAlteracaoItemPorIdProd(Integer.valueOf(daoProd.getProdutoPorCodUtiliz(codigo).getId().toString())).getCodAntItem());
			
		    if(mpEntradas.get(codigo) != null) {
		    	  obj.setTotQtdeEnt(mpEntradas.get(codigo).getVlTotQtde());
		    	  obj.setTotVlItemEnt(mpEntradas.get(codigo).getVlTotItem());
		    }else {
		    	  obj.setTotQtdeEnt(0.0);	
		    	  obj.setTotVlItemEnt(0.0);
		    }
		  
		    if(mpSaidas.get(codigo) != null) {
		    	
		    	  obj.setTotQtdeSai(mpSaidas.get(codigo).getVlTotQtde());
		    	  obj.setTotVlItemSai(mpSaidas.get(codigo).getVlTotItem());
		    }else {
		    	  obj.setTotQtdeSai(0.0);
		    	  obj.setTotVlItemSai(0.0);
		    }
	
            
		    if((obj.getTotQtdeEnt() + obj.getTotQtdeSai()) > 0.0) {
		    	inserirMovimentacaoNaRespectivaTabela(obj);
		    }
		   
		}
		
	}
	private void inserirMovimentacaoNaRespectivaTabela(EntradasSaidasDeProdutos obj) throws SQLException {
		  switch (obj.getMes()) {
			case "1":
				entsai.cadastrarTabEntSaiJan(obj);
				break;
			case "2":
				entsai.cadastrarTabEntSaiFev(obj);
				break;
			case "3":
				entsai.cadastrarTabEntSaiMar(obj);
				break;
			case "4":
				entsai.cadastrarTabEntSaiAbr(obj);
				break;
			case "5":
				entsai.cadastrarTabEntSaiMai(obj);
				break;
			case "6":
				entsai.cadastrarTabEntSaiJun(obj);
				break;
			case "7":
				entsai.cadastrarTabEntSaiJul(obj);
				break;
			case "8":
				entsai.cadastrarTabEntSaiAgo(obj);
				break;
			case "9":
				entsai.cadastrarTabEntSaiSet(obj);
				break;
			case "10":
				entsai.cadastrarTabEntSaiOut(obj);
				break;
			case "11":
				entsai.cadastrarTabEntSaiNov(obj);
				break;
			case "12":
				entsai.cadastrarTabEntSaiDez(obj);
				break;
			}
		
	}

	public Long idPaiReg0200(String codItem,Long idPaiEmp,Long idPaiEst)  {
		Long id = 0L;
		try {
			if(daoProd.getMpProdutos(idPaiEmp,idPaiEst).get(codItem) != null) {
				id = daoProd.getMpProdutos(idPaiEmp,idPaiEst).get(codItem).getId();
			}
		}catch (Exception e) {
			
		}		
		return id;
	}

	public Set<String> getListaCodigosProdutosNoLote() {
		return listaCodigosProdutosNoLote;
	}
	
	public ArrayList<TotalizadoresPorItem> getTotaisEntradas() {
		return totaisEntradas;
	}

	public ArrayList<TotalizadoresPorItem> getTotaisSaidas() {
		return totaisSaidas;
	}
	
	
}
