package versaogama.dao.estabelecimentodao.equipecf;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.equipecf.ReducaoZ;

public interface ReducaoZDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	Long                      salvar(ReducaoZ rdz) throws SQLException;
	Long                      cadastrar(ReducaoZ rdz) throws SQLException;
	void                      atualizar(ReducaoZ rdz) throws SQLException;
	ReducaoZ        getNotaFiscal(Integer codigo) throws SQLException;
	List<ReducaoZ>  getNotasFiscais() throws SQLException;
	
}
