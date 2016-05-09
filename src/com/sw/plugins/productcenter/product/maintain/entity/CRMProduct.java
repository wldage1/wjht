/**
 * rwoms Copyright 2010 SINOWEL, Co.ltd .
 * All rights reserved.
 * Package:  com.sw.plugins.productcenter.product.maintain.entity
 * FileName: Product.java
 * @version 1.0
 * @author haoyuanfu
 * @created on 2012-5-14
 * @last Modified 
 * @history
 */
package com.sw.plugins.productcenter.product.maintain.entity;

import java.math.BigDecimal;
import java.util.Calendar;

import com.sw.core.data.entity.BaseEntity;


/**
 *  CRM产品信息实体类
 *  @author haoyuanfu
 *  @version 1.0
 *  </pre>
 *  Created on :上午09:37:25
 *  LastModified:
 *  History:
 *  </pre>
 */

public class CRMProduct extends BaseEntity {
	
	/**1 、CRM产品ID（CRM产品表主键 SerialNo）*/
	private  Integer serialNo;
	/**2、产品名称*/
	private String  productName;
	/**3、产品提供方*/
	private String source;
	/**4、产品类型 （选择列表形式,包括：公募基金、私募基金PS、信托产品PD、投连险、券商集合理财
	 * 信托产品PE、保险产品、券商佣金、私募股权PE、投资移民）*/
	private String productType;
	/**5、产品信息格式定义(XML格式)*/
	private String productDef;
	/**6、产品信息格式定义类型*/
	private String productDefType;
	/**7、产品特点*/
	private String productDesc;
	/**8、产品年化收益，手工输入*/
	private String productIncome;
	/**9、上线时间*/
	private String createTimeCRM;
	/**10、成立时间*/
	private String productFoundTime;
	/**11、销售状态，选择列表，包括：份额满，结束，可打款，可预约，预热期*/
	private String purchasePhase;
	/**12、购买起点，单位万*/
	private Float purchaseStartPoint;
	/**13、销售开始时间*/
	private String saleStartTime;
	/**14、销售结束时间*/
	private String saleEndTime;
	/**15、佣金比例，单位%*/
	private Float commisionRate;
	/**16、阶梯佣金比例*/
	private String ladderCommisionRate;
	/**17、实际佣金比例*/
	private Float factCommisionRate;
	/**18、产品期限，单位：月*/
	private Integer productTerm;
	/**19、产品结束时间*/
	private String productFinishTime;
	/**20、风险收益特征*/
	private String ventureCharacter;
	/**21、研究部建议*/
	private String productAdvice;
	/**22、产品状态，选择列表，包括终止，提前终止，正常*/
	private String productStatus;
	/**23、合同文件名*/
	private String pad1;
	/**24、收益分配方式*/
	private String pad2;
	/**25、备用字段3*/
	private String pad3;
	/**26、分配日期*/
	private String pad4;
	/**27、备用字段5*/
	private String pad5;
	/**28、私募开放日*/
	private String pad6;
	/**29、财汇产品代码*/
	private String pad7;
	/**30、产品简称*/
	private String pad8;
	/**31、产品同步判断标识*/
	private String pad9;
	/**32、备用字段10*/
	private String pad10;
	
	
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

	/**财汇数据库实体类*/
	private TrustProduct trustProduct;
	
	//上架标记
	private Integer addedFlag;
	
