package versaogama.dao.estabelecimentodao.participantes;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.estabelecimento.Participante;

public interface ParticipanteDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	void                      salvar(Participante part) throws SQLException;
	void                      cadastrar(Participante part) throws SQLException;
	void                   atualizar(Participante part) throws SQLException;
	Participante        getParticipante(Integer codigo) throws SQLException;
	List<Participante>  getParticipantes() throws SQLException;
}
