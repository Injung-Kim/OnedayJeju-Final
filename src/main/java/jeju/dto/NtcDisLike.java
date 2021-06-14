package jeju.dto;

public class NtcDisLike {
	
	private String userId;
	private int ntcNo;
	@Override
	public String toString() {
		return "NtcDisLike [userId=" + userId + ", ntcNo=" + ntcNo + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getNtcNo() {
		return ntcNo;
	}
	public void setNtcNo(int ntcNo) {
		this.ntcNo = ntcNo;
	}
	
	

}
