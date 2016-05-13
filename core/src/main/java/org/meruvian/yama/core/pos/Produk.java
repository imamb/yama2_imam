package org.meruvian.yama.core.pos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
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
@Table(name = "produk")
public class Produk extends DefaultPersistence{
	private String kode;
	private String nama;
	private String keterangan;
	private long stok;
	private long harga;
	private List<DetailJual> penjualans=new ArrayList<DetailJual>();
	private List<DetailBeli> pembelians=new ArrayList<DetailBeli>();
	
	@NotNull
	@Column(name = "kode", unique = true, nullable = false)
	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}
	
	@Column(nullable = false)
	public String getNama() {
		return nama;
	}
	
	public void setNama(String nama) {
		this.nama = nama;
	}

	@Lob
	public String getKeterangan() {
		return keterangan;
	}
	
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	@Column(name = "stok")
	public long getStok() {
		return stok;
	}
	
	public void setStok(long stok) {
		this.stok = stok;
	}
	
	@Column(name = "harga")
	public long getHarga() {
		return harga;
	}
	
	public void setHarga(long harga) {
		this.harga = harga;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "produk", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	public List<DetailJual> getPenjualans(){
		return penjualans;
	}
	
	public void setPenjualans(List<DetailJual> penjualans){
		this.penjualans=penjualans;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "produk",cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	public List<DetailBeli> getPembelians(){
		return pembelians;
	}
	
	public void setPembelians(List<DetailBeli> pembelians){
		this.pembelians=pembelians;
	}
	
}
