package cn.mytaobao.model;
/**
 * 用户对某商品的评价
 * @author By Jiang
 *
 */
public class PEvaluate {
	private int id;
	private User user;
	private String evluate;
	private Product product;
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
	public String getEvluate() {
		return evluate;
	}
	public void setEvluate(String evluate) {
		this.evluate = evluate;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public PEvaluate (){
		
	}
	public PEvaluate(User user, String evluate, Product product) {
		super();
		this.user = user;
		this.evluate = evluate;
		this.product = product;
	}
	@Override
	public String toString() {
		return "PEvaluate [id=" + id + ", user=" + user + ", evluate="
				+ evluate + ", product=" + product + "]";
	}
	

}
