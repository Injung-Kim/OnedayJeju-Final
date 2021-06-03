package jeju.dto;

public class PlaceDetail {
	private String contentid;
	private String contenttypeid;
	private String homepage;
	private String title;
	private String overview;
	
	@Override
	public String toString() {
		return "PlaceDetail [contentid=" + contentid + ", contenttypeid=" + contenttypeid + ", homepage=" + homepage
				+ ", title=" + title + ", overview=" + overview + "]";
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

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	
}
