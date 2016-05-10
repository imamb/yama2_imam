package tot6.news;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {
	News findById(long id);
	News findByTitle(String title);
	
	@Query("SELECT n FROM News n WHERE n.title LIKE %?1% OR n.content LIKE %?2% OR n.pembuat LIKE %?3%")
	List<News> findByTitle(String title, String content, String pembuat);
}