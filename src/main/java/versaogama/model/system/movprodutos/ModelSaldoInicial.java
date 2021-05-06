package versaogama.model.system.movprodutos;

public class ModelSaldoInicial {

	private Long   id;
	private String descricao;
	private String ano;
	private String operacao;
	private String codItem;
	private String codAnItem;
	private Double saldo;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	public String getCodAnItem() {
		return codAnItem;
	}
	public void setCodAnItem(String codAnItem) {
		this.codAnItem = codAnItem;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
}
