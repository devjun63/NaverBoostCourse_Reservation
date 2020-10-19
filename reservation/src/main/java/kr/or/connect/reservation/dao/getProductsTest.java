package kr.or.connect.reservation.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.ReservationPrice;
import kr.or.connect.reservation.dto.ReservationResponse;
import kr.or.connect.reservation.service.DetailService;
import kr.or.connect.reservation.service.MainPageService;
import kr.or.connect.reservation.service.ReservationService;


public class getProductsTest {
	private static ApplicationContext ac;
	private static Promotion promotion_api;

	public static void main(String[] args) {
		ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		MainPageDao reservationMainpageDao = ac.getBean(MainPageDao.class);
		MainPageService serviceTest = ac.getBean(MainPageService.class);
		DetailService detailTest = ac.getBean(DetailService.class);
		ReservationService reservationService = ac.getBean(ReservationService.class);
		ReservationDao reservationDao = ac.getBean(ReservationDao.class); 

		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyy.MM.dd.(EEE)", Locale.KOREAN);
		System.out.println("현재 날짜 : " + formatTime.format(cal.getTime()));


		cal.add(Calendar.DATE, 4);
		System.out.println("더한 날짜 : " + formatTime.format(cal.getTime()));
		ReservationResponse reservationResponse = new ReservationResponse();
		reservationResponse.setReservationTelephone("010-1234-1234");
		reservationResponse.setReservationName("아무개");
		reservationResponse.setReservationEmail("test@gmail.com");


		String reservationDate = "2020.10.20";
		SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyy.mm.dd");
		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
		java.util.Date tempDate = null;
		try {
			tempDate = beforeFormat.parse(reservationDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// java.util.Date를 yyyy-mm-dd 형식으로 변경하여 String로 반환한다.
		String transDate = afterFormat.format(tempDate);

		// 반환된 String 값을 Date로 변경한다.
		java.sql.Date reserveDate = java.sql.Date.valueOf(transDate); 
		reservationResponse.setReservationDate(reserveDate);




		reservationResponse.setModifyDate(new Date());
		reservationResponse.setCreateDate(new Date());
		reservationResponse.setDisplayInfoId(1);
		reservationResponse.setProductId(1);
		Integer id = reservationDao.insertReservationResponse(reservationResponse);


		ReservationPriceDao reservationPriceDao = ac.getBean(ReservationPriceDao.class);

		List<ReservationPrice> reservationPrices = new ArrayList<ReservationPrice>();
		ReservationPrice reservationPrice = new ReservationPrice();
		reservationPrice.setCount(3);
		reservationPrice.setProductPriceId(1);
		reservationPrice.setReservationInfoId(id.intValue());

		Integer priceId = reservationPriceDao.insertReservationPrice(reservationPrice);
		reservationPrice.setReservationInfoPriceId(priceId);

		reservationResponse.setPrices(reservationPrices);




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
		/*DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();
		displayInfoResponse = detailTest.getDisplayInfoResponse(1);
		System.out.println(displayInfoResponse.getAverageScore());
		System.out.println(displayInfoResponse.getComments().get(0).getReservationDate());
		System.out.println(displayInfoResponse.getDisplayInfo().getModifyDate());
		System.out.println(displayInfoResponse.getDisplayInfoImage().getCreateDate());
		System.out.println(displayInfoResponse.getProductImages().get(0).getModifyDate());
		System.out.println(displayInfoResponse.getProductPrices().get(0).getCreateDate());*/

		System.out.println(reservationService.getReservationInfo("kimjinsu@connect.co.kr"));

	}
}
