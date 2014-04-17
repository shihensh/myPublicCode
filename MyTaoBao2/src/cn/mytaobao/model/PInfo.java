package cn.mytaobao.model;
/**
 * ��Ʒ������Ϣ
 * @author By Jiang
 *
 */
public class PInfo {
	private int id;
	private Product product;
	private int saleCount;		//������
	private int clickCount;		//�����
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(int saleCount) {
		this.saleCount = saleCount;
	}
	public int getClickCount() {
		return clickCount;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	public PInfo(){
		
	}
	public PInfo(Product product, int saleCount, int clickCount) {
		super();
		this.product = product;
		this.saleCount = saleCount;
		this.clickCount = clickCount;
	}
	@Override
	public String toString() {
		return "PInfo [id=" + id + ", product=" + product + ", saleCount="
				+ saleCount + ", clickCount=" + clickCount + "]";
	}
	

}
