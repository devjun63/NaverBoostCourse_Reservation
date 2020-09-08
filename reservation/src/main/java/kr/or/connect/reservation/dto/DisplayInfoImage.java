package kr.or.connect.reservation.dto;

import java.util.Date;

public class DisplayInfoImage {
	private String contentType;     	// 파일 확장자
	private Date createDate;        	// 생성일
	private boolean deleteFlag;     	// 삭제 여부
	private int displayInfoId;      	// 전시 Id
	private int displayInfoImageId;		// 전시 이미지 Id
	private int fileId;             	// 파일 Id
	private String fileName;        	// 파일 이름
	private Date modifyDate;        	// 수정일
	private String saveFileName;    	// 파일 저장 위치 이름
}
