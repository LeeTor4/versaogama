package versaogama.model.xml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import versaogama.managerxml.LeitorXML;



public class ProdutoNotaXmlProprio {

	private String  modeloDoc;
	private String  chaveNota;
	private String  numItem;
	private String  codItem;
	private String  descricao;
	private String  ncm;
	private Double  qtde;
	private String  und;
	private Double  vlUnit;
	private Double  vlProd;
	private Double  vlDesc;
	private Double  vlItem;
	private String  cfop;
    private String  cstIcms;
	
	private List<ProdutoNotaXmlProprio> produtos = new ArrayList<>();
	private Map<String , ProdutoNotaXmlProprio> produtoPorCodigo = new HashMap<>();
	
	public ProdutoNotaXmlProprio() {
		
	}

	
	public ProdutoNotaXmlProprio(String chaveNota,String modeloDoc ,String numItem, String codItem, String descricao, String ncm,
			Double qtde, String und, Double vlUnit,Double vlProd,Double vlDesc ,Double vlItem, String cfop, String cstIcms) {
		this.modeloDoc = modeloDoc;
		this.chaveNota = chaveNota;
		this.numItem = numItem;
		this.codItem = codItem;
		this.descricao = descricao;
		this.ncm = ncm;
		this.qtde = qtde;
		this.und = und;
		this.vlUnit = vlUnit;
		this.vlProd = vlProd;
		this.vlDesc = vlDesc;
		this.vlItem = vlItem;
		this.cfop = cfop;
        this.cstIcms = cstIcms;
	}


    
	public String getModeloDoc() {
		return modeloDoc;
	}


	public String getChaveNota() {
		return chaveNota;
	}
	public void setChaveNota(String chaveNota) {
		this.chaveNota = chaveNota;
	}
	public String getNumItem() {
		return numItem;
	}
	public void setNumItem(String numItem) {
		this.numItem = numItem;
	}
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getNcm() {
		return ncm;
	}
	public void setNcm(String ncm) {
		this.ncm = ncm;
	}
	public Double getQtde() {
		return qtde;
	}
	public void setQtde(Double qtde) {
		this.qtde = qtde;
	}
	public String getUnd() {
		return und;
	}
	public void setUnd(String und) {
		this.und = und;
	}
	
	public Double getVlUnit() {
		return vlUnit;
	}
	public void setVlUnit(Double vlUnit) {
		this.vlUnit = vlUnit;
	}
	
	public Double getVlProd() {
		return vlProd;
	}


	public Double getVlDesc() {
		return vlDesc;
	}


	public Double getVlItem() {
		return vlItem;
	}
	public void setVlItem(Double vlItem) {
		this.vlItem = vlItem;
	}
	public String getCfop() {
		return cfop;
	}
	public void setCfop(String cfop) {
		this.cfop = cfop;
	}
	
	public String getCstIcms() {
		return cstIcms;
	}


	private void getProdutosNota(ProdutoNotaXmlProprio prNF) {
		this.produtos.add(prNF);
		this.produtoPorCodigo.put(prNF.getCodItem(),prNF);
	}
	
	
	public void getProdutosXML(Path pXml, XMLReader reader,LeitorXML logica) throws Exception {
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(pXml)){
			
			for(Path path :  stream) {				
				InputStream ips = new FileInputStream(path.getParent().toString().concat("\\").concat(path.getFileName().toString()));
				InputSource is = new InputSource(ips);
				reader.parse(is);				
				for(ProdutoNotaXmlProprio pr :  logica.getProdutosNF()) {
					getProdutosNota(pr);
									
				}				
			}
		}
	}
	
	
	public ProdutoNotaXmlProprio buscaProdudutoPorCodigo(String codigo) {
		return produtoPorCodigo.get(codigo);
	}
	public List<ProdutoNotaXmlProprio> getProdutos() {
		return produtos;
	}
}
