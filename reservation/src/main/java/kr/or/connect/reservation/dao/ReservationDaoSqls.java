package kr.or.connect.reservation.dao;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import kr.or.connect.reservation.dto.ReservationPrice;

public class ReservationDaoSqls {
	public static final String SELECT_RESERVATION_INFO = "select RI.id as reservationInfoId, RI.product_id as productId, RI.display_info_id as displayInfoId,\r\n" + 
			"			RI.reservation_name as reservationName, RI.reservation_email as reservationEmail,\r\n" + 
			"           RI.reservation_date as reservationDate, RI.cancel_flag as cancelYn,\r\n" + 
			"           RI.create_date as createDate, RI.modify_date as modifyDate,\r\n" + 
			"           SUM((PP.price - (PP.price * (PP.discount_rate / 100))) * RIP.count) as totalPrice\r\n" + 
			"           from reservation_info RI\r\n" + 
			"			join reservation_info_price RIP on (RIP. reservation_info_id = RI.id)\r\n" + 
			"			join product_price PP on (RIP.product_price_id = PP.id)\r\n" + 
			"           where RI.reservation_email = :reservationEmail group by RI.id order by RI.id DESC;";
	
	public static final String SELECT_PRODUCT_PRICES = "select PP.id as productPriceId, PP.product_id as productId,\r\n" + 
			"PP.price_type_name as priceTypeName, PP.price as price,\r\n" + 
			"PP.discount_rate as discountRate, PP.create_date as createDate,\r\n" + 
			"PP.modify_date as modifyDate\r\n" + 
			"from product_price PP\r\n" + 
			"join product P on (PP.product_id = P.id)\r\n" + 
			"join display_info DI on (P.id = DI.product_Id)\r\n" + 
			"where DI.id = :displayInfoId";
	public static final String UPDATE_RESERVATION_INFO = "UPDATE reservation_info SET cancel_flag = :cancel_flag, modify_date = now() WHERE ID = :reservationInfoId";
	public static final String SELECT_BY_RESERVATION_INFO_ID = "select RI.cancel_flag as cancelYn, RI.create_date as createDate, RI.display_info_id as displayInfoId,\r\n" + 
			"RI.modify_date as modifyDate, RI.product_id as productId, RI.reservation_date as reservationDate, RI.reservation_email as reservationEmail,\r\n" + 
			"RI.id as reservationInfoId, RI.reservation_name as reservationName, RI.reservation_tel as reservationTelephone,\r\n" + 
			"SUM((PP.price - (PP.price * (PP.discount_rate / 100))) * RIP.count) as totalCount\r\n" + 
			"from reservation_info RI\r\n" + 
			"join reservation_info_price RIP on (RIP. reservation_info_id = RI.id)\r\n" + 
			"join product_price PP on (RIP.product_price_id = PP.id)\r\n" + 
			"where RI.id = :reservationInfoId";
	
	public static final String SELECT_RESERVATION_INFO_BY_RESERVATION_INFO_ID =
			"select RI.id as reservationInfoId, RI.product_id as productId, RI.display_info_id as displayInfoId,\r\n" + 
			"			RI.reservation_name as reservationName, RI.reservation_email as reservationEmail,\r\n" + 
			"            RI.reservation_date as reservationDate, RI.cancel_flag as cancelYn,\r\n" + 
			"            RI.create_date as createDate, RI.modify_date as modifyDate,\r\n" + 
			"            RI.reservation_tel as reservationTelephone,\r\n" + 
			"            SUM((PP.price - (PP.price * (PP.discount_rate / 100))) * RIP.count) as totalPrice\r\n" + 
			"            from reservation_info RI\r\n" + 
			"			join reservation_info_price RIP on (RIP. reservation_info_id = RI.id)\r\n" + 
			"			join product_price PP on (RIP.product_price_id = PP.id)\r\n" + 
			"            where RI.id = :reservationInfoId";
}
