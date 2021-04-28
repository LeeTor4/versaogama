package versaogama.model.system.produto;

public class UnidadeMedida {
	
	private Long id;
	private Long idPaiEmp;
	private Long idPaiEst;
    private String unidade;
    private String descricao;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdPaiEmp() {
		return idPaiEmp;
	}
	public void setIdPaiEmp(Long idPaiEmp) {
		this.idPaiEmp = idPaiEmp;
	}
	public Long getIdPaiEst() {
		return idPaiEst;
	}
	public void setIdPaiEst(Long idPaiEst) {
		this.idPaiEst = idPaiEst;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
}
