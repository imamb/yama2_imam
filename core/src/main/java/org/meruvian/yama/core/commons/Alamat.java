package org.meruvian.yama.core.commons;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Imam Badrudin
 *
 */

@Embeddable
public class Alamat {
	private String jln;
	private String kel;
	private String kec;
	private String kota;
	private String kodepos;
	private String prop;
	
	@Column(name = "jalan")
	public String getJln() {
		return jln;
	}

	public void setJln(String jln) {
		this.jln = jln;
	}
	
	@Column(name = "kelurahan")
	public String getKel() {
		return kel;
	}

	public void setKel(String kel) {
		this.kel = kel;
	}
	
	@Column(name = "kecamatan")
	public String getKec() {
		return kec;
	}

	public void setKec(String kec) {
		this.kec = kec;
	}
	
	@Column(name = "kota")
	public String getKota() {
		return kota;
	}

	public void setKota(String kota) {
		this.kota = kota;
	}
	
	@Column(name = "kodepos")
	public String getKodepos() {
		return kodepos;
	}

	public void setKodepos(String kodepos) {
		this.kodepos = kodepos;
	}
	
	@Column(name = "propinsi")
	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}
}
