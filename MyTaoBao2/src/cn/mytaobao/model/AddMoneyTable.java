package cn.mytaobao.model;
/**
 * ≥‰÷µ±Ì
 * @author By Jiang
 *
 */
public class AddMoneyTable {
	private int amId;
	private User user;
	private String bank;
	private String time;
	private double money;
	private int stateId;
	private String payType;
	
	public int getAmId() {
		return amId;
	}

	public void setAmId(int amId) {
		this.amId = amId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	public AddMoneyTable(){
		
	}
	public AddMoneyTable( User user, String bank, String time,
			double money, int stateId, String payType) {
		super();
		this.user = user;
		this.bank = bank;
		this.time = time;
		this.money = money;
		this.stateId = stateId;
		this.payType = payType;
	}

	@Override
	public String toString() {
		return "AddMoneyTable [amId=" + amId + ", user=" + user + ", bank="
				+ bank + ", time=" + time + ", money=" + money + ", stateId="
				+ stateId + ", payType=" + payType + "]";
	}
	
	

}
