package versaogama.managersped;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import versaogama.conexao.Pool;
import versaogama.dao.estabelecimentodao.equipcfe.EquipamentoCFeDao;
import versaogama.dao.estabelecimentodao.equipecf.EquipamentoECFDao;
import versaogama.dao.estabelecimentodao.equipecf.ReducaoZDao;
import versaogama.dao.estabelecimentodao.equipecf.TotParciaisRDZDao;
import versaogama.dao.estabelecimentodao.importacaospedfical.LoteImportacaoSpedFiscalDao;
import versaogama.dao.estabelecimentodao.notafiscal.NotaFiscalDao;
import versaogama.dao.estabelecimentodao.participantes.ParticipanteDao;
import versaogama.dao.estabelecimentodao.produto.ProdutoDao;
import versaogama.managerxml.LeitorXML;
import versaogama.model.sped.Reg0000;
import versaogama.model.sped.Reg0150;
import versaogama.model.sped.Reg0190;
import versaogama.model.sped.Reg0200;
import versaogama.model.sped.Reg0205;
import versaogama.model.sped.Reg0220;
import versaogama.model.sped.RegC100;
import versaogama.model.sped.RegC170;
import versaogama.model.sped.RegC400;
import versaogama.model.sped.RegC405;
import versaogama.model.sped.RegC420;
import versaogama.model.sped.RegC425;
import versaogama.model.sped.RegC490;
import versaogama.model.sped.RegC860;
import versaogama.model.sped.RegE200;
import versaogama.model.sped.RegE210;
import versaogama.model.sped.RegE220;
import versaogama.model.sped.RegE240;
import versaogama.model.sped.RegE250;
import versaogama.model.sped.RegH005;
import versaogama.model.sped.RegH010;
import versaogama.model.xml.ProdutoCupomFiscalXml;
import versaogama.model.xml.ProdutoNotaXmlProprio;
import versaogama.util.UtilsEConverters;

public class LeitorTxtSpedFiscal {

	protected Long id0000     = 0L;
	protected Long id0150     = 0L;  
	protected Long idPai0150  = 0L;
	protected Long id0190     = 0L;  
	protected Long idPai0950  = 0L;
	protected Long id0200     = 0L;
	protected Long idPai0200  = 0L;
	protected Long id0220     = 0L;  
	protected Long idPai0220  = 0L;
	protected Long id0205     = 0L;  
	protected Long idPai0205  = 0L;
	protected Long idC100     = 0L;
	protected Long idPaiC100  = 0L;
	protected Long idC170     = 0L;  
	protected Long idPaiC170  = 0L;
	protected Long idC400     = 0L;
	protected Long idPaiC400  = 0L;
	protected Long idC405     = 0L;
	protected Long idPaiC405  = 0L;
	protected Long idC420     = 0L;
	protected Long idPaiC420  = 0L;
	protected Long idC425     = 0L;
	protected Long idPaiC425  = 0L;
	protected Long idC490     = 0L;
	protected Long idPaiC490  = 0L;
	protected Long idC860     = 0L;
	protected Long idPaiC860  = 0L;
	protected Long idC870     = 0L;
	protected Long idPaiC870  = 0L;
	protected Long idC890     = 0L;
	protected Long idPaiC890  = 0L;
	protected Long idE200     = 0L;
	protected Long idPaiE200  = 0L;
	protected Long idE210     = 0L;
	protected Long idPaiE210  = 0L;
	protected Long idE220     = 0L;
	protected Long idPaiE220  = 0L;
	protected Long idE240     = 0L;
	protected Long idPaiE240  = 0L;
	protected Long idE250     = 0L;
	protected Long idPaiE250  = 0L;
	protected Long idH005     = 0L;  
	protected Long idPaiH005  = 0L;
	protected Long idH010     = 0L;  
	protected Long idPaiH010  = 0L;
	
	private List<Reg0000> regs0000 = new ArrayList<>();
	private List<Reg0150> regs0150 = new ArrayList<>();
	private List<Reg0190> regs0190 = new ArrayList<>();
	private List<Reg0200> regs0200 = new ArrayList<>();
	private List<Reg0220> regs0220 = new ArrayList<>();
	private List<Reg0205> regs0205 = new ArrayList<>();
	
	private List<RegC100> regsC100 = new ArrayList<>();
	private List<RegC170> regsC170 = new ArrayList<>();
	
	private List<RegC400> regsC400 = new ArrayList<>();
	private List<RegC405> regsC405 = new ArrayList<>();
	private List<RegC420> regsC420 = new ArrayList<>();
	private List<RegC425> regsC425 = new ArrayList<>();
	private List<RegC490> regsC490 = new ArrayList<>();
	
	private List<RegC860> regsC860 = new ArrayList<>();

	
	private List<RegH005> regsH005 = new ArrayList<>();
	private List<RegH010> regsH010 = new ArrayList<>();
	
