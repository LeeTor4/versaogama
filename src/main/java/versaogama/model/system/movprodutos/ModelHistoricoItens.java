package versaogama.model.system.movprodutos;

import java.time.LocalDate;

public class ModelHistoricoItens {

	private Long             id;
	private Long      idPaiLote;   
	private Long          idPai;
	private String      empresa;
	private String      cnpj;
	private String      operacao;
	private String      ecfCx;
	private LocalDate   dtDoc;
	private String      codItem;
	private String      codAntItem;
	private Double      qtde;
	private String      und;
	private Double      vlUnit;
	private Double      vlBruto;
	private Double      desconto;
	private Double      vlLiq;
	private String      cst;
	private String      cfop;
	private Double      aliqIcms;
	private String      codMod;
	private String      descricao;
	private String      numDoc;
	private String      chaveDoc;
	private String      nome;
	private String      cpfCnpj;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdPaiLote() {
		return idPaiLote;
	}
	public void setIdPaiLote(Long idPaiLote) {
		this.idPaiLote = idPaiLote;
	}
	public Long getIdPai() {
		return idPai;
	}
	public void setIdPai(Long idPai) {
		this.idPai = idPai;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getOperacao() {
		return operacao;
	}
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	public String getEcfCx() {
		return ecfCx;
	}
	public void setEcfCx(String ecfCx) {
		this.ecfCx = ecfCx;
	}
	public LocalDate getDtDoc() {
		return dtDoc;
	}
	public void setDtDoc(LocalDate dtDoc) {
		this.dtDoc = dtDoc;
	}
	public String getCodItem() {
		return codItem;
	}
	public void setCodItem(String codItem) {
		this.codItem = codItem;
	}
	public String getCodAntItem() {
		return codAntItem;
	}
	public void setCodAntItem(String codAntItem) {
		this.codAntItem = codAntItem;
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
	public Double getVlBruto() {
		return vlBruto;
	}
	public void setVlBruto(Double vlBruto) {
		this.vlBruto = vlBruto;
	}
	public Double getDesconto() {
		return desconto;
	}
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	public Double getVlLiq() {
		return vlLiq;
	}
	public void setVlLiq(Double vlLiq) {
		this.vlLiq = vlLiq;
	}
	public String getCst() {
		return cst;
	}
	public void setCst(String cst) {
		this.cst = cst;
	}
	public String getCfop() {
		return cfop;
	}
	public void setCfop(String cfop) {
		this.cfop = cfop;
	}
	public Double getAliqIcms() {
		return aliqIcms;
	}
	public void setAliqIcms(Double aliqIcms) {
		this.aliqIcms = aliqIcms;
	}
	public String getCodMod() {
		return codMod;
	}
	public void setCodMod(String codMod) {
		this.codMod = codMod;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNumDoc() {
		return numDoc;
	}
	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}
	public String getChaveDoc() {
		return chaveDoc;
	}
	public void setChaveDoc(String chaveDoc) {
		this.chaveDoc = chaveDoc;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
	
	
}
