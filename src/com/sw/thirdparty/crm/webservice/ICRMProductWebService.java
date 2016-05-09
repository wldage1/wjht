/**
 * ICRMProductWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.thirdparty.crm.webservice;

public interface ICRMProductWebService extends java.rmi.Remote {
    public com.sw.thirdparty.crm.webservice.ReturnData updateProduct(com.sw.thirdparty.crm.webservice.CrmProduct arg0) throws java.rmi.RemoteException;
    public com.sw.thirdparty.crm.webservice.CrmProduct[] findProductGtID(java.lang.Long arg0) throws java.rmi.RemoteException;
    public com.sw.thirdparty.crm.webservice.CrmProduct[] findProductPSAndPDGtID(java.lang.Long arg0) throws java.rmi.RemoteException;
    public com.sw.thirdparty.crm.webservice.CrmProduct getOneById(java.lang.Long arg0) throws java.rmi.RemoteException;
}
