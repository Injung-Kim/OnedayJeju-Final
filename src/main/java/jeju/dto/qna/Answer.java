package jeju.dto.qna;

import java.util.Date;

public class Answer {
	private int ansNo;
	private int qstNo;
	private int userNo;
	private String ansContent;
	private Date ansTime;
	private int ansLike;
	
	@Override
	public String toString() {
		return "Answer [ansNo=" + ansNo + ", qstNo=" + qstNo + ", userNo=" + userNo + ", ansContent=" + ansContent
				+ ", ansTime=" + ansTime + ", ansLike=" + ansLike + "]";
	}
	public int getAnsNo() {
		return ansNo;
	}
	public void setAnsNo(int ansNo) {
		this.ansNo = ansNo;
	}
	public int getQstNo() {
		return qstNo;
	}
	public void setQstNo(int qstNo) {
		this.qstNo = qstNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getAnsContent() {
		return ansContent;
	}
	public void setAnsContent(String ansContent) {
		this.ansContent = ansContent;
	}
	public Date getAnsTime() {
		return ansTime;
	}
	public void setAnsTime(Date ansTime) {
		this.ansTime = ansTime;
	}
	public int getAnsLike() {
		return ansLike;
	}
	public void setAnsLike(int ansLike) {
		this.ansLike = ansLike;
	}
	
	
}
