package versaogama.model.system.produto;

import java.util.ArrayList;
import java.util.List;


public class ProdutoNota {

	private Long    id;
	private Long    idPai;
	private String  numItem;
	private String  codItem;
	private String  descricao;
	private Double  qtde;
	private String  und;
	private Double  vlUnit;
	private Double  vlProd;
	private Double  vlDesc;
	private Double  vlItem;
	private String  cfop;
	private String  cst;
	
	private List<ProdutoNota> produtos = new ArrayList<>();
	private List<ProdutoNota> entProds = new ArrayList<>();
	private List<ProdutoNota> saiProds = new ArrayList<>();

	
	public ProdutoNota() {
		
	}
	
    public ProdutoNota(String codItem,Double  qtde,String  und,Double  vlItem,String  cfop,String  cst ) {
		this.codItem = codItem;
		this.qtde = qtde;
		this.und = und;
		this.vlItem = vlItem;
		this.cfop = cfop;
		this.cst  = cst;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	
	public Double getVlUnit() {
		return vlUnit;
	}
	public void setVlUnit(Double vlUnit) {
		this.vlUnit = vlUnit;
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
	public String getCfop() {
		return cfop;
	}
	public void setCfop(String cfop) {
		this.cfop = cfop;
	}
	public String getCst() {
		return cst;
	}
	public void setCst(String cst) {
		this.cst = cst;
	}
	
	
	public List<ProdutoNota> getProdutos() {
		return produtos;
	}
	
	public List<ProdutoNota> getEntProds() {
		return entProds;
	}
	
	public List<ProdutoNota> getSaiProds() {
		return saiProds;
	}
}
