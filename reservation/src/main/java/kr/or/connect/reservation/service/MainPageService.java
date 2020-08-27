package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Category_api;
import kr.or.connect.reservation.dto.Product_api;
import kr.or.connect.reservation.dto.Promotion_api;

public interface MainPageService {
	public static final Integer LIMIT = 4;
	public int getProductsCountFromAllCategorys();
	public int getNumberOfProductsCountFromCategory(Integer category_id);
	public List<Promotion_api> getPromotionList();
	public List<Category_api> getCategoryList();
	public List<Product_api> getProductList(Integer category_id, Integer start);
}
