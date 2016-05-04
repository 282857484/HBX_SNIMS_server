package datasouce;

public class DBCarLoc {
	private long carlocid;
	private long carid;
	private long carmasterid;
	private long uploadtime;
	private String latitude;
	private String logitude;
	private String title;
	private String tags;
	
	
	
	public DBCarLoc(long carlocid, long carid, long carmasterid,
			long uploadtime, String latitude, String logitude, String title,
			String tags) {
		super();
		this.carlocid = carlocid;
		this.carid = carid;
		this.carmasterid = carmasterid;
		this.uploadtime = uploadtime;
		this.latitude = latitude;
		this.logitude = logitude;
		this.title = title;
		this.tags = tags;
	}
	public long getCarlocid() {
		return carlocid;
	}
	public void setCarlocid(long carlocid) {
		this.carlocid = carlocid;
	}
	public long getCarid() {
		return carid;
	}
	public void setCarid(long carid) {
		this.carid = carid;
	}
	public long getCarmasterid() {
		return carmasterid;
	}
	public void setCarmasterid(long carmasterid) {
		this.carmasterid = carmasterid;
	}
	public long getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(long uploadtime) {
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
	
	

}
