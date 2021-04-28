package versaogama.dao.estabelecimentodao.equipecf;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.equipecf.ItensMovDiario;



public interface ItensMovDiarioDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	Long                      salvar(ItensMovDiario itnMovDirario) throws SQLException;
	Long                      cadastrar(ItensMovDiario itnMovDirario) throws SQLException;
	void                      atualizar(ItensMovDiario itnMovDirario) throws SQLException;
	ItensMovDiario        getNotaFiscal(Integer codigo) throws SQLException;
	List<ItensMovDiario>  getNotasFiscais() throws SQLException;
}
