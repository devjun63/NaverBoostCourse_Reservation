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
	
	public File_info() {}

	public File_info(int id, String file_name, String save_file_name, String content_type, int delete_flag,
			Date create_date, Date modify_date) {
		this.id = id;
		this.file_name = file_name;
		this.save_file_name = save_file_name;
		this.content_type = content_type;
		this.delete_flag = delete_flag;
		this.create_date = create_date;
		this.modify_date = modify_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getSave_file_name() {
		return save_file_name;
	}

	public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public int getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(int delete_flag) {
		this.delete_flag = delete_flag;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	@Override
	public String toString() {
		return "File_info [id=" + id + ", file_name=" + file_name + ", save_file_name=" + save_file_name
				+ ", content_type=" + content_type + ", delete_flag=" + delete_flag + ", create_date=" + create_date
				+ ", modify_date=" + modify_date + "]";
	}
	
}
