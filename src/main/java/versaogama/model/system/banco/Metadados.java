package versaogama.model.system.banco;

public class Metadados {

	private String nomeBanco;
	private String nomeTabela;
	private Long   linhaTabela;
	private Long   autoIncremento;
	
	
	public String getNomeBanco() {
		return nomeBanco;
	}
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}
	public String getNomeTabela() {
		return nomeTabela;
	}
	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}
	public Long getLinhaTabela() {
		return linhaTabela;
	}
	public void setLinhaTabela(Long linhaTabela) {
		this.linhaTabela = linhaTabela;
	}
	public Long getAutoIncremento() {
		return autoIncremento;
	}
	public void setAutoIncremento(Long autoIncremento) {
		this.autoIncremento = autoIncremento;
	}
	
	
	
}
