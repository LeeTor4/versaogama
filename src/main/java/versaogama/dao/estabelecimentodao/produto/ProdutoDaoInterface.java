package versaogama.dao.estabelecimentodao.produto;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.produto.Produto;



public interface ProdutoDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	void                      salvar(Produto prod) throws SQLException;
	void                      cadastrar(Produto prod) throws SQLException;
	void            atualizar(Produto prod) throws SQLException;
	Produto        getProduto(Integer codigo) throws SQLException;
	List<Produto>  getProdutos() throws SQLException;
}
