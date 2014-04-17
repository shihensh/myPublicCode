package cn.mytaobao.model;
/**
 * 订单状态 ： 未发货，发货，送货中，确认到货
 * @author By Jiang
 *
 */
public class OrderState {
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
	public OrderState(){
		
	}
	public OrderState(String state) {
		super();
		this.state = state;
	}
	@Override
	public String toString() {
		return "OrderState [id=" + id + ", state=" + state + "]";
	}
	
	
	

}
