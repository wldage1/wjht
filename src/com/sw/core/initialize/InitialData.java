package com.sw.core.initialize;

import java.util.Map;

public class InitialData {

	private Map<String, Object> backupConfig;
	private String productRsyncHost;
	private String rsyncCmd;
	private Map<String, Object> deviceType;
	private Map<String, Object> noticeRate;

	public InitialData() {
	}

	public Map<String, Object> getBackupConfig() {
		return backupConfig;
	}

	public void setBackupConfig(Map<String, Object> backupConfig) {
		this.backupConfig = backupConfig;
	}

	public String getProductRsyncHost() {
		return productRsyncHost;
	}

	public void setProductRsyncHost(String productRsyncHost) {
		this.productRsyncHost = productRsyncHost;
	}

	public String getRsyncCmd() {
		return rsyncCmd;
	}

	public void setRsyncCmd(String rsyncCmd) {
		this.rsyncCmd = rsyncCmd;
	}

	public Map<String, Object> getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Map<String, Object> deviceType) {
		this.deviceType = deviceType;
	}

	public Map<String, Object> getNoticeRate() {
		return noticeRate;
	}

	public void setNoticeRate(Map<String, Object> noticeRate) {
		this.noticeRate = noticeRate;
	}

}
