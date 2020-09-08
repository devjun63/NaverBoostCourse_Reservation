package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;

public interface MainPageService {
	public static final Integer LIMIT = 4;
	public int getProductsCountFromAllCategorys();
	public int getNumberOfProductsCountFromCategory(Integer categoryId);
	public List<Promotion> getPromotionList();
	public List<Category> getCategoryList();
	public List<Product> getAllProductList(Integer start);
	public List<Product> getProductList(Integer categoryId, Integer start);
}
