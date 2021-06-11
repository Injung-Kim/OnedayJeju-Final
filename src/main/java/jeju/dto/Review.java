package jeju.dto;

import java.sql.Date;

public class Review {
	private int rvNo;
	private int userNo;
	private String userId;
	private String rvTitle;
	private String rvContent;
	private Date rvCreateDate;
	private Date rvUpdDate;
	private int rvHit;
	private String rvLink;
	private int rvBudjet;
	@Override
	public String toString() {
		return "Review [rvNo=" + rvNo + ", userNo=" + userNo + ", userId=" + userId + ", rvTitle=" + rvTitle
				+ ", rvContent=" + rvContent + ", rvCreateDate=" + rvCreateDate + ", rvUpdDate=" + rvUpdDate
				+ ", rvHit=" + rvHit + ", rvLink=" + rvLink + ", rvBudjet=" + rvBudjet + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getRvNo() {
		return rvNo;
	}
	public void setRvNo(int rvNo) {
		this.rvNo = rvNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getRvTitle() {
		return rvTitle;
	}
	public void setRvTitle(String rvTitle) {
		this.rvTitle = rvTitle;
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
	public Date getRvUpdDate() {
		return rvUpdDate;
	}
	public void setRvUpdDate(Date rvUpdDate) {
		this.rvUpdDate = rvUpdDate;
	}
	public int getRvHit() {
		return rvHit;
	}
	public void setRvHit(int rvHit) {
		this.rvHit = rvHit;
	}
	public String getRvLink() {
		return rvLink;
	}
	public void setRvLink(String rvLink) {
		this.rvLink = rvLink;
	}
	public int getrvBudjet() {
		return rvBudjet;
	}
	public void setrvBudjet(int rvBudjet) {
		this.rvBudjet = rvBudjet;
	}
	
}
