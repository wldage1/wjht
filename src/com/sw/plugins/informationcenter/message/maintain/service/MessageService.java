package com.sw.plugins.informationcenter.message.maintain.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javapns.back.PushNotificationManager;
import javapns.back.SSLConnectionHelper;
import javapns.data.Device;
import javapns.data.PayLoad;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sw.core.common.Constant;
import com.sw.core.common.SystemProperty;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.service.CommonService;
import com.sw.plugins.clientcenter.member.maintain.entity.Member;
import com.sw.plugins.clientcenter.member.maintain.entity.TerminalCode;
import com.sw.plugins.clientcenter.member.maintain.service.MemberService;
import com.sw.plugins.informationcenter.message.maintain.entity.Message;
import com.sw.plugins.informationcenter.message.maintain.entity.SendRecord;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;
import com.sw.plugins.usercenter.system.log.entity.Log;
import com.sw.plugins.usercenter.system.log.service.LogService;
import com.sw.plugins.usercenter.system.user.entity.User;

/**
 *  Service实现类 - Service实现类基类
 *  @author haoyuanfu
 *  @version 1.0
 *  </pre>
 *  Created on :上午10:57:11
 *  LastModified:
 *  History:
 *  </pre>
 */
@Service
public class MessageService extends CommonService<Message>{
	
	private static Logger logger = Logger.getLogger(MessageService.class);
	
	@Resource
    private DictionaryService dictionaryService;
	
	@Resource
    private MemberService memberService;
	
	public MessageService() {
	}

	
	/**
	 *用于展示符合条件的表格JSON
	 */
	@Override
	public Map<String, Object> getGrid(Message message) {

		List<Object> list = new ArrayList<Object>();
		List<Message> resultList = null;
		//查询条件开始时间和结束时间为同一日期处理
		if(!message.getStartTime().equals("") && !message.getEndTime().equals("") && message.getStartTime().equals(message.getEndTime())){
			message.setPublished(message.getStartTime());
			message.setStartTime("");
			message.setEndTime("");
		}
		if(message.getAuditState()==1){
			//已审核列表
			resultList = getCommonDao().selectList("message.selectAudited", message); 
		}else if(message.getAuditState()==0){
			//待审核列表
			resultList = getCommonDao().selectList("message.selectAuditing", message); 
		}else{
			if(message.getDelFlag()==1){
				//消息回收站
				resultList = getCommonDao().selectList("message.selectRecycled", message); 
			}else{
				//消息列表
				resultList = getCommonDao().selectList("message.select", message); 
			}
		}
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.MSG_TYPE_MSGSOURCE);  //消息来源类型
		Map<Long,Dictionary> msgSourceMap = dictionaryService.getDictMap(dictionary);
		dictionary.setDictSortValue(Constant.MSG_TYPE_AUDITINGSTATE); //审核状态类型
		Map<Long,Dictionary> auditingStateMap = dictionaryService.getDictMap(dictionary);
		
