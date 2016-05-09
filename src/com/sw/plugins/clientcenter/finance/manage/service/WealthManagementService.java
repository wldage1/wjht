package com.sw.plugins.clientcenter.finance.manage.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sw.core.common.SystemProperty;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;
import com.sw.core.util.FileUtil;
import com.sw.core.util.HttpClientUtil;
import com.sw.plugins.clientcenter.finance.manage.entity.WealthManagement;

@Service
public class WealthManagementService  extends CommonService<WealthManagement> {

	private static Logger logger = Logger.getLogger(WealthManagementService.class);
	
	@Override
	public void save(WealthManagement entity) throws Exception {
		String image = entity.getImage();
		if(File.separator.equals("\\")){
			image = image.replace("/", "\\");
		}
		if(image != null && !"".equals(image)){
			entity.setImage(image.substring(image.lastIndexOf(File.separator)+1,image.length()));
			super.getCommonDao().save("wealthManagement.insert",entity);
			//资源服务器同步状态
			String openStatus = SystemProperty.getInstance("parameterConfig").getProperty("openStatus");
			if(openStatus.equals("open")){
				Long wmid =entity.getGeneratedKey();
				entity.setId(wmid);
				entity = getOneById(entity);
				//同步到资源库
				String tempPath = SystemProperty.getInstance("parameterConfig").getProperty("wealthManagement");
				String filePath = tempPath+"/";
				String fileName = image.substring(image.lastIndexOf(File.separator)+1,image.length());
				String targetURL = SystemProperty.getInstance("parameterConfig").getProperty("appswsUploadURL") ;
				HttpClientUtil.receiveFile(filePath, fileName, targetURL + "file") ;
			}
		}else{
			super.getCommonDao().save("wealthManagement.insert",entity);
		}
	}

	@Override
	public void update(WealthManagement entity) throws Exception {
		String image = entity.getImage();
		if(File.separator.equals("\\")){
			image = image.replace("/", "\\");
		}
		if(image != null && !"".equals(image) && !" ".equals(image)){
			entity.setImage(image.substring(image.lastIndexOf(File.separator)+1,image.length()));
			super.getCommonDao().update("wealthManagement.update", entity);
			//资源服务器同步状态
			String openStatus = SystemProperty.getInstance("parameterConfig").getProperty("openStatus");
			if(openStatus.equals("open")){
				Long wmid =entity.getGeneratedKey();
				entity.setId(wmid);
				entity = getOneById(entity);
				//同步到资源库
				String tempPath = SystemProperty.getInstance("parameterConfig").getProperty("wealthManagement");
				String filePath = tempPath+"/";
				String fileName = image.substring(image.lastIndexOf(File.separator)+1,image.length());
				String targetURL = SystemProperty.getInstance("parameterConfig").getProperty("appswsUploadURL") ;
				HttpClientUtil.receiveFile(filePath, fileName, targetURL + "file");
			}
		}
		else{
			super.getCommonDao().update("wealthManagement.update", entity);
		}
	}

	@Override
	public Long getRecordCount(WealthManagement entity) throws Exception {
		return (Long)super.getCommonDao().selectObject("wealthManagement.selectCount", entity);
	}

	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long)super.getCommonDao().selectObject("wealthManagement.selectCount");
	}

	@Override
	public List<? extends BaseEntity> getList(WealthManagement entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends BaseEntity> getAll() throws Exception {
		return super.getCommonDao().selectList("wealthManagement.select");
	}

	@Override
	public void delete(WealthManagement entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIn(WealthManagement entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByArr(WealthManagement entity) throws Exception {
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				entity.setId(Long.parseLong(id));
				super.getCommonDao().delete("wealthManagement.delete",entity);
			}
		}
	}
	
	public void deleteFileById(String id, HttpServletRequest request) throws Exception{
		String realPath = request.getSession().getServletContext().getRealPath("") ;
		WealthManagement wealthManagement = new WealthManagement();
		wealthManagement.setId(Long.parseLong(id));
		wealthManagement = getOneById(wealthManagement);
		if(wealthManagement.getImage()!=null){
			String imgUrl = wealthManagement.getImage();
			File file = null;
			String tempPath = SystemProperty.getInstance("parameterConfig").getProperty("wealthManagement");
			if(imgUrl.indexOf('/') != -1){
				file = new File(realPath + File.separator + tempPath + File.separator + imgUrl.substring(17, imgUrl.length()));
			}else{
				file = new File(realPath + File.separator + tempPath + File.separator + imgUrl);
			}
		    //路径与文件都不为空则进行删除   
		    if (file.isFile() && file.exists()) {
		        file.delete();
		    }
		    //资源服务器同步状态
			String openStatus = SystemProperty.getInstance("parameterConfig").getProperty("openStatus");
			if(openStatus.equals("open")){
				//同步删除到资源库
			    String image = wealthManagement.getImage();
				if(File.separator.equals("\\")){
					image = image.replace("/", "\\");
				}
				String filePath = tempPath+"/";
				String fileName = image.substring(image.lastIndexOf(File.separator)+1,image.length());
				String targetURL = SystemProperty.getInstance("parameterConfig").getProperty("appswsUploadURL") ;
				try {
					HttpClientUtil.deleteFile(filePath, fileName, targetURL + "delete/file") ;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public WealthManagement getOneById(WealthManagement entity)
			throws Exception {
		return (WealthManagement)super.getCommonDao().selectObject("wealthManagement.select", entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(WealthManagement entity) {
		List<Object> list = new ArrayList<Object>();
		List<WealthManagement> resultList = null;
		resultList = getCommonDao().selectList("wealthManagement.selectPaginated", entity); 
		for (WealthManagement wealthManagement : resultList) {
			Map<String, Object> maprole = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			celllist.add(wealthManagement.getName());
			celllist.add(wealthManagement.getAddress());
			celllist.add(wealthManagement.getPhone());
			maprole.put("id", wealthManagement.getId());
			maprole.put("cell", celllist);
			list.add(maprole);
		}
		
		// 记录数
		long record = 0;
		try {
			record = getRecordCount(entity);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		// 页数
		int pageCount = (int) Math.ceil(record / (double) entity.getRows());
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("rows", list);
		map.put("page", entity.getPage());
		map.put("total", pageCount);
		map.put("records", record);
		return map;
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String realPath = request.getSession().getServletContext().getRealPath("");
		if (realPath == null){
			try {
				realPath = request.getSession().getServletContext().getResource("/").toString();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		String tempFileName = request.getParameter("Filename");
		//图片扩展名
		String imgExtensionName = tempFileName.substring(tempFileName.lastIndexOf("."));
		String filePathList = "";
		String nowTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
		String realFileName = nowTime + imgExtensionName;
		String tempPath = SystemProperty.getInstance("parameterConfig").getProperty("wealthManagement");
		FileUtil.createFolder(tempPath);
		//String tempPath = "upload/mapimage";
		Iterator<String> iterator = multipartRequest.getFileNames();
		while(iterator.hasNext()){
			String fileName = iterator.next().toString();
			File nfile = new File(realPath + File.separator + tempPath,realFileName);
			//文件全名
			filePathList = "/" + tempPath + "/" + realFileName;
			List<MultipartFile> flist = multipartRequest.getFiles(fileName);
			for (MultipartFile mfile : flist) {
				byte[] bytes;
				try {
					bytes = mfile.getBytes();
					if (bytes.length != 0) {
						mfile.transferTo(nfile);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return filePathList;
	}
	
	public void deleteImgFile(WealthManagement wealthManagement, HttpServletRequest request){
		String realPath = request.getSession().getServletContext().getRealPath("") ;
		if(wealthManagement.getImage()!=null){
			String imgUrl = wealthManagement.getImage();
			File file = null;
			String tempPath = SystemProperty.getInstance("parameterConfig").getProperty("wealthManagement") ;
			if(imgUrl.indexOf('/') != -1){
				file = new File(realPath + File.separator + tempPath + File.separator + imgUrl.substring(17, imgUrl.length()));
			}else{
				file = new File(realPath + File.separator + tempPath + File.separator + imgUrl);
			}
		    //路径与文件都不为空则进行删除   
		    if (file.isFile() && file.exists()) {
		        file.delete();
		    }
		    //资源服务器同步状态
			String openStatus = SystemProperty.getInstance("parameterConfig").getProperty("openStatus");
			if(openStatus.equals("open")){
				//同步删除到资源库
			    String image = wealthManagement.getImage();
				if(File.separator.equals("\\")){
					image = image.replace("/", "\\");
				}
				String filePath = tempPath+"/";
				String fileName = image.substring(image.lastIndexOf(File.separator)+1,image.length());
				String targetURL = SystemProperty.getInstance("parameterConfig").getProperty("appswsUploadURL") ;
				try {
					HttpClientUtil.deleteFile(filePath, fileName, targetURL + "delete/file") ;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(wealthManagement.getId() != null){
			wealthManagement.setImage(" ");
			super.getCommonDao().update("wealthManagement.update",wealthManagement);
		}
	}
}
