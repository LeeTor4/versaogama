package versaogama.model.system.cfe;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoCFe {

	private Long      id;
	private Long      id_pai_emp;
	private Long      id_pai_est;
	private Long      idPai;
	private String    codModDocFiscal;
	private String    numSerieEquipSat;
	private LocalDate dtEmissao;
	private String    docInicial;
	private String    docFinal;
	
	private List<ResumoDiarioCFe> resumosDiarioCFe = new ArrayList<ResumoDiarioCFe>();
	
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
	public String getCodModDocFiscal() {
		return codModDocFiscal;
	}
	public void setCodModDocFiscal(String codModDocFiscal) {
		this.codModDocFiscal = codModDocFiscal;
	}
	public String getNumSerieEquipSat() {
		return numSerieEquipSat;
	}
	public void setNumSerieEquipSat(String numSerieEquipSat) {
		this.numSerieEquipSat = numSerieEquipSat;
	}
	public LocalDate getDtEmissao() {
		return dtEmissao;
	}
	public void setDtEmissao(LocalDate dtEmissao) {
		this.dtEmissao = dtEmissao;
	}
	public String getDocInicial() {
		return docInicial;
	}
	public void setDocInicial(String docInicial) {
		this.docInicial = docInicial;
	}
	public String getDocFinal() {
		return docFinal;
	}
	public void setDocFinal(String docFinal) {
		this.docFinal = docFinal;
	}
	
	public void adicionaResumoDiarioCFe(ResumoDiarioCFe res) {
		resumosDiarioCFe.add(res);
	}
	public List<ResumoDiarioCFe> getResumoDiarioCFe() {
		return resumosDiarioCFe;
	}
	
	
}
