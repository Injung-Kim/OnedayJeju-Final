package jeju.dto;

import java.sql.Date;

public class RvComment {
	private int rvNo;
	private int rvCmtNo;
	private String userId;
	private String rvContent;
	private Date rvCreateDate;
	@Override
	public String toString() {
		return "RvComment [rvNo=" + rvNo + ", rvCmtNo=" + rvCmtNo + ", userId=" + userId + ", rvContent=" + rvContent
				+ ", rvCreateDate=" + rvCreateDate + "]";
	}
	public int getRvNo() {
		return rvNo;
	}
	public void setRvNo(int rvNo) {
		this.rvNo = rvNo;
	}
	public int getRvCmtNo() {
		return rvCmtNo;
	}
	public void setRvCmtNo(int rvCmtNo) {
		this.rvCmtNo = rvCmtNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRvContent() {
		return rvContent;
	}
	public void setRvContent(String rvContent) {
		this.rvContent = rvContent;
	}
	public Date getRvCreateDate() {
		return rvCreateDate;
	}
	public void setRvCreateDate(Date rvCreateDate) {
		this.rvCreateDate = rvCreateDate;
	}
	
	
	
	
}
