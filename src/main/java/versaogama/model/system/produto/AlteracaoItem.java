package versaogama.model.system.produto;

import java.time.LocalDate;

public class AlteracaoItem {
	
	private Long id;
	private Long idPaiEmp;
	private Long idPaiEst;
	private Long idPai;
	private String descrAntItem;
	private LocalDate dtInicial;
	private LocalDate dtFinal;
	private String codAntItem;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdPaiEmp() {
		return idPaiEmp;
	}
	public void setIdPaiEmp(Long idPaiEmp) {
		this.idPaiEmp = idPaiEmp;
	}
	public Long getIdPaiEst() {
		return idPaiEst;
	}
	public void setIdPaiEst(Long idPaiEst) {
		this.idPaiEst = idPaiEst;
	}
	public Long getIdPai() {
		return idPai;
	}
	public void setIdPai(Long idPai) {
		this.idPai = idPai;
	}
	public String getDescrAntItem() {
		return descrAntItem;
	}
	public void setDescrAntItem(String descrAntItem) {
		this.descrAntItem = descrAntItem;
	}
	public LocalDate getDtInicial() {
		return dtInicial;
	}
	public void setDtInicial(LocalDate dtInicial) {
		this.dtInicial = dtInicial;
	}
	public LocalDate getDtFinal() {
		return dtFinal;
	}
	public void setDtFinal(LocalDate dtFinal) {
		this.dtFinal = dtFinal;
	}
	public String getCodAntItem() {
		return codAntItem;
	}
	public void setCodAntItem(String codAntItem) {
		this.codAntItem = codAntItem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codAntItem == null) ? 0 : codAntItem.hashCode());
		result = prime * result + ((dtFinal == null) ? 0 : dtFinal.hashCode());
		result = prime * result + ((dtInicial == null) ? 0 : dtInicial.hashCode());
		result = prime * result + ((idPai == null) ? 0 : idPai.hashCode());
		result = prime * result + ((idPaiEmp == null) ? 0 : idPaiEmp.hashCode());
		result = prime * result + ((idPaiEst == null) ? 0 : idPaiEst.hashCode());
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
		AlteracaoItem other = (AlteracaoItem) obj;
		if (codAntItem == null) {
			if (other.codAntItem != null)
				return false;
		} else if (!codAntItem.equals(other.codAntItem))
			return false;
		if (dtFinal == null) {
			if (other.dtFinal != null)
				return false;
		} else if (!dtFinal.equals(other.dtFinal))
			return false;
		if (dtInicial == null) {
			if (other.dtInicial != null)
				return false;
		} else if (!dtInicial.equals(other.dtInicial))
			return false;
		if (idPai == null) {
			if (other.idPai != null)
				return false;
		} else if (!idPai.equals(other.idPai))
			return false;
		if (idPaiEmp == null) {
			if (other.idPaiEmp != null)
				return false;
		} else if (!idPaiEmp.equals(other.idPaiEmp))
			return false;
		if (idPaiEst == null) {
			if (other.idPaiEst != null)
				return false;
		} else if (!idPaiEst.equals(other.idPaiEst))
			return false;
		return true;
	}
	
	
	
	
	
	

}
