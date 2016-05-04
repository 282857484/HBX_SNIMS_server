package protocol.infoclass;

public class addMaster_C {

	private int p = h_procotol.addMaster_C;
//	carmasterid
	private String uploadtime = "0";
	private String mastername = "0";
	private String masterlevel = "0";
	private String masterage = "0";
	private String masteridentification = "0";
	
	
	
	

	public addMaster_C(String uploadtime, String mastername,
			String masterlevel, String masterage, String masteridentification) {
		super();
		this.uploadtime = uploadtime;
		this.mastername = mastername;
		this.masterlevel = masterlevel;
		this.masterage = masterage;
		this.masteridentification = masteridentification;
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
	public int getP() {
		return p;
	}
	public String getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(String uploadtime) {
		this.uploadtime = uploadtime;
	}
	
	

}
