package kr.or.connect.reservation.dto;

import java.util.Date;
import java.util.List;

public class Comment {
	private String comment; 						// 상품평
	private int commentId; 							// 상품평 Id
	private List<CommentImage> commentImages;		// 상품평 이미지들
	private Date createDate; 						// 생성일
	private Date modifyDate;						// 수정일
	private int productId;							// 상품 Id
	private String reservationDate;                 // 예약일 
	private String reservationEmail;                // 예약자 이메일
	private int reservationInfoId;                  // 예약 Id
	private String reservationName;                 // 예약자 명
	private String reservationTelephone;            // 예약자 전화번호
	private double score;                           // 평점
}
