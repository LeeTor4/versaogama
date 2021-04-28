package versaogama.model.system.produto;

import java.util.HashMap;
import java.util.Map;

public class SituacaoGrpProdutoSpedFiscal {

	private Long   id;
	private String valor;
	private String descricao;
	
	public SituacaoGrpProdutoSpedFiscal() {
		
	}
	public SituacaoGrpProdutoSpedFiscal(Long id,String valor, String descricao) {
		this.id    = id;
		this.valor = valor;
		this.descricao = descricao;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
  
	public Map<String,SituacaoGrpProdutoSpedFiscal> mpSitProdSpedFiscal(){
		
		Map<String,SituacaoGrpProdutoSpedFiscal> retorno = new HashMap<String, SituacaoGrpProdutoSpedFiscal>();
		
		SituacaoGrpProdutoSpedFiscal sit01 = new SituacaoGrpProdutoSpedFiscal(1L,"00", "Mercadoria para Revenda");
		SituacaoGrpProdutoSpedFiscal sit02 = new SituacaoGrpProdutoSpedFiscal(2L,"01", "Matéria-prima");
		SituacaoGrpProdutoSpedFiscal sit03 = new SituacaoGrpProdutoSpedFiscal(3L,"02", "Embalagem");
		SituacaoGrpProdutoSpedFiscal sit04 = new SituacaoGrpProdutoSpedFiscal(4L,"03", "Produto em Processo");
		SituacaoGrpProdutoSpedFiscal sit05 = new SituacaoGrpProdutoSpedFiscal(5L,"04", "Produto Acabado");
		SituacaoGrpProdutoSpedFiscal sit06 = new SituacaoGrpProdutoSpedFiscal(6L,"05", "Subproduto");
		SituacaoGrpProdutoSpedFiscal sit07 = new SituacaoGrpProdutoSpedFiscal(7L,"06", "Produto Intermediário");
		SituacaoGrpProdutoSpedFiscal sit08 = new SituacaoGrpProdutoSpedFiscal(8L,"07", "Material de Uso e Consumo");
		SituacaoGrpProdutoSpedFiscal sit09 = new SituacaoGrpProdutoSpedFiscal(9L,"08", "Ativo Imobilizado");
		SituacaoGrpProdutoSpedFiscal sit10 = new SituacaoGrpProdutoSpedFiscal(10L,"09", "Serviço");
		SituacaoGrpProdutoSpedFiscal sit11 = new SituacaoGrpProdutoSpedFiscal(11L,"10", "Outros insumos");
		SituacaoGrpProdutoSpedFiscal sit12 = new SituacaoGrpProdutoSpedFiscal(12L,"99", "Outros insumos");

		retorno.put(sit01.getValor(), sit01);
		retorno.put(sit02.getValor(), sit02);
		retorno.put(sit03.getValor(), sit03);
		retorno.put(sit04.getValor(), sit04);
		retorno.put(sit05.getValor(), sit05);
		retorno.put(sit06.getValor(), sit06);
		retorno.put(sit07.getValor(), sit07);
		retorno.put(sit08.getValor(), sit08);
		retorno.put(sit09.getValor(), sit09);
		retorno.put(sit10.getValor(), sit10);
		retorno.put(sit11.getValor(), sit11);
		retorno.put(sit12.getValor(), sit12);
		
		return retorno;
	}
	
	
}
