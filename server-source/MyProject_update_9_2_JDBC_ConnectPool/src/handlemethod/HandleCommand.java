package handlemethod;


/**
 * 客户端发来的信息解析完后
 * 对相应的信息进行处理
 */
public class HandleCommand{
	
	public H_Founction h;
	public H_Founction_Pusher hPusher;
	
	public HandleCommand(){
		h = new H_Founction();
		hPusher = new H_Founction_Pusher();
	}
	
}

