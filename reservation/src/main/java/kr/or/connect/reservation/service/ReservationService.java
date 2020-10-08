package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.ReservationInfoResponse;

public interface ReservationService {
	public ReservationInfoResponse getReservationInfo (String reservationEmail);
}
