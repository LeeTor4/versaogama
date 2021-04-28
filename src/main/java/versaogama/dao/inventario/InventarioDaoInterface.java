package versaogama.dao.inventario;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.inventario.Inventario;


public interface InventarioDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	void                      salvar(Inventario inv) throws SQLException;
	void                      cadastrar(Inventario inv) throws SQLException;
	void                   atualizar(Inventario inv) throws SQLException;
	Inventario        getInventario(Integer codigo) throws SQLException;
	List<Inventario>  getInventarios() throws SQLException;
}
