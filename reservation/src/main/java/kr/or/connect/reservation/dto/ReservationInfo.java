package kr.or.connect.reservation.dto;

import java.util.Date;
import java.util.List;

public class ReservationInfo {
	// description: 예약정보 모델
	private boolean cancelYn;                   // 예약 취소 여부
	private Date createDate;                    // 예약 생성일시
	private List<DisplayInfo> displayInfo;		// 상품 전시 (Display) 모델
	private int displayInfoId;                  // 전시상품 Id
	private Date modifyDate;                    // 예약 수정일시
	private int productId;                      // 상품 Id
	private String reservationDate;             // 예약일
	private String reservationEmail;            // 예약자 이메일
	private int reservationInfoId;              // 예약 Id
	private String reservationName;             // 예약자명
	private String reservationTelephone;        // 예약자 전화번호
	private Long totalPrice;                    // 예약한 상품 총 가격
}
