package versaogama.dao.estabelecimentodao.equipcfe;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.cfe.EquipamentoCFe;


public interface EquipamentoCFeDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	void                      salvar(EquipamentoCFe equip) throws SQLException;
	void                      cadastrar(EquipamentoCFe equip) throws SQLException;
	void                   atualizar(EquipamentoCFe equip) throws SQLException;
	EquipamentoCFe        getEquipamento(Integer codigo) throws SQLException;
	List<EquipamentoCFe>  getEquipamentos() throws SQLException;
}
