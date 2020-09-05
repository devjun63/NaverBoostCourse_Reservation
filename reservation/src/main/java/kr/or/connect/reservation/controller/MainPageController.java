package kr.or.connect.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.connect.reservation.dto.Category_api;
import kr.or.connect.reservation.dto.Product_api;
import kr.or.connect.reservation.dto.Promotion_api;
import kr.or.connect.reservation.service.MainPageService;


@Controller
@RequestMapping(path="")
public class MainPageController {
	
	@Autowired
	MainPageService mainpageService;

	/*@GetMapping("")
	public String mainPage(@RequestParam(name="categoryId", required=false, defaultValue="0") Integer categoryId,
			ModelMap model) {
		System.out.println("카테고리 아이디 값 체크 : " + categoryId);
		int count = reservationMainPageService.getNumberOfProductsCountFromCategory(category_id);
		System.out.println(count);
		model.addAttribute("totalCount", count);
		return "mainPage";
	}*/
	
	@GetMapping("")
	public String mainPage(@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
			@RequestParam(name="start", required=false, defaultValue="0") int start, ModelMap model){
		int totalCount = 0;
		List<Product_api> product_list = null;
		if(categoryId == 0)
		{
			totalCount = mainpageService.getProductsCountFromAllCategorys();
			product_list = mainpageService.getAllProductList(start);	
		}
		else {
			totalCount = mainpageService.getNumberOfProductsCountFromCategory(categoryId);
			product_list = mainpageService.getProductList(categoryId, start);
		}
		
		List<Category_api> category_list = mainpageService.getCategoryList();
		List<Promotion_api> promotion_list = mainpageService.getPromotionList();
		
		Map<String, Object> map = new HashMap<>();
		map.put("totalProductCount", totalCount);
		map.put("product_list", product_list);
		map.put("category_list", category_list);
		map.put("promotion_list", promotion_list);
		model.addAllAttributes(map);
		return "mainTestPage";
	}
	
	@GetMapping(path="/maincontents", produces = "application/json; charset=utf-8")
	@ResponseBody 
	public Map<String,Object> content(
			@RequestParam(name="categoryId", required=false, defaultValue="0") Integer categoryId,
			@RequestParam(name="start", required=false, defaultValue="0") int start,
			ModelMap model) {
		System.out.println("카테고리 아이디 : "+categoryId);
		System.out.println("시작 숫자 : "+start);
		int totalCount = 0;
		List<Product_api> product_list = null;
		List<Product_api> check_product_list = null;
		if(categoryId == 0)
		{
			totalCount = mainpageService.getProductsCountFromAllCategorys();
			product_list = mainpageService.getAllProductList(start);
			check_product_list = mainpageService.getAllProductList(start + 4);
		}
		else {
			totalCount = mainpageService.getNumberOfProductsCountFromCategory(categoryId);
			product_list = mainpageService.getProductList(categoryId, start);
			check_product_list = mainpageService.getProductList(categoryId, start + 4);
		}
		List<Category_api> category_list = mainpageService.getCategoryList();
		
		Map<String, Object> map = new HashMap<>();
		map.put("categoryId", categoryId);
		map.put("totalProductCount", totalCount);
		if(check_product_list.isEmpty()) {
			System.out.println();
			map.put("category_list", category_list);
			map.put("product_list", product_list);
		}
		else {
			map.put("category_list", category_list);
			map.put("product_list", product_list);
			map.put("morebtn", "morebtn");
		}
		return map;
	} 
}