		for (Message tempMessage : resultList){
			Map<String, Object> mapMsg = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			celllist.add(tempMessage.getMsgTitle());
			celllist.add(tempMessage.getPublished());
			//根据字典值查询设置消息来源的中文名称
			if(msgSourceMap.get((long)tempMessage.getMsgFrom())!=null){
				celllist.add(msgSourceMap.get((long)tempMessage.getMsgFrom()).getName());
			}else{
				celllist.add("");
			}
			//根据字典值查询设置审核状态的中文名称
			if(tempMessage.getMsgAuditing()+""!=null && auditingStateMap.get((long)tempMessage.getMsgAuditing())!=null){
				celllist.add(auditingStateMap.get((long)tempMessage.getMsgAuditing()).getName());
			}else{
				celllist.add("");
			}
			celllist.add(tempMessage.getMsgAuditing());
			mapMsg.put("id", tempMessage.getMsgId());
			mapMsg.put("cell", celllist);
			list.add(mapMsg);
		}
		//记录数
		long record = 0;
		try {
			if(message.getAuditState()==1){
				//已审核列表数据数目
				record = getAuditedRecordCount(message);
			}else if(message.getAuditState()==0){
				//待审核列表数据数目
				record = getAuditingRecordCount(message);
			}else{
				if(message.getDelFlag()==1){
					//消息回收站列表数目
					record = (Long) getCommonDao().selectObject("message.selectRecycledCount", message);
				}else{
					//消息列表数据数目
					record = getRecordCount(message);
				}
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		//页数
		int pageCount = (int)Math.ceil(record/(double) message.getRows());
		Map<String, Object> map = new Hashtable<String, Object>(); 
		map.put("rows", list);	
		map.put("page", message.getPage());
		map.put("total", pageCount);
		map.put("records", record);
		return map;
	}



	/**
	 *删除方法（真删除）
	 */
	@Override
	public void delete(Message entity) {
		getCommonDao().delete("message.delete", entity);
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getAll() throws Exception {
		return getCommonDao().selectList("message.select");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Message> getList(Message entity) throws Exception {
		return getCommonDao().selectList("message.select", entity);
	}

	/**
	 * 消息列表——按条件检索记录数
	 */
	@Override
	public Long getRecordCount(Message entity) throws Exception {
		return (Long) getCommonDao().selectObject("message.selectCount", entity);
	}
	
	/**
	 * 待审核列表——按条件检索记录数
	 */
	public Long getAuditingRecordCount(Message entity) throws Exception {
		return (Long) getCommonDao().selectObject("message.selectAuditingCount", entity);
	}
	
	/**
	 * 待审核列表——按条件检索记录数
	 */
	public Long getAuditedRecordCount(Message entity) throws Exception {
		return (Long) getCommonDao().selectObject("message.selectAuditedCount", entity);
	}
	/**
	 * 查询所有记录数
	 */
	@Override
	public Long getAllRecordCount() throws Exception {
		return (Long) getCommonDao().selectObject("message.selectCount");
	}	


	/**
	 * 依据ID查找消息记录
	 */
	@Override
	public Message getOneById(Message entity) throws Exception {
		if (entity.getMsgId() == null)
			return null;
		return (Message)getCommonDao().selectObject("message.selectByOne", entity);
	}

	/**
	 *保存方法
	 */
	@Override
	public void save(Message entity) throws Exception {
		entity.setMsgAuditing(1);
		getCommonDao().insert("message.insert" , entity);
	}

	/**
	 * 更新方法
	 */
	@Override
	public void update(Message entity) throws Exception {
		getCommonDao().update("message.update" , entity);
	}

	@Override
	public void deleteAll() throws Exception {
		super.getCommonDao().delete("message.delete");
	}

	@Override
	public void deleteByIn(Message entity) throws Exception {
		super.getCommonDao().delete("message.deleteByIn");
	}

	/**
	 * 列表删除——假删除
	 */
	@Override
	public void deleteByArr(Message entity) throws Exception {
		Message paramMessage = new Message();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				paramMessage.setMsgId(Long.parseLong(id));				
				super.getCommonDao().delete("message.fdelete",paramMessage);
			}
		}
		//单条删除
		if(entity != null && entity.getId() !=null){
			paramMessage.setMsgId(entity.getId());
			super.getCommonDao().delete("message.fdelete",paramMessage);
		}
	}
	
	/**
	 *  回收站删除——真删除
	 */
	public void deleteByArrTrue(Message entity) throws Exception {
		Message paramMessage = new Message();
		SendRecord paramEntity = new SendRecord();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				paramMessage.setMsgId(Long.parseLong(id));				
				super.getCommonDao().delete("message.delete",paramMessage);
			}
		}
		//单条删除
		if(entity != null && entity.getId() !=null){
			paramMessage.setMsgId(entity.getId());
			super.getCommonDao().delete("message.delete",paramMessage);
		}
	}
	

	/**
	 * 
	 *  回收站——消息还原
	 *  @param entity
	 *  @throws Exception
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-7 下午01:34:12
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public void restoreByArr(Message entity) throws Exception {
		Message paramMessage = new Message();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				paramMessage.setMsgId(Long.parseLong(id));				
				super.getCommonDao().delete("message.restore",paramMessage);
			}
		}
		//单条还原
		if(entity != null && entity.getId() !=null){
			paramMessage.setMsgId(entity.getId());
			super.getCommonDao().delete("message.restore",paramMessage);
		}
	}
	
	/**
	 * 
	 *  消息发送——消息批量发送
	 *  @param entity
	 *  @throws Exception
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-7 下午01:34:12
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public void sendByArr(Message message,Member member) throws Exception {
		SendRecord sendRecord = new SendRecord();
		if(member !=null && member.getIds() !=null){
			for(String id:member.getIds()){
				sendRecord.setMsgId(message.getMsgId());
				sendRecord.setUserId(Long.parseLong(id));
				sendRecord.setOper(Long.parseLong(message.getCreator()));
				super.getCommonDao().save("sendrecord.saveSendRecord", sendRecord);
				update(message);
			}
		}else{
			sendRecord.setMsgId(message.getMsgId());
			sendRecord.setUserId(member.getId());
			sendRecord.setOper(Long.parseLong(message.getCreator()));
			super.getCommonDao().save("sendrecord.saveSendRecord", sendRecord);
			update(message);
		}
	}

	
	public String[] getMemberIds(Member member) throws Exception {
		List<Member> resultList = memberService.getListForSendMessage(member);
		String[] memberIds = new String[resultList.size()];
		int i = 0;
		for (Member tempMember : resultList) {
			memberIds[i] = tempMember.getId().toString();
			i++;
		}
		return memberIds;
	}
	
	public Member listToStringArray(List<Member> list) throws Exception {
		String[] memberIds = new String[list.size()];
		Member member = new Member();
		int i = 0;
		if(list.size()!=0){
			for(Member tempMember:list){
				memberIds[i] = tempMember.getId().toString();
				i++;
			}
			member.setIds(memberIds);
			return member;
		}else{
			return null;
		}
	}
	
	
	/**
	 *  IDS数组转换为String 字符串
	 *  @param member
	 *  @return
	 *  @throws Exception
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-21 上午10:04:13
	 *  LastModified: 2012-7-12
	 *  History:
	 *  </pre>
	 */
	public String getMemberIdsToString(Member member) throws Exception {
		String memberIdsString = "";
		int i = 0;
		String[] strArray = member.getIds();
		for(String str:strArray){
			if(i != 0)
				memberIdsString += ",";
			memberIdsString += str;
			i++;
		}
		return memberIdsString;
	}
	/**
	 *  页面存储检索出来的用户ID
	 *  @param member
	 *  @return
	 *  @throws Exception
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-7-13 下午12:52:12
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public String getMemberIdsToStr(Member member) throws Exception {
		List<Member> resultList = memberService.getListForSendMessage(member);
		String memberIdsString = "";
		int i = 0;
		for (Member tempMember : resultList) {
			if(i != 0)
				memberIdsString += ",";
			memberIdsString += tempMember.getId().toString();
			i++;
		}
		return memberIdsString;
	}
	/**
	 * IDS字符串转为String[]
	 *  @param member
	 *  @return
	 *  @throws Exception
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-21 上午10:04:50
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public String[] getMemberIdsToArray(Member member) throws Exception {
		String memberIdsString = member.getIdsString();
		String[] memberIds=memberIdsString.split(",");
		return memberIds;
	}
	
	//文件上传
	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String realPath = request.getSession().getServletContext().getRealPath("/upload/");
		if (realPath == null){
			try {
				realPath = request.getSession().getServletContext().getResource("/").toString();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		String filePathList = null;
		String realFileName = "";
		String tempPath = "";
		Iterator<String> iterator = multipartRequest.getFileNames();
		while(iterator.hasNext()){
			String fileName = iterator.next().toString();
			File nfile = new File(realPath + File.separator + tempPath,realFileName);
			//文件全名
			filePathList = File.separator + tempPath + File.separator + realFileName;
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
	/**
	 * 
	 *  读取Excel
	 *  @return
	 *  @throws IOException
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-10 下午03:30:06
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	private static List readXls() throws IOException {        
		InputStream is = new FileInputStream("D:\\pldrxkxxmb.xls");        
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);  
		HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
		//创建一个DataFormat对象
		HSSFDataFormat format = hssfWorkbook.createDataFormat();
		//这样才能真正的控制单元格格式，@就是指文本型
		cellStyle.setDataFormat(format.getFormat("@"));
		//cell.setCellStyle(cellStyle);

		List<String> list = new ArrayList<String>();        
		// 循环工作表Sheet       
	for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {            
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);            
		if (hssfSheet == null) {                
			continue;            
			}            
		// 循环行Row            
		for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {                
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			hssfRow.getCell(1).setCellStyle(cellStyle);
			list.add(hssfRow.getCell(1).toString());
		}       
	}
	return list;    
	}
	/**
	 * 
	 *  根据Excel导入表发送消息
	 *  @param message
	 *  @param member
	 *  @throws Exception
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-10 下午04:03:02
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public String sendByExcel(Message message,Member member) throws Exception {
		//获取Excel中的电话号码值List
		List<String> list = MessageService.readXls();
		Member mb = new Member();
		//获取本系统中所有用户List
		List<Member> resultList = memberService.getList(member);
		//定义Excel表中与用户表电话号码吻合的List
		String memberIdsString = "";
		for(int i = 0;i<list.size();i++){
			for(Member tempMember : resultList){
				String telNum = this.getValue(list.get(i).toString());
				if(telNum.equals(tempMember.getMobilePhone())){
					if(i != 0)
						memberIdsString += ",";
					memberIdsString += tempMember.getId().toString();
				}
			}
		}
		return memberIdsString;
	}
	/**
	 * 
	 *  科学计数法转换为正常数字
	 *  @param value
	 *  @return String
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-10 下午04:39:55
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public String getValue(String value){
	      NumberFormat fomatter = new  DecimalFormat();
	      String[] dataList=fomatter.format(Double.parseDouble(String.valueOf(value))).split(",");
	      StringBuffer sb=new StringBuffer();
	       for (int i = 0; i < dataList.length; i++) {
	          sb.append(dataList[i]);
	       }
	return sb.toString();
	}
/*****************************************************    SendApp   *****************************************************************/
	/**
	 * 推送消息
	 *  @param message
	 *  @param member
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-29 上午11:20:24
	 *  LastModified:
	 *  History:
	 *  </pre>
	 * @throws Exception 
	 */
	public String pushData(Message message , Member member , HttpServletRequest request) throws Exception{
		String status = null;
		message.setMsgId(member.getMsgId());
		message = getOneById(message);
		String msg = message.getMsgTitle();     //发送消息标题
		//发送过程记录log
		Log log  = new Log();
		//获取发送的机器号
		List<String> deviceTokens = findDeviceTokens(member);
        PayLoad payLoad = new PayLoad();
        payLoad.addAlert(msg);//push的内容
        payLoad.addBadge(1);//客户端桌面图标小红圈的数值
        payLoad.addSound("default");//提示铃音
        
        String host= SystemProperty.getInstance("parameterConfig").getProperty("appHost") ;
        int port = Integer.parseInt(SystemProperty.getInstance("parameterConfig").getProperty("appPort")) ;
        String rootPath =SystemProperty.getInstance("parameterConfig").getProperty("realPath");//配置文件中证书所在路径
        PushNotificationManager pushManager = PushNotificationManager.getInstance();
        try{          
	        for(int i = 0; i < deviceTokens.size(); i++){
	          pushManager.addDevice("item"+i, deviceTokens.get(i));	
	          System.out.println("Push item deviceToken:" + deviceTokens.get(i));
			}
        }catch(Exception e){
        	e.printStackTrace();
        }
      //发送到iphone
        String statusIphone = pushManagerIphone(deviceTokens,payLoad,host,port,pushManager,rootPath); 
        //发送到ipad
        String statusIpad = pushManagerIpad(deviceTokens,payLoad,host,port,pushManager,rootPath);
        if(statusIpad !=null ||statusIphone !=null ){
        	status = "fail";
        }
        // pushManager
        int count = 0;
        pushManager.stopConnection();
        if(deviceTokens.size()>0){
        	for(int i = 0; i < deviceTokens.size(); i++){
        		pushManager.removeDevice("item"+i);
        		count ++;
        	}  
        }
		return status;
	}
	
	public String pushManagerIpad(List<String> deviceTokens,PayLoad payLoad,String host,int port,PushNotificationManager pushManager,String rootPath){
		String statusIpad = null;
		//发送过程记录log
		Log log  = new Log();
        String ipadCertificatePath=SystemProperty.getInstance("parameterConfig").getProperty("ipadCertificatePath");//导出的证书
        ipadCertificatePath = rootPath+ipadCertificatePath;  //证书实际路径
        String ipadCertificatePassword= SystemProperty.getInstance("parameterConfig").getProperty("ipadCertificatePassword");//此处注意导出的证书密码不能为空因为空密码会报错
        try{
        	pushManager.initializeConnection(host,port, ipadCertificatePath,ipadCertificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
        }catch(Exception e){
        	e.printStackTrace();
        	log.setContent("ipad:APNS服务器链接失败！");
        	logBySend(log);
        	statusIpad = "fail";
        }
        
       //Send Push
       if(deviceTokens.size()>0){
	      for(int i = 0; i < deviceTokens.size(); i++){
	          //记录发送失败log
	          try{
	        	  Device client = pushManager.getDevice("item"+i);
	        	  pushManager.sendNotification(client, payLoad);	
	          }catch(Exception ex){
	          	ex.printStackTrace();
	        	log.setContent("iPad:机器码为"+deviceTokens.get(i)+"用户消息发送失败！");
	        	logBySend(log);
	        	statusIpad = "fail";
	          }
			}
        }
       return statusIpad;
	}
	
	public String pushManagerIphone(List<String> deviceTokens,PayLoad payLoad,String host,int port,PushNotificationManager pushManager,String rootPath){
		String statusIphone = null;
		//发送过程记录log
		Log log  = new Log();
        String iphoneCertificatePath=SystemProperty.getInstance("parameterConfig").getProperty("iphoneCertificatePath");//导出的证书
        iphoneCertificatePath = rootPath+iphoneCertificatePath;  //证书实际路径
        String iphoneCertificatePassword= SystemProperty.getInstance("parameterConfig").getProperty("iphoneCertificatePassword");//此处注意导出的证书密码不能为空因为空密码会报错
        try{
        	pushManager.initializeConnection(host,port, iphoneCertificatePath,iphoneCertificatePassword, SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
        }catch(Exception e){
        	e.printStackTrace();
        	log.setContent("iphone:APNS服务器链接失败！");
        	logBySend(log);
        	statusIphone = "fail";
        }
        
       //Send Push
       if(deviceTokens.size()>0){
	      for(int i = 0; i < deviceTokens.size(); i++){
	          //记录发送失败log
	          try{
	        	  Device client = pushManager.getDevice("item"+i);
	        	  pushManager.sendNotification(client, payLoad);	
	          }catch(Exception ex){
	          	ex.printStackTrace();
	        	log.setContent("iPhone:机器码为"+deviceTokens.get(i)+"用户消息发送失败！");
	        	logBySend(log);
	        	statusIphone = "fail";
	          }
			}
        }
       return statusIphone;
	}
	
	/**
	 *  获取用户IDS机器码
	 *  @param member
	 *  @return
	 *  @throws Exception
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-29 下午12:56:58
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@SuppressWarnings("unchecked")
	public List<String> findDeviceTokens( Member member) throws Exception{
//		List<String> idsList = new ArrayList<String>();
		List<String> DeviceTokens = new ArrayList<String>();
		TerminalCode tc = new TerminalCode();
//		idsList= getMemberIdsToString(member);
//		tc.setIdsString(idsString);
		tc.setIds(member.getIds());
		List<TerminalCode> list= getCommonDao().selectList("terminalcode.select", tc);
		if(list.size()>0){
			for (TerminalCode tempTerminalCode : list){
				DeviceTokens.add(tempTerminalCode.getCode());
			}
		}
		return  DeviceTokens;
	}
	
	public void logBySend(Log log){
  	  Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (currentUser != null) {
			if (currentUser instanceof User) {
				User user = (User) currentUser;
				log.setUserId(user.getId());
				log.setAccessIp(user.getAccessIp());
				log.setUserAccount(user.getAccount());
				log.setUserName(user.getName());
//				
					//写入日志
				super.getCommonDao().insert("log.insert", log);
			}
        } 
	}
/*********************************************************************************************************************************/	
}