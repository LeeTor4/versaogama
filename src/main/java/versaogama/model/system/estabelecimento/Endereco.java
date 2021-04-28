package versaogama.model.system.estabelecimento;

public class Endereco {

	private Long   id;
	private String nmLogradouro;
	private String numLogradouro;
	private String compl;
	private String bairro;
	private String cep;
    private Long codUf;
    private String uf;
    private Long  codMun;
    private String nmMun;
  
    public Endereco() {
		
	}
	public Endereco(Long id) {
		this.id = id;
	}
	
	
	public Endereco(Long id, String nmLogradouro, String numLogradouro) {
		super();
		this.id = id;
		this.nmLogradouro = nmLogradouro;
		this.numLogradouro = numLogradouro;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNmLogradouro() {
		return nmLogradouro;
	}
	public void setNmLogradouro(String nmLogradouro) {
		this.nmLogradouro = nmLogradouro;
	}
	public String getNumLogradouro() {
		return numLogradouro;
	}
	public void setNumLogradouro(String numLogradouro) {
		this.numLogradouro = numLogradouro;
	}
	public String getCompl() {
		return compl;
	}
	public void setCompl(String compl) {
		this.compl = compl;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public Long getCodUf() {
		return codUf;
	}
	public void setCodUf(Long codUf) {
		this.codUf = codUf;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Long getCodMun() {
		return codMun;
	}
	public void setCodMun(Long codMun) {
		this.codMun = codMun;
	}
	public String getNmMun() {
		return nmMun;
	}
	public void setNmMun(String nmMun) {
		this.nmMun = nmMun;
	}
	
}
