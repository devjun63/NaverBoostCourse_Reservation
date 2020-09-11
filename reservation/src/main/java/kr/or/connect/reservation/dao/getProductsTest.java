package kr.or.connect.reservation.dao;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.DisplayInfoResponse;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.DetailService;
import kr.or.connect.reservation.service.MainPageService;
import kr.or.connect.reservation.service.impl.MainPageServiceImpl;


public class getProductsTest {
	private static ApplicationContext ac;
	private static Promotion promotion_api;

	public static void main(String[] args) {
		ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		MainPageDao reservationMainpageDao = ac.getBean(MainPageDao.class);
		MainPageService serviceTest = ac.getBean(MainPageService.class);
		DetailService detailTest = ac.getBean(DetailService.class);
		
		
		/*List<Promotion> promotionTestList = new ArrayList<Promotion>(); 
		List<Category> categoryTestList = new ArrayList<Category>();
		List<Product> product_apiTestList = new ArrayList<Product>();
		
		
		
		promotionTestList = reservationMainpageDao.selectPromotionList();
		categoryTestList = reservationMainpageDao.selectCategoryList();
		product_apiTestList = reservationMainpageDao.selectProducts(3, 5, 4);

		BigDecimal commentScoreSumValue = new BigDecimal(55);
		BigDecimal commentScoreCountValue = new BigDecimal(15);
		
		DetailDao detailDaoTest = ac.getBean(DetailDao.class);
		List<ProductImage> productImageTest = new ArrayList<ProductImage>();
		
		
		System.out.println("Comment AVGScore : " + commentScoreSumValue.divide(commentScoreCountValue, 16, RoundingMode.DOWN));
		for(int i = 0; i < product_apiTestList.size(); i++)
		{
						int d_id = product_apiTestList.get(i).getDisplayInfoId();
						String placeName = product_apiTestList.get(i).getPlaceName();
						String content =product_apiTestList.get(i).getProductContent();
						String desc = product_apiTestList.get(i).getProductDescription();
						int p_id =product_apiTestList.get(i).getProductId();
						String url = product_apiTestList.get(i).getProductImageUrl();
			System.out.println(product_apiTestList.get(i).toString());
		}

		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(detailDaoTest.getDisplayInfo(42).toString());
		productImageTest = detailDaoTest.getProductImages(42);
		for(int i = 0; i < productImageTest.size(); i++)
		{
			System.out.println("프로덕트 이미지 데이터 : "+productImageTest.get(i).toString());
		}
		
		System.out.println("디스플레이 인포 이미지 데이터 : "+detailDaoTest.getDisplayInfoImage(42).toString());*/
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();
		displayInfoResponse = detailTest.getDisplayInfoResponse(1);
		System.out.println(displayInfoResponse.getAverageScore());
		System.out.println(displayInfoResponse.getComments().get(0).getReservationDate());
		System.out.println(displayInfoResponse.getDisplayInfo().getModifyDate());
		System.out.println(displayInfoResponse.getDisplayInfoImage().getCreateDate());
		System.out.println(displayInfoResponse.getProductImages().get(0).getModifyDate());
		System.out.println(displayInfoResponse.getProductPrices().get(0).getCreateDate());
	}
}
