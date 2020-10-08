package kr.or.connect.reservation.dao;

public class ReservationDaoSqls {
	public static final String SELECT_RESERVATION_INFO = "select RI.cancel_flag as cancelYn, RI.create_date as createDate, RI.display_info_id as displayInfoId,\r\n" + 
			"RI.modify_date as modifyDate, RI.product_id as productId, RI.reservation_date as reservationDate, RI.reservation_email as reservationEmail,\r\n" + 
			"RI.id as reservationInfoId, RI.reservation_name as reservationName, RI.reservation_tel as reservationTelephone,\r\n" + 
			"SUM((PP.price - (PP.price * (PP.discount_rate / 100))) * RIP.count) as totalPrice\r\n" + 
			"from reservation_info RI\r\n" + 
			"join reservation_info_price RIP on (RIP. reservation_info_id = RI.id)\r\n" + 
			"join product_price PP on (RIP.product_price_id = PP.id)\r\n" + 
			"where RI.reservation_email = :reservationEmail";
	
}
