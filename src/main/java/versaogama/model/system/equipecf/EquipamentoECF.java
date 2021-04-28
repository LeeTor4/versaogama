package versaogama.model.system.equipecf;

import java.util.ArrayList;
import java.util.List;

public class EquipamentoECF {
	
	private Long              id;
	private Long              id_pai_emp;
	private Long              id_pai_est;
	private Long              idPai;
	private String            codModDocFiscal;
	private String            modeloEquip;
	private String            numSerieFabECF;
	private String            numECF;
	private List<ReducaoZ>    reducoes;
	
	public EquipamentoECF() {
		reducoes = new ArrayList<>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId_pai_emp() {
		return id_pai_emp;
	}
	public void setId_pai_emp(Long id_pai_emp) {
		this.id_pai_emp = id_pai_emp;
	}
	public Long getId_pai_est() {
		return id_pai_est;
	}
	public void setId_pai_est(Long id_pai_est) {
		this.id_pai_est = id_pai_est;
	}
	
	public Long getIdPai() {
		return idPai;
	}
	public void setIdPai(Long idPai) {
		this.idPai = idPai;
	}
	public String getCodModDocFiscal() {
		return codModDocFiscal;
	}
	public void setCodModDocFiscal(String codModDocFiscal) {
		this.codModDocFiscal = codModDocFiscal;
	}
	public String getModeloEquip() {
		return modeloEquip;
	}
	public void setModeloEquip(String modeloEquip) {
		this.modeloEquip = modeloEquip;
	}
	public String getNumSerieFabECF() {
		return numSerieFabECF;
	}
	public void setNumSerieFabECF(String numSerieFabECF) {
		this.numSerieFabECF = numSerieFabECF;
	}
	public String getNumECF() {
		return numECF;
	}
	public void setNumECF(String numECF) {
		this.numECF = numECF;
	}
	public List<ReducaoZ> getReducoes() {
		return reducoes;
	}
	public void setReducoes(List<ReducaoZ> reducoes) {
		this.reducoes = reducoes;
	}

	public void adicionaReducoes(ReducaoZ rdz) {
		reducoes.add(rdz);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_pai_emp == null) ? 0 : id_pai_emp.hashCode());
		result = prime * result + ((id_pai_est == null) ? 0 : id_pai_est.hashCode());
		result = prime * result + ((numECF == null) ? 0 : numECF.hashCode());
		result = prime * result + ((numSerieFabECF == null) ? 0 : numSerieFabECF.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EquipamentoECF other = (EquipamentoECF) obj;
		if (id_pai_emp == null) {
			if (other.id_pai_emp != null)
				return false;
		} else if (!id_pai_emp.equals(other.id_pai_emp))
			return false;
		if (id_pai_est == null) {
			if (other.id_pai_est != null)
				return false;
		} else if (!id_pai_est.equals(other.id_pai_est))
			return false;
		if (numECF == null) {
			if (other.numECF != null)
				return false;
		} else if (!numECF.equals(other.numECF))
			return false;
		if (numSerieFabECF == null) {
			if (other.numSerieFabECF != null)
				return false;
		} else if (!numSerieFabECF.equals(other.numSerieFabECF))
			return false;
		return true;
	}
	
	
}
