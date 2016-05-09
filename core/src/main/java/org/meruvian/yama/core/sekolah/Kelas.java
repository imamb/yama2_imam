package org.meruvian.yama.core.sekolah;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.meruvian.yama.core.DefaultPersistence;

@Entity
@Table(name = "yama_kelas")
public class Kelas extends DefaultPersistence{
	private String kelas;
	private Jurusan jurusan;
	
	@NotNull
	@Column(name = "kelas", unique = true, nullable = false)
	public String getKelas() {
		return kelas;
	}

	public void setKelas(String kelas) {
		this.kelas = kelas;
	}
	
	@ManyToOne
	@JoinColumn(name = "jurusan_id")
	public Jurusan getJurusan() {
		return jurusan;
	}
	
	public void setJurusan(Jurusan jurusan) {
		this.jurusan = jurusan;
	}
}
