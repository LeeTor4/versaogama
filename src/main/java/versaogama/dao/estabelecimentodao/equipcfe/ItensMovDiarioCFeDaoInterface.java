package versaogama.dao.estabelecimentodao.equipcfe;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.cfe.ItensMovDiarioCFe;


public interface ItensMovDiarioCFeDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	void                      salvar(ItensMovDiarioCFe item) throws SQLException;
	void                      cadastrar(ItensMovDiarioCFe item) throws SQLException;
	void                   atualizar(ItensMovDiarioCFe item) throws SQLException;
	ItensMovDiarioCFe        getItemMovDiario(Integer codigo) throws SQLException;
	List<ItensMovDiarioCFe>  getItensMovDiario() throws SQLException;
}
