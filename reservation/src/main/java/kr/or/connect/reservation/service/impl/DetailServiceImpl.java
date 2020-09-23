package kr.or.connect.reservation.service.impl;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.DetailDao;
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.DisplayInfoResponse;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.service.DetailService;

@Service
public class DetailServiceImpl implements DetailService{

	@Autowired
	DetailDao detailDao;
	
	@Override
	@Transactional
	public DisplayInfoResponse getDisplayInfoResponse(Integer displayInfoId) {
		
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();
		
		Map<String, Double> countAndSum = new HashMap<>();
		
		
		DisplayInfo displayInfo = detailDao.getDisplayInfo(displayInfoId);
		DisplayInfoImage displayInfoImage = detailDao.getDisplayInfoImage(displayInfoId);
		List<ProductImage> productImages = detailDao.getProductImages(displayInfoId);
		List<ProductPrice> productPrices = detailDao.getProductPrices(displayInfoId);
		
		Double averageScore = new Double(0);
		List<CommentImage> commentImages = detailDao.getCommentImages(displayInfoId);
		List<Comment> comments = detailDao.getComments(displayInfoId);
		comments = detailDao.setCommentAsCommentImages(comments, commentImages);
		countAndSum = detailDao.getCountAndSumOfScoresFromcomments(comments);
		averageScore = detailDao.getTheAverageOfTheScores(countAndSum);
		List<ProductImage> extractproductImages = detailDao.extractProductEtcImage(productImages);
		
		
		displayInfoResponse.setAverageScore(averageScore);
		displayInfoResponse.setComments(comments);
		displayInfoResponse.setDisplayInfo(displayInfo);
		displayInfoResponse.setDisplayInfoImage(displayInfoImage);
		displayInfoResponse.setProductImages(extractproductImages);
		displayInfoResponse.setProductPrices(productPrices);
		
		return displayInfoResponse;
	}

}
