package versaogama.model.sped;

import java.time.LocalDate;

public class RegE200 {
	
	private Long   Id;
	private Long   IdPai;
	private String reg;
	private String uf;
	private LocalDate dtIni;
	private LocalDate dtFin;
	
	
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
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public LocalDate getDtIni() {
		return dtIni;
	}
	public void setDtIni(LocalDate dtIni) {
		this.dtIni = dtIni;
	}
	public LocalDate getDtFin() {
		return dtFin;
	}
	public void setDtFin(LocalDate dtFin) {
		this.dtFin = dtFin;
	}
	
	

}
