package com.sw.core.data.entity;

import java.io.Serializable;
import java.util.Date;

import com.sw.core.common.Constant;
import com.sw.core.data.dbholder.CreatorContextHolder;
import com.sw.core.data.dbholder.DatabaseTypeContextHolder;
import com.sw.core.data.dbholder.DatasourceTypeContextHolder;

public class BaseEntity  implements Serializable{

	private static final long serialVersionUID = -6896154521253993228L;
	
	/**主键*/
	private Long id;
	/**主键串*/
	private String ids [];
	/**唯一标识串*/
	private Long generatedKey;
	/**创建时间*/
	private Date createTime;
	/**创建时间*/
	private Date modifyTime;	
	/**创建者 '-1' 默认超级管理员*/
	private String creator;
	/**是否为返回url 值   1：是， 0或null 否*/
	private String back;
	/**系统通用code代码*/
	private String c;
	/**系统操作类型*/
	private String operate;
	/**数据库类型*/
	private String databaseType;
	/**系统内所有资源都在某一个机构范围内*/
	/**适用于这对多个企业（个人）的公共系统*/
	private String orgIdPath;
	/**提示信息替换字段配置*/
	public String prompt;
	//列表传递参数
	/**表示请求页码（当前页）的参数名称*/
	private int page;
	/**表示请求记录数的参数名称*/
	private int rows;
	/**表示分页起始数的参数名称**/
	private int start;
	/**表示分页偏移量**/
	private int offset;
	/**表示用于排序的列名的参数名称*/
	private String sidx;
	/**表示采用的排序方式的参数名称*/
	private String sord;
	/**表示是否是搜索请求的参数名称*/
	private String _search;
	/** 表示已经发送请求的次数的参数名称*/
	private String nd;
	/**系统操作类型*/
	private String action;
	/**异常错误信息**/
	private String errorMsg;
	public BaseEntity(){
		this.setCreator(null);
		this.setDatabaseType(null);
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		if(creator == null || creator.equals("")){
			String creatorTemp = CreatorContextHolder.getCreatorContext();
			if(creatorTemp != null){
				this.creator = creatorTemp;
			}
		}	
		else{
			this.creator = creator;
		}
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getOrgIdPath() {
		return orgIdPath;
	}

	public void setOrgIdPath(String orgIdPath) {
		this.orgIdPath = orgIdPath;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}

	public String get_search() {
		return _search;
	}

	public void set_search(String _search) {
		this._search = _search;
	}

	public String getNd() {
		return nd;
	}

	public void setNd(String nd) {
		this.nd = nd;
	}

	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
	}

	public Long getGeneratedKey() {
		return generatedKey;
	}

	public void setGeneratedKey(long generatedKey) {
		this.generatedKey = generatedKey;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		if(databaseType == null || databaseType.equals("")){
			String datebaseType = DatabaseTypeContextHolder.getDatabaseType();
			if(datebaseType != null && !datebaseType.equals("")){
				this.databaseType = datebaseType;
			}else{
				this.databaseType = Constant.DEFAULT_DATABASE_TYPE;
			}
		}		
		else{
			this.databaseType = databaseType;
		}
	}

	public String [] getIds() {
		return ids;
	}

	public void setIds(String [] ids) {
		this.ids = ids;
	}

	public int getStart() {
		if (this.databaseType != null){
			//根据数据库类型计算分页起始值
			if (this.databaseType.equals(Constant.DATASOURCE_MYSQL)){
				this.start = (this.getPage() - 1) * this.getRows();
			}
			else if (this.databaseType.equals(Constant.DATASOURCE_ORACLE)){
				this.start = (this.getPage() - 1) * this.getRows() + 1;
			}
			else if (this.databaseType.equals(Constant.DATASOURCE_SQLSERVER)){
				this.start = (this.getPage() - 1) * this.getRows();
			}		
		}
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getOffset() {
		if (this.databaseType != null){
			//根据数据库类型计算分页偏移量（跳跃记录数）
			if (this.databaseType.equals(Constant.DATASOURCE_MYSQL)){
				this.offset = this.getRows();			
			}
			else if (this.databaseType.equals(Constant.DATASOURCE_ORACLE)){
				this.offset = this.getPage() * this.getRows();				
			}
			else if (this.databaseType.equals(Constant.DATASOURCE_SQLSERVER)){
				this.offset = this.getPage() * this.getRows() + 1;			
			}	
		}
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
}
