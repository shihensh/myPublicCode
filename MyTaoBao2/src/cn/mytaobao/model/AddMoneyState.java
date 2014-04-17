package cn.mytaobao.model;
/**
 * ³äÖµ×´Ì¬
 * @author By Jiang
 *
 */
public class AddMoneyState {
	private int id;
	private String state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public AddMoneyState(){
		
	}
	public AddMoneyState(String state) {
		super();
		this.state = state;
	}
	@Override
	public String toString() {
		return "AddMoneyState [id=" + id + ", state=" + state + "]";
	}
	
	

}
