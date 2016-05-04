package datasouce;

public class DBCarInfo {
	private long carid;
	private long carmasterid;
	private long uploadtime;
	private String cartype;
	private String cardevice;
	private String carprice;
	
	
	
	public DBCarInfo(long carid, long carmasterid, long uploadtime,
			String cartype, String cardevice, String carprice) {
		super();
		this.carid = carid;
		this.carmasterid = carmasterid;
		this.uploadtime = uploadtime;
		this.cartype = cartype;
		this.cardevice = cardevice;
		this.carprice = carprice;
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
	public String getCartype() {
		return cartype;
	}
	public void setCartype(String cartype) {
		this.cartype = cartype;
	}
	public String getCardevice() {
		return cardevice;
	}
	public void setCardevice(String cardevice) {
		this.cardevice = cardevice;
	}
	public String getCarprice() {
		return carprice;
	}
	public void setCarprice(String carprice) {
		this.carprice = carprice;
	}

	
	
}
