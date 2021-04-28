package versaogama.model.system.equipecf;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReducaoZ {

	private Long                   id;
	private Long                   id_pai_emp;
	private Long                   id_pai_est;
	private Long                   id_ecf;
	private Long                   idPai;
	private LocalDate              dtReducaoZ;
	private String                 posicaoCRO;
	private String                 posicaoRDZ;
	private String                 numCOO;
	private BigDecimal             vlGrandeTotal;
	private BigDecimal             vlVendaBruta;
	private List<TotParciaisRDZ>   totaisParcReducoesZ;
	private List<TotalizadorDiarioCuponsFiscais> totaisCuponsFiscais;
	
	public ReducaoZ() {
		totaisParcReducoesZ = new ArrayList<TotParciaisRDZ>();
		totaisCuponsFiscais = new ArrayList<TotalizadorDiarioCuponsFiscais>();
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
	
	public Long getId_ecf() {
		return id_ecf;
	}
	public void setId_ecf(Long id_ecf) {
		this.id_ecf = id_ecf;
	}
	public Long getIdPai() {
		return idPai;
	}
	public void setIdPai(Long idPai) {
		this.idPai = idPai;
	}
	public LocalDate getDtReducaoZ() {
		return dtReducaoZ;
	}
	public void setDtReducaoZ(LocalDate dtReducaoZ) {
		this.dtReducaoZ = dtReducaoZ;
	}
	public String getPosicaoCRO() {
		return posicaoCRO;
	}
	public void setPosicaoCRO(String posicaoCRO) {
		this.posicaoCRO = posicaoCRO;
	}
	public String getPosicaoRDZ() {
		return posicaoRDZ;
	}
	public void setPosicaoRDZ(String posicaoRDZ) {
		this.posicaoRDZ = posicaoRDZ;
	}
	public String getNumCOO() {
		return numCOO;
	}
	public void setNumCOO(String numCOO) {
		this.numCOO = numCOO;
	}
	public BigDecimal getVlGrandeTotal() {
		return vlGrandeTotal;
	}
	public void setVlGrandeTotal(BigDecimal vlGrandeTotal) {
		this.vlGrandeTotal = vlGrandeTotal;
	}
	public BigDecimal getVlVendaBruta() {
		return vlVendaBruta;
	}
	public void setVlVendaBruta(BigDecimal vlVendaBruta) {
		this.vlVendaBruta = vlVendaBruta;
	}
	public List<TotParciaisRDZ> getTotParcReducoesZ() {
		return totaisParcReducoesZ;
	}
	public void setTotParcReducoesZ(List<TotParciaisRDZ> totParcReducoesZ) {
		this.totaisParcReducoesZ = totParcReducoesZ;
	}
	
	public List<TotalizadorDiarioCuponsFiscais> getTotaisCuponsFiscais() {
		return totaisCuponsFiscais;
	}
	public void adicionaTotParcRedZ(TotParciaisRDZ tot) {
		totaisParcReducoesZ.add(tot);
	}
	
	public void adicionaTotalCuponsFiscais(TotalizadorDiarioCuponsFiscais totais) {
		totaisCuponsFiscais.add(totais);
	}
}
