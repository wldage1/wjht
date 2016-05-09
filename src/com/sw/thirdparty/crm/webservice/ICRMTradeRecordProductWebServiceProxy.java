package com.sw.thirdparty.crm.webservice;

public class ICRMTradeRecordProductWebServiceProxy implements com.sw.thirdparty.crm.webservice.ICRMTradeRecordProductWebService {
  private String _endpoint = null;
  private com.sw.thirdparty.crm.webservice.ICRMTradeRecordProductWebService iCRMTradeRecordProductWebService = null;
  
  public ICRMTradeRecordProductWebServiceProxy() {
    _initICRMTradeRecordProductWebServiceProxy();
  }
  
  public ICRMTradeRecordProductWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initICRMTradeRecordProductWebServiceProxy();
  }
  
  private void _initICRMTradeRecordProductWebServiceProxy() {
    try {
      iCRMTradeRecordProductWebService = (new com.sw.thirdparty.crm.webservice.impl.CRMTradeRecordProductWebServiceServiceLocator()).getCRMTradeRecordProductWebServicePort();
      if (iCRMTradeRecordProductWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iCRMTradeRecordProductWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iCRMTradeRecordProductWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iCRMTradeRecordProductWebService != null)
      ((javax.xml.rpc.Stub)iCRMTradeRecordProductWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.sw.thirdparty.crm.webservice.ICRMTradeRecordProductWebService getICRMTradeRecordProductWebService() {
    if (iCRMTradeRecordProductWebService == null)
      _initICRMTradeRecordProductWebServiceProxy();
    return iCRMTradeRecordProductWebService;
  }
  
  public com.sw.thirdparty.crm.webservice.CrmTradeRecordProduct findCRMTradeRecordProductByID(java.lang.Long arg0) throws java.rmi.RemoteException{
    if (iCRMTradeRecordProductWebService == null)
      _initICRMTradeRecordProductWebServiceProxy();
    return iCRMTradeRecordProductWebService.findCRMTradeRecordProductByID(arg0);
  }
  
  public com.sw.thirdparty.crm.webservice.CrmTradeRecordProduct[] findCRMTradeRecordProductByMemberID(java.lang.Long arg0) throws java.rmi.RemoteException{
    if (iCRMTradeRecordProductWebService == null)
      _initICRMTradeRecordProductWebServiceProxy();
    return iCRMTradeRecordProductWebService.findCRMTradeRecordProductByMemberID(arg0);
  }
  
  public com.sw.thirdparty.crm.webservice.CrmTradeRecordProduct[] findCRMTradeRecordProduct() throws java.rmi.RemoteException{
    if (iCRMTradeRecordProductWebService == null)
      _initICRMTradeRecordProductWebServiceProxy();
    return iCRMTradeRecordProductWebService.findCRMTradeRecordProduct();
  }
  
  
}