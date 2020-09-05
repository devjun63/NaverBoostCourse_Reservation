package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.MainPageDao;
import kr.or.connect.reservation.dto.Category_api;
import kr.or.connect.reservation.dto.Product_api;
import kr.or.connect.reservation.dto.Promotion_api;
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
	public List<Promotion_api> getPromotionList() {
		List<Promotion_api> promotions = reservationMainPageDao.selectPromotionList();
		return promotions;
	}
	
	@Override
	public List<Category_api> getCategoryList() {
		List<Category_api> categories = reservationMainPageDao.selectCategoryList();
		return categories;
	}
	
	@Override
	public List<Product_api> getAllProductList(Integer start) {
		List<Product_api> product_api_datas = reservationMainPageDao.selectallProductApiDatas(start, MainPageService.LIMIT);
		return product_api_datas;
	}
	
	@Override
	public List<Product_api> getProductList(Integer categoryId, Integer start) {
		List<Product_api> product_api_datas = reservationMainPageDao.selectProductApiDatas(categoryId, start, MainPageService.LIMIT);
		return product_api_datas;
	}

	
	
}
