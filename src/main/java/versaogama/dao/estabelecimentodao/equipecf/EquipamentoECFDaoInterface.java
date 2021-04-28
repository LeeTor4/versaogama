package versaogama.dao.estabelecimentodao.equipecf;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.equipecf.EquipamentoECF;


public interface EquipamentoECFDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	Long                      salvar(EquipamentoECF equipEcf) throws SQLException;
	Long                      cadastrar(EquipamentoECF equipEcf) throws SQLException;
	void                      atualizar(EquipamentoECF equipEcf) throws SQLException;
	EquipamentoECF        getEquipamentoFiscal(Integer codigo) throws SQLException;
	List<EquipamentoECF>  getEquipamentosFiscais() throws SQLException;
}
