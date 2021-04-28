package versaogama.dao.estabelecimentodao.produto;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.produto.OutrasUnid;


public interface OutrasUnidDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	void                      salvar(OutrasUnid outUnd) throws SQLException;
	void                      cadastrar(OutrasUnid outUnd) throws SQLException;
	void                      atualizar(OutrasUnid outUnd) throws SQLException;
	OutrasUnid        getOutUnid(Integer codigo) throws SQLException;
	List<OutrasUnid>  getOutrasUnidsMed() throws SQLException;
}
