package com.sw.thirdparty.crm.webservice;

public class ICRMMemberWebServiceProxy implements com.sw.thirdparty.crm.webservice.ICRMMemberWebService {
  private String _endpoint = null;
  private com.sw.thirdparty.crm.webservice.ICRMMemberWebService iCRMMemberWebService = null;
  
  public ICRMMemberWebServiceProxy() {
    _initICRMMemberWebServiceProxy();
  }
  
  public ICRMMemberWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initICRMMemberWebServiceProxy();
  }
  
  private void _initICRMMemberWebServiceProxy() {
    try {
      iCRMMemberWebService = (new com.sw.thirdparty.crm.webservice.impl.CRMMemberWebServiceServiceLocator()).getCRMMemberWebServicePort();
      if (iCRMMemberWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iCRMMemberWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iCRMMemberWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iCRMMemberWebService != null)
      ((javax.xml.rpc.Stub)iCRMMemberWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.sw.thirdparty.crm.webservice.ICRMMemberWebService getICRMMemberWebService() {
    if (iCRMMemberWebService == null)
      _initICRMMemberWebServiceProxy();
    return iCRMMemberWebService;
  }
  
  public java.lang.Long countCRMMemberByMobilePhone(java.lang.String arg0) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception{
    if (iCRMMemberWebService == null)
      _initICRMMemberWebServiceProxy();
    return iCRMMemberWebService.countCRMMemberByMobilePhone(arg0);
  }
  
  public com.sw.thirdparty.crm.webservice.CrmMember getCRMMemberById(java.lang.Long arg0) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception{
    if (iCRMMemberWebService == null)
      _initICRMMemberWebServiceProxy();
    return iCRMMemberWebService.getCRMMemberById(arg0);
  }
  
  public com.sw.thirdparty.crm.webservice.CrmMember getCRMMemberByMobilePhone(java.lang.String arg0) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception{
    if (iCRMMemberWebService == null)
      _initICRMMemberWebServiceProxy();
    return iCRMMemberWebService.getCRMMemberByMobilePhone(arg0);
  }
  
  public com.sw.thirdparty.crm.webservice.CrmMember[] findCRMTradeClientInfoByTime(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception{
    if (iCRMMemberWebService == null)
      _initICRMMemberWebServiceProxy();
    return iCRMMemberWebService.findCRMTradeClientInfoByTime(arg0, arg1);
  }
  
  public com.sw.thirdparty.crm.webservice.CrmMember[] getCRMMemberArrayByMobilephones(java.lang.String[] arg0) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception{
    if (iCRMMemberWebService == null)
      _initICRMMemberWebServiceProxy();
    return iCRMMemberWebService.getCRMMemberArrayByMobilephones(arg0);
  }
  
  public com.sw.thirdparty.crm.webservice.CrmMember findMemberCFP(java.lang.Long arg0) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception{
    if (iCRMMemberWebService == null)
      _initICRMMemberWebServiceProxy();
    return iCRMMemberWebService.findMemberCFP(arg0);
  }
  
  public com.sw.thirdparty.crm.webservice.CrmMember[] getCRMMemberInfoArrayByID(java.lang.String[] arg0) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception{
    if (iCRMMemberWebService == null)
      _initICRMMemberWebServiceProxy();
    return iCRMMemberWebService.getCRMMemberInfoArrayByID(arg0);
  }
  
  public com.sw.thirdparty.crm.webservice.CrmMember authenticationMember(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception{
    if (iCRMMemberWebService == null)
      _initICRMMemberWebServiceProxy();
    return iCRMMemberWebService.authenticationMember(arg0, arg1);
  }
  
  
}