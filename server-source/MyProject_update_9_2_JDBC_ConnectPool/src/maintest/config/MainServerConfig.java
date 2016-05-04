package maintest.config;



public interface MainServerConfig {

	/**
	 * @X 服务器log4j配置文件路径
	 * @X src/resource/log4j.properties
	 * @X d:/myconfig/log4j.properties
	 */
	String log4jpropertiesPath = "src/resource/log4j.properties";
	
	/**
	 * @X 选择器选择间隔时间
	 */
	int UDPSocketSelectTimeOut = 100;

	/**
	 * @X UDP主服务socket端口号
	 */
	int MainServerSocketPort = 12345;

	/**
	 * UDP主服务器socket线程池基数-根据核数来判定开启线程数
	 */
	int MainServerThreadPoolSize = 4;

	/**
	 * 检查重复信息从开始的延迟
	 */
	int CheckRepeatDelay = 60000;

	/**
	 * 检查重复信息周期
	 */
	long CheckRepeatPeriod = 60000;

	/**
	 * 推送信息线程从开始的延时
	 */
	int PushMessageDelay = 1000;

	/**
	 * 推送信息线程周期
	 */
	long PushMessagePeriod = 1000;
	

	
}
