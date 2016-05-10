package tot6.news;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "category")
public class Category {
	private long id;
	private String kategori;
	private Date createDate;
	private List<NewsCategory> newss=new ArrayList<NewsCategory>();
	/* private String title;
	private String content;
	private Date createDate;
*/
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

/*	@Column(nullable = false)
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

*/

	@Column(name = "kategori")
	public String getKategori() {
		return kategori;
	}
	
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "category", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	public List<NewsCategory> getNewss(){
		return newss;
	}
	
	public void setNewss(List<NewsCategory> newss){
		this.newss=newss;
	}
}
