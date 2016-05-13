package org.meruvian.yama.core.pos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.meruvian.yama.core.DefaultPersistence;

/**
 * @author IMAM BADRUDIN
 *
 */
@Entity
@Table(name = "detailbeli", uniqueConstraints = @UniqueConstraint(columnNames = { "pembelian_id", "produk_id" }))
public class DetailBeli extends DefaultPersistence{
	private Produk produk=new Produk();
	private Pembelian pembelian=new Pembelian();
	private long jumlah;
	private long harga;
	
	public DetailBeli(){}
	
	public DetailBeli(Produk produk, Pembelian pembelian){
		this.produk=produk;
		this.pembelian=pembelian;
	}
	
	@Column(name="jumlah")
	public long getJumlah() {
		return jumlah;
	}
	
	public void setJumlah(long jumlah) {
		this.jumlah = jumlah;
	}
	
	@Column(name="harga")
	public long getHarga() {
		return harga;
	}
	
	public void setHarga(long harga) {
		this.harga = harga;
	}
	
	@ManyToOne
	@JoinColumn(name = "produk_id", nullable = false)
	public Produk getProduk(){
		return produk;
	}
	
	public void setProduk(Produk produk){
		this.produk=produk;
	}
	
	@ManyToOne
	@JoinColumn(name = "pembelian_id", nullable = false)
	public Pembelian getPembelian(){
		return pembelian;
	}
	
	public void setPembelian(Pembelian pembelian){
		this.pembelian=pembelian;
	}
}
