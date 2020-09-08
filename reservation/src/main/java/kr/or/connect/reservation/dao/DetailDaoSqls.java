package kr.or.connect.reservation.dao;

public class DetailDaoSqls {
	public static final String SELECT_EACH_PRODUCT_COUNT_FROM_CATEGORY = "select C.id, C.name, COUNT(P.id) as count from category C join product P on C.id = P.category_id group by P.category_id";
	public static final String SELECT_DISPLAY_INFO_BY_DISPLAY_INFO_ID = "select C.id, C.name, COUNT(P.id) as count from category C join product P on C.id = P.category_id group by P.category_id ";
}
