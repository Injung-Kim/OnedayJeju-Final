package jeju.dto;

import java.util.Date;

public class Notice {
	
	private int ntNo;
	private int userNo;
	private String ntTitle;
	private String ntContent;
	private String ntDiv;
	private Date ntCtime;
	private Date ntUtime;
	private int hit;
	
	private int fileCnt;

	@Override
	public String toString() {
		return "Notice [ntNo=" + ntNo + ", userNo=" + userNo + ", ntTitle=" + ntTitle + ", ntContent=" + ntContent
				+ ", ntDiv=" + ntDiv + ", ntCtime=" + ntCtime + ", ntUtime=" + ntUtime + ", hit=" + hit + ", fileCnt="
				+ fileCnt + "]";
	}

	public int getNtNo() {
		return ntNo;
	}

	public void setNtNo(int ntNo) {
		this.ntNo = ntNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getNtTitle() {
		return ntTitle;
	}

	public void setNtTitle(String ntTitle) {
		this.ntTitle = ntTitle;
	}

	public String getNtContent() {
		return ntContent;
	}

	public void setNtContent(String ntContent) {
		this.ntContent = ntContent;
	}

	public String getNtDiv() {
		return ntDiv;
	}

	public void setNtDiv(String ntDiv) {
		this.ntDiv = ntDiv;
	}

	public Date getNtCtime() {
		return ntCtime;
	}

	public void setNtCtime(Date ntCtime) {
		this.ntCtime = ntCtime;
	}

	public Date getNtUtime() {
		return ntUtime;
	}

	public void setNtUtime(Date ntUtime) {
		this.ntUtime = ntUtime;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getFileCnt() {
		return fileCnt;
	}

	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}
	

	
	

}
