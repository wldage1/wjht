/**
 * CrmProduct.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.thirdparty.crm.webservice;

public class CrmProduct  extends com.sw.thirdparty.crm.webservice.BaseEntity  implements java.io.Serializable {
    private java.lang.Float commisionRate;

    private java.util.Calendar createTime;

    private java.lang.Float factCommisionRate;

    private com.sw.thirdparty.crm.webservice.FcTrust fcTrust;

    private java.lang.String ladderCommisionRate;

    private java.lang.String pad1;

    private java.lang.String pad10;

    private java.lang.String pad2;

    private java.lang.String pad3;

    private java.lang.String pad4;

    private java.lang.String pad5;

    private java.lang.String pad6;

    private java.lang.String pad7;

    private java.lang.String pad8;

    private java.lang.String pad9;

    private java.lang.String pd;

    private java.lang.String productAdvice;

    private java.lang.String productDefType;

    private java.lang.String productDesc;

    private java.util.Calendar productFinishTime;

    private java.util.Calendar productFoundTime;

    private java.lang.String productIncome;

    private java.lang.String productName;

    private java.lang.String productStatus;

    private java.lang.Integer productTerm;

    private java.lang.String productType;

    private java.lang.String ps;

    private java.lang.String purchasePhase;

    private java.lang.Float purchaseStartPoint;

    private java.util.Calendar saleEndTime;

    private java.util.Calendar saleStartTime;

    private java.lang.String source;

    private java.lang.String ventureCharacter;

    public CrmProduct() {
    }

    public CrmProduct(
           java.lang.Long generatedKey,
           java.lang.Long id,
           java.lang.String[] ids,
           int limitRows,
           int page,
           int rows,
           java.lang.Float commisionRate,
           java.util.Calendar createTime,
           java.lang.Float factCommisionRate,
           com.sw.thirdparty.crm.webservice.FcTrust fcTrust,
           java.lang.String ladderCommisionRate,
           java.lang.String pad1,
           java.lang.String pad10,
           java.lang.String pad2,
           java.lang.String pad3,
           java.lang.String pad4,
           java.lang.String pad5,
           java.lang.String pad6,
           java.lang.String pad7,
           java.lang.String pad8,
           java.lang.String pad9,
           java.lang.String pd,
           java.lang.String productAdvice,
           java.lang.String productDefType,
           java.lang.String productDesc,
           java.util.Calendar productFinishTime,
           java.util.Calendar productFoundTime,
           java.lang.String productIncome,
           java.lang.String productName,
           java.lang.String productStatus,
           java.lang.Integer productTerm,
           java.lang.String productType,
           java.lang.String ps,
           java.lang.String purchasePhase,
           java.lang.Float purchaseStartPoint,
           java.util.Calendar saleEndTime,
           java.util.Calendar saleStartTime,
           java.lang.String source,
           java.lang.String ventureCharacter) {
        super(
            generatedKey,
            id,
            ids,
            limitRows,
            page,
            rows);
        this.commisionRate = commisionRate;
        this.createTime = createTime;
        this.factCommisionRate = factCommisionRate;
        this.fcTrust = fcTrust;
        this.ladderCommisionRate = ladderCommisionRate;
        this.pad1 = pad1;
        this.pad10 = pad10;
        this.pad2 = pad2;
        this.pad3 = pad3;
        this.pad4 = pad4;
        this.pad5 = pad5;
        this.pad6 = pad6;
        this.pad7 = pad7;
        this.pad8 = pad8;
        this.pad9 = pad9;
        this.pd = pd;
        this.productAdvice = productAdvice;
        this.productDefType = productDefType;
        this.productDesc = productDesc;
        this.productFinishTime = productFinishTime;
        this.productFoundTime = productFoundTime;
        this.productIncome = productIncome;
        this.productName = productName;
        this.productStatus = productStatus;
        this.productTerm = productTerm;
        this.productType = productType;
        this.ps = ps;
        this.purchasePhase = purchasePhase;
        this.purchaseStartPoint = purchaseStartPoint;
        this.saleEndTime = saleEndTime;
        this.saleStartTime = saleStartTime;
        this.source = source;
        this.ventureCharacter = ventureCharacter;
    }


    /**
     * Gets the commisionRate value for this CrmProduct.
     * 
     * @return commisionRate
     */
    public java.lang.Float getCommisionRate() {
        return commisionRate;
    }


    /**
     * Sets the commisionRate value for this CrmProduct.
     * 
     * @param commisionRate
     */
    public void setCommisionRate(java.lang.Float commisionRate) {
        this.commisionRate = commisionRate;
    }


    /**
     * Gets the createTime value for this CrmProduct.
     * 
     * @return createTime
     */
    public java.util.Calendar getCreateTime() {
        return createTime;
    }


    /**
     * Sets the createTime value for this CrmProduct.
     * 
     * @param createTime
     */
    public void setCreateTime(java.util.Calendar createTime) {
        this.createTime = createTime;
    }


    /**
     * Gets the factCommisionRate value for this CrmProduct.
     * 
     * @return factCommisionRate
     */
    public java.lang.Float getFactCommisionRate() {
        return factCommisionRate;
    }


    /**
     * Sets the factCommisionRate value for this CrmProduct.
     * 
     * @param factCommisionRate
     */
    public void setFactCommisionRate(java.lang.Float factCommisionRate) {
        this.factCommisionRate = factCommisionRate;
    }


    /**
     * Gets the fcTrust value for this CrmProduct.
     * 
     * @return fcTrust
     */
    public com.sw.thirdparty.crm.webservice.FcTrust getFcTrust() {
        return fcTrust;
    }


    /**
     * Sets the fcTrust value for this CrmProduct.
     * 
     * @param fcTrust
     */
    public void setFcTrust(com.sw.thirdparty.crm.webservice.FcTrust fcTrust) {
        this.fcTrust = fcTrust;
    }


    /**
     * Gets the ladderCommisionRate value for this CrmProduct.
     * 
     * @return ladderCommisionRate
     */
    public java.lang.String getLadderCommisionRate() {
        return ladderCommisionRate;
    }


    /**
     * Sets the ladderCommisionRate value for this CrmProduct.
     * 
     * @param ladderCommisionRate
     */
    public void setLadderCommisionRate(java.lang.String ladderCommisionRate) {
        this.ladderCommisionRate = ladderCommisionRate;
    }


    /**
     * Gets the pad1 value for this CrmProduct.
     * 
     * @return pad1
     */
    public java.lang.String getPad1() {
        return pad1;
    }


    /**
     * Sets the pad1 value for this CrmProduct.
     * 
     * @param pad1
     */
    public void setPad1(java.lang.String pad1) {
        this.pad1 = pad1;
    }


    /**
     * Gets the pad10 value for this CrmProduct.
     * 
     * @return pad10
     */
    public java.lang.String getPad10() {
        return pad10;
    }


    /**
     * Sets the pad10 value for this CrmProduct.
     * 
     * @param pad10
     */
    public void setPad10(java.lang.String pad10) {
        this.pad10 = pad10;
    }


    /**
     * Gets the pad2 value for this CrmProduct.
     * 
     * @return pad2
     */
    public java.lang.String getPad2() {
        return pad2;
    }


    /**
     * Sets the pad2 value for this CrmProduct.
     * 
     * @param pad2
     */
    public void setPad2(java.lang.String pad2) {
        this.pad2 = pad2;
    }


    /**
     * Gets the pad3 value for this CrmProduct.
     * 
     * @return pad3
     */
    public java.lang.String getPad3() {
        return pad3;
    }


    /**
     * Sets the pad3 value for this CrmProduct.
     * 
     * @param pad3
     */
    public void setPad3(java.lang.String pad3) {
        this.pad3 = pad3;
    }


    /**
     * Gets the pad4 value for this CrmProduct.
     * 
     * @return pad4
     */
    public java.lang.String getPad4() {
        return pad4;
    }


    /**
     * Sets the pad4 value for this CrmProduct.
     * 
     * @param pad4
     */
    public void setPad4(java.lang.String pad4) {
        this.pad4 = pad4;
    }


    /**
     * Gets the pad5 value for this CrmProduct.
     * 
     * @return pad5
     */
    public java.lang.String getPad5() {
        return pad5;
    }


    /**
     * Sets the pad5 value for this CrmProduct.
     * 
     * @param pad5
     */
    public void setPad5(java.lang.String pad5) {
        this.pad5 = pad5;
    }


    /**
     * Gets the pad6 value for this CrmProduct.
     * 
     * @return pad6
     */
    public java.lang.String getPad6() {
        return pad6;
    }


    /**
     * Sets the pad6 value for this CrmProduct.
     * 
     * @param pad6
     */
    public void setPad6(java.lang.String pad6) {
        this.pad6 = pad6;
    }


    /**
     * Gets the pad7 value for this CrmProduct.
     * 
     * @return pad7
     */
    public java.lang.String getPad7() {
        return pad7;
    }


    /**
     * Sets the pad7 value for this CrmProduct.
     * 
     * @param pad7
     */
    public void setPad7(java.lang.String pad7) {
        this.pad7 = pad7;
    }


    /**
     * Gets the pad8 value for this CrmProduct.
     * 
     * @return pad8
     */
    public java.lang.String getPad8() {
        return pad8;
    }


    /**
     * Sets the pad8 value for this CrmProduct.
     * 
     * @param pad8
     */
    public void setPad8(java.lang.String pad8) {
        this.pad8 = pad8;
    }


    /**
     * Gets the pad9 value for this CrmProduct.
     * 
     * @return pad9
     */
    public java.lang.String getPad9() {
        return pad9;
    }


    /**
     * Sets the pad9 value for this CrmProduct.
     * 
     * @param pad9
     */
    public void setPad9(java.lang.String pad9) {
        this.pad9 = pad9;
    }


    /**
     * Gets the pd value for this CrmProduct.
     * 
     * @return pd
     */
    public java.lang.String getPd() {
        return pd;
    }


    /**
     * Sets the pd value for this CrmProduct.
     * 
     * @param pd
     */
    public void setPd(java.lang.String pd) {
        this.pd = pd;
    }


    /**
     * Gets the productAdvice value for this CrmProduct.
     * 
     * @return productAdvice
     */
    public java.lang.String getProductAdvice() {
        return productAdvice;
    }


    /**
     * Sets the productAdvice value for this CrmProduct.
     * 
     * @param productAdvice
     */
    public void setProductAdvice(java.lang.String productAdvice) {
        this.productAdvice = productAdvice;
    }


    /**
     * Gets the productDefType value for this CrmProduct.
     * 
     * @return productDefType
     */
    public java.lang.String getProductDefType() {
        return productDefType;
    }


    /**
     * Sets the productDefType value for this CrmProduct.
     * 
     * @param productDefType
     */
    public void setProductDefType(java.lang.String productDefType) {
        this.productDefType = productDefType;
    }


    /**
     * Gets the productDesc value for this CrmProduct.
     * 
     * @return productDesc
     */
    public java.lang.String getProductDesc() {
        return productDesc;
    }


    /**
     * Sets the productDesc value for this CrmProduct.
     * 
     * @param productDesc
     */
    public void setProductDesc(java.lang.String productDesc) {
        this.productDesc = productDesc;
    }


    /**
     * Gets the productFinishTime value for this CrmProduct.
     * 
     * @return productFinishTime
     */
    public java.util.Calendar getProductFinishTime() {
        return productFinishTime;
    }


    /**
     * Sets the productFinishTime value for this CrmProduct.
     * 
     * @param productFinishTime
     */
    public void setProductFinishTime(java.util.Calendar productFinishTime) {
        this.productFinishTime = productFinishTime;
    }


    /**
     * Gets the productFoundTime value for this CrmProduct.
     * 
     * @return productFoundTime
     */
    public java.util.Calendar getProductFoundTime() {
        return productFoundTime;
    }


    /**
     * Sets the productFoundTime value for this CrmProduct.
     * 
     * @param productFoundTime
     */
    public void setProductFoundTime(java.util.Calendar productFoundTime) {
        this.productFoundTime = productFoundTime;
    }


    /**
     * Gets the productIncome value for this CrmProduct.
     * 
     * @return productIncome
     */
    public java.lang.String getProductIncome() {
        return productIncome;
    }


    /**
     * Sets the productIncome value for this CrmProduct.
     * 
     * @param productIncome
     */
    public void setProductIncome(java.lang.String productIncome) {
        this.productIncome = productIncome;
    }


    /**
     * Gets the productName value for this CrmProduct.
     * 
     * @return productName
     */
    public java.lang.String getProductName() {
        return productName;
    }


    /**
     * Sets the productName value for this CrmProduct.
     * 
     * @param productName
     */
    public void setProductName(java.lang.String productName) {
        this.productName = productName;
    }


    /**
     * Gets the productStatus value for this CrmProduct.
     * 
     * @return productStatus
     */
    public java.lang.String getProductStatus() {
        return productStatus;
    }


    /**
     * Sets the productStatus value for this CrmProduct.
     * 
     * @param productStatus
     */
    public void setProductStatus(java.lang.String productStatus) {
        this.productStatus = productStatus;
    }


    /**
     * Gets the productTerm value for this CrmProduct.
     * 
     * @return productTerm
     */
    public java.lang.Integer getProductTerm() {
        return productTerm;
    }


    /**
     * Sets the productTerm value for this CrmProduct.
     * 
     * @param productTerm
     */
    public void setProductTerm(java.lang.Integer productTerm) {
        this.productTerm = productTerm;
    }


    /**
     * Gets the productType value for this CrmProduct.
     * 
     * @return productType
     */
    public java.lang.String getProductType() {
        return productType;
    }


    /**
     * Sets the productType value for this CrmProduct.
     * 
     * @param productType
     */
    public void setProductType(java.lang.String productType) {
        this.productType = productType;
    }


    /**
     * Gets the ps value for this CrmProduct.
     * 
     * @return ps
     */
    public java.lang.String getPs() {
        return ps;
    }


    /**
     * Sets the ps value for this CrmProduct.
     * 
     * @param ps
     */
    public void setPs(java.lang.String ps) {
        this.ps = ps;
    }


    /**
     * Gets the purchasePhase value for this CrmProduct.
     * 
     * @return purchasePhase
     */
    public java.lang.String getPurchasePhase() {
        return purchasePhase;
    }


    /**
     * Sets the purchasePhase value for this CrmProduct.
     * 
     * @param purchasePhase
     */
    public void setPurchasePhase(java.lang.String purchasePhase) {
        this.purchasePhase = purchasePhase;
    }


    /**
     * Gets the purchaseStartPoint value for this CrmProduct.
     * 
     * @return purchaseStartPoint
     */
    public java.lang.Float getPurchaseStartPoint() {
        return purchaseStartPoint;
    }


    /**
     * Sets the purchaseStartPoint value for this CrmProduct.
     * 
     * @param purchaseStartPoint
     */
    public void setPurchaseStartPoint(java.lang.Float purchaseStartPoint) {
        this.purchaseStartPoint = purchaseStartPoint;
    }


    /**
     * Gets the saleEndTime value for this CrmProduct.
     * 
     * @return saleEndTime
     */
    public java.util.Calendar getSaleEndTime() {
        return saleEndTime;
    }


    /**
     * Sets the saleEndTime value for this CrmProduct.
     * 
     * @param saleEndTime
     */
    public void setSaleEndTime(java.util.Calendar saleEndTime) {
        this.saleEndTime = saleEndTime;
    }


    /**
     * Gets the saleStartTime value for this CrmProduct.
     * 
     * @return saleStartTime
     */
    public java.util.Calendar getSaleStartTime() {
        return saleStartTime;
    }


    /**
     * Sets the saleStartTime value for this CrmProduct.
     * 
     * @param saleStartTime
     */
    public void setSaleStartTime(java.util.Calendar saleStartTime) {
        this.saleStartTime = saleStartTime;
    }


    /**
     * Gets the source value for this CrmProduct.
     * 
     * @return source
     */
    public java.lang.String getSource() {
        return source;
    }


    /**
     * Sets the source value for this CrmProduct.
     * 
     * @param source
     */
    public void setSource(java.lang.String source) {
        this.source = source;
    }


    /**
     * Gets the ventureCharacter value for this CrmProduct.
     * 
     * @return ventureCharacter
     */
    public java.lang.String getVentureCharacter() {
        return ventureCharacter;
    }


    /**
     * Sets the ventureCharacter value for this CrmProduct.
     * 
     * @param ventureCharacter
     */
    public void setVentureCharacter(java.lang.String ventureCharacter) {
        this.ventureCharacter = ventureCharacter;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CrmProduct)) return false;
        CrmProduct other = (CrmProduct) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.commisionRate==null && other.getCommisionRate()==null) || 
             (this.commisionRate!=null &&
              this.commisionRate.equals(other.getCommisionRate()))) &&
            ((this.createTime==null && other.getCreateTime()==null) || 
             (this.createTime!=null &&
              this.createTime.equals(other.getCreateTime()))) &&
            ((this.factCommisionRate==null && other.getFactCommisionRate()==null) || 
             (this.factCommisionRate!=null &&
              this.factCommisionRate.equals(other.getFactCommisionRate()))) &&
            ((this.fcTrust==null && other.getFcTrust()==null) || 
             (this.fcTrust!=null &&
              this.fcTrust.equals(other.getFcTrust()))) &&
            ((this.ladderCommisionRate==null && other.getLadderCommisionRate()==null) || 
             (this.ladderCommisionRate!=null &&
              this.ladderCommisionRate.equals(other.getLadderCommisionRate()))) &&
            ((this.pad1==null && other.getPad1()==null) || 
             (this.pad1!=null &&
              this.pad1.equals(other.getPad1()))) &&
            ((this.pad10==null && other.getPad10()==null) || 
             (this.pad10!=null &&
              this.pad10.equals(other.getPad10()))) &&
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
            ((this.pad6==null && other.getPad6()==null) || 
             (this.pad6!=null &&
              this.pad6.equals(other.getPad6()))) &&
            ((this.pad7==null && other.getPad7()==null) || 
             (this.pad7!=null &&
              this.pad7.equals(other.getPad7()))) &&
            ((this.pad8==null && other.getPad8()==null) || 
             (this.pad8!=null &&
              this.pad8.equals(other.getPad8()))) &&
            ((this.pad9==null && other.getPad9()==null) || 
             (this.pad9!=null &&
              this.pad9.equals(other.getPad9()))) &&
            ((this.pd==null && other.getPd()==null) || 
             (this.pd!=null &&
              this.pd.equals(other.getPd()))) &&
            ((this.productAdvice==null && other.getProductAdvice()==null) || 
             (this.productAdvice!=null &&
              this.productAdvice.equals(other.getProductAdvice()))) &&
            ((this.productDefType==null && other.getProductDefType()==null) || 
             (this.productDefType!=null &&
              this.productDefType.equals(other.getProductDefType()))) &&
            ((this.productDesc==null && other.getProductDesc()==null) || 
             (this.productDesc!=null &&
              this.productDesc.equals(other.getProductDesc()))) &&
            ((this.productFinishTime==null && other.getProductFinishTime()==null) || 
             (this.productFinishTime!=null &&
              this.productFinishTime.equals(other.getProductFinishTime()))) &&
            ((this.productFoundTime==null && other.getProductFoundTime()==null) || 
             (this.productFoundTime!=null &&
              this.productFoundTime.equals(other.getProductFoundTime()))) &&
            ((this.productIncome==null && other.getProductIncome()==null) || 
             (this.productIncome!=null &&
              this.productIncome.equals(other.getProductIncome()))) &&
            ((this.productName==null && other.getProductName()==null) || 
             (this.productName!=null &&
              this.productName.equals(other.getProductName()))) &&
            ((this.productStatus==null && other.getProductStatus()==null) || 
             (this.productStatus!=null &&
              this.productStatus.equals(other.getProductStatus()))) &&
            ((this.productTerm==null && other.getProductTerm()==null) || 
             (this.productTerm!=null &&
              this.productTerm.equals(other.getProductTerm()))) &&
            ((this.productType==null && other.getProductType()==null) || 
             (this.productType!=null &&
              this.productType.equals(other.getProductType()))) &&
            ((this.ps==null && other.getPs()==null) || 
             (this.ps!=null &&
              this.ps.equals(other.getPs()))) &&
            ((this.purchasePhase==null && other.getPurchasePhase()==null) || 
             (this.purchasePhase!=null &&
              this.purchasePhase.equals(other.getPurchasePhase()))) &&
            ((this.purchaseStartPoint==null && other.getPurchaseStartPoint()==null) || 
             (this.purchaseStartPoint!=null &&
              this.purchaseStartPoint.equals(other.getPurchaseStartPoint()))) &&
            ((this.saleEndTime==null && other.getSaleEndTime()==null) || 
             (this.saleEndTime!=null &&
              this.saleEndTime.equals(other.getSaleEndTime()))) &&
            ((this.saleStartTime==null && other.getSaleStartTime()==null) || 
             (this.saleStartTime!=null &&
              this.saleStartTime.equals(other.getSaleStartTime()))) &&
            ((this.source==null && other.getSource()==null) || 
             (this.source!=null &&
              this.source.equals(other.getSource()))) &&
            ((this.ventureCharacter==null && other.getVentureCharacter()==null) || 
             (this.ventureCharacter!=null &&
              this.ventureCharacter.equals(other.getVentureCharacter())));
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
        if (getCommisionRate() != null) {
            _hashCode += getCommisionRate().hashCode();
        }
        if (getCreateTime() != null) {
            _hashCode += getCreateTime().hashCode();
        }
        if (getFactCommisionRate() != null) {
            _hashCode += getFactCommisionRate().hashCode();
        }
        if (getFcTrust() != null) {
            _hashCode += getFcTrust().hashCode();
        }
        if (getLadderCommisionRate() != null) {
            _hashCode += getLadderCommisionRate().hashCode();
        }
        if (getPad1() != null) {
            _hashCode += getPad1().hashCode();
        }
        if (getPad10() != null) {
            _hashCode += getPad10().hashCode();
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
        if (getPad6() != null) {
            _hashCode += getPad6().hashCode();
        }
        if (getPad7() != null) {
            _hashCode += getPad7().hashCode();
        }
        if (getPad8() != null) {
            _hashCode += getPad8().hashCode();
        }
        if (getPad9() != null) {
            _hashCode += getPad9().hashCode();
        }
        if (getPd() != null) {
            _hashCode += getPd().hashCode();
        }
        if (getProductAdvice() != null) {
            _hashCode += getProductAdvice().hashCode();
        }
        if (getProductDefType() != null) {
            _hashCode += getProductDefType().hashCode();
        }
        if (getProductDesc() != null) {
            _hashCode += getProductDesc().hashCode();
        }
        if (getProductFinishTime() != null) {
            _hashCode += getProductFinishTime().hashCode();
        }
        if (getProductFoundTime() != null) {
            _hashCode += getProductFoundTime().hashCode();
        }
        if (getProductIncome() != null) {
            _hashCode += getProductIncome().hashCode();
        }
        if (getProductName() != null) {
            _hashCode += getProductName().hashCode();
        }
        if (getProductStatus() != null) {
            _hashCode += getProductStatus().hashCode();
        }
        if (getProductTerm() != null) {
            _hashCode += getProductTerm().hashCode();
        }
        if (getProductType() != null) {
            _hashCode += getProductType().hashCode();
        }
        if (getPs() != null) {
            _hashCode += getPs().hashCode();
        }
        if (getPurchasePhase() != null) {
            _hashCode += getPurchasePhase().hashCode();
        }
        if (getPurchaseStartPoint() != null) {
            _hashCode += getPurchaseStartPoint().hashCode();
        }
        if (getSaleEndTime() != null) {
            _hashCode += getSaleEndTime().hashCode();
        }
        if (getSaleStartTime() != null) {
            _hashCode += getSaleStartTime().hashCode();
        }
        if (getSource() != null) {
            _hashCode += getSource().hashCode();
        }
        if (getVentureCharacter() != null) {
            _hashCode += getVentureCharacter().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CrmProduct.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("webservice.crm.thirdparty.sw.com", "crmProduct"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commisionRate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "commisionRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "createTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("factCommisionRate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "factCommisionRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fcTrust");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fcTrust"));
        elemField.setXmlType(new javax.xml.namespace.QName("webservice.crm.thirdparty.sw.com", "fcTrust"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ladderCommisionRate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ladderCommisionRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("pad10");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad10"));
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
        elemField.setFieldName("pad6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad7");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad8");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pad9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pad9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pd");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productAdvice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productAdvice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productDefType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productDefType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productDesc");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productDesc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productFinishTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productFinishTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productFoundTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productFoundTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productIncome");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productIncome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productTerm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productTerm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "productType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ps");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ps"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("purchasePhase");
        elemField.setXmlName(new javax.xml.namespace.QName("", "purchasePhase"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("purchaseStartPoint");
        elemField.setXmlName(new javax.xml.namespace.QName("", "purchaseStartPoint"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saleEndTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "saleEndTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("saleStartTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "saleStartTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("source");
        elemField.setXmlName(new javax.xml.namespace.QName("", "source"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ventureCharacter");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ventureCharacter"));
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
