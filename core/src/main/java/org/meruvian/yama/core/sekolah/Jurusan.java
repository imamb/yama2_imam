package org.meruvian.yama.core.sekolah;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.meruvian.yama.core.DefaultPersistence;

@Entity
@Table(name = "yama_jurusan")
public class Jurusan extends DefaultPersistence{
	private String kode;
	private String keterangan;
	
	@NotNull
	@Column(name = "kode", unique = true, nullable = false)
	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}
	
	@Column(name = "keterangan")
	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
}
