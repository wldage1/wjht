/**
 * CrmTradeRecordProduct.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.thirdparty.crm.webservice;

public class CrmTradeRecordProduct  extends com.sw.thirdparty.crm.webservice.BaseEntity  implements java.io.Serializable {
    private java.lang.String checkMemo;

    private java.lang.Integer checkStatus;

    private java.util.Calendar checkTime;

    private java.lang.String checkUserID;

    private java.util.Calendar documentCreateTime;

    private java.lang.String documentType;

    private java.util.Calendar documentUpdateTime;

    private java.util.Calendar followTime;

    private java.lang.String followUserID;

    private java.lang.String measurementUnit;

    private java.lang.Long memberID;

    private java.lang.String pad1;

    private java.lang.String pad2;

    private java.lang.String pad3;

    private java.lang.String pad4;

    private java.lang.String pad5;

    private java.lang.String product;

    private java.lang.Float purchaseAmount;

    private java.lang.Float purchaseFee;

    private java.lang.String purchaseMemo;

    public CrmTradeRecordProduct() {
    }

    public CrmTradeRecordProduct(
           java.lang.Long generatedKey,
           java.lang.Long id,
           java.lang.String[] ids,
           int limitRows,
           int page,
           int rows,
           java.lang.String checkMemo,
           java.lang.Integer checkStatus,
           java.util.Calendar checkTime,
           java.lang.String checkUserID,
           java.util.Calendar documentCreateTime,
           java.lang.String documentType,
           java.util.Calendar documentUpdateTime,
           java.util.Calendar followTime,
           java.lang.String followUserID,
           java.lang.String measurementUnit,
           java.lang.Long memberID,
           java.lang.String pad1,
           java.lang.String pad2,
           java.lang.String pad3,
           java.lang.String pad4,
           java.lang.String pad5,
           java.lang.String product,
           java.lang.Float purchaseAmount,
           java.lang.Float purchaseFee,
           java.lang.String purchaseMemo) {
        super(
            generatedKey,
            id,
            ids,
            limitRows,
            page,
            rows);
        this.checkMemo = checkMemo;
        this.checkStatus = checkStatus;
        this.checkTime = checkTime;
        this.checkUserID = checkUserID;
        this.documentCreateTime = documentCreateTime;
        this.documentType = documentType;
        this.documentUpdateTime = documentUpdateTime;
        this.followTime = followTime;
        this.followUserID = followUserID;
        this.measurementUnit = measurementUnit;
        this.memberID = memberID;
        this.pad1 = pad1;
        this.pad2 = pad2;
        this.pad3 = pad3;
        this.pad4 = pad4;
        this.pad5 = pad5;
        this.product = product;
        this.purchaseAmount = purchaseAmount;
        this.purchaseFee = purchaseFee;
        this.purchaseMemo = purchaseMemo;
    }


    /**
     * Gets the checkMemo value for this CrmTradeRecordProduct.
     * 
     * @return checkMemo
     */
    public java.lang.String getCheckMemo() {
        return checkMemo;
    }


    /**
     * Sets the checkMemo value for this CrmTradeRecordProduct.
     * 
     * @param checkMemo
     */
    public void setCheckMemo(java.lang.String checkMemo) {
        this.checkMemo = checkMemo;
    }


    /**
     * Gets the checkStatus value for this CrmTradeRecordProduct.
     * 
     * @return checkStatus
     */
    public java.lang.Integer getCheckStatus() {
        return checkStatus;
    }


    /**
     * Sets the checkStatus value for this CrmTradeRecordProduct.
     * 
     * @param checkStatus
     */
    public void setCheckStatus(java.lang.Integer checkStatus) {
        this.checkStatus = checkStatus;
    }


    /**
     * Gets the checkTime value for this CrmTradeRecordProduct.
     * 
     * @return checkTime
     */
    public java.util.Calendar getCheckTime() {
        return checkTime;
    }


    /**
     * Sets the checkTime value for this CrmTradeRecordProduct.
     * 
     * @param checkTime
     */
    public void setCheckTime(java.util.Calendar checkTime) {
        this.checkTime = checkTime;
    }


    /**
     * Gets the checkUserID value for this CrmTradeRecordProduct.
     * 
     * @return checkUserID
     */
    public java.lang.String getCheckUserID() {
        return checkUserID;
    }


    /**
     * Sets the checkUserID value for this CrmTradeRecordProduct.
     * 
     * @param checkUserID
     */
    public void setCheckUserID(java.lang.String checkUserID) {
        this.checkUserID = checkUserID;
    }


    /**
     * Gets the documentCreateTime value for this CrmTradeRecordProduct.
     * 
     * @return documentCreateTime
     */
    public java.util.Calendar getDocumentCreateTime() {
        return documentCreateTime;
    }


    /**
     * Sets the documentCreateTime value for this CrmTradeRecordProduct.
     * 
     * @param documentCreateTime
     */
    public void setDocumentCreateTime(java.util.Calendar documentCreateTime) {
        this.documentCreateTime = documentCreateTime;
    }


    /**
     * Gets the documentType value for this CrmTradeRecordProduct.
     * 
     * @return documentType
     */
    public java.lang.String getDocumentType() {
        return documentType;
    }


    /**
     * Sets the documentType value for this CrmTradeRecordProduct.
     * 
     * @param documentType
     */
    public void setDocumentType(java.lang.String documentType) {
        this.documentType = documentType;
    }


    /**
     * Gets the documentUpdateTime value for this CrmTradeRecordProduct.
     * 
     * @return documentUpdateTime
     */
    public java.util.Calendar getDocumentUpdateTime() {
        return documentUpdateTime;
    }


    /**
     * Sets the documentUpdateTime value for this CrmTradeRecordProduct.
     * 
     * @param documentUpdateTime
     */
    public void setDocumentUpdateTime(java.util.Calendar documentUpdateTime) {
        this.documentUpdateTime = documentUpdateTime;
    }


    /**
     * Gets the followTime value for this CrmTradeRecordProduct.
     * 
     * @return followTime
     */
    public java.util.Calendar getFollowTime() {
        return followTime;
    }


    /**
     * Sets the followTime value for this CrmTradeRecordProduct.
     * 
     * @param followTime
     */
    public void setFollowTime(java.util.Calendar followTime) {
        this.followTime = followTime;
    }


    /**
     * Gets the followUserID value for this CrmTradeRecordProduct.
     * 
     * @return followUserID
     */
    public java.lang.String getFollowUserID() {
        return followUserID;
    }


    /**
     * Sets the followUserID value for this CrmTradeRecordProduct.
     * 
     * @param followUserID
     */
    public void setFollowUserID(java.lang.String followUserID) {
        this.followUserID = followUserID;
    }


    /**
     * Gets the measurementUnit value for this CrmTradeRecordProduct.
     * 
     * @return measurementUnit
     */
    public java.lang.String getMeasurementUnit() {
        return measurementUnit;
    }


    /**
     * Sets the measurementUnit value for this CrmTradeRecordProduct.
     * 
     * @param measurementUnit
     */
    public void setMeasurementUnit(java.lang.String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }


    /**
     * Gets the memberID value for this CrmTradeRecordProduct.
     * 
     * @return memberID
     */
    public java.lang.Long getMemberID() {
        return memberID;
    }


    /**
     * Sets the memberID value for this CrmTradeRecordProduct.
     * 
     * @param memberID
     */
    public void setMemberID(java.lang.Long memberID) {
        this.memberID = memberID;
    }


    /**
     * Gets the pad1 value for this CrmTradeRecordProduct.
     * 
     * @return pad1
     */
    public java.lang.String getPad1() {
        return pad1;
    }


    /**
     * Sets the pad1 value for this CrmTradeRecordProduct.
     * 
     * @param pad1
     */
    public void setPad1(java.lang.String pad1) {
        this.pad1 = pad1;
    }


    /**
     * Gets the pad2 value for this CrmTradeRecordProduct.
     * 
     * @return pad2
     */
    public java.lang.String getPad2() {
        return pad2;
    }


    /**
     * Sets the pad2 value for this CrmTradeRecordProduct.
     * 
     * @param pad2
     */
    public void setPad2(java.lang.String pad2) {
        this.pad2 = pad2;
    }


    /**
     * Gets the pad3 value for this CrmTradeRecordProduct.
     * 
     * @return pad3
     */
    public java.lang.String getPad3() {
        return pad3;
    }


    /**
     * Sets the pad3 value for this CrmTradeRecordProduct.
     * 
     * @param pad3
     */
    public void setPad3(java.lang.String pad3) {
        this.pad3 = pad3;
    }


    /**
     * Gets the pad4 value for this CrmTradeRecordProduct.
     * 
     * @return pad4
     */
    public java.lang.String getPad4() {
        return pad4;
    }


    /**
     * Sets the pad4 value for this CrmTradeRecordProduct.
     * 
     * @param pad4
     */
    public void setPad4(java.lang.String pad4) {
        this.pad4 = pad4;
    }


    /**
     * Gets the pad5 value for this CrmTradeRecordProduct.
     * 
     * @return pad5
     */
    public java.lang.String getPad5() {
        return pad5;
    }


    /**
     * Sets the pad5 value for this CrmTradeRecordProduct.
     * 
     * @param pad5
     */
    public void setPad5(java.lang.String pad5) {
        this.pad5 = pad5;
    }


    /**
     * Gets the product value for this CrmTradeRecordProduct.
     * 
     * @return product
     */
    public java.lang.String getProduct() {
        return product;
    }


    /**
     * Sets the product value for this CrmTradeRecordProduct.
     * 
     * @param product
     */
    public void setProduct(java.lang.String product) {
        this.product = product;
    }


    /**
     * Gets the purchaseAmount value for this CrmTradeRecordProduct.
     * 
     * @return purchaseAmount
     */
    public java.lang.Float getPurchaseAmount() {
        return purchaseAmount;
    }


    /**
     * Sets the purchaseAmount value for this CrmTradeRecordProduct.
     * 
     * @param purchaseAmount
     */
    public void setPurchaseAmount(java.lang.Float purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }


    /**
     * Gets the purchaseFee value for this CrmTradeRecordProduct.
     * 
     * @return purchaseFee
     */
    public java.lang.Float getPurchaseFee() {
        return purchaseFee;
    }


    /**
     * Sets the purchaseFee value for this CrmTradeRecordProduct.
     * 
     * @param purchaseFee
     */
    public void setPurchaseFee(java.lang.Float purchaseFee) {
        this.purchaseFee = purchaseFee;
    }


    /**
     * Gets the purchaseMemo value for this CrmTradeRecordProduct.
     * 
     * @return purchaseMemo
     */
    public java.lang.String getPurchaseMemo() {
        return purchaseMemo;
    }


    /**
     * Sets the purchaseMemo value for this CrmTradeRecordProduct.
     * 
     * @param purchaseMemo
     */
    public void setPurchaseMemo(java.lang.String purchaseMemo) {
        this.purchaseMemo = purchaseMemo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CrmTradeRecordProduct)) return false;
        CrmTradeRecordProduct other = (CrmTradeRecordProduct) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.checkMemo==null && other.getCheckMemo()==null) || 
             (this.checkMemo!=null &&
              this.checkMemo.equals(other.getCheckMemo()))) &&
            ((this.checkStatus==null && other.getCheckStatus()==null) || 
             (this.checkStatus!=null &&
              this.checkStatus.equals(other.getCheckStatus()))) &&
            ((this.checkTime==null && other.getCheckTime()==null) || 
             (this.checkTime!=null &&
              this.checkTime.equals(other.getCheckTime()))) &&
            ((this.checkUserID==null && other.getCheckUserID()==null) || 
             (this.checkUserID!=null &&
              this.checkUserID.equals(other.getCheckUserID()))) &&
            ((this.documentCreateTime==null && other.getDocumentCreateTime()==null) || 
             (this.documentCreateTime!=null &&
              this.documentCreateTime.equals(other.getDocumentCreateTime()))) &&
            ((this.documentType==null && other.getDocumentType()==null) || 
             (this.documentType!=null &&
              this.documentType.equals(other.getDocumentType()))) &&
            ((this.documentUpdateTime==null && other.getDocumentUpdateTime()==null) || 
             (this.documentUpdateTime!=null &&
              this.documentUpdateTime.equals(other.getDocumentUpdateTime()))) &&
            ((this.followTime==null && other.getFollowTime()==null) || 
             (this.followTime!=null &&
              this.followTime.equals(other.getFollowTime()))) &&
            ((this.followUserID==null && other.getFollowUserID()==null) || 
             (this.followUserID!=null &&
              this.followUserID.equals(other.getFollowUserID()))) &&
            ((this.measurementUnit==null && other.getMeasurementUnit()==null) || 
             (this.measurementUnit!=null &&
              this.measurementUnit.equals(other.getMeasurementUnit()))) &&
            ((this.memberID==null && other.getMemberID()==null) || 
             (this.memberID!=null &&
              this.memberID.equals(other.getMemberID()))) &&
            ((this.pad1==null && other.getPad1()==null) || 
             (this.pad1!=null &&
              this.pad1.equals(other.getPad1()))) &&
            ((this.pad2==null && other.getPad2()==null) || 
             (this.pad2!=null &&
              this.pad2.equals(other.getPad2()))) &&
            ((this.pad3==null && other.getPad3()==null) || 
             (this.pad3!=null &&
              this.pad3.equals(other.getPad3()))) &&
            ((this.pad4==null && other.getPad4()==null) || 
             (this.pad4!=null &&
              this.pad4.equals(other.getPad4()))) &&
            ((this.pad5==null && other.getPad5()==null) || 
             (this.pad5!=null &&
              this.pad5.equals(other.getPad5()))) &&
            ((this.product==null && other.getProduct()==null) || 
             (this.product!=null &&
              this.product.equals(other.getProduct()))) &&
            ((this.purchaseAmount==null && other.getPurchaseAmount()==null) || 
             (this.purchaseAmount!=null &&
              this.purchaseAmount.equals(other.getPurchaseAmount()))) &&
            ((this.purchaseFee==null && other.getPurchaseFee()==null) || 
             (this.purchaseFee!=null &&
              this.purchaseFee.equals(other.getPurchaseFee()))) &&
            ((this.purchaseMemo==null && other.getPurchaseMemo()==null) || 
             (this.purchaseMemo!=null &&
              this.purchaseMemo.equals(other.getPurchaseMemo())));
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
        if (getCheckMemo() != null) {
            _hashCode += getCheckMemo().hashCode();
        }
        if (getCheckStatus() != null) {
            _hashCode += getCheckStatus().hashCode();
        }
        if (getCheckTime() != null) {
            _hashCode += getCheckTime().hashCode();
        }
        if (getCheckUserID() != null) {
            _hashCode += getCheckUserID().hashCode();
        }
        if (getDocumentCreateTime() != null) {
            _hashCode += getDocumentCreateTime().hashCode();
        }
        if (getDocumentType() != null) {
            _hashCode += getDocumentType().hashCode();
        }
        if (getDocumentUpdateTime() != null) {
            _hashCode += getDocumentUpdateTime().hashCode();
        }
        if (getFollowTime() != null) {
            _hashCode += getFollowTime().hashCode();
        }
        if (getFollowUserID() != null) {
            _hashCode += getFollowUserID().hashCode();
        }
        if (getMeasurementUnit() != null) {
            _hashCode += getMeasurementUnit().hashCode();
        }
        if (getMemberID() != null) {
            _hashCode += getMemberID().hashCode();
        }
        if (getPad1() != null) {
            _hashCode += getPad1().hashCode();
        }
        if (getPad2() != null) {
            _hashCode += getPad2().hashCode();
        }
        if (getPad3() != null) {
            _hashCode += getPad3().hashCode();
        }
        if (getPad4() != null) {
            _hashCode += getPad4().hashCode();
        }
        if (getPad5() != null) {
            _hashCode += getPad5().hashCode();
        }
        if (getProduct() != null) {
            _hashCode += getProduct().hashCode();
        }
        if (getPurchaseAmount() != null) {
            _hashCode += getPurchaseAmount().hashCode();
        }
        if (getPurchaseFee() != null) {
            _hashCode += getPurchaseFee().hashCode();
        }
        if (getPurchaseMemo() != null) {
            _hashCode += getPurchaseMemo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CrmTradeRecordProduct.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("webservice.crm.thirdparty.sw.com", "crmTradeRecordProduct"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkMemo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "checkMemo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "checkStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "checkTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checkUserID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "checkUserID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentCreateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentCreateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentUpdateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentUpdateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("followTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "followTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("followUserID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "followUserID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("measurementUnit");
        elemField.setXmlName(new javax.xml.namespace.QName("", "measurementUnit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "memberID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad5");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("product");
        elemField.setXmlName(new javax.xml.namespace.QName("", "product"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("purchaseAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "purchaseAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("purchaseFee");
        elemField.setXmlName(new javax.xml.namespace.QName("", "purchaseFee"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("purchaseMemo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "purchaseMemo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
