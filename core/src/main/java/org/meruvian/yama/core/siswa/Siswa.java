package org.meruvian.yama.core.siswa;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.meruvian.yama.core.DefaultPersistence;
import org.meruvian.yama.core.commons.Alamat;
import org.meruvian.yama.core.commons.Ortu;
import org.meruvian.yama.core.sekolah.Kelas;

@Entity
@Table(name = "yama_siswa")
public class Siswa extends DefaultPersistence{
	private String nis;
	private String nik;
	private String nama;
	private String kelamin;
	private Agama agama;
	private String warga;
	private Kelas kelas;
	private String tplahir;
	private String tglahir;
	private String telp;
	private String st_aktif;
	private Alamat alamat = new Alamat();
	private Ortu ortu=new Ortu();
	
	@NotNull
	@Size(min = 3)
	@Column(name = "nis", unique = true, nullable = false)
	public String getNis() {
		return nis;
	}

	public void setNis(String nis) {
		this.nis = nis;
	}
	
	@Column(name = "nik", nullable = false)
	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}
	
	@Column(name = "nama", nullable = false)
	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
	@Column(name = "kelamin", nullable = false)
	public String getKelamin() {
		return kelamin;
	}

	public void setKelamin(String kelamin) {
		this.kelamin = kelamin;
	}
	
	@Column(name = "warga")
	public String getWarga() {
		return warga;
	}

	public void setWarga(String warga) {
		this.warga = warga;
	}
	
	@ManyToOne
	@JoinColumn(name = "agama_id")
	public Agama getAgama() {
		return agama;
	}
	
	public void setAgama(Agama agama) {
		this.agama = agama;
	}
	
	@ManyToOne
	@JoinColumn(name = "kelas_id")
	public Kelas getKelas() {
		return kelas;
	}
	
	public void setKelas(Kelas kelas) {
		this.kelas = kelas;
	}
	
	@Column(name = "telp")
	public String getTelp() {
		return telp;
	}

	public void setTelp(String telp) {
		this.telp = telp;
	}
	
	@Column(name = "st_aktif")
	public String getStaktif() {
		return st_aktif;
	}

	public void setStaktif(String st_aktif) {
		this.st_aktif = st_aktif;
	}
	
	@Column(name = "tplahir")
	public String getTplahir() {
		return tplahir;
	}

	public void setTplahir(String tplahir) {
		this.tplahir = tplahir;
	}
	
	@Column(name = "tglahir")
	public String getTglahir() {
		return tglahir;
	}

	public void setTglahir(String tglahir) {
		this.tglahir = tglahir;
	}
	
	@Embedded
	public Alamat getAlamat(){
		return alamat;
	}
	
	public void setAlamat(Alamat alamat){
		this.alamat=alamat;
	}
	
	@Embedded
	public Ortu getOrtu(){
		return ortu;
	}
	
	public void setOrtu(Ortu ortu){
		this.ortu=ortu;
	}


}