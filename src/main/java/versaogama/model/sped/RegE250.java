package versaogama.model.sped;

import java.time.LocalDate;

public class RegE250 {

	private Long   Id;
	private Long   IdPai;
	private String reg;
	private String codigoDaObrigARecolher;
	private Double vlDaObrigacaoARecolher;
	private LocalDate dtVencimentoObrigacao;
	private String codigoDaReceita;
	private String numProcesso;
	private String indOrigemDoProcesso;
	private String descrResumidaProcesso;
	private String descrComplementar;
	//Formato mmyyyy
	private String mesReferencia;
	
	
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
	public String getCodigoDaObrigARecolher() {
		return codigoDaObrigARecolher;
	}
	public void setCodigoDaObrigARecolher(String codigoDaObrigARecolher) {
		this.codigoDaObrigARecolher = codigoDaObrigARecolher;
	}
	public Double getVlDaObrigacaoARecolher() {
		return vlDaObrigacaoARecolher;
	}
	public void setVlDaObrigacaoARecolher(Double vlDaObrigacaoARecolher) {
		this.vlDaObrigacaoARecolher = vlDaObrigacaoARecolher;
	}
	public LocalDate getDtVencimentoObrigacao() {
		return dtVencimentoObrigacao;
	}
	public void setDtVencimentoObrigacao(LocalDate dtVencimentoObrigacao) {
		this.dtVencimentoObrigacao = dtVencimentoObrigacao;
	}
	public String getCodigoDaReceita() {
		return codigoDaReceita;
	}
	public void setCodigoDaReceita(String codigoDaReceita) {
		this.codigoDaReceita = codigoDaReceita;
	}
	public String getNumProcesso() {
		return numProcesso;
	}
	public void setNumProcesso(String numProcesso) {
		this.numProcesso = numProcesso;
	}
	public String getIndOrigemDoProcesso() {
		return indOrigemDoProcesso;
	}
	public void setIndOrigemDoProcesso(String indOrigemDoProcesso) {
		this.indOrigemDoProcesso = indOrigemDoProcesso;
	}
	public String getDescrResumidaProcesso() {
		return descrResumidaProcesso;
	}
	public void setDescrResumidaProcesso(String descrResumidaProcesso) {
		this.descrResumidaProcesso = descrResumidaProcesso;
	}
	public String getDescrComplementar() {
		return descrComplementar;
	}
	public void setDescrComplementar(String descrComplementar) {
		this.descrComplementar = descrComplementar;
	}
	public String getMesReferencia() {
		return mesReferencia;
	}
	public void setMesReferencia(String mesReferencia) {
		this.mesReferencia = mesReferencia;
	}
	
	
}
