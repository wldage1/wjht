/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.survey.questionnaire.maintain.service
 * FileName: QuestionnaireService.java
 * @version 1.0
 * @author zhaofeng
 * @created on 2012-5-16
 * @last Modified 
 * @history
 */
package com.sw.plugins.survey.questionnaire.maintain.service;

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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sw.core.common.Constant;
import com.sw.core.common.SystemProperty;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.exception.DetailException;
import com.sw.core.service.CommonService;
import com.sw.core.util.FileUtil;
import com.sw.core.util.HttpClientUtil;
import com.sw.plugins.clientcenter.member.maintain.entity.Member;
import com.sw.plugins.clientcenter.member.maintain.service.MemberService;
import com.sw.plugins.informationcenter.message.maintain.entity.Message;
import com.sw.plugins.informationcenter.message.maintain.entity.SendRecord;
import com.sw.plugins.survey.question.maintain.entity.Question;
import com.sw.plugins.survey.question.maintain.service.QuestionOptionService;
import com.sw.plugins.survey.question.maintain.service.QuestionService;
import com.sw.plugins.survey.questionnaire.maintain.entity.Questionnaire;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;

/**
 *  调查问卷业务类
 *  @author zhaofeng
 *  @version 1.0
 *  </pre>
 *  Created on :下午12:05:40
 *  LastModified:
 *  History:
 *  </pre>
 */
@Service
public class QuestionnaireService extends CommonService<Questionnaire>{
	
	/**
	 * 问题service
	 */
	@Resource
    private QuestionService questionService;
	
	/**
	 * 问题选项service
	 */
	@Resource
    private QuestionOptionService questionOptionService;
	

	@Resource
    private DictionaryService dictionaryService;

	@Override
	public void save(Questionnaire entity) throws Exception {
		getCommonDao().insert("questionnaire.insert" , entity);
		
	}

	@Override
	public void update(Questionnaire entity) throws Exception {
		getCommonDao().update("questionnaire.update" , entity);
	}

