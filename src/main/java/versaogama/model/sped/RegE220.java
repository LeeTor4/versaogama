package versaogama.model.sped;

public class RegE220 {

	private Long   Id;
	private Long   IdPai;
	private String reg;
	private String CodigoDeAjusteApuracao;
	private String DescricaoComplementarAjuste;
	private Double vlAjusteApuracao;
	
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public Long getIdPai() {
		return IdPai;
	}
	public void setIdPai(Long idPai) {
		IdPai = idPai;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public String getCodigoDeAjusteApuracao() {
		return CodigoDeAjusteApuracao;
	}
	public void setCodigoDeAjusteApuracao(String codigoDeAjusteApuracao) {
		CodigoDeAjusteApuracao = codigoDeAjusteApuracao;
	}
	public String getDescricaoComplementarAjuste() {
		return DescricaoComplementarAjuste;
	}
	public void setDescricaoComplementarAjuste(String descricaoComplementarAjuste) {
		DescricaoComplementarAjuste = descricaoComplementarAjuste;
	}
	public Double getVlAjusteApuracao() {
		return vlAjusteApuracao;
	}
	public void setVlAjusteApuracao(Double vlAjusteApuracao) {
		this.vlAjusteApuracao = vlAjusteApuracao;
	}
	
	
	
}
