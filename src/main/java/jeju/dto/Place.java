package jeju.dto;

public class Place {
	private String contentid;
	private String contenttypeid;
	private String addr1;
	private String mapx;
	private String mapy;
	private String tel;
	private String title;
	private String firstimage;
	private String firstimage2;
	
	@Override
	public String toString() {
		return "Place [contentid=" + contentid + ", contenttypeid=" + contenttypeid + ", addr1=" + addr1 + ", mapx="
				+ mapx + ", mapy=" + mapy + ", tel=" + tel + ", title=" + title + ", firstimage=" + firstimage
				+ ", firstimage2=" + firstimage2 + "]";
	}

	public String getContentid() {
		return contentid;
	}

	public void setContentid(String contentid) {
		this.contentid = contentid;
	}

	public String getContenttypeid() {
		return contenttypeid;
	}

	public void setContenttypeid(String contenttypeid) {
		this.contenttypeid = contenttypeid;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getMapx() {
		return mapx;
	}

	public void setMapx(String mapx) {
		this.mapx = mapx;
	}

	public String getMapy() {
		return mapy;
	}

	public void setMapy(String mapy) {
		this.mapy = mapy;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstimage() {
		return firstimage;
	}

	public void setFirstimage(String firstimage) {
		this.firstimage = firstimage;
	}

	public String getFirstimage2() {
		return firstimage2;
	}

	public void setFirstimage2(String firstimage2) {
		this.firstimage2 = firstimage2;
	}
	
	
}
