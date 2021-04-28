package versaogama.model.sped;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import versaogama.conexao.Pool;
import versaogama.managersped.LeitorTxtSpedFiscal;


public class RegC170 {

	private  Long   id;
	private  Long  idPai;
	private  String reg;
	private  String numItem;
	private  String codItem;
	private  String descrCompl;
	private  Double qtd;
	private  String unid;
	private  Double vlItem;
	private  Double vlDesc;
	private  String indMov;
	private  String cstIcms;
	private  String cfop;
	private  String codNat;
	private  Double vlBcIcms;
	private  Double aliqIcms;
	private  Double vlIcms;
	private  Double vlBcIcmsSt;
	private  Double aliqIcmsSt;
	private  Double vlIcmsSt;
	private  String indApur;
	private  String cstIpi;
	private  String codEnq;
	private  Double vlBcIpi;
	private  Double aliqIpi;
	private  Double vlIpi;
	private  String cstPis;
	private  Double vlBcPis;
	private  Double aliqPisPerc;
	private  Double quantBcPis;
	private  Double aliqPisReais;
	private  Double vlPis;
	private  String cstCofins;
	private  Double vlBcCofins;
	private  Double aliqCofinsPerc;
	private  Double quantBcCofins;
	private  Double aliqCofinsReais;
	private  Double vlCofins;
	private  String codCta;
	private  Double vlAbatNt;
	
