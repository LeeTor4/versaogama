package versaogama.dao.estabelecimentodao.produto;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.produto.GrupoProduto;


public interface GrupoProdutoDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	void                      salvar(GrupoProduto grpProd) throws SQLException;
	Long                      cadastrar(GrupoProduto grpProd) throws SQLException;
	void                      atualizar(GrupoProduto grpProd) throws SQLException;
	GrupoProduto        getGrupoProd(Integer codigo) throws SQLException;
	List<GrupoProduto>  getGruposProdutos() throws SQLException;
}
