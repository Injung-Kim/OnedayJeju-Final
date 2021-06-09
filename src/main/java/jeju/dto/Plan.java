package jeju.dto;

import java.util.Date;

public class Plan {
	private int pNo;
	private String pTitle;
	private Date pDepartureDate;
	private int pNumDays;
	private int pNumPeople;
	private Date pCDate;
	private int userNo;
	
	@Override
	public String toString() {
		return "Plan [pNo=" + pNo + ", pTitle=" + pTitle + ", pDepartureDate=" + pDepartureDate + ", pNumDays="
				+ pNumDays + ", pNumPeople=" + pNumPeople + ", pCDate=" + pCDate + ", userNo=" + userNo + "]";
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public Date getpDepartureDate() {
		return pDepartureDate;
	}

	public void setpDepartureDate(Date pDepartureDate) {
		this.pDepartureDate = pDepartureDate;
	}

	public int getpNumDays() {
		return pNumDays;
	}

	public void setpNumDays(int pNumDays) {
		this.pNumDays = pNumDays;
	}

	public int getpNumPeople() {
		return pNumPeople;
	}

	public void setpNumPeople(int pNumPeople) {
		this.pNumPeople = pNumPeople;
	}

	public Date getpCDate() {
		return pCDate;
	}

	public void setpCDate(Date pCDate) {
		this.pCDate = pCDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	
}
