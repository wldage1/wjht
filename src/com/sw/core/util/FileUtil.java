package com.sw.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Vector;

import com.sw.core.common.Constant;
import com.sw.core.common.SystemProperty;

public class FileUtil {
	static boolean init = false;
	public static void deleteFile(String filename) throws IOException {
		File file = new File(filename);

		if (file.isDirectory()) {
			throw new IOException(
					"IOException -> BadInputException: not a file.");
		}
		if (file.exists() == false) {
			throw new IOException(
					"IOException -> BadInputException: file is not exist.");
		}
		if (file.delete() == false) {
			throw new IOException("Cannot delete file. filename = " + filename);
		}
	}

	public static void deleteDir(File dir) throws IOException {
		if (dir.isFile())
			throw new IOException(
					"IOException -> BadInputException: not a directory.");
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				if (file.isFile()) {
					file.delete();
				} else {
					deleteDir(file);
				}
			}
		}// if
		dir.delete();
	}

	public static void lowCaseFile(String fileName) {
		File f = new File(fileName);
		String lowCaseFileName = fileName.toLowerCase();
		File lowCaseF = new File(lowCaseFileName);

		if (!fileName.equals(fileName.toUpperCase())) {
			f.renameTo(lowCaseF);
		}

		if (lowCaseF.isDirectory()) {
			File[] files = lowCaseF.listFiles();

			for (int i = 0; i < files.length; i++) {
				lowCaseFile(files[i].getPath());
			}

		}
	}
	
	public static void main(String arg[])

	{
		String str="";
//		String   str   = "abc32.45 <> 1.42**.11222YY0.Xaab12.999.+0.9&bbc "; 
//		List   numbers   =   new   ArrayList(); 
//
//		StringBuilder   sb   =   new   StringBuilder(str); 
//
//		while(sb.indexOf( ".")   >=   0)   { 
//		StringBuilder   sbFront   =   getFront(sb); 
//		StringBuilder   sbEnd       =   getEnd(sb); 
//		if   (sbFront.length()   >   0   &&   sbEnd.length()   >   0)   { 
//		numbers.add(sbFront.append( ".").append(sbEnd)); 
//		} 
//		} 
//
//		for(Object   obj   :   numbers)   { 
//			System.out.println(obj); 
//		} 
//		try{
//	        String str1= "d:\\Documents and Settings\\deng_shuanghua\\����\\CustomerVisitManageAction.java";
//	        String str2= "d:\\CustomerVisitManageAction.java";
//	        File file = new File(str1);
//	        file.renameTo(new File("d:\\Documents and Settings\\deng_shuanghua\\����\\CustomerVisitManageAction_bak.java"));
			//fileToFile(str1,str2);
//			System.out.println("�ϴ��ɹ���");
//		  }catch(IOException e){
//			e.printStackTrace();
//		}
		  
	}
	private   static   StringBuilder   getFront(StringBuilder   sb)   { 

//		handle   the   front 
		StringBuilder   strb   =   new   StringBuilder(); 
		for(int   i   =   sb.indexOf( ". ")   -   1;   i   >   0;   i--)   { 

		if(Character.isDigit(sb.charAt(i)))   { 
		strb.insert(0,   sb.charAt(i)); 
		}   else   { 
		break; 
		} 
		} 
		return   strb; 
		} 

		private   static   StringBuilder   getEnd(StringBuilder   sb)   { 

//		handle   the   end 
		StringBuilder   strb   =   new   StringBuilder(); 
		int   i   =   0; 
		for(i   =   sb.indexOf( ". ")   +   1;   i   >   0;   i++)   { 

		if(Character.isDigit(sb.charAt(i)))   { 
		strb.append(sb.charAt(i)); 
		}   else   { 
		break; 
		} 
		} 
//		delete   the   string 
		sb.delete(0,   i); 
		return   strb; 
		} 

	public static String normalizePath(String s) {
		return normalizePath(s, File.separator);
	} // normalize(String):String

	public static String fileToStr(String fileName) throws IOException {
		return fileToStr(new File(fileName));
	}
	
//	public static byte[] fileToBytes(String fileName) throws IOException {
//		return fileToBytes(new File(fileName));
//	}

