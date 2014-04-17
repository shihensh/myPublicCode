package cn.mytaobao.model;
/**
 * 商品信息
 * @author By Jiang
 *
 */
public class Product {
	private int pId;
	private String pName;
	private Category category;
	private String pImage;
	private double pPrice;
	private String Note;
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getpImage() {
		return pImage;
	}
	public void setpImage(String pImage) {
		this.pImage = pImage;
	}
	public double getpPrice() {
		return pPrice;
	}
	public void setpPrice(double pPrice) {
		this.pPrice = pPrice;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public Product(){
		
	}
	
	public Product(int pId, String pName, Category category, String pImage,
			double pPrice, String note) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.category = category;
		this.pImage = pImage;
		this.pPrice = pPrice;
		Note = note;
	}
	public Product(String pName, Category category, String pImage,
			double pPrice, String note) {
		super();
		this.pName = pName;
		this.category = category;
		this.pImage = pImage;
		this.pPrice = pPrice;
		Note = note;
	}
	@Override
	public String toString() {
		return "Product [pId=" + pId + ", pName=" + pName + ", category="
				+ category.getcName() + ", pImage=" + pImage + ", pPrice=" + pPrice
				+ ", Note=" + Note + "]";
	}

	
	

}
