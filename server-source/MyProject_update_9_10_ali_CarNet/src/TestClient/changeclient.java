package TestClient;

//package maintest;
//java测试客户端（非android）
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class changeclient {
	private static final String server_address = "127.0.0.1";
	private static final int TIMEOUT = 3000; // Resend timeout (milliseconds)
	private static final int MAXTRIES = 5; // Maximum retransmissions
	private static final int servPort = 12789;

	/**
	 * 注意事项: 不能包含特殊字符 ' " \
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		InetAddress serverAddress = InetAddress.getByName(server_address); // server
																			// address

		Test_Info TI = new Test_Info();

//		String testText = TI.Test_addMaster();
//		String testText = TI.Test_addCar();
		String testText = TI.Test_addCarLoc();

		byte[] bytesToSend = testText.getBytes();
		DatagramSocket socket = new DatagramSocket();
		socket.setSoTimeout(TIMEOUT);
		DatagramPacket sendPacket = new DatagramPacket(bytesToSend,
				bytesToSend.length, serverAddress, servPort);
		DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);

		// Map<String, Object> retMap = new LinkedHashMap<String, Object>();
		int tries = 0;
		boolean receivedResponse = false;

		socket.send(sendPacket);
		do {
			try {

				socket.receive(receivePacket);
				if (!receivePacket.getAddress().equals(serverAddress)) {
					throw new IOException(
							"Received packet from an unknown source");
				}
				receivedResponse = true;
				String sss = new String(receivePacket.getData(), 0,
						receivePacket.getData().length);

				// 去掉空格！！！！非常重要！！！！
				testText = sss.trim();
				System.out.println("*********" + testText);

			} catch (InterruptedIOException e) {
				tries += 1;
				System.out.println("Time out, " + (MAXTRIES - tries)
						+ "more tries...");
			}

		}
		// while((!receivedResponse) && (tries < MAXTRIES));
		while ((tries < MAXTRIES));

		if (receivedResponse) {
			System.out.println("Received: "
					+ new String(receivePacket.getData(), 0, receivePacket
							.getLength()));
		} else {
			System.out.println("No response -- giving up.");
		}
		socket.close();

	}

}