//	public static byte[] fileToBytes(File file) throws IOException {
//		BufferedInputStream in = null;
//		try {
//
//			in = new BufferedInputStream(new FileInputStream(file));
//			ByteBuffer bf = new ByteBuffer();
//			int len = -1;
//			byte[] buffer = new byte[1024];
//			while ((len = in.read(buffer)) != -1) {
//				bf.append(buffer, 0, len);
//			}
//
//			return bf.getContent();
//		} finally {
//			if (in != null)
//				in.close();
//		}
//	}

	public static void bytesToFile(File file, byte[] content)
			throws IOException {
		BufferedOutputStream out = null;
		try {

			File parent = file.getParentFile();
			if (parent != null)
				parent.mkdirs();

			out = new BufferedOutputStream(new FileOutputStream(file));
			out.write(content);
			out.flush();

		} finally {
			if (out != null)
				out.close();
		}

	}

	public static void bytesToFile(String strFileName, byte[] content)
			throws IOException {
		bytesToFile(new File(strFileName), content);

	}
	public static String fileToStr(File file) throws IOException {
		BufferedReader reader = null;
		try {
//			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
//			reader = new BufferedReader(new FileReader(file));
//			StringBuffer sb = new StringBuffer();
//			char[] buffer = new char[1024];
//			int len = -1;
//			while ((len = reader.read(buffer)) != -1) {
//				sb.append(buffer, 0, len);
//			}
//			InputStreamReader read = new InputStreamReader (new FileInputStream(file),"GBK");
//			reader = new BufferedReader(read);
//			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			StringBuffer sb = new StringBuffer();
//			while (reader.readLine() != null) {
//				sb.append(reader.readLine()+"\\r\\n") ;
//			}
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), Constant.ENCODING); 
			reader = new BufferedReader(isr); 
			int ch; 
			while ((ch = reader.read()) > -1) { 
			   sb.append((char)ch); 
			} 
			reader.close(); 
			return sb.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

public static int strToFile(String strContent, String strFileName)	throws IOException {

	BufferedWriter writer = null;
	
	try {
	
		File file = new File(strFileName);
		File parent = file.getParentFile();
		if (parent != null)
			parent.mkdirs();
	
		writer = new BufferedWriter(new FileWriter(strFileName));
		writer.write(strContent);
		writer.flush();
	
		return 0;
	
	} finally {
	if (writer != null)
		writer.close();
	}

}
/**
 * 
 * �ļ����ļ�src�ϴ��ļ��ļ���des�洢Ŀ���ļ���
 * 
 * */
public static void fileToFile(String src,String des) throws IOException {	
	File file1 = new File(src);
	File file2 = new File(des);
	fileToFile(file1,file2);
 }
/**
 * 
 * �ļ����ļ�src�ϴ��ļ���des�洢Ŀ���ļ�
 * 
 * */
public static void fileToFile(File src,File des) throws IOException {	
	 BufferedReader reader = null;
	 BufferedWriter writer = null;
	try {
		reader = new BufferedReader(new FileReader(src));
		StringBuffer sb = new StringBuffer();
		char[] buffer = new char[1024];
		int len = -1;
		while ((len = reader.read(buffer)) != -1) {
			sb.append(buffer, 0, len);
		}
		File parent = des.getParentFile();
		if (parent != null)
			parent.mkdirs();	
		writer = new BufferedWriter(new FileWriter(des));
		writer.write(sb.toString());
		writer.flush();
	  } finally {
		if (reader != null)
			reader.close();
		if (writer != null)
			writer.close();
	}
 }

	
	public static int strToFile(String str_strIn, File fileOut)
			throws IOException {
		return strToFile(str_strIn, fileOut.getAbsolutePath());
	}

	public static String normalizePath(String s, String file_seperator) {
		if (s == null)
			return s;
		StringBuffer str = new StringBuffer();
		boolean is_spe = false;
		int len = (s != null) ? s.length() : 0;
		char ch = '\0';
		if (len > 0) {
			ch = s.charAt(0);
			if (ch == '/' || ch == '\\') {
				str.append(file_seperator);
			} else {
				str.append(ch);

			}

			for (int i = 1; i < len; i++) {
				ch = s.charAt(i);
				switch (ch) {
				case '/':
				case '\\':
					if (is_spe) {

					} else {
						str.append(file_seperator);
						is_spe = true;
					}
					break;
				default: {
					is_spe = false;
					str.append(ch);
				}
				}
			}
		}

		return str.toString();

	} // normalize(String):String

	/**
	 * Insert the method's description here. Creation date: (2003-3-7 13:41:15)
	 * 
	 * @return java.util.Date
	 * @param filePath
	 *            java.lang.String
	 */
	public static java.util.Date getFileCreateTime(String filePath)
			throws java.text.ParseException {

		if (!init) {
			System.loadLibrary("fileUtil");
			init = true;
		}

		if (filePath == null)
			return null;
		String t = getFileCreateTime0(filePath);
		// log.log("file:" + filePath + " createTime:" + t);
		if (t != null) {

			// System.out.println(filePath+"createTime:"+t);
			return (new java.text.SimpleDateFormat("yyyy/MM/dd/HH:mm/ss"))
					.parse(t);

		}
		return null;
	}

	public static String url2Filename(URL u) {
		StringBuffer sb = new StringBuffer();

		sb.append(u.getHost());
		sb.append(u.getPath());
		if (u.getPath().equals(""))
			sb.append("/");

		// is there a query part ?
		// that is something after the file name seperated by ?
		String query = u.getQuery();

		// if(u.getFile().startsWith("ThemeFmore.asp"))
		// {
		// log.info("");
		// }
		//		
		if ((query != null) && (!"".equals(query))) {
			sb.append("?");
			sb.append(query);
		}

		// filename that ends with /
		// are directories, we will name the file "index.html"
		if (sb.charAt(sb.length() - 1) == '/') {
			sb.append("index.html");
		}

		// �滻�ļ���������ַ�
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			char newc = (char) 0;

			// ͳһ /\ Ϊ�ļ��ָ��
			if (c == '\\' || c == '/') {
				newc = File.separatorChar;
			}
			// replace :*?"<>| Ϊ'_'

			if (c == ':' || c == '*' || c == '?' || c == '<' || c == '>'
					|| c == '|') {
				newc = '_';
			}

			if ((newc != (char) 0) && (newc != c)) {
				sb.setCharAt(i, newc);
			}
		}

		return sb.toString();
	}

	/**
	 * Insert the method's description here. Creation date: (2003-3-7 13:41:15)
	 * 
	 * @return java.util.Date
	 * @param filePath
	 *            java.lang.String
	 */
	public static native String getFileCreateTime0(String filePath);

	public static native boolean mapDriver(String userName, String password,
			String remoteName, String localName);

	public static native boolean unMapDriver(String localName);

	// ���ļ����з��ظ�һ���ַ�����
	public static String[] fileToStringArray(File file) throws IOException {
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(new FileReader(file));
			Vector v = new Vector();
			String str = null;
			while ((str = reader.readLine()) != null) {
				v.add(str.trim());
			}

			if (v.size() > 0) {
				String[] arr = new String[v.size()];
				for (int i = 0; i < v.size(); i++) {
					arr[i] = (String) v.get(i);
				}
				return arr;
			}
			return null;
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	// ���ļ����з��ظ�һ���ַ�����
	public static String[] fileToStringArray(String fileName)
			throws IOException {
		return fileToStringArray(new File(fileName));
	}
	
	
	public static void copy(File src, File dst) {   
	        try {   
	            InputStream in = null;   
	            OutputStream out = null;   
	            try {   
	                in = new BufferedInputStream(new FileInputStream(src));   
	                out = new BufferedOutputStream(new FileOutputStream(dst));   
	                byte[] buffer = new byte[1024*10];   
	                while (in.read(buffer) > 0) {   
	                    out.write(buffer);   
	                }   
	            } finally {   
	                if (null != in) {   
	                    in.close();   
	                }   
	                if (null != out) {   
	                    out.close();   
	                }   
	            }   
	        } catch (Exception e) {   
	            e.printStackTrace();   
	        }   
	    }   
	
	/**
	 * 
	 * ��˫����� 
	 * 2009-03-12
	 * �ϴ�ͼƬ
	 * 
	 * */
	public static void uploadPic(String src,String dst){				 
        try{     
            File file = new File(src);     
           InputStream inputStream = new FileInputStream(file);   
			OutputStream outputStream = new FileOutputStream(dst);
			try {
				inputStream = new BufferedInputStream(inputStream);
				outputStream = new BufferedOutputStream(outputStream);
				byte[] buffer = new byte[(int)file.length()];
				while (inputStream.read(buffer) > 0) {
					outputStream.write(buffer);
				}
			   } finally {
			 if (null != inputStream) {
					inputStream.close();
			  }
			 if (null != outputStream) {
					outputStream.close();
				}
			  }
         }catch (Exception e) {
   	   e.printStackTrace();
      }
	}
	
	/**
	 * 以行的方式进行文件的读取
	 * @param fileName
	 * @return
	 */
	public static String readFileByLines(String fileName) {
		StringBuffer sb = new StringBuffer();
		File file = new File(fileName);
		if (!file.exists())
			return "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString + "\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return sb.toString();
	}
	  /**
	 *  删除指定目录下的所有文件
	 *  @param path
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-25 下午3:36:53
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	/**
	 *  删除文件夹
	 *  @param folderPath
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-25 下午3:40:11
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建文件夹
	 */
	public static void createFolder(String folderPath) {
		try {
			String[] tempArray = folderPath.split("/");
			//资源文件根路径
			String rootPath = SystemProperty.getInstance("parameterConfig").getProperty("realPath") ;
			String path = rootPath;
			for(int i =0;i<tempArray.length;i++){
				path +=tempArray[i]+File.separator;
				File pdfFilePath = new File(path); 
			    if (!pdfFilePath.exists()) { 
			    	pdfFilePath.mkdir(); 
			    } 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}