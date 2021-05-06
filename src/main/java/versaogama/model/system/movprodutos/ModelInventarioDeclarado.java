package versaogama.model.system.movprodutos;

import java.time.LocalDate;

public class ModelInventarioDeclarado {
	private String    cnpj;
	private String    ano;
	private String    codItem;
	private String    codItemAnt;
	private Double    qtde;
	private Double    vlUnit;
	private Double    vlItem;
	private String    descricao;
	private LocalDate dtInv;
	
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	public String getCodItemAnt() {
		return codItemAnt;
	}
	public void setCodItemAnt(String codItemAnt) {
		this.codItemAnt = codItemAnt;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getDtInv() {
		return dtInv;
	}
	public void setDtInv(LocalDate dtInv) {
		this.dtInv = dtInv;
	}
	
}
