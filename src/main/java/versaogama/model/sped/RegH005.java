package versaogama.model.sped;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegH005 {
	
	private Long      id;
	private Long      idPai;
	private String    reg;
	private LocalDate dataInv;
	private Double    vlTotEstoque;
	private String    motivoInventario;
	private List<RegH010> listaItensInv = new ArrayList<RegH010>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdPai() {
		return idPai;
	}
	public void setIdPai(Long idPai) {
		this.idPai = idPai;
	}
	public String getReg() {
		return reg;
	}
	public void setReg(String reg) {
		this.reg = reg;
	}
	public LocalDate getDataInv() {
		return dataInv;
	}
	public void setDataInv(LocalDate dataInv) {
		this.dataInv = dataInv;
	}
	public Double getVlTotEstoque() {
		return vlTotEstoque;
	}
	public void setVlTotEstoque(Double vlTotEstoque) {
		this.vlTotEstoque = vlTotEstoque;
	}
	public String getMotivoInventario() {
		return motivoInventario;
	}
	public void setMotivoInventario(String motivoInventario) {
		this.motivoInventario = motivoInventario;
	}
	
	public void adicionaItemInv(RegH010 regH010) {
		listaItensInv.add(regH010);
	}
	
    public List<RegH010> getListaItensInv() {
		return listaItensInv;
	}
}
