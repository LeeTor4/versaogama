package versaogama.managerxml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import versaogama.model.xml.ProdutoCupomFiscalXml;
import versaogama.model.xml.ProdutoNotaXmlProprio;

public class LeitorXML  extends DefaultHandler{
	
	private String tagAtual;
	private String chaveDoc;
	private String modeloDoc;
	
	private String numDoc;
	private String nItem;
	private String cProd;
	private String descrProduto;
	private String ncm;
	private String cest;
	private String cfop;
	private String icmsCstA;
	private String icmsCstB;
	
	private String uCom;
    private String qCom;
    private String vUnCom;
    private String vProd;
    private String vDesc;
    private String vRatDesc;
    private String vItem;
	
    
    private int idNumItem = 0;
    private int idVlDesc = 0;
    private int idCST = 0;
    
    private Map<String,String> pegaVlProd = new HashMap<String,String>();
    private Map<String,String> pegaVlDesc = new HashMap<String,String>();
    private Map<String,String> pegaVlRatDesc = new HashMap<String,String>();
    private Map<String,String> pegaVlItem = new HashMap<String,String>();
    
    private List<ProdutoNotaXmlProprio> prodsNF = new ArrayList<ProdutoNotaXmlProprio>();
    private List<ProdutoNotaXmlProprio> prodsNFEnt = new ArrayList<ProdutoNotaXmlProprio>();
    private List<ProdutoNotaXmlProprio> prodsNFSai = new ArrayList<ProdutoNotaXmlProprio>();
    private List<ProdutoCupomFiscalXml> prodsCF = new ArrayList<ProdutoCupomFiscalXml>();
    
    private Set<String> listaDeProdutos =  new LinkedHashSet<>();
    
    private ProdutoNotaXmlProprio xmlNFe;
    private ProdutoCupomFiscalXml xmlCFE;
    
	@Override
	public void startDocument() throws SAXException {
		//System.out.println("\nIniciando o Parsing...\n");
		
	}
	
	@Override
	public void endDocument() throws SAXException {
		//System.out.println("\nFim do Parsing...");
		
	}
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// recupera o nome da tag atual
		   tagAtual = qName;
		   
		   if (qName.compareTo("infNFe") == 0) {
			   chaveDoc = attributes.getValue(0).substring(3);
			   idNumItem = 1;
		   }
		   
		   if (qName.compareTo("infCFe") == 0) {			   
			   chaveDoc = attributes.getValue(0).substring(3);
			   idNumItem = 2;
		   }
		   
		   if (tagAtual.compareTo("det") == 0) { 
			   nItem = attributes.getValue(0);
		   }

		   if (tagAtual.compareTo("ICMS") == 0) {
			   idCST=1;
		   }
		   
		   if (tagAtual.compareTo("PIS") == 0 || tagAtual.compareTo("COFINS") == 0) {
			   idCST=2;
			   
		   }
		   
		   if (tagAtual.compareTo("prod") == 0) {
			   idVlDesc = 1;
		   }
		   if (tagAtual.compareTo("total") == 0) {
			   idVlDesc = 2;
		   }

		  
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		tagAtual = "";
		
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String texto = new String(ch, start, length);
 	
		
		if (tagAtual.compareTo("mod") == 0) {
			
			if(texto.compareTo("55") == 0) {	
				modeloDoc = texto;
			}
			
			if(texto.compareTo("59") == 0 ) {
				modeloDoc = texto;
			}
		}
		
		if (tagAtual.compareTo("nNF") == 0) {
			numDoc = texto;
		}
		
        if (tagAtual.compareTo("nCFe") == 0) {
        	numDoc = texto;
		}
		
		if (tagAtual.compareTo("cProd") == 0) {
			if(modeloDoc.equals("55")) {
				cProd = texto;
				listaDeProdutos.add(cProd);
			}
			if(modeloDoc.equals("59")) {
				cProd = texto;
			}
		}
		
		
		if (tagAtual.compareTo("xProd") == 0) {

			if(modeloDoc.equals("55")) {
				descrProduto = texto;
			}
			
			if(modeloDoc.equals("59")) {
				descrProduto = texto;
			}
		}
		
		if (tagAtual.compareTo("NCM") == 0) {
			
			if(modeloDoc.equals("55")) {
				ncm = texto;
			}
			if(modeloDoc.equals("59")) {
				ncm = texto;
			}
		}
		
		
		if (tagAtual.compareTo("CEST") == 0) {
			
			if(modeloDoc.equals("55")) {
				cest = texto;
			}
			
			if(modeloDoc.equals("59")) {
				cest = texto;
			}
			
		}
		
