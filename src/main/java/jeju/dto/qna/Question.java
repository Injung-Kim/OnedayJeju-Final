package jeju.dto.qna;

import java.util.Date;

public class Question {
	private int qstNo;
	private String title;
	private int userNo;
	private String qstContent;
	private Date qstTime;
	private int qstCnt;
	
	@Override
	public String toString() {
		return "qnaQuestion [qstNo=" + qstNo + ", title=" + title + ", userNo=" + userNo + ", qstContent=" + qstContent
				+ ", qst_time=" + qstTime + ", qstCnt=" + qstCnt + "]";
	}
	public int getQstNo() {
		return qstNo;
	}
	public void setQstNo(int qstNo) {
		this.qstNo = qstNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getQstContent() {
		return qstContent;
	}
	public void setQstContent(String qstContent) {
		this.qstContent = qstContent;
	}
	public Date getQstTime() {
		return qstTime;
	}
	public void setQst_time(Date qstTime) {
		this.qstTime = qstTime;
	}
	public int getQstCnt() {
		return qstCnt;
	}
	public void setQstCnt(int qstCnt) {
		this.qstCnt = qstCnt;
	}
	
	
}
