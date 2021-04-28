package versaogama.model.system.equipecf;

public class TotalizadorDiarioCuponsFiscais {

	private Long                   id;
	private Long                   id_pai_emp;
	private Long                   id_pai_est;
	private Long                   idPai;
	private String                 cst;
	private String                 cfop;
	private Double                 vlOperacao;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId_pai_emp() {
		return id_pai_emp;
	}
	public void setId_pai_emp(Long id_pai_emp) {
		this.id_pai_emp = id_pai_emp;
	}
	public Long getId_pai_est() {
		return id_pai_est;
	}
	public void setId_pai_est(Long id_pai_est) {
		this.id_pai_est = id_pai_est;
	}
	public Long getIdPai() {
		return idPai;
	}
	public void setIdPai(Long idPai) {
		this.idPai = idPai;
	}
	public String getCst() {
		return cst;
	}
	public void setCst(String cst) {
		this.cst = cst;
	}
	public String getCfop() {
		return cfop;
	}
	public void setCfop(String cfop) {
		this.cfop = cfop;
	}
	public Double getVlOperacao() {
		return vlOperacao;
	}
	public void setVlOperacao(Double vlOperacao) {
		this.vlOperacao = vlOperacao;
	}
	
	
	
}
