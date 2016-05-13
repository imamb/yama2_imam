package org.meruvian.yama.core.pos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.meruvian.yama.core.DefaultPersistence;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Imam Badrudin
 *
 */
@Entity
@Table(name = "pembelian")
public class Pembelian extends DefaultPersistence{
	private String nomor;
	private String surat;
	private String penjual;
	private String kasir;
	private List<DetailBeli> produks=new ArrayList<DetailBeli>();
	//private String total;
	
	@NotNull
	@Column(name = "nomor", unique = true, nullable = false)
	public String getNomor() {
		return nomor;
	}

	public void setNomor(String nomor) {
		this.nomor = nomor;
	}
	
	@Column(name="penjual")
	public String getPenjual() {
		return penjual;
	}
	
	public void setPenjual(String penjual) {
		this.penjual = penjual;
	}
	
	@Column(name="kasir")
	public String getKasir() {
		return kasir;
	}
	
	public void setKasir(String kasir) {
		this.kasir = kasir;
	}
	
	@Column(name="surat")
	public String getSurat() {
		return surat;
	}
	
	public void setSurat(String surat) {
		this.surat = surat;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "pembelian", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	public List<DetailBeli> getProduks(){
		return produks;
	}
	
	public void setProduks(List<DetailBeli> produks){
		this.produks=produks;
	}
}
