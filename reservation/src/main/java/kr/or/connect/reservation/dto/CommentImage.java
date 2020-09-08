package kr.or.connect.reservation.dto;

import java.util.Date;

public class CommentImage {
	private String contentType;				// 파일 확장자
	private Date createDate;                // 생성일
	private boolean deleteFlag;             // 삭제 여부
	private int fileId;                     // 파일 id
	private String fileName;                // 파일 이름
	private int imageId;                    // 이미지 Id
	private Date modifyDate;                // 수정일
	private int reservationInfoId;          // 예약 Id
	private int reservationUserCommentId;	// 예약자 상품평 Id
	private String saveFileName;            // 파일 저장 위치 이름
}
