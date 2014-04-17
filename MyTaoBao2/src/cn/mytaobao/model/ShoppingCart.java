package cn.mytaobao.model;
/**
 * ¹ºÎï³µ
 * @author By Jiang
 *
 */
public class ShoppingCart {
	private int id;
	private User user;
	private Product product;
	private int count;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ShoppingCart(){
		
	}
	public ShoppingCart(User user, Product product, int count) {
		super();
		this.user = user;
		this.product = product;
		this.count = count;
	}
	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", user=" + user + ", product="
				+ product + ", count=" + count + "]";
	}
	

}
