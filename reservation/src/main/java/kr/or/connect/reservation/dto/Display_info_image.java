package kr.or.connect.reservation.dto;

public class Display_info_image {
	private int id;					// 전시정보 이미지 id & PK 
	private int display_info_id;	// 전시정보 id & FK reference - display_info (id)
	private int file_id;			// file id & FK reference - file_info (id)
	
	public Display_info_image() {}

	public Display_info_image(int id, int display_info_id, int file_id) {
		this.id = id;
		this.display_info_id = display_info_id;
		this.file_id = file_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDisplay_info_id() {
		return display_info_id;
	}

	public void setDisplay_info_id(int display_info_id) {
		this.display_info_id = display_info_id;
	}

	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	@Override
	public String toString() {
		return "Display_info_image [id=" + id + ", display_info_id=" + display_info_id + ", file_id=" + file_id + "]";
	}

}
