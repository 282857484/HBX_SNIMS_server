package httptransfer;


import java.util.HashMap;
import java.util.Map;

import protocol.infoclass.protocolwithbaidustore;


/**
 * 3.5 删除单个数据（poi）接口（支持批量）
3.5.1 请求url

http://api.map.baidu.com/geodata/v3/poi/delete     // POST请求
3.5.2 请求参数

参数名	参数含义	类型	备注
id	被删除的id	uint64	如果传了这个参数，此其它的删除条件会被忽略，此时此操作不是批量请求。只会最多删除一个poi
自定义唯一索引key	Value	用户自定义类型	可选，若自定义索引字段和id共存时，将以id为准，且自定义索引key将被新的value
ids	id列表	以,分隔的id	最多1000个id,如果有这个条件,其它条件将被忽略.
{index key}	用户在column定义的key/value对	 	column需要设置了is_index_field=1。对于string，是两端匹配。对于int或者double，则是范围查找，传递的格式为：最小值,最大值。当无最小值或者最大值时，用-代替，同时，此字段最大长度不超过50，最小值与最大值都是整数.
title	名称	string(256)	可选
tags	标签	string(256)	可选
bounds	查询的矩形区域	string(100)	格式x1,y1;x2,y2分别代表矩形的左上角和右下角
geotable_id	geotable_id	string(50)	必选
ak	用户的访问权限key	string(50)	必选。
sn	用户的权限签名	string(50)	可选。
{column key}	用户在column定义的key/value对	 	 
3.5.3 响应参数（json格式）


参数名	参数含义	类型	备注
status	状态码	int32	必须。0代表成功，其它取值含义另行说明
message	响应的信息	string(50)	对status的英文描述。
id	job id	string	 s
 * @author 侯斌
 *
 */
public class deletePoi {
	// POST请求
	private String url = "http://api.map.baidu.com/geodata/v3/poi/delete";
	private String id = null;
	private String title = null;
	private String tags = null;
	private String bounds = null;
	private String geotable_id = null;
	private String ak = null;
	private String sn = null;
	private Map<String , Object> columnkey = new HashMap<String , Object>();
	
	
	public deletePoi()
	{
		ak = protocolwithbaidustore.strkey;
	}
	
	public String getAk()
	{
		return ak;
	}
	
	/**
	 * // POST请求
	 * @return
	 */
	public String getUrl()
	{
		return url;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getBounds() {
		return bounds;
	}
	public void setBounds(String bounds) {
		this.bounds = bounds;
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

	public Map<String , Object> getColumnkey() {
		return columnkey;
	}

	public void setColumnkey(Map<String , Object> columnkey) {
		this.columnkey = columnkey;
	}
	
}
