package versaogama.model.sped;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegC405 {

	private Long                    id;
	private Long                 idPai;
	private Long              idPaiECF;
	private String                 reg;
	private LocalDate            dtDoc;
	private String          posicaoCRO; 
	private String          posicaoRDZ; 
	/**
	 * Número do Contador de Ordem de Operação do último documento emitido no dia. (Número do COO na Redução Z) 
	 * */
	private String           numCOOFin;
	private Double  vlGrandeTotalFinal;
	private Double        vlVendaBruta;
	
    private List<RegC420> regsC420 = new ArrayList<RegC420>();
    private List<RegC490> regsC490 = new ArrayList<RegC490>();
    
    
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
	
	public Long getIdPaiECF() {
		return idPaiECF;
	}
	public void setIdPaiECF(Long idPaiECF) {
		this.idPaiECF = idPaiECF;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public LocalDate getDtDoc() {
		return dtDoc;
	}
	public void setDtDoc(LocalDate dtDoc) {
		this.dtDoc = dtDoc;
	}
	public String getPosicaoCRO() {
		return posicaoCRO;
	}
	public void setPosicaoCRO(String posicaoCRO) {
		this.posicaoCRO = posicaoCRO;
	}
	public String getPosicaoRDZ() {
		return posicaoRDZ;
	}
	public void setPosicaoRDZ(String posicaoRDZ) {
		this.posicaoRDZ = posicaoRDZ;
	}
	public String getNumCOOFin() {
		return numCOOFin;
	}
	public void setNumCOOFin(String numCOOFin) {
		this.numCOOFin = numCOOFin;
	}
	public Double getVlGrandeTotalFinal() {
		return vlGrandeTotalFinal;
	}
	public void setVlGrandeTotalFinal(Double vlGrandeTotalFinal) {
		this.vlGrandeTotalFinal = vlGrandeTotalFinal;
	}
	public Double getVlVendaBruta() {
		return vlVendaBruta;
	}
	public void setVlVendaBruta(Double vlVendaBruta) {
		this.vlVendaBruta = vlVendaBruta;
	}
	
	public void adicionaRegC420(RegC420 regC420) {
		regsC420.add(regC420);
	}
	public List<RegC420> getRegsC420() {
		return regsC420;
	}
	
	public void adicionaRegC490(RegC490 regC490) {
		regsC490.add(regC490);
	}
	public List<RegC490> getRegsC490() {
		return regsC490;
	}
}
