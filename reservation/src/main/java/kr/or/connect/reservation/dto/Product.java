package kr.or.connect.reservation.dto;

public class Product {
	// description: 상품 (Product) 모델
	
	private int displayInfoId;              // 전시 (display_info) Id
	private String placeName;               // 전시 장소 명
	private String productContent;          // 상품 상세 설명
	private String productDescription;		// 상품 설명
	private int productId;                  // 상품 (product) Id
	private String productImageUrl;         // 상품 썸네일 이미지 URL
	
	public Product() {}
	
	public Product(int displayInfoId, String placeName, String productContent, String productDescription,
			int productId, String productImageUrl) {
		this.displayInfoId = displayInfoId;
		this.placeName = placeName;
		this.productContent = productContent;
		this.productDescription = productDescription;
		this.productId = productId;
		this.productImageUrl = productImageUrl;
	}
	
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getProductContent() {
		return productContent;
	}
	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductImageUrl() {
		return productImageUrl;
	}
	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

	@Override
	public String toString() {
		return "Product_api [displayInfoId=" + displayInfoId + ", placeName=" + placeName + ", productContent="
				+ productContent + ", productDescription=" + productDescription + ", productId=" + productId
				+ ", productImageUrl=" + productImageUrl + "]";
	}
	
}
