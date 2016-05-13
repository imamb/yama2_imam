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
@Table(name = "penjualan")
public class Penjualan extends DefaultPersistence{
	private String nomor;
	private String pembeli;
	private String kasir;
	private List<DetailJual> produks=new ArrayList<DetailJual>();
	//private String total;
	
	@NotNull
	@Column(name = "nomor", unique = true, nullable = false)
	public String getNomor() {
		return nomor;
	}

	public void setNomor(String nomor) {
		this.nomor = nomor;
	}
	
	@Column(name="pembeli")
	public String getPembeli() {
		return pembeli;
	}
	
	public void setPembeli(String pembeli) {
		this.pembeli = pembeli;
	}
	
	@Column(name="kasir")
	public String getKasir() {
		return kasir;
	}
	
	public void setKasir(String kasir) {
		this.kasir = kasir;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "penjualan", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	public List<DetailJual> getProduks(){
		return produks;
	}
	
	public void setProduks(List<DetailJual> produks){
		this.produks=produks;
	}

}
