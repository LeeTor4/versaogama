package versaogama;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import versaogama.conexao.Pool;
import versaogama.dao.movprodutosdao.ModelHistoricoItensDao;
import versaogama.managerxml.LeitorXML;
import versaogama.model.xml.CopiaArquivoXML_DE_PARA;
import versaogama.util.UtilsEConverters;

public class XmlSubstituir {

	public static void main(String[] args) throws Exception {
		
//		Path pXml = Paths.get("D:\\XML");
//		String caminho = pXml.getParent().toString().concat("\\").concat(pXml.getFileName().toString());
//        SAXParserFactory spf = SAXParserFactory.newInstance();
//		
//		SAXParser parser = spf.newSAXParser();
//		StringBuffer sb =  new StringBuffer();
//		try (DirectoryStream<Path> stream = Files.newDirectoryStream(pXml)){
//	    	for(Path path :  stream) {	
//	    		LeitorXML leitor = new LeitorXML(); 
//	    		caminho = path.getParent().toString().concat("\\").concat(path.getFileName().toString());
//	    		InputStreamReader ips = new InputStreamReader(new FileInputStream(caminho),"UTF-8");
//	   			InputSource is = new InputSource(ips);	
//	   			parser.parse(is, leitor);
//	   			
//	   			File xmlFile = new File(caminho);
//	   			BufferedReader br = new BufferedReader(new FileReader(xmlFile));
//	   			String line = null;
//	   			while((line = br.readLine())!= null)
//	   			{
//	   			    if(line.indexOf("&") != -1)
//	   			    {
//	   			        line = line.replaceAll("&","E");
//	   			    }         
//	   			    sb.append(line);                
//	   			}
//	   			br.close();
//
//	   			BufferedWriter bw = new BufferedWriter(new FileWriter(xmlFile));
//	   			bw.write(sb.toString());
//	   			bw.close();
//	   			
//	    	}
//	    }
		
//		Path pXmlDE = Paths.get("D:\\xml-202012-repo");
//		Path pXmlPARA = Paths.get("D:\\xml-servidor");
//		CopiaArquivoXML_DE_PARA copiarXML = new CopiaArquivoXML_DE_PARA();
//		
//		boolean copia = copiarXML.copia(new File(pXmlDE.toString()), new File(pXmlPARA.toString()));
//		
//		System.out.println(copia);
		
		
		//System.out.println(UtilsEConverters.preencheZerosAEsquerda("0000000012345"));
		
		Pool pool = new Pool();
		ModelHistoricoItensDao dao = new ModelHistoricoItensDao(pool);


	}

}
