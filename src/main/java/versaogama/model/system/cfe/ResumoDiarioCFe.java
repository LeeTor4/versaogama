package versaogama.model.system.cfe;

public class ResumoDiarioCFe {

	private Long     id;
	private Long     idPaiEmp;
	private Long     idPaiEst;
	private Long     idPai;
	private String   cstIcms;
	private String   cfop;
	private Double   aliqIcms;
	private Double   vlOper;
	private Double   bcIcms;
	private Double   vlIcms;
	private String   obs;
	
	
	
	public Long getId() {
		return id;
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

	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdPai() {
		return idPai;
	}
	public void setIdPai(Long idPai) {
		this.idPai = idPai;
	}
	public String getCstIcms() {
		return cstIcms;
	}
	public void setCstIcms(String cstIcms) {
		this.cstIcms = cstIcms;
	}
	public String getCfop() {
		return cfop;
	}
	public void setCfop(String cfop) {
		this.cfop = cfop;
	}
	public Double getAliqIcms() {
		return aliqIcms;
	}
	public void setAliqIcms(Double aliqIcms) {
		this.aliqIcms = aliqIcms;
	}
	public Double getVlOper() {
		return vlOper;
	}
	public void setVlOper(Double vlOper) {
		this.vlOper = vlOper;
	}
	public Double getBcIcms() {
		return bcIcms;
	}
	public void setBcIcms(Double bcIcms) {
		this.bcIcms = bcIcms;
	}
	public Double getVlIcms() {
		return vlIcms;
	}
	public void setVlIcms(Double vlIcms) {
		this.vlIcms = vlIcms;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	
	
}
