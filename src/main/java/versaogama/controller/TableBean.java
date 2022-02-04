package versaogama.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import javax.faces.model.ListDataModel;


import versaogama.conexao.Pool;
import versaogama.dao.estabelecimentodao.importacaospedfical.LoteImportacaoSpedFiscalDao;
import versaogama.dao.estabelecimentodao.notafiscal.NotaFiscalDao;
import versaogama.dao.estabelecimentodao.produto.ProdutoDao;
import versaogama.managersped.LeitorTxtSpedFiscal;
import versaogama.model.sped.Reg0200;


public class TableBean {

	
	//private ListDataModel<Reg0200> produtos;
	private List<Reg0200> produtos;
	
	public TableBean() throws Exception {
		Pool pool = new Pool();
		Path p = Paths.get("D:\\EMPRESAS\\SELLENE\\MEGADIET\\SPED\\2021\\202101.txt");
		LeitorTxtSpedFiscal leitor = new LeitorTxtSpedFiscal();
		leitor.leitorSpedFiscal(p,pool);
		
		produtos = new ArrayList<Reg0200>();
		produtos.addAll(leitor.getRegs0200());
	}
	public List<Reg0200> getProdutos() {
		return produtos;
	}
}
