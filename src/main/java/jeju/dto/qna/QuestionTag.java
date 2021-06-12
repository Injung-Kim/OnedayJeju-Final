package jeju.dto.qna;

public class QuestionTag {
	private int qutaNo;
	private int qstNo;
	private int tagNo;
	@Override
	public String toString() {
		return "QuestionTag [qutaNo=" + qutaNo + ", qstNo=" + qstNo + ", tagNo=" + tagNo + "]";
	}
	public int getQutaNo() {
		return qutaNo;
	}
	public void setQutaNo(int qutaNo) {
		this.qutaNo = qutaNo;
	}
	public int getQstNo() {
		return qstNo;
	}
	public void setQstNo(int qstNo) {
		this.qstNo = qstNo;
	}
	public int getTagNo() {
		return tagNo;
	}
	public void setTagNo(int tagNo) {
		this.tagNo = tagNo;
	}
	
	
	
}
