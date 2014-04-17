package cn.mytaobao.model;
/**
 * ¶©»õÃ÷Ï¸
 * @author By Jiang
 *
 */
public class OrderInfo {
	private int id;
	private Product product;
	private  double price;
	private int count;
	private double discount;	//ÕÛ¿Û
	private double money;
	private User user;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public OrderInfo(){
		
	}
	public OrderInfo(Product product, double price, int count, double discount,
			double money, User user) {
		super();
		this.product = product;
		this.price = price;
		this.count = count;
		this.discount = discount;
		this.money = money;
		this.user = user;
	}
	@Override
	public String toString() {
		return "OrderInfo [id=" + id + ", product=" + product + ", price="
				+ price + ", count=" + count + ", discount=" + discount
				+ ", money=" + money + ", user=" + user + "]";
	}
	
	

}
