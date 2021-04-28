package versaogama.model.system.notafiscal;

public class TotalizadorNota {
	
	private Long id;
	private Long idPai;
	private String cstIcms;
	private String cfop;
	private Double vlOper;
	
	
	public Long getId() {
		return id;
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
	public Double getVlOper() {
		return vlOper;
	}
	public void setVlOper(Double vlOper) {
		this.vlOper = vlOper;
	}
	
	

}
