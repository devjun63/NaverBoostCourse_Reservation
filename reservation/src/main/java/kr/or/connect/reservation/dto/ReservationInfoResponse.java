package kr.or.connect.reservation.dto;

import java.util.List;

public class ReservationInfoResponse {
	// description: 예약조회 Response 모델
	private List<ReservationInfo> reservations;		// 예약 정보들
	private int size;								// 예약 수
}
