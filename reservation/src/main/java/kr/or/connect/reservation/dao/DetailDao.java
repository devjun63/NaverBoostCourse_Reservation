package kr.or.connect.reservation.dao;

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
	
}
