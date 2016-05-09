/**
 * IFCTrustWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.thirdparty.crm.webservice;

public interface IFCTrustWebService extends java.rmi.Remote {
    public com.sw.thirdparty.crm.webservice.FcTrustNav[] findFCTrustNav(java.lang.String[] arg0) throws java.rmi.RemoteException;
    public com.sw.thirdparty.crm.webservice.FcTrust[] findFCTrust(java.lang.String[] arg0) throws java.rmi.RemoteException;
    public com.sw.thirdparty.crm.webservice.FcTrustFQNav[] findFCTrustFQNav(java.lang.String[] arg0) throws java.rmi.RemoteException;
    public com.sw.thirdparty.crm.webservice.FcTrustNav findPurchaseNav(com.sw.thirdparty.crm.webservice.FcTrustNav arg0) throws java.rmi.RemoteException;
}
