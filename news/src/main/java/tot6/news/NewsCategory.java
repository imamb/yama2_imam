package tot6.news;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



/**
 * @author Imam Badrudin
 *
 */
@Entity
@Table(name = "yama_news_category", uniqueConstraints = @UniqueConstraint(columnNames = { "news_id", "category_id" }))
public class NewsCategory {
	private long id;
	private News news= new News();
	private Category category=new Category();
	
	public NewsCategory() {}

	public NewsCategory(Category category, News news) {
		this.category = category;
		this.news = news;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "news_id", nullable = false)
	public News getNews(){
		return news;
	}
	
	public void setNews(News news){
		this.news=news;
	}
	
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	public Category getCategory(){
		return category;
	}
	
	public void setCategory(Category category){
		this.category=category;
	}
}
