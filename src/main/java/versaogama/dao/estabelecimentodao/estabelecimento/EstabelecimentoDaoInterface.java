package versaogama.dao.estabelecimentodao.estabelecimento;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.estabelecimento.Estabelecimento;


public interface EstabelecimentoDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	void                      salvar(Estabelecimento est) throws SQLException;
	void                      cadastrar(Estabelecimento est) throws SQLException;
	void                   atualizar(Estabelecimento est) throws SQLException;
	Estabelecimento        getEstabelecimento(Integer codigo) throws SQLException;
	List<Estabelecimento>  getEstabelecimentos() throws SQLException;
}
