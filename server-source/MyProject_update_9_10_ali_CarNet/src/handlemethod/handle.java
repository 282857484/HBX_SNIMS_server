package handlemethod;

import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Queue;

import protocol.infoclass.h_procotol;
import maintest.KeyObject;
import maintest.ReadObject;
import maintest.WriteObject;
import maintest.server_version_2;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.gson.Gson;

public class handle implements Runnable {

	// 转换Json格式
	public static Gson g = new Gson();

	public SocketAddress receivesocketaddress;
	// 从receivedQueue中取出的String
	String receiveString;
	// 直接获取发送队列
	Queue<WriteObject> sendQueue;

	// 发送的消息
	String sendString;

	// 对象列表

	WriteObject wo;

	HandleCommand abc;

	String timeStamp;

	public handle(ReadObject ro, Queue<WriteObject> qwo,
			HandleCommand handleCenter) {
		this.receivesocketaddress = ro.getSocketaddress();
		this.receiveString = ro.getReceiveString();
		this.sendQueue = qwo;
		this.abc = handleCenter;
	}

	@Override
	public void run() {
		// 8-20 从类成员中剥离到这边
		ArrayList<Object> objlist = new ArrayList<Object>();
		// 8-20 从类成员中剥离到这边
		KeyObject sa = new KeyObject();

		System.out.println("receivesocketaddress: " + receivesocketaddress
				+ "| receivestring: " + receiveString);
		if (!checkLegitimacy(receiveString)) {
			System.out.println("ReceiveString not available");
			return;
		}

		// 解析JSON列表
		JSONArray objList = JSONArray.fromObject(receiveString);

		// 得到对象数目
		int size = objList.size();

		for (int i = 0; i < size; i++) {
			JSONObject obj = objList.getJSONObject(i);
			int p = obj.getInt("p");

			// 放入表,主要防止重复信息重复处理
			timeStamp = obj.getString("uploadtime");
			if (server_version_2.repeatMessageMap.containsKey(timeStamp)) { // 可能有重复信息
				sa = server_version_2.repeatMessageMap.get(timeStamp);

				if (sa.receivesocketaddress.equals(receivesocketaddress)) { // 重复,查表重发
					System.out.println("message repeat!");
					wo = new WriteObject(sa.receivesocketaddress,
							sa.sendMessage);
					// 处理与判定
					sendQueue.offer(wo);
					return;
				} else { // ip数据不重复,只是相同的时间戳(还未解决, 可通过设置id + timestamp解决)
					// continue,客户端是否会更换
					sa.receivesocketaddress = receivesocketaddress;
					server_version_2.repeatMessageMap.put(timeStamp, sa);
				}
			} else { // 数据不重复,
				sa.receivesocketaddress = receivesocketaddress;
				// server_version_2.repeatMessageMap.put(timeStamp,sa);
			}

			try {
				switch (p) {
				/**
				 * 这里是侯斌的协议
				 */
				case h_procotol.addMaster_C:
					H_Founction.addMaster_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_procotol.addCar_C:
					H_Founction.addCar_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
				case h_procotol.addCarLocation_C:
					H_Founction.addCarLocation_C_Deal(obj, objlist,
							receivesocketaddress);
					break;
//				case h_procotol.:
//					H_Founction.;
//					break;

//				case h_protocol_pusher.REQ_Heart_Beat:
//					H_Founction_Pusher.REQ_Heart_Beat_Deal(obj, objlist,
//							receivesocketaddress);
//					return;
//				case h_protocol_pusher.ACK:
//					H_Founction_Pusher.ACK_Deal(obj, objlist,
//							receivesocketaddress);
//					return;

					/**
					 * 其他
					 */
				default:
					System.out.println("unknown packet！");

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (objlist.isEmpty()) {
			System.out.println("EMPTY");
			return;
		}

		sendString = g.toJson(objlist);

		// 存储,防止重复
		sa.sendMessage = sendString;
		server_version_2.repeatMessageMap.put(timeStamp, sa);

		wo = new WriteObject(receivesocketaddress, sendString);
		// 处理与判定
		server_version_2.sendQueue.offer(wo);
		if(server_version_2.sendQueue.isEmpty()) {
			System.out.println("FUCK EMPTY");
		}
		// key在下一次循环中被select选中！

	}

	//
	private static boolean checkLegitimacy(String receiveString) {
		// TODO Auto-generated method stub
		if (receiveString.length() < 20) {
			return false;
		}
//		if ((!receiveString.contains("UserID"))
//				&& (!receiveString.contains("UploadTime"))) {
//			return false;
//		}
		if (receiveString.contains("\"uploadtime\":\"0\"")) {
			return false;
		}

		return true;
	}
}
