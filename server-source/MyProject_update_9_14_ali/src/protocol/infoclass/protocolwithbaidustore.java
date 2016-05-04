package protocol.infoclass;


public interface protocolwithbaidustore {
	final String strkey = "8747d370171ae72e51893506dbff23bb";
	
	/**
	 * 1.GPS经纬度坐标
	 */
	final static int GPSLL = 1;
	/**
	 * 2.国测局加密经纬度坐标
	 */
	final static int GPSNLL = 2;
	/**
	 * 3.百度加密经纬度坐标
	 */
	final static int BAIDULL = 3;
	/**
	 * 4.百度加密墨卡托坐标
	 */
	final static int BAIDUMLL = 4;
	
	
	//creatpoi
	int baidutableschoolRoute = 49444;
	int baidutablestorePOI = 49443;
	int baidutablemessagePOI = 49442;
	int baidutablearroundpersonPOI = 49441;
	int baidutableteammatePOI = 49440;
	int baidutableteamPOI = 49439;
	int baidutableactivityRoute = 49437;
	int baidutableactivityPOI = 49436;
	
	//udpatepoi
	int baidutableudpateschoolRoute = 59444;
	int baidutableudpatestorePOI = 59443;
	int baidutableudpatemessagePOI = 59442;
	int baidutableudpatearroundpersonPOI = 59441;
	int baidutableudpateteammatePOI = 59440;
	int baidutableudpateteamPOI = 59439;
	int baidutableudpateactivityRoute = 59437;
	int baidutableudpateactivityPOI = 59436;
	
	//deletepoi
	int baidutabledeleteschoolRoute = 69444;
	int baidutabledeletestorePOI = 69443;
	int baidutabledeletemessagePOI = 69442;
	int baidutabledeletearroundpersonPOI = 69441;
	int baidutabledeleteteammatePOI = 69440;
	int baidutabledeleteteamPOI = 69439;
	int baidutabledeleteactivityRoute = 69437;
	int baidutabledeleteactivityPOI = 69436;
	
	//creatpoi
		int replybaidutableschoolRoute = 79444;
		int replybaidutablestorePOI = 79443;
		int replybaidutablemessagePOI = 79442;
		int replybaidutablearroundpersonPOI = 79441;
		int replybaidutableteammatePOI = 79440;
		int replybaidutableteamPOI = 79439;
		int replybaidutableactivityRoute = 79437;
		int replybaidutableactivityPOI = 79436;
		
		//udpatepoi
		int replybaidutableudpateschoolRoute = 89444;
		int replybaidutableudpatestorePOI = 89443;
		int replybaidutableudpatemessagePOI = 89442;
		int replybaidutableudpatearroundpersonPOI = 89441;
		int replybaidutableudpateteammatePOI = 89440;
		int replybaidutableudpateteamPOI = 89439;
		int replybaidutableudpateactivityRoute = 89437;
		int replybaidutableudpateactivityPOI = 89436;
		
		//deletepoi
		int replybaidutabledeleteschoolRoute = 99444;
		int replybaidutabledeletestorePOI = 99443;
		int replybaidutabledeletemessagePOI = 99442;
		int replybaidutabledeletearroundpersonPOI = 99441;
		int replybaidutabledeleteteammatePOI = 99440;
		int replybaidutabledeleteteamPOI = 99439;
		int replybaidutabledeleteactivityRoute = 99437;
		int replybaidutabledeleteactivityPOI = 99436;

		// 新增动态评论
		int baidutablemessagediscuss = 74065;
		int baidutableupdatemessagediscuss = 84065;
		int baidutabledeletemessagediscuss = 94065;
		int replybaidutablemessagediscuss = 104065;
		int replybaidutableupdatemessagediscuss = 114065;
		int replybaidutabledeletemessagediscuss = 124065;
}
