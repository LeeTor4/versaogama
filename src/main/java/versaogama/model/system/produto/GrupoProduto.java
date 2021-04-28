package versaogama.model.system.produto;

public class GrupoProduto {

	private Long id;
	private Long idPaiEmp;
	private Long idPaiEst;
	private String valor;
	private String descricao;
	
    private SituacaoGrpProdutoSpedFiscal sitGrpProdSpedFiscal;
    
    
    public GrupoProduto() {
    	sitGrpProdSpedFiscal = new SituacaoGrpProdutoSpedFiscal();
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

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	public SituacaoGrpProdutoSpedFiscal getSitGrpProdSpedFiscal() {
		return sitGrpProdSpedFiscal;
	}

	public void setSitGrpProdSpedFiscal(SituacaoGrpProdutoSpedFiscal sitGrpProdSpedFiscal) {
		this.sitGrpProdSpedFiscal = sitGrpProdSpedFiscal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((idPaiEmp == null) ? 0 : idPaiEmp.hashCode());
		result = prime * result + ((idPaiEst == null) ? 0 : idPaiEst.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		GrupoProduto other = (GrupoProduto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
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
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

    

	
}
