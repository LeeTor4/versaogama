package versaogama.model.system.produto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Produto {
	
	private Long   id;
	private Long   idPaiEmp;
	private Long   idPaiEst;
	private Long   idGrp;
	private String tipoItem;
	private String codUtilizEmp;
	private String descricao;
    private String ncm;
    private String undPadrao;
    private String codBarras;
    private String cest;
    private String prodEspec;
    private String tipoMed;
    
    
    
    private GrupoProduto grpProd;  
    private List<OutrasUnid>  outrasUndMedidas;
    private ProdutoEspecifico prodEspecifico; 
    private TipoMedicamento   tipoMedicamento;
    private boolean desativado;
    
    private Map<Long, Produto> mpProduto;
    
    
    
    public Produto() {
    	grpProd = new GrupoProduto();
    	outrasUndMedidas = new ArrayList<OutrasUnid>();
    	mpProduto = new HashMap<Long, Produto>();
	}
    
    public Produto(Long idPaiEmp, Long idPaiEst , String codUtilizEmp,String descricao, String ncm,String undPadrao) {
    	this.idPaiEmp = idPaiEmp;
    	this.idPaiEst = idPaiEst;
    	this.codUtilizEmp = codUtilizEmp;
    	this.descricao = descricao;
    	this.ncm = ncm;
    	this.undPadrao = undPadrao;
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
	public Long getIdGrp() {
		return idGrp;
	}
	public void setIdGrp(Long idGrp) {
		this.idGrp = idGrp;
	}
	
	public String getTipoItem() {
		return tipoItem;
	}


	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}


	public String getCodUtilizEmp() {
		return codUtilizEmp;
	}
	public void setCodUtilizEmp(String codUtilizEmp) {
		this.codUtilizEmp = codUtilizEmp;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNcm() {
		return ncm;
	}
	public void setNcm(String ncm) {
		this.ncm = ncm;
	}
	public String getUndPadrao() {
		return undPadrao;
	}
	public void setUndPadrao(String undPadrao) {
		this.undPadrao = undPadrao;
	}
	public String getCodBarras() {
		return codBarras;
	}
	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}
	public String getCest() {
		return cest;
	}
	public void setCest(String cest) {
		this.cest = cest;
	}
	public GrupoProduto getGrpProd() {
		return grpProd;
	}
	public void setGrpProd(GrupoProduto grpProd) {
		this.grpProd = grpProd;
	}

	public String getProdEspec() {
		return prodEspec;
	}
	public void setProdEspec(String prodEspec) {
		this.prodEspec = prodEspec;
	}
	public String getTipoMed() {
		return tipoMed;
	}
	public void setTipoMed(String tipoMed) {
		this.tipoMed = tipoMed;
	}
	public ProdutoEspecifico getProdEspecifico() {
		return prodEspecifico;
	}
	public void setProdEspecifico(ProdutoEspecifico prodEspecifico) {
		this.prodEspecifico = prodEspecifico;
	}
	public TipoMedicamento getTipoMedicamento() {
		return tipoMedicamento;
	}
	public void setTipoMedicamento(TipoMedicamento tipoMedicamento) {
		this.tipoMedicamento = tipoMedicamento;
	}
	public boolean isDesativado() {
		return desativado;
	}
	public void setDesativado(boolean desativado) {
		this.desativado = desativado;
	}
   
	
	
    public void adicionaOutUndMedida(OutrasUnid outUnd) {
    	outrasUndMedidas.add(outUnd);	
    }
    
    public int qtdeOutUnd() {
    	return outrasUndMedidas.size(); 
    }
    
    public void excluirOutUndMedida(OutrasUnid outUnd) {
    	outrasUndMedidas.remove(outUnd);
    }
    
    public OutrasUnid getOutrasUnid(int posicao) {
    	return outrasUndMedidas.get(posicao);
    }
	public List<OutrasUnid> getOutrasUndMedidas() {
		return outrasUndMedidas;
	}
	
	
	public Map<Long, Produto> getMpProduto() {
		return mpProduto;
	}
	public void setMpProduto(Map<Long, Produto> mpProduto) {
		this.mpProduto = mpProduto;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codUtilizEmp == null) ? 0 : codUtilizEmp.hashCode());
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
		Produto other = (Produto) obj;
		if (codUtilizEmp == null) {
			if (other.codUtilizEmp != null)
				return false;
		} else if (!codUtilizEmp.equals(other.codUtilizEmp))
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
