package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.MainPageDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.MainPageService;

@Service
public class MainPageServiceImpl implements MainPageService {

	@Autowired
	MainPageDao reservationMainPageDao;
	
	@Override
	@Transactional
	public int getProductsCountFromAllCategorys() {
		int allProductsCount = reservationMainPageDao.selectProductsTotalCountFromCategory();
		return allProductsCount;
	}

	@Override
	public int getNumberOfProductsCountFromCategory(Integer categoryId) {
		int productsCount = reservationMainPageDao.selectProductsTotalCountFromCategory(categoryId);
		return productsCount;
	}
	
	@Override
	public List<Promotion> getPromotionList() {
		List<Promotion> promotions = reservationMainPageDao.selectPromotionList();
		return promotions;
	}
	
	@Override
	public List<Category> getCategoryList() {
		List<Category> categories = reservationMainPageDao.selectCategoryList();
		return categories;
	}
	
	@Override
	public List<Product> getAllProductList(Integer start) {
		List<Product> product_api_datas = reservationMainPageDao.selectallProductApiDatas(start, MainPageService.LIMIT);
		return product_api_datas;
	}
	
	@Override
	public List<Product> getProductList(Integer categoryId, Integer start) {
		List<Product> product_api_datas = reservationMainPageDao.selectProductApiDatas(categoryId, start, MainPageService.LIMIT);
		return product_api_datas;
	}

	
	
}
