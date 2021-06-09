package jeju.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Expenses {
	private int expNo;
	private String expName;
	@DateTimeFormat(pattern="yyyy-MM-dd") private Date expDate;
	private int pNo;
	private int userNo;
	private String category;
	private int cost;
	private String memo;
	
	@Override
	public String toString() {
		return "Expenses [expNo=" + expNo + ", expName=" + expName + ", expDate=" + expDate + ", pNo=" + pNo
				+ ", userNo=" + userNo + ", category=" + category + ", cost=" + cost + ", memo=" + memo + "]";
	}

	public int getExpNo() {
		return expNo;
	}

	public void setExpNo(int expNo) {
		this.expNo = expNo;
	}

	public String getExpName() {
		return expName;
	}

	public void setExpName(String expName) {
		this.expName = expName;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
