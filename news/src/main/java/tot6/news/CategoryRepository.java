package tot6.news;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
	Category findById(long id);
	
	@Query("SELECT n FROM Category n WHERE n.kategori LIKE %?1%")
	List<Category> findByKategori(String kategori);
}
