/**
 * ICRMTradeRecordProductWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.thirdparty.crm.webservice;

public interface ICRMTradeRecordProductWebService extends java.rmi.Remote {
    public com.sw.thirdparty.crm.webservice.CrmTradeRecordProduct findCRMTradeRecordProductByID(java.lang.Long arg0) throws java.rmi.RemoteException;
    public com.sw.thirdparty.crm.webservice.CrmTradeRecordProduct[] findCRMTradeRecordProductByMemberID(java.lang.Long arg0) throws java.rmi.RemoteException;
    public com.sw.thirdparty.crm.webservice.CrmTradeRecordProduct[] findCRMTradeRecordProduct() throws java.rmi.RemoteException;
}
