package kr.or.connect.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import static kr.or.connect.reservation.dao.ReservationMainPageDaoSqls.*;

import kr.or.connect.reservation.dto.Product;

@Repository
public class ReservationMainPageDao {
	private NamedParameterJdbcTemplate jdbc;

	private SimpleJdbcInsert insertAction;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public ReservationMainPageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("Product")
				.usingGeneratedKeyColumns("id");
	}

	public List<Product> selectProducts(Integer category_id) {
		Map<String, Integer> params = new HashMap<>();
		params.put("category_id", category_id);
		return jdbc.query(SELECT_NUMBER_OF_PRODUCT_COUNT_FROM_CATEOGORY, params, rowMapper);
	}

	public Integer selectCount(Integer category_id) {
		try {
			Map<String,Integer> params = Collections.singletonMap("category_id", category_id);
			return (Integer)jdbc.queryForObject(SELECT_NUMBER_OF_PRODUCT_COUNT_FROM_CATEOGORY, params, Integer.class);
		}catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return 0;
		}
	}
}
