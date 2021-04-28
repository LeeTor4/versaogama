package versaogama.dao.estabelecimentodao.notafiscal;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.notafiscal.NotaFiscal;


public interface NotaFiscalDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	Long                      salvar(NotaFiscal nota) throws SQLException;
	Long                      cadastrar(NotaFiscal nota) throws SQLException;
	void                      atualizar(NotaFiscal nota) throws SQLException;
	NotaFiscal        getNotaFiscal(Integer codigo) throws SQLException;
	List<NotaFiscal>  getNotasFiscais() throws SQLException;
}
