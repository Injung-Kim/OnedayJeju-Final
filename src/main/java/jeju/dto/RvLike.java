package jeju.dto;

public class RvLike {

	private int userNo;
	private int rvNo;
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getRvNo() {
		return rvNo;
	}
	public void setRvNo(int rvNo) {
		this.rvNo = rvNo;
	}
	@Override
	public String toString() {
		return "RvLike [userNo=" + userNo + ", rvNo=" + rvNo + "]";
	}

	
	
}
