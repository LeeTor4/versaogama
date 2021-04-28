package versaogama.model.system.equipecf;

import java.util.ArrayList;
import java.util.List;

public class TotParciaisRDZ {

	private Long                   id;
	private Long                   id_pai_emp;
	private Long                   id_pai_est;
	private Long                   idPai;
	private String                 codTotalizador;
	private Double                 vlAcumuladoTotRedZ;
	private String                 numTotalizador;
	private String                 descrNumTotalizador;
	private List<ItensMovDiario>   itensMovimentoDiario;
	
	public TotParciaisRDZ() {
		itensMovimentoDiario = new ArrayList<>();
	}
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
	public String getCodTotalizador() {
		return codTotalizador;
	}
	public void setCodTotalizador(String codTotalizador) {
		this.codTotalizador = codTotalizador;
	}
	public Double getVlAcumuladoTotRedZ() {
		return vlAcumuladoTotRedZ;
	}
	public void setVlAcumuladoTotRedZ(Double vlAcumuladoTotRedZ) {
		this.vlAcumuladoTotRedZ = vlAcumuladoTotRedZ;
	}
	public String getNumTotalizador() {
		return numTotalizador;
	}
	public void setNumTotalizador(String numTotalizador) {
		this.numTotalizador = numTotalizador;
	}
	public String getDescNumTotalizador() {
		return descrNumTotalizador;
	}
	public void setDescNumTotalizador(String descrNumTotalizador) {
		this.descrNumTotalizador = descrNumTotalizador;
	}
	public List<ItensMovDiario> getItensMovimentoDiario() {
		return itensMovimentoDiario;
	}
	public void setItensMovimentoDiario(List<ItensMovDiario> itensMovimentoDiario) {
		this.itensMovimentoDiario = itensMovimentoDiario;
	}
	
	public void adicionaItensMovDiario(ItensMovDiario itn) {
		itensMovimentoDiario.add(itn);
	}
}
