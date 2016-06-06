/**
 * @author Glan.duanyj
 * @date 2014-05-12
 * @project rest_demo
 */
package com.sw.core.ucpaas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sw.core.common.Constant;
import com.sw.core.ucpaas.client.AbsRestClient;
import com.sw.core.ucpaas.client.JsonReqClient;
import com.sw.core.ucpaas.client.XmlReqClient;

public class SMSRest {
	private String accountSid;
	private String authToken;
	
	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	static AbsRestClient InstantiationRestAPI(boolean enable) {
		if(enable) {
			return new JsonReqClient();
		} else {
			return new XmlReqClient();
		}
	}
	public static void testFindAccount(boolean json,String accountSid,String authToken){
		try {
			String result=InstantiationRestAPI(json).findAccoutInfo(accountSid, authToken);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void testCreateClient(boolean json,String accountSid,String authToken,String appId,String clientName
			,String chargeType,String charge,String mobile){
		try {
			String result=InstantiationRestAPI(json).createClient(accountSid, authToken, appId, clientName, chargeType, charge,mobile);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void testfindClients(boolean json,String accountSid,String authToken,String appId,String start
			,String limit){
		try {
			String result=InstantiationRestAPI(json).findClients(accountSid, authToken, appId, start, limit);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void testfindClientByNbr(boolean json,String accountSid,String authToken,String clientNumber,String appId){
		try {
			String result=InstantiationRestAPI(json).findClientByNbr(accountSid, authToken, clientNumber,appId);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void testCloseClient(boolean json,String accountSid,String authToken,String clientNumber,String appId){
		try {
			String result=InstantiationRestAPI(json).closeClient(accountSid, authToken, clientNumber,appId);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void testChargeClient(boolean json,String accountSid,String authToken,String clientNumber,
			String chargeType,String charge,String appId){
		try {
			String result=InstantiationRestAPI(json).charegeClient(accountSid, authToken, clientNumber, chargeType, charge,appId);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void testBillList(boolean json,String accountSid,String authToken,String appId,String date){
		try {
			String result=InstantiationRestAPI(json).billList(accountSid, authToken, appId, date);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void testClientBillList(boolean json,String accountSid,String authToken,String appId,String clientNumber,String date){
		try {
			String result=InstantiationRestAPI(json).clientBillList(accountSid, authToken, appId, clientNumber, date);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void testCallback(boolean json,String accountSid,String authToken,String appId,String fromClient,String to,String fromSerNum,String toSerNum){
		try {
			String result=InstantiationRestAPI(json).callback(accountSid, authToken, appId, fromClient, to,fromSerNum,toSerNum);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void testVoiceCode(boolean json,String accountSid,String authToken,String appId,String to,String verifyCode){
		try {
			String result=InstantiationRestAPI(json).voiceCode(accountSid, authToken, appId, to, verifyCode);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void testTemplateSMS(boolean json,String accountSid,String authToken,String appId,String templateId,String to,String param){
		try {
			String result=InstantiationRestAPI(json).templateSMS(accountSid, authToken, appId, templateId, to, param);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void testfindClientByMobile(boolean json,String accountSid,String authToken,String mobile,String appId){
		try {
			String result=InstantiationRestAPI(json).findClientByMobile(accountSid, authToken, mobile, appId);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void testDispalyNumber(boolean json,String accountSid,String authToken,String appId,String clientNumber,String display){
		try {
			String result=InstantiationRestAPI(json).dispalyNumber(accountSid, authToken, appId, clientNumber, display);
			System.out.println("Response content is: " + result);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void sendSms(String templateId, String to, String content){
		//是否开启短信发送功能
		String isOpen = SysConfig.getInstance().getProperty("is_open");
		if(isOpen.equals("1")){
			testTemplateSMS(true, Constant.ACCOUNT_SID, Constant.AUTH_TOKEN, Constant.APPID, templateId, to, content);
		}
	}
	
	
	
	/**
	 * 测试说明 参数顺序，请参照各具体方法参数名称
	 * 参数名称含义，请参考rest api 文档
	 * @author Glan.duanyj
	 * @date 2014-06-30
	 * @param params[]
	 * @return void
	 * @throws IOException 
	 * @method main
	 */
	public static void main(String[] args) {
		//微金汇通	25083	错误提醒	 普通模板	
		//微金汇通	23985	卡拒绝	 普通模板	
		//微金汇通	22041	卡激活	 普通模板	
		//微金汇通	22042	消费通知	 普通模板	//尊敬的用户{1}已得到商城确认，商品会在7-10个工作日寄出;"您的卡号位数为"+ cardNum +"的消费卡购买产品（"+ consume.getDescription() +"价值"+consume.getMoney()+"元，余额"+ money +"元）";
		//微金汇通	22040	发卡提醒	 普通模板	
		//微金汇通	22039	申请购物卡	 普通模板	
		//尊敬的用户{1}，给您带来的困扰请谅解。“尊敬的用户xx,您的额度公司已经处理，目前已经解决，您现在的额度是297320,给您带来的困扰请谅解”
		//String content = "王磊，您的额度我公司已经处理，目前已经解决，您现在的额度是300000";
		String content = "卞论，您的卡号位数为"+ 2421 +"的消费卡购买产品（"+ "HTC VR" +"价值"+6680+"元）";
		SMSRest.sendSms("22042", "18502412421", content);
		//SMSRest.sendSms("25083", "18240148334", content);
	}
	
	/*public static void main(String[] args) throws IOException {
		sendSms("22042", "18240148334", null);
		
		String accountSid="181f13d0347c655770d80c4861f2f882";
		String token="f0d73a98ed0163ce5b225c042cc97b8e";
//		String jsonStr="{\"client\":\"1\"}";
//		JSONObject obj=JSONObject.fromObject(jsonStr);
//		System.out.println(obj.getInt("client"));
		System.out.println("请输入参数，以空格隔开...");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String param=br.readLine();
		String [] params=param.split(" ");
		String method = params[0];
		boolean json=true;
		if (params[1].equals("xml")) {
			json=false;
		}
		if (method.equals("1")) {
//			String accountSid="181f13d0347c655770d80c4861f2f882";
//			String token="f0d73a98ed0163ce5b225c042cc97b8e";
			testFindAccount(json,accountSid,token);
		}else if (method.equals("2")) {
			testCreateClient(json, params[2],params[3], params[4], params[5], params[6], params[7], params[8]);
		}else if (method.equals("3")) {
//			String accountSid="";
//			String token="";
			String appId="";
			testfindClients(json,accountSid,token,appId,"0","5");
		}else if (method.equals("4")) {
			testfindClientByNbr(json,params[2],params[3], params[4], params[5]);
		}else if (method.equals("5")) {
			testCloseClient(json, params[2],params[3], params[4], params[5]);
		}else if (method.equals("6")) {
			testChargeClient(json, params[2],params[3], params[4], params[5], params[6], params[7]);
		}else if (method.equals("7")) {
			testBillList(json, params[2], params[3],params[4], params[5]);
		}else if (method.equals("8")) {
			testClientBillList(json, params[2], params[3],params[4],params[5], params[6]);
		}else if (method.equals("9")) {
			//String accountSid = "";// 主账户Id
			String authToken="";
			String appId="";
			accountSid="";
			authToken="";
			appId="";
			String fromClient="";
			String to="";
			String fromSerNum="";
			String toSerNum="";
			testCallback(json, accountSid, authToken, appId, fromClient, to, fromSerNum, toSerNum);
		}else if (method.equals("10")) {
			String to="";
//			String accountSid="";
//			String token="";
			String appId="";
			String para = "";
			testVoiceCode(json, accountSid, token, appId, to, para);
		}else if (method.equals("11")) { //短信验证码 
//			String accountSid="";
//			String token="";
			String appId="f0fac203d73b4ddd9dcfa85604b741e4";
			String templateId="22042";
			String to="18240148334";
			String para="张作霖张(张作霖)作霖张作霖张作霖";
			testTemplateSMS(json, accountSid,token,appId, templateId,to,para);
		}else if (method.equals("12")) {
			testfindClientByMobile(json, params[2],params[3], params[4], params[5]);
		}else if (method.equals("13")) {
//			String accountSid="";
//			String token="";
			String clientNumber="";
			String appId="";
			String display="1";
			testDispalyNumber(json, accountSid, token, appId, clientNumber, display);
		}
	}*/
}
