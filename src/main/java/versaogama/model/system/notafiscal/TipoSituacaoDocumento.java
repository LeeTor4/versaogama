package versaogama.model.system.notafiscal;

public enum TipoSituacaoDocumento {

	REGULAR("00"),
	NORMAL_EXTEMPORANEO("01"),
	CANCELADO("02"),
	CANCELADO_EXTEMPORANEO("03"),
	DENEGADA("04"),
	INUTILIZADA("05"),
	COMPLEMENTAR("06"),
	COMPL_EXTEMPORANEO("07"),
	REGIME_ESP_OU_NORMA_ESP("08"),
	DOCUMENTO_FISCAL_SUBST("09");
	
	private String descricao;
	
	private TipoSituacaoDocumento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
