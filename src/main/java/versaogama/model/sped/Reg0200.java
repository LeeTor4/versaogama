package versaogama.model.sped;

import java.util.ArrayList;
import java.util.List;


public class Reg0200 implements Comparable<Reg0200>{
	
	private Long   id;
	private Long   idPai;
	private String reg;
	private String codItem;
	private String descrItem;
	private String codBarra;
	private String CodAntItem;
	private String unidInv;
	private String tipoItem;
	private String codNcm;
	private String ExIpi;
	private String codGen;
	private String codLst;
	private Double AliqIcms;
	private String cest;
	
	private List<Reg0220> outrasUnidades;
	private List<Reg0205> alteracaoItem;
	
	public Reg0200() {
		outrasUnidades = new ArrayList<Reg0220>();
		alteracaoItem  = new ArrayList<Reg0205>();
	}
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
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	public String getDescrItem() {
		return descrItem;
	}
	public void setDescrItem(String descrItem) {
		this.descrItem = descrItem;
	}
	public String getCodBarra() {
		return codBarra;
	}
	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}
	public String getCodAntItem() {
		return CodAntItem;
	}
	public void setCodAntItem(String codAntItem) {
		CodAntItem = codAntItem;
	}
	public String getUnidInv() {
		return unidInv;
	}
	public void setUnidInv(String unidInv) {
		this.unidInv = unidInv;
	}
	public String getTipoItem() {
		return tipoItem;
	}
	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}
	public String getCodNcm() {
		return codNcm;
	}
	public void setCodNcm(String codNcm) {
		this.codNcm = codNcm;
	}
	public String getExIpi() {
		return ExIpi;
	}
	public void setExIpi(String exIpi) {
		ExIpi = exIpi;
	}
	public String getCodGen() {
		return codGen;
	}
	public void setCodGen(String codGen) {
		this.codGen = codGen;
	}
	public String getCodLst() {
		return codLst;
	}
	public void setCodLst(String codLst) {
		this.codLst = codLst;
	}
	public Double getAliqIcms() {
		return AliqIcms;
	}
	public void setAliqIcms(Double aliqIcms) {
		AliqIcms = aliqIcms;
	}
	public String getCest() {
		return cest;
	}
	public void setCest(String cest) {
		this.cest = cest;
	}
	@Override
	public int compareTo(Reg0200 obj2) {
		
		return this.descrItem.compareTo(obj2.descrItem);
	}
	

    public void adicionaOutUndMedida(Reg0220 outUnd) {
    	outrasUnidades.add(outUnd);	
    }
    
    public int qtdeOutUnd() {
    	return outrasUnidades.size(); 
    }
    
    public void excluirOutUndMedida(Reg0220 outUnd) {
    	outrasUnidades.remove(outUnd);
    }
    
    public Reg0220 getOutrasUnid(int posicao) {
    	return outrasUnidades.get(posicao);
    }
	public List<Reg0220> getOutrasUndMedidas() {
		return outrasUnidades;
	}
	
	public void adicionaAlteracaoItem(Reg0205 altItem) {
		alteracaoItem.add(altItem);
	}
	
	public List<Reg0205> getAlteracaoItem() {
		return alteracaoItem;
	}

}
