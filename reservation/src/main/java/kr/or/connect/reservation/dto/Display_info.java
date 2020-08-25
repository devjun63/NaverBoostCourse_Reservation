package kr.or.connect.reservation.dto;

import java.util.Date;

public class Display_info {
	private int id;					// 전시정보 id & PK
	private int product_id;			// product id & FK - References product (id)
	private String opening_hours;	// 전시 시간
	private String place_name;		// 장소 명 ex>세종문화회관
	private String place_lot;		// 지번 주소
	private String place_street;	// 도로명 주소
	private String tel;				// 문의 전화번호
	private String homepage;		// 홈페이지
	private String email;			// 문의 email
	private Date create_date;		// 생성 시간
	private Date modify_date;		// 수정 시간
	
	
}
