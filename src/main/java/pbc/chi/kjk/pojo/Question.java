package pbc.chi.kjk.pojo;

public class Question {

	private int que_id;
	private int que_num;
	private int backtime;
	private String que_title;
	private String que_answer;
	private int flag;
	private int status;
	private String fenzhi;
		
	public Question() {
		super();
		
	}

	public Question(int que_id, int que_num, int backtime, String que_title,
			String que_answer, int flag, int status, String fenzhi) {
		super();
		this.que_id = que_id;
		this.que_num = que_num;
		this.backtime = backtime;
		this.que_title = que_title;
		this.que_answer = que_answer;
		this.flag = flag;
		this.status = status;
		this.fenzhi = fenzhi;
	}
	
	public int getQue_id() {
		return que_id;
	}
	public void setQue_id(int que_id) {
		this.que_id = que_id;
	}
	public int getQue_num() {
		return que_num;
	}
	public void setQue_num(int que_num) {
		this.que_num = que_num;
	}
	public int getBacktime() {
		return backtime;
	}
	public void setBacktime(int backtime) {
		this.backtime = backtime;
	}
	public String getQue_title() {
		return que_title;
	}
	public void setQue_title(String que_title) {
		this.que_title = que_title;
	}
	
	public String getQue_answer() {
		return que_answer;
	}
	public void setQue_answer(String que_answer) {
		this.que_answer = que_answer;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getFenzhi() {
		return fenzhi;
	}
	public void setFenzhi(String fenzhi) {
		this.fenzhi = fenzhi;
	}
	
	
}
