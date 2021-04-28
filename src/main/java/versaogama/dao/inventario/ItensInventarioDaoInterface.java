package versaogama.dao.inventario;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.inventario.ItensInventario;


public interface ItensInventarioDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	void                      salvar(ItensInventario itn) throws SQLException;
	void                      cadastrar(ItensInventario itn) throws SQLException;
	void                   atualizar(ItensInventario itn) throws SQLException;
	ItensInventario        getItensIventario(Integer codigo) throws SQLException;
	List<ItensInventario>  getItensInventarios() throws SQLException;
}
