package versaogama.model.system;

public class TotalizadoresPorItem {

	private Long            id;
	private Long        idLote;
	private String        cnpj;
	private int            ano;
	private int            mes;
	private String    operacao;
	private Long    frequencia;
	private String     codItem;
	private Double   vlTotQtde;
	private Double   vlTotItem;
	
	public TotalizadoresPorItem() {
		
	}
	
	
	public TotalizadoresPorItem(Long idLote,String cnpj,int ano, int mes ,Long frequencia, String codItem, Double vlTotQtde, Double vlTotItem) {
		super();
		this.idLote = idLote;
		this.cnpj = cnpj;
		this.ano  = ano;
		this.mes  = mes;
		this.frequencia = frequencia;
		this.codItem = codItem;
		this.vlTotQtde = vlTotQtde;
		this.vlTotItem = vlTotItem;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdLote() {
		return idLote;
	}
	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}
	
	
	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public int getAno() {
		return ano;
	}


	public void setAno(int ano) {
		this.ano = ano;
	}


	public int getMes() {
		return mes;
	}


	public void setMes(int mes) {
		this.mes = mes;
	}


	public String getOperacao() {
		return operacao;
	}


	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}


	public Long getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(Long frequencia) {
		this.frequencia = frequencia;
	}
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	public Double getVlTotQtde() {
		return vlTotQtde;
	}
	public void setVlTotQtde(Double vlTotQtde) {
		this.vlTotQtde = vlTotQtde;
	}
	public Double getVlTotItem() {
		return vlTotItem;
	}
	public void setVlTotItem(Double vlTotItem) {
		this.vlTotItem = vlTotItem;
	}
}
