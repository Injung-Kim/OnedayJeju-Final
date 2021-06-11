package jeju.dto.qna;

public class Hashtag {
	private int tagNo;
	private String tagName;
	@Override
	public String toString() {
		return "Hashtag [tagNo=" + tagNo + ", tagName=" + tagName + "]";
	}
	public int getTagNo() {
		return tagNo;
	}
	public void setTagNo(int tagNo) {
		this.tagNo = tagNo;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	
}
