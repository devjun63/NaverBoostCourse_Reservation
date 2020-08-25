package kr.or.connect.reservation.service;

import java.util.List;

public interface ReservationMainPageService {
	public static final Integer LIMIT = 4;
	public int getProductsCountFromAllCategorys();
	public int getNumberOfProductsCountFromCategory(int category_id);

}
