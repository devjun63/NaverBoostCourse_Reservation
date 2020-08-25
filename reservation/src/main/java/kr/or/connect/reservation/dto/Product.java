package kr.or.connect.reservation.dto;

import java.util.Date;

public class Product {
	private int id;					// 상품 id - PK
	private int category_id;        // 카테고리 id - FK - References category (id)
	private String description;     // 상품 간단 설명
	private String content;         // 상품 상세 설명
	private String event;           // 이벤트 정보
	private Date create_date;       // 생성 시간
	private Date modify_date;       // 수정 시간
}
