package versaogama.dao.estabelecimentodao.produto;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.produto.UnidadeMedida;


public interface UnidadeMedidaDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	void                      salvar(UnidadeMedida undMed) throws SQLException;
	void                      cadastrar(UnidadeMedida undMed) throws SQLException;
	void                      atualizar(UnidadeMedida undMed) throws SQLException;
	UnidadeMedida        getUnidadeMedida(Integer codigo) throws SQLException;
	List<UnidadeMedida>  getUnidadesMedidas() throws SQLException;
}
