package versaogama.model.system;

import java.time.LocalDate;


public class LoteImportacaoSpedFiscal {

	private Long       id;
	private String     codVersao;
	private String     codFinalidade;
	private LocalDate  dtIni;
	private LocalDate  dtFin;
	private String   nome;
	private String   cnpj;
	private String   cpf;
	private String   uf;
	private String   ie;
	private String   codMun;
	private String   IM;
	private String   suframa;
	private String   indPerfil;
	private String   indAtiv;
	
	
	public LoteImportacaoSpedFiscal(Long id, String   cnpj,LocalDate  dtIni,LocalDate  dtFin) {
		this.id    = id;
		this.cnpj  = cnpj;
		this.dtIni = dtIni;
		this.dtFin = dtFin;
	}
	
	public LoteImportacaoSpedFiscal() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCodVersao() {
		return codVersao;
	}
	public void setCodVersao(String codVersao) {
		this.codVersao = codVersao;
	}
	public String getCodFinalidade() {
		return codFinalidade;
	}
	public void setCodFinalidade(String codFinalidade) {
		this.codFinalidade = codFinalidade;
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
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getIe() {
		return ie;
	}
	public void setIe(String ie) {
		this.ie = ie;
	}
	public String getCodMun() {
		return codMun;
	}
	public void setCodMun(String codMun) {
		this.codMun = codMun;
	}
	public String getIM() {
		return IM;
	}
	public void setIM(String iM) {
		IM = iM;
	}
	public String getSuframa() {
		return suframa;
	}
	public void setSuframa(String suframa) {
		this.suframa = suframa;
	}
	public String getIndPerfil() {
		return indPerfil;
	}
	public void setIndPerfil(String indPerfil) {
		this.indPerfil = indPerfil;
	}
	public String getIndAtiv() {
		return indAtiv;
	}
	public void setIndAtiv(String indAtiv) {
		this.indAtiv = indAtiv;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((dtFin == null) ? 0 : dtFin.hashCode());
		result = prime * result + ((dtIni == null) ? 0 : dtIni.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoteImportacaoSpedFiscal other = (LoteImportacaoSpedFiscal) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (dtFin == null) {
			if (other.dtFin != null)
				return false;
		} else if (!dtFin.equals(other.dtFin))
			return false;
		if (dtIni == null) {
			if (other.dtIni != null)
				return false;
		} else if (!dtIni.equals(other.dtIni))
			return false;
		return true;
	}

	
	
}
