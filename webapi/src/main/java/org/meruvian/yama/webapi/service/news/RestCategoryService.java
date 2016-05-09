package org.meruvian.yama.webapi.service.news;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import tot6.news.Category;
import tot6.news.CategoryRepository;
//import org.meruvian.yama.tot6.news.News;
//import org.meruvian.yama.tot6.news.NewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RestCategoryService implements CategoryService{
	@Inject
	private CategoryRepository categoryRepository;
	
	@Override
	public Category getCategoryById(long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public List<Category> findCategoryByKategori(String kategori) {
		return categoryRepository.findByKategori(kategori);
	}

	@Override
	@Transactional
	public Category saveCategory(Category category) {
		category.setId(0);
		category.setCreateDate(new Date());
		
		return categoryRepository.save(category);
	}

	@Override
	@Transactional
	public Category updateCategory(long id, Category category) {
		Category ori = getCategoryById(category.getId());
		if (ori != null) {
			ori.setKategori(category.getKategori());
			//ori.setContent(news.getContent());
		}
		
		return ori;
	}

	@Override
	@Transactional
	public void deleteCategory(long id) {
		categoryRepository.delete(id);
	}

}
