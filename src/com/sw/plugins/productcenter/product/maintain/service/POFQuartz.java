package com.sw.plugins.productcenter.product.maintain.service;

import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sw.core.common.Constant;
import com.sw.core.data.dao.CommonDao;
import com.sw.core.exception.DetailException;
import com.sw.core.quartz.IQuartz;
import com.sw.plugins.productcenter.product.maintain.entity.CRMProduct;
import com.sw.plugins.productcenter.product.maintain.entity.POFData;
import com.sw.plugins.productcenter.product.maintain.entity.TrustProduct;
import com.sw.thirdparty.crm.webservice.FcTrust;
import com.sw.thirdparty.crm.webservice.FcTrustFQNav;
import com.sw.thirdparty.crm.webservice.FcTrustNav;
import com.sw.thirdparty.crm.webservice.IFCTrustWebService;
import com.sw.thirdparty.finchina.webservice.impl.FCTrustWebServiceServiceLocator;
import com.sw.thirdparty.webservice.WebserviceConfigCache;

/**
 *  类简要说明
 *  @author Administrator
 *  @version 1.0
 *  </pre>
 *  Created on :下午1:33:00
 *  LastModified:
 *  History:
 *  </pre>
 */
public class POFQuartz implements IQuartz {

	private static Logger logger = Logger.getLogger(POFQuartz.class);
	private CommonDao commonDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public void execute() {
		this.synchronousPOF();
		this.calculationPOF();
	}
	
