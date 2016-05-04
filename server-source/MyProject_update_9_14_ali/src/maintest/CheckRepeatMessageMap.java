package maintest;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TimerTask;

import maintest.config.MainServerConfig;

import org.apache.log4j.Logger;

import tool.FormatTime;

public class CheckRepeatMessageMap extends TimerTask {

	public static Logger logger = Logger.getLogger(CheckRepeatMessageMap.class
			.getName());

	@Override
	public void run() {
		int count = 0;
		String serverTimeStamp = FormatTime.getFormatTime();
		
		Iterator<Entry<String, KeyObject>> iter = server_version_2.repeatMessageMap.entrySet().iterator();
		Entry<String , KeyObject> entry;
		while(iter.hasNext()) {
			entry = iter.next();
			String clientTimeStamp = entry.getKey();
			long sts = (Long.valueOf(serverTimeStamp));
			long cts = (Long.valueOf(clientTimeStamp));
			
			long timeInterval = sts - cts;
			
			if(timeInterval > MainServerConfig.MsgMinSaveTimeout) {
				server_version_2.repeatMessageMap.remove(clientTimeStamp);
				count ++;
			}
		}
		if(count > MainServerConfig.LogDeleteMsgMinNumber)
			logger.info("repeatMessageMap delete udp msg number " + count);
//		System.out.println("超时检测终端,会将重新启动");
	}

}
