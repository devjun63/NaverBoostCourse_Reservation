package kr.or.connect.reservation.dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.connect.reservation.config.ApplicationConfig;


public class getProductsTest {
	private static ApplicationContext ac;

	public static void main(String[] args) {
		ac = new AnnotationConfigApplicationContext(ApplicationConfig.class); 
		ReservationMainPageDao reservationMainpageDao = ac.getBean(ReservationMainPageDao.class);
		
		int category_id = 1;
		int result = reservationMainpageDao.selectCount(category_id);
		
		System.out.print("프로덕트의 갯수 : " + result);
		
		
	}
}