	 /**
	 *  同步私募基金数据
	 *  @author baokai
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-11 下午1:33:32
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	private void synchronousPOF() {
		logger.debug("synchronous POF , please waiting...");
		try {
			// 定义WebService接口
			IFCTrustWebService iFCTrustWebService = (new FCTrustWebServiceServiceLocator()).getFCTrustWebServicePort(new URL(WebserviceConfigCache.getCache("fcTrust").getValue()));
			// 获取TrustProduct列表
			List<TrustProduct> trustProductList = commonDao.selectList("trustProduct.select");
			List<String> trustCodesUpdate = new ArrayList<String>();
			for (int i = 0; i < trustProductList.size(); i++) {
				TrustProduct trustProduct = trustProductList.get(i);
				if (trustProduct != null) {
					if (trustProduct.getTrustCode() != null	&& !trustProduct.getTrustCode().equals(""))
						trustCodesUpdate.add(trustProduct.getTrustCode());
				}
			}
			// 更新数据
			if (trustCodesUpdate.size() > 0) {
				FcTrust[] fcTrustsUpdate = iFCTrustWebService.findFCTrust((String[]) trustCodesUpdate.toArray(new String[trustCodesUpdate.size()]));
				Map<String, FcTrust> fcTrustMapUpdate = new HashMap<String, FcTrust>();
				for (int i = 0; i < fcTrustsUpdate.length; i++) {
					FcTrust fctTemp = fcTrustsUpdate[i];
					if (fctTemp != null && fctTemp.getTrustCode() != null && !fctTemp.getTrustCode().equals("")) {
						fcTrustMapUpdate.put(fctTemp.getTrustCode(), fctTemp);
					}
				}
				int succCount = 0;
				int failCount = 0;
				for (int i = 0; i < trustProductList.size(); i++) {
					TrustProduct tpTemp = trustProductList.get(i);
					if (tpTemp != null && tpTemp.getTrustCode() != null	&& !tpTemp.getTrustCode().equals("")) {
						FcTrust fcTrust = fcTrustMapUpdate.get(tpTemp.getTrustCode());
						if (!fcTrust.getTmstamp().equals(tpTemp.getTmstamp())) {
							TrustProduct trustProduct = new TrustProduct();
							trustProduct.setTrustName(fcTrust.getTrustName());
							trustProduct.setTrustCode(fcTrust.getTrustCode());
							trustProduct.setTrustAName(fcTrust.getTrustAName());
							trustProduct.setSspell(fcTrust.getSspell());
							trustProduct.setDeclareDate(fcTrust.getDeclareDate());
							trustProduct.setUpdateDate(fcTrust.getUpdateDate());
							trustProduct.setTrust22(fcTrust.getTrust22());
							trustProduct.setTrust31(fcTrust.getTrust31());
							trustProduct.setTrust28(fcTrust.getTrust28());
							trustProduct.setType(fcTrust.getType());
							trustProduct.setTrust30(fcTrust.getTrust30());
							trustProduct.setIsstat(fcTrust.getIsstat());
							trustProduct.setTrust16(fcTrust.getTrust16());
							trustProduct.setTrust17(fcTrust.getTrust17());
							trustProduct.setTrust32(fcTrust.getTrust32());
							trustProduct.setTrust1(fcTrust.getTrust1());
							trustProduct.setTrust23(fcTrust.getTrust23());
							trustProduct.setTrust2(fcTrust.getTrust2());
							trustProduct.setTrust3(fcTrust.getTrust3());
							trustProduct.setTrust27(fcTrust.getTrust27());
							trustProduct.setTrust4(fcTrust.getTrust4());
							trustProduct.setTrust29(fcTrust.getTrust29());
							trustProduct.setTrust34(fcTrust.getTrust34());
							trustProduct.setTrust5(fcTrust.getTrust5());
							trustProduct.setTrust6(fcTrust.getTrust6());
							trustProduct.setTrust26(fcTrust.getTrust26());
							trustProduct.setTrust38(fcTrust.getTrust38());
							trustProduct.setTrust7(fcTrust.getTrust7());
							trustProduct.setTrust41(fcTrust.getTrust41());
							trustProduct.setTrust9(fcTrust.getTrust9());
							trustProduct.setTrust8(fcTrust.getTrust8());
							trustProduct.setTrust18(fcTrust.getTrust18());
							trustProduct.setTrust24(fcTrust.getTrust24());
							trustProduct.setTrust37(fcTrust.getTrust37());
							trustProduct.setTrust25(fcTrust.getTrust25());
							trustProduct.setTrust10(fcTrust.getTrust10());
							trustProduct.setTrust11(fcTrust.getTrust11());
							trustProduct.setTrust12(fcTrust.getTrust12());
							trustProduct.setTrust21(fcTrust.getTrust21());
							trustProduct.setTrust19(fcTrust.getTrust19());
							trustProduct.setTrust13(fcTrust.getTrust13());
							trustProduct.setTrust20(fcTrust.getTrust20());
							trustProduct.setTrust39(fcTrust.getTrust39());
							trustProduct.setTrust40(fcTrust.getTrust40());
							trustProduct.setTrust14(fcTrust.getTrust14());
							trustProduct.setTrust35(fcTrust.getTrust35());
							trustProduct.setTrust15(fcTrust.getTrust15());
							trustProduct.setTrust33(fcTrust.getTrust33());
							trustProduct.setTrust36(fcTrust.getTrust36());
							trustProduct.setMemo(fcTrust.getMemo());
							trustProduct.setDataSource(fcTrust.getDataSource());
							trustProduct.setTmstamp(fcTrust.getTmstamp());
							int flag = commonDao.update("trustProduct.update",trustProduct);
							if (flag == 1) {
								logger.debug("trustcode: " + trustProduct.getTrustCode() + " update success");
								succCount++;
								//更新状态位
								String crmId = tpTemp.getCrmId();
								if(crmId != null && !crmId.equals("")){
									String[] crmIds = crmId.split(",");
									for (int j = 0; j < crmIds.length; j++) {
										if(crmIds[j]!=null && !crmIds[j].equals("")){
											CRMProduct crm = new CRMProduct();
											crm.setSerialNo(Integer.valueOf(crmIds[j]));
											crm.setUpdFlag(Constant.PRD_PRODUCTUPDFLAG_UPDATE);
											commonDao.update("crmproduct.updateUpdFlag", crm);
										}
									}
								}
							} else {
								logger.debug("trustcode: " + trustProduct.getTrustCode() + " update fail");
								failCount++;
							}
						}
					}
				}
				logger.debug(" update success " + succCount + " number ");
				logger.debug(" update fail " + failCount + " number ");
			}
			logger.debug("synchronous POF update finish!");
			/**
			// 新增数据
			// 获取产品Array
			List<CRMProduct> crmProductList = commonDao.selectList("crmproduct.getCrmTypeIn");
			if (crmProductList == null || crmProductList.size() == 0) {
				logger.debug("synchronous POF insert finish!");
				return;
			}
			// 取所有新增数据的财汇编号
			List<String> trustCodesInsert = new ArrayList<String>();
			for (int i = 0; i < crmProductList.size(); i++) {
				CRMProduct crmTemp = crmProductList.get(i);
				if (crmTemp != null && crmTemp.getPad7() != null && !crmTemp.getPad7().equals("") && !trustCodesUpdate.contains(crmTemp.getPad7())) {
					trustCodesInsert.add(crmTemp.getPad7());
				}
			}
			if (trustCodesInsert.size() == 0) {
				logger.debug("synchronous POF insert finish!");
				return;
			}
			FcTrust[] fcTrustsInsert = iFCTrustWebService.findFCTrust((String[]) trustCodesInsert.toArray(new String[trustCodesInsert.size()]));
			Map<String, FcTrust> fcTrustMapInsert = new HashMap<String, FcTrust>();
			for (int i = 0; i < fcTrustsInsert.length; i++) {
				FcTrust fctTemp = fcTrustsInsert[i];
				if (fctTemp != null && fctTemp.getTrustCode() != null && !fctTemp.getTrustCode().equals("")) {
					fcTrustMapInsert.put(fctTemp.getTrustCode(), fctTemp);
				}
			}
			int succCount = 0;
			int failCount = 0;
			for (int i = 0; i < crmProductList.size(); i++) {
				CRMProduct crmTemp = crmProductList.get(i);
				String trustCode = crmTemp.getPad7();
				if (crmTemp != null && crmTemp.getPad7() != null && !crmTemp.getPad7().equals("") && !trustCodesUpdate.contains(crmTemp.getPad7())) {
					TrustProduct tp = new TrustProduct();
					tp.setTrustCode(trustCode);
					FcTrust fcTrust = fcTrustMapInsert.get(trustCode);
					TrustProduct trustProduct = new TrustProduct();
					trustProduct.setCrmId(crmTemp.getSerialNo());
//					trustProduct.setTrustId(fcTrust.getTrustId());
					trustProduct.setTrustName(fcTrust.getTrustName());
					trustProduct.setTrustCode(fcTrust.getTrustCode());
					trustProduct.setTrustAName(fcTrust.getTrustAName());
					trustProduct.setSspell(fcTrust.getSspell());
					trustProduct.setDeclareDate(fcTrust.getDeclareDate());
					trustProduct.setUpdateDate(fcTrust.getUpdateDate());
					trustProduct.setTrust22(fcTrust.getTrust22());
					trustProduct.setTrust31(fcTrust.getTrust31());
					trustProduct.setTrust28(fcTrust.getTrust28());
					trustProduct.setType(fcTrust.getType());
					trustProduct.setTrust30(fcTrust.getTrust30());
					trustProduct.setIsstat(fcTrust.getIsstat());
					trustProduct.setTrust16(fcTrust.getTrust16());
					trustProduct.setTrust17(fcTrust.getTrust17());
					trustProduct.setTrust32(fcTrust.getTrust32());
					trustProduct.setTrust1(fcTrust.getTrust1());
					trustProduct.setTrust23(fcTrust.getTrust23());
					trustProduct.setTrust2(fcTrust.getTrust2());
					trustProduct.setTrust3(fcTrust.getTrust3());
					trustProduct.setTrust27(fcTrust.getTrust27());
					trustProduct.setTrust4(fcTrust.getTrust4());
					trustProduct.setTrust29(fcTrust.getTrust29());
					trustProduct.setTrust34(fcTrust.getTrust34());
					trustProduct.setTrust5(fcTrust.getTrust5());
					trustProduct.setTrust6(fcTrust.getTrust6());
					trustProduct.setTrust26(fcTrust.getTrust26());
					trustProduct.setTrust38(fcTrust.getTrust38());
					trustProduct.setTrust7(fcTrust.getTrust7());
					trustProduct.setTrust41(fcTrust.getTrust41());
					trustProduct.setTrust9(fcTrust.getTrust9());
					trustProduct.setTrust8(fcTrust.getTrust8());
					trustProduct.setTrust18(fcTrust.getTrust18());
					trustProduct.setTrust24(fcTrust.getTrust24());
					trustProduct.setTrust37(fcTrust.getTrust37());
					trustProduct.setTrust25(fcTrust.getTrust25());
					trustProduct.setTrust10(fcTrust.getTrust10());
					trustProduct.setTrust11(fcTrust.getTrust11());
					trustProduct.setTrust12(fcTrust.getTrust12());
					trustProduct.setTrust21(fcTrust.getTrust21());
					trustProduct.setTrust19(fcTrust.getTrust19());
					trustProduct.setTrust13(fcTrust.getTrust13());
					trustProduct.setTrust20(fcTrust.getTrust20());
					trustProduct.setTrust39(fcTrust.getTrust39());
					trustProduct.setTrust40(fcTrust.getTrust40());
					trustProduct.setTrust14(fcTrust.getTrust14());
					trustProduct.setTrust35(fcTrust.getTrust35());
					trustProduct.setTrust15(fcTrust.getTrust15());
					trustProduct.setTrust33(fcTrust.getTrust33());
					trustProduct.setTrust36(fcTrust.getTrust36());
					trustProduct.setMemo(fcTrust.getMemo());
					trustProduct.setDataSource(fcTrust.getDataSource());
					trustProduct.setTmstamp(fcTrust.getTmstamp());
					int flag = commonDao.insert("trustProduct.insert", trustProduct);
					if (flag == 1) {
						logger.debug("trustcode: " + trustProduct.getTrustCode() + " insert success");
						succCount++;
					} else {
						logger.debug("trustcode: " + trustProduct.getTrustCode() + " insert fail");
						failCount++;
					}
				}
			}
			logger.debug(" insert success " + succCount + " number ");
			logger.debug(" insert fail " + failCount + " number ");
			logger.debug("synchronous POF insert finish!");
			 */
		} catch (Exception e) {
			logger.debug("synchronous POF fail!");
			String debug = DetailException.expDetail(e, POFQuartz.class);
			logger.debug(debug);
		}
	}

	/**
	 *  计算私募基金数据
	 *  @author baokai
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-11 下午1:34:10
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	private void calculationPOF() {
		logger.debug("calculation POF , please waiting...");
		try {
			//取prd_trust表数据
			List<TrustProduct> trustProductList = commonDao.selectList("trustProduct.select");
			//组织trustCodeList
			List<String> trustCodes = new ArrayList<String>();
			for (int i = 0; i < trustProductList.size(); i++) {
				if(!trustCodes.contains(trustProductList.get(i).getTrustCode()))
					trustCodes.add(trustProductList.get(i).getTrustCode());
			}
			IFCTrustWebService iFCTrustWebService = (new FCTrustWebServiceServiceLocator()).getFCTrustWebServicePort(new URL(WebserviceConfigCache.getCache("fcTrust").getValue()));
			//查询所有trustCode相关的单位净值数据
			FcTrustNav[] fcTrustNavList = iFCTrustWebService.findFCTrustNav((String[]) trustCodes.toArray(new String[trustCodes.size()]));
			Map<String,List<FcTrustNav>> fcTrustNavMap = new HashMap<String,List<FcTrustNav>>();
			for (int i = 0; i < fcTrustNavList.length; i++) {
				if(fcTrustNavMap.containsKey(fcTrustNavList[i].getTrustCode())){
					fcTrustNavMap.get(fcTrustNavList[i].getTrustCode()).add(fcTrustNavList[i]);
				}else{
					List<FcTrustNav> tempList = new ArrayList<FcTrustNav>();
					tempList.add(fcTrustNavList[i]);
					fcTrustNavMap.put(fcTrustNavList[i].getTrustCode(), tempList);
				}
			}
			//查询所有trustCode相关的证卷日回报系数表中的数据
			FcTrustFQNav[] fcTrustFQNavList = iFCTrustWebService.findFCTrustFQNav((String[])trustCodes.toArray(new String[trustCodes.size()]));
			Map<String,List<FcTrustFQNav>> fcTrustFQNavMap = new HashMap<String,List<FcTrustFQNav>>();
			for (int i = 0; i < fcTrustFQNavList.length; i++) {
				if(fcTrustFQNavMap.containsKey(fcTrustFQNavList[i].getTrustCode())){
					fcTrustFQNavMap.get(fcTrustFQNavList[i].getTrustCode()).add(fcTrustFQNavList[i]);
				}else{
					List<FcTrustFQNav> tempList = new ArrayList<FcTrustFQNav>();
					tempList.add(fcTrustFQNavList[i]);
					fcTrustFQNavMap.put(fcTrustFQNavList[i].getTrustCode(), tempList);
				}
			}
			//循环prd_trust数据，逐条计算相关收益，并更新私募数据表
			for (int i = 0; i < trustProductList.size(); i++) {
				TrustProduct tp = trustProductList.get(i);
				String trustCode = tp.getTrustCode();
				List<FcTrustNav> navList = fcTrustNavMap.get(trustCode);
				List<FcTrustFQNav> fqNavList = fcTrustFQNavMap.get(trustCode);
				logger.debug("calculation trustCode:"+trustCode);
				POFData pofData = CalculationPOFUtil.calculationDetail(trustCode, navList, fqNavList);
				if(pofData!=null && pofData.getTrustCode() !=  null && !pofData.getTrustCode().equals("")){
					if(this.getRecordCount(pofData)==0){
						commonDao.save("pofData.insert", pofData);
					}else{
						commonDao.update("pofData.update", pofData);
					}
				}
			}
			logger.debug("calculation POF finish!");
		} catch (Exception e) {
			logger.debug("calculation POF fail!");
			String debug = DetailException.expDetail(e, POFQuartz.class);
			logger.debug(debug);
		}
	}
	
	
	private Long getRecordCount(POFData entity) throws Exception {
		Object obj = commonDao.selectObject("pofData.selectCount",entity);
		if (obj instanceof Integer){
			return (Long)obj;
		}
		else if (obj instanceof Long){
			return (Long)obj;
		}
		return 0l;
	}

}
