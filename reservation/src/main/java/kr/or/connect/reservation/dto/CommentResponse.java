package kr.or.connect.reservation.dto;

import java.util.Date;
import java.util.List;

public class CommentResponse {
	private String comment;                     // 평
	private int commentId;                      // 한줄평 Id
	private List<CommentImage> commentImage;	// 상품평 이미지
	private Date createDate;                    // 등록일
	private Date modifyDate;                    // 수정일
	private int productId;                      // 상품 Id
	private int reservationInfoId;              // 예약정보 Id
	private int score;                          // 별점
}
