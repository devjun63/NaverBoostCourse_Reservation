package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationPrice;

@Repository
public class ReservationPriceDao {

	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<ReservationPrice> reservationPricerowMapper = BeanPropertyRowMapper.newInstance(ReservationPrice.class);
	
	
	public  ReservationPriceDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_info_price")
				.usingGeneratedKeyColumns("id");
		
				
	}

	public Integer insertReservationPrice(ReservationPrice reservationPrice) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("reservation_info_id", reservationPrice.getReservationInfoId());
		parameters.put("product_price_id", reservationPrice.getProductPriceId());
		parameters.put("count", reservationPrice.getCount());
		return insertAction.executeAndReturnKey(parameters).intValue();
	}
}
