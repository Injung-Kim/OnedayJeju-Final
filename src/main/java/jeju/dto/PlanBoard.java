package jeju.dto;

import java.util.Date;

public class PlanBoard {
	private int pbNo;
	private String pbFilename;
	private String pbTitle;
	private int pbViews;
	private Date pbCDate;
	private int pNo;
	private int userNo;
	
	@Override
	public String toString() {
		return "PlanBoard [pbNo=" + pbNo + ", pbFilename=" + pbFilename + ", pbTitle=" + pbTitle + ", pbViews="
				+ pbViews + ", pNo=" + pNo + ", userNo=" + userNo + "]";
	}
	
	public int getPbNo() {
		return pbNo;
	}
	public void setPbNo(int pbNo) {
		this.pbNo = pbNo;
	}
	public String getPbFilename() {
		return pbFilename;
	}
	public void setPbFilename(String pbFilename) {
		this.pbFilename = pbFilename;
	}
	public String getPbTitle() {
		return pbTitle;
	}
	public void setPbTitle(String pbTitle) {
		this.pbTitle = pbTitle;
	}
	public int getPbViews() {
		return pbViews;
	}
	public void setPbViews(int pbViews) {
		this.pbViews = pbViews;
	}
	public Date getPbCDate() {
		return pbCDate;
	}
	public void setPbCDate(Date pbCDate) {
		this.pbCDate = pbCDate;
	}
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
	
}
