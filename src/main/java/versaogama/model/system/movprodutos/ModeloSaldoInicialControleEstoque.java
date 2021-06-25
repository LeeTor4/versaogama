package versaogama.model.system.movprodutos;

public class ModeloSaldoInicialControleEstoque {
	
	private Long id;
	private String cnpj;
	private String ano;
	private String codItem;
	private String codAntItem;
	private String descricao;
	private Double qtdeInicial;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getCodAntItem() {
		return codAntItem;
	}
	public void setCodAntItem(String codAntItem) {
		this.codAntItem = codAntItem;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Double getQtdeInicial() {
		return qtdeInicial;
	}
	public void setQtdeInicial(Double qtdeInicial) {
		this.qtdeInicial = qtdeInicial;
	}
	
	

}
