package kr.or.connect.reservation.dto;

public class Promotion_api {
	private int id;				// 프로모션 id PK 
	private int product_id;		// 프로덕트 id FK - References product (id)
	private String productImageUrl; // 프로모션 이미지 경로
	
	public Promotion_api() {}

	public Promotion_api(int id, int product_id, String productImageUrl) {
		this.id = id;
		this.product_id = product_id;
		this.productImageUrl = productImageUrl;
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
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}
	
	@Override
	public String toString() {
		return "Promotion_api [id=" + id + ", product_id=" + product_id + ", productImageUrl=" + productImageUrl + "]";
	}
}
