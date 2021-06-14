package jeju.dto;

public class NtcReport {
	private String userId;
	private int ntcNo;
	private String ntcrContent;
	@Override
	public String toString() {
		return "NtcReport [userId=" + userId + ", ntcNo=" + ntcNo + ", ntcrContent=" + ntcrContent + "]";
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
	public String getNtcrContent() {
		return ntcrContent;
	}
	public void setNtcrContent(String ntcrContent) {
		this.ntcrContent = ntcrContent;
	}

	
}
