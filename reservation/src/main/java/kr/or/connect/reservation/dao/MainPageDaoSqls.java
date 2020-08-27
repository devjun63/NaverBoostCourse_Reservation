package kr.or.connect.reservation.dao;

public class MainPageDaoSqls {
	public static final String SELECT_EACH_PRODUCT_COUNT_FROM_CATEGORY = "select C.id, C.name, COUNT(P.id) as count from category C join product P on C.id = P.category_id group by P.category_id";
	public static final String SELECT_ALL_PRODUCT_COUNT = "select COUNT(*) from product";
	public static final String SELECT_NUMBER_OF_PRODUCT_COUNT_FROM_CATEOGORY = "select COUNT(*) from category join product on category.id = product.category_id where category_id = :category_id group by category_id;";
	public static final String SELECT_PROMOTION_API_DATAS = "select PM.id, PM.product_id, FI.save_file_name as productImageUrl \r\n" + 
			"FROM file_info FI left outer join product_image PI on FI.id = PI.file_id and PI.type = 'th'\r\n" + 
			"join promotion PM ON PM.product_id = PI.product_id";
	public static final String SELECT_PRODUCT_API_DATAS_AS_CATEGORYID_AND_START_NUMBER = "select DI.id as displayInfoId, DI.place_name as placeName,"
			+ " P.content as productContent, P.description as productDescription, P.id as productId, FI.save_file_name as productImageUrl\r\n" + 
			"from product P\r\n" + 
			"join product P2 on (P.category_id = P2.category_id)\r\n" + 
			"join product_image PI on (P.id = PI.product_id)\r\n" + 
			"join display_info DI on (P.id = DI.product_id)\r\n" + 
			"join file_info FI on (PI.file_id = FI.id)\r\n" + 
			"where PI.type = 'th' and P.category_id = :categoryId group by P.id limit :start, :limit";
}
