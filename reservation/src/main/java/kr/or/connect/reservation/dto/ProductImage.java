package kr.or.connect.reservation.dto;

import java.util.Date;

public class ProductImage {
	// description: 상품 이미지 모델
	private String contentType;     // 파일 확장자
	private Date createDate;        // 생성일
	private boolean deleteFlag;     // 삭제 여부
	private int fileInfoId;         // 파일 Id
	private String fileName;        // 파일 이름
	private Date modifyDate;        // 수정일
	private int productId;          // 상품 Id
	private int productImageId;		// 상품 이미지Id
	private String saveFileName;	// 파일 저장 위치 이름
	private Type type;            // 이미지 타입 (main, thumbnail, etc)
	
	
}
