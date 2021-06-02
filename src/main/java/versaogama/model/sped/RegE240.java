package versaogama.model.sped;

import java.time.LocalDate;

public class RegE240 {

	private Long   Id;
	private Long   IdPai;
	private String reg;
	private String codigoPart;
	private String codModeloDocFiscal;
	private String serie;
	private String subSerie;
	private String numDocumento;
	private LocalDate dtEmissaoDoc;
	private String codigoItem;
	private Double vlAjusteItem;
	private String chaveEletronicaDoc;
	
	
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
	public String getCodigoPart() {
		return codigoPart;
	}
	public void setCodigoPart(String codigoPart) {
		this.codigoPart = codigoPart;
	}
	public String getCodModeloDocFiscal() {
		return codModeloDocFiscal;
	}
	public void setCodModeloDocFiscal(String codModeloDocFiscal) {
		this.codModeloDocFiscal = codModeloDocFiscal;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getSubSerie() {
		return subSerie;
	}
	public void setSubSerie(String subSerie) {
		this.subSerie = subSerie;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public LocalDate getDtEmissaoDoc() {
		return dtEmissaoDoc;
	}
	public void setDtEmissaoDoc(LocalDate dtEmissaoDoc) {
		this.dtEmissaoDoc = dtEmissaoDoc;
	}
	public String getCodigoItem() {
		return codigoItem;
	}
	public void setCodigoItem(String codigoItem) {
		this.codigoItem = codigoItem;
	}
	public Double getVlAjusteItem() {
		return vlAjusteItem;
	}
	public void setVlAjusteItem(Double vlAjusteItem) {
		this.vlAjusteItem = vlAjusteItem;
	}
	public String getChaveEletronicaDoc() {
		return chaveEletronicaDoc;
	}
	public void setChaveEletronicaDoc(String chaveEletronicaDoc) {
		this.chaveEletronicaDoc = chaveEletronicaDoc;
	}
	
	
}
