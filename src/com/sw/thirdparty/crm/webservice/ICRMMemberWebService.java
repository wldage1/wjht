/**
 * ICRMMemberWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.thirdparty.crm.webservice;

public interface ICRMMemberWebService extends java.rmi.Remote {
    public java.lang.Long countCRMMemberByMobilePhone(java.lang.String arg0) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception;
    public com.sw.thirdparty.crm.webservice.CrmMember getCRMMemberById(java.lang.Long arg0) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception;
    public com.sw.thirdparty.crm.webservice.CrmMember getCRMMemberByMobilePhone(java.lang.String arg0) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception;
    public com.sw.thirdparty.crm.webservice.CrmMember[] findCRMTradeClientInfoByTime(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception;
    public com.sw.thirdparty.crm.webservice.CrmMember[] getCRMMemberArrayByMobilephones(java.lang.String[] arg0) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception;
    public com.sw.thirdparty.crm.webservice.CrmMember findMemberCFP(java.lang.Long arg0) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception;
    public com.sw.thirdparty.crm.webservice.CrmMember[] getCRMMemberInfoArrayByID(java.lang.String[] arg0) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception;
    public com.sw.thirdparty.crm.webservice.CrmMember authenticationMember(java.lang.String arg0, java.lang.String arg1) throws java.rmi.RemoteException, com.sw.thirdparty.crm.webservice.Exception;
}
