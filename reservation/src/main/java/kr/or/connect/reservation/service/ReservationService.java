package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.ReservationInfoResponse;

public interface ReservationService {
	public ReservationInfoResponse getReservationInfo (String reservationEmail);
	public String getReserveStartDate();
	public String getReserveEndDate();
	public String getReserveRandomDate();
	public List<ProductPrice> getProductPrices(Integer displayInfoId);
	/*public List<ProductPrice> getFormatPrices(List<ProductPrice> productPrices);*/
}
