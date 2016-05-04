package protocol.infoclass;

public class addCar_C {
	private int p = h_procotol.addCar_C;
//	private String carid = "0";
	private String carmasterid = "0";
	private String uploadtime = "0";
	private String cartype = "0";
	private String cardevice = "0";
	private String carprice = "0";
	
	
	
	public addCar_C(String carmasterid, String uploadtime, String cartype,
			String cardevice, String carprice) {
		super();
		this.carmasterid = carmasterid;
		this.uploadtime = uploadtime;
		this.cartype = cartype;
		this.cardevice = cardevice;
		this.carprice = carprice;
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
	public int getP() {
		return p;
	}
	
	

}
