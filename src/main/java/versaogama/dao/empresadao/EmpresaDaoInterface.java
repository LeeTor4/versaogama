package versaogama.dao.empresadao;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.empresa.Empresa;

public interface EmpresaDaoInterface {

	void              excluir(Integer codigo) throws SQLException;
	void              cadastrar(Empresa empresa) throws SQLException;
	void               salvar(Empresa empresa) throws SQLException;
	void            atualizar(Empresa empresa) throws SQLException;
	Empresa        getEmpresa(Integer codigo) throws SQLException;
	List<Empresa> getEmpresas() throws SQLException;
}
