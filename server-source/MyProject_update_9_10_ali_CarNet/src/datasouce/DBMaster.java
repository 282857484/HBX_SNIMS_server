package datasouce;

public class DBMaster {
	private long carmasterid;
	private long uploadtime;
	private String mastername;
	private String masterlevel;
	private String masterage;
	private String masteridentification;
	
	public DBMaster(long carmasterid, long uploadtime, String mastername,
			String masterlevel, String masterage, String masteridentification) {
		super();
		this.carmasterid = carmasterid;
		this.uploadtime = uploadtime;
		this.mastername = mastername;
		this.masterlevel = masterlevel;
		this.masterage = masterage;
		this.masteridentification = masteridentification;
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
	public String getMastername() {
		return mastername;
	}
	public void setMastername(String mastername) {
		this.mastername = mastername;
	}
	public String getMasterlevel() {
		return masterlevel;
	}
	public void setMasterlevel(String masterlevel) {
		this.masterlevel = masterlevel;
	}
	public String getMasterage() {
		return masterage;
	}
	public void setMasterage(String masterage) {
		this.masterage = masterage;
	}
	public String getMasteridentification() {
		return masteridentification;
	}
	public void setMasteridentification(String masteridentification) {
		this.masteridentification = masteridentification;
	}
	
}
