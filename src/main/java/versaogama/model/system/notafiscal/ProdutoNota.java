package versaogama.model.system.notafiscal;

import java.util.ArrayList;
import java.util.List;


public class ProdutoNota {
	
	private Long   id;
	private Long   idPai;
	private String numItem;
	private String codItem;
	private Double qtde;
	private String und;
	private Double vlUnitItem;
	private Double vlProd;
	private Double vlDesc;
	private Double vlItem;
	private String cstIcms;
	private String cfop;
	
	private List<ProdutoNota> produtos = new ArrayList<>();
	public ProdutoNota() {

	}
	
	
	public ProdutoNota(Long idPai, String numItem, String codItem, Double qtde, String und, Double vlUnitItem,Double vlProd,
			Double vlDesc,Double vlItem, String cstIcms, String cfop) {
		super();
		this.idPai = idPai;
		this.numItem = numItem;
		this.codItem = codItem;
		this.qtde = qtde;
		this.und = und;
		this.vlUnitItem = vlUnitItem;
		this.vlProd = vlProd;
		this.vlDesc = vlDesc;
		this.vlItem = vlItem;
		this.cstIcms = cstIcms;
		this.cfop = cfop;
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
	public String getNumItem() {
		return numItem;
	}
	public void setNumItem(String numItem) {
		this.numItem = numItem;
	}
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	public Double getQtde() {
		return qtde;
	}
	public void setQtde(Double qtde) {
		this.qtde = qtde;
	}
	public String getUnd() {
		return und;
	}
	public void setUnd(String und) {
		this.und = und;
	}
	public Double getVlUnitItem() {
		return vlUnitItem;
	}
	public void setVlUnitItem(Double vlUnitItem) {
		this.vlUnitItem = vlUnitItem;
	}
	
	public Double getVlProd() {
		return vlProd;
	}


	public void setVlProd(Double vlProd) {
		this.vlProd = vlProd;
	}


	public Double getVlDesc() {
		return vlDesc;
	}


	public void setVlDesc(Double vlDesc) {
		this.vlDesc = vlDesc;
	}


	public Double getVlItem() {
		return vlItem;
	}
	public void setVlItem(Double vlItem) {
		this.vlItem = vlItem;
	}
	public String getCstIcms() {
		return cstIcms;
	}
	public void setCstIcms(String cstIcms) {
		this.cstIcms = cstIcms;
	}
	public String getCfop() {
		return cfop;
	}
	public void setCfop(String cfop) {
		this.cfop = cfop;
	}
	


	public List<ProdutoNota> getProdutos() {
		return produtos;
	}
	

}
