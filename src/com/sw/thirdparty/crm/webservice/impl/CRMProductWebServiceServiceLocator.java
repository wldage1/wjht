/**
 * CRMProductWebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.thirdparty.crm.webservice.impl;

public class CRMProductWebServiceServiceLocator extends org.apache.axis.client.Service implements com.sw.thirdparty.crm.webservice.impl.CRMProductWebServiceService {

    public CRMProductWebServiceServiceLocator() {
    }


    public CRMProductWebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CRMProductWebServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CRMProductWebServicePort
    private java.lang.String CRMProductWebServicePort_address = "http://124.207.165.67:8080/crmsws/service/crmProductWebService";

    public java.lang.String getCRMProductWebServicePortAddress() {
        return CRMProductWebServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CRMProductWebServicePortWSDDServiceName = "CRMProductWebServicePort";

    public java.lang.String getCRMProductWebServicePortWSDDServiceName() {
        return CRMProductWebServicePortWSDDServiceName;
    }

    public void setCRMProductWebServicePortWSDDServiceName(java.lang.String name) {
        CRMProductWebServicePortWSDDServiceName = name;
    }

    public com.sw.thirdparty.crm.webservice.ICRMProductWebService getCRMProductWebServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CRMProductWebServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCRMProductWebServicePort(endpoint);
    }

    public com.sw.thirdparty.crm.webservice.ICRMProductWebService getCRMProductWebServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sw.thirdparty.crm.webservice.impl.CRMProductWebServiceServiceSoapBindingStub _stub = new com.sw.thirdparty.crm.webservice.impl.CRMProductWebServiceServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getCRMProductWebServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCRMProductWebServicePortEndpointAddress(java.lang.String address) {
        CRMProductWebServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.sw.thirdparty.crm.webservice.ICRMProductWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sw.thirdparty.crm.webservice.impl.CRMProductWebServiceServiceSoapBindingStub _stub = new com.sw.thirdparty.crm.webservice.impl.CRMProductWebServiceServiceSoapBindingStub(new java.net.URL(CRMProductWebServicePort_address), this);
                _stub.setPortName(getCRMProductWebServicePortWSDDServiceName());
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
        if ("CRMProductWebServicePort".equals(inputPortName)) {
            return getCRMProductWebServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.webservice.crm.thirdparty.sw.com/", "CRMProductWebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.webservice.crm.thirdparty.sw.com/", "CRMProductWebServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CRMProductWebServicePort".equals(portName)) {
            setCRMProductWebServicePortEndpointAddress(address);
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
