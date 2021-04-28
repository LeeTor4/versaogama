package versaogama.dao.enderecodao;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.estabelecimento.Municipio;
public interface MunicipioDaoInterface {

	void              excluir(Integer codigo) throws SQLException;
	void              cadastrar(Municipio mun) throws SQLException;
	void               salvar(Municipio mun) throws SQLException;
	void            atualizar(Municipio mun) throws SQLException;
	Municipio        getMunicipio(Integer codigo) throws SQLException;
	List<Municipio> getMunicipios() throws SQLException;
}
