package handlemethod;

public interface Config {

	/**
	 * 最新发布版本
	 */
	int VersionNow = 2;
	
	/**
	 * 最新发布版本app下载链接
	 */
//	String VersionNowURL = "http://121.40.123.240:5984/_utils/image/se/xxx.apk";
	String VersionNowURL = "http://121.40.123.240:5984/_utils/client/LetsParty.apk";
	
	/**
	 * 最新版本发布时间
	 */
	String UpdateTime = "201408201200000";
	
	/**
	 * 最新发布版本的附加文本信息
	 */
	String Text = "此版本更新了许多bug";

	int BufferSize = 1024 * 64;
	
}
