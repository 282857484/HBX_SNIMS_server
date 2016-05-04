package protocol.infoclass;

public class addCarLocation_C {

	private int p = h_procotol.addCarLocation_C;
//	private String carlocid = "0";
	private String carid = "0";
	private String carmasterid = "0";
	private String uploadtime = "0";
	private String latitude = "0";
	private String logitude = "0";
	private String title = "0";
	private String tags = "0";
	
	
	
	public addCarLocation_C(String carid, String carmasterid,
			String uploadtime, String latitude, String logitude, String title,
			String tags) {
		super();
		this.carid = carid;
		this.carmasterid = carmasterid;
		this.uploadtime = uploadtime;
		this.latitude = latitude;
		this.logitude = logitude;
		this.title = title;
		this.tags = tags;
	}
	public String getCarid() {
		return carid;
	}
	public void setCarid(String carid) {
		this.carid = carid;
	}
	public String getCarmasterid() {
		return carmasterid;
	}
	public void setCarmasterid(String carmasterid) {
		this.carmasterid = carmasterid;
	}
	public String getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLogitude() {
		return logitude;
	}
	public void setLogitude(String logitude) {
		this.logitude = logitude;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public int getP() {
		return p;
	}
	
}
