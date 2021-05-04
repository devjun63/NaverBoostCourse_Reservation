package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.DetailDaoSqls.SELECT_DISPLAY_INFO_BY_DISPLAY_INFO_ID;
import static kr.or.connect.reservation.dao.ReservationDaoSqls.SELECT_PRODUCT_PRICES;
import static kr.or.connect.reservation.dao.ReservationDaoSqls.SELECT_RESERVATION_INFO;
import static kr.or.connect.reservation.dao.ReservationDaoSqls.UPDATE_RESERVATION_INFO;
import static kr.or.connect.reservation.dao.ReservationDaoSqls.SELECT_BY_RESERVATION_INFO_ID;
import static kr.or.connect.reservation.dao.ReservationDaoSqls.SELECT_RESERVATION_INFO_BY_RESERVATION_INFO_ID;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationResponse;

@Repository
public class ReservationDao {

	final static Integer CANCLE_FLAG = 1;
	
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;

	private RowMapper<ReservationInfo> reservationInfo_rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	private RowMapper<DisplayInfo> displayInfo_rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<ProductPrice> productPrice_rowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);
	private RowMapper<ReservationResponse> reservationResponse_rowMapper = BeanPropertyRowMapper.newInstance(ReservationResponse.class);
	
	
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public  ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_info")
				.usingGeneratedKeyColumns("id");
		/*SimpleJdbcInsertOperations insertion = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_info_price").usingGeneratedKeyColumns("id");*/
	}

	public List<ReservationInfo> getReservations(String reservationEmail) {
		try {
			Map<String, String> params = Collections.singletonMap("reservationEmail" , reservationEmail);
			return jdbc.query(SELECT_RESERVATION_INFO, params, reservationInfo_rowMapper);
		}catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<DisplayInfo> getDisplayInfo(Integer displayInfoId) {
		try {
			Map<String,Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
			return jdbc.query(SELECT_DISPLAY_INFO_BY_DISPLAY_INFO_ID, params, displayInfo_rowMapper);
		}catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<ReservationInfo> setReservationsAsDisplayInfo(List<ReservationInfo> reservations){
		for(int i = 0; i < reservations.size(); i++)
		{
			int currentDisplayInfoId = reservations.get(i).getDisplayInfoId();
			List<DisplayInfo> multiple_displayInfo = this.getDisplayInfo(currentDisplayInfoId);
			reservations.get(i).setDisplayInfo(multiple_displayInfo);
		}
		return reservations;
	}

	public List<ProductPrice> getProductPrices(Integer displayInfoId) {
		try {
			Map<String,Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
			return jdbc.query(SELECT_PRODUCT_PRICES, params, productPrice_rowMapper);
		}catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return null;
		}
	}

	public Integer insertReservationResponse(ReservationResponse reservationResponse) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("product_id", reservationResponse.getProductId());
		parameters.put("display_info_id", reservationResponse.getDisplayInfoId());
		parameters.put("reservation_name", reservationResponse.getReservationName());
		parameters.put("reservation_tel", reservationResponse.getReservationTelephone());
		parameters.put("reservation_email", reservationResponse.getReservationEmail());
		parameters.put("reservation_date", reservationResponse.getReservationDate());
		parameters.put("cancel_flag", reservationResponse.isCancelYn());
		parameters.put("create_date", reservationResponse.getCreateDate());
		parameters.put("modify_date", reservationResponse.getModifyDate());
		return insertAction.executeAndReturnKey(parameters).intValue();
		
		
	}

	public int updateReservation(Integer reservationInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationInfoId", reservationInfoId);
		params.put("cancel_flag", CANCLE_FLAG);
		return jdbc.update(UPDATE_RESERVATION_INFO, params);
	}
	
	public ReservationResponse getReservationResponse(Integer reservationInfoId) {
		try {
			Map<String,Integer> params = Collections.singletonMap("reservationInfoId", reservationInfoId);
			return jdbc.queryForObject(SELECT_BY_RESERVATION_INFO_ID, params, reservationResponse_rowMapper);
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ReservationInfo getReservationInfo(Integer reservationInfoId) {
		try {
			Map<String, Integer> params = Collections.singletonMap("reservationInfoId", reservationInfoId);
			return jdbc.queryForObject(SELECT_RESERVATION_INFO_BY_RESERVATION_INFO_ID, params, reservationInfo_rowMapper);
		}catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
}
