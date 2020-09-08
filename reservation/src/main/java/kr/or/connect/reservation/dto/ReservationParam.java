package kr.or.connect.reservation.dto;

import java.util.List;

public class ReservationParam {
	// description: 예약하기 Request Body 모델
	private int displayInfoId;					// 전시상품 Id
	private List<ReservationPrice> prices;		// 예약 가격 정보
	private int productId;                      // 상품 Id
	private String reservationEmail;            // 예약자 이메일
	private String reservationName;             // 예약자명
	private String reservationTelephone;        // 예약자 전화번호
	private String reservationYearMonthDay;		// 예약일
}
