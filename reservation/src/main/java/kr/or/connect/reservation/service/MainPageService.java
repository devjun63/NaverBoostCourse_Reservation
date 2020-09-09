package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.CategoryResponse;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductResponse;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.PromotionResponse;

public interface MainPageService {
	public static final Integer LIMIT = 4;
	
	public CategoryResponse getCategories();
	public PromotionResponse getPromotions();
	public ProductResponse getProducts(Integer start);
	public ProductResponse getProducts(Integer categoryId, Integer start);
	
	/*
	public int getProductsCountFromAllCategorys();
	public int getNumberOfProductsCountFromCategory(Integer categoryId);
	
	public List<Product> getAllProductList(Integer start);
	public List<Product> getProductList(Integer categoryId, Integer start);
	*/
	
}
