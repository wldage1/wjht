/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.productcenter.product.maintain.entity
 * FileName: TrustProduct.java
 * @version 1.0
 * @author baokai
 * @created on 2012-6-4
 * @last Modified 
 * @history
 */
package com.sw.plugins.productcenter.product.maintain.entity;

import java.math.BigDecimal;

import com.sw.core.data.entity.BaseEntity;

/**
 *  类简要说明
 *  @author baokai
 *  @version 1.0
 *  </pre>
 *  Created on :下午3:24:47
 *  LastModified:
 *  History:
 *  </pre>
 */
public class TrustProduct extends BaseEntity{
	
	private Integer trustId; //流水号
	private String trustName; //信托计划名称
	private String trustCode;//信托计划代码
	private String trustAName;//信托计划简称
	private String sspell; //简称拼音
	private String declareDate;//公告日期
	private String updateDate;//更新日期
	private Integer trust22;//信托类型
	private Integer trust31;//是否阳光私募
	private String trust28;//结构化信托资金比例
	private String type;//信托类别
	private String trust30;//证券投资类别
	private Integer isstat;//是否参与合并统计
	private String trust16;//受托人代码
	private String trust17;//受托人
	private Byte trust32;//是否TOT
	private String trust1;//发行年度
	private Integer trust23;//运用领域
	private String trust2;//推介起始日
	private String trust3;//推介截止日
	private Integer trust27;//期限类型
	private Float trust4;//存续期
	private String trust29;//开放日
	private String trust34;//封闭期
	private String trust5;//成立日期
	private String trust6;//终止日期
	private Integer trust26;//是否可提前终止
	private Integer trust38;//是否可延期
	private String trust7;//信托计划经理
	private Integer trust41;//清盘原因
	private Float trust9;//预计年收益率
	private String trust8;//币种
	private String trust18;//预计年收益率说明
	private Float trust24;//实际年收益率
	private Integer trust37;//收益类型
	private String trust25;//实际年收益率说明
	private Float trust10;//预计发行规模
	private Float trust11;//实际发行规模
	private BigDecimal trust12;//合同份数
	private Float trust21;//面值
	private Float trust19;//最低认购资金
	private String trust13;//发行地
	private String trust20;//认购资金递增说明
	private BigDecimal trust39;//最低追加金额
	private String trust40;//追加金额递增说明
	private String trust14;//信用增级方式
	private String trust35;//投资范围
	private String trust15;//产品说明
	private String trust33;//费率说明
	private String trust36;//风险提示
	private String memo;//备注
	private String dataSource;//数据来源
	private String tmstamp;//TMStamp
	private String crmId ;//CRM编号
	public Integer getTrustId() {
		return trustId;
	}
	public void setTrustId(Integer trustId) {
		this.trustId = trustId;
	}
	public String getTrustName() {
		return trustName;
	}
	public void setTrustName(String trustName) {
		this.trustName = trustName;
	}
	public String getTrustCode() {
		return trustCode;
	}
	public void setTrustCode(String trustCode) {
		this.trustCode = trustCode;
	}
	public String getTrustAName() {
		return trustAName;
	}
	public void setTrustAName(String trustAName) {
		this.trustAName = trustAName;
	}
	public String getSspell() {
		return sspell;
	}
	public void setSspell(String sspell) {
		this.sspell = sspell;
	}
	public String getDeclareDate() {
		return declareDate;
	}
	public void setDeclareDate(String declareDate) {
		this.declareDate = declareDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getTrust22() {
		return trust22;
	}
	public void setTrust22(Integer trust22) {
		this.trust22 = trust22;
	}
	public Integer getTrust31() {
		return trust31;
	}
	public void setTrust31(Integer trust31) {
		this.trust31 = trust31;
	}
	public String getTrust28() {
		return trust28;
	}
	public void setTrust28(String trust28) {
		this.trust28 = trust28;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTrust30() {
		return trust30;
	}
	public void setTrust30(String trust30) {
		this.trust30 = trust30;
	}
	public Integer getIsstat() {
		return isstat;
	}
	public void setIsstat(Integer isstat) {
		this.isstat = isstat;
	}
	public String getTrust16() {
		return trust16;
	}
	public void setTrust16(String trust16) {
		this.trust16 = trust16;
	}
	public String getTrust17() {
		return trust17;
	}
	public void setTrust17(String trust17) {
		this.trust17 = trust17;
	}
	public Byte getTrust32() {
		return trust32;
	}
	public void setTrust32(Byte trust32) {
		this.trust32 = trust32;
	}
	public String getTrust1() {
		return trust1;
	}
	public void setTrust1(String trust1) {
		this.trust1 = trust1;
	}
	public Integer getTrust23() {
		return trust23;
	}
	public void setTrust23(Integer trust23) {
		this.trust23 = trust23;
	}
	public String getTrust2() {
		return trust2;
	}
	public void setTrust2(String trust2) {
		this.trust2 = trust2;
	}
	public String getTrust3() {
		return trust3;
	}
	public void setTrust3(String trust3) {
		this.trust3 = trust3;
	}
	public Integer getTrust27() {
		return trust27;
	}
	public void setTrust27(Integer trust27) {
		this.trust27 = trust27;
	}
	public Float getTrust4() {
		return trust4;
	}
	public void setTrust4(Float trust4) {
		this.trust4 = trust4;
	}
	public String getTrust29() {
		return trust29;
	}
	public void setTrust29(String trust29) {
		this.trust29 = trust29;
	}
	public String getTrust34() {
		return trust34;
	}
	public void setTrust34(String trust34) {
		this.trust34 = trust34;
	}
	public String getTrust5() {
		return trust5;
	}
	public void setTrust5(String trust5) {
		this.trust5 = trust5;
	}
	public String getTrust6() {
		return trust6;
	}
	public void setTrust6(String trust6) {
		this.trust6 = trust6;
	}
	public Integer getTrust26() {
		return trust26;
	}
	public void setTrust26(Integer trust26) {
		this.trust26 = trust26;
	}
	public Integer getTrust38() {
		return trust38;
	}
	public void setTrust38(Integer trust38) {
		this.trust38 = trust38;
	}
	public String getTrust7() {
		return trust7;
	}
	public void setTrust7(String trust7) {
		this.trust7 = trust7;
	}
	public Integer getTrust41() {
		return trust41;
	}
	public void setTrust41(Integer trust41) {
		this.trust41 = trust41;
	}
	public Float getTrust9() {
		return trust9;
	}
	public void setTrust9(Float trust9) {
		this.trust9 = trust9;
	}
	public String getTrust8() {
		return trust8;
	}
	public void setTrust8(String trust8) {
		this.trust8 = trust8;
	}
	public String getTrust18() {
		return trust18;
	}
	public void setTrust18(String trust18) {
		this.trust18 = trust18;
	}
	public Float getTrust24() {
		return trust24;
	}
	public void setTrust24(Float trust24) {
		this.trust24 = trust24;
	}
	public Integer getTrust37() {
		return trust37;
	}
	public void setTrust37(Integer trust37) {
		this.trust37 = trust37;
	}
	public String getTrust25() {
		return trust25;
	}
	public void setTrust25(String trust25) {
		this.trust25 = trust25;
	}
	public Float getTrust10() {
		return trust10;
	}
	public void setTrust10(Float trust10) {
		this.trust10 = trust10;
	}
	public Float getTrust11() {
		return trust11;
	}
	public void setTrust11(Float trust11) {
		this.trust11 = trust11;
	}
	public BigDecimal getTrust12() {
		return trust12;
	}
	public void setTrust12(BigDecimal trust12) {
		this.trust12 = trust12;
	}
	public Float getTrust21() {
		return trust21;
	}
	public void setTrust21(Float trust21) {
		this.trust21 = trust21;
	}
	public Float getTrust19() {
		return trust19;
	}
	public void setTrust19(Float trust19) {
		this.trust19 = trust19;
	}
	public String getTrust13() {
		return trust13;
	}
	public void setTrust13(String trust13) {
		this.trust13 = trust13;
	}
	public String getTrust20() {
		return trust20;
	}
	public void setTrust20(String trust20) {
		this.trust20 = trust20;
	}
	public BigDecimal getTrust39() {
		return trust39;
	}
	public void setTrust39(BigDecimal trust39) {
		this.trust39 = trust39;
	}
	public String getTrust40() {
		return trust40;
	}
	public void setTrust40(String trust40) {
		this.trust40 = trust40;
	}
	public String getTrust14() {
		return trust14;
	}
	public void setTrust14(String trust14) {
		this.trust14 = trust14;
	}
	public String getTrust35() {
		return trust35;
	}
	public void setTrust35(String trust35) {
		this.trust35 = trust35;
	}
	public String getTrust15() {
		return trust15;
	}
	public void setTrust15(String trust15) {
		this.trust15 = trust15;
	}
	public String getTrust33() {
		return trust33;
	}
	public void setTrust33(String trust33) {
		this.trust33 = trust33;
	}
	public String getTrust36() {
		return trust36;
	}
	public void setTrust36(String trust36) {
		this.trust36 = trust36;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public String getTmstamp() {
		return tmstamp;
	}
	public void setTmstamp(String tmstamp) {
		this.tmstamp = tmstamp;
	}
	public String getCrmId() {
		return crmId;
	}
	public void setCrmId(String crmId) {
		this.crmId = crmId;
	}
	
	
	
}
