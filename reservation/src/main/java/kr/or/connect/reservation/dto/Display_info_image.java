package kr.or.connect.reservation.dto;

public class Display_info_image {
	private int id;					// 전시정보 이미지 id & PK 
	private int display_info_id;	// 전시정보 id & FK reference - display_info (id)
	private int file_id;			// file id & FK reference - file_info (id)
}
