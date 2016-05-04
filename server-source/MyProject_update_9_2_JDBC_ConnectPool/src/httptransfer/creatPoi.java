package httptransfer;


import java.util.Map;

import protocol.infoclass.protocolwithbaidustore;



/**
 * 3.1 创建数据（create poi）接口
3.1.1 请求url

http://api.map.baidu.com/geodata/v3/poi/create    // POST请求
3.1.2 请求参数

参数名	参数含义	类型	备注
title	poi名称	string(256)	可选 。
address	地址	string(256)	可选 。
tags	tags	string(256)	可选 。
latitude	用户上传的纬度	double	必选 。
longitude	用户上传的经度	double	必选 。
coord_type	用户上传的坐标的类型	uint32	1：GPS经纬度坐标
2：国测局加密经纬度坐标
3：百度加密经纬度坐标
4：百度加密墨卡托坐标
必选
geotable_id	记录关联的geotable的标识	string(50)	必选，加密后的id 。
ak	用户的访问权限key	string(50)	必选。
sn	用户的权限签名	string(50)	可选。
{column key}	用户在column定义的key/value对	开发者自定义的类型（string、int、double）	唯一索引字段需要保证唯一，否则会创建失败
3.1.3 响应参数（json格式）


参数名	参数含义	类型	备注
status	状态码	int32	必须。0代表成功，其它取值含义另行说明
message	响应的信息	string(50)	对status的英文描述。
id	新增的数据的id	string	必选
 * @author 侯斌
 *
 */
public class creatPoi {
	
	private String url = "http://api.map.baidu.com/geodata/v3/poi/create";
	private String title = null;
	private String address = null;
	private String tags = null;
	private double latitude ; //必选
	private double longitude ;//必选
	private int coord_type;//必选
	private String geotable_id = null;//必选
	private String ak = null;//必选
	//修改为public
	public String sn = null;
	private Map<String , Object> columnkey;
	
	public creatPoi()
	{
		ak = protocolwithbaidustore.strkey;
	}
	
	public String getAk()
	{
		return ak;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getCoord_type() {
		return coord_type;
	}

	public void setCoord_type(int coord_type) {
		this.coord_type = coord_type;
	}

	public String getGeotable_id() {
		return geotable_id;
	}

	public void setGeotable_id(String geotable_id) {
		this.geotable_id = geotable_id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

//	public Map<String, Object> getColumnkey() {
//		return columnkey;
//	}
//
//	public void setColumnkey(Map<String, Object> columnkey) {
//		this.columnkey = columnkey;
//	}

	/**
	 * 获取URL请求
	 * POST请求
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	public Map<String , Object> getColumnkey() {
		return columnkey;
	}

	public void setColumnkey(Map<String , Object> columnkey) {
		this.columnkey = columnkey;
	}
	
	
	
}
