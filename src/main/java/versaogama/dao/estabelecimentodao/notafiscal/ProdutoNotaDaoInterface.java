package versaogama.dao.estabelecimentodao.notafiscal;

import java.sql.SQLException;
import java.util.List;

import versaogama.model.system.notafiscal.ProdutoNota;

public interface ProdutoNotaDaoInterface {

	void                      excluir(Integer codigo) throws SQLException;
	void                      salvar(ProdutoNota prodNF) throws SQLException;
	void                      cadastrar(ProdutoNota prodNF) throws SQLException;
	void                      atualizar(ProdutoNota prodNF) throws SQLException;
	ProdutoNota        getProdNFe(Integer codigo) throws SQLException;
	List<ProdutoNota>  getProdsNFe() throws SQLException;
}
