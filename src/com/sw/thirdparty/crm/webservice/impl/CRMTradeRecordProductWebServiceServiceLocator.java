/**
 * CRMTradeRecordProductWebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.thirdparty.crm.webservice.impl;

public class CRMTradeRecordProductWebServiceServiceLocator extends org.apache.axis.client.Service implements com.sw.thirdparty.crm.webservice.impl.CRMTradeRecordProductWebServiceService {

    public CRMTradeRecordProductWebServiceServiceLocator() {
    }


    public CRMTradeRecordProductWebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CRMTradeRecordProductWebServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CRMTradeRecordProductWebServicePort
    private java.lang.String CRMTradeRecordProductWebServicePort_address = "http://localhost:8080/crmsws/service/crmTradeRecordProductWebService";

    public java.lang.String getCRMTradeRecordProductWebServicePortAddress() {
        return CRMTradeRecordProductWebServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CRMTradeRecordProductWebServicePortWSDDServiceName = "CRMTradeRecordProductWebServicePort";

    public java.lang.String getCRMTradeRecordProductWebServicePortWSDDServiceName() {
        return CRMTradeRecordProductWebServicePortWSDDServiceName;
    }

    public void setCRMTradeRecordProductWebServicePortWSDDServiceName(java.lang.String name) {
        CRMTradeRecordProductWebServicePortWSDDServiceName = name;
    }

    public com.sw.thirdparty.crm.webservice.ICRMTradeRecordProductWebService getCRMTradeRecordProductWebServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CRMTradeRecordProductWebServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCRMTradeRecordProductWebServicePort(endpoint);
    }

    public com.sw.thirdparty.crm.webservice.ICRMTradeRecordProductWebService getCRMTradeRecordProductWebServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sw.thirdparty.crm.webservice.impl.CRMTradeRecordProductWebServiceServiceSoapBindingStub _stub = new com.sw.thirdparty.crm.webservice.impl.CRMTradeRecordProductWebServiceServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getCRMTradeRecordProductWebServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCRMTradeRecordProductWebServicePortEndpointAddress(java.lang.String address) {
        CRMTradeRecordProductWebServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.sw.thirdparty.crm.webservice.ICRMTradeRecordProductWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sw.thirdparty.crm.webservice.impl.CRMTradeRecordProductWebServiceServiceSoapBindingStub _stub = new com.sw.thirdparty.crm.webservice.impl.CRMTradeRecordProductWebServiceServiceSoapBindingStub(new java.net.URL(CRMTradeRecordProductWebServicePort_address), this);
                _stub.setPortName(getCRMTradeRecordProductWebServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("CRMTradeRecordProductWebServicePort".equals(inputPortName)) {
            return getCRMTradeRecordProductWebServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.webservice.crm.thirdparty.sw.com/", "CRMTradeRecordProductWebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.webservice.crm.thirdparty.sw.com/", "CRMTradeRecordProductWebServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CRMTradeRecordProductWebServicePort".equals(portName)) {
            setCRMTradeRecordProductWebServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
