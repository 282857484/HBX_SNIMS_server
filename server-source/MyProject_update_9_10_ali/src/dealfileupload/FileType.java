package dealfileupload;

public interface FileType {
	
	/***********************************/
	//大图
	// 活动项图片展示(命名规则: ActivityID + "," + ItemNumber)
	final int  ActivityItem = 1;
	// 活动logo(命名规则: ActivityID)
	final int ActivityLogo = 2;
	// 表情(命名规则: UserID + "," + ???)
//	final int biaoqing = 3;
	// 动态(命名规则:　id)
	final int DongTai = 4;
	// 头像
	final int TouXiang = 5;
	// 照片墙
	final int tupianqiang = 6;
	
	/***********************************/
	final int ActivityItem_small = 11;
	final int ActivityLogo_small = 12;
	final int Dongtai_small = 14;
	final int TouXiang_small = 15;
	final int tupianqiang_small = 16;
	

}
