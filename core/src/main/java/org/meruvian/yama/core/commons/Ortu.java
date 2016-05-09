package org.meruvian.yama.core.commons;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Imam Badrudin
 *
 */

@Embeddable
public class Ortu {
	private String nama_a;
	private String kerja_a;
	private String agama_a;
	private String telp_a;
	
	private String nama_i;
	private String kerja_i;
	private String agama_i;
	private String telp_i;
	
	private String nama_w;
	private String kerja_w;
	private String agama_w;
	private String alamat_w;
	private String telp_w;
	private String st_w;
	
	@Column(name = "nama_a")
	public String getNama_a() {
		return nama_a;
	}

	public void setNama_a(String nama_a) {
		this.nama_a = nama_a;
	}
	
	@Column(name = "kerja_a")
	public String getKerja_a() {
		return kerja_a;
	}

	public void setKerja_a(String kerja_a) {
		this.kerja_a = kerja_a;
	}
	
	@Column(name = "agama_a")
	public String getAgama_a() {
		return agama_a;
	}

	public void setAgama_a(String agama_a) {
		this.agama_a = agama_a;
	}
	
	@Column(name = "telp_a")
	public String getTelp_a() {
		return telp_a;
	}

	public void setTelp_a(String telp_a) {
		this.telp_a = telp_a;
	}
	
	/**** Data Ibu ****/
	@Column(name = "nama_i")
	public String getNama_i() {
		return nama_i;
	}

	public void setNama_i(String nama_i) {
		this.nama_i = nama_i;
	}
	
	@Column(name = "kerja_i")
	public String getKerja_i() {
		return kerja_i;
	}

	public void setKerja_i(String kerja_i) {
		this.kerja_i = kerja_i;
	}
	
	@Column(name = "agama_i")
	public String getAgama_i() {
		return agama_i;
	}

	public void setAgama_i(String agama_i) {
		this.agama_i = agama_i;
	}
	
	@Column(name = "telp_i")
	public String getTelp_i() {
		return telp_i;
	}

	public void setTelp_i(String telp_i) {
		this.telp_i = telp_i;
	}
	
	/**** Data Wali ****/
	@Column(name = "nama_w")
	public String getNama_w() {
		return nama_w;
	}

	public void setNama_w(String nama_w) {
		this.nama_w = nama_w;
	}
	
	@Column(name = "kerja_w")
	public String getKerja_w() {
		return kerja_w;
	}

	public void setKerja_w(String kerja_w) {
		this.kerja_w = kerja_w;
	}
	
	@Column(name = "agama_w")
	public String getAgama_w() {
		return agama_w;
	}

	public void setAgama_w(String agama_w) {
		this.agama_w = agama_w;
	}
	
	@Column(name = "telp_w")
	public String getTelp_w() {
		return telp_w;
	}

	public void setTelp_w(String telp_w) {
		this.telp_w = telp_w;
	}
	@Column(name = "alamat_w")
	public String getAlamat_w() {
		return alamat_w;
	}

	public void setAlamat_w(String alamat_w) {
		this.alamat_w = alamat_w;
	}
	
	@Column(name = "st_w")
	public String getSt_w() {
		return st_w;
	}

	public void setSt_w(String st_w) {
		this.st_w = st_w;
	}
}