	private Map<Long,Reg0000>        mpEmpresa  = new HashMap<Long, Reg0000>();
    private Map<String,Reg0150> mpParticipante  = new HashMap<String, Reg0150>();
    private Map<String,Reg0200>     mpProdTerc  = new HashMap<String, Reg0200>();
    
    private Map<Long,RegC400>       mpEquipEcf  = new HashMap<Long, RegC400>();
    private Map<Long,RegC405>        mpReducao  = new HashMap<Long, RegC405>();
    private Map<Long,RegC490>  mpTotalizadorCF  = new HashMap<Long, RegC490>();
    
    private Map<Long,RegC860>       mpEquipCFe    = new HashMap<Long, RegC860>();
  
    
    
	private Reg0000 reg0000;
	private Reg0200 reg0200;
	private RegC100 regC100;
	
  	private RegC400 regC400;
  	private RegC405 regC405;
  	private RegC420 regC420;
  	private RegC425 regC425;
	
  	private RegC860 regC860;

  	private RegE200 regE200;
	private RegE210 regE210;
	private RegE220 regE220;
	private RegE240 regE240;
	private RegE250 regE250;
  	
  	private RegH005 regH005;
  	private RegH010 regH010;
  	
  	private LoteImportacaoSpedFiscalDao daoLote;
  	private ParticipanteDao daoPart;
  	private ProdutoDao daoProd;
	private NotaFiscalDao daoNF;
	private EquipamentoECFDao daoECF;
	private ReducaoZDao  daoRdz;
	private TotParciaisRDZDao daoTotParRdz;
	private EquipamentoCFeDao daoEquipCFe;
	

    public Long incLoteImportacao(Pool pool) {
    	Long id = 0L;
    	try {
    		daoLote = new LoteImportacaoSpedFiscalDao(pool);
    		id = daoLote.getIncLoteImp();
    	}catch (Exception e) {}
    	
    	return id;
    }
	public Long incPart(Pool pool) {
		daoPart    = new ParticipanteDao(pool);
		id0150     = daoPart.getIncPart();
		return id0150;
	}
	public Long incProd(Pool pool) {
		daoProd      = new ProdutoDao(pool);
		id0200 = daoProd.getIncProd();

		return id0200;
	}

	public Long incNFe(Pool pool) {
		
		daoNF      = new NotaFiscalDao(pool);	
		idC100 = daoNF.getIncNFe();
		return idC100;
	}
	
	public Long incRDZ(Pool pool) {
		daoRdz = new ReducaoZDao(pool);
		idC405 = daoRdz.getIncReducaoZ();		
		return idC405;
	}
	public Long idECFRegC405(String numFab,Pool pool) {
		Long id = 0L;
		daoECF = new EquipamentoECFDao(pool);
		try {
			if(daoECF.getEquipamentosFiscaisPorNumFab().get(numFab) != null) {
				id = daoECF.getEquipamentosFiscaisPorNumFab().get(numFab).getId();
			}
			
		}catch (Exception e) {
			
		}
		return id;
	}
	
	public Long incTotParcRdz(Pool pool) {
		Long id = 0L;
		daoTotParRdz = new TotParciaisRDZDao(pool);
		try {
			id = daoTotParRdz.getIncTotParcRDZ();
		}catch (Exception e) {}
		return id;
	}
	
	public Long incTotEquipCFe(Pool pool) {
		Long id = 0L;
		daoEquipCFe = new EquipamentoCFeDao(pool);
		try {
		    id = daoEquipCFe.getIncEquipCFe();
        }catch (Exception e) {}
		return id;
	}
	
	public LeitorTxtSpedFiscal() {
		
		
	}
    
	public void leitorSpedFiscal(Path p,Pool pool) throws Exception {
		id0000 = (incLoteImportacao(pool) !=0 ? incLoteImportacao(pool) -1:incLoteImportacao(pool));
		id0200 = (incProd(pool)!=0 ? incProd(pool)-1:incProd(pool));
		idC100 = (incNFe(pool)!=0 ? incNFe(pool)-1:incNFe(pool));
		idC405 = (incRDZ(pool)!=0 ? incRDZ(pool)-1:incRDZ(pool));
		idC420 = (incTotParcRdz(pool)!=0 ? incTotParcRdz(pool)-1:incTotParcRdz(pool));
		idC860 = (incTotEquipCFe(pool) !=0 ? incTotEquipCFe(pool)-1:incTotEquipCFe(pool));
		List<String> lines = Files.readAllLines(p,Charset.forName("ISO-8859-1"));
		 for(String line : lines) {
			 id0000 = reg0000(line,pool);			 
		 }
	}

