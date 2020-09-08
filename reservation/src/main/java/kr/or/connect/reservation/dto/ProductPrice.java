package kr.or.connect.reservation.dto;

import java.util.Date;

public class ProductPrice {
	// description: 상품 가격
	private Date createDate;        // 생성일
	private double discountRate;    // 할인율
	private Date modifyDate;        // 수정일
	private int price;              // 가격
	private String priceTypeName;	// 가격 타입명
	private int productId;          // 상품 Id
	private int productPriceId;     // 상품 가격 Id
}
