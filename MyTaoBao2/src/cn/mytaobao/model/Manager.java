package cn.mytaobao.model;
/**
 * π‹¿Ì‘±
 * @author By Jiang
 *
 */
public class Manager {
	private int id;
	private String mName;
	private String mPassWord;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmPassWord() {
		return mPassWord;
	}
	public void setmPassWord(String mPassWord) {
		this.mPassWord = mPassWord;
	}
	public Manager(){
		
	}
	public Manager(String mName, String mPassWord) {
		super();
		this.mName = mName;
		this.mPassWord = mPassWord;
	}
	@Override
	public String toString() {
		return "Manager [id=" + id + ", mName=" + mName + ", mPassWord="
				+ mPassWord + "]";
	}
	
	

}
