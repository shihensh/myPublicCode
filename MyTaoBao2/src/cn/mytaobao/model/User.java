package cn.mytaobao.model;
/**
 * 用户实体
 * @author By Jiang
 *
 */
public class User {
	private int uId;
	private String uNmae;
	private String uPassWord;
	private double uMoney;
	private String uPhone;
	private String uAddress;
	private String uEmail;
	private int uPostCode;
	private String state;		//表示用户账户可用，禁用，登陆，退出等状态
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getuNmae() {
		return uNmae;
	}
	public void setuNmae(String uNmae) {
		this.uNmae = uNmae;
	}
	public String getuPassWord() {
		return uPassWord;
	}
	public void setuPassWord(String uPassWord) {
		this.uPassWord = uPassWord;
	}
	public double getuMoney() {
		return uMoney;
	}
	public void setuMoney(double uMoney) {
		this.uMoney = uMoney;
	}
	public String getuPhone() {
		return uPhone;
	}
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	public String getuAddress() {
		return uAddress;
	}
	public void setuAddress(String uAddress) {
		this.uAddress = uAddress;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public int getuPostCode() {
		return uPostCode;
	}
	public void setuPostCode(int uPostCode) {
		this.uPostCode = uPostCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public User(String uNmae, String uPassWord, double uMoney, String uPhone,
			String uAddress, String uEmail, int uPostCode, String state) {
		super();
		this.uNmae = uNmae;
		this.uPassWord = uPassWord;
		this.uMoney = uMoney;
		this.uPhone = uPhone;
		this.uAddress = uAddress;
		this.uEmail = uEmail;
		this.uPostCode = uPostCode;
		this.state = state;
	}
	public User(){
		
	}
	@Override
	public String toString() {
		return "User [uId=" + uId + ", uNmae=" + uNmae + ", uPassWord="
				+ uPassWord + ", uMoney=" + uMoney + ", uPhone=" + uPhone
				+ ", uAddress=" + uAddress + ", uEmail=" + uEmail
				+ ", uPostCode=" + uPostCode + ", state=" + state + "]";
	}
	
	
	
	

}
