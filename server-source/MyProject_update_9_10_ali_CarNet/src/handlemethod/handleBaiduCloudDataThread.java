package handlemethod;

import protocol.infoclass.protocolwithbaidustore;
import httptransfer.creatPoi;
import httptransfer.deletePoi;
import httptransfer.httpManager;
import httptransfer.updatePoi;




public class handleBaiduCloudDataThread implements Runnable {

	httpManager httpmanager = new httpManager();

	private int what = 0;
	private Object obj = null;

	public handleBaiduCloudDataThread(int what, Object obj, 
			int arg1, int arg2) {
		this.what = what;
		this.obj = obj;
	}

	@Override
	public void run() {
		switch (what) {
		case protocolwithbaidustore.CreateCarLocation:
			httpmanager.sendMessageToBDStore((creatPoi) obj, what);
			break;
		case protocolwithbaidustore.UpdateCarLocation:
			httpmanager.sendMessageToBDStore((updatePoi) obj);
			break;
		case protocolwithbaidustore.DeleteCarLocation:
			httpmanager.sendMessageToBDStore((deletePoi) obj);
			break;
		}

	}

}
