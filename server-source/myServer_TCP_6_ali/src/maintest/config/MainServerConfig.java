package maintest.config;



public interface MainServerConfig {

	/**
	 * @X 服务器log4j配置文件路径
	 * @X src/resource/log4j.properties
	 * @X d:/server/log4j.properties
	 */
	String log4jpropertiesPath = "d:/server/log4j.properties";
//	String log4jpropertiesPath = "src/resource/log4j.properties";
	
	
	/**
	 * @X 选择器选择间隔时间
	 */
	int UDPSocketSelectTimeOut = 200;

	/**
	 * @X UDP主服务socket端口号
	 */
	int MainServerSocketPort = 12345;

	/**
	 * UDP主服务器socket线程池基数-根据核数来判定开启线程数
	 */
	int MainServerThreadPoolSize = 100;

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

	/**
	 * udp消息存在于词典中的时间最小时间,超过此值就很可能被删掉
	 */
	long MsgMinSaveTimeout = 60000;

	/**
	 * 记录删除Msg的最小数目
	 */
	int LogDeleteMsgMinNumber = 0;

	/**
	 * 记录推送Pusher的最小数目
	 */
	int LogDeletePushCount = 0;


	int ChannelBufferSize = 1024 * 64;


	int TCPSocketSelectTimeOut = 0;
	

	
}
