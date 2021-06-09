package versaogama.model.system.empresa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import versaogama.model.system.estabelecimento.Estabelecimento;

public class Empresa {

	private Long      id;
	private String    razaoSocial;
	private String    nmFantasia;
	private String    cnpjBase;
	private String    cpf;
	private LocalDate dtIniAtiv;
	private List<Estabelecimento> estabs = new ArrayList<>();
	
	private Map<String,Empresa> mpEmp = new HashMap<String,Empresa>();
	
	public Empresa() {
		
	}
	
	public Empresa(Long id) {
		super();
		this.id = id;

	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getNmFantasia() {
		return nmFantasia;
	}
	public void setNmFantasia(String nmFantasia) {
		this.nmFantasia = nmFantasia;
	}
	public String getCnpjBase() {
		return cnpjBase;
	}
	public void setCnpjBase(String cnpjBase) {
		this.cnpjBase = cnpjBase;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public LocalDate getDtIniAtiv() {
		return dtIniAtiv;
	}
	public void setDtIniAtiv(LocalDate dtIniAtiv) {
		this.dtIniAtiv = dtIniAtiv;
	}
	public List<Estabelecimento> getEstabs() {
		return estabs;
	}
	public void setEstabs(List<Estabelecimento> estabs) {
		this.estabs = estabs;
	}
	
	public void adicionaEstab(Estabelecimento est) {
		this.estabs.add(est);
	}

	public Map<String, Empresa> getMpEmp() {
		return mpEmp;
	}

	public void setMpEmp(Map<String, Empresa> mpEmp) {
		this.mpEmp = mpEmp;
	}

	
	
}
