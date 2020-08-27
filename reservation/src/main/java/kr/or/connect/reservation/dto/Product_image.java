package kr.or.connect.reservation.dto;

public class Product_image {
	private int id;				// 상품 이미지 id & PK 
	private int product_id;		// 상품 id & FK - References product (id)
	private String type;		// 이미지 type, th: 썸네일, ma: 메인, et: 기타 (etc)
	private int file_id;		// file id & FK - References file_info (id)
	
	public Product_image() {}

	public Product_image(int id, int product_id, String type, int file_id) {
		this.id = id;
		this.product_id = product_id;
		this.type = type;
		this.file_id = file_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFile_id() {
		return file_id;
	}

	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}

	@Override
	public String toString() {
		return "Product_image [id=" + id + ", product_id=" + product_id + ", type=" + type + ", file_id=" + file_id
				+ "]";
	}
	
	
}
