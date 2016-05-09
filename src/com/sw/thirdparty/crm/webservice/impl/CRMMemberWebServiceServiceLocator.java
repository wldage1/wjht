/**
 * CRMMemberWebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.thirdparty.crm.webservice.impl;

public class CRMMemberWebServiceServiceLocator extends org.apache.axis.client.Service implements com.sw.thirdparty.crm.webservice.impl.CRMMemberWebServiceService {

    public CRMMemberWebServiceServiceLocator() {
    }


    public CRMMemberWebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CRMMemberWebServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CRMMemberWebServicePort
    private java.lang.String CRMMemberWebServicePort_address = "http://192.168.71.122:8080/crmsws/service/crmMemberWebService";

    public java.lang.String getCRMMemberWebServicePortAddress() {
        return CRMMemberWebServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CRMMemberWebServicePortWSDDServiceName = "CRMMemberWebServicePort";

    public java.lang.String getCRMMemberWebServicePortWSDDServiceName() {
        return CRMMemberWebServicePortWSDDServiceName;
    }

    public void setCRMMemberWebServicePortWSDDServiceName(java.lang.String name) {
        CRMMemberWebServicePortWSDDServiceName = name;
    }

    public com.sw.thirdparty.crm.webservice.ICRMMemberWebService getCRMMemberWebServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CRMMemberWebServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCRMMemberWebServicePort(endpoint);
    }

    public com.sw.thirdparty.crm.webservice.ICRMMemberWebService getCRMMemberWebServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sw.thirdparty.crm.webservice.impl.CRMMemberWebServiceServiceSoapBindingStub _stub = new com.sw.thirdparty.crm.webservice.impl.CRMMemberWebServiceServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getCRMMemberWebServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCRMMemberWebServicePortEndpointAddress(java.lang.String address) {
        CRMMemberWebServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.sw.thirdparty.crm.webservice.ICRMMemberWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sw.thirdparty.crm.webservice.impl.CRMMemberWebServiceServiceSoapBindingStub _stub = new com.sw.thirdparty.crm.webservice.impl.CRMMemberWebServiceServiceSoapBindingStub(new java.net.URL(CRMMemberWebServicePort_address), this);
                _stub.setPortName(getCRMMemberWebServicePortWSDDServiceName());
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
        if ("CRMMemberWebServicePort".equals(inputPortName)) {
            return getCRMMemberWebServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.webservice.crm.thirdparty.sw.com/", "CRMMemberWebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.webservice.crm.thirdparty.sw.com/", "CRMMemberWebServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CRMMemberWebServicePort".equals(portName)) {
            setCRMMemberWebServicePortEndpointAddress(address);
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
