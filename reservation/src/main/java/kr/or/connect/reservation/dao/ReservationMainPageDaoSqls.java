package kr.or.connect.reservation.dao;

public class ReservationMainPageDaoSqls {
	public static final String SELECT_EACH_PRODUCT_COUNT_FROM_CATEGORY = "select category_id, COUNT(*) from category join product on category.id = product.category_id group by category_id";
	public static final String SELECT_NUMBER_OF_PRODUCT_COUNT_FROM_CATEOGORY = "select COUNT(*) from category join product on category.id = product.category_id where category_id = :category_id group by category_id;";
}
