package kr.or.connect.reservation.dto;

public class Product_image {
	private int id;				// 상품 이미지 id & PK 
	private int product_id;		// 상품 id & FK - References product (id)
	private String type;		// 이미지 type, th: 썸네일, ma: 메인, et: 기타 (etc)
	private int file_id;		// file id & FK - References file_info (id)
}
