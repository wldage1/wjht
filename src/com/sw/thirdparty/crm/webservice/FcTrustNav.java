/**
 * FcTrustNav.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.thirdparty.crm.webservice;

public class FcTrustNav  extends com.sw.thirdparty.crm.webservice.BaseEntity  implements java.io.Serializable {
    private float nav;

    private java.lang.String navDate;

    private java.lang.String queryPram;

    private java.lang.String trustCode;

    private java.lang.String[] trustCodes;

    public FcTrustNav() {
    }

    public FcTrustNav(
           java.lang.Long generatedKey,
           java.lang.Long id,
           java.lang.String[] ids,
           int limitRows,
           int page,
           int rows,
           float nav,
           java.lang.String navDate,
           java.lang.String queryPram,
           java.lang.String trustCode,
           java.lang.String[] trustCodes) {
        super(
            generatedKey,
            id,
            ids,
            limitRows,
            page,
            rows);
        this.nav = nav;
        this.navDate = navDate;
        this.queryPram = queryPram;
        this.trustCode = trustCode;
        this.trustCodes = trustCodes;
    }


    /**
     * Gets the nav value for this FcTrustNav.
     * 
     * @return nav
     */
    public float getNav() {
        return nav;
    }


    /**
     * Sets the nav value for this FcTrustNav.
     * 
     * @param nav
     */
    public void setNav(float nav) {
        this.nav = nav;
    }


    /**
     * Gets the navDate value for this FcTrustNav.
     * 
     * @return navDate
     */
    public java.lang.String getNavDate() {
        return navDate;
    }


    /**
     * Sets the navDate value for this FcTrustNav.
     * 
     * @param navDate
     */
    public void setNavDate(java.lang.String navDate) {
        this.navDate = navDate;
    }


    /**
     * Gets the queryPram value for this FcTrustNav.
     * 
     * @return queryPram
     */
    public java.lang.String getQueryPram() {
        return queryPram;
    }


    /**
     * Sets the queryPram value for this FcTrustNav.
     * 
     * @param queryPram
     */
    public void setQueryPram(java.lang.String queryPram) {
        this.queryPram = queryPram;
    }


    /**
     * Gets the trustCode value for this FcTrustNav.
     * 
     * @return trustCode
     */
    public java.lang.String getTrustCode() {
        return trustCode;
    }


    /**
     * Sets the trustCode value for this FcTrustNav.
     * 
     * @param trustCode
     */
    public void setTrustCode(java.lang.String trustCode) {
        this.trustCode = trustCode;
    }


    /**
     * Gets the trustCodes value for this FcTrustNav.
     * 
     * @return trustCodes
     */
    public java.lang.String[] getTrustCodes() {
        return trustCodes;
    }


    /**
     * Sets the trustCodes value for this FcTrustNav.
     * 
     * @param trustCodes
     */
    public void setTrustCodes(java.lang.String[] trustCodes) {
        this.trustCodes = trustCodes;
    }

    public java.lang.String getTrustCodes(int i) {
        return this.trustCodes[i];
    }

    public void setTrustCodes(int i, java.lang.String _value) {
        this.trustCodes[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FcTrustNav)) return false;
        FcTrustNav other = (FcTrustNav) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.nav == other.getNav() &&
            ((this.navDate==null && other.getNavDate()==null) || 
             (this.navDate!=null &&
              this.navDate.equals(other.getNavDate()))) &&
            ((this.queryPram==null && other.getQueryPram()==null) || 
             (this.queryPram!=null &&
              this.queryPram.equals(other.getQueryPram()))) &&
            ((this.trustCode==null && other.getTrustCode()==null) || 
             (this.trustCode!=null &&
              this.trustCode.equals(other.getTrustCode()))) &&
            ((this.trustCodes==null && other.getTrustCodes()==null) || 
             (this.trustCodes!=null &&
              java.util.Arrays.equals(this.trustCodes, other.getTrustCodes())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        _hashCode += new Float(getNav()).hashCode();
        if (getNavDate() != null) {
            _hashCode += getNavDate().hashCode();
        }
        if (getQueryPram() != null) {
            _hashCode += getQueryPram().hashCode();
        }
        if (getTrustCode() != null) {
            _hashCode += getTrustCode().hashCode();
        }
        if (getTrustCodes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTrustCodes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTrustCodes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FcTrustNav.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("webservice.crm.thirdparty.sw.com", "fcTrustNav"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nav");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nav"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("navDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "navDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("queryPram");
        elemField.setXmlName(new javax.xml.namespace.QName("", "queryPram"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trustCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trustCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trustCodes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trustCodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