	@Override
	public Long getRecordCount(Questionnaire entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAllRecordCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends BaseEntity> getList(Questionnaire entity)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends BaseEntity> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//已废弃，改为deletQue(Questionnaire entity,HttpServletRequest request)
	public void delete(Questionnaire entity) throws Exception {
		//判断问卷是否发布,如果已经发布,不可以删除
		 Questionnaire q =  getOneById( entity);
		 if(q.getOpenFlag() == null || q.getOpenFlag() !=  Constant.QUE_QUESTIONNAIRE_OPEN_FLAG_TRUE){
			 getCommonDao().delete("questionnaire.delete",entity);
		 }
	}
	/**
	 *  删除调查问卷（并删除调查问卷关联的图片附件）
	 *  @param entity
	 *  @param request
	 *  @throws Exception
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-9-14 上午12:30:52
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public void deletQue(Questionnaire entity,HttpServletRequest request) throws Exception{
		//判断问卷是否发布,如果已经发布,不可以删除
		 Questionnaire q =  getOneById( entity);
		 if(q.getOpenFlag() == null || q.getOpenFlag() !=  Constant.QUE_QUESTIONNAIRE_OPEN_FLAG_TRUE){
			 getCommonDao().delete("questionnaire.delete",entity);
			 //删除ipad图片
			 if(null != q.getQueImgIpad() && !"".equals(q.getQueImgIpad())){
				 String realPath = request.getSession().getServletContext().getRealPath(""); 
				 deleteQeuImgFile(realPath + "/",q.getQueImgIpad() , "Ipad"); 
			 }
			 //删除iphone图片
            if(null != q.getQueImgIphone() && !"".equals(q.getQueImgIphone())){
            	 String realPath = request.getSession().getServletContext().getRealPath(""); 
				 deleteQeuImgFile(realPath + "/",q.getQueImgIphone() , "Iphone");
			 }
			 
		 }
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIn(Questionnaire entity) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	//已废弃，改为 deleteByArrAndImg(Questionnaire entity,HttpServletRequest request) 
	public void deleteByArr(Questionnaire entity) throws Exception {
		Questionnaire questionnaire = new Questionnaire();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				questionnaire.setId(Long.parseLong(id));
				this.delete(questionnaire);
			}
		}
	}
	/**
	 *  批量删除问卷，并删除问卷关联的图片附件
	 *  @param entity
	 *  @param request
	 *  @throws Exception
	 *  @author sean
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-9-14 上午12:33:03
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public void deleteByArrAndImg(Questionnaire entity,HttpServletRequest request) throws Exception {
		Questionnaire questionnaire = new Questionnaire();
		if (entity != null && entity.getIds() != null){
			for (String id : entity.getIds()){
				questionnaire.setId(Long.parseLong(id));
				this.deletQue(questionnaire,request);
			}
		}
	}

	@Override
	public Questionnaire getOneById(Questionnaire entity) throws Exception {
		if (entity.getId() == null)
			return null;
		return (Questionnaire)getCommonDao().selectObject("questionnaire.selectByOne", entity);
	}

	@Override
	public Map<String, Object> getGrid(Questionnaire entity) {
		List<Object> list = new ArrayList<Object>();
		List<Questionnaire> resultList =  getCommonDao().selectList("questionnaire.select", entity); 
		
		for (Questionnaire questionnaire : resultList){
			Map<String, Object> map = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			celllist.add(questionnaire.getQueName());
			celllist.add(questionnaire.getQueTitle());
			celllist.add(questionnaire.getOpenFlag());
			//查询问卷调查的回复数
			if(questionnaire.getOpenFlag() != null && questionnaire.getOpenFlag()== Constant.QUE_QUESTIONNAIRE_OPEN_FLAG_TRUE){
				//只有发布的问卷才统计回复数
				celllist.add(selectQuestionnaireResponseNum(questionnaire));
			}else{
				celllist.add(0);
			}
			//celllist.add(questionnaire.getQueStartTime());
			//celllist.add(questionnaire.getQueEndTime());
			
			map.put("id", questionnaire.getId());
			map.put("cell", celllist);
			list.add(map);
		}
		//记录数
		long record = 0;
		try {
			record = (Long) getCommonDao().selectObject("questionnaire.selectCount", entity);
		} catch (Exception e) {
			DetailException.expDetail(e, QuestionnaireService.class);
		}
		//页数
		int pageCount = (int)Math.ceil(record/(double) entity.getRows());
		Map<String, Object> map = new Hashtable<String, Object>(); 
		map.put("rows", list);	
		map.put("page", entity.getPage());
		map.put("total", pageCount);
		map.put("records", record);
		return map;
	}

	
	/**
	 * 上传图片
	 */
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
		//先删除已上传 的图片，因为一个文件调查关联只能关联一个图片
		  String queImgId = request.getParameter("queImgId");
		  String queImgType = request.getParameter("queImgType");
		  if(null != queImgId && !"".equals(queImgId)){
			  deleteQeuImgFile(realPath + "/",queImgId , queImgType); 
			  //删除表中关于图片的信息的记录
			  if(null != request.getParameter("queId") && !"".equals(request.getParameter("queId"))){
				    Questionnaire questionnaire = new Questionnaire();
			    	questionnaire.setId(Long.valueOf(request.getParameter("queId")));
			    	questionnaire.setQueImgType(queImgType);
			    	getCommonDao().delete("questionnaire.deleteQueImg",questionnaire);
			  }
		  }
		
		String nowTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
		String realFileName = nowTime+".jpg";
		// 图片存储路径
		String imgPath = SystemProperty.getInstance("parameterConfig").getProperty("queImgPath" + queImgType) ;
		//判断文件夹是否存在如果不存在就创建
		FileUtil.createFolder(imgPath);
		String tempPath = imgPath;
		Iterator<String> iterator = multipartRequest.getFileNames();
		while(iterator.hasNext()){
			String fileName = iterator.next().toString();
			File nfile = new File(realPath + File.separator + tempPath,realFileName);
			//文件全名
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
		//资源库同步标记
		String updateStatus = "0";
		try{
			updateStatus =String.valueOf(synchronizationFile(realFileName,queImgType));
		}catch(Exception e){
			e.printStackTrace();
		}
		// 同步服务器url
		String synUploadUrl = SystemProperty.getInstance("parameterConfig").getProperty("uploadUrl") ;
		StringBuffer sb = new StringBuffer();
		sb.append("{\"filename\": \"").append(realFileName);
		sb.append("\",\"updateStatus\":\"").append(updateStatus);
		sb.append("\",\"synUploadUrl\":\"").append(synUploadUrl);
		sb.append("\",\"imgPath\":\"").append(imgPath+"/");
		sb.append("\"}");
		return sb.toString();
	}
	
	/**
	 *  资源服务器同步
	 *  @param imgName
	 *  @return
	 *  @throws Exception
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-9-12 下午2:30:56
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	private int synchronizationFile(String imgName,String imgType) throws Exception{
		//资源服务器同步状态
		String openStatus = SystemProperty.getInstance("parameterConfig").getProperty("openStatus") ;
		//同步上传状态 0=不成功 1=上传成功2=不使用资源同步功能
		int updateStatus = 0;
		//同步到资源库
		if(openStatus.equals("open")){
			String filePath = SystemProperty.getInstance("parameterConfig").getProperty("queImgPath" + imgType)+"/" ;
			String fileName = imgName;
			String targetURL = SystemProperty.getInstance("parameterConfig").getProperty("appswsUploadURL") ;
			updateStatus = HttpClientUtil.receiveFile(filePath, fileName, targetURL + "file") ;
		}else if("close".equals(openStatus)){
			updateStatus = 2 ;
		}
		return updateStatus;
	}
	/**
	 *  统计调查回复数 
	 *  @param questionnaire
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-29 下午3:23:22
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public Long selectQuestionnaireResponseNum(Questionnaire questionnaire){
		return (Long)getCommonDao().selectObject("questionnaire.selectQuestionnaireResponseNum", questionnaire);
	}
	
	/**
	 *  发布问卷
	 *  @param entity
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-28 下午4:34:02
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public void release(Questionnaire questionnaire){
		//查询问题集合
	   List<Question> questionList = questionService.selectQuestions(questionnaire);
	   questionnaire.setCustomSQL(this.generateAnswerSQL(questionList,questionnaire.getDatabaseType()));
	   this.getCommonDao().insert("questionnaire.creatAnswerTable",questionnaire);
	   //如果数据库为oracle，创建答案表的触发器（答案表ID改为手动插入，此方法废弃）
	   /*if(Constant.DATASOURCE_ORACLE.equals(questionnaire.getDatabaseType())){
		   this.getCommonDao().insert("questionnaire.createAnswerTrigger",questionnaire);
	   }*/
	}
	/**
	 *  建立问卷答案表,组装建表语句
	 *  @param questionList
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-28 下午7:56:17
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	private String generateAnswerSQL (List<Question> questionList,String databaseType){
		StringBuffer sb = new StringBuffer();
		//根据不同的数据库生成不同的建表语句
		
		//oracle数据 
		if(Constant.DATASOURCE_ORACLE.equals(databaseType)){
			for(Question question : questionList){
				//列名，列名为option_问题ID
				sb.append(" OPTION_").append(question.getId()).append("  ");
				//数据类型
				if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SINGLE_CHOICE ){
					sb.append(" INTEGER ,");
				}else if (question.getQuestionType() == Constant.QUE_QUESTION_TYPE_BLANK || 
						question.getQuestionType() == Constant.QUE_QUESTION_TYPE_MULTIPLE_CHOICE){
					//这个地方应该增加最大长度的判断
					sb.append(" varchar2(400) default NULL ,");
	 			}else if (question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SHORTANSWER){
	 				sb.append(" varchar2(800) default NULL ,");
	 			}
			}
		}else if(Constant.DATASOURCE_MYSQL.equals(databaseType)){//MySQL
			for(Question question : questionList){
				//列名，列名为option_问题ID
				sb.append(" `OPTION_").append(question.getId()).append("`  ");
				//数据类型
				if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SINGLE_CHOICE ){
					sb.append(" int(10) ,");
				}else if (question.getQuestionType() == Constant.QUE_QUESTION_TYPE_BLANK || 
						question.getQuestionType() == Constant.QUE_QUESTION_TYPE_MULTIPLE_CHOICE){
					//这个地方应该增加最大长度的判断
					sb.append(" varchar(400) default NULL ,");
	 			}else if (question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SHORTANSWER){
	 				sb.append(" varchar(800) default NULL ,");
	 			}
			}
		}else if(Constant.DATASOURCE_SQLSERVER.equals(databaseType)){//sqlServer
			for(Question question : questionList){
				//列名，列名为option_问题ID
				sb.append(" OPTION_").append(question.getId());
				//数据类型
				if(question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SINGLE_CHOICE ){
					sb.append(" int ,");
				}else if (question.getQuestionType() == Constant.QUE_QUESTION_TYPE_BLANK || 
						question.getQuestionType() == Constant.QUE_QUESTION_TYPE_MULTIPLE_CHOICE){
					//这个地方应该增加最大长度的判断
					sb.append(" varchar(400)  NULL ,");
	 			}else if (question.getQuestionType() == Constant.QUE_QUESTION_TYPE_SHORTANSWER){
	 				sb.append(" varchar(800)  NULL ,");
	 			}
			}
		}
		return sb.toString();
	}
	

	/**
	 *  保存调查问卷答案
	 *  @param parameterMap
	 *  @param questionnaire
	 *  @param commonColumnMap
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-30 上午11:45:44
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public void saveQuestionnaireAnswer (Map parameterMap,Questionnaire questionnaire,Map<String,String> commonColumnMap){
		Iterator it = parameterMap.entrySet().iterator();
	
		//StringBuffer column = new StringBuffer();//答案表的列名
	   // StringBuffer values = new StringBuffer();//答案值
	    
	    String columnSQL = "";
	    String valuesSQL = "";
		
        //遍历表单参数map，对应的插入到答案表中
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			//名为id的参数是问卷的id，不保存到答案的表中
			if("id".equals(entry.getKey().toString()) || "JoinTime".equals(entry.getKey().toString())){
				continue;
			}
			
			//根据数据库类型不同组装SQL语句
			columnSQL += "," +entry.getKey().toString();
		/*	if(questionnaire.getDatabaseType().equals(Constant.DATASOURCE_ORACLE)){
				columnSQL += entry.getKey().toString();
			}else{
				columnSQL += ",`" + entry.getKey().toString() + "`";
			}*/
				
			
			String[] arrayValue = (String[]) entry.getValue();
			String tempValue = "";
			for(int i=0; i < arrayValue.length; i++){
				if(i == 0){
					tempValue = arrayValue[i];
				}else{
					tempValue += "," + arrayValue[i];//checkbox提交上来的值为数组
				}
			}
			/*if(valuesSQL == null ){
				valuesSQL = "'" + tempValue + "'";
			}else{*/
				valuesSQL += ",'" + tempValue + "'";
			//}
		}
		//组装公共字段的SQL
		String commonSQL = null;
		String commonSQLValue = null;
		if(questionnaire.getDatabaseType().equals(Constant.DATASOURCE_ORACLE)){
			commonSQL = "ID,IpAddress,JoinTime,OverTime";
			commonSQLValue = "S_QUE_ANSWER.NEXTVAL,'" + commonColumnMap.get("IpAddress") + "',to_date('"+ commonColumnMap.get("JoinTime") + "','YYYY-MM-DD HH24:MI:SS'),sysdate" ;
		}else if (questionnaire.getDatabaseType().equals(Constant.DATASOURCE_SQLSERVER)){
			commonSQL = "IpAddress,JoinTime,OverTime";
			commonSQLValue = "'" + commonColumnMap.get("IpAddress") + "','" + commonColumnMap.get("JoinTime") + "',getdate() " ;
		}else{
			commonSQL = "`IpAddress`,`JoinTime`,`OverTime`";
			commonSQLValue = "'" + commonColumnMap.get("IpAddress") + "','" + commonColumnMap.get("JoinTime") + "',now()" ;
		}
		
		
		
		//组装插入语句
		String sql =  "(" + commonSQL  + columnSQL + ") values (" + commonSQLValue + valuesSQL + " )";
		questionnaire.setCustomSQL(sql);
		this.getCommonDao().insert("questionnaire.saveQuestionnaireAnswer",questionnaire);
	}
	
	/**
	 *  统计问卷包含的问题数
	 *  @param questionnaire
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-29 下午4:05:14
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public Long countQueQuestions(Questionnaire questionnaire){
		long record = 0;
		try {
			record = (Long) getCommonDao().selectObject("question.countQueQuestions", questionnaire);
		} catch (Exception e) {
			DetailException.expDetail(e, QuestionnaireService.class);
		}
		return record;
	}
	
	/**
	 *  清空调查问卷对应的答案数据（删除问卷对应的答案表）
	 *  @param questionnaire
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-1 上午10:04:12
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public void deleteQuestionnaireAnswer(Questionnaire questionnaire){
		getCommonDao().insert("questionnaire.deleteQuestionnaireAnswer", questionnaire);
	}


	/**
	 *  设置发布状态
	 *  @param questionnaire
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-11 上午10:41:47
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public void setQuestionnaireOpenFlag(Questionnaire questionnaire){
		getCommonDao().update("questionnaire.setQuestionnaireOpenFlag", questionnaire);
	}
	/**
	 *  获取参与调查的用户列表
	 *  @param entity
	 *  @return
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-7-13 下午3:00:40
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public Map<String, Object> getUserResult(Questionnaire entity) {
		List<Object> list = new ArrayList<Object>();
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			List<Questionnaire> resultList = getCommonDao().selectList("questionnaire.getUserResult", entity);
			Dictionary dictionary = new Dictionary();
			dictionary.setDictSortValue(Constant.DICT_TYPE_GENDER);
			Map<Long,Dictionary> genderMap = dictionaryService.getDictMap(dictionary);   //性别
			for (Questionnaire questionnaire : resultList) {
				Map<String, Object> mapQuestionnaire = new Hashtable<String, Object>();
				List<Object> celllist = new ArrayList<Object>();
				celllist.add(questionnaire.getMemberName());
				if(questionnaire.getSex()!=null && genderMap.get(questionnaire.getSex())!=null){
					celllist.add(genderMap.get(questionnaire.getSex()).getName());
				}else{
					celllist.add("");
				}
				celllist.add(questionnaire.getCity());
				celllist.add(questionnaire.getMobile());
				celllist.add(questionnaire.getEmail());
				mapQuestionnaire.put("id", questionnaire.getUserID());
				mapQuestionnaire.put("cell", celllist);
				list.add(mapQuestionnaire);
			}
			// 记录数
			long record = 0;
			record = getSendUsersCount(entity);
			// 页数
			int pageCount = (int) Math.ceil(record
					/ (double) entity.getRows());
			map.put("rows", list);
			map.put("page", entity.getPage());
			map.put("total", pageCount);
			map.put("records", record);

		} catch (Exception e) {
			DetailException.expDetail(e, MemberService.class);
		}
		return map;
	}
	
	/**
	 *  查询参与调查的会员总数
	 *  @param entity
	 *  @return
	 *  @throws Exception
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-7-13 下午3:24:14
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public Long getSendUsersCount(Questionnaire entity) throws Exception {
		Object obj = getCommonDao().selectObject("questionnaire.getUserResultCount",entity);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}
	
	/**
	 *  删除用户参与问卷的记录
	 *  @param questionnaire
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-7-23 下午3:11:12
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public void deleteUserResult(Questionnaire questionnaire){
		 getCommonDao().delete("questionnaire.deleteUserResult",questionnaire);
	}
	
	/**
	 *  删除问卷图片
	 *  @param request
	 *  @author  zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-9-13 上午8:55:35
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	public void deleteQeuImg(HttpServletRequest request){
		String queImgType= request.getParameter("queImgType");
	    String queImgName = request.getParameter("queImgId") + ".jpg";
		String projectRealPath  = request.getSession().getServletContext().getRealPath("") + "/";
	    this.deleteQeuImgFile(projectRealPath, queImgName, queImgType);
	    //问卷id不为空，删除表中关于图片的记录的字段
	    if(request.getParameter("queId") != null && !"".equals(request.getParameter("queId"))){
	    	Questionnaire questionnaire = new Questionnaire();
	    	questionnaire.setId(Long.valueOf(request.getParameter("queId")));
	    	questionnaire.setQueImgType(queImgType);
	    	getCommonDao().delete("questionnaire.deleteQueImg",questionnaire);
	    }
	}
    
	/**
	 *  删除图片文件
	 *  @param filePath
	 *  @param fileName
	 *  @author zhaofeng
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-9-13 上午8:57:50
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */

	public void deleteQeuImgFile(String projectRealPath, String imgName,
			String imgType) {
		String imgPath = SystemProperty.getInstance("parameterConfig")
				.getProperty("queImgPath" + imgType);
		// 资源服务器同步状态
		String openStatus = SystemProperty.getInstance("parameterConfig")
				.getProperty("openStatus");
		File file = new File(projectRealPath + imgPath + "/" + imgName);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
		}
		// 同步到资源库
		if (openStatus.equals("open")) {
			String targetURL = SystemProperty.getInstance("parameterConfig")
					.getProperty("appswsUploadURL");
			try {
				HttpClientUtil.deleteFile(imgPath, imgName, targetURL
						+ "delete/file");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
