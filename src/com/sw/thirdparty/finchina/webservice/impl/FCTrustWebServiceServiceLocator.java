/**
 * FCTrustWebServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.thirdparty.finchina.webservice.impl;

public class FCTrustWebServiceServiceLocator extends org.apache.axis.client.Service implements com.sw.thirdparty.finchina.webservice.impl.FCTrustWebServiceService {

    public FCTrustWebServiceServiceLocator() {
    }


    public FCTrustWebServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public FCTrustWebServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for FCTrustWebServicePort
    private java.lang.String FCTrustWebServicePort_address = "http://localhost:8080/crmsws/service/fcTrustWebService";

    public java.lang.String getFCTrustWebServicePortAddress() {
        return FCTrustWebServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String FCTrustWebServicePortWSDDServiceName = "FCTrustWebServicePort";

    public java.lang.String getFCTrustWebServicePortWSDDServiceName() {
        return FCTrustWebServicePortWSDDServiceName;
    }

    public void setFCTrustWebServicePortWSDDServiceName(java.lang.String name) {
        FCTrustWebServicePortWSDDServiceName = name;
    }

    public com.sw.thirdparty.crm.webservice.IFCTrustWebService getFCTrustWebServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(FCTrustWebServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getFCTrustWebServicePort(endpoint);
    }

    public com.sw.thirdparty.crm.webservice.IFCTrustWebService getFCTrustWebServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sw.thirdparty.finchina.webservice.impl.FCTrustWebServiceServiceSoapBindingStub _stub = new com.sw.thirdparty.finchina.webservice.impl.FCTrustWebServiceServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getFCTrustWebServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setFCTrustWebServicePortEndpointAddress(java.lang.String address) {
        FCTrustWebServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.sw.thirdparty.crm.webservice.IFCTrustWebService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sw.thirdparty.finchina.webservice.impl.FCTrustWebServiceServiceSoapBindingStub _stub = new com.sw.thirdparty.finchina.webservice.impl.FCTrustWebServiceServiceSoapBindingStub(new java.net.URL(FCTrustWebServicePort_address), this);
                _stub.setPortName(getFCTrustWebServicePortWSDDServiceName());
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
        if ("FCTrustWebServicePort".equals(inputPortName)) {
            return getFCTrustWebServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.webservice.finchina.thirdparty.sw.com/", "FCTrustWebServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.webservice.finchina.thirdparty.sw.com/", "FCTrustWebServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("FCTrustWebServicePort".equals(portName)) {
            setFCTrustWebServicePortEndpointAddress(address);
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
