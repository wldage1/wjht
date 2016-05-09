/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.clientcenter.agreement.service.controller
 * FileName: AgreementServices.java
 * @version 1.0
 * @author zhaofeng
 * @created on 2012-4-27
 * @last Modified 
 * @history
 */
package com.sw.plugins.clientcenter.agreement.service.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sw.core.common.SystemProperty;
import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.util.FileUtil;
import com.sw.core.util.HttpClientUtil;

/**
 *  服务约定控制器类
 *  @author zhaofeng
 *  @version 1.0
 *  </pre>
 *  Created on :下午4:40:48
 *  LastModified:
 *  History:
 *  </pre>
 */
@Controller  
public class AgreementController extends BaseController{
	/**
	 *  跳转到服务约定编辑页面
	 *  @param request
	 *  @param entity
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-4-28 下午4:25:05
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/clientcenter/agreement/service/editAgreement")
    public CommonModelAndView editAgreement(HttpServletRequest request,BaseEntity entity){ 
		String ipadPath = request.getSession().getServletContext().getRealPath("")+"/" +SystemProperty.getInstance("parameterConfig").getProperty("agreementServiceIpad") ;
		String iphonePath= request.getSession().getServletContext().getRealPath("")+"/" +SystemProperty.getInstance("parameterConfig").getProperty("agreementServiceIphone");
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,entity);
		commonModelAndView.addObject("agreement", entity);
		//查询文件名
				File padimage = new File(ipadPath);
				File[] array = padimage.listFiles();
				if(array != null && array.length >0){
					for (int i = 0; i < array.length; i++) {
						if (array[i].isFile() && array[i].getName().substring(array[i].getName().lastIndexOf(".")+1).equals("pdf")) {
							commonModelAndView.addObject("ipadimage",SystemProperty.getInstance("parameterConfig").getProperty("agreementServiceIpad") +"/" +array[i].getName());
						}
					}
				}
			

				File phoneimage = new File(iphonePath);
				File[] array2 = phoneimage.listFiles();
				if(array2 != null && array2.length >0){
					for (int i = 0; i < array2.length; i++) {
						if (array2[i].isFile()&&array2[i].getName().substring(array2[i].getName().lastIndexOf(".")+1).equals("pdf")) {
							commonModelAndView.addObject("iphoneimage", SystemProperty.getInstance("parameterConfig").getProperty("agreementServiceIphone") +"/" + array2[i].getName());
						}
					}
				}
				
        return commonModelAndView;
    }  
	
	/**
	 *  保存服务约定信息
	 *  @param request
	 *  @param entity
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-2 下午3:05:38
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/clientcenter/agreement/service/save")
	public CommonModelAndView save (HttpServletRequest request, BaseEntity entity){
		String ipadPath = /*request.getSession().getServletContext().getRealPath("")+*/ SystemProperty.getInstance("parameterConfig").getProperty("agreementServiceIpad") ;
		String iphonePath=/* request.getSession().getServletContext().getRealPath("")+*/SystemProperty.getInstance("parameterConfig").getProperty("agreementServiceIphone");
	    //File tempFile = new File(request.getSession().getServletContext().getRealPath("")+"/common/files/agreement/service",file.getOriginalFilename());
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	        Map files = multipartRequest.getFileMap();
	    	//Random random = new Random();
	        //只有两个图片上传，不支持多个图片，两张图片上传以后的文件名规定为imageIpad和imageIphone
	        if(files.containsKey("imageIpad")){
	        	CommonsMultipartFile imageIpad = (CommonsMultipartFile) files.get("imageIpad");
	        	if(imageIpad.getSize() >0){
	        		FileUtil.createFolder(ipadPath);
	        		//删除文件
		        	FileUtil.delAllFile(request.getSession().getServletContext().getRealPath("") +"/" +  ipadPath);
		        	//String  imageIpadFileName = imageIpad.getOriginalFilename();
		        	//获取上传文件后缀名
		        	//String fileType = imageIpadFileName.substring(imageIpadFileName.lastIndexOf(".")+1);
		        	//生成一个随机数，保证每次生成的文件不重名，防止页面有缓存，无法显示新上传的图片
		        	//File tempFile = new File(ipadPath,"ipadimage_"+String.valueOf(random.nextInt(1000))+ "." +fileType);
		        	File tempFile = new File(request.getSession().getServletContext().getRealPath("") +"/" +  ipadPath,"ipadimage.pdf");
		        	 byte[] bytes = imageIpad.getBytes();
		             if (bytes.length != 0) {
		            		try {
		    					imageIpad.transferTo(tempFile);
		    				} catch (IllegalStateException e) {
		    					// TODO Auto-generated catch block
		    					e.printStackTrace();
		    				} catch (IOException e) {
		    					// TODO Auto-generated catch block
		    					e.printStackTrace();
		    				}
		             }
		             //同步到资源库
		             String targetURL = SystemProperty.getInstance("parameterConfig").getProperty("appswsUploadURL") ;
		             try {
						HttpClientUtil.receiveFile(SystemProperty.getInstance("parameterConfig").getProperty("agreementServiceIpad") + "/", "ipadimage.pdf", targetURL + "file") ;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
	        }
	        if(files.containsKey("imageIphone")){
	        	CommonsMultipartFile imageIphone = (CommonsMultipartFile) files.get("imageIphone");
	        	if(imageIphone.getSize() > 0){
	        		FileUtil.createFolder(iphonePath);
	        		//删除文件夹下的所有文件
		        	FileUtil.delAllFile(request.getSession().getServletContext().getRealPath("") +"/" + iphonePath);
		        	String  oName = imageIphone.getOriginalFilename();
		        	//获取上传文件后缀名
		        	//String fileType = oName.substring(oName.lastIndexOf(".")+1);
		        	//File tempFile = new File(iphonePath,"iphoneimage." + fileType);
		        	//File tempFile = new File(iphonePath,"iphoneimage_"+String.valueOf(random.nextInt(1000))+ "." +fileType);
		        	File tempFile = new File(request.getSession().getServletContext().getRealPath("") +"/" + iphonePath,"iphoneimage.pdf");
		        	 byte[] bytes = imageIphone.getBytes();
		             if (bytes.length != 0) {
		            		try {
		    	        		imageIphone.transferTo(tempFile);
		    				} catch (IllegalStateException e) {
		    					// TODO Auto-generated catch block
		    					e.printStackTrace();
		    				} catch (IOException e) {
		    					// TODO Auto-generated catch block
		    					e.printStackTrace();
		    				}
		             }
		             //同步到资源库
		             String targetURL = SystemProperty.getInstance("parameterConfig").getProperty("appswsUploadURL") ;
		             try {
						HttpClientUtil.receiveFile(SystemProperty.getInstance("parameterConfig").getProperty("agreementServiceIphone") + "/", "iphoneimage.pdf", targetURL + "file") ;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        	}
        	}
		
	    CommonModelAndView commonModelAndView = new CommonModelAndView("/clientcenter/agreement/service/editAgreement" );
		commonModelAndView.addObject("agreement", entity);
		//查询文件名
		File padimage = new File(request.getSession().getServletContext().getRealPath("") +"/" +  ipadPath);
		File[] array = padimage.listFiles();
		if(array != null && array.length >0){
			for (int i = 0; i < array.length; i++) {
				if (array[i].isFile() && array[i].getName().substring(array[i].getName().lastIndexOf(".")+1).equals("pdf") ) {
					commonModelAndView.addObject("ipadimage",SystemProperty.getInstance("parameterConfig").getProperty("agreementServiceIpad") +"/" + array[i].getName());
				}
			}
		}

		File phoneimage = new File(request.getSession().getServletContext().getRealPath("") +"/" +iphonePath);
		File[] array2 = phoneimage.listFiles();
		if(array2 != null && array2.length >0){
			for (int i = 0; i < array2.length; i++) {
				if (array2[i].isFile()&&array2[i].getName().substring(array2[i].getName().lastIndexOf(".")+1).equals("pdf")) {
					commonModelAndView.addObject("iphoneimage", SystemProperty.getInstance("parameterConfig").getProperty("agreementServiceIphone") + "/" + array2[i].getName());
				}
			}
		}
		return  commonModelAndView;
		
	}
	/**
	 *  下载服务约定
	 *  @param request
	 *  @param response
	 *  @throws IOException
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-7-20 下午4:19:38
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/clientcenter/agreement/service/download")
		public void download(HttpServletRequest request,HttpServletResponse response) throws IOException {
		    String ipadPath = SystemProperty.getInstance("parameterConfig").getProperty("agreementServiceIpad") ;
		    String iphonePath = SystemProperty.getInstance("parameterConfig").getProperty("agreementServiceIphone");
		    File file = null;
		    if(request.getParameter("type").equals("ipad")){
		    	file = new File(request.getSession().getServletContext().getRealPath("") +"/" +  ipadPath,"ipadimage.pdf");
		    }else if(request.getParameter("type").equals("iphone")){
		    	file = new File(request.getSession().getServletContext().getRealPath("") +"/" + iphonePath,"iphoneimage.pdf");
		    }
		     
			InputStream input = FileUtils.openInputStream(file);
			byte[] data = IOUtils.toByteArray(input);

			String fileName = URLEncoder.encode(file.getName(), "UTF-8");
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.addHeader("Content-Length", "" + data.length);
			response.setContentType("application/octet-stream; charset=UTF-8");

			IOUtils.write(data, response.getOutputStream());
			IOUtils.closeQuietly(input);
		}
	
	
	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
