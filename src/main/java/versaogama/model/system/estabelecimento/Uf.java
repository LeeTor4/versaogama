package versaogama.model.system.estabelecimento;

import java.util.ArrayList;
import java.util.List;

public class Uf {

	private Long id;
	private String nome;
	private String sigla;
    private List<Municipio> municipios = new ArrayList<>();
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public List<Municipio> getMunicipios() {
		return municipios;
	}
	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}
	
    
    public void adicionaMunicipios(Municipio mun) {
    	municipios.add(mun);
    }
    	

	
	
}
