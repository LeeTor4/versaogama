package versaogama.model.sped;

import java.util.ArrayList;
import java.util.List;

public class RegC420 {

	private Long     id;
	private Long     idPai;
	private String   reg;

	private String CodTotPar;
	
	private Double vlAcumTot;
	
	private String nrTot;
	
	private String descrNrTot;
	
	private List<RegC425> regsC425 = new ArrayList<RegC425>();
	
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
	
	public String getCodTotPar() {
		return CodTotPar;
	}
	public void setCodTotPar(String codTotPar) {
		CodTotPar = codTotPar;
	}
	public Double getVlAcumTot() {
		return vlAcumTot;
	}
	public void setVlAcumTot(Double vlAcumTot) {
		this.vlAcumTot = vlAcumTot;
	}
	public String getNrTot() {
		return nrTot;
	}
	public void setNrTot(String nrTot) {
		this.nrTot = nrTot;
	}
	public String getDescrNrTot() {
		return descrNrTot;
	}
	public void setDescrNrTot(String descrNrTot) {
		this.descrNrTot = descrNrTot;
	}
	public void setRegsC425(List<RegC425> regsC425) {
		this.regsC425 = regsC425;
	}
	public void adicionaRegC425(RegC425 regC425) {
		regsC425.add(regC425);
	}
	public List<RegC425> getRegsC425() {
		return regsC425;
	}
}
