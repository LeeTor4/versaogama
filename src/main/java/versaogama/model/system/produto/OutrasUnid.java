package versaogama.model.system.produto;

public class OutrasUnid {

	private Long id;
	private Long idPaiEmp;
	private Long idPaiEst;
	private Long idPai;
	private Produto prod;
	private String codProd;
	private String undMed;
	
	//Utilizar 3 (três) casas decimais
	private Double undEquivPadrao;
	private String codBarras;
	
	
	public OutrasUnid() {
		prod = new Produto();
	}
	
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

	public String getCodProd() {
		return codProd;
	}
	public void setCodProd(String codProd) {
		this.codProd = codProd;
	}
	public Produto getProd() {
		return prod;
	}
	public void setProd(Produto prod) {
		this.prod = prod;
	}
	public String getUndMed() {
		return undMed;
	}
	public void setUndMed(String undMed) {
		this.undMed = undMed;
	}
	public Double getUndEquivPadrao() {
		return undEquivPadrao;
	}
	public void setUndEquivPadrao(Double undEquivPadrao) {
		this.undEquivPadrao = undEquivPadrao;
	}
	public String getCodBarras() {
		return codBarras;
	}
	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPai == null) ? 0 : idPai.hashCode());
		result = prime * result + ((idPaiEmp == null) ? 0 : idPaiEmp.hashCode());
		result = prime * result + ((idPaiEst == null) ? 0 : idPaiEst.hashCode());
		result = prime * result + ((undEquivPadrao == null) ? 0 : undEquivPadrao.hashCode());
		result = prime * result + ((undMed == null) ? 0 : undMed.hashCode());
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
		OutrasUnid other = (OutrasUnid) obj;
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
		if (undEquivPadrao == null) {
			if (other.undEquivPadrao != null)
				return false;
		} else if (!undEquivPadrao.equals(other.undEquivPadrao))
			return false;
		if (undMed == null) {
			if (other.undMed != null)
				return false;
		} else if (!undMed.equals(other.undMed))
			return false;
		return true;
	}
	
    
	
	
}
