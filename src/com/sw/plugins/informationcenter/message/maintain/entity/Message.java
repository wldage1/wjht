package com.sw.plugins.informationcenter.message.maintain.entity;

import java.util.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.sw.core.data.entity.BaseEntity;
/**
 *  消息管理实体类
 *  @author haoyuanfu
 *  @version 1.0
 *  </pre>
 *  Created on :2012-04-27 10:49:16
 *  LastModified:
 *  History:
 *  </pre>
 */
public class Message extends BaseEntity{
	
	private static final long serialVersionUID = 3953280725714035762L;
	/**消息ID */	
	private Long msgId;
	/**消息标题 */
	@NotEmpty
	@Size(max = 50)
	private String msgTitle;
	/**消息内容 */
	private String msgContent;
	/**推送时间 */
	private String msgPushDate;
	/**推送人 */
	private Long msgPusher;
	/**来源ID */
	private Long sourceId;
	/**审核状态 1=待审核，2=审核通过，3=审核驳回 */
	private int msgAuditing;
	/**审核人 */
	private Long auditor;
	/**消息来源  0=系统填入，1=理财沙龙，2=研究报告，3=行业资讯，4=调查问卷 */
	private int msgFrom;
	/**删除状态 0=为未删除，1=已删除 */
	private int delFlag;	
	/**发布日期 */
	private String  published;
	/**来源类型  0：手动录入 1：CMS 2：理财沙龙 3：调查问卷*/
	private int sourceType;
	
	/*判断列表状态*/
	private int auditState;
	
	/*通过APP发送状态位*/
	private String sendApp;
	
	/*查询时间段*/
	/**发布日期开始时间 */
	private String startTime;
	/**发布日期结束时间 */
	private String endTime;
	
	/**创建消息内容 */
	@NotEmpty
	@Size(max = 500)
	private String content;
	//消息来源
	private String msgFromChn;



	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	
	public String getMsgPushDate() {
		return msgPushDate;
	}

	public void setMsgPushDate(String msgPushDate) {
		this.msgPushDate = msgPushDate;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	public Long getMsgPusher() {
		return msgPusher;
	}

	public void setMsgPusher(Long msgPusher) {
		this.msgPusher = msgPusher;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public int getMsgAuditing() {
		return msgAuditing;
	}

	public void setMsgAuditing(int msgAuditing) {
		this.msgAuditing = msgAuditing;
	}

	public Long getAuditor() {
		return auditor;
	}

	public void setAuditor(Long auditor) {
		this.auditor = auditor;
	}

	public int getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(int msgFrom) {
		this.msgFrom = msgFrom;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getAuditState() {
		return auditState;
	}

	public void setAuditState(int auditState) {
		this.auditState = auditState;
	}

	public String getSendApp() {
		return sendApp;
	}

	public void setSendApp(String sendApp) {
		this.sendApp = sendApp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgFromChn() {
		return msgFromChn;
	}

	public void setMsgFromChn(String msgFromChn) {
		this.msgFromChn = msgFromChn;
	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}


}