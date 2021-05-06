package versaogama.model.system.movprodutos;

public class ModelSaldosMensaisAnuais {
	
	 private String cnpj;
	  private String descricao;
	  private String ano;
	  private String mes;
	  private String operacao;
	  private String tipo;
	  private String codItem;
	  private String codAntItem;
	  private String undMedida;
	  private Double fatConv;
	  private Double totQtde;
	  private Double vlTotal;
	  private Double saldo;

	  
	
	  public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getOperacao() {
		return operacao;
	  }
	
	  public void setOperacao(String operacao) {
		this.operacao = operacao;
	  }
	  
	  public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String getUndMedida() {
		return undMedida;
	}

	public void setUndMedida(String undMedida) {
		this.undMedida = undMedida;
	}

	public Double getFatConv() {
		return fatConv;
	}

	public void setFatConv(Double fatConv) {
		this.fatConv = fatConv;
	}

	public Double getTotQtde() {
		return totQtde;
	  }
	  
	  public void setTotQtde(Double totQtde) {
		this.totQtde = totQtde;
	  }
	  
	  
	  public Double getVlTotal() {
		return vlTotal;
	}

	public void setVlTotal(Double vlTotal) {
		this.vlTotal = vlTotal;
	}

	public Double getSaldo() {
		return saldo;
	  }
	
	  public void setSaldo(Double saldo) {
		this.saldo = saldo;
	  }

}
