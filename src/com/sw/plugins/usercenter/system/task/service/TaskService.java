package com.sw.plugins.usercenter.system.task.service;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sw.core.exception.DetailException;
import com.sw.core.initialize.InitialData;
import com.sw.core.service.ITaskService;
import com.sw.core.util.DateUtil;

@Component
public class TaskService implements ITaskService {

	private static Logger logger = Logger.getLogger(TaskService.class);

	@Autowired
	private InitialData initialData;

	@Scheduled(cron = "0 0 0 ? * *")
	@Override
	public void Task() {
		try {
			dbBack();
		} catch (IOException e) {
			logger.error(DetailException.expDetail(e, getClass()));
		}
	}

	// 备份数据库
	private void dbBack() throws IOException {
		if (initialData == null || initialData.getBackupConfig() == null) {
			logger.error("initial data is null");
			return;
		}
		String username = (String)initialData.getBackupConfig().get("username");
		String password = (String)initialData.getBackupConfig().get("password");
		String dbname = (String)initialData.getBackupConfig().get("dbname");
		String proHost = (String)initialData.getBackupConfig().get("host");
		String backDir = (String)initialData.getBackupConfig().get("backdir");

		Runtime rt = Runtime.getRuntime();
		Process child = rt.exec("mysqldump -u" + username + " -p" + password + " -h" + proHost + " " + dbname);
		InputStream in = child.getInputStream();
		InputStreamReader readerEnCode = new InputStreamReader(in, "utf8");

		String inStr;
		StringBuffer sb = new StringBuffer("");
		String outStr;
		BufferedReader br = new BufferedReader(readerEnCode);
		while ((inStr = br.readLine()) != null) {
			sb.append(inStr + "\r\n");
		}
		outStr = sb.toString();

		FileOutputStream fout = new FileOutputStream(backDir + "/" + DateUtil.getCurrentDateString() + "-bak.sql");
		OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
		writer.write(outStr);
		writer.flush();
		in.close();
		readerEnCode.close();
		br.close();
		writer.close();
		fout.close();
	}

}
