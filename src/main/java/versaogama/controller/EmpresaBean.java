package versaogama.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import versaogama.conexao.Pool;
import versaogama.dao.empresadao.EmpresaDAO;
import versaogama.dao.estabelecimentodao.estabelecimento.EstabelecimentoDao;
import versaogama.model.system.empresa.Empresa;
import versaogama.model.system.estabelecimento.Estabelecimento;

@Named
@SessionScoped 
public class EmpresaBean implements Serializable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 4026290446837347258L;
	private Empresa emp = new Empresa();
	private Estabelecimento est = new Estabelecimento();
	private EmpresaDAO empDao; 
	private EstabelecimentoDao estDao;

	
	private Integer estabId;
	
	public EmpresaBean() {
	    Pool pool = new Pool();
	    empDao    = new EmpresaDAO(pool);
	    estDao    = new EstabelecimentoDao(pool);
	}
	public Empresa getEmp() {
		return emp;
	}

    
	public Estabelecimento getEst() {
		return est;
	}
	
	public void setEmp(Empresa emp) {
		this.emp = emp;
	}
	public String gravar() throws SQLException {
		System.out.println("Gravando empresa " + this.emp.getRazaoSocial());
		empDao.salvar(this.emp);
		emp = new Empresa();
	    return "empresa?faces-redirect=true";
	}
	
	public void adicionaEstab() throws SQLException {
		this.emp.adicionaEstab(est);
	    est = new Estabelecimento();	
	}
	
	public List<Estabelecimento> getEstabelecimentoDaEmpresa() throws SQLException{
		return this.emp.getEstabs();
	}


	public List<Estabelecimento> todosEstabelecimentos() throws SQLException{ 
		return this.estDao.getEstabelecimentos();
	}
	
    public Integer getEstabId() {
		return estabId;
	}
    
    public void setEstabId(Integer estabId) {
		this.estabId = estabId;
	}
}