	private Long reg0000(String line,Pool pool) {	
			if(line.startsWith("|0000|")) {	
				id0000 ++;
			    reg0000 = new Reg0000();			 
				String[] campos = line.split("\\|");   			
				
	 			
				reg0000.setId(id0000);
				reg0000.setReg(campos[1]);
				reg0000.setCodVer(campos[2]);
				reg0000.setCodFin(campos[3]);
				reg0000.setDtIni(UtilsEConverters.getStringParaData(campos[4]));
				reg0000.setDtFin(UtilsEConverters.getStringParaData(campos[5]));
				reg0000.setNome(campos[6]);
				reg0000.setCnpj(campos[7]);
				reg0000.setCpf(campos[8]);
				reg0000.setUf(campos[9]);
				reg0000.setIe(campos[10]);
				reg0000.setCodMun(campos[11]);
				reg0000.setIm(campos[12]);
				reg0000.setSuframa(campos[13]);
				reg0000.setIndPerfil(campos[14]);
				reg0000.setIndAtiv(campos[15]);
				
				regs0000.add(reg0000);
				mpEmpresa.put(reg0000.getId(), reg0000);
			}
	
			
			if(line.startsWith("|0150|")) {
				id0150++;
			    Reg0150 reg0150 = new Reg0150();
				String[] campos = line.split("\\|");   
				List<String> lista = Arrays.asList(campos);
				reg0150.setId(id0150);
				reg0150.setIdPai(idPai0150);
			    for(int i=0; i < campos.length;i++){
				   if(i==1){reg0150.setReg(lista.get(i));}
				   if(i==2){reg0150.setCodPart(lista.get(i));}
				   if(i==3){reg0150.setNome(lista.get(i));}
				   if(i==4){reg0150.setCodPais(lista.get(i));}
				   if(i==5){reg0150.setCnpj(lista.get(i));}
				   if(i==6){reg0150.setCpf(lista.get(i));}
				   if(i==7){reg0150.setIe(lista.get(i));}
				   if(i==8){reg0150.setCodMun(lista.get(i));}
				   if(i==9){reg0150.setSuframa(lista.get(i));}
				   if(i==10){reg0150.setEndereco(lista.get(i));}
				   if(i==11){reg0150.setNum(lista.get(i));}
				   if(i==12){reg0150.setCompl(lista.get(i));}
				   if(i==13){reg0150.setBairro(lista.get(i));}
			    }
				   regs0150.add(reg0150);
				   mpParticipante.put(reg0150.getCodPart(), reg0150);
			}
			
		
				if(line.startsWith("|0190|")) {
					id0190++;
					Reg0190 reg0190 = new Reg0190();
					String[] campos = line.split("\\|");   	
					
					reg0190.setId(id0190);
					//reg0190.setIdPai(idPai0190);
					reg0190.setReg(campos[1]);
					reg0190.setUnd(campos[2]);
					reg0190.setDesc(campos[3]);
					
					regs0190.add(reg0190);
				}
				
				if(line.startsWith("|0200|")) {
					id0200++;
					reg0200 = new Reg0200();
					String[] campos = line.split("\\|"); 
					List<String> lista = Arrays.asList(campos);
					
					reg0200.setId(id0200);
					reg0200.setIdPai(idPai0200);
					for(int i=0; i < campos.length;i++){
						 if(i==1) { reg0200.setReg(lista.get(i));  }
		                 if(i==2) { reg0200.setCodItem(UtilsEConverters.preencheZerosAEsquerda(lista.get(i)));}
		                 if(i==3) { reg0200.setDescrItem(lista.get(i)); }
		                 if(i==4) { reg0200.setCodBarra(lista.get(i)); }
		                 if(i==5) { reg0200.setCodAntItem(lista.get(i));}
		                 if(i==6) { reg0200.setUnidInv(lista.get(i)); }
		                 if(i==7) { reg0200.setTipoItem(lista.get(i));}
		                 if(i==8) {reg0200.setCodNcm(lista.get(i)); }
		                 if(i==9) { reg0200.setExIpi(lista.get(i)); }
		                 if(i==10) { reg0200.setCodGen(lista.get(i)); }
		                 if(i==11) { reg0200.setCodLst(lista.get(i));}
		                
		                 if(i==12) { 
		                	 if(!lista.get(i).isEmpty()) {
		                		 reg0200.setAliqIcms(Double.valueOf(lista.get(i).replace(",", "."))); 
		                	 }
		                 }
		                 if(i==13) { reg0200.setCest(lista.get(i));}
		                
					}
					
					regs0200.add(reg0200);
					mpProdTerc.put(reg0200.getCodItem(), reg0200);
				}
				
				if(line.startsWith("|0220|")) {
					id0220++;
					Reg0220 reg0220 = new Reg0220();
					String[] campos = line.split("\\|"); 
					List<String> lista = Arrays.asList(campos);
					reg0220.setId(id0220);

					reg0220.setCodItem(reg0200.getCodItem());
		
					for(int i=0; i < campos.length;i++){
						
						if(i==1) { reg0220.setReg(lista.get(i));  }
						if(i==2) { reg0220.setUndConv(lista.get(i));  }
						if(i==3) { reg0220.setFatConv(Double.valueOf(lista.get(i).replace(",", ".")));  }
						
					}
					reg0200.adicionaOutUndMedida(reg0220);
					regs0220.add(reg0220);
				}
				
				if(line.startsWith("|0205|")) {
					id0205++;
					Reg0205 reg0205 = new Reg0205();
					String[] campos = line.split("\\|"); 
					List<String> lista = Arrays.asList(campos);
					reg0205.setId(id0205);
					reg0205.setCodItem(reg0200.getCodItem()); 
					for(int i=0; i < campos.length;i++){
						
						if(i==1) { reg0205.setReg(lista.get(i));}
						if(i==2) { reg0205.setDescrAntItem(lista.get(i));}
						if(i==3) { reg0205.setDtIni(UtilsEConverters.getStringParaData(lista.get(i)));}
						if(i==4) { reg0205.setDtFim(UtilsEConverters.getStringParaData(lista.get(i)));}
						if(i==5) { reg0205.setCodAntItem(UtilsEConverters.preencheZerosAEsquerda(lista.get(i)));}
		
					}
					reg0200.adicionaAlteracaoItem(reg0205);
					regs0205.add(reg0205);
				}
	
				if(line.startsWith("|C100|") || line.startsWith("|C170|")) {
					
					if(line.startsWith("|C100|")) {
						idC100++;
						regC100 = new RegC100();
						String[] campos = line.split("\\|"); 
						List<String> lista = Arrays.asList(campos);

						regC100.setId(idC100);
						regC100.setIdPai(id0000);
						for(int i=0; i < campos.length;i++){
							
							if(i==1) { regC100.setReg(lista.get(i));}
							if(i==2) { regC100.setIndOper(lista.get(i));}
							if(i==3) { regC100.setIndEmit(lista.get(i));}
							if(i==4) { regC100.setCodPart(lista.get(i));}
							if(i==5) { regC100.setCodMod(lista.get(i));}
							if(i==6) { regC100.setCodSit(lista.get(i));}
							if(i==7) { regC100.setSer(lista.get(i));}
							if(i==8) { regC100.setNumDoc(lista.get(i));}
							if(i==9) { regC100.setChvNfe(lista.get(i));}
							
							if(i==10){
								if(!lista.get(i).isEmpty()) {
									regC100.setDtDoc(UtilsEConverters.getStringParaData(lista.get(i)));
								}		
							}
							
							if(i==11){ 
								if(!lista.get(i).isEmpty()) {
									regC100.setDtEntSai(UtilsEConverters.getStringParaData(lista.get(i)));
								}
							}
							
							if(i==12){ regC100.setVlDoc(Double.valueOf(lista.get(i).replace(",", ".")));}
							if(i==13){ regC100.setIndPgto(lista.get(i));}
							if(i==14){ if(!lista.get(i).isEmpty()) {regC100.setVlDesc(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==15){ if(!lista.get(i).isEmpty()) {regC100.setVlAbatNt(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==16){ if(!lista.get(i).isEmpty()){regC100.setVlMerc(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==17){ regC100.setIndFrt(lista.get(i));}
							if(i==18){ if(!lista.get(i).isEmpty()){regC100.setVlFrt(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==19){ if(!lista.get(i).isEmpty()){regC100.setVlSeg(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==20){ if(!lista.get(i).isEmpty()){regC100.setVlOutDa(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==21){ if(!lista.get(i).isEmpty()){regC100.setVlBcIcms(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==22){ if(!lista.get(i).isEmpty()){regC100.setVlIcms(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==23){ if(!lista.get(i).isEmpty()){regC100.setVlBcIcmsSt(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==24){ if(!lista.get(i).isEmpty()){regC100.setVlIcmsSt(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==25){ if(!lista.get(i).isEmpty()){regC100.setVlIpi(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==26){ if(!lista.get(i).isEmpty()){regC100.setVlPis(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==27){ if(!lista.get(i).isEmpty()){regC100.setVlCofins(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==28){ if(!lista.get(i).isEmpty()){regC100.setVlPisSt(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==29){ if(!lista.get(i).isEmpty()){regC100.setVlCofinsSt(Double.valueOf(lista.get(i).replace(",", ".")));}}
							
							
						}
						regsC100.add(regC100);  
						
					}
					
					
					if(line.startsWith("|C170|")) {
						idC170++;
						RegC170 regC170 = new RegC170();
						String[] campos = line.split("\\|"); 
						List<String> lista = Arrays.asList(campos);
						regC170.setId(idC170);
						regC170.setIdPai(idC100);
						for(int i=0; i < campos.length;i++){
						  	
							if(i==1) { regC170.setReg(lista.get(i));}
							if(i==2) { regC170.setNumItem(lista.get(i));}
							if(i==3) { regC170.setCodItem(UtilsEConverters.preencheZerosAEsquerda(lista.get(i)));}
							if(i==4) { regC170.setDescrCompl(lista.get(i));}
							if(i==5){if(!lista.get(i).isEmpty()){ regC170.setQtd(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==6) {regC170.setUnid(lista.get(i));}
							if(i==7){if(!lista.get(i).isEmpty()){ regC170.setVlItem(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==8){if(!lista.get(i).isEmpty()){regC170.setVlDesc(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==9) {regC170.setIndMov(lista.get(i));}
							if(i==10) {regC170.setCstIcms(lista.get(i));}
							if(i==11) {regC170.setCfop(lista.get(i));}
							if(i==12) {regC170.setCodNat(lista.get(i));}
							if(i==13){if(!lista.get(i).isEmpty()){ regC170.setVlBcIcms(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==14){if(!lista.get(i).isEmpty()){ regC170.setAliqIcms(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==15){if(!lista.get(i).isEmpty()){ regC170.setVlIcms(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==16){if(!lista.get(i).isEmpty()){ regC170.setVlBcIcmsSt(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==17){if(!lista.get(i).isEmpty()){ regC170.setAliqIcmsSt(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==18){if(!lista.get(i).isEmpty()){ regC170.setVlIcmsSt(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==19) {regC170.setIndApur(lista.get(i));}
							if(i==20) {regC170.setCstIpi(lista.get(i));}
							if(i==21) {regC170.setCodEnq(lista.get(i));}
							if(i==22){if(!lista.get(i).isEmpty()){ regC170.setVlBcIpi(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==23){if(!lista.get(i).isEmpty()){ regC170.setAliqIpi(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==24){if(!lista.get(i).isEmpty()){ regC170.setVlIpi(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==25) {regC170.setCstPis(lista.get(i));}
							if(i==26){if(!lista.get(i).isEmpty()){ regC170.setVlBcPis(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==27){if(!lista.get(i).isEmpty()){ regC170.setAliqPisPerc(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==28){if(!lista.get(i).isEmpty()){ regC170.setQuantBcPis(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==29){if(!lista.get(i).isEmpty()){ regC170.setAliqPisReais(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==30){if(!lista.get(i).isEmpty()){ regC170.setVlPis(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==31) {regC170.setCstCofins(lista.get(i));}
							if(i==32){if(!lista.get(i).isEmpty()){ regC170.setVlBcCofins(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==33){if(!lista.get(i).isEmpty()){ regC170.setAliqCofinsPerc(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==34){if(!lista.get(i).isEmpty()){ regC170.setQuantBcCofins(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==35){if(!lista.get(i).isEmpty()){ regC170.setAliqCofinsReais(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==36){if(!lista.get(i).isEmpty()){ regC170.setVlCofins(Double.valueOf(lista.get(i).replace(",", ".")));}}
							if(i==37) {regC170.setCodCta(lista.get(i));}
							if(i==38){if(!lista.get(i).isEmpty()){ regC170.setVlAbatNt(Double.valueOf(lista.get(i).replace(",", ".")));}}
						}

						regC100.adicionaProdutos(regC170);
						regsC170.add(regC170);
					}
				}

        	 if(line.startsWith("|C400|")) {
        		 idC400++;
        		 regC400 = new RegC400();
        		 String[] campos = line.split("\\|"); 
				 List<String> lista = Arrays.asList(campos);
				 regC400.setId(idC400);
				 regC400.setIdPai(idPaiC400);
				 for(int i=0; i < campos.length;i++){
					 if(i==1) {regC400.setReg(lista.get(i));}
					 if(i==2) {regC400.setCodModelo(lista.get(i));}
					 if(i==3) {regC400.setModeloEquipamento(lista.get(i));}
					 if(i==4) {regC400.setNumSerieFabECF(lista.get(i));}
					 if(i==5) {regC400.setNumCaixaECF(lista.get(i));}
				 }
				 regsC400.add(regC400);
				 mpEquipEcf.put(regC400.getId(), regC400);
        	 }
        	 
        	 if(line.startsWith("|C405|")) {
        		 idC405++;
        		 regC405 = new RegC405();
        		 String[] campos = line.split("\\|"); 
				 List<String> lista = Arrays.asList(campos);
				 regC405.setId(idC405);
				 
				 if(idECFRegC405(regC400.getNumSerieFabECF(),pool) != null) {
					 regC405.setIdPaiECF(idECFRegC405(regC400.getNumSerieFabECF(),pool));
				 }
				 
				 regC405.setIdPai(id0000);
				 for(int i=0; i < campos.length;i++){
					 if(i==1) {regC405.setReg(lista.get(i));}
					 if(i==2) {regC405.setDtDoc(UtilsEConverters.getStringParaData(lista.get(i)));}
					 if(i==3) {regC405.setPosicaoCRO(lista.get(i));}
					 if(i==4) {regC405.setPosicaoRDZ(lista.get(i));}
					 if(i==5) {regC405.setNumCOOFin(lista.get(i));}
					 if(i==6) {regC405.setVlGrandeTotalFinal(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i==7) {regC405.setVlVendaBruta(Double.valueOf(lista.get(i).replace(",",".")));}
				 }
				 regC400.adicionaRegC405(regC405);
				 mpReducao.put(regC405.getId(), regC405);
				 regsC405.add(regC405);
        	 }
        	 
        	 if(line.startsWith("|C420|")) {
        		 idC420++;
        		 regC420 = new RegC420();
        		 String[] campos = line.split("\\|"); 
				 List<String> lista = Arrays.asList(campos);
				 regC420.setId(idC420);
				 regC420.setIdPai(idC405);
				 for(int i=0; i < campos.length;i++){
					 if(i==1) {regC420.setReg(lista.get(i));}
					 if(i==2) {regC420.setCodTotPar(lista.get(i));}
					 if(i==3) {regC420.setVlAcumTot(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i==4) {regC420.setNrTot(lista.get(i));}
					 if(i==5) {regC420.setDescrNrTot(lista.get(i));}
				 }
				 regC405.adicionaRegC420(regC420);
				 regsC420.add(regC420);
        	 }
        	 
        	 if(line.startsWith("|C425|")) {
        		 idC425++;
        		 regC425 = new RegC425();
        		 String[] campos = line.split("\\|"); 
				 List<String> lista = Arrays.asList(campos);
				 regC425.setId(idC425);
				 regC425.setIdPai(idC420);
				 regC425.setIdPaiRedZ(idC405);
				 for(int i=0; i < campos.length;i++){
					 if(i==1) {regC425.setReg(lista.get(i));}
					 if(i==2) {
						 regC425.setCodItem(UtilsEConverters.preencheZerosAEsquerda(lista.get(i)));
					 }
					 if(i==3) {regC425.setQtd(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i==4) {regC425.setUnd(lista.get(i));}
					 if(i==5) {regC425.setVlItem(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(!lista.get(i).isEmpty()) {
						 if(i==6) {regC425.setVlPis(Double.valueOf(lista.get(i).replace(",",".")));}
						 if(i==7) {regC425.setVlCofins(Double.valueOf(lista.get(i).replace(",",".")));}
					 }

				 }
				 
				 regC420.adicionaRegC425(regC425);
				 regsC425.add(regC425);
        	 }
        	 
        	 if(line.startsWith("|C490|")) {
        		 idC490++;
        		 RegC490 regC490 = new RegC490();
        		 String[] campos = line.split("\\|"); 
				 List<String> lista = Arrays.asList(campos);
				 regC490.setId(idC490);
				 regC490.setIdPai(idC405);
				 for(int i=0; i < campos.length;i++){
					 if(i==1) {regC490.setReg(lista.get(i));}
					 if(i==2) {regC490.setCstIcms(lista.get(i));}
					 if(i==3) {regC490.setCfop(lista.get(i));}
					 if(i==4) {
						 if((!lista.get(i).isEmpty())) {
							 regC490.setAliqIcms(Double.valueOf(lista.get(i).replace(",",".")));
						 }
						
					 }
					 if(i==5) {regC490.setVlOperacao(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i==6) {regC490.setVlBcIcms(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i==7) {regC490.setVlIcms(Double.valueOf(lista.get(i).replace(",",".")));}
				 }
				 regC405.adicionaRegC490(regC490);
				 mpTotalizadorCF.put(regC490.getIdPai(), regC490);
				 regsC490.add(regC490);
        	 }

		 
        	 if(line.startsWith("|C860|")) {
        		 idC860++;
        		 regC860 = new RegC860();
        		 String[] campos = line.split("\\|"); 
				 List<String> lista = Arrays.asList(campos);
				 regC860.setId(idC860);
				 regC860.setIdPai(id0000);
				 for(int i=0; i < campos.length;i++){
					 if(i==1) {regC860.setReg(lista.get(i));}
					 if(i==2) {regC860.setCodModDocFiscal(lista.get(i));}
					 if(i==3) {regC860.setNumSerieEquipSat(lista.get(i));}
					 if(i==4) {regC860.setDtEmissao(UtilsEConverters.getStringParaData(lista.get(i)));}
					 if(i==5) {regC860.setDocInicial(lista.get(i));}
					 if(i==6) {regC860.setDocFinal(lista.get(i));}
				 }
				 
				 regsC860.add(regC860);
				 mpEquipCFe.put(regC860.getId(), regC860);
				
        	 }
   
        	 
        	 if(line.startsWith("|E200|")) {
        		 idE200++;
        		 regE200 = new RegE200();
        		 String[] campos = line.split("\\|"); 
				 List<String> lista = Arrays.asList(campos);
				 regE200.setId(idE200);
				 regE200.setIdPai(id0000);
				 for(int i=0; i < campos.length;i++){
					 if(i == 1) {regE200.setReg(lista.get(i));}
					 if(i == 2) {regE200.setUf(lista.get(i));}
					 if(i == 3) {regE200.setDtIni(UtilsEConverters.getStringParaData(lista.get(i)));}
					 if(i == 4) {regE200.setDtFin(UtilsEConverters.getStringParaData(lista.get(i)));}
				 }
        	 
        	 }

        	 if(line.startsWith("|E210|")) {
        		 idE210++;
        		 regE210 = new RegE210();
        		 String[] campos = line.split("\\|"); 
				 List<String> lista = Arrays.asList(campos);
				 regE210.setId(idE210);
				 regE210.setIdPai(idE200);
				 for(int i=0; i < campos.length;i++){
					 if(i == 1) {regE210.setReg(lista.get(i));}
					 if(i == 2) {regE210.setReg(lista.get(i));}
					 if(i == 3) {regE210.setIndMovST(lista.get(i));}
					 if(i == 4) {regE210.setVlSaldoCredAntST(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i == 5) {regE210.setVlDevolucaoICMSST(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i == 6) {regE210.setVlRessarcimentoICMSST(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i == 7) {regE210.setVlOutrosCreditosICMSST(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i == 8) {regE210.setVlAjusteCreditosICMSST(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i == 9) {regE210.setVlRetencaoICMSST(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i == 10) {regE210.setVlOutrosDebitosICMSST(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i == 11) {regE210.setVlSaldoDevedorAnteriorICMSST(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i == 12) {regE210.setVlDeducoesICMSST(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i == 13) {regE210.setVlArecolherICMSST(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i == 14) {regE210.setVlSaldoCredorICMSSTAtransportar(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i == 15) {regE210.setDebitoExtraApurICMSSTARecolher(Double.valueOf(lista.get(i).replace(",",".")));}
					
				 }
        		 
        	 }
        	 
        	 if(line.startsWith("|E220|")) {
        		 idE220++;
        		 regE220 = new RegE220();
        		 String[] campos = line.split("\\|"); 
				 List<String> lista = Arrays.asList(campos);
				 regE220.setId(idE220);
				 regE220.setIdPai(idE200);
				 for(int i=0; i < campos.length;i++){
					 if(i == 1) {regE220.setReg(lista.get(i));}
					 if(i == 2) {regE220.setCodigoDeAjusteApuracao(lista.get(i));}
					 if(i == 3) {regE220.setDescricaoComplementarAjuste(lista.get(i));}
					 if(i == 4) {regE220.setVlAjusteApuracao(Double.valueOf(lista.get(i).replace(",",".")));}
					 
				 }

        	 }
        	 
        	 if(line.startsWith("|E240|")) {
        		 idE240++;
        		 regE240 = new RegE240();
        		 String[] campos = line.split("\\|"); 
				 List<String> lista = Arrays.asList(campos);
				 regE240.setId(idE240);
				 regE240.setIdPai(idE220);
				 for(int i=0; i < campos.length;i++){
					 if(i == 1) {regE240.setReg(lista.get(i));}
					 if(i == 2) {regE240.setCodigoPart(lista.get(i));}
					 if(i == 3) {regE240.setCodModeloDocFiscal(lista.get(i));}
					 if(i == 4) {regE240.setSerie(lista.get(i));}
					 if(i == 5) {regE240.setSubSerie(lista.get(i));}
					 if(i == 6) {regE240.setNumDocumento(lista.get(i));}
					 if(i == 7) {regE240.setDtEmissaoDoc(UtilsEConverters.getStringParaData(lista.get(i)));}
					 if(i == 8) {regE240.setCodigoItem(lista.get(i));}
					 if(i == 9) {regE240.setVlAjusteItem(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i == 10) {regE240.setChaveEletronicaDoc(lista.get(i));}
				 
				 }
        		 
        	 }
        	 
        	 if(line.startsWith("|E250|")) {
        		 idE250++;
        		 regE250 = new RegE250();
        		 String[] campos = line.split("\\|"); 
				 List<String> lista = Arrays.asList(campos);
				 regE250.setId(idE250);
				 regE250.setIdPai(idE220);
				 for(int i=0; i < campos.length;i++){
					 if(i == 1) {regE250.setReg(lista.get(i));}
					 if(i == 2) {regE250.setCodigoDaObrigARecolher(lista.get(i));}
					 if(i == 3) {regE250.setVlDaObrigacaoARecolher(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i == 4) {regE250.setDtVencimentoObrigacao(UtilsEConverters.getStringParaData(lista.get(i)));}
					 if(i == 5) {regE250.setCodigoDaReceita(lista.get(i));}
					 if(i == 6) {regE250.setNumProcesso(lista.get(i));}
					 if(i == 7) {regE250.setIndOrigemDoProcesso(lista.get(i));}
					 if(i == 8) {regE250.setDescrResumidaProcesso(lista.get(i));}
					 if(i == 9) {regE250.setDescrComplementar(lista.get(i));}
					 if(i == 10) {regE250.setMesReferencia(lista.get(i));}
				 }
        		 
        	 }
        	 
        	 
        	 
        	 if(line.startsWith("|H005|")) {
        		 idH005++;
        		 regH005 = new RegH005();
        		 String[] campos = line.split("\\|"); 
				 List<String> lista = Arrays.asList(campos);
				 regH005.setId(idH005);
				 regH005.setIdPai(id0000);
				 for(int i=0; i < campos.length;i++){
					 if(i==1) {regH005.setReg(lista.get(i));}
					 if(i==2) {regH005.setDataInv(UtilsEConverters.getStringParaData(lista.get(i)));}
					 if(i==3) {regH005.setVlTotEstoque(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i==4) {regH005.setMotivoInventario(lista.get(i));}
				 }
				 
				 regsH005.add(regH005);
        	 }
        	 
        	 if(line.startsWith("|H010|")) {
        		 idH010++;
        		 regH010 = new RegH010();
        		 String[] campos = line.split("\\|"); 
				 List<String> lista = Arrays.asList(campos);
				 regH010.setId(idH010);
				 regH010.setIdPai(idH005);
				 for(int i=0; i < campos.length;i++){
					 if(i==1) {regH010.setReg(lista.get(i));}
					 if(i==2) {regH010.setCodItem(UtilsEConverters.preencheZerosAEsquerda(lista.get(i)));}
					 if(i==3) {regH010.setUnd(lista.get(i));}
					 if(i==4) {regH010.setQtde(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i==5) {regH010.setVlUnit(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i==6) {regH010.setVlItem(Double.valueOf(lista.get(i).replace(",",".")));}
					 if(i==7) {regH010.setIndProp(lista.get(i));}
					 if(i==8) {regH010.setCodPart(lista.get(i));}
					 if(i==9) {regH010.setTxtCompl(lista.get(i));}
					 if(i==10) {regH010.setCodCtda(lista.get(i));}
					 if(i==11) {regH010.setVlItemIR(Double.valueOf(lista.get(i).replace(",",".")));}
					 
				 }
				 
				 regH005.adicionaItemInv(regH010);
				 regsH010.add(regH010);
        	 }
        	 
		return id0000;
	}

	
	
	public List<ProdutoNotaXmlProprio> getProdutosXMLHandler(Path pXml) throws Exception {
	   List<ProdutoNotaXmlProprio>  retorno = new ArrayList<ProdutoNotaXmlProprio>();
	   LeitorXML logica = new LeitorXML();
       SAXParserFactory spf = SAXParserFactory.newInstance();
       SAXParser parser = spf.newSAXParser();
	   try (DirectoryStream<Path> stream = Files.newDirectoryStream(pXml)){
			
			for(Path path :  stream) {				
	   			InputStream ips = new FileInputStream(path.getParent().toString().concat("\\").concat(path.getFileName().toString()));
	   			InputSource is = new InputSource(ips);	   			
	   			parser.parse(is, logica);
	   			retorno = logica.getProdutosNF();
			}
		}
		
		return retorno;
	}

	
	public List<ProdutoCupomFiscalXml> getProdutosXMLHandlerCF(Path pXml) throws Exception{
		List<ProdutoCupomFiscalXml> retorno = new ArrayList<ProdutoCupomFiscalXml>();
		SAXParserFactory spf = SAXParserFactory.newInstance();
		LeitorXML logica = new LeitorXML();			
		SAXParser parser = spf.newSAXParser();
	    try (DirectoryStream<Path> stream = Files.newDirectoryStream(pXml)){
	    	for(Path path :  stream) {		
	    		InputStream ips = new FileInputStream(path.getParent().toString().concat("\\").concat(path.getFileName().toString()));
	   			InputSource is = new InputSource(ips);	
	   			parser.parse(is, logica);
	   			retorno = logica.getProdutosCF();
	    	}
	    }
		        
		return retorno;
	}
	
	
	public Reg0000 getReg0000() {
		return reg0000;
	}
	
	
	public List<Reg0000> getRegs0000() {
		return regs0000;
	}
	public Map<Long, Reg0000> getMpEmpresa() {
		return mpEmpresa;
	}
	public List<Reg0150> getRegs0150() {
		return regs0150;
	}
	
	public Map<String, Reg0150> getMpParticipante() {
		return mpParticipante;
	}
	public List<Reg0200> getRegs0200() {
		return regs0200;
	}
	
    public Map<String, Reg0200> getMpProdTerc() {
		return mpProdTerc;
	}
	public List<Reg0220> getRegs0220() {
		return regs0220;
	}
    
    public List<Reg0205> getRegs0205() {
		return regs0205;
	}
	
	public List<RegC100> getRegsC100() {
		return regsC100;
	}
	
	public List<RegC170> getRegsC170() {
		return regsC170;
	}
	public List<RegC400> getRegsC400() {
		return regsC400;
	}
	
	public Map<Long, RegC400> getMpEquipEcf() {
		return mpEquipEcf;
	}
	public List<RegC405> getRegsC405() {
		return regsC405;
	}
	
	public Map<Long, RegC405> getMpReducao() {
		return mpReducao;
	}
	public List<RegC420> getRegsC420() {
		return regsC420;
	}
	public List<RegC425> getRegsC425() {
		return regsC425;
	}
	public List<RegC490> getRegsC490() {
		return regsC490;
	}
    
	public Map<Long, RegC490> getMpTotalizadorCF() {
		return mpTotalizadorCF;
	}
	public List<RegC860> getRegsC860() {
		return regsC860;
	}
	
	public Map<Long, RegC860> getMpEquipCFe() {
		return mpEquipCFe;
	}
	
	public List<RegH005> getRegsH005() {
		return regsH005;
	}
	
    public RegH010 getRegH010() {
		return regH010;
	}
}	
