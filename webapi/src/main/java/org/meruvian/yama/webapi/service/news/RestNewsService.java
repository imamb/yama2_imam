package org.meruvian.yama.webapi.service.news;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import tot6.news.Category;
import tot6.news.News;
import tot6.news.NewsCategory;
import tot6.news.NewsCategoryRepository;
import tot6.news.NewsRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
//import org.meruvian.yama.tot6.news.News;
//import org.meruvian.yama.tot6.news.NewsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class RestNewsService implements NewsService {
	@Inject
	private NewsRepository newsRepository;
	
	@Inject
	private NewsCategoryRepository newscategoryRepository;
	
	@Override
	public News getNewsById(long id) {
		return newsRepository.findById(id);
	}

	@Override
	public News getNewsByTitle(String title) {
		return newsRepository.findByTitle(title);
	}
	
	@Override
	public List<News> findNewsByTitle(String title) {
		return newsRepository.findByTitle(title,title,title);
	}

	@Override
	@Transactional
	public News saveNews(News news) {
		news.setId(0);
		news.setCreateDate(new Date());
		
		return newsRepository.save(news);
	}

	@Override
	@Transactional
	public News updateNews(long id, News news) {
		News ori = getNewsById(news.getId());
		if (ori != null) {
			ori.setTitle(news.getTitle());
			ori.setContent(news.getContent());
		}
		
		return ori;
	}

	@Override
	@Transactional
	public void deleteNews(long id) {
		newsRepository.delete(id);
	}

	@Override
	@Transactional
	public boolean addCategoryToNews(long id, long categoryId) {
		News n=getNewsById(id);
		for(NewsCategory nc : n.getCategorys()){
			if(nc.getCategory().getId() == categoryId){
				return false;
			}
		}
		
		NewsCategory newsCategory=new NewsCategory();
		newsCategory.setNews(n);
		Category category=new Category();
		category.setId(categoryId);
		newsCategory.setCategory(category);
		
		newscategoryRepository.save(newsCategory);
		return true;
	}
	
	@Override
	@Transactional
	public boolean removeCategoryFromNews(long id, long categoryId) {
		News n = getNewsById(id);
		NewsCategory nc=newscategoryRepository.findByNewsIdAndCategoryId(n.getId(), categoryId);
		newscategoryRepository.delete(nc.getId());
		return true;
	}
	
	@Override
	@Transactional
	public boolean removeAllCategoryFromNews(long id) {
		News n=getNewsById(id);
		newscategoryRepository.delete(n.getCategorys());
		return true;	
	}
	
	@Override
	public Page<Category> findCategoryByNews(long id, Pageable pageable) {
		News n=getNewsById(id);
		Page<NewsCategory> newsCategorys=newscategoryRepository.findByNewsId(n.getId(), pageable);
		
		List<Category> categorys = new ArrayList<Category>();
	
		for(NewsCategory nc : newsCategorys){
			categorys.add(nc.getCategory());
		}						
		return new PageImpl<Category>(categorys,pageable,newsCategorys.getTotalElements());
				
		/*
		 *Page<UserRole> userRoles = userRoleRepository.findByUserId(u.getId(), pageable);
		
		List<Role> roles = new ArrayList<Role>();
		for (UserRole ur : userRoles) {
			roles.add(ur.getRole());
		}
		
		return new PageImpl<Role>(roles, pageable, userRoles.getTotalElements()); 
		 *
		 */
	}
}