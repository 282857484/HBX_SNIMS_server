package handlemethod;

import httptransfer.creatPoi;
import httptransfer.deletePoi;
import httptransfer.httpManager;
import httptransfer.updatePoi;

import java.util.Vector;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import protocol.infoclass.protocolwithbaidustore;




public class handleBaiduCloudDataThread implements Runnable {

	httpManager httpmanager = new httpManager();
	
	private int what = 0;
	private Object obj = null;
//	private int arg1 = 0;
//	private int arg2 = 0;

	// ...

	public handleBaiduCloudDataThread(int what, Object obj, 
			int arg1, int arg2) {
		// TODO Auto-generated constructor stub
		this.what = what;
		this.obj = obj;
//		this.arg1 = arg1;
//		this.arg2 = arg2;
	}

	public void run() {
		// TODO Auto-generated method stub
		switch (what) {
		// creat
		case protocolwithbaidustore.baidutableactivityPOI:
		case protocolwithbaidustore.baidutableactivityRoute:
		case protocolwithbaidustore.baidutablearroundpersonPOI:
		case protocolwithbaidustore.baidutablemessagePOI:
		case protocolwithbaidustore.baidutableschoolRoute:
		case protocolwithbaidustore.baidutablestorePOI:
		case protocolwithbaidustore.baidutableteammatePOI:
		case protocolwithbaidustore.baidutableteamPOI:
			
			
			HttpResponse response_cre = httpmanager.sendMessageToBDStore((creatPoi) obj, what);
			break;

		case protocolwithbaidustore.baidutabledeleteactivityPOI:
		case protocolwithbaidustore.baidutabledeleteactivityRoute:
		case protocolwithbaidustore.baidutabledeletearroundpersonPOI:
		case protocolwithbaidustore.baidutabledeletemessagePOI:
		case protocolwithbaidustore.baidutabledeleteschoolRoute:
		case protocolwithbaidustore.baidutabledeletestorePOI:
		case protocolwithbaidustore.baidutabledeleteteammatePOI:
		case protocolwithbaidustore.baidutabledeleteteamPOI:
			HttpResponse response_del = httpmanager.sendMessageToBDStore((deletePoi) obj );
			break;

		case protocolwithbaidustore.baidutableudpateactivityPOI:
		case protocolwithbaidustore.baidutableudpateactivityRoute:
		case protocolwithbaidustore.baidutableudpatemessagePOI:
		case protocolwithbaidustore.baidutableudpateschoolRoute:
		case protocolwithbaidustore.baidutableudpatearroundpersonPOI:
		case protocolwithbaidustore.baidutableudpatestorePOI:
		case protocolwithbaidustore.baidutableudpateteammatePOI:
		case protocolwithbaidustore.baidutableudpateteamPOI:
			HttpResponse response_upd = httpmanager.sendMessageToBDStore((updatePoi) obj);
			break;
		}

	}

}
