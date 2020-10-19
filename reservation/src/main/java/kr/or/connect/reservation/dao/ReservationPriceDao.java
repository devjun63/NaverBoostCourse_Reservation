package kr.or.connect.reservation.dao;

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
		final String sql = "insert into reservation_info_price(id, reservation_info_id, product_price_id, count) values(:reservationInfoPriceId, :reservationInfoId, :productPriceId, :count)";
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationPrice);
		
		return jdbc.update(sql, params);
	}
}
