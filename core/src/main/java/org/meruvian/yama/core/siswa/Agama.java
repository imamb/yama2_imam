package org.meruvian.yama.core.siswa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.meruvian.yama.core.DefaultPersistence;

@Entity
@Table(name = "yama_agama")
public class Agama extends DefaultPersistence {
	private String agama;
	
	@NotNull
	@Size(min = 3)
	@Column(name = "agama", unique = true, nullable = false)
	public String getAgama() {
		return agama;
	}

	public void setAgama(String agama) {
		this.agama = agama;
	}
}
