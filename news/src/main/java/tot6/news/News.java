package tot6.news;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "news")
public class News {
	private long id;
	private String title;
	private String content;
	private Category kategori;
	private Date createDate;
	private String pembuat;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@Lob
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name = "pembuat")
	public String getPembuat() {
		return pembuat;
	}
	
	public void setPembuat(String pembuat) {
		this.pembuat = pembuat;
	}
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	public Category getKategori() {
		return kategori;
	}

	public void setKategori(Category kategori) {
		this.kategori = kategori;
	}
}