/**
 * FcTrustFQNav.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.thirdparty.crm.webservice;

public class FcTrustFQNav  extends com.sw.thirdparty.crm.webservice.BaseEntity  implements java.io.Serializable {
    private java.lang.String date;

    private java.math.BigDecimal ltdr;

    private java.lang.String trustCode;

    private java.lang.String[] trustCodes;

    public FcTrustFQNav() {
    }

    public FcTrustFQNav(
           java.lang.Long generatedKey,
           java.lang.Long id,
           java.lang.String[] ids,
           int limitRows,
           int page,
           int rows,
           java.lang.String date,
           java.math.BigDecimal ltdr,
           java.lang.String trustCode,
           java.lang.String[] trustCodes) {
        super(
            generatedKey,
            id,
            ids,
            limitRows,
            page,
            rows);
        this.date = date;
        this.ltdr = ltdr;
        this.trustCode = trustCode;
        this.trustCodes = trustCodes;
    }


    /**
     * Gets the date value for this FcTrustFQNav.
     * 
     * @return date
     */
    public java.lang.String getDate() {
        return date;
    }


    /**
     * Sets the date value for this FcTrustFQNav.
     * 
     * @param date
     */
    public void setDate(java.lang.String date) {
        this.date = date;
    }


    /**
     * Gets the ltdr value for this FcTrustFQNav.
     * 
     * @return ltdr
     */
    public java.math.BigDecimal getLtdr() {
        return ltdr;
    }


    /**
     * Sets the ltdr value for this FcTrustFQNav.
     * 
     * @param ltdr
     */
    public void setLtdr(java.math.BigDecimal ltdr) {
        this.ltdr = ltdr;
    }


    /**
     * Gets the trustCode value for this FcTrustFQNav.
     * 
     * @return trustCode
     */
    public java.lang.String getTrustCode() {
        return trustCode;
    }


    /**
     * Sets the trustCode value for this FcTrustFQNav.
     * 
     * @param trustCode
     */
    public void setTrustCode(java.lang.String trustCode) {
        this.trustCode = trustCode;
    }


    /**
     * Gets the trustCodes value for this FcTrustFQNav.
     * 
     * @return trustCodes
     */
    public java.lang.String[] getTrustCodes() {
        return trustCodes;
    }


    /**
     * Sets the trustCodes value for this FcTrustFQNav.
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
        if (!(obj instanceof FcTrustFQNav)) return false;
        FcTrustFQNav other = (FcTrustFQNav) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.date==null && other.getDate()==null) || 
             (this.date!=null &&
              this.date.equals(other.getDate()))) &&
            ((this.ltdr==null && other.getLtdr()==null) || 
             (this.ltdr!=null &&
              this.ltdr.equals(other.getLtdr()))) &&
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
        if (getDate() != null) {
            _hashCode += getDate().hashCode();
        }
        if (getLtdr() != null) {
            _hashCode += getLtdr().hashCode();
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
        new org.apache.axis.description.TypeDesc(FcTrustFQNav.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("webservice.crm.thirdparty.sw.com", "fcTrustFQNav"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("date");
        elemField.setXmlName(new javax.xml.namespace.QName("", "date"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ltdr");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ltdr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
