package kr.or.connect.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import kr.or.connect.reservation.dto.CategoryResponse;
import kr.or.connect.reservation.dto.DisplayInfoResponse;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductResponse;
import kr.or.connect.reservation.dto.PromotionResponse;
import kr.or.connect.reservation.service.DetailService;
import kr.or.connect.reservation.service.MainPageService;


@Controller
@RequestMapping(path="")
public class MainPageController {

	@Autowired
	MainPageService mainPageService;
	
	@Autowired
	DetailService detailService;

	@GetMapping("")
	public String mainPage(@RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
			@RequestParam(name="start", required=false, defaultValue="0") int start, ModelMap model){

		ProductResponse productResponse = mainPageService.getProducts(start);
		CategoryResponse categories = mainPageService.getCategories();
		PromotionResponse promotions = mainPageService.getPromotions();

		Map<String, Object> map = new HashMap<>();
		map.put("totalProductCount", productResponse.getTotalCount());
		map.put("product_list", productResponse.getItems());
		map.put("categories", categories.getItems());
		map.put("promotions", promotions.getItems());
		model.addAllAttributes(map);
		return "mainPage";
	}

	@GetMapping(path="/maincontents", produces = "application/json; charset=utf-8")
	@ResponseBody 
	public Map<String,Object> content(
			@RequestParam(name="categoryId", required=false, defaultValue="0") Integer categoryId,
			@RequestParam(name="start", required=false, defaultValue="0") int start) 
	{
		System.out.println("카테고리 아이디 : "+categoryId);
		System.out.println("시작 숫자 : "+start);

		ProductResponse productResponse = new ProductResponse();
		ProductResponse checkProductResponse = new ProductResponse();

		if(categoryId == 0)
		{
			productResponse = mainPageService.getProducts(start);
			checkProductResponse = mainPageService.getProducts(start + 4);
		}
		else {
			productResponse = mainPageService.getProducts(categoryId, start);
			checkProductResponse = mainPageService.getProducts(categoryId, start + 4 );

		}
		CategoryResponse categories = mainPageService.getCategories();

		Map<String, Object> map = new HashMap<>();
		map.put("categoryId", categoryId);
		map.put("totalProductCount", productResponse.getTotalCount());
		if(checkProductResponse.getItems().isEmpty()) {
			map.put("categories", categories.getItems());
			map.put("product_list", productResponse.getItems());
		}
		else {
			map.put("categories", categories.getItems());
			map.put("product_list", productResponse.getItems());
			map.put("morebtn", "morebtn");
		}
		
		return map;
	}
	
	@GetMapping(path = "/logout")
	public String logout(HttpSession session) {
		session.setMaxInactiveInterval(0);
		session.invalidate();
		System.out.println("logout");
		return "redirect:./";
		
	}
	
	@GetMapping(path = "/bookinglogin")
	public String bookinglogin(){
		
		return "bookinglogin";
	}
}
