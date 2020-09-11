package kr.or.connect.reservation.dao;


import static kr.or.connect.reservation.dao.DetailDaoSqls.SELECT_DISPLAY_INFO_BY_DISPLAY_INFO_ID;
import static kr.or.connect.reservation.dao.DetailDaoSqls.SELECT_DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID;
import static kr.or.connect.reservation.dao.DetailDaoSqls.SELECT_PRODUCTIMAGES_BY_DISPLAY_INFO_ID;
import static kr.or.connect.reservation.dao.DetailDaoSqls.SELECT_PRODUCTPRICE_BY_DISPLAY_INFO_ID;
import static kr.or.connect.reservation.dao.DetailDaoSqls.SELECT_COMMENTIMAGES_BY_DISPLAY_INFO_ID;
import static kr.or.connect.reservation.dao.DetailDaoSqls.SELECT_COMMENTS_BY_DISPLAY_INFO_ID;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.dto.Promotion;

@Repository
public class DetailDao {
	private NamedParameterJdbcTemplate jdbc;

	private SimpleJdbcInsert insertAction;
	private RowMapper<DisplayInfo> displayInfo_rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<DisplayInfoImage> displayInfoImage_rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	private RowMapper<ProductImage> productImage_rowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	private RowMapper<ProductPrice> productPrice_rowMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);
	private RowMapper<Comment> comment_rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> commentImage_rowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);

	public void setDataSource(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DetailDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("DisplayInfo")
				.usingGeneratedKeyColumns("id");
	}

	public Double getTheAverageOfTheScores(Map<String, Double> map) {
		BigDecimal average = new BigDecimal(0);
		BigDecimal sum = new BigDecimal(map.get("sum"));
		BigDecimal count = new BigDecimal(map.get("count"));
		if(!count.equals(average)) {average = sum.divide(count, 16, RoundingMode.DOWN);}
		return average.doubleValue();
	}

	public List<Comment> getComments(Integer displayInfoId) {
		
		/*
		 select RUC.comment as comment, RUC.id as commentId, RUC.create_date as createDate, RUC.modify_date as modifyDate, 
				RUC.product_id as productId, RI.reservation_date as reservationDate, RI.reservation_email as reservationEmail,
				RUC.reservation_info_id as reservationInfoId, RI.reservation_name as reservationName,
				RI.reservation_tel as reservationTelephone, RUC.score as score
				from display_info DI
				join reservation_info RI
				join reservation_user_comment RUC on (DI.product_id = RUC.product_id)
				where DI.id = :displayInfoId desc;
		 * */
		try {
			Map<String,Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
			return jdbc.query(SELECT_COMMENTS_BY_DISPLAY_INFO_ID, params, comment_rowMapper);
		}catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<CommentImage> getCommentImages(Integer displayInfoId) {
		try {
			Map<String,Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
			return jdbc.query(SELECT_COMMENTIMAGES_BY_DISPLAY_INFO_ID, params, commentImage_rowMapper);
		}catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return null;
		}
	}

	public DisplayInfo getDisplayInfo(Integer displayInfoId) {
		try {
			Map<String,Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
			return jdbc.queryForObject(SELECT_DISPLAY_INFO_BY_DISPLAY_INFO_ID, params, displayInfo_rowMapper);
		}catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return null;
		}
	}

	public DisplayInfoImage getDisplayInfoImage(Integer displayInfoId) {
		try {
			Map<String,Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
			return jdbc.queryForObject(SELECT_DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID, params, displayInfoImage_rowMapper);
		}catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<ProductImage> getProductImages(Integer displayInfoId) {
		try {
			Map<String,Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
			return jdbc.query(SELECT_PRODUCTIMAGES_BY_DISPLAY_INFO_ID, params, productImage_rowMapper);
		}catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return null;
		}
	}

	public List<ProductPrice> getProductPrices(Integer displayInfoId) {
		try {
			Map<String,Integer> params = Collections.singletonMap("displayInfoId", displayInfoId);
			return jdbc.query(SELECT_PRODUCTPRICE_BY_DISPLAY_INFO_ID, params, productPrice_rowMapper);
		}catch (EmptyResultDataAccessException e){
			e.printStackTrace();
			return null;
		}

	}

	public List<Comment> setCommentAsCommentImages(List<Comment> comments, List<CommentImage> commentImages){
		for(int i = 0; i < comments.size(); i++)
		{
			ArrayList<CommentImage> tempCommentImage = new ArrayList<>();
			for(int j = 0; j < commentImages.size(); j++ )
			{
				if(comments.get(i).getCommentId() == commentImages.get(j).getReservationUserCommentId())
				{
					tempCommentImage.add(commentImages.get(j));
				}
			}
			comments.get(i).setCommentImages(tempCommentImage);
		}
		return comments;
	}
	
	public Map<String, Double> getCountAndSumOfScoresFromcomments (List<Comment> comments)
	{
		Map<String, Double> map = new HashMap<>();
		
		Double count = 0.0;
		Double sum = 0.0;
		
		for(int i = 0; i < comments.size(); i++)
		{
			sum = sum + comments.get(i).getScore();
			count = count + 1;
		}
		map.put("count", count);
		map.put("sum", sum);
		return map;
	}
	
	
}
