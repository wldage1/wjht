package com.sw.core.common;



public class Constant {
	
	/**
	 * 系统log4j日志类型
	 */
	public static final String FRAMEWORK = "FRAMEWORK";	
	public static final String ENCODING = "utf-8";	
	/**数据库类型*/
	public static String DATASOURCE_MYSQL = "mysql";
	public static String DATASOURCE_ORACLE = "oracle";
	public static String DATASOURCE_SQLSERVER = "sqlserver";
	public static String DATASOURCE_DB2 = "db2";
	public static String DATASOURCE_SYBASE = "sybase";
	public static String DATASOURCE_INFORMIX = "informix";
	public static String DATASOURCE_POSTGRES = "postgres";
	/**保存登录会员ID的Session名称*/
	public static final String MEMBER_LOGIN_SESSION_ID = "memberLoginId";
	/**保存登录来源URL的Session名称*/
	public static final String LOGIN_REDIRECTION_URL_SESSION_NAME = "redirectionUrl"; 
	/**密码找回Key分隔符*/
	public static final String PASSWORD_RECOVER_KEY_SEPARATOR = "_";
	/**密码找回Key有效时间（单位：分钟）*/
	public static final int PASSWORD_RECOVER_KEY_PERIOD = 10080; 
	/**保存登录会员用户名的Cookie名称*/
	public static final String LOGIN_MEMBER_USERNAME_COOKIE_NAME = "loginMemberUsername";
	
	public static final String CONYENT = "/content";
	public static final String ALL_USER_MAIN = "ALL_USER_MAIN";
	public static final String ALL_USER_LEFT = "ALL_USER_LEFT";
	public static final String ALL_USER_EXIT = "ALL_USER_EXIT";
	public static final String ALL_USER_LOGOUT = "ALL_USER_LOGOUT";
	public static final String ALL_USER_CONTENT = "ALL_USER_CONTENT";
	public static final String ALL_USER_WELCOME = "ALL_USER_WELCOME";

    /**默认样式表为蓝色系*/
	public static final String STYLE = "blue";
    /**默认样式表为蓝色系*/
	public static final String COMMON = "common";   
	
	/**首页跳转*/
	public static String INDEX = "index";  
    /**主操作页面跳转*/
	public static String MAIN = "main";
	 /**错误页面跳转*/
	public static String ERROR = "error";
    /**主操作页面左侧菜单*/
	public static String LEFT = "left";    
    /**欢迎页面*/
	public static String WELCOME = "welcome";   
    /**退出系统*/
	public static String LOGOUT = "logout"; 
    /**退出系统*/
	public static String EXIT = "exit"; 
	/**json返回状态*/
	public static String STATUS = "status";
	/**是否输出自定义控制器访问信息*/
	public static boolean IS_OUTPUT = false;
	public static String DEFAULT_DATABASE_TYPE;
	
	/**会员常量定义*/
	public static String MEMBER_SOURCE_TERMINA = "1";//终端注册
	public static String MEMBER_SOURCE_REGISTRATION = "2";//代注册
	public static String MEMBER_SOURCE_CRM = "3";//嘉华CRM
	public static String MEMBER_SOURCE_JHLC = "4";//嘉华理财网
	
	public static String DICT_TYPE_CRM_LEVEL = "CRMLevel";
	public static String DICT_TYPE_LEVEL = "level";
	public static String DICT_TYPE_DATASOURCE = "dataSource";
	public static String DICT_TYPE_GENDER = "gender";
	public static String DICT_TYPE_MEMBERSTATUS = "memberStatus";
	public static String DICT_TYPE_PRODUCTSTRUCTUREVERIFY = "productStructureVerify";
	public static String DICT_TYPE_PRODUCTSTRUCTURESHOWTYPE = "productStructureShowType";
	public static String DICT_TYPE_PRODUCTSTRUCTUREDATATYPE = "productStructureDataType";
	public static String DICT_TYPE_PWQUESTION = "pwQuestion";
	
	public static int MEMBER_DELSTATUS_NORMAL = 0;
	public static int MEMBER_DELSTATUS_DEL = 1;
	public static String MEMBER_STATUS_START = "1";
	public static String MEMBER_STATUS_STOP = "2";
	
	/**投资需求常量定义*/
	
	//投资需求产品类型
	public static String DICT_TYPE_PRODUCT_TYPE = "productType";
	//投资期限
	public static String DICT_TYPE_INVESDEADLINE = "investDeadline";
	//预期收益率
	public static String DICT_TYPE_YIELD = "yield";
	//预计投资时间
	public static String DICT_TYPE_PREDICTTIME = "predictTime";
	
