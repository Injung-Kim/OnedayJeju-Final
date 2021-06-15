package jeju.dto.qna;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Answer {
	private int ansNo;
	private int qstNo;
	private int userNo;
	private String ansContent;
	private Date ansTime;
	private int ansLike;
	private String userId;
	private String userOrigin;
	
	List<HashMap<String, Object>> filenames;

	private int ansLikeCnt; //답글의 총 좋아요 수
	private int ansLikeCheck; //로그인아이디-답글의 좋아요 여부
	
	@Override
	public String toString() {
		return "Answer [ansNo=" + ansNo + ", qstNo=" + qstNo + ", userNo=" + userNo + ", ansContent=" + ansContent
				+ ", ansTime=" + ansTime + ", ansLike=" + ansLike + ", userId=" + userId + ", userOrigin=" + userOrigin
				+ ", filenames=" + filenames + ", ansLikeCnt=" + ansLikeCnt + ", ansLikeCheck=" + ansLikeCheck + "]";
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserOrigin() {
		return userOrigin;
	}
	public void setUserOrigin(String userOrigin) {
		this.userOrigin = userOrigin;
	}
	public List<HashMap<String, Object>> getFilenames() {
		return filenames;
	}
	public void setFilenames(List<HashMap<String, Object>> filenames) {
		this.filenames = filenames;
	}
	public int getAnsLikeCnt() {
		return ansLikeCnt;
	}
	public void setAnsLikeCnt(int ansLikeCnt) {
		this.ansLikeCnt = ansLikeCnt;
	}
	public int getAnsLikeCheck() {
		return ansLikeCheck;
	}
	public void setAnsLikeCheck(int ansLikeCheck) {
		this.ansLikeCheck = ansLikeCheck;
	}
}
