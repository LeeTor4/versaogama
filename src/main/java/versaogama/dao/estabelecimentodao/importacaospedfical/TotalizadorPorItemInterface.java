package versaogama.dao.estabelecimentodao.importacaospedfical;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.TotalizadoresPorItem;
public interface TotalizadorPorItemInterface {

	void                        excluir(Integer codigo);
	void                        salvar(TotalizadoresPorItem totItem) throws SQLException;
	void                        atualizar(TotalizadoresPorItem totItem);
	TotalizadoresPorItem        getTotItem(Integer codigo);
	List<TotalizadoresPorItem>  getTotItens() throws SQLException;
}
