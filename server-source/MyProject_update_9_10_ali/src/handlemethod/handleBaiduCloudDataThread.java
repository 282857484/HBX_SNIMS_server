package handlemethod;

import protocol.infoclass.protocolwithbaidustore;
import httptransfer.creatPoi;
import httptransfer.deletePoi;
import httptransfer.httpManager;
import httptransfer.updatePoi;




public class handleBaiduCloudDataThread implements Runnable {

	httpManager httpmanager = new httpManager();

	// private Message msg = null;
	// Context context = null;
	// public handleBaiduCloudDataThread(Message msg ) {
	// this.msg = msg;
	// this.context = context;
	// }

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

	@Override
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
			
			
			httpmanager.sendMessageToBDStore((creatPoi) obj, what);
			break;

		case protocolwithbaidustore.baidutabledeleteactivityPOI:
		case protocolwithbaidustore.baidutabledeleteactivityRoute:
		case protocolwithbaidustore.baidutabledeletearroundpersonPOI:
		case protocolwithbaidustore.baidutabledeletemessagePOI:
		case protocolwithbaidustore.baidutabledeleteschoolRoute:
		case protocolwithbaidustore.baidutabledeletestorePOI:
		case protocolwithbaidustore.baidutabledeleteteammatePOI:
		case protocolwithbaidustore.baidutabledeleteteamPOI:
			httpmanager.sendMessageToBDStore((deletePoi) obj );
			break;

		case protocolwithbaidustore.baidutableudpateactivityPOI:
		case protocolwithbaidustore.baidutableudpateactivityRoute:
		case protocolwithbaidustore.baidutableudpatemessagePOI:
		case protocolwithbaidustore.baidutableudpateschoolRoute:
		case protocolwithbaidustore.baidutableudpatearroundpersonPOI:
		case protocolwithbaidustore.baidutableudpatestorePOI:
		case protocolwithbaidustore.baidutableudpateteammatePOI:
		case protocolwithbaidustore.baidutableudpateteamPOI:
			httpmanager.sendMessageToBDStore((updatePoi) obj);
			break;
		}

	}

}
