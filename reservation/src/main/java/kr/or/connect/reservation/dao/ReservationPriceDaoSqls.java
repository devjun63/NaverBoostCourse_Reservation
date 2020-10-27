package kr.or.connect.reservation.dao;

public class ReservationPriceDaoSqls {
	
	public static final String SELECT_RESERVATION_PRICE_BY_RESERVATION_INFO_ID = "select id as reservationInfoPriceId, reservation_info_id as reservationInfoId,\r\n" + 
			"product_price_id as productPriceId, count from reservation_info_price where reservation_info_id = :reservationInfoId";
}
