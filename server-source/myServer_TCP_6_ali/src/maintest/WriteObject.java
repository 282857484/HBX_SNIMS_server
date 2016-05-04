package maintest;



import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.Calendar;

public class WriteObject {

	private String ServerTimeStamp ;
	private SocketAddress socketaddress;
	public String getServerTimeStamp() {
		return ServerTimeStamp;
	}
	private String sendString;
	private boolean isMulticastaddress = false;
	private InetAddress multicastInetaddress;
	private int multicastPort = -1;
	
	
//	public WriteObject() 
//	{}
	public WriteObject(SocketAddress receivesocketaddress, String sendString2) {
		// TODO Auto-generated constructor stub
		this.socketaddress = receivesocketaddress;
		this.sendString = sendString2;
		this.ServerTimeStamp = Calendar.YEAR + "/" + Calendar.MONTH + "/" + Calendar.DAY_OF_MONTH + "/" + Calendar.HOUR_OF_DAY + "/" + Calendar.MILLISECOND;
	}
	
//	public String getSendIndex()
//	{
////		return this.ServerTimeStamp + "+" + this.socketaddress;
//	}
	
	public SocketAddress getSocketaddress() {
		return socketaddress;
	}
	public void setSocketaddress(SocketAddress socketaddress) {
		this.socketaddress = socketaddress;
	}
	
	public String getSendString() {
		return sendString;
	}
	public void setSendString(String sendString) {
		this.sendString = sendString;
	}
	public boolean isMulticastaddress() {
		return isMulticastaddress;
	}
	public void setMulticastaddress(boolean isMulticastaddress) {
		this.isMulticastaddress = isMulticastaddress;
	}
	public InetAddress getMulticastInetaddress() {
		return multicastInetaddress;
	}
	public void setMulticastInetaddress(InetAddress multicastInetaddress) {
		this.multicastInetaddress = multicastInetaddress;
	}
	public int getMulticastPort() {
		return multicastPort;
	}
	public void setMulticastPort(int multicastPort) {
		this.multicastPort = multicastPort;
	}
	
	
}
