package jeju.dto;

import java.util.Date;

public class PBComment {
	private int pbcNo;
	private String pbcContent;
	private Date pbcCDate;
	private int pbNo;
	private int userNo;
	private String userNick;
	
	@Override
	public String toString() {
		return "PBComment [pbcNo=" + pbcNo + ", pbcContent=" + pbcContent + ", pbcCDate=" + pbcCDate + ", pbNo=" + pbNo
				+ ", userNo=" + userNo + ", userNick=" + userNick + "]";
	}

	public int getPbcNo() {
		return pbcNo;
	}

	public void setPbcNo(int pbcNo) {
		this.pbcNo = pbcNo;
	}

	public String getPbcContent() {
		return pbcContent;
	}

	public void setPbcContent(String pbcContent) {
		this.pbcContent = pbcContent;
	}

	public Date getPbcCDate() {
		return pbcCDate;
	}

	public void setPbcCDate(Date pbcCDate) {
		this.pbcCDate = pbcCDate;
	}

	public int getPbNo() {
		return pbNo;
	}

	public void setPbNo(int pbNo) {
		this.pbNo = pbNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	
	
	
	
}
