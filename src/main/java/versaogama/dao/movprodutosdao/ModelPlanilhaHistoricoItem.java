package versaogama.dao.movprodutosdao;

import java.time.LocalDate;

public class ModelPlanilhaHistoricoItem {

	private LocalDate Data;

	private String Operacao;

	private String ecfCx;

	private String CFOP;

	private Double EntQtd;

	private Double EntVrUnit;

	private Double EntVrTotal;

	private Double SaidaQtd;

	private Double SaidaVrUnit;

	private Double SaidaVrTotal;

	private Double SaldoQtd;

	private Double SaldoVrUnit;

	private Double SaldoVrTotal;

	private String Modelo;

	private String DecricaoItem;

	private String Numero;

	private String CNPJCPF;

	private String Participante;

	private String ChaveDoc;

	public LocalDate getData() {
		return Data;
	}

	public void setData(LocalDate data) {
		Data = data;
	}

	public String getOperacao() {
		return Operacao;
	}

	public void setOperacao(String operacao) {
		Operacao = operacao;
	}

	public String getEcfCx() {
		return ecfCx;
	}

	public void setEcfCx(String ecfCx) {
		this.ecfCx = ecfCx;
	}

	public String getCFOP() {
		return CFOP;
	}

	public void setCFOP(String cFOP) {
		CFOP = cFOP;
	}

	public Double getEntQtd() {
		return EntQtd;
	}

	public void setEntQtd(Double entQtd) {
		EntQtd = entQtd;
	}

	public Double getEntVrUnit() {
		return EntVrUnit;
	}

	public void setEntVrUnit(Double entVrUnit) {
		EntVrUnit = entVrUnit;
	}

	public Double getEntVrTotal() {
		return EntVrTotal;
	}

	public void setEntVrTotal(Double entVrTotal) {
		EntVrTotal = entVrTotal;
	}

	public Double getSaidaQtd() {
		return SaidaQtd;
	}

	public void setSaidaQtd(Double saidaQtd) {
		SaidaQtd = saidaQtd;
	}

	public Double getSaidaVrUnit() {
		return SaidaVrUnit;
	}

	public void setSaidaVrUnit(Double saidaVrUnit) {
		SaidaVrUnit = saidaVrUnit;
	}

	public Double getSaidaVrTotal() {
		return SaidaVrTotal;
	}

	public void setSaidaVrTotal(Double saidaVrTotal) {
		SaidaVrTotal = saidaVrTotal;
	}

	public Double getSaldoQtd() {
		return SaldoQtd;
	}

	public void setSaldoQtd(Double saldoQtd) {
		SaldoQtd = saldoQtd;
	}

	public Double getSaldoVrUnit() {
		return SaldoVrUnit;
	}

	public void setSaldoVrUnit(Double saldoVrUnit) {
		SaldoVrUnit = saldoVrUnit;
	}

	public Double getSaldoVrTotal() {
		return SaldoVrTotal;
	}

	public void setSaldoVrTotal(Double saldoVrTotal) {
		SaldoVrTotal = saldoVrTotal;
	}

	public String getModelo() {
		return Modelo;
	}

	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	public String getDecricaoItem() {
		return DecricaoItem;
	}

	public void setDecricaoItem(String decricaoItem) {
		DecricaoItem = decricaoItem;
	}

	public String getNumero() {
		return Numero;
	}

	public void setNumero(String numero) {
		Numero = numero;
	}

	public String getCNPJCPF() {
		return CNPJCPF;
	}

	public void setCNPJCPF(String cNPJCPF) {
		CNPJCPF = cNPJCPF;
	}

	public String getParticipante() {
		return Participante;
	}

	public void setParticipante(String participante) {
		Participante = participante;
	}

	public String getChaveDoc() {
		return ChaveDoc;
	}

	public void setChaveDoc(String chaveDoc) {
		ChaveDoc = chaveDoc;
	}
	
}
