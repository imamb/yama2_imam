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
@Table(name = "detailjual", uniqueConstraints = @UniqueConstraint(columnNames = { "penjualan_id", "produk_id" }))
public class DetailJual extends DefaultPersistence{
	private Produk produk=new Produk();
	private Penjualan penjualan=new Penjualan();
	private long jumlah;
	private long harga;
	
	public DetailJual(){}
	
	public DetailJual(Produk produk, Penjualan penjualan){
		this.produk=produk;
		this.penjualan=penjualan;
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
	@JoinColumn(name = "penjualan_id", nullable = false)
	public Penjualan getPenjualan(){
		return penjualan;
	}
	
	public void setPenjualan(Penjualan penjualan){
		this.penjualan=penjualan;
	}
	
}
