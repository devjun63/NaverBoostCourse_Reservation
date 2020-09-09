package kr.or.connect.reservation.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.CategoryResponse;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductResponse;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.PromotionResponse;
import kr.or.connect.reservation.service.DetailService;
import kr.or.connect.reservation.service.MainPageService;

@RestController
@RequestMapping(path="/api")
public class ReservationApiController {
	
	@Autowired
	MainPageService mainpageService;
	
	@Autowired
	DetailService detailService;
	
	@ApiOperation(response=Product.class, value="상품 정보")
	@GetMapping(path = "/products", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> productList(@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
			@RequestParam(name="start", required=false, defaultValue="0") int start, ModelMap modelMap){
		
		ProductResponse products = new ProductResponse();
		
		if(categoryId == 0){
			products = mainpageService.getProducts(categoryId +1 , start);
		}
		else {
			products = mainpageService.getProducts(categoryId, start);
		}
		
		Map<String, Object> map = new HashMap<>();
		
		if(products.getItems().isEmpty()) {}
		else {
			map.put("totalCount", products.getTotalCount());
			map.put("items", products.getItems());
			map.put("morebtn", "morebtn");
		}
		return map;
	} 
	@ApiOperation(response=Category.class, value="카테고리 정보")
	@GetMapping("/categories")
	@ResponseBody
	public CategoryResponse categoryList(){
		CategoryResponse categoryResponse = mainpageService.getCategories();
		return categoryResponse;
	}
	
	@ApiOperation(response=Promotion.class, value="프로모션 정보")
	@GetMapping("/promotions")
	@ResponseBody
	public PromotionResponse promotionList() {
		PromotionResponse promotion_list = mainpageService.getPromotions();
		return promotion_list;
	}
	
	@ApiOperation(response=Product.class, value="상품 정보")
	@GetMapping(path = "/products/{displayInfoId}", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> detail(@RequestParam(name="displayInfoId", required=true) ModelMap modelMap){
		
		Map<String, Object> map = new HashMap<>();
		
		
		
		return map;
	} 
	
	
}
