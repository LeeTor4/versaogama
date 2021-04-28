package versaogama.dao.enderecodao;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.estabelecimento.Endereco;

public interface EnderecoDaoInterface {

	void              excluir(Integer codigo) throws SQLException;
	void              cadastrar(Endereco end) throws SQLException;
	void               salvar(Endereco end) throws SQLException;
	void            atualizar(Endereco end) throws SQLException;
	Endereco        getEndereco(Integer codigo) throws SQLException;
	List<Endereco> getEnderecos() throws SQLException;
}
