package versaogama.dao.estabelecimentodao.equipecf;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.equipecf.TotalizadorDiarioCuponsFiscais;

public interface TotalizadorDiarioCuponsFiscaisDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	Long                      salvar(TotalizadorDiarioCuponsFiscais totdircf) throws SQLException;
	Long                      cadastrar(TotalizadorDiarioCuponsFiscais totdircf) throws SQLException;
	void                      atualizar(TotalizadorDiarioCuponsFiscais totdircf) throws SQLException;
	TotalizadorDiarioCuponsFiscais        getNotaFiscal(Integer codigo) throws SQLException;
	List<TotalizadorDiarioCuponsFiscais>  getNotasFiscais() throws SQLException;
	
}
