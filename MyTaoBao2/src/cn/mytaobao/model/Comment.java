package cn.mytaobao.model;

public class Comment {
	private String note;
	private String name;
	private String time;
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Comment() {
		super();
	}
	public Comment(String note, String name, String time) {
		super();
		this.note = note;
		this.name = name;
		this.time = time;
	}
	

}
