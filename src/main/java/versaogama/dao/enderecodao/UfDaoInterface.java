package versaogama.dao.enderecodao;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.estabelecimento.Uf;



public interface UfDaoInterface {

	void              excluir(Integer codigo) throws SQLException;
	void              cadastrar(Uf uf) throws SQLException;
	void               salvar(Uf uf) throws SQLException;
	void            atualizar(Uf uf) throws SQLException;
	Uf        getUf(Integer codigo) throws SQLException;
	List<Uf> getUfs() throws SQLException;
}
