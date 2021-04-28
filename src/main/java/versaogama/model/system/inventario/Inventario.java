package versaogama.model.system.inventario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Inventario {

	private Long      id;
	private Long      idPai;
	private LocalDate dataInv;
	private Double    vlTotal;
	private String    motivoInventario;
	private List<ItensInventario> itensInv = new ArrayList<ItensInventario>();
	 
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
	public LocalDate getDataInv() {
		return dataInv;
	}
	public void setDataInv(LocalDate dataInv) {
		this.dataInv = dataInv;
	}
	public Double getVlTotal() {
		return vlTotal;
	}
	public void setVlTotal(Double vlTotal) {
		this.vlTotal = vlTotal;
	}
	public String getMotivoInventario() {
		return motivoInventario;
	}
	public void setMotivoInventario(String motivoInventario) {
		this.motivoInventario = motivoInventario;
	}

	public void adicionaitensInv(ItensInventario itn) {
		itensInv.add(itn);
	}
	
	public List<ItensInventario> getItensInv() {
		return itensInv;
	}
}
