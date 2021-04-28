package versaogama.dao.estabelecimentodao.equipecf;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.equipecf.TotParciaisRDZ;


public interface TotParciaisRDZDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	Long                      salvar(TotParciaisRDZ totparRdz) throws SQLException;
	Long                      cadastrar(TotParciaisRDZ totparRdz) throws SQLException;
	void                      atualizar(TotParciaisRDZ totparRdz) throws SQLException;
	TotParciaisRDZ        getNotaFiscal(Integer codigo) throws SQLException;
	List<TotParciaisRDZ>  getNotasFiscais() throws SQLException;
}
