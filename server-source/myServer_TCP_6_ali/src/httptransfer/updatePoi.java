package httptransfer;


import java.util.HashMap;
import java.util.Map;

import protocol.infoclass.protocolwithbaidustore;



/**
 * 3.4 修改数据（poi）接口
3.4.1 请求url

http://api.map.baidu.com/geodata/v3/poi/update   //  POST请求
3.4.2 请求参数

参数名	参数含义	类型	备注
id	poi的id	uint64	当不存在唯一索引字段时必须，存在唯一索引字段可选
自定义唯一索引key	Value	用户自定义类型	可选，若自定义索引字段和id共存时，将以id为准，且自定义索引key将被新的value
title	poi名称	string(256)	 
address	地址	 	 
tags	tags	 	 
latitude	用户上传的纬度	double	 
longitude	用户上传的经度	double	 
coord_type	用户上传的坐标的类型	uint32	 1．GPS经纬度坐标
2．测局加密经纬度坐标
3．度加密经纬度坐标
4．度加密墨卡托坐标
必选
geotable_id	记录关联的geotable的标识	string(50)	必选，加密后的id
ak	用户的访问权限key	string(50)	必选。
sn	用户的权限签名	string(50)	可选。
{column key}	用户在column定义的key/value对	用户自定义的的列类别	 
3.4.3 响应参数（json格式）


参数名	参数含义	类型	备注
status	状态码	int32	必须。0代表成功，其它取值含义另行说明
message	响应的信息	string(50)	对status的英文描述。
 * @author 侯斌
 *
 */
public class updatePoi {
	
	//  POST请求
	private String url = "http://api.map.baidu.com/geodata/v3/poi/update";
//	private int id = 0;
//	private String single;
	private String title = null;
	private String address = null;
	private String tags = null;
	private double latitude ;
	private double longitude;
	private int coord_type;
	private String geotable_id = null;
	private String ak = null;
	private String sn = null;
	private Map<String , Object> columnkey = new HashMap<String , Object>();
	
	public updatePoi()
	{
		ak = protocolwithbaidustore.strkey;
	}
	
	public String getAk()
	{
		return ak;
	}
	
	// POST 请求
	public String getUrl()
	{
		return url;
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
//	public void setColumnkey(Map<String, Object> columnkey) {
//		this.columnkey = columnkey;
//	}

	public Map<String , Object> getColumnkey() {
		return columnkey;
	}

	public void setColumnkey(Map<String , Object> columnkey) {
		this.columnkey = columnkey;
	}

	
}
