package versaogama.model.system.equipecf;

public class ItensMovDiario {

	private Long                   id;
	private Long                   id_pai_emp;
	private Long                   id_pai_est;
	private Long                   idPai;
	private Long                   idPaiRedZ;
	private String                 codItem;
	private Double                 qtde;
	private String                 und;
	private Double                 vlItem;
	private Double                 vlPis;
	private Double                 vlCofins;
	
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
	
	public Long getIdPaiRedZ() {
		return idPaiRedZ;
	}
	public void setIdPaiRedZ(Long idPaiRedZ) {
		this.idPaiRedZ = idPaiRedZ;
	}
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	public Double getQtde() {
		return qtde;
	}
	public void setQtde(Double qtde) {
		this.qtde = qtde;
	}
	public String getUnd() {
		return und;
	}
	public void setUnd(String und) {
		this.und = und;
	}
	public Double getVlItem() {
		return vlItem;
	}
	public void setVlItem(Double vlItem) {
		this.vlItem = vlItem;
	}
	public Double getVlPis() {
		return vlPis;
	}
	public void setVlPis(Double vlPis) {
		this.vlPis = vlPis;
	}
	public Double getVlCofins() {
		return vlCofins;
	}
	public void setVlCofins(Double vlCofins) {
		this.vlCofins = vlCofins;
	}
}
