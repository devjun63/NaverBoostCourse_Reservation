package kr.or.connect.reservation.dao;

public class DetailDaoSqls {

	public static final String SELECT_DISPLAY_INFO_BY_DISPLAY_INFO_ID = "select C.id as categoryId, C.name as categoryName, DI.create_date as createDate, DI.id as displayInfoId,\r\n" + 
			"		  DI.email as email, DI.homepage as homepage, DI.modify_date as modifyDate, DI.opening_Hours as openingHours,\r\n" + 
			"		  DI.place_lot as placeLot, DI.place_name as placeName, DI.place_street as placeStreet,\r\n" + 
			"		  P.content as productContent, P.description as productDescription, P.event as productEvent, P.id as productId,\r\n" + 
			"		  DI.tel as telephone\r\n" + 
			"		  from display_info DI\r\n" + 
			"		  join product P on (P.id = DI.product_id)\r\n" + 
			"		  join category C on (C.id = P.category_id)\r\n" + 
			"		  where DI.id = :displayInfoId";
	public static final String SELECT_DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID = "select FI.content_type as contentType, FI.create_date as createDate, FI.delete_flag as deleteFlag,\r\n" + 
			"		  DI.id as displayInfoId, DII.id as displayInfoImageId, FI.id as fileId, FI.file_name as fileName,\r\n" + 
			"		  FI.modify_date as modifyDate, FI.save_file_name as saveFileName\r\n" + 
			"		  from display_info DI\r\n" + 
			"		  join display_info_image DII on (DI.id = DII.display_info_id)\r\n" + 
			"		  join file_info FI on (FI.id = DII.file_id)\r\n" + 
			"		  where DI.id = :displayInfoId";
	public static final String SELECT_PRODUCTIMAGES_BY_DISPLAY_INFO_ID = "select FI.content_type as contentType, FI.create_date as createDate, FI.delete_flag as deleteFlag,\r\n" + 
			"		  FI.id as fileInfoId, FI.file_name as fileName, FI.modify_date as modifyDate, \r\n" + 
			"		  PI.product_id as productId, PI.id as productImageId, FI.save_file_name as saveFileName,\r\n" + 
			"		  PI.type as type\r\n" + 
			"		  from display_info DI\r\n" + 
			"		  join product_image PI on (DI.product_id = PI.product_id)\r\n" + 
			"		  join file_info FI on (FI.id = PI.file_id)\r\n" + 
			"		  where DI.id = :displayInfoId";
	public static final String SELECT_PRODUCTPRICE_BY_DISPLAY_INFO_ID = "select PP.create_date as createDate, PP.discount_rate as discountRate, PP.modify_date as modifyDate,\r\n" + 
			"		 PP.price as price, PP.price_type_name as priceTypeName, PP.product_id as productId, PP.id as productPriceId\r\n" + 
			"		 from display_info DI\r\n" + 
			"		 join  product_price PP on (PP.product_id = DI.product_id)\r\n" + 
			"		 where DI.id = :displayInfoId";
	public static final String SELECT_COMMENTIMAGES_BY_DISPLAY_INFO_ID = "select FI.content_type as contentType, FI.create_date as createDate, FI.delete_flag as deleteFlag,\r\n" + 
			"		 FI.id as fileId, FI.file_name as fileName, RUCI.id as imageId, FI.modify_date as modifyDate,\r\n" + 
			"		 RUCI.reservation_info_id as reservationInfoId, RUCI.reservation_user_comment_id as reservationUserCommentId,\r\n" + 
			"		 FI.save_file_name as saveFileName\r\n" + 
			"		 from display_info DI\r\n" + 
			"		 join reservation_user_comment RUC on (DI.product_id = RUC.product_id)\r\n" + 
			"		 join reservation_user_comment_image RUCI on (RUC.id = RUCI.reservation_user_comment_id)\r\n" + 
			"		 join file_info FI on (FI.id = RUCI.file_id)\r\n" + 
			"		 where DI.id = :displayInfoId";
	public static final String SELECT_COMMENTS_BY_DISPLAY_INFO_ID = "select RUC.comment as comment, RUC.id as commentId, RUC.create_date as createDate, RUC.modify_date as modifyDate, \r\n" + 
			"							RUC.product_id as productId, RI.reservation_date as reservationDate, RI.reservation_email as reservationEmail,\r\n" + 
			"							RUC.reservation_info_id as reservationInfoId, RI.reservation_name as reservationName,\r\n" + 
			"							RI.reservation_tel as reservationTelephone, RUC.score as score\r\n" + 
			"							from display_info DI\r\n" + 
			"							join reservation_user_comment RUC on (DI.product_id = RUC.product_id)\r\n" + 
			"                            join reservation_info RI on (RI.id = RUC.reservation_info_id)\r\n" + 
			"							where DI.id = :displayInfoId order by RUC.id desc";
}
