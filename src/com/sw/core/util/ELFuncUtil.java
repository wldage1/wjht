package com.sw.core.util;


public class ELFuncUtil {  
    /** 
     * EL方法用于获取继续操作连接 
     *  
     * @param requestMap 
     * @param sessionMap 
     * @return 
     */  
    public static String controller(java.util.Map requestMap,java.util.Map sessionMap) {  
    	return (String) sessionMap.get("controller_"+requestMap.get("statusParam"));
    } 
    /** 
     * EL方法用于获取返回街垒连接 
     *  
     * @param requestMap 
     * @param sessionMap 
     * @return 
     */
    public static String parentcontroller(java.util.Map requestMap,java.util.Map sessionMap) {  
    	return (String) sessionMap.get("parent_controller_"+requestMap.get("statusParam"));
    } 
}  