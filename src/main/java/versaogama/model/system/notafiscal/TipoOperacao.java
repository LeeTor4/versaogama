package versaogama.model.system.notafiscal;

public enum TipoOperacao {

	 ENTRADA("E"),SAIDA("S");
	
	 private String operacao;
	 
	 private TipoOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	 
	 
}
