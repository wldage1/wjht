/**
 * FcTrust.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sw.thirdparty.crm.webservice;

public class FcTrust  extends com.sw.thirdparty.crm.webservice.BaseEntity  implements java.io.Serializable {
    private java.lang.Integer crmId;

    private java.lang.String dataSource;

    private java.lang.String declareDate;

    private java.lang.Integer isstat;

    private java.lang.String memo;

    private java.lang.String sspell;

    private java.lang.String tmstamp;

    private java.lang.String trust1;

    private java.lang.Float trust10;

    private java.lang.Float trust11;

    private java.math.BigDecimal trust12;

    private java.lang.String trust13;

    private java.lang.String trust14;

    private java.lang.String trust15;

    private java.lang.String trust16;

    private java.lang.String trust17;

    private java.lang.String trust18;

    private java.lang.Float trust19;

    private java.lang.String trust2;

    private java.lang.String trust20;

    private java.lang.Float trust21;

    private java.lang.Integer trust22;

    private java.lang.Integer trust23;

    private java.lang.Float trust24;

    private java.lang.String trust25;

    private java.lang.Integer trust26;

    private java.lang.Integer trust27;

    private java.lang.String trust28;

    private java.lang.String trust29;

    private java.lang.String trust3;

    private java.lang.String trust30;

    private java.lang.Integer trust31;

    private java.lang.Byte trust32;

    private java.lang.String trust33;

    private java.lang.String trust34;

    private java.lang.String trust35;

    private java.lang.String trust36;

    private java.lang.Integer trust37;

    private java.lang.Integer trust38;

    private java.math.BigDecimal trust39;

    private java.lang.Float trust4;

    private java.lang.String trust40;

    private java.lang.Integer trust41;

    private java.lang.String trust5;

    private java.lang.String trust6;

    private java.lang.String trust7;

    private java.lang.String trust8;

    private java.lang.Float trust9;

    private java.lang.String trustAName;

    private java.lang.String trustCode;

    private java.lang.String[] trustCodes;

    private java.lang.Integer trustId;

    private java.lang.String trustName;

    private java.lang.String type;

    private java.lang.String updateDate;

    public FcTrust() {
    }

    public FcTrust(
           java.lang.Long generatedKey,
           java.lang.Long id,
           java.lang.String[] ids,
           int limitRows,
           int page,
           int rows,
           java.lang.Integer crmId,
           java.lang.String dataSource,
           java.lang.String declareDate,
           java.lang.Integer isstat,
           java.lang.String memo,
           java.lang.String sspell,
           java.lang.String tmstamp,
           java.lang.String trust1,
           java.lang.Float trust10,
           java.lang.Float trust11,
           java.math.BigDecimal trust12,
           java.lang.String trust13,
           java.lang.String trust14,
           java.lang.String trust15,
           java.lang.String trust16,
           java.lang.String trust17,
           java.lang.String trust18,
           java.lang.Float trust19,
           java.lang.String trust2,
           java.lang.String trust20,
           java.lang.Float trust21,
           java.lang.Integer trust22,
           java.lang.Integer trust23,
           java.lang.Float trust24,
           java.lang.String trust25,
           java.lang.Integer trust26,
           java.lang.Integer trust27,
           java.lang.String trust28,
           java.lang.String trust29,
           java.lang.String trust3,
           java.lang.String trust30,
           java.lang.Integer trust31,
           java.lang.Byte trust32,
           java.lang.String trust33,
           java.lang.String trust34,
           java.lang.String trust35,
           java.lang.String trust36,
           java.lang.Integer trust37,
           java.lang.Integer trust38,
           java.math.BigDecimal trust39,
           java.lang.Float trust4,
           java.lang.String trust40,
           java.lang.Integer trust41,
           java.lang.String trust5,
           java.lang.String trust6,
           java.lang.String trust7,
           java.lang.String trust8,
           java.lang.Float trust9,
           java.lang.String trustAName,
           java.lang.String trustCode,
           java.lang.String[] trustCodes,
           java.lang.Integer trustId,
           java.lang.String trustName,
           java.lang.String type,
           java.lang.String updateDate) {
        super(
            generatedKey,
            id,
            ids,
            limitRows,
            page,
            rows);
        this.crmId = crmId;
        this.dataSource = dataSource;
        this.declareDate = declareDate;
        this.isstat = isstat;
        this.memo = memo;
        this.sspell = sspell;
        this.tmstamp = tmstamp;
        this.trust1 = trust1;
        this.trust10 = trust10;
        this.trust11 = trust11;
        this.trust12 = trust12;
        this.trust13 = trust13;
        this.trust14 = trust14;
        this.trust15 = trust15;
        this.trust16 = trust16;
        this.trust17 = trust17;
        this.trust18 = trust18;
        this.trust19 = trust19;
        this.trust2 = trust2;
        this.trust20 = trust20;
        this.trust21 = trust21;
        this.trust22 = trust22;
        this.trust23 = trust23;
        this.trust24 = trust24;
        this.trust25 = trust25;
        this.trust26 = trust26;
        this.trust27 = trust27;
        this.trust28 = trust28;
        this.trust29 = trust29;
        this.trust3 = trust3;
        this.trust30 = trust30;
        this.trust31 = trust31;
        this.trust32 = trust32;
        this.trust33 = trust33;
        this.trust34 = trust34;
        this.trust35 = trust35;
        this.trust36 = trust36;
        this.trust37 = trust37;
        this.trust38 = trust38;
        this.trust39 = trust39;
        this.trust4 = trust4;
        this.trust40 = trust40;
        this.trust41 = trust41;
        this.trust5 = trust5;
        this.trust6 = trust6;
        this.trust7 = trust7;
        this.trust8 = trust8;
        this.trust9 = trust9;
        this.trustAName = trustAName;
        this.trustCode = trustCode;
        this.trustCodes = trustCodes;
        this.trustId = trustId;
        this.trustName = trustName;
        this.type = type;
        this.updateDate = updateDate;
    }


    /**
     * Gets the crmId value for this FcTrust.
     * 
     * @return crmId
     */
    public java.lang.Integer getCrmId() {
        return crmId;
    }


    /**
     * Sets the crmId value for this FcTrust.
     * 
     * @param crmId
     */
    public void setCrmId(java.lang.Integer crmId) {
        this.crmId = crmId;
    }


    /**
     * Gets the dataSource value for this FcTrust.
     * 
     * @return dataSource
     */
    public java.lang.String getDataSource() {
        return dataSource;
    }


    /**
     * Sets the dataSource value for this FcTrust.
     * 
     * @param dataSource
     */
    public void setDataSource(java.lang.String dataSource) {
        this.dataSource = dataSource;
    }


    /**
     * Gets the declareDate value for this FcTrust.
     * 
     * @return declareDate
     */
    public java.lang.String getDeclareDate() {
        return declareDate;
    }


    /**
     * Sets the declareDate value for this FcTrust.
     * 
     * @param declareDate
     */
    public void setDeclareDate(java.lang.String declareDate) {
        this.declareDate = declareDate;
    }


    /**
     * Gets the isstat value for this FcTrust.
     * 
     * @return isstat
     */
    public java.lang.Integer getIsstat() {
        return isstat;
    }


    /**
     * Sets the isstat value for this FcTrust.
     * 
     * @param isstat
     */
    public void setIsstat(java.lang.Integer isstat) {
        this.isstat = isstat;
    }


    /**
     * Gets the memo value for this FcTrust.
     * 
     * @return memo
     */
    public java.lang.String getMemo() {
        return memo;
    }


    /**
     * Sets the memo value for this FcTrust.
     * 
     * @param memo
     */
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }


    /**
     * Gets the sspell value for this FcTrust.
     * 
     * @return sspell
     */
    public java.lang.String getSspell() {
        return sspell;
    }


    /**
     * Sets the sspell value for this FcTrust.
     * 
     * @param sspell
     */
    public void setSspell(java.lang.String sspell) {
        this.sspell = sspell;
    }


    /**
     * Gets the tmstamp value for this FcTrust.
     * 
     * @return tmstamp
     */
    public java.lang.String getTmstamp() {
        return tmstamp;
    }


    /**
     * Sets the tmstamp value for this FcTrust.
     * 
     * @param tmstamp
     */
    public void setTmstamp(java.lang.String tmstamp) {
        this.tmstamp = tmstamp;
    }


    /**
     * Gets the trust1 value for this FcTrust.
     * 
     * @return trust1
     */
    public java.lang.String getTrust1() {
        return trust1;
    }


    /**
     * Sets the trust1 value for this FcTrust.
     * 
     * @param trust1
     */
    public void setTrust1(java.lang.String trust1) {
        this.trust1 = trust1;
    }


    /**
     * Gets the trust10 value for this FcTrust.
     * 
     * @return trust10
     */
    public java.lang.Float getTrust10() {
        return trust10;
    }


    /**
     * Sets the trust10 value for this FcTrust.
     * 
     * @param trust10
     */
    public void setTrust10(java.lang.Float trust10) {
        this.trust10 = trust10;
    }


    /**
     * Gets the trust11 value for this FcTrust.
     * 
     * @return trust11
     */
    public java.lang.Float getTrust11() {
        return trust11;
    }


    /**
     * Sets the trust11 value for this FcTrust.
     * 
     * @param trust11
     */
    public void setTrust11(java.lang.Float trust11) {
        this.trust11 = trust11;
    }


    /**
     * Gets the trust12 value for this FcTrust.
     * 
     * @return trust12
     */
    public java.math.BigDecimal getTrust12() {
        return trust12;
    }


    /**
     * Sets the trust12 value for this FcTrust.
     * 
     * @param trust12
     */
    public void setTrust12(java.math.BigDecimal trust12) {
        this.trust12 = trust12;
    }


    /**
     * Gets the trust13 value for this FcTrust.
     * 
     * @return trust13
     */
    public java.lang.String getTrust13() {
        return trust13;
    }


    /**
     * Sets the trust13 value for this FcTrust.
     * 
     * @param trust13
     */
    public void setTrust13(java.lang.String trust13) {
        this.trust13 = trust13;
    }


    /**
     * Gets the trust14 value for this FcTrust.
     * 
     * @return trust14
     */
    public java.lang.String getTrust14() {
        return trust14;
    }


    /**
     * Sets the trust14 value for this FcTrust.
     * 
     * @param trust14
     */
    public void setTrust14(java.lang.String trust14) {
        this.trust14 = trust14;
    }


    /**
     * Gets the trust15 value for this FcTrust.
     * 
     * @return trust15
     */
    public java.lang.String getTrust15() {
        return trust15;
    }


    /**
     * Sets the trust15 value for this FcTrust.
     * 
     * @param trust15
     */
    public void setTrust15(java.lang.String trust15) {
        this.trust15 = trust15;
    }


    /**
     * Gets the trust16 value for this FcTrust.
     * 
     * @return trust16
     */
    public java.lang.String getTrust16() {
        return trust16;
    }


    /**
     * Sets the trust16 value for this FcTrust.
     * 
     * @param trust16
     */
    public void setTrust16(java.lang.String trust16) {
        this.trust16 = trust16;
    }


    /**
     * Gets the trust17 value for this FcTrust.
     * 
     * @return trust17
     */
    public java.lang.String getTrust17() {
        return trust17;
    }


    /**
     * Sets the trust17 value for this FcTrust.
     * 
     * @param trust17
     */
    public void setTrust17(java.lang.String trust17) {
        this.trust17 = trust17;
    }


    /**
     * Gets the trust18 value for this FcTrust.
     * 
     * @return trust18
     */
    public java.lang.String getTrust18() {
        return trust18;
    }


    /**
     * Sets the trust18 value for this FcTrust.
     * 
     * @param trust18
     */
    public void setTrust18(java.lang.String trust18) {
        this.trust18 = trust18;
    }


    /**
     * Gets the trust19 value for this FcTrust.
     * 
     * @return trust19
     */
    public java.lang.Float getTrust19() {
        return trust19;
    }


    /**
     * Sets the trust19 value for this FcTrust.
     * 
     * @param trust19
     */
    public void setTrust19(java.lang.Float trust19) {
        this.trust19 = trust19;
    }


    /**
     * Gets the trust2 value for this FcTrust.
     * 
     * @return trust2
     */
    public java.lang.String getTrust2() {
        return trust2;
    }


    /**
     * Sets the trust2 value for this FcTrust.
     * 
     * @param trust2
     */
    public void setTrust2(java.lang.String trust2) {
        this.trust2 = trust2;
    }


    /**
     * Gets the trust20 value for this FcTrust.
     * 
     * @return trust20
     */
    public java.lang.String getTrust20() {
        return trust20;
    }


    /**
     * Sets the trust20 value for this FcTrust.
     * 
     * @param trust20
     */
    public void setTrust20(java.lang.String trust20) {
        this.trust20 = trust20;
    }


    /**
     * Gets the trust21 value for this FcTrust.
     * 
     * @return trust21
     */
    public java.lang.Float getTrust21() {
        return trust21;
    }


    /**
     * Sets the trust21 value for this FcTrust.
     * 
     * @param trust21
     */
    public void setTrust21(java.lang.Float trust21) {
        this.trust21 = trust21;
    }


    /**
     * Gets the trust22 value for this FcTrust.
     * 
     * @return trust22
     */
    public java.lang.Integer getTrust22() {
        return trust22;
    }


    /**
     * Sets the trust22 value for this FcTrust.
     * 
     * @param trust22
     */
    public void setTrust22(java.lang.Integer trust22) {
        this.trust22 = trust22;
    }


    /**
     * Gets the trust23 value for this FcTrust.
     * 
     * @return trust23
     */
    public java.lang.Integer getTrust23() {
        return trust23;
    }


    /**
     * Sets the trust23 value for this FcTrust.
     * 
     * @param trust23
     */
    public void setTrust23(java.lang.Integer trust23) {
        this.trust23 = trust23;
    }


    /**
     * Gets the trust24 value for this FcTrust.
     * 
     * @return trust24
     */
    public java.lang.Float getTrust24() {
        return trust24;
    }


    /**
     * Sets the trust24 value for this FcTrust.
     * 
     * @param trust24
     */
    public void setTrust24(java.lang.Float trust24) {
        this.trust24 = trust24;
    }


    /**
     * Gets the trust25 value for this FcTrust.
     * 
     * @return trust25
     */
    public java.lang.String getTrust25() {
        return trust25;
    }


    /**
     * Sets the trust25 value for this FcTrust.
     * 
     * @param trust25
     */
    public void setTrust25(java.lang.String trust25) {
        this.trust25 = trust25;
    }


    /**
     * Gets the trust26 value for this FcTrust.
     * 
     * @return trust26
     */
    public java.lang.Integer getTrust26() {
        return trust26;
    }


    /**
     * Sets the trust26 value for this FcTrust.
     * 
     * @param trust26
     */
    public void setTrust26(java.lang.Integer trust26) {
        this.trust26 = trust26;
    }


    /**
     * Gets the trust27 value for this FcTrust.
     * 
     * @return trust27
     */
    public java.lang.Integer getTrust27() {
        return trust27;
    }


    /**
     * Sets the trust27 value for this FcTrust.
     * 
     * @param trust27
     */
    public void setTrust27(java.lang.Integer trust27) {
        this.trust27 = trust27;
    }


    /**
     * Gets the trust28 value for this FcTrust.
     * 
     * @return trust28
     */
    public java.lang.String getTrust28() {
        return trust28;
    }


    /**
     * Sets the trust28 value for this FcTrust.
     * 
     * @param trust28
     */
    public void setTrust28(java.lang.String trust28) {
        this.trust28 = trust28;
    }


    /**
     * Gets the trust29 value for this FcTrust.
     * 
     * @return trust29
     */
    public java.lang.String getTrust29() {
        return trust29;
    }


    /**
     * Sets the trust29 value for this FcTrust.
     * 
     * @param trust29
     */
    public void setTrust29(java.lang.String trust29) {
        this.trust29 = trust29;
    }


    /**
     * Gets the trust3 value for this FcTrust.
     * 
     * @return trust3
     */
    public java.lang.String getTrust3() {
        return trust3;
    }


    /**
     * Sets the trust3 value for this FcTrust.
     * 
     * @param trust3
     */
    public void setTrust3(java.lang.String trust3) {
        this.trust3 = trust3;
    }


    /**
     * Gets the trust30 value for this FcTrust.
     * 
     * @return trust30
     */
    public java.lang.String getTrust30() {
        return trust30;
    }


    /**
     * Sets the trust30 value for this FcTrust.
     * 
     * @param trust30
     */
    public void setTrust30(java.lang.String trust30) {
        this.trust30 = trust30;
    }


    /**
     * Gets the trust31 value for this FcTrust.
     * 
     * @return trust31
     */
    public java.lang.Integer getTrust31() {
        return trust31;
    }


    /**
     * Sets the trust31 value for this FcTrust.
     * 
     * @param trust31
     */
    public void setTrust31(java.lang.Integer trust31) {
        this.trust31 = trust31;
    }


    /**
     * Gets the trust32 value for this FcTrust.
     * 
     * @return trust32
     */
    public java.lang.Byte getTrust32() {
        return trust32;
    }


    /**
     * Sets the trust32 value for this FcTrust.
     * 
     * @param trust32
     */
    public void setTrust32(java.lang.Byte trust32) {
        this.trust32 = trust32;
    }


    /**
     * Gets the trust33 value for this FcTrust.
     * 
     * @return trust33
     */
    public java.lang.String getTrust33() {
        return trust33;
    }


    /**
     * Sets the trust33 value for this FcTrust.
     * 
     * @param trust33
     */
    public void setTrust33(java.lang.String trust33) {
        this.trust33 = trust33;
    }


    /**
     * Gets the trust34 value for this FcTrust.
     * 
     * @return trust34
     */
    public java.lang.String getTrust34() {
        return trust34;
    }


    /**
     * Sets the trust34 value for this FcTrust.
     * 
     * @param trust34
     */
    public void setTrust34(java.lang.String trust34) {
        this.trust34 = trust34;
    }


    /**
     * Gets the trust35 value for this FcTrust.
     * 
     * @return trust35
     */
    public java.lang.String getTrust35() {
        return trust35;
    }


    /**
     * Sets the trust35 value for this FcTrust.
     * 
     * @param trust35
     */
    public void setTrust35(java.lang.String trust35) {
        this.trust35 = trust35;
    }


    /**
     * Gets the trust36 value for this FcTrust.
     * 
     * @return trust36
     */
    public java.lang.String getTrust36() {
        return trust36;
    }


    /**
     * Sets the trust36 value for this FcTrust.
     * 
     * @param trust36
     */
    public void setTrust36(java.lang.String trust36) {
        this.trust36 = trust36;
    }


    /**
     * Gets the trust37 value for this FcTrust.
     * 
     * @return trust37
     */
    public java.lang.Integer getTrust37() {
        return trust37;
    }


    /**
     * Sets the trust37 value for this FcTrust.
     * 
     * @param trust37
     */
    public void setTrust37(java.lang.Integer trust37) {
        this.trust37 = trust37;
    }


    /**
     * Gets the trust38 value for this FcTrust.
     * 
     * @return trust38
     */
    public java.lang.Integer getTrust38() {
        return trust38;
    }


    /**
     * Sets the trust38 value for this FcTrust.
     * 
     * @param trust38
     */
    public void setTrust38(java.lang.Integer trust38) {
        this.trust38 = trust38;
    }


    /**
     * Gets the trust39 value for this FcTrust.
     * 
     * @return trust39
     */
    public java.math.BigDecimal getTrust39() {
        return trust39;
    }


    /**
     * Sets the trust39 value for this FcTrust.
     * 
     * @param trust39
     */
    public void setTrust39(java.math.BigDecimal trust39) {
        this.trust39 = trust39;
    }


    /**
     * Gets the trust4 value for this FcTrust.
     * 
     * @return trust4
     */
    public java.lang.Float getTrust4() {
        return trust4;
    }


    /**
     * Sets the trust4 value for this FcTrust.
     * 
     * @param trust4
     */
    public void setTrust4(java.lang.Float trust4) {
        this.trust4 = trust4;
    }


    /**
     * Gets the trust40 value for this FcTrust.
     * 
     * @return trust40
     */
    public java.lang.String getTrust40() {
        return trust40;
    }


    /**
     * Sets the trust40 value for this FcTrust.
     * 
     * @param trust40
     */
    public void setTrust40(java.lang.String trust40) {
        this.trust40 = trust40;
    }


    /**
     * Gets the trust41 value for this FcTrust.
     * 
     * @return trust41
     */
    public java.lang.Integer getTrust41() {
        return trust41;
    }


    /**
     * Sets the trust41 value for this FcTrust.
     * 
     * @param trust41
     */
    public void setTrust41(java.lang.Integer trust41) {
        this.trust41 = trust41;
    }


    /**
     * Gets the trust5 value for this FcTrust.
     * 
     * @return trust5
     */
    public java.lang.String getTrust5() {
        return trust5;
    }


    /**
     * Sets the trust5 value for this FcTrust.
     * 
     * @param trust5
     */
    public void setTrust5(java.lang.String trust5) {
        this.trust5 = trust5;
    }


    /**
     * Gets the trust6 value for this FcTrust.
     * 
     * @return trust6
     */
    public java.lang.String getTrust6() {
        return trust6;
    }


    /**
     * Sets the trust6 value for this FcTrust.
     * 
     * @param trust6
     */
    public void setTrust6(java.lang.String trust6) {
        this.trust6 = trust6;
    }


    /**
     * Gets the trust7 value for this FcTrust.
     * 
     * @return trust7
     */
    public java.lang.String getTrust7() {
        return trust7;
    }


    /**
     * Sets the trust7 value for this FcTrust.
     * 
     * @param trust7
     */
    public void setTrust7(java.lang.String trust7) {
        this.trust7 = trust7;
    }


    /**
     * Gets the trust8 value for this FcTrust.
     * 
     * @return trust8
     */
    public java.lang.String getTrust8() {
        return trust8;
    }


    /**
     * Sets the trust8 value for this FcTrust.
     * 
     * @param trust8
     */
    public void setTrust8(java.lang.String trust8) {
        this.trust8 = trust8;
    }


    /**
     * Gets the trust9 value for this FcTrust.
     * 
     * @return trust9
     */
    public java.lang.Float getTrust9() {
        return trust9;
    }


    /**
     * Sets the trust9 value for this FcTrust.
     * 
     * @param trust9
     */
    public void setTrust9(java.lang.Float trust9) {
        this.trust9 = trust9;
    }


    /**
     * Gets the trustAName value for this FcTrust.
     * 
     * @return trustAName
     */
    public java.lang.String getTrustAName() {
        return trustAName;
    }


    /**
     * Sets the trustAName value for this FcTrust.
     * 
     * @param trustAName
     */
    public void setTrustAName(java.lang.String trustAName) {
        this.trustAName = trustAName;
    }


    /**
     * Gets the trustCode value for this FcTrust.
     * 
     * @return trustCode
     */
    public java.lang.String getTrustCode() {
        return trustCode;
    }


    /**
     * Sets the trustCode value for this FcTrust.
     * 
     * @param trustCode
     */
    public void setTrustCode(java.lang.String trustCode) {
        this.trustCode = trustCode;
    }


    /**
     * Gets the trustCodes value for this FcTrust.
     * 
     * @return trustCodes
     */
    public java.lang.String[] getTrustCodes() {
        return trustCodes;
    }


    /**
     * Sets the trustCodes value for this FcTrust.
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


    /**
     * Gets the trustId value for this FcTrust.
     * 
     * @return trustId
     */
    public java.lang.Integer getTrustId() {
        return trustId;
    }


    /**
     * Sets the trustId value for this FcTrust.
     * 
     * @param trustId
     */
    public void setTrustId(java.lang.Integer trustId) {
        this.trustId = trustId;
    }


    /**
     * Gets the trustName value for this FcTrust.
     * 
     * @return trustName
     */
    public java.lang.String getTrustName() {
        return trustName;
    }


    /**
     * Sets the trustName value for this FcTrust.
     * 
     * @param trustName
     */
    public void setTrustName(java.lang.String trustName) {
        this.trustName = trustName;
    }


    /**
     * Gets the type value for this FcTrust.
     * 
     * @return type
     */
    public java.lang.String getType() {
        return type;
    }


    /**
     * Sets the type value for this FcTrust.
     * 
     * @param type
     */
    public void setType(java.lang.String type) {
        this.type = type;
    }


    /**
     * Gets the updateDate value for this FcTrust.
     * 
     * @return updateDate
     */
    public java.lang.String getUpdateDate() {
        return updateDate;
    }


    /**
     * Sets the updateDate value for this FcTrust.
     * 
     * @param updateDate
     */
    public void setUpdateDate(java.lang.String updateDate) {
        this.updateDate = updateDate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FcTrust)) return false;
        FcTrust other = (FcTrust) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.crmId==null && other.getCrmId()==null) || 
             (this.crmId!=null &&
              this.crmId.equals(other.getCrmId()))) &&
            ((this.dataSource==null && other.getDataSource()==null) || 
             (this.dataSource!=null &&
              this.dataSource.equals(other.getDataSource()))) &&
            ((this.declareDate==null && other.getDeclareDate()==null) || 
             (this.declareDate!=null &&
              this.declareDate.equals(other.getDeclareDate()))) &&
            ((this.isstat==null && other.getIsstat()==null) || 
             (this.isstat!=null &&
              this.isstat.equals(other.getIsstat()))) &&
            ((this.memo==null && other.getMemo()==null) || 
             (this.memo!=null &&
              this.memo.equals(other.getMemo()))) &&
            ((this.sspell==null && other.getSspell()==null) || 
             (this.sspell!=null &&
              this.sspell.equals(other.getSspell()))) &&
            ((this.tmstamp==null && other.getTmstamp()==null) || 
             (this.tmstamp!=null &&
              this.tmstamp.equals(other.getTmstamp()))) &&
            ((this.trust1==null && other.getTrust1()==null) || 
             (this.trust1!=null &&
              this.trust1.equals(other.getTrust1()))) &&
            ((this.trust10==null && other.getTrust10()==null) || 
             (this.trust10!=null &&
              this.trust10.equals(other.getTrust10()))) &&
            ((this.trust11==null && other.getTrust11()==null) || 
             (this.trust11!=null &&
              this.trust11.equals(other.getTrust11()))) &&
            ((this.trust12==null && other.getTrust12()==null) || 
             (this.trust12!=null &&
              this.trust12.equals(other.getTrust12()))) &&
            ((this.trust13==null && other.getTrust13()==null) || 
             (this.trust13!=null &&
              this.trust13.equals(other.getTrust13()))) &&
            ((this.trust14==null && other.getTrust14()==null) || 
             (this.trust14!=null &&
              this.trust14.equals(other.getTrust14()))) &&
            ((this.trust15==null && other.getTrust15()==null) || 
             (this.trust15!=null &&
              this.trust15.equals(other.getTrust15()))) &&
            ((this.trust16==null && other.getTrust16()==null) || 
             (this.trust16!=null &&
              this.trust16.equals(other.getTrust16()))) &&
            ((this.trust17==null && other.getTrust17()==null) || 
             (this.trust17!=null &&
              this.trust17.equals(other.getTrust17()))) &&
            ((this.trust18==null && other.getTrust18()==null) || 
             (this.trust18!=null &&
              this.trust18.equals(other.getTrust18()))) &&
            ((this.trust19==null && other.getTrust19()==null) || 
             (this.trust19!=null &&
              this.trust19.equals(other.getTrust19()))) &&
            ((this.trust2==null && other.getTrust2()==null) || 
             (this.trust2!=null &&
              this.trust2.equals(other.getTrust2()))) &&
            ((this.trust20==null && other.getTrust20()==null) || 
             (this.trust20!=null &&
              this.trust20.equals(other.getTrust20()))) &&
            ((this.trust21==null && other.getTrust21()==null) || 
             (this.trust21!=null &&
              this.trust21.equals(other.getTrust21()))) &&
            ((this.trust22==null && other.getTrust22()==null) || 
             (this.trust22!=null &&
              this.trust22.equals(other.getTrust22()))) &&
            ((this.trust23==null && other.getTrust23()==null) || 
             (this.trust23!=null &&
              this.trust23.equals(other.getTrust23()))) &&
            ((this.trust24==null && other.getTrust24()==null) || 
             (this.trust24!=null &&
              this.trust24.equals(other.getTrust24()))) &&
            ((this.trust25==null && other.getTrust25()==null) || 
             (this.trust25!=null &&
              this.trust25.equals(other.getTrust25()))) &&
            ((this.trust26==null && other.getTrust26()==null) || 
             (this.trust26!=null &&
              this.trust26.equals(other.getTrust26()))) &&
            ((this.trust27==null && other.getTrust27()==null) || 
             (this.trust27!=null &&
              this.trust27.equals(other.getTrust27()))) &&
            ((this.trust28==null && other.getTrust28()==null) || 
             (this.trust28!=null &&
              this.trust28.equals(other.getTrust28()))) &&
            ((this.trust29==null && other.getTrust29()==null) || 
             (this.trust29!=null &&
              this.trust29.equals(other.getTrust29()))) &&
            ((this.trust3==null && other.getTrust3()==null) || 
             (this.trust3!=null &&
              this.trust3.equals(other.getTrust3()))) &&
            ((this.trust30==null && other.getTrust30()==null) || 
             (this.trust30!=null &&
              this.trust30.equals(other.getTrust30()))) &&
            ((this.trust31==null && other.getTrust31()==null) || 
             (this.trust31!=null &&
              this.trust31.equals(other.getTrust31()))) &&
            ((this.trust32==null && other.getTrust32()==null) || 
             (this.trust32!=null &&
              this.trust32.equals(other.getTrust32()))) &&
            ((this.trust33==null && other.getTrust33()==null) || 
             (this.trust33!=null &&
              this.trust33.equals(other.getTrust33()))) &&
            ((this.trust34==null && other.getTrust34()==null) || 
             (this.trust34!=null &&
              this.trust34.equals(other.getTrust34()))) &&
            ((this.trust35==null && other.getTrust35()==null) || 
             (this.trust35!=null &&
              this.trust35.equals(other.getTrust35()))) &&
            ((this.trust36==null && other.getTrust36()==null) || 
             (this.trust36!=null &&
              this.trust36.equals(other.getTrust36()))) &&
            ((this.trust37==null && other.getTrust37()==null) || 
             (this.trust37!=null &&
              this.trust37.equals(other.getTrust37()))) &&
            ((this.trust38==null && other.getTrust38()==null) || 
             (this.trust38!=null &&
              this.trust38.equals(other.getTrust38()))) &&
            ((this.trust39==null && other.getTrust39()==null) || 
             (this.trust39!=null &&
              this.trust39.equals(other.getTrust39()))) &&
            ((this.trust4==null && other.getTrust4()==null) || 
             (this.trust4!=null &&
              this.trust4.equals(other.getTrust4()))) &&
            ((this.trust40==null && other.getTrust40()==null) || 
             (this.trust40!=null &&
              this.trust40.equals(other.getTrust40()))) &&
            ((this.trust41==null && other.getTrust41()==null) || 
             (this.trust41!=null &&
              this.trust41.equals(other.getTrust41()))) &&
            ((this.trust5==null && other.getTrust5()==null) || 
             (this.trust5!=null &&
              this.trust5.equals(other.getTrust5()))) &&
            ((this.trust6==null && other.getTrust6()==null) || 
             (this.trust6!=null &&
              this.trust6.equals(other.getTrust6()))) &&
            ((this.trust7==null && other.getTrust7()==null) || 
             (this.trust7!=null &&
              this.trust7.equals(other.getTrust7()))) &&
            ((this.trust8==null && other.getTrust8()==null) || 
             (this.trust8!=null &&
              this.trust8.equals(other.getTrust8()))) &&
            ((this.trust9==null && other.getTrust9()==null) || 
             (this.trust9!=null &&
              this.trust9.equals(other.getTrust9()))) &&
            ((this.trustAName==null && other.getTrustAName()==null) || 
             (this.trustAName!=null &&
              this.trustAName.equals(other.getTrustAName()))) &&
            ((this.trustCode==null && other.getTrustCode()==null) || 
             (this.trustCode!=null &&
              this.trustCode.equals(other.getTrustCode()))) &&
            ((this.trustCodes==null && other.getTrustCodes()==null) || 
             (this.trustCodes!=null &&
              java.util.Arrays.equals(this.trustCodes, other.getTrustCodes()))) &&
            ((this.trustId==null && other.getTrustId()==null) || 
             (this.trustId!=null &&
              this.trustId.equals(other.getTrustId()))) &&
            ((this.trustName==null && other.getTrustName()==null) || 
             (this.trustName!=null &&
              this.trustName.equals(other.getTrustName()))) &&
            ((this.type==null && other.getType()==null) || 
             (this.type!=null &&
              this.type.equals(other.getType()))) &&
            ((this.updateDate==null && other.getUpdateDate()==null) || 
             (this.updateDate!=null &&
              this.updateDate.equals(other.getUpdateDate())));
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
        if (getCrmId() != null) {
            _hashCode += getCrmId().hashCode();
        }
        if (getDataSource() != null) {
            _hashCode += getDataSource().hashCode();
        }
        if (getDeclareDate() != null) {
            _hashCode += getDeclareDate().hashCode();
        }
        if (getIsstat() != null) {
            _hashCode += getIsstat().hashCode();
        }
        if (getMemo() != null) {
            _hashCode += getMemo().hashCode();
        }
        if (getSspell() != null) {
            _hashCode += getSspell().hashCode();
        }
        if (getTmstamp() != null) {
            _hashCode += getTmstamp().hashCode();
        }
        if (getTrust1() != null) {
            _hashCode += getTrust1().hashCode();
        }
        if (getTrust10() != null) {
            _hashCode += getTrust10().hashCode();
        }
        if (getTrust11() != null) {
            _hashCode += getTrust11().hashCode();
        }
        if (getTrust12() != null) {
            _hashCode += getTrust12().hashCode();
        }
        if (getTrust13() != null) {
            _hashCode += getTrust13().hashCode();
        }
        if (getTrust14() != null) {
            _hashCode += getTrust14().hashCode();
        }
        if (getTrust15() != null) {
            _hashCode += getTrust15().hashCode();
        }
        if (getTrust16() != null) {
            _hashCode += getTrust16().hashCode();
        }
        if (getTrust17() != null) {
            _hashCode += getTrust17().hashCode();
        }
        if (getTrust18() != null) {
            _hashCode += getTrust18().hashCode();
        }
        if (getTrust19() != null) {
            _hashCode += getTrust19().hashCode();
        }
        if (getTrust2() != null) {
            _hashCode += getTrust2().hashCode();
        }
        if (getTrust20() != null) {
            _hashCode += getTrust20().hashCode();
        }
        if (getTrust21() != null) {
            _hashCode += getTrust21().hashCode();
        }
        if (getTrust22() != null) {
            _hashCode += getTrust22().hashCode();
        }
        if (getTrust23() != null) {
            _hashCode += getTrust23().hashCode();
        }
        if (getTrust24() != null) {
            _hashCode += getTrust24().hashCode();
        }
        if (getTrust25() != null) {
            _hashCode += getTrust25().hashCode();
        }
        if (getTrust26() != null) {
            _hashCode += getTrust26().hashCode();
        }
        if (getTrust27() != null) {
            _hashCode += getTrust27().hashCode();
        }
        if (getTrust28() != null) {
            _hashCode += getTrust28().hashCode();
        }
        if (getTrust29() != null) {
            _hashCode += getTrust29().hashCode();
        }
        if (getTrust3() != null) {
            _hashCode += getTrust3().hashCode();
        }
        if (getTrust30() != null) {
            _hashCode += getTrust30().hashCode();
        }
        if (getTrust31() != null) {
            _hashCode += getTrust31().hashCode();
        }
        if (getTrust32() != null) {
            _hashCode += getTrust32().hashCode();
        }
        if (getTrust33() != null) {
            _hashCode += getTrust33().hashCode();
        }
        if (getTrust34() != null) {
            _hashCode += getTrust34().hashCode();
        }
        if (getTrust35() != null) {
            _hashCode += getTrust35().hashCode();
        }
        if (getTrust36() != null) {
            _hashCode += getTrust36().hashCode();
        }
        if (getTrust37() != null) {
            _hashCode += getTrust37().hashCode();
        }
        if (getTrust38() != null) {
            _hashCode += getTrust38().hashCode();
        }
        if (getTrust39() != null) {
            _hashCode += getTrust39().hashCode();
        }
        if (getTrust4() != null) {
            _hashCode += getTrust4().hashCode();
        }
        if (getTrust40() != null) {
            _hashCode += getTrust40().hashCode();
        }
        if (getTrust41() != null) {
            _hashCode += getTrust41().hashCode();
        }
        if (getTrust5() != null) {
            _hashCode += getTrust5().hashCode();
        }
        if (getTrust6() != null) {
            _hashCode += getTrust6().hashCode();
        }
        if (getTrust7() != null) {
            _hashCode += getTrust7().hashCode();
        }
        if (getTrust8() != null) {
            _hashCode += getTrust8().hashCode();
        }
        if (getTrust9() != null) {
            _hashCode += getTrust9().hashCode();
        }
        if (getTrustAName() != null) {
            _hashCode += getTrustAName().hashCode();
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
        if (getTrustId() != null) {
            _hashCode += getTrustId().hashCode();
        }
        if (getTrustName() != null) {
            _hashCode += getTrustName().hashCode();
        }
        if (getType() != null) {
            _hashCode += getType().hashCode();
        }
        if (getUpdateDate() != null) {
            _hashCode += getUpdateDate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FcTrust.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("webservice.crm.thirdparty.sw.com", "fcTrust"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("crmId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "crmId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataSource");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("declareDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "declareDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isstat");
        elemField.setXmlName(new javax.xml.namespace.QName("", "isstat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "memo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sspell");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sspell"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tmstamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tmstamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust10");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust10"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust11");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust11"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust12");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust12"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust13");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust13"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust14");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust14"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust15");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust15"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust16");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust16"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust17");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust17"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust18");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust18"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust19");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust19"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust20");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust20"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust21");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust21"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust22");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust22"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust23");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust23"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust24");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust24"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust25");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust25"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust26");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust26"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust27");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust27"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust28");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust28"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust29");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust29"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust30");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust30"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust31");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust31"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust32");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust32"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "byte"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust33");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust33"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust34");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust34"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust35");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust35"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust36");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust36"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust37");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust37"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust38");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust38"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust39");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust39"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust40");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust40"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust41");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust41"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust5");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust6");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust7");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust8");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trust9");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trust9"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trustAName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trustAName"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trustId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trustId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trustName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "trustName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type");
        elemField.setXmlName(new javax.xml.namespace.QName("", "type"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "updateDate"));
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