	private List<RegC170> produtos = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdPai() {
		return idPai;
	}
	public void setIdPai(Long idPai) {
		this.idPai = idPai;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public String getNumItem() {
		return numItem;
	}
	public void setNumItem(String numItem) {
		this.numItem = numItem;
	}
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	public String getDescrCompl() {
		return descrCompl;
	}
	public void setDescrCompl(String descrCompl) {
		this.descrCompl = descrCompl;
	}
	public Double getQtd() {
		return qtd;
	}
	public void setQtd(Double qtd) {
		this.qtd = qtd;
	}
	public String getUnid() {
		return unid;
	}
	public void setUnid(String unid) {
		this.unid = unid;
	}
	public Double getVlItem() {
		return vlItem;
	}
	public void setVlItem(Double vlItem) {
		this.vlItem = vlItem;
	}
	public Double getVlDesc() {
		return vlDesc;
	}
	public void setVlDesc(Double vlDesc) {
		this.vlDesc = vlDesc;
	}
	public String getIndMov() {
		return indMov;
	}
	public void setIndMov(String indMov) {
		this.indMov = indMov;
	}
	public String getCstIcms() {
		return cstIcms;
	}
	public void setCstIcms(String cstIcms) {
		this.cstIcms = cstIcms;
	}
	public String getCfop() {
		return cfop;
	}
	public void setCfop(String cfop) {
		this.cfop = cfop;
	}
	public String getCodNat() {
		return codNat;
	}
	public void setCodNat(String codNat) {
		this.codNat = codNat;
	}
	public Double getVlBcIcms() {
		return vlBcIcms;
	}
	public void setVlBcIcms(Double vlBcIcms) {
		this.vlBcIcms = vlBcIcms;
	}
	public Double getAliqIcms() {
		return aliqIcms;
	}
	public void setAliqIcms(Double aliqIcms) {
		this.aliqIcms = aliqIcms;
	}
	public Double getVlIcms() {
		return vlIcms;
	}
	public void setVlIcms(Double vlIcms) {
		this.vlIcms = vlIcms;
	}
	public Double getVlBcIcmsSt() {
		return vlBcIcmsSt;
	}
	public void setVlBcIcmsSt(Double vlBcIcmsSt) {
		this.vlBcIcmsSt = vlBcIcmsSt;
	}
	public Double getAliqIcmsSt() {
		return aliqIcmsSt;
	}
	public void setAliqIcmsSt(Double aliqIcmsSt) {
		this.aliqIcmsSt = aliqIcmsSt;
	}
	public Double getVlIcmsSt() {
		return vlIcmsSt;
	}
	public void setVlIcmsSt(Double vlIcmsSt) {
		this.vlIcmsSt = vlIcmsSt;
	}
	public String getIndApur() {
		return indApur;
	}
	public void setIndApur(String indApur) {
		this.indApur = indApur;
	}
	public String getCstIpi() {
		return cstIpi;
	}
	public void setCstIpi(String cstIpi) {
		this.cstIpi = cstIpi;
	}
	public String getCodEnq() {
		return codEnq;
	}
	public void setCodEnq(String codEnq) {
		this.codEnq = codEnq;
	}
	public Double getVlBcIpi() {
		return vlBcIpi;
	}
	public void setVlBcIpi(Double vlBcIpi) {
		this.vlBcIpi = vlBcIpi;
	}
	public Double getAliqIpi() {
		return aliqIpi;
	}
	public void setAliqIpi(Double aliqIpi) {
		this.aliqIpi = aliqIpi;
	}
	public Double getVlIpi() {
		return vlIpi;
	}
	public void setVlIpi(Double vlIpi) {
		this.vlIpi = vlIpi;
	}
	public String getCstPis() {
		return cstPis;
	}
	public void setCstPis(String cstPis) {
		this.cstPis = cstPis;
	}
	public Double getVlBcPis() {
		return vlBcPis;
	}
	public void setVlBcPis(Double vlBcPis) {
		this.vlBcPis = vlBcPis;
	}
	public Double getAliqPisPerc() {
		return aliqPisPerc;
	}
	public void setAliqPisPerc(Double aliqPisPerc) {
		this.aliqPisPerc = aliqPisPerc;
	}
	public Double getQuantBcPis() {
		return quantBcPis;
	}
	public void setQuantBcPis(Double quantBcPis) {
		this.quantBcPis = quantBcPis;
	}
	public Double getAliqPisReais() {
		return aliqPisReais;
	}
	public void setAliqPisReais(Double aliqPisReais) {
		this.aliqPisReais = aliqPisReais;
	}
	public Double getVlPis() {
		return vlPis;
	}
	public void setVlPis(Double vlPis) {
		this.vlPis = vlPis;
	}
	public String getCstCofins() {
		return cstCofins;
	}
	public void setCstCofins(String cstCofins) {
		this.cstCofins = cstCofins;
	}
	public Double getVlBcCofins() {
		return vlBcCofins;
	}
	public void setVlBcCofins(Double vlBcCofins) {
		this.vlBcCofins = vlBcCofins;
	}
	public Double getAliqCofinsPerc() {
		return aliqCofinsPerc;
	}
	public void setAliqCofinsPerc(Double aliqCofinsPerc) {
		this.aliqCofinsPerc = aliqCofinsPerc;
	}
	public Double getQuantBcCofins() {
		return quantBcCofins;
	}
	public void setQuantBcCofins(Double quantBcCofins) {
		this.quantBcCofins = quantBcCofins;
	}
	public Double getAliqCofinsReais() {
		return aliqCofinsReais;
	}
	public void setAliqCofinsReais(Double aliqCofinsReais) {
		this.aliqCofinsReais = aliqCofinsReais;
	}
	public Double getVlCofins() {
		return vlCofins;
	}
	public void setVlCofins(Double vlCofins) {
		this.vlCofins = vlCofins;
	}
	public String getCodCta() {
		return codCta;
	}
	public void setCodCta(String codCta) {
		this.codCta = codCta;
	}
	public Double getVlAbatNt() {
		return vlAbatNt;
	}
	public void setVlAbatNt(Double vlAbatNt) {
		this.vlAbatNt = vlAbatNt;
	}
	
	private void getProdutosNota(RegC170 prNF) {
		this.produtos.add(prNF);
	}

	public void  getProdutosSped(Path p, LeitorTxtSpedFiscal leitor,Pool pool) throws Exception {		 
		 leitor.leitorSpedFiscal(p,pool);
		 
		 for(RegC170 prod :  leitor.getRegsC170()){	   
			  getProdutosNota(prod);		 
		 }
	}
	
	public List<RegC170> getProdutos() {
		return produtos;
	}
}
