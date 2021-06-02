package versaogama.bean;

import java.sql.SQLException;

import javax.annotation.ManagedBean;

import versaogama.conexao.Pool;
import versaogama.dao.empresadao.EmpresaDAO;
import versaogama.model.system.empresa.Empresa;

@ManagedBean
public class EmpresaBean {

	private Empresa emp = new Empresa();
	private EmpresaDAO empDao; 
	
	public EmpresaBean() {
	    Pool pool = new Pool();
	    empDao    = new EmpresaDAO(pool);
	}
	public Empresa getEmp() {
		return emp;
	}


	public void gravar() throws SQLException {
		System.out.println("Gravando empresa " + this.emp.getRazaoSocial());
		empDao.salvar(this.emp);
	}
}
