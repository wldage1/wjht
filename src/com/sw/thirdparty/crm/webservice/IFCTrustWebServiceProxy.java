package com.sw.thirdparty.crm.webservice;

public class IFCTrustWebServiceProxy implements com.sw.thirdparty.crm.webservice.IFCTrustWebService {
  private String _endpoint = null;
  private com.sw.thirdparty.crm.webservice.IFCTrustWebService iFCTrustWebService = null;
  
  public IFCTrustWebServiceProxy() {
    _initIFCTrustWebServiceProxy();
  }
  
  public IFCTrustWebServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIFCTrustWebServiceProxy();
  }
  
  private void _initIFCTrustWebServiceProxy() {
    try {
      iFCTrustWebService = (new com.sw.thirdparty.finchina.webservice.impl.FCTrustWebServiceServiceLocator()).getFCTrustWebServicePort();
      if (iFCTrustWebService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iFCTrustWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iFCTrustWebService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iFCTrustWebService != null)
      ((javax.xml.rpc.Stub)iFCTrustWebService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.sw.thirdparty.crm.webservice.IFCTrustWebService getIFCTrustWebService() {
    if (iFCTrustWebService == null)
      _initIFCTrustWebServiceProxy();
    return iFCTrustWebService;
  }
  
  public com.sw.thirdparty.crm.webservice.FcTrustNav[] findFCTrustNav(java.lang.String[] arg0) throws java.rmi.RemoteException{
    if (iFCTrustWebService == null)
      _initIFCTrustWebServiceProxy();
    return iFCTrustWebService.findFCTrustNav(arg0);
  }
  
  public com.sw.thirdparty.crm.webservice.FcTrust[] findFCTrust(java.lang.String[] arg0) throws java.rmi.RemoteException{
    if (iFCTrustWebService == null)
      _initIFCTrustWebServiceProxy();
    return iFCTrustWebService.findFCTrust(arg0);
  }
  
  public com.sw.thirdparty.crm.webservice.FcTrustFQNav[] findFCTrustFQNav(java.lang.String[] arg0) throws java.rmi.RemoteException{
    if (iFCTrustWebService == null)
      _initIFCTrustWebServiceProxy();
    return iFCTrustWebService.findFCTrustFQNav(arg0);
  }
  
  public com.sw.thirdparty.crm.webservice.FcTrustNav findPurchaseNav(com.sw.thirdparty.crm.webservice.FcTrustNav arg0) throws java.rmi.RemoteException{
    if (iFCTrustWebService == null)
      _initIFCTrustWebServiceProxy();
    return iFCTrustWebService.findPurchaseNav(arg0);
  }
  
  
}