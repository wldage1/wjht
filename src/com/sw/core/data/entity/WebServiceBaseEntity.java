/**
 * WebServiceBaseEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.core.data.entity;

public class WebServiceBaseEntity  implements java.io.Serializable {
    private java.lang.String databaseType;

    private java.lang.Long generatedKey;

    private java.lang.Long id;

    private java.lang.String[] ids;

    private int limitRows;

    private int page;

    private int rows;

    private int skipResult;

    public WebServiceBaseEntity() {
    }

    public WebServiceBaseEntity(
           java.lang.String databaseType,
           java.lang.Long generatedKey,
           java.lang.Long id,
           java.lang.String[] ids,
           int limitRows,
           int page,
           int rows,
           int skipResult) {
           this.databaseType = databaseType;
           this.generatedKey = generatedKey;
           this.id = id;
           this.ids = ids;
           this.limitRows = limitRows;
           this.page = page;
           this.rows = rows;
           this.skipResult = skipResult;
    }


    /**
     * Gets the databaseType value for this WebServiceBaseEntity.
     * 
     * @return databaseType
     */
    public java.lang.String getDatabaseType() {
        return databaseType;
    }


    /**
     * Sets the databaseType value for this WebServiceBaseEntity.
     * 
     * @param databaseType
     */
    public void setDatabaseType(java.lang.String databaseType) {
        this.databaseType = databaseType;
    }


    /**
     * Gets the generatedKey value for this WebServiceBaseEntity.
     * 
     * @return generatedKey
     */
    public java.lang.Long getGeneratedKey() {
        return generatedKey;
    }


    /**
     * Sets the generatedKey value for this WebServiceBaseEntity.
     * 
     * @param generatedKey
     */
    public void setGeneratedKey(java.lang.Long generatedKey) {
        this.generatedKey = generatedKey;
    }


    /**
     * Gets the id value for this WebServiceBaseEntity.
     * 
     * @return id
     */
    public java.lang.Long getId() {
        return id;
    }


    /**
     * Sets the id value for this WebServiceBaseEntity.
     * 
     * @param id
     */
    public void setId(java.lang.Long id) {
        this.id = id;
    }


    /**
     * Gets the ids value for this WebServiceBaseEntity.
     * 
     * @return ids
     */
    public java.lang.String[] getIds() {
        return ids;
    }


    /**
     * Sets the ids value for this WebServiceBaseEntity.
     * 
     * @param ids
     */
    public void setIds(java.lang.String[] ids) {
        this.ids = ids;
    }


    /**
     * Gets the limitRows value for this WebServiceBaseEntity.
     * 
     * @return limitRows
     */
    public int getLimitRows() {
        return limitRows;
    }


    /**
     * Sets the limitRows value for this WebServiceBaseEntity.
     * 
     * @param limitRows
     */
    public void setLimitRows(int limitRows) {
        this.limitRows = limitRows;
    }


    /**
     * Gets the page value for this WebServiceBaseEntity.
     * 
     * @return page
     */
    public int getPage() {
        return page;
    }


    /**
     * Sets the page value for this WebServiceBaseEntity.
     * 
     * @param page
     */
    public void setPage(int page) {
        this.page = page;
    }


    /**
     * Gets the rows value for this WebServiceBaseEntity.
     * 
     * @return rows
     */
    public int getRows() {
        return rows;
    }


    /**
     * Sets the rows value for this WebServiceBaseEntity.
     * 
     * @param rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }


    /**
     * Gets the skipResult value for this WebServiceBaseEntity.
     * 
     * @return skipResult
     */
    public int getSkipResult() {
        return skipResult;
    }


    /**
     * Sets the skipResult value for this WebServiceBaseEntity.
     * 
     * @param skipResult
     */
    public void setSkipResult(int skipResult) {
        this.skipResult = skipResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WebServiceBaseEntity)) return false;
        WebServiceBaseEntity other = (WebServiceBaseEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.databaseType==null && other.getDatabaseType()==null) || 
             (this.databaseType!=null &&
              this.databaseType.equals(other.getDatabaseType()))) &&
            ((this.generatedKey==null && other.getGeneratedKey()==null) || 
             (this.generatedKey!=null &&
              this.generatedKey.equals(other.getGeneratedKey()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.ids==null && other.getIds()==null) || 
             (this.ids!=null &&
              java.util.Arrays.equals(this.ids, other.getIds()))) &&
            this.limitRows == other.getLimitRows() &&
            this.page == other.getPage() &&
            this.rows == other.getRows() &&
            this.skipResult == other.getSkipResult();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDatabaseType() != null) {
            _hashCode += getDatabaseType().hashCode();
        }
        if (getGeneratedKey() != null) {
            _hashCode += getGeneratedKey().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += getLimitRows();
        _hashCode += getPage();
        _hashCode += getRows();
        _hashCode += getSkipResult();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WebServiceBaseEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entity.data.core.sw.com", "WebServiceBaseEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("databaseType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.data.core.sw.com", "databaseType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("generatedKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.data.core.sw.com", "generatedKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.data.core.sw.com", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ids");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.data.core.sw.com", "ids"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://impl.webservice.product.thirdparty.sw.com", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("limitRows");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.data.core.sw.com", "limitRows"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("page");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.data.core.sw.com", "page"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rows");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.data.core.sw.com", "rows"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("skipResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity.data.core.sw.com", "skipResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
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
