package jeju.dto.qna;

public class FileTB {
	private int fileNo;
	private int qstNo;
	private int ansNo;
	private String origin;
	private String stored;
	@Override
	public String toString() {
		return "File [fileNo=" + fileNo + ", qstNo=" + qstNo + ", ansNo=" + ansNo + ", origin=" + origin + ", stored="
				+ stored + "]";
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getQstNo() {
		return qstNo;
	}
	public void setQstNo(int qstNo) {
		this.qstNo = qstNo;
	}
	public int getAnsNo() {
		return ansNo;
	}
	public void setAnsNo(int ansNo) {
		this.ansNo = ansNo;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getStored() {
		return stored;
	}
	public void setStored(String stored) {
		this.stored = stored;
	}
	
	
	
}
