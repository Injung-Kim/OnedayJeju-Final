package jeju.dto;

import java.util.Date;

public class NoticeComment {
	
	private int ntcNo;					// 댓글 번호
	private int ntNo;					// 공지글 번호
	private String ntcContent;			// 댓글 내용
	private String ntcId;				// 댓글 id
	private String ntcDelete;			// 댓글 삭제 여부
	private Date ntcCtime;				// 작성일
	private Date ntcUtime;				// 수정일
	   
	private int likeCnt;					// 좋아요 수
	private int disLikeCnt;				// 싫어요 수
	private int reportCnt;				// 신고 수
	@Override
	public String toString() {
		return "NoticeComment [ntcNo=" + ntcNo + ", ntNo=" + ntNo + ", ntcContent=" + ntcContent + ", ntcId=" + ntcId
				+ ", ntcDelete=" + ntcDelete + ", ntcCtime=" + ntcCtime + ", ntcUtime=" + ntcUtime + ", likeCnt="
				+ likeCnt + ", disLikeCnt=" + disLikeCnt + ", reportCnt=" + reportCnt + "]";
	}
	public int getNtcNo() {
		return ntcNo;
	}
	public void setNtcNo(int ntcNo) {
		this.ntcNo = ntcNo;
	}
	public int getNtNo() {
		return ntNo;
	}
	public void setNtNo(int ntNo) {
		this.ntNo = ntNo;
	}
	public String getNtcContent() {
		return ntcContent;
	}
	public void setNtcContent(String ntcContent) {
		this.ntcContent = ntcContent;
	}
	public String getNtcId() {
		return ntcId;
	}
	public void setNtcId(String ntcId) {
		this.ntcId = ntcId;
	}
	public String getNtcDelete() {
		return ntcDelete;
	}
	public void setNtcDelete(String ntcDelete) {
		this.ntcDelete = ntcDelete;
	}
	public Date getNtcCtime() {
		return ntcCtime;
	}
	public void setNtcCtime(Date ntcCtime) {
		this.ntcCtime = ntcCtime;
	}
	public Date getNtcUtime() {
		return ntcUtime;
	}
	public void setNtcUtime(Date ntcUtime) {
		this.ntcUtime = ntcUtime;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public int getDisLikeCnt() {
		return disLikeCnt;
	}
	public void setDisLikeCnt(int disLikeCnt) {
		this.disLikeCnt = disLikeCnt;
	}
	public int getReportCnt() {
		return reportCnt;
	}
	public void setReportCnt(int reportCnt) {
		this.reportCnt = reportCnt;
	}

	
   
   
}
