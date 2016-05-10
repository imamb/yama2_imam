package tot6.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsCategoryRepository extends CrudRepository<NewsCategory,Long> {
	NewsCategory findById(Long id);
	Page<NewsCategory> findByNewsId(Long id, Pageable pageable);
	Page<NewsCategory> findByNewsTitle(String title, Pageable pageable);
	Page<NewsCategory> findByCategoryId(Long id, Pageable pageable);
	Page<NewsCategory> findByCategoryKategori(String Kategori, Pageable pageable);
	NewsCategory findByNewsIdAndCategoryId(Long newsId, Long categoryId);
}
