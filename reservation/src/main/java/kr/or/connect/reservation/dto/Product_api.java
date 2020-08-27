package kr.or.connect.reservation.dto;

public class Product_api {
	
	private int displayInfoId;
	private String placeName;
	private String productContent;
	private String productDescription;
	private int productId;
	private String productImageUrl;
	
	public Product_api() {}
	
	public Product_api(int displayInfoId, String placeName, String productContent, String productDescription,
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
