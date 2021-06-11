package jeju.dto;

import java.util.Date;

public class DetailPlan {
	private int dpNo;
	private Date dpDate;
	private int pNo;
	
	@Override
	public String toString() {
		return "DetailPlan [dpNo=" + dpNo + ", dpDate=" + dpDate + ", pNo=" + pNo + "]";
	}

	public int getDpNo() {
		return dpNo;
	}

	public void setDpNo(int dpNo) {
		this.dpNo = dpNo;
	}

	public Date getDpDate() {
		return dpDate;
	}

	public void setDpDate(Date dpDate) {
		this.dpDate = dpDate;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	
	
}
