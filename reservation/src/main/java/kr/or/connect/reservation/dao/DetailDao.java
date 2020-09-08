package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.MainPageDaoSqls.SELECT_ALL_PRODUCT_API_DATA_AS_STARTING_NUMBER;
import static kr.or.connect.reservation.dao.MainPageDaoSqls.SELECT_ALL_PRODUCT_COUNT;
import static kr.or.connect.reservation.dao.MainPageDaoSqls.SELECT_EACH_PRODUCT_COUNT_FROM_CATEGORY;
import static kr.or.connect.reservation.dao.MainPageDaoSqls.SELECT_NUMBER_OF_PRODUCT_COUNT_FROM_CATEOGORY;
import static kr.or.connect.reservation.dao.MainPageDaoSqls.SELECT_PRODUCT_API_DATAS_AS_CATEGORYID_AND_START_NUMBER;
import static kr.or.connect.reservation.dao.MainPageDaoSqls.SELECT_PROMOTION_API_DATAS;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.Promotion;

@Repository
public class DetailDao {
	private NamedParameterJdbcTemplate jdbc;

	private SimpleJdbcInsert insertAction;
	private RowMapper<Product> product_rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<Promotion> promotion_Api_rowMapper = BeanPropertyRowMapper.newInstance(Promotion.class);
	private RowMapper<Category> Cateogory_Api_rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	private RowMapper<Product> Product_Api_rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DetailDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("Product")
				.usingGeneratedKeyColumns("id");
	}
	
	 

	public List<Product> selectallProductApiDatas(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_ALL_PRODUCT_API_DATA_AS_STARTING_NUMBER, params, Product_Api_rowMapper);
	}
	
	public List<Product> selectProductApiDatas(Integer categoryId, Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PRODUCT_API_DATAS_AS_CATEGORYID_AND_START_NUMBER, params, Product_Api_rowMapper);
	}

	public Integer selectProductsTotalCountFromCategory() {
		try {
			return (Integer)jdbc.queryForObject(SELECT_ALL_PRODUCT_COUNT, Collections.EMPTY_MAP, Integer.class);
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public Integer selectProductsTotalCountFromCategory(Integer category_id) {
		try {
			Map<String,Integer> params = Collections.singletonMap("category_id", category_id);
			return (Integer)jdbc.queryForObject(SELECT_NUMBER_OF_PRODUCT_COUNT_FROM_CATEOGORY, params, Integer.class);
		}catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<Category> selectCategoryList() {
		try {
			return jdbc.query(SELECT_EACH_PRODUCT_COUNT_FROM_CATEGORY, Collections.emptyMap(), Cateogory_Api_rowMapper);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Promotion> selectPromotionList() {
		try {
			return jdbc.query(SELECT_PROMOTION_API_DATAS, Collections.emptyMap(), promotion_Api_rowMapper);
		} catch (EmptyResultDataAccessException e2) {
			e2.printStackTrace();
			return null;
		}
	}
}