        if (tagAtual.compareTo("CFOP") == 0) {
			
            if(modeloDoc.equals("55")) {
            	cfop = texto;
			}

        	if(modeloDoc.equals("59")) {
        		cfop = texto;
        		
        	}
		}
        
        if (tagAtual.compareTo("uCom") == 0) {
        	
        	 if(modeloDoc.equals("55")) {
        		 
        		 uCom = texto;
        	 }
        	 
        	 if(modeloDoc.equals("59")) {
        		 uCom = texto;
        	 }
        }
        
        if (tagAtual.compareTo("qCom") == 0) {
        	
       	 if(modeloDoc.equals("55")) {
       		 
       		qCom = texto;
       	 }
       	 
       	 if(modeloDoc.equals("59")) {
       		qCom = texto;
       	 }
       }
        
        if (tagAtual.compareTo("vUnCom") == 0) {
        	
          	 if(modeloDoc.equals("55")) {
          		 
          		vUnCom = texto;
          	 }
          	 
          	 if(modeloDoc.equals("59")) {
          		vUnCom = texto;
          	 }
          }
        
        
        if (tagAtual.compareTo("vProd") == 0) {
        	
         	 if(modeloDoc.equals("55")) {
         		 
         		vProd = texto;
         		pegaVlProd.put(cProd.concat(nItem), vProd);
         	 }
         	 
         	 if(modeloDoc.equals("59")) {
         		vProd = texto;
         		pegaVlProd.put(cProd.concat(nItem), vProd);
         	 }
         }
        
        if (tagAtual.compareTo("vDesc") == 0) {
        
        	if(modeloDoc.equals("55")) {
             	if(idVlDesc == 1) {
             	    vDesc = texto;	
             	    pegaVlDesc.put(cProd.concat(nItem), vDesc);
            	}
          	 }
          	 if(modeloDoc.equals("59")) {
	            if(idVlDesc == 1) {
	            	vDesc = texto; 
	            	pegaVlDesc.put(cProd.concat(nItem), vDesc);
	            	
            	}
          	 }
        }
        
        if (tagAtual.compareTo("vRatDesc") == 0) {
        	 vRatDesc = texto;	
        	 pegaVlRatDesc.put(cProd.concat(nItem), vRatDesc);
        }
        
        if (tagAtual.compareTo("vItem") == 0) {
        	 if(modeloDoc.equals("55")) {	 
        		 vItem = texto;
        		 pegaVlItem.put(cProd.concat(nItem), vItem);
          	 }
          	 if(modeloDoc.equals("59")) {
          		vItem = texto;
          		pegaVlItem.put(cProd.concat(nItem), vItem);
          	 }
        }
        
        
        if (tagAtual.compareTo("orig") == 0 || tagAtual.compareTo("Orig") == 0) {
        	
        	
        	 if(modeloDoc.equals("55")) {
         		 
        		 icmsCstA = texto;
          	 }
          	 
          	 if(modeloDoc.equals("59")) {
          		 icmsCstA = texto;
          	 }
        }
        