	//更新标记
	private Integer updFlag;
	

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductDef() {
		return productDef;
	}
	public void setProductDef(String productDef) {
		this.productDef = productDef;
	}
	public String getProductDefType() {
		return productDefType;
	}
	public void setProductDefType(String productDefType) {
		this.productDefType = productDefType;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductIncome() {
		return productIncome;
	}
	public void setProductIncome(String productIncome) {
		this.productIncome = productIncome;
	}

	public String getCreateTimeCRM() {
		return createTimeCRM;
	}
	public void setCreateTimeCRM(String createTimeCRM) {
		this.createTimeCRM = createTimeCRM;
	}
	public String getProductFoundTime() {
		return productFoundTime;
	}
	public void setProductFoundTime(String productFoundTime) {
		this.productFoundTime = productFoundTime;
	}
	public String getPurchasePhase() {
		return purchasePhase;
	}
	public void setPurchasePhase(String purchasePhase) {
		this.purchasePhase = purchasePhase;
	}

	public Float getPurchaseStartPoint() {
		return purchaseStartPoint;
	}
	public void setPurchaseStartPoint(Float purchaseStartPoint) {
		this.purchaseStartPoint = purchaseStartPoint;
	}
	public String getSaleStartTime() {
		return saleStartTime;
	}
	public void setSaleStartTime(String saleStartTime) {
		this.saleStartTime = saleStartTime;
	}
	public String getSaleEndTime() {
		return saleEndTime;
	}
	public void setSaleEndTime(String saleEndTime) {
		this.saleEndTime = saleEndTime;
	}
	public Float getCommisionRate() {
		return commisionRate;
	}
	public void setCommisionRate(Float commisionRate) {
		this.commisionRate = commisionRate;
	}
	public String getLadderCommisionRate() {
		return ladderCommisionRate;
	}
	public void setLadderCommisionRate(String ladderCommisionRate) {
		this.ladderCommisionRate = ladderCommisionRate;
	}
	public Float getFactCommisionRate() {
		return factCommisionRate;
	}
	public void setFactCommisionRate(Float factCommisionRate) {
		this.factCommisionRate = factCommisionRate;
	}

	public Integer getProductTerm() {
		return productTerm;
	}
	public void setProductTerm(Integer productTerm) {
		this.productTerm = productTerm;
	}
	public String getProductFinishTime() {
		return productFinishTime;
	}
	public void setProductFinishTime(String productFinishTime) {
		this.productFinishTime = productFinishTime;
	}
	public String getVentureCharacter() {
		return ventureCharacter;
	}
	public void setVentureCharacter(String ventureCharacter) {
		this.ventureCharacter = ventureCharacter;
	}
	public String getProductAdvice() {
		return productAdvice;
	}
	public void setProductAdvice(String productAdvice) {
		this.productAdvice = productAdvice;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public String getPad1() {
		return pad1;
	}
	public void setPad1(String pad1) {
		this.pad1 = pad1;
	}
	public String getPad2() {
		return pad2;
	}
	public void setPad2(String pad2) {
		this.pad2 = pad2;
	}
	public String getPad3() {
		return pad3;
	}
	public void setPad3(String pad3) {
		this.pad3 = pad3;
	}
	public String getPad4() {
		return pad4;
	}
	public void setPad4(String pad4) {
		this.pad4 = pad4;
	}
	public String getPad5() {
		return pad5;
	}
	public void setPad5(String pad5) {
		this.pad5 = pad5;
	}
	public String getPad6() {
		return pad6;
	}
	public void setPad6(String pad6) {
		this.pad6 = pad6;
	}
	public String getPad7() {
		return pad7;
	}
	public void setPad7(String pad7) {
		this.pad7 = pad7;
	}
	public String getPad8() {
		return pad8;
	}
	public void setPad8(String pad8) {
		this.pad8 = pad8;
	}
	public String getPad9() {
		return pad9;
	}
	public void setPad9(String pad9) {
		this.pad9 = pad9;
	}
	public String getPad10() {
		return pad10;
	}
	public void setPad10(String pad10) {
		this.pad10 = pad10;
	}
	public Integer getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	public Integer getAddedFlag() {
		return addedFlag;
	}
	public void setAddedFlag(Integer addedFlag) {
		this.addedFlag = addedFlag;
	}
	public TrustProduct getTrustProduct() {
		return trustProduct;
	}
	public void setTrustProduct(TrustProduct trustProduct) {
		this.trustProduct = trustProduct;
	}
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
	public Integer getUpdFlag() {
		return updFlag;
	}
	public void setUpdFlag(Integer updFlag) {
		this.updFlag = updFlag;
	}

}