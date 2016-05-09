package com.sw.core.util;

import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import com.sw.core.common.SystemProperty;

public class HttpClientUtil {

	/**
	 * 上传文件到制定的服务器,默认为文件加密
	 * @param filePath 文件在应用中的相对路径
	 * @param fileName 文件名称，如果为null，则直接使用上传文件的名称
	 * @param targetURL 指定URL，接收文件的服务器url
	 * @return 1-上传成功
	 */
	public static int receiveFile(String filePath, String fileName, String targetURL) throws Exception  {
		return receiveFile(filePath, fileName, targetURL, true) ;
	}
	
	/**
	 * 上传文件到制定的服务器
	 * @param filePath 文件在应用中的相对路径
	 * @param fileName 文件名称，如果为null，则直接使用上传文件的名称
	 * @param targetURL 指定URL，接收文件的服务器url
	 * @param isEncrypt 是否加密
	 * @return 1-上传成功
	 */
	public static int receiveFile(String filePath, String fileName, String targetURL, boolean isEncrypt) throws Exception  {
		//add by zhaofeng 2012-08-31 增加关闭同步到自由服务器的功能
		if(!"open".equals(SystemProperty.getInstance("parameterConfig").getProperty("openStatus"))){
			return 0 ;
		}
		File targetFile = null;//指定上传文件
		PostMethod filePost = new PostMethod(targetURL);
		try {
			String realPath = SystemProperty.getInstance("parameterConfig").getProperty("realPath") ;
			/*modify by zhaofeng 2012-07-05 删除upload路径，改为自己读取配置路径*/
			targetFile = new File(realPath /*+ SystemProperty.getInstance("parameterConfig").getProperty("upload")*/ + filePath + fileName);
			Part[] parts = { 
					new StringPart("fileName", fileName),
					new StringPart("filePath", filePath), 
					new StringPart("isEncrypt", isEncrypt ? "1" : "0"), 
//					new FilePart(targetFile.getName(), targetFile) 
					new CustomFilePart(targetFile.getName(), targetFile)
			};
			
			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
			HttpClient client = new HttpClient();
			
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			int status = client.executeMethod(filePost);
			if (status == HttpStatus.SC_OK) {// 上传成功
				//System.out.println("上传成功");
				return 1 ;
			} 
		} catch (Exception ex) {
			throw new Exception(ex) ;
		} finally {
			filePost.releaseConnection();
		}
		return 0 ;
	}
	
	/**
	 * 删除指定服务器上的文件
	 * @param filePath 文件在应用中的相对路径
	 * @param fileName 文件名称，如果为null，则直接使用上传文件的名称
	 * @param targetURL 指定URL，接收文件的服务器url
	 * @return 1-上传成功
	 */
	public static int deleteFile(String filePath, String fileName, String targetURL) throws Exception {
		PostMethod filePost = new PostMethod(targetURL);	//
		try {
			Part[] parts = { 
					new StringPart("fileName", fileName),
					new StringPart("filePath", filePath)
			};
			
			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			int status = client.executeMethod(filePost);
			if (status == HttpStatus.SC_OK) {// 删除成功
				//System.out.println("删除成功");
				return 1 ;
			} 
		} catch (Exception ex) {
			throw new Exception(ex) ;
		} finally {
			filePost.releaseConnection();
		}
		return 0 ;
	}
}
