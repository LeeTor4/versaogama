package versaogama.model.xml;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import versaogama.managerxml.LeitorXML;

public class ProdutoCupomFiscalXml {

	private String  modDoc;
	private String  chaveCF;
	private String  numDoc;
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
	
	private List<ProdutoCupomFiscalXml> produtos = new ArrayList<>();
	
	public ProdutoCupomFiscalXml() {
		
	}
    
	public  ProdutoCupomFiscalXml(String modDoc, String  chaveCF,String  numDoc, String  numItem, String  codItem,String  descricao,
			String ncm,Double qtde, String und, Double vlUnit,Double vlProd,Double vlDesc, Double vlItem, String cfop, String cstIcms) {
		
		this.modDoc = modDoc;
		this.chaveCF = chaveCF;
		this.numDoc = numDoc;
		this.numItem = numItem;
		this.codItem = codItem;
		this.descricao = descricao;
		this.ncm = ncm;
		this.qtde = qtde;
		this.und = und;
		this.vlProd = vlProd;
		this.vlUnit = vlUnit;
		this.vlDesc = vlDesc;
		this.vlItem = vlItem;
		this.cfop = cfop;
		this.cstIcms = cstIcms;
	}
	
	public String getModDoc() {
		return modDoc;
	}
	public void setModDoc(String modDoc) {
		this.modDoc = modDoc;
	}
	public String getChaveCF() {
		return chaveCF;
	}
	public void setChaveCF(String chaveCF) {
		this.chaveCF = chaveCF;
	}
	
	public String getNumDoc() {
		return numDoc;
	}
	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
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

	public void setVlProd(Double vlProd) {
		this.vlProd = vlProd;
	}

	public Double getVlDesc() {
		return vlDesc;
	}
	public void setVlDesc(Double vlDesc) {
		this.vlDesc = vlDesc;
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


	public void setCstIcms(String cstIcms) {
		this.cstIcms = cstIcms;
	}
  
	private void getProdutosNota(ProdutoCupomFiscalXml prNF) {
		this.produtos.add(prNF);
		
	}
	
	public void getProdutosXML(Path pXml, XMLReader reader,LeitorXML logica) throws Exception {
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(pXml)){
			
			for(Path path :  stream) {				
				InputStream ips = new FileInputStream(path.getParent().toString().concat("\\").concat(path.getFileName().toString()));
				InputSource is = new InputSource(ips);
				reader.parse(is);				
				for(ProdutoCupomFiscalXml pr :  logica.getProdutosCF()) {
					getProdutosNota(pr);
									
				}				
			}
		}
	}
	
	public List<ProdutoCupomFiscalXml> getProdutos() {
		return produtos;
	}
}
