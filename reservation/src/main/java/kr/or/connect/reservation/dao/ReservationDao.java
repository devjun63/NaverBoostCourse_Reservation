package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.DetailDaoSqls.SELECT_DISPLAY_INFO_BY_DISPLAY_INFO_ID;
import static kr.or.connect.reservation.dao.ReservationDaoSqls.SELECT_RESERVATION_INFO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.ReservationInfo;

@Repository
public class ReservationDao {

	private NamedParameterJdbcTemplate jdbc;

	private SimpleJdbcInsert insertAction;
	private RowMapper<ReservationInfo> reservationInfo_rowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	private RowMapper<DisplayInfo> displayInfo_rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	
	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("DisplayInfo")
				.usingGeneratedKeyColumns("id");
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
	
}
