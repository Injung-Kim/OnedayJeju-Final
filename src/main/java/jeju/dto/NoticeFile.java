package jeju.dto;

import java.util.Date;

public class NoticeFile {
	private int ntfNo;
	private int ntNo;
	private String ntfOgname;
	private String ntfStname;
	private Date ntfCtime;
	@Override
	public String toString() {
		return "NoticeFile [ntfNo=" + ntfNo + ", ntNo=" + ntNo + ", ntfOgname=" + ntfOgname + ", ntfStname=" + ntfStname
				+ ", ntfCtime=" + ntfCtime + "]";
	}
	public int getNtfNo() {
		return ntfNo;
	}
	public void setNtfNo(int ntfNo) {
		this.ntfNo = ntfNo;
	}
	public int getNtNo() {
		return ntNo;
	}
	public void setNtNo(int ntNo) {
		this.ntNo = ntNo;
	}
	public String getNtfOgname() {
		return ntfOgname;
	}
	public void setNtfOgname(String ntfOgname) {
		this.ntfOgname = ntfOgname;
	}
	public String getNtfStname() {
		return ntfStname;
	}
	public void setNtfStname(String ntfStname) {
		this.ntfStname = ntfStname;
	}
	public Date getNtfCtime() {
		return ntfCtime;
	}
	public void setNtfCtime(Date ntfCtime) {
		this.ntfCtime = ntfCtime;
	}
	

	
	
}
