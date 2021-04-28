package versaogama.model.system.notafiscal;

public enum TipoEmissor {
	
	PROPRIA("0"),TERCEIRO("1");
	
	private String descricao;
	
	private TipoEmissor(String descricao) {
	   this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}
