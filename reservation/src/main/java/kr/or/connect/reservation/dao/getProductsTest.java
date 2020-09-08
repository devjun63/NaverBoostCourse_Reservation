package kr.or.connect.reservation.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.MainPageService;
import kr.or.connect.reservation.service.impl.MainPageServiceImpl;


public class getProductsTest {
	private static ApplicationContext ac;
	private static Promotion promotion_api;

	public static void main(String[] args) {
		ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		MainPageDao reservationMainpageDao = ac.getBean(MainPageDao.class);
		MainPageService serviceTest = ac.getBean(MainPageService.class);
		
		List<Promotion> promotionTestList = new ArrayList<Promotion>(); 
		List<Category> categoryTestList = new ArrayList<Category>();
		List<Product> product_apiTestList = new ArrayList<Product>();
		
		promotionTestList = reservationMainpageDao.selectPromotionList();
		categoryTestList = reservationMainpageDao.selectCategoryList();
		product_apiTestList = serviceTest.getProductList(3, 5);
		
		for(int i = 0; i < product_apiTestList.size(); i++)
		{
//			int d_id = product_apiTestList.get(i).getDisplayInfoId();
//			String placeName = product_apiTestList.get(i).getPlaceName();
//			String content =product_apiTestList.get(i).getProductContent();
//			String desc = product_apiTestList.get(i).getProductDescription();
//			int p_id =product_apiTestList.get(i).getProductId();
//			String url = product_apiTestList.get(i).getProductImageUrl();
			System.out.println(product_apiTestList.get(i).toString());
		}
		
		/*for(int i = 0; i < categoryTestList.size(); i++)
		{
			int id = categoryTestList.get(i).getId();
			int count = categoryTestList.get(i).getCount();
			String name = categoryTestList.get(i).getName();
			System.out.println("id : "+id+" count : "+ count +" name : " + name);
		}*/
	}
}
