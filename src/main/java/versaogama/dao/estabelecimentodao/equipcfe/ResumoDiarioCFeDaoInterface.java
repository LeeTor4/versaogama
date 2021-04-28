package versaogama.dao.estabelecimentodao.equipcfe;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.cfe.ResumoDiarioCFe;


public interface ResumoDiarioCFeDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	void                      salvar(ResumoDiarioCFe res) throws SQLException;
	void                      cadastrar(ResumoDiarioCFe res) throws SQLException;
	void                   atualizar(ResumoDiarioCFe res) throws SQLException;
	ResumoDiarioCFe        getResumoDiario(Integer res) throws SQLException;
	List<ResumoDiarioCFe>  getResunosDiarios() throws SQLException;
}
