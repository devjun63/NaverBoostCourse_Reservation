package kr.or.connect.reservation.service;

import java.util.Date;
import java.util.List;

import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationParam;
import kr.or.connect.reservation.dto.ReservationResponse;

public interface ReservationService {
	
	public String getReserveStartDate();
	public String getReserveEndDate();
	public String getReserveRandomDate();
	public String getReserveDate(Date date);
	
	
	public ReservationInfoResponse getReservationInfo (String reservationEmail);
	public List<ProductPrice> getProductPrices(Integer displayInfoId);
	
	public ReservationResponse setReservation(ReservationParam param);
	
	
	public int deleteReservationInfo();
	
	
	
}
