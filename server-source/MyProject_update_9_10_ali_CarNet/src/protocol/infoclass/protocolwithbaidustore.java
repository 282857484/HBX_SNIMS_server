package protocol.infoclass;


public interface protocolwithbaidustore {
	final String strkey = "juykpDTR22aTsAXGQTWopeyn";
	
	/**
	 * 1.GPS经纬度坐标
	 */
	final static int GPSLL = 1;
	/**
	 * 2.国测局加密经纬度坐标
	 */
	final static int GPSNLL = 2;
	/**
	 * 3.百度加密经纬度坐标
	 */
	final static int BAIDULL = 3;
	/**
	 * 4.百度加密墨卡托坐标
	 */
	final static int BAIDUMLL = 4;
	
	/**
	 * 百度数据库
	 */
	final static int CreateCarLocation = 78896;
	final static int UpdateCarLocation = 88896;
	final static int DeleteCarLocation = 98896;
	
}
