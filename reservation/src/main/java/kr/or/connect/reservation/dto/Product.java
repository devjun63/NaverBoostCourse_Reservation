package kr.or.connect.reservation.dto;

import java.util.Date;

public class Product {
	private int id;					// 상품 id - PK
	private int category_id;        // 카테고리 id - FK - References category (id)
	private String description;     // 상품 간단 설명
	private String content;         // 상품 상세 설명
	private String event;           // 이벤트 정보
	private Date create_date;       // 생성 시간
	private Date modify_date;       // 수정 시간
	
	public Product() {}

	public Product(int id, int category_id, String description, String content, String event, Date create_date,
			Date modify_date) {
		this.id = id;
		this.category_id = category_id;
		this.description = description;
		this.content = content;
		this.event = event;
		this.create_date = create_date;
		this.modify_date = modify_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
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
		return "Product [id=" + id + ", category_id=" + category_id + ", description=" + description + ", content="
				+ content + ", event=" + event + ", create_date=" + create_date + ", modify_date=" + modify_date + "]";
	}
	
	
}
