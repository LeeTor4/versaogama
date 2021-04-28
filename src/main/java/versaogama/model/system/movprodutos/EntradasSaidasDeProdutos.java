package versaogama.model.system.movprodutos;

public class EntradasSaidasDeProdutos {

	private Long   id;
	private Long   idPai;
	private Long   idCodItem;
	private String cnpj;
	private String descricao;
	private String ano;
	private String mes;
	private String codItem;
	private String codAntItem;
	
	private Double totQtdeEnt;
	private Double totVlItemEnt;
	
	private Double totQtdeSai;
	private Double totVlItemSai;
	
	
	public EntradasSaidasDeProdutos() {
		
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
	public Long getIdCodItem() {
		return idCodItem;
	}
	public void setIdCodItem(Long idCodItem) {
		this.idCodItem = idCodItem;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	public String getCodAntItem() {
		return codAntItem;
	}
	public void setCodAntItem(String codAntItem) {
		this.codAntItem = codAntItem;
	}
	public Double getTotQtdeEnt() {
		return totQtdeEnt;
	}
	public void setTotQtdeEnt(Double totQtdeEnt) {
		this.totQtdeEnt = totQtdeEnt;
	}
	public Double getTotVlItemEnt() {
		return totVlItemEnt;
	}
	public void setTotVlItemEnt(Double totVlItemEnt) {
		this.totVlItemEnt = totVlItemEnt;
	}
	public Double getTotQtdeSai() {
		return totQtdeSai;
	}
	public void setTotQtdeSai(Double totQtdeSai) {
		this.totQtdeSai = totQtdeSai;
	}
	public Double getTotVlItemSai() {
		return totVlItemSai;
	}
	public void setTotVlItemSai(Double totVlItemSai) {
		this.totVlItemSai = totVlItemSai;
	}
	
	
	
	
}
