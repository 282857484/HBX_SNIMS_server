package maintest;




import java.net.SocketAddress;

public class ReadObject {
	private SocketAddress socketaddress;
	private String receiveString;
	
	
	public ReadObject(SocketAddress clientAddress , String receiveString)
	{
		this.socketaddress = clientAddress;
		this.receiveString = receiveString;
	}
	
	public SocketAddress getSocketaddress() {
		return socketaddress;
	}
	public void setSocketaddress(SocketAddress socketaddress) {
		this.socketaddress = socketaddress;
	}
	
	public String getReceiveString() {
		return receiveString;
	}
	public void setReceiveString(String receiveString) {
		this.receiveString = receiveString;
	}

	
	

}
