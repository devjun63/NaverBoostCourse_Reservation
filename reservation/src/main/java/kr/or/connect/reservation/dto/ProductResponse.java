package kr.or.connect.reservation.dto;

import java.util.List;

public class ProductResponse {
	/* description: items field 에 전시상품전시상품 (Product) 들을 담는다.
	   totalCount 는 카테고리에 해당하는 전체 상품 수이다.
	 */
	private List<Product> items; 	// 상품 (Product) 모델
	private int totalCount;			// 카테고리에 해당하는 전체 상품 수
}