	/**预约处理状态*/
	public static Integer RESERVATION_DISPOSE_STATE_NO = 0;    //未处理
	public static Integer RESERVATION_DISPOSE_STATE_YES = 1;	//已处理
	
	/** 消息常量定义*/
	public static String MSG_TYPE_AUDITINGSTATE = "auditingState";
	public static String MSG_TYPE_MSGSOURCE = "msgSource";
	
	public static String MSG_MSGSOURCE_LOCAL = "1";   //本系统添加
	public static String MSG_MSGSOURCE_LCSL = "2";   //理财沙龙邀请
	public static String MSG_MSGSOURCE_YJBG = "3";   //研究报告
	public static String MSG_MSGSOURCE_HYZX = "4";   //行业资讯
	public static String MSG_MSGSOURCE_DCWJ = "5";   //调查问卷
	public static String MSG_MSGSOURCE_LCSLFK = "6";   //理财沙龙反馈
	
	public static String MSG_AUDITINGSTATE_AUDITING = "1"; //待审核
	public static String MSG_AUDITINGSTATE_AUDIT = "2"; //审核通过
	public static String MSG_AUDITINGSTATE_AUDITED = "3"; //审核驳回
	
	/** 产品管理常量定义*/
	public static String PRD_TYPE_PRODUCTSTATUE = "productStatus";     //产品状态
	public static String PRD_TYPE_PRODUCTFROM = "productFrom";		//产品来源
	public static String PRD_TYPE_PRODUCTTYPE = "productType";		//产品类型
	public static String PRD_TYPE_CRMADDEDFLAG = "addedFlag";		//CRM产品上架状态
	
	
	/**产品类型*/
	public static String PRD_PRODUCTTYPE_TRUSTKINDOFFIXEDINCOME = "1";	//信托类固定收益
	public static String PRD_PRODUCTTYPE_SUNSHINEPRIVATEFUNDS = "2";		//阳光私募基金
	public static String PRD_PRODUCTTYPE_BROKERSSETFINANCIAL = "3";			//券商集合理财
	public static String PRD_PRODUCTTYPE_PE = "4";			//股权投资基金（PE）
	public static String PRD_PRODUCTTYPE_BANKFINANCIALPRODUCTS = "5";			//银行理财产品
	public static String PRD_PRODUCTTYPE_OTHERS = "6";			//其他
	/**产品状态*/
	public static String PRD_PRODUCTSTATE_START = "1";     //	发布
	public static String PRD_PRODUCTSTATE_STOP = "2";       //未发布
	/**CRM产品更新状态*/
	public static Integer PRD_PRODUCTUPDFLAG_NORMAL = 0;     //	正常
	public static Integer PRD_PRODUCTUPDFLAG_UPDATE = 1;       //更新
	/**产品来源*/
	public static String PRD_PRODUCTFROM_LOCAL = "1";		//系统创建
	public static String PRD_PRODUCTFROM_CRM = "2";			//CRM
	/**产品属性显示类型*/
	public static String PRD_SHOWTYPE_TEXT = "1";		//文本输入框
	public static String PRD_SHOWTYPE_SELECT = "2";	//下拉列表
	public static String PRD_SHOWTYPE_RADIO = "3";		//单选按钮
	public static String PRD_SHOWTYPE_CHECKBOX = "4";	//复选框
	public static String PRD_SHOWTYPE_TEXTAREA = "5";	//文本域
	public static String PRD_SHOWTYPE_DATE = "6";		//时间
	
			//CRM
	
	/**理财沙龙消息*/
	public static String SALON_MESSAGE_START = "1";
	public static String SALON_MESSAGE_STOP = "2";
	public static String SALON_MESSAGE_STATES= "msgStatus";
	
	/**问卷调查题型*/
	public static long QUE_QUESTION_TYPE_SINGLE_CHOICE = 1;
	public static long QUE_QUESTION_TYPE_MULTIPLE_CHOICE = 2;
	public static long QUE_QUESTION_TYPE_BLANK = 3;
	public static long QUE_QUESTION_TYPE_SHORTANSWER = 4;
	
	/**问卷调查状态*/
	public static long QUE_QUESTIONNAIRE_OPEN_FLAG_TRUE = 1;  //已发布
	public static long QUE_QUESTIONNAIRE_OPEN_FLAG_FALSE = 0; //未发布

	/** 短信发送 */
	public static final String ACCOUNT_SID = "181f13d0347c655770d80c4861f2f882";
	public static final String AUTH_TOKEN = "f0d73a98ed0163ce5b225c042cc97b8e";
	public static final String APPID = "f0fac203d73b4ddd9dcfa85604b741e4";
}
