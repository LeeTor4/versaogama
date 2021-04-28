package versaogama.model.system.notafiscal;

import java.time.LocalDate;

public class NotaFiscal {

	private Long      id;
	private Long      idPaiEmp;
	private Long      idPaiEst;
	private Long      idPai;
	private String    tipoOperacao;
    private String    tipoEmissor;
	private String    codParticipante;
	private String    especieDoc; // Conforme tabela 10 layout Fortes
	private String    numeroDocumento;
	private String    chaveDocumento;
	private LocalDate dataEmisaso;
	private LocalDate dataEntSai;
	//campo rementent/destinatario
	private Double    vlTotalProd;
	private Double    vlDesconto;
	private Double    vlMercadorias;
   

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
	public Long getIdPai() {
		return idPai;
	}
	public void setIdPai(Long idPai) {
		this.idPai = idPai;
	}
	public String getEspecieDoc() {
		return especieDoc;
	}
	public void setEspecieDoc(String especieDoc) {
		this.especieDoc = especieDoc;
	}
	
	public String getTipoOperacao() {
		return tipoOperacao;
	}
	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	public String getTipoEmissor() {
		return tipoEmissor;
	}
	public void setTipoEmissor(String tipoEmissor) {
		this.tipoEmissor = tipoEmissor;
	}
	public String getCodParticipante() {
		return codParticipante;
	}
	public void setCodParticipante(String codParticipante) {
		this.codParticipante = codParticipante;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getChaveDocumento() {
		return chaveDocumento;
	}
	public void setChaveDocumento(String chaveDocumento) {
		this.chaveDocumento = chaveDocumento;
	}
	public LocalDate getDataEmisaso() {
		return dataEmisaso;
	}
	public void setDataEmisaso(LocalDate dataEmisaso) {
		this.dataEmisaso = dataEmisaso;
	}
	public LocalDate getDataEntSai() {
		return dataEntSai;
	}
	public void setDataEntSai(LocalDate dataEntSai) {
		this.dataEntSai = dataEntSai;
	}
	public Double getVlTotalProd() {
		return vlTotalProd;
	}
	public void setVlTotalProd(Double vlTotalProd) {
		this.vlTotalProd = vlTotalProd;
	}
	public Double getVlDesconto() {
		return vlDesconto;
	}
	public void setVlDesconto(Double vlDesconto) {
		this.vlDesconto = vlDesconto;
	}
	public Double getVlMercadorias() {
		return vlMercadorias;
	}
	public void setVlMercadorias(Double vlMercadorias) {
		this.vlMercadorias = vlMercadorias;
	}
	
	
}
