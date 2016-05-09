package com.sw.plugins.usercenter.socialization.appversion.entity;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.sw.core.data.entity.BaseEntity;

public class AppVersion extends BaseEntity {

	private static final long serialVersionUID = -5645913212917376676L;

	private Integer deviceType;// 终端类型 1：IPHONE 2:IPAD 3：ANDROID
	@NotEmpty
	private String version;// 版本号
	@Size(max = 200)
	private String info;// 版本信息
	@NotEmpty
	private String downloadURL; // 下载地址
	private Integer noticeRate;// 提醒频率 1:一小时 2：一天3:一周4:一月

	public AppVersion() {
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getDownloadURL() {
		return downloadURL;
	}

	public void setDownloadURL(String downloadURL) {
		this.downloadURL = downloadURL;
	}

	public Integer getNoticeRate() {
		return noticeRate;
	}

	public void setNoticeRate(Integer noticeRate) {
		this.noticeRate = noticeRate;
	}

}
