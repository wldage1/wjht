package com.sw.core.util;

import org.apache.log4j.Logger;

public class RsyncUtil {

	private static Logger logger = Logger.getLogger(RsyncUtil.class);

	/**
	 * @param rDir
	 *            同步目录
	 * @param host
	 *            同步服务器
	 */
	public static void rsyncSingleFile(String rsyncCmd, String rDir, String file, String host) throws Exception {
		rDir = rDir.replace("\\", "/");
		rDir = rDir.replace("c:", "/cygdrive/c");
		rDir = rDir.replace("C:", "/cygdrive/c");
		rDir = rDir.replace("d:", "/cygdrive/d");
		rDir = rDir.replace("D:", "/cygdrive/d");
		rDir = rDir.replace("e:", "/cygdrive/e");
		rDir = rDir.replace("E:", "/cygdrive/e");
		rDir = rDir.replace("f:", "/cygdrive/f");
		rDir = rDir.replace("F:", "/cygdrive/f");
		rDir += "./";

		String str = "/";
		String[] arry = file.split("/");
		for (int i = 0; i < arry.length - 1; i++) {
			if (!arry[i].trim().equals("")) {
				str += arry[i] + "/";
				file += " --include=" + str;
			}
		}

		String command = rsyncCmd + "  --relative  --recursive -O --delete --times --compress  " + rDir + " --include=/"+ file + " "+host+" --exclude=*  ";
		logger.info(command);
		Process proc = Runtime.getRuntime().exec(command);
		proc.waitFor();
		int exitValue = 0;
		if ((exitValue = proc.waitFor()) != 0) {
			logger.info("exitValue:" + exitValue);
		}
	}
	
	// public static void main(String[] args) throws Exception {
	// if (args.length != 2) {
	// System.out.println("Usage: java Rsync src des");
	// return;
	// }
	// Process proc =
	// Runtime.getRuntime().exec("rsync -v -r -e --progress ssh -t -C " +
	// args[0] + " " + args[1]);
	// System.out.println("Waiting for end...");
	// int exitValue = 0;
	// if ((exitValue = proc.waitFor()) != 0) {
	// System.out.println("exitValue:" + exitValue);
	// }
	// System.out.println("rsync complete!");
	// }
}
