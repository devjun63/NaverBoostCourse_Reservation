package kr.or.connect.reservation.dto;

import java.util.Date;

public class File_info {
	private int id;					// 파일id & PK
	private String file_name;		// 원본 파일명
	private String save_file_name;	// save파일 명,
	private String content_type;	// 파일 mime type
	private int delete_flag;		// 삭제 유무 - 삭제 : 1  삭제안됨 :0
	private Date create_date;		// 등록일
	private Date modify_date;		// 수정일
}
