package jeju.dto.qna;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jeju.dto.JejuUser;

public class Question {
	private int qstNo;
	private String title;
	private int userNo;
	private String qstContent;
	private Date qstTime;
	private int qstCnt;
	private String userId;
	
	List<HashMap<String, Object>> qtags;

	@Override
	public String toString() {
		return "Question [qstNo=" + qstNo + ", title=" + title + ", userNo=" + userNo + ", qstContent=" + qstContent
				+ ", qstTime=" + qstTime + ", qstCnt=" + qstCnt + ", userId=" + userId + ", qtags=" + qtags + "]";
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

	public void setQstTime(Date qstTime) {
		this.qstTime = qstTime;
	}

	public int getQstCnt() {
		return qstCnt;
	}

	public void setQstCnt(int qstCnt) {
		this.qstCnt = qstCnt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<HashMap<String, Object>> getQtags() {
		return qtags;
	}

	public void setQtags(List<HashMap<String, Object>> qtags) {
		this.qtags = qtags;
	}
	

}