        if (tagAtual.compareTo("CST") == 0) {
        	 String vlProduto;
        	 String vlDescConv;
        	 String vlRatDesc;
        	 String vlItemConv;
        	 Double vlItem = 0.0;
        	 if(modeloDoc.equals("55")) { 
        		 if(idCST == 1) {
        			 icmsCstB = texto;
//       			  System.out.println( " NFe - Prod: " + chaveDoc + "|" + numDoc + "|" + nItem + "|" + cProd + "|" + descrProduto +
//         	   	    		"|" + ncm + "|" + ((cest)==null ? "" : cest)  + "|" + cfop + "|" + icmsCstA.concat(icmsCstB)+ "|" + uCom +"|" +vUnCom +"|"+ qCom + "|" + vUnCom +"|"+ vProd+
//         	   	    		"|" + (pegaVlDesc.get(nItem)==null ? 0.0 : (pegaVlDesc.get(nItem))) + "|" +  (pegaVlItem.get(nItem)==null ? 0.0 : (pegaVlItem.get(nItem)))); 
        		  
        	    
        		vlProduto  = (pegaVlProd.get(cProd.concat(nItem)) ==null ? "0.0" : pegaVlProd.get(cProd.concat(nItem)));
       			vlDescConv = (pegaVlDesc.get(cProd.concat(nItem)) ==null ? "0.0" : pegaVlDesc.get(cProd.concat(nItem)));
       			vlRatDesc  = (pegaVlRatDesc.get(cProd.concat(nItem)) == null ? "0.0" : pegaVlRatDesc.get(cProd.concat(nItem)));
       			vlItemConv = (pegaVlItem.get(cProd.concat(nItem))==null ? "0.0" : (pegaVlItem.get(cProd.concat(nItem))));
       			
	       		 if(vlDescConv.equals("0.0")) {
	    			 vlItemConv = vlProduto;
	    		 }
	       		vlItem = Double.parseDouble(vlProduto)  - Double.parseDouble(vlDescConv)+Double.parseDouble(vlRatDesc);
       			xmlNFe = new ProdutoNotaXmlProprio(chaveDoc, modeloDoc,nItem, cProd, descrProduto, ncm,
								       					Double.parseDouble(qCom), 
								       					uCom,
								       					Double.parseDouble(vUnCom), 
								       					Double.parseDouble(vlProduto), 
								       					Double.parseDouble(vlDescConv)+Double.parseDouble(vlRatDesc), 
								       					vlItem,
								       					cfop,
								       					icmsCstA.concat(icmsCstB));
       			
       			  
       			
       			if(cfop.startsWith("1") || cfop.startsWith("2")) {
       				prodsNFEnt.add(xmlNFe);
       			}
       			
       			if(cfop.startsWith("5") || cfop.startsWith("6")) {
       				prodsNFSai.add(xmlNFe);
       			}
       			prodsNF.add(xmlNFe);
       			  
        	 }
               
          }
        	 
        	 if(modeloDoc.equals("59")) {
        		 if(idCST == 1) {
        			 icmsCstB = texto;
//        			 System.out.println( " CFe - Prod: " + chaveDoc + "|" + numDoc + "|" + nItem + "|" + cProd + "|" + descrProduto +
//          	   	    		"|" + ncm + "|" + ((cest)==null ? "" : cest) + "|" + cfop + "|" + icmsCstA.concat(icmsCstB)+ "|" + uCom +"|" + qCom + "|" + vUnCom +"|"+  (pegaVlProd.get(nItem) ==null ? "0.0" : pegaVlProd.get(nItem))+
//          	   	    	"|" + (pegaVlDesc.get(nItem)==null ? 0.0 : (pegaVlDesc.get(nItem))) + "|" +  (pegaVlItem.get(nItem)==null ? 0.0 : (pegaVlItem.get(nItem)))); 
        		 
        			 vlProduto = (pegaVlProd.get(cProd.concat(nItem)) ==null ? "0.0" : pegaVlProd.get(cProd.concat(nItem)));
        			 vlDescConv = (pegaVlDesc.get(cProd.concat(nItem)) ==null ? "0.0" : pegaVlDesc.get(cProd.concat(nItem)));
        			 vlRatDesc  = (pegaVlRatDesc.get(cProd.concat(nItem)) == null ? "0.0" : pegaVlRatDesc.get(cProd.concat(nItem)));
            		 vlItemConv = (pegaVlItem.get(cProd.concat(nItem)) ==null ? "0.0" : (pegaVlItem.get(cProd.concat(nItem))));
            		 
            		 if(vlDescConv.equals("0.0")) {
    	    			 vlItemConv = vlProduto;
    	    		 }
        			 xmlCFE = new ProdutoCupomFiscalXml(
			        					 modeloDoc, 
			        					 chaveDoc,
			        					 numDoc,
			        					 nItem,
			        					 cProd,
			        					 descrProduto,
			        					 ncm,
			        					 Double.parseDouble(qCom),
			        					 uCom,
			        					 Double.parseDouble(vUnCom),
			        					 Double.parseDouble(vlProduto),
			        					 Double.parseDouble(vlDescConv)+Double.parseDouble(vlRatDesc),
			        					 Double.parseDouble(vlItemConv),
			        					 cfop,
			        					 icmsCstA.concat(icmsCstB));
        			 
        			 prodsCF.add(xmlCFE);
        			 
        		 }
        	 }
        }
	}

	
	
	public List<ProdutoNotaXmlProprio> getProdutosNF() {
		return prodsNF;
	}

	public List<ProdutoCupomFiscalXml> getProdutosCF() {
		return prodsCF;
	}

	
	public List<ProdutoNotaXmlProprio> getProdsNFEnt() {
		return prodsNFEnt;
	}

	public List<ProdutoNotaXmlProprio> getProdsNFSai() {
		return prodsNFSai;
	}

	public Set<String> getListaDeProdutos() {
		return listaDeProdutos;
	}
}
