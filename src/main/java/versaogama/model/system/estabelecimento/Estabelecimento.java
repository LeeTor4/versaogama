package versaogama.model.system.estabelecimento;

import java.util.ArrayList;
import java.util.List;

import versaogama.model.system.empresa.Empresa;
import versaogama.model.system.produto.Produto;

public class Estabelecimento {

	private Long     id;
	private Long     idPai; 
	private Long     idPaiEnd; 
	private String   cnpj;
	private String   ie;
	private String   nome;
	private String   nmFantasia;
	private Endereco end = new Endereco();
	private List<Participante> participantes = new ArrayList<Participante>();
	private List<Produto> produtos  = new ArrayList<>();
	private Empresa  emp = new Empresa();
	
	public Estabelecimento() {
		
		
	}
	
	
	public Estabelecimento(Long idPai, Long idPaiEnd, String cnpj, String nome, String nmFantasia,
			Endereco end, Empresa emp) {
		super();
		
		this.idPai = idPai;
		this.idPaiEnd = idPaiEnd;
		this.cnpj = cnpj;
		this.nome = nome;
		this.nmFantasia = nmFantasia;
		this.end = end;
		this.emp = emp;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public Long getIdPai() {
		return idPai;
	}
	public void setIdPai(Long idPai) {
		this.idPai = idPai;
	}
	
	public Long getIdPaiEnd() {
		return idPaiEnd;
	}
	public void setIdPaiEnd(Long idPaiEnd) {
		this.idPaiEnd = idPaiEnd;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getIe() {
		return ie;
	}
	public void setIe(String ie) {
		this.ie = ie;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNmFantasia() {
		return nmFantasia;
	}
	public void setNmFantasia(String nmFantasia) {
		this.nmFantasia = nmFantasia;
	}
	public Endereco getEnd() {
		return end;
	}
	public void setEnd(Endereco end) {
		this.end = end;
	}
	public Empresa getEmp() {
		return emp;
	}
	public void setEmp(Empresa emp) {
		this.emp = emp;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

    public void adicionaParticipante(Participante par) {   	
    	participantes.add(par);
    }

	public List<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

}
