package jeju.dto;

public class Dppm {
	private int dpNo;
	private String contentid;
	private int order;
	
	@Override
	public String toString() {
		return "Dppm [dpNo=" + dpNo + ", contentid=" + contentid + ", order=" + order + "]";
	}
	
	public int getDpNo() {
		return dpNo;
	}
	public void setDpNo(int dpNo) {
		this.dpNo = dpNo;
	}
	public String getContentid() {
		return contentid;
	}
	public void setContentid(String contentid) {
		this.contentid = contentid;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

	
}
