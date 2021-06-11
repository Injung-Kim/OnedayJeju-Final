package jeju.dto;

public class PlanLike {
	private int userNo;
	private int pbNo;
	private int plStatus;
	
	@Override
	public String toString() {
		return "PlanLike [userNo=" + userNo + ", pbNo=" + pbNo + ", plStatus=" + plStatus + "]";
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

	public int getPlStatus() {
		return plStatus;
	}

	public void setPlStatus(int plStatus) {
		this.plStatus = plStatus;
	}
	
	
}
