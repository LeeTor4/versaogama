package versaogama.util;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

public class UtilsEConverters {

	private static DateTimeFormatter formatter = 
			  DateTimeFormatter.ofPattern("ddMMyyyy");
	
	private static DateTimeFormatter formatter2 = 
			  DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public static LocalDate getStringParaData(String data) {
		TemporalAccessor parse = formatter.parse(data);
		LocalDate from = LocalDate.from(parse);
		//System.out.println(from);
		return from;
	}
	
	public static String getDataParaString(LocalDate data) {
		
		String dtFormatada = formatter.format(data);
		//System.out.println(dtFormatada);
		return dtFormatada;
	}
	
	public static Date getLocalDateParaDateSQL(LocalDate locald) {		
		Date date = null;
		if(locald != null) {
		    date = Date.valueOf(locald);	
		}
		return date;
	}

	public static LocalDate getSQLParaLocalDate(Date dateToConvert) {	
		 LocalDate localDate = null;
		if(dateToConvert != null) {
			Instant instant = Instant.ofEpochMilli(dateToConvert.getTime());
	        localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		}
		
        
        return localDate;
	}
	
	public static List<String> getPaths(Path dir) {     
		List<String> retorno = new ArrayList<>();
		/*LISTAR O CONTEÚDO*/
		try (DirectoryStream<Path> stream =  Files.newDirectoryStream(dir)){
			for(Path path : stream) {								
			   path.toAbsolutePath().toString();	
			  // System.out.println(path.toAbsolutePath().toString());
			   retorno.add(path.toAbsolutePath().toString());
			} 			
		}catch(Exception e) {
			e.printStackTrace();
		} 
		return retorno;
	}
	
    public static String convertDecimalFatConv(Double valor) {
    	
    	String formatado = String.format("%,.03f", valor);
    	return formatado;
    }
	
	public static String getDataParaString2(LocalDate data) {
		
		String dtFormatada = formatter2.format(data);
		//System.out.println(dtFormatada);
		return dtFormatada;
	}
	
	public static String preencheZerosAEsquerda(String str) {
		String zeros = "0";
		String qtdeZeros="";
		for(int i =1; i <= 8 ;i++) {			
			qtdeZeros += zeros;
		}
		String formatted = (qtdeZeros + str).substring(str.length());
		return formatted;
	}
	
	/*
	 * public static void main(String[] args) { LocalDate of = LocalDate.of(2019, 2,
	 * 13); //getDataParaString(of); //getStringParaData("05072019"); String cam =
	 * "C:\\Users\\flaragao\\eclipse-workspace\\versaobeta\\src\\main\\resources\\seqProdutos/";
	 * for(String s : listarConteudoDir(cam)) { System.out.println(s); } }
	 */
	
}
