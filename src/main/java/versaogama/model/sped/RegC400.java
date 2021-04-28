package versaogama.model.sped;

import java.util.ArrayList;
import java.util.List;

public class RegC400 {
	
	private Long                  id;
	private Long               idPai;
	private String               reg;
	private String         codModelo;
	private String modeloEquipamento;
	private String    numSerieFabECF;
	private String       numCaixaECF;
	
	private List<RegC405> regsC405 = new ArrayList<RegC405>();
	
	
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
	public String getCodModelo() {
		return codModelo;
	}
	public void setCodModelo(String codModelo) {
		this.codModelo = codModelo;
	}
	public String getModeloEquipamento() {
		return modeloEquipamento;
	}
	public void setModeloEquipamento(String modeloEquipamento) {
		this.modeloEquipamento = modeloEquipamento;
	}
	public String getNumSerieFabECF() {
		return numSerieFabECF;
	}
	public void setNumSerieFabECF(String numSerieFabECF) {
		this.numSerieFabECF = numSerieFabECF;
	}
	public String getNumCaixaECF() {
		return numCaixaECF;
	}
	public void setNumCaixaECF(String numCaixaECF) {
		this.numCaixaECF = numCaixaECF;
	}

	public void adicionaRegC405(RegC405 regC405) {
		regsC405.add(regC405);
	}
	public List<RegC405> getRegsC405() {
		return regsC405;
	}
	
	
}
