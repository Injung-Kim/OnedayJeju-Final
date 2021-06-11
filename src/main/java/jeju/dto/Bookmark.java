package jeju.dto;

public class Bookmark {
	private int userNo;
	private int pbNo;
	private int bm_status;
	
	@Override
	public String toString() {
		return "Bookmark [userNo=" + userNo + ", pbNo=" + pbNo + ", bm_status=" + bm_status + "]";
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getPbNo() {
		return pbNo;
	}

	public void setPbNo(int pbNo) {
		this.pbNo = pbNo;
	}

	public int getBm_status() {
		return bm_status;
	}

	public void setBm_status(int bm_status) {
		this.bm_status = bm_status;
	}
	
	
}
