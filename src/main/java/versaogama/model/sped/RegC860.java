package versaogama.model.sped;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegC860 {

	private Long      id;
	private Long      idPai;
	private String    reg;
	private String    codModDocFiscal;
	private String    numSerieEquipSat;
	private LocalDate dtEmissao;
	private String    docInicial;
	private String    docFinal;
	
	private List<RegC870> regsC870 = new ArrayList<RegC870>();
	private List<RegC890> regsC890 = new ArrayList<RegC890>();
	
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
	public String getCodModDocFiscal() {
		return codModDocFiscal;
	}
	public void setCodModDocFiscal(String codModDocFiscal) {
		this.codModDocFiscal = codModDocFiscal;
	}
	public String getNumSerieEquipSat() {
		return numSerieEquipSat;
	}
	public void setNumSerieEquipSat(String numSerieEquipSat) {
		this.numSerieEquipSat = numSerieEquipSat;
	}
	public LocalDate getDtEmissao() {
		return dtEmissao;
	}
	public void setDtEmissao(LocalDate dtEmissao) {
		this.dtEmissao = dtEmissao;
	}
	public String getDocInicial() {
		return docInicial;
	}
	public void setDocInicial(String docInicial) {
		this.docInicial = docInicial;
	}
	public String getDocFinal() {
		return docFinal;
	}
	public void setDocFinal(String docFinal) {
		this.docFinal = docFinal;
	}
	
	public void adicionaItensCF(RegC870 regC870) {
		regsC870.add(regC870);
	}
	
	public void adicionaResumoDarioCF(RegC890 regC890) {
		regsC890.add(regC890);
	}
	public List<RegC870> getRegsC870() {
		return regsC870;
	}
	public List<RegC890> getRegsC890() {
		return regsC890;
	}

}
