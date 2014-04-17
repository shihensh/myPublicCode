package cn.mytaobao.model;
/**
 * 商品类别
 * @author By Jiang
 *
 */
public class Category {
	private int cId;
	private String cName;
	private int parentId;
	private int orderId;
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * 无参构造器
	 */
	public Category(){
		
	}
	/**
	 * 带参构造器
	 * @param cName
	 * @param parentId
	 * @param orderId
	 */
	public Category(String cName, int parentId, int orderId) {
		super();
		this.cName = cName;
		this.parentId = parentId;
		this.orderId = orderId;
	}
	@Override
	public String toString() {
		return "Category [cId=" + cId + ", cName=" + cName + ", parentId="
				+ parentId + ", orderId=" + orderId + "]";
	}
	
	
	

}
