package log;

import org.apache.log4j.Level;

/**
 * <b>通用日志包把消息分为6个级别：FATAL、ERROR、WARN、INFO、DEBUG和TRACE ,级别依次下降</b>
 * @Title:    Log4jLevel.java
 * @ClassName: Log4jLevel  
 * @Description: TODO(日志级别)
 * @author:   (ZhouXiaoyu)
 * @version:  V1.0  
 * @Date:     2012-11-1 下午04:58:50  <BR>
 * @版权：   版权所有(C)2012<BR>
 * @公司：   FUCK
* @知识交流群 133436255 欢迎大家加入
 */
public class Log4jLevel {
	//级别依次下降
	/**关闭*/
	public static Level OFF = Level.OFF;
	/**致命的错误*/
	public static Level FATAL = Level.FATAL;
	/**错误*/
	public static Level ERROR = Level.ERROR;
	/**警告*/
	public static Level WARN = Level.WARN;
	/**消息*/
	public static Level INFO = Level.INFO;
	/**调试*/
	public static Level DEBUG = Level.TRACE;
	/**痕迹*/
	public static Level TRACE = Level.TRACE;
	/**所有的*/
	public static Level ALL = Level.ALL;
	//注：只有当输出日志的级别大于或等于为日志配置器配置的日志级别时，这个方法才会执行。
}
