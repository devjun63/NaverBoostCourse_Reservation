package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.MainPageDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.CategoryResponse;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductResponse;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.PromotionResponse;
import kr.or.connect.reservation.service.MainPageService;

@Service
public class MainPageServiceImpl implements MainPageService {

	@Autowired
	MainPageDao mainPageDao;
	
	@Override
	@Transactional
	public CategoryResponse getCategories() {
		
		CategoryResponse categories = new CategoryResponse();
		List<Category> items = mainPageDao.selectCategoryList();
		categories.setItems(items);
		return categories;
	}

	@Override
	@Transactional
	public PromotionResponse getPromotions() {
		
		PromotionResponse promotions = new PromotionResponse();
		List<Promotion> items = mainPageDao.selectPromotionList();
		promotions.setItems(items);
		return promotions;
	}
	
	@Override
	@Transactional
	public ProductResponse getProducts(Integer start) {
		ProductResponse products = new ProductResponse();
		List<Product> items = mainPageDao.selectAllProducts(start, MainPageService.LIMIT);
		int totalCount = mainPageDao.selectProductsTotalCount();
		products.setItems(items);
		products.setTotalCount(totalCount);
		return products;
	}
	

	@Override
	@Transactional
	public ProductResponse getProducts(Integer categoryId, Integer start) {
		
		ProductResponse products = new ProductResponse();
		List<Product> items = mainPageDao.selectProducts(categoryId, start, MainPageService.LIMIT);
		int totalCount = mainPageDao.selectProductsTotalCount(categoryId);
		products.setItems(items);
		products.setTotalCount(totalCount);
		return products;
	}

	
}
