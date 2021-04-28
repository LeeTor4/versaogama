package versaogama.dao.estabelecimentodao.produto;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.produto.AlteracaoItem;

public interface AlteracaoItemDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	void                      salvar(AlteracaoItem alt) throws SQLException;
	void                      cadastrar(AlteracaoItem alt) throws SQLException;
	void                      atualizar(AlteracaoItem alt) throws SQLException;
	AlteracaoItem        getAlteracaoItem(Integer codigo) throws SQLException;
	List<AlteracaoItem>  getAltItens() throws SQLException;
}
