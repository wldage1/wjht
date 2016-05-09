package com.sw.thirdparty.crm.webservice;

public class ICRMProductWebServiceProxy implements com.sw.thirdparty.crm.webservice.ICRMProductWebService {
  private String _endpoint = null;
  private com.sw.thirdparty.crm.webservice.ICRMProductWebService iCRMProductWebService = null;
  
  public ICRMProductWebServiceProxy() {
    _initICRMProductWebServiceProxy();
  }
  
  public ICRMProductWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initICRMProductWebServiceProxy();
  }
  
  private void _initICRMProductWebServiceProxy() {
    try {
      iCRMProductWebService = (new com.sw.thirdparty.crm.webservice.impl.CRMProductWebServiceServiceLocator()).getCRMProductWebServicePort();
      if (iCRMProductWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iCRMProductWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iCRMProductWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iCRMProductWebService != null)
      ((javax.xml.rpc.Stub)iCRMProductWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.sw.thirdparty.crm.webservice.ICRMProductWebService getICRMProductWebService() {
    if (iCRMProductWebService == null)
      _initICRMProductWebServiceProxy();
    return iCRMProductWebService;
  }
  
  public com.sw.thirdparty.crm.webservice.ReturnData updateProduct(com.sw.thirdparty.crm.webservice.CrmProduct arg0) throws java.rmi.RemoteException{
    if (iCRMProductWebService == null)
      _initICRMProductWebServiceProxy();
    return iCRMProductWebService.updateProduct(arg0);
  }
  
  public com.sw.thirdparty.crm.webservice.CrmProduct[] findProductGtID(java.lang.Long arg0) throws java.rmi.RemoteException{
    if (iCRMProductWebService == null)
      _initICRMProductWebServiceProxy();
    return iCRMProductWebService.findProductGtID(arg0);
  }
  
  public com.sw.thirdparty.crm.webservice.CrmProduct[] findProductPSAndPDGtID(java.lang.Long arg0) throws java.rmi.RemoteException{
    if (iCRMProductWebService == null)
      _initICRMProductWebServiceProxy();
    return iCRMProductWebService.findProductPSAndPDGtID(arg0);
  }
  
  public com.sw.thirdparty.crm.webservice.CrmProduct getOneById(java.lang.Long arg0) throws java.rmi.RemoteException{
    if (iCRMProductWebService == null)
      _initICRMProductWebServiceProxy();
    return iCRMProductWebService.getOneById(arg0);
  }
  
  
}