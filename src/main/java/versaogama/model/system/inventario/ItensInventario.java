package versaogama.model.system.inventario;

public class ItensInventario {
	
	private Long      id;
	private Long      idPai;
	private String    codItem;
	private String    und;
	private Double    qtde;
	private Double    vlUnit;
	private Double    vlItem;
	private String    indProp;
	private String    codPart;
	private String    txtCompl;
	private String    codCta;
	private Double    vlItemIr;
	
	
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
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	
	public String getUnd() {
		return und;
	}
	public void setUnd(String und) {
		this.und = und;
	}
	public Double getQtde() {
		return qtde;
	}
	public void setQtde(Double qtde) {
		this.qtde = qtde;
	}
	public Double getVlUnit() {
		return vlUnit;
	}
	public void setVlUnit(Double vlUnit) {
		this.vlUnit = vlUnit;
	}
	public Double getVlItem() {
		return vlItem;
	}
	public void setVlItem(Double vlItem) {
		this.vlItem = vlItem;
	}
	public String getIndProp() {
		return indProp;
	}
	public void setIndProp(String indProp) {
		this.indProp = indProp;
	}
	public String getCodPart() {
		return codPart;
	}
	public void setCodPart(String codPart) {
		this.codPart = codPart;
	}
	public String getTxtCompl() {
		return txtCompl;
	}
	public void setTxtCompl(String txtCompl) {
		this.txtCompl = txtCompl;
	}
	public String getCodCta() {
		return codCta;
	}
	public void setCodCta(String codCta) {
		this.codCta = codCta;
	}
	public Double getVlItemIr() {
		return vlItemIr;
	}
	public void setVlItemIr(Double vlItemIr) {
		this.vlItemIr = vlItemIr;
	}
	
	
	
	 
}
