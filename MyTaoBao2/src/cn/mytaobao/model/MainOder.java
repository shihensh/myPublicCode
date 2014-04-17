package cn.mytaobao.model;
/**
 * ¶©»õÖ÷µµ
 * @author By Jiang
 *
 */
public class MainOder {
	private int id;
	private User user;
	private String date;
	private String phone;
	private String address;
	private int postCode;
	private double totalMoney;
	private OrderState odOrderState;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public OrderState getOdOrderState() {
		return odOrderState;
	}
	public void setOdOrderState(OrderState odOrderState) {
		this.odOrderState = odOrderState;
	}
	public MainOder(){
		
	}
	public MainOder(User user, String date, String phone, String address,
			int postCode, double totalMoney, OrderState odOrderState) {
		super();
		this.user = user;
		this.date = date;
		this.phone = phone;
		this.address = address;
		this.postCode = postCode;
		this.totalMoney = totalMoney;
		this.odOrderState = odOrderState;
	}
	@Override
	public String toString() {
		return "MainOder [id=" + id + ", user=" + user + ", date=" + date
				+ ", phone=" + phone + ", address=" + address + ", postCode="
				+ postCode + ", totalMoney=" + totalMoney + ", odOrderState="
				+ odOrderState + "]";
	}
	

}
