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
import kr.or.connect.reservation.dto.Category_api;
import kr.or.connect.reservation.dto.Product_api;
import kr.or.connect.reservation.dto.Promotion_api;
import kr.or.connect.reservation.service.MainPageService;

@RestController
@RequestMapping(path="/api")
public class MainPageApiController {
	
	@Autowired
	MainPageService mainpageService;
	
	@ApiOperation(response=Product_api.class, value="상품 정보")
	@GetMapping(path = "/products", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> productList(@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
			@RequestParam(name="start", required=false, defaultValue="0") int start, ModelMap modelMap){
		int totalCount = 0;
		List<Product_api> product_list = null;
		if(categoryId == 0)
		{
			totalCount = mainpageService.getProductsCountFromAllCategorys();
			product_list = mainpageService.getProductList(categoryId + 1, start);	//categoryId => 1 ~ 5
		}
		else {
			totalCount = mainpageService.getNumberOfProductsCountFromCategory(categoryId);
			product_list = mainpageService.getProductList(categoryId, start);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("totalCount", totalCount);
		if(product_list.isEmpty()) {}
		else {
			map.put("items", product_list);
			map.put("morebtn", "morebtn");
		}
		return map;
	} 
	@ApiOperation(response=Category_api.class, value="카테고리 정보")
	@GetMapping("/categories")
	@ResponseBody
	public List<Category_api> categoryList(){
		List<Category_api> category_list = mainpageService.getCategoryList();
		return category_list;
	}
	
	@ApiOperation(response=Promotion_api.class, value="프로모션 정보")
	@GetMapping("/promotions")
	@ResponseBody
	public List<Promotion_api> promotionList() {
		List<Promotion_api> promotion_list = mainpageService.getPromotionList();
		return promotion_list;
	}
	
	
}
