package versaogama.model.system.estabelecimento;

public class Participante {

	private Long id;
	private Long idPaiEmp;
	private Long idpaiEst;
	private String codPart;
	private String nome;
	private String codPais;
	private String cnpjCpf;
	private String inscEstadual;
	private String codMunicipio;
	private String inscMunicipal;

	private Endereco endereco;

	public Participante() {
		endereco = new Endereco();
	}
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

	public Long getIdpaiEst() {
		return idpaiEst;
	}

	public void setIdpaiEst(Long idpaiEst) {
		this.idpaiEst = idpaiEst;
	}

	public String getCodPart() {
		return codPart;
	}

	public void setCodPart(String codPart) {
		this.codPart = codPart;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodPais() {
		return codPais;
	}

	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}

	public String getCnpjCpf() {
		return cnpjCpf;
	}

	public void setCnpjCpf(String cnpjCpf) {
		this.cnpjCpf = cnpjCpf;
	}

	public String getInscEstadual() {
		return inscEstadual;
	}

	public void setInscEstadual(String inscEstadual) {
		this.inscEstadual = inscEstadual;
	}

	public String getCodMunicipio() {
		return codMunicipio;
	}

	public void setCodMunicipio(String codMunicipio) {
		this.codMunicipio = codMunicipio;
	}

	public String getInscMunicipal() {
		return inscMunicipal;
	}

	public void setInscMunicipal(String inscMunicipal) {
		this.inscMunicipal = inscMunicipal;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codPart == null) ? 0 : codPart.hashCode());
		result = prime * result + ((idPaiEmp == null) ? 0 : idPaiEmp.hashCode());
		result = prime * result + ((idpaiEst == null) ? 0 : idpaiEst.hashCode());
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
		Participante other = (Participante) obj;
		if (codPart == null) {
			if (other.codPart != null)
				return false;
		} else if (!codPart.equals(other.codPart))
			return false;
		if (idPaiEmp == null) {
			if (other.idPaiEmp != null)
				return false;
		} else if (!idPaiEmp.equals(other.idPaiEmp))
			return false;
		if (idpaiEst == null) {
			if (other.idpaiEst != null)
				return false;
		} else if (!idpaiEst.equals(other.idpaiEst))
			return false;
		return true;
	}
	
	
	
}
