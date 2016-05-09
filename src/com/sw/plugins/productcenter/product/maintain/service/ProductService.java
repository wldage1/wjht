package com.sw.plugins.productcenter.product.maintain.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sw.core.common.Constant;
import com.sw.core.common.SystemProperty;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.data.entity.ProductSort;
import com.sw.core.exception.DetailException;
import com.sw.core.initialize.InitialData;
import com.sw.core.initialize.PluginsConfigCache;
import com.sw.core.initialize.PluginsConfigInit;
import com.sw.core.service.CommonService;
import com.sw.core.util.CommonUtil;
import com.sw.core.util.DateUtil;
import com.sw.core.util.FileUtil;
import com.sw.core.util.FreemarkerUtil;
import com.sw.core.util.HttpClientUtil;
import com.sw.core.util.RsyncUtil;
import com.sw.core.util.poi.excel.write.Excel2007WriteHandler;
import com.sw.plugins.productcenter.attribute.structure.entity.Structure;
import com.sw.plugins.productcenter.attribute.structure.service.StructureService;
import com.sw.plugins.productcenter.product.maintain.entity.CRMProduct;
import com.sw.plugins.productcenter.product.maintain.entity.POFData;
import com.sw.plugins.productcenter.product.maintain.entity.Product;
import com.sw.plugins.productcenter.product.maintain.entity.ProductPdf;
import com.sw.plugins.productcenter.product.maintain.entity.ProductValue;
import com.sw.plugins.productcenter.product.maintain.entity.TrustProduct;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;
import com.sw.thirdparty.crm.webservice.CrmProduct;
import com.sw.thirdparty.crm.webservice.CrmTradeRecordProduct;
import com.sw.thirdparty.crm.webservice.FcTrust;
import com.sw.thirdparty.crm.webservice.FcTrustFQNav;
import com.sw.thirdparty.crm.webservice.FcTrustNav;
import com.sw.thirdparty.crm.webservice.ICRMTradeRecordProductWebService;
import com.sw.thirdparty.crm.webservice.IFCTrustWebService;
import com.sw.thirdparty.crm.webservice.impl.CRMProductWebServiceServiceLocator;
import com.sw.thirdparty.crm.webservice.impl.CRMTradeRecordProductWebServiceServiceLocator;
import com.sw.thirdparty.finchina.webservice.impl.FCTrustWebServiceServiceLocator;
import com.sw.thirdparty.webservice.WebserviceConfig;
import com.sw.thirdparty.webservice.WebserviceConfigCache;

@Service
public class ProductService extends CommonService<Product> {

	private static Logger logger = Logger.getLogger(ProductService.class);

	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private StructureService structureService;
	
	@Autowired
	private InitialData initialData;

	public static Map<String, Object> productSort;

	/**
	 * 加载产品初始化
	 * 
	 * @throws ServletException
	 * @author Administrator
	 * @version 1.0 </pre> Created on :2012-5-23 上午09:24:05 LastModified:
	 *          History: </pre>
	 */
	public void init() throws ServletException {
		try {
			logger.debug("data productSort info initializing , please waiting...");
			if (productSort != null) {
				Iterator<String> iterator = productSort.keySet().iterator();
				while (iterator.hasNext()) {
					String key = null, value = null;
					key = iterator.next();
					if (key != null) {
						value = productSort.get(key) == null ? "" : productSort.get(key).toString();
					}
					if (key != null && value != null) {
						ProductSort productSort = new ProductSort();
						productSort.setName(value);
						productSort.setValue(key);
						PluginsConfigCache.putProductSortCache(productSort);
					}
				}
			}
			logger.debug("data product sort info initialize finish.");
		} catch (Exception e) {
			logger.debug("data product sort info initialize fail！");
			String debug = DetailException.expDetail(e, PluginsConfigInit.class);
			logger.debug(debug);
			/** 异常退出系统 */
			System.exit(0);
		}
	}

	/**
	 * 删除列表信息（真删除）
	 */
	public void delete(Product entity, HttpServletRequest request) throws Exception {
		Product paramProduct = new Product();
		if (entity != null && entity.getIds() != null) {
			for (String id : entity.getIds()) {
				ProductPdf productPdf = new ProductPdf();
				productPdf.setPrdId(Long.parseLong(id));
				deletePdfFileByPrdId(productPdf, request);
				paramProduct.setPrdId(Long.parseLong(id));

				// 更新CRM产品
				paramProduct = (Product) getCommonDao().selectObject("product.selectByOne", paramProduct);
				getCommonDao().update("product.updateCRM", paramProduct);

				super.getCommonDao().delete("product.delete", paramProduct);
			}
		}
		// 单条删除
		if (entity != null && entity.getId() != null) {
			ProductPdf productPdf = new ProductPdf();
			productPdf.setPrdId(entity.getId());
			deletePdfFileByPrdId(productPdf, request);
			paramProduct.setPrdId(entity.getId());

			// 更新CRM产品
			paramProduct = (Product) getCommonDao().selectObject("product.selectByOne", paramProduct);
			getCommonDao().update("product.updateCRM", paramProduct);
			super.getCommonDao().delete("product.delete", paramProduct);
		}

	}

	/**
	 * 列表信息——产品还原
	 */
	public void restore(Product entity) throws Exception {
		Product paramProduct = new Product();
		if (entity != null && entity.getIds() != null) {
			for (String id : entity.getIds()) {
				paramProduct.setPrdId(Long.parseLong(id));
				paramProduct.setDelFlag(0);
				super.getCommonDao().delete("product.update", paramProduct);
			}
		}
		// 单条还原
		if (entity != null && entity.getId() != null) {
			paramProduct.setPrdId(entity.getId());
			paramProduct.setDelFlag(0);
			super.getCommonDao().delete("product.update", paramProduct);

		}
	}

	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 产品列表删除产品——假删除
	 */
	@Override
	public void deleteByArr(Product entity) throws Exception {
		Product paramProduct = new Product();
		if (entity != null && entity.getIds() != null) {
			for (String id : entity.getIds()) {
				paramProduct.setPrdId(Long.parseLong(id));
				paramProduct.setDelFlag(1);
				super.getCommonDao().delete("product.update", paramProduct);
			}
		}
		// 单条删除
		if (entity != null && entity.getId() != null) {
			paramProduct.setPrdId(entity.getId());
			paramProduct.setDelFlag(1);
			super.getCommonDao().delete("product.update", paramProduct);
		}
	}

	@Override
	public void deleteByIn(Product entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<? extends BaseEntity> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 按产品值和属性查找符合条件的产品列表
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 * @author Administrator
	 * @version 1.0 </pre> Created on :2012-5-24 上午11:15:22 LastModified:
	 *          History: </pre>
	 */
	@SuppressWarnings("unchecked")
	public List<ProductValue> getPrdList(ProductValue entity) {
		return getCommonDao().selectList("productvalue.selectPrdVal", entity);
	}

	public Long getAllRecordCount() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 列表——加载数据方法 by haoyuanfu
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getGrid(Product entity) {
		List<Object> list = new ArrayList<Object>();
		List<Product> resultList = null;
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.PRD_TYPE_PRODUCTFROM); // 产品来源类型
		Map<Long, Dictionary> productFromMap = dictionaryService.getDictMap(dictionary);
		List<ProductSort> productTypeList = (List<ProductSort>) PluginsConfigCache.getAllProductSortCache(); // 产品类型
		if (entity.getAttributeAndValue() != null && !entity.getAttributeAndValue().equals("")) {
			String attributeString = entity.getAttributeAndValue(); // 查询条件和值组合的字符串
			String[] attrArray = attributeString.split("@");// 查询条件组合成数组
			String selectPrdIds = ""; // 符合条件的产品id集合
			for (int i = 0; i < attrArray.length; i++) {
				String[] valueArray = attrArray[i].split("#"); // 属性和值分离
				// 如果查询条件有值
				if (valueArray.length >= 2) {
					ProductValue pv = new ProductValue(); // 查询条件
					List<ProductValue> pvList = new ArrayList<ProductValue>(); // 查询出来的结果集
					pv.setPrdAttribute(Long.parseLong(valueArray[0])); // 查询条件——产品属性ID
					// 如果查询条件中包含“%”或“_”处理
					pv.setPrdValue(CommonUtil.convertSearchSign(valueArray[1])); // 查询条件——查询对应属性产品值
					if (selectPrdIds != null && !selectPrdIds.equals("")) {
						pv.setSelectPrdIds(selectPrdIds);
					}
					pvList = getPrdList(pv);
					String selStr = ""; // 本次查询包含的IDS
					for (int j = 0; j < pvList.size(); j++) {
						String str = ""; // 组成的ids字符串
						if (j == 0) {
							str += pvList.get(j).getPrdId().toString();
						} else {
							str += "," + pvList.get(j).getPrdId();
						}
						selStr += str;
					}
					if (!selStr.equals("")) {
						selectPrdIds = selStr;
					} else {
						// 如果为空则制成' '
						selectPrdIds = " \' \' ";
					}
					entity.setSelectPrdIds(selectPrdIds);
				}
			}
		}
		resultList = getCommonDao().selectList("product.select", entity);
		for (Product tempProduct : resultList) {
			Map<String, Object> maprole = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			// 产品名称
			celllist.add(tempProduct.getPrdName());
			// 产品序号
			celllist.add(tempProduct.getCrmPrdId());
			// 取产品类型
			if (tempProduct.getPrdType() == 0) {
				celllist.add(" ");
			} else {
				for (ProductSort ps : productTypeList) {
					if (Long.parseLong(ps.getValue()) == tempProduct.getPrdType()) {
						celllist.add(ps.getName());
					}
				}
			}
			// 产品编号
			celllist.add(tempProduct.getPrdNo());
			// 数据字典取产品来源名称
			if (productFromMap.get((long) tempProduct.getPrdFrom()) != null) {
				celllist.add(productFromMap.get((long) tempProduct.getPrdFrom()).getName());
			} else {
				celllist.add("");
			}
			// 产品可分享状态
			celllist.add(tempProduct.getShared());
			// 产品状态
			celllist.add(tempProduct.getPrdStatus());
			maprole.put("id", tempProduct.getPrdId());
			maprole.put("cell", celllist);
			list.add(maprole);
		}

		// 记录数
		long record = 0;
		try {
			record = getRecordCount(entity);
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}

		// 页数
		int pageCount = (int) Math.ceil(record / (double) entity.getRows());
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("rows", list);
		map.put("page", entity.getPage());
		map.put("total", pageCount);
		map.put("records", record);
		return map;
	}

	/**
	 * 添加页面——加载对应产品属性列表 by haoyuanfu
	 */

	public Map<String, Object> getAttribute(Product entity) {
		List<Object> list = new ArrayList<Object>();
		List<Structure> StructureList = null;
		Structure structure = new Structure();
		structure.setProductTypeId(entity.getPrdType());
		// 加载数据字典中的验证规则
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.DICT_TYPE_PRODUCTSTRUCTUREVERIFY); // 属性验证规则
		Map<Long, Dictionary> prdVerifyMap = dictionaryService.getDictMap(dictionary);
		try {
			// 查询当前产品属性在属性表中查询对应属性列表
			StructureList = structureService.getListByOrder(structure);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 构建属性List拼装成json
		for (Structure tempstructure : StructureList) {
			Map<String, Object> mapList = new Hashtable<String, Object>();
			// 属性ID
			mapList.put("id", tempstructure.getId());
			// 中文名称
			mapList.put("chineseName", tempstructure.getChineseName());
			// 显示类型（用于生成填入框）
			mapList.put("showType", tempstructure.getShowType());
			// 是否必填&验证规则
			mapList.put("isRequired", tempstructure.getIsRequired());
			// 数据字典取属性验证信息
			if (prdVerifyMap.get((long) tempstructure.getVerify()) != null) {
				String str = "";
				if (tempstructure.getVerify() == 1) {
					str = String.valueOf(tempstructure.getVerify() + "#" + "1");
				} else {
					str = prdVerifyMap.get((long) tempstructure.getVerify()).getName() + "#" + prdVerifyMap.get((long) tempstructure.getVerify()).getDescription();
				}
				mapList.put("verify", str);
			} else {
				mapList.put("verify", "1#1");
			}
			// 是否启用
			mapList.put("isEnabled", tempstructure.getIsEnabled());
			// 下拉框、多选框、单选框的值
			if (tempstructure.getValue() != null) {
				mapList.put("checkValue", tempstructure.getValue());
			} else {
				mapList.put("checkValue", "");
			}
			list.add(mapList);
		}
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("StructureList", list);
		// list长度用于循环产生属性值
		map.put("listSize", list.size());
		return map;
	}

	/**
	 * 按条件获取列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<? extends BaseEntity> getList(Product entity) throws Exception {
		return getCommonDao().selectList("product.select", entity);
	}

	/**
	 * 按条件查询一条产品信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Product getOneById(Product entity) throws Exception {
		if (entity.getPrdId() == null)
			return null;
		Product prd = (Product) getCommonDao().selectObject("product.selectByOne", entity);
		ProductPdf pp = new ProductPdf();
		pp.setPrdId(entity.getPrdId());
		// 获取与当前产品相关的PDF文件集合(Ipad)
		List<ProductPdf> ipadList = getCommonDao().selectList("productpdf.selectByPrdIdIpad", pp);
		if (ipadList.size() != 0) {
			prd.setIpadFileList(ipadList);
		}
		// 获取与当前产品相关的PDF文件集合(Iphone)
		List<ProductPdf> iphoneList = getCommonDao().selectList("productpdf.selectByPrdIdIphone", pp);
		if (iphoneList.size() != 0) {
			prd.setIphoneFileList(iphoneList);
		}
		return prd;
	}

	/**
	 * 
	 * 按照产品ID获取对应属性和值
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-5-22 上午10:11:35 LastModified:
	 *          History: </pre>
	 */
	public Map<String, Object> getPrdAndAttrById(Product entity) {
		List<Object> list = new ArrayList<Object>();
		// 查询当前产品属性在属性表中查询对应属性列表
		List<Structure> structureList = null;
		Structure structure = new Structure();
		// 已存储产品值列表
		List<ProductValue> prdValList = null;
		ProductValue productValue = new ProductValue();
		// 加载数据字典中的验证规则
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.DICT_TYPE_PRODUCTSTRUCTUREVERIFY); // 属性验证规则
		Map<Long, Dictionary> prdVerifyMap = dictionaryService.getDictMap(dictionary);
		// 取出对应产品ID的附加属性值
		try {
			// 根据当前产品类型获取类型值
			entity = getOneById(entity);
			structure.setProductTypeId(entity.getPrdType());
			// 当前类型属性list
			structureList = structureService.getListByOrder(structure);
			// 根据产品ID获取对应的属性对应的值
			productValue.setPrdId(entity.getPrdId());
			prdValList = getPrdValList(productValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Structure tempstructure : structureList) {
			Map<String, Object> mapList = new Hashtable<String, Object>();
			// 属性ID
			mapList.put("id", tempstructure.getId());
			// 中文名称
			mapList.put("chineseName", tempstructure.getChineseName());
			// 显示类型（用于生成填入框）
			mapList.put("showType", tempstructure.getShowType());
			// 是否必填
			mapList.put("isRequired", tempstructure.getIsRequired());
			// 是否启用
			mapList.put("isEnabled", tempstructure.getIsEnabled());
			// 下拉框、多选框、单选框的值
			if (tempstructure.getValue() != null) {
				mapList.put("checkValue", tempstructure.getValue());
			} else {
				mapList.put("checkValue", "");
			}
			mapList.put("prdValue", "");
			for (ProductValue tempPrdVal : prdValList) {
				if (tempPrdVal.getPrdAttribute() == tempstructure.getId()) {
					// 属性对应值
					mapList.put("prdValue", tempPrdVal.getPrdValue());
				}
			}
			// 数据字典取属性验证信息
			if (prdVerifyMap.get((long) tempstructure.getVerify()) != null) {
				String str = "";
				if (tempstructure.getVerify() == 1) {
					str = String.valueOf(tempstructure.getVerify() + "#" + "1");
				} else {
					str = prdVerifyMap.get((long) tempstructure.getVerify()).getName() + "#" + prdVerifyMap.get((long) tempstructure.getVerify()).getDescription();
				}
				mapList.put("verify", str);
			} else {
				mapList.put("verify", "1#1");
			}
			list.add(mapList);
		}
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("allList", list);
		// list长度用于循环产生属性值
		map.put("listSize", list.size());
		return map;
	}

	/**
	 * 
	 * 根据产品ID获取当前值表中对应值
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-5-22 上午10:22:09 LastModified:
	 *          History: </pre>
	 */
	@SuppressWarnings("unchecked")
	public List<ProductValue> getPrdValList(ProductValue entity) throws Exception {
		return getCommonDao().selectList("productvalue.selectByPrdId", entity);
	}

	/**
	 * 属性查询——属性在产品值表中是否有值
	 */
	public boolean isUseProductType(Product entity) throws Exception {
		Long i = (Long) super.getCommonDao().selectObject("product.selectPrdTypeCount", entity);
		if (i == 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 按条件查询总记录数 by haoyuanfu
	 */
	@Override
	public Long getRecordCount(Product entity) throws Exception {

		return (Long) super.getCommonDao().selectObject("product.selectCount", entity);
	}

	/**
	 * 产品保存
	 */

	public boolean saveProduct(Product entity, HttpServletRequest request) throws Exception {
		entity.setDelFlag(0);
		if (entity.getPrdDescribe().equals("")) {
			entity.setPrdDescribe(null);
		}
		// 插入主表值
		getCommonDao().insert("product.insert", entity);
		// 获取刚插入的产品ID
		Long prdId = entity.getGeneratedKey();
		try {
			if (entity.getCrmPrdId() != null) {
				CRMProduct crmp = new CRMProduct();
				crmp.setSerialNo(entity.getCrmPrdId().intValue());
				crmp = selectCRMBySerialNo(crmp);
				// 更新产品上架状态
				updateCrmPrd(crmp);
			}
			// 获取产品相关PDF字符串(Ipad)
			String pdfFileNameIpad = entity.getPdfFileIPad();
			if (pdfFileNameIpad != null && !pdfFileNameIpad.equals("")) {
				String[] pdfArray = pdfFileNameIpad.split("@");
				for (int j = 0; j < pdfArray.length; j++) {
					String[] pdfUrlAndName = pdfArray[j].split("#");
					ProductPdf pp = new ProductPdf();
					pp.setPrdId(prdId);
					pp.setiPadFileName(pdfUrlAndName[0]);
					pp.setiPadUrl(pdfUrlAndName[1]);
					getCommonDao().insert("productpdf.insert", pp);
				}
			}
			// 获取产品相关PDF字符串(Iphone)
			String pdfFileNameIphone = entity.getPdfFileIPhone();
			if (pdfFileNameIphone != null && !pdfFileNameIphone.equals("")) {
				String[] pdfArray = pdfFileNameIphone.split("@");
				for (int j = 0; j < pdfArray.length; j++) {
					String[] pdfUrlAndName = pdfArray[j].split("#");
					ProductPdf pp = new ProductPdf();
					pp.setPrdId(prdId);
					pp.setiPhoneFileName(pdfUrlAndName[0]);
					pp.setiPhoneUrl(pdfUrlAndName[1]);
					getCommonDao().insert("productpdf.insert", pp);
				}
			}

			if (entity.getAttributeAndValue() != null && !entity.getAttributeAndValue().equals("")) {
				// 获取产品属性字符串
				String attributeString = entity.getAttributeAndValue();
				String[] attrArray = attributeString.split("@");
				for (int i = 0; i < attrArray.length; i++) {
					String[] valueArray = attrArray[i].split("#");
					if (valueArray.length >= 2) {
						ProductValue pv = new ProductValue();
						pv.setPrdId(prdId);
						pv.setPrdAttribute(Long.parseLong(valueArray[0]));
						pv.setPrdValue(valueArray[1]);
						getCommonDao().insert("productvalue.insert", pv);
					}
				}
			}
			return true;
		} catch (Exception e) {
			// 保存失败时删除产品主表数据
			entity.setPrdId(prdId);
			super.getCommonDao().delete("product.delete", entity);
			// 保存失败时删除PDF中上传的PDF文件路径
			ProductPdf pp = new ProductPdf();
			pp.setPrdId(prdId);
			super.getCommonDao().delete("productpdf.delete", pp);
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 按条件查询一条产品信息
	 */
	@SuppressWarnings("unchecked")
	public Product getOneByOther(Product entity) throws Exception {
		if (entity.getPrdType() == null)
			return null;
		List<Product> list = getCommonDao().selectList("product.selectByOther", entity);
		return list.get(0);
	}

	/**
	 * 更新产品
	 */
	@Override
	public void update(Product entity) throws Exception {
		// 根据财汇编号更新财汇数据
		updateTrustCode(entity.getFinchinaSymbol(), entity.getCrmPrdId());
		// 更新产品信息
		getCommonDao().update("product.update", entity);
		// 获取产品相关PDF字符串Ipad
		String pdfFileNameIpad = entity.getPdfFileIPad();
		if (pdfFileNameIpad != null && !pdfFileNameIpad.equals("")) {
			String[] pdfArray = pdfFileNameIpad.split("@");
			for (int j = 0; j < pdfArray.length; j++) {
				String[] pdfUrlAndName = pdfArray[j].split("#");
				ProductPdf pp = new ProductPdf();
				pp.setPrdId(entity.getPrdId());
				pp.setiPadFileName(pdfUrlAndName[0]);
				pp.setiPadUrl(pdfUrlAndName[1]);
				getCommonDao().insert("productpdf.insert", pp);
			}
		}
		// 获取产品相关PDF字符串Iphone
		String pdfFileNameIphone = entity.getPdfFileIPhone();
		if (pdfFileNameIphone != null && !pdfFileNameIphone.equals("")) {
			String[] pdfArray = pdfFileNameIphone.split("@");
			for (int j = 0; j < pdfArray.length; j++) {
				String[] pdfUrlAndName = pdfArray[j].split("#");
				ProductPdf pp = new ProductPdf();
				pp.setPrdId(entity.getPrdId());
				pp.setiPhoneFileName(pdfUrlAndName[0]);
				pp.setiPhoneUrl(pdfUrlAndName[1]);
				getCommonDao().insert("productpdf.insert", pp);
			}
		}
		if (entity.getAttributeAndValue() != null && !entity.getAttributeAndValue().equals("")) {
			String attributeString = entity.getAttributeAndValue();
			String[] attrArray = attributeString.split("@");
			for (int i = 0; i < attrArray.length; i++) {
				String[] valueArray = attrArray[i].split("#");
				ProductValue pv = new ProductValue();
				pv.setPrdId(entity.getPrdId());
				pv.setPrdAttribute(Long.parseLong(valueArray[0]));
				Long pvCount = (Long) super.getCommonDao().selectObject("productvalue.selectCount", pv);
				// 如果当前属性有值
				if (valueArray.length >= 2) {
					pv.setPrdValue(valueArray[1]);
					if (pvCount == 0) {
						getCommonDao().insert("productvalue.insert", pv);
					} else {
						getCommonDao().update("productvalue.update", pv);
					}
				} else {
					if (pvCount != 0) {
						getCommonDao().delete("productvalue.delete", pv);
					}
				}
			}
		}
	}

	/**
	 * 列表信息——产品启用
	 */
	public void start(Product entity) throws Exception {
		Product paramProduct = new Product();
		if (entity != null && entity.getIds() != null) {
			for (String id : entity.getIds()) {
				paramProduct.setPrdId(Long.parseLong(id));
				// 设置状态为启用
				paramProduct.setPrdStatus(Integer.parseInt(Constant.PRD_PRODUCTSTATE_START));
				super.getCommonDao().delete("product.update", paramProduct);
			}
		}
		// 单条
		if (entity != null && entity.getId() != null) {
			paramProduct.setPrdId(entity.getId());
			// 设置状态为启用
			paramProduct.setPrdStatus(Integer.parseInt(Constant.PRD_PRODUCTSTATE_START));
			super.getCommonDao().delete("product.update", paramProduct);
		}
	}

	/**
	 * 列表信息——产品停用
	 */
	public void stop(Product entity) throws Exception {
		Product paramProduct = new Product();
		if (entity != null && entity.getIds() != null) {
			for (String id : entity.getIds()) {
				paramProduct.setPrdId(Long.parseLong(id));
				// 设置状态为停用
				paramProduct.setPrdStatus(Integer.parseInt(Constant.PRD_PRODUCTSTATE_STOP));
				super.getCommonDao().delete("product.update", paramProduct);
			}
		}
		// 单条
		if (entity != null && entity.getId() != null) {
			paramProduct.setPrdId(entity.getId());
			// 设置状态为停用
			paramProduct.setPrdStatus(Integer.parseInt(Constant.PRD_PRODUCTSTATE_STOP));
			super.getCommonDao().delete("product.update", paramProduct);
		}
	}

	@Override
	public String upload(BaseEntity baseEntity, HttpServletRequest request) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String realPath = request.getSession().getServletContext().getRealPath("");
		if (realPath == null) {
			try {
				realPath = request.getSession().getServletContext().getResource("/").toString();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		String filePathList = "";
		String nowTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(Calendar.getInstance().getTime());
		String realFileName = nowTime + ".pdf";
		// PDF存储路径
		String pdfPath = SystemProperty.getInstance("parameterConfig").getProperty("pdfPath");
		// 判断文件夹是否存在如果不存在就创建
		FileUtil.createFolder(pdfPath);
		String tempPath = pdfPath;
		Iterator<String> iterator = multipartRequest.getFileNames();
		while (iterator.hasNext()) {
			String fileName = iterator.next().toString();
			File nfile = new File(realPath + File.separator + tempPath, realFileName);
			// 文件全名
			filePathList = pdfPath + realFileName;
			List<MultipartFile> flist = multipartRequest.getFiles(fileName);
			for (MultipartFile mfile : flist) {
				byte[] bytes;
				try {
					bytes = mfile.getBytes();
					if (bytes.length != 0) {
						mfile.transferTo(nfile);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		// 资源库同步标记
		String updateStatus = "0";
		try {
			updateStatus = String.valueOf(synchronizationFile(realFileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 同步服务器url
		String synUploadUrl = SystemProperty.getInstance("parameterConfig").getProperty("uploadUrl");
		return filePathList + "&" + updateStatus + "&" + synUploadUrl;
	}

	/**
	 * 文件上传同步到资源服务器
	 * 
	 * @param pdfName
	 * @return
	 * @throws Exception
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-8-24 上午10:20:09 LastModified:
	 *          History: </pre> 上传成功 = 1
	 */
	public int synchronizationFile(String pdfName) throws Exception {
		// 资源服务器同步状态
		String openStatus = SystemProperty.getInstance("parameterConfig").getProperty("openStatus");
		// 同步上传状态 0=不成功 1=上传成功
		int updateStatus = 0;
		// 同步到资源库
		if (openStatus.equals("open")) {
			String filePath = SystemProperty.getInstance("parameterConfig").getProperty("pdfPath");
			String fileName = pdfName;
			String targetURL = SystemProperty.getInstance("parameterConfig").getProperty("appswsUploadURL");
			updateStatus = HttpClientUtil.receiveFile(filePath, fileName, targetURL + "file");
		}
		return updateStatus;
	}

	public Map<String, Object> getProductSort() {
		return productSort;
	}

	public void setProductSort(Map<String, Object> productSort) {
		this.productSort = productSort;
	}

	/**
	 * 根据产品类型查找符合的查询条件
	 * 
	 * @param entity
	 * @return
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-5-22 下午06:37:49 LastModified:
	 *          History: </pre>
	 */
	public Map<String, Object> getSelectByArrt(Product entity) {
		List<Object> list = new ArrayList<Object>();
		List<Structure> StructureList = null;
		Structure structure = new Structure();
		structure.setProductTypeId(entity.getPrdType());
		try {
			// 查询当前产品属性在属性表中查询对应属性列表
			StructureList = structureService.getList(structure);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 构建属性List拼装成json
		for (Structure tempstructure : StructureList) {
			Map<String, Object> mapList = new Hashtable<String, Object>();
			if (tempstructure.getIsSreach() == 1) {
				// 属性ID
				mapList.put("id", tempstructure.getId());
				// 中文名称
				mapList.put("chineseName", tempstructure.getChineseName());
				// 显示类型（用于生成填入框）
				mapList.put("showType", tempstructure.getShowType());
				// 是否必填
				mapList.put("isRequired", tempstructure.getIsRequired());
				// 是否启用
				mapList.put("isEnabled", tempstructure.getIsEnabled());
				// 下拉框、多选框、单选框的值
				mapList.put("checkValue", tempstructure.getValue());
				list.add(mapList);
			}
		}
		Map<String, Object> map = new Hashtable<String, Object>();
		map.put("StructureList", list);
		// list长度用于循环产生属性值
		map.put("listSize", list.size());
		return map;
	}

	/**
	 * 查询CRM产品表
	 * 
	 * @param entity
	 * @return
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-5-25 下午04:16:08 LastModified:
	 *          History: </pre>
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getCrmGrid(CRMProduct entity) {
		List<Object> list = new ArrayList<Object>();
		Map<String, Object> map = new Hashtable<String, Object>();
		// 数据字典区CRM产品上架状态
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.PRD_TYPE_CRMADDEDFLAG); // CRM产品上架状态
		Map<Long, Dictionary> crmAddedFlagMap = dictionaryService.getDictMap(dictionary);
		List<CRMProduct> resultList = getCommonDao().selectList("crmproduct.select", entity);
		for (CRMProduct crmPrd : resultList) {
			Map<String, Object> mapCrmPrd = new Hashtable<String, Object>();
			List<Object> celllist = new ArrayList<Object>();
			// CRM产品名称
			celllist.add(crmPrd.getProductName());
			// CRM产品序号
			celllist.add(crmPrd.getSerialNo());
			// CRM产品类别
			celllist.add(crmPrd.getProductType());
			// CRM销售状态
			celllist.add(crmPrd.getPurchasePhase());
			// CRM购买起点
			celllist.add(crmPrd.getPurchaseStartPoint());
			// CRM产品年化收益
			celllist.add(crmPrd.getProductIncome());
			// CRM产品期限
			celllist.add(crmPrd.getProductTerm());
			// 财汇编号
			celllist.add(crmPrd.getPad7());
			// 数据字典取CRM上架状态
			if (crmAddedFlagMap.get((long) crmPrd.getAddedFlag()) != null) {
				celllist.add(crmAddedFlagMap.get((long) crmPrd.getAddedFlag()).getName());
			} else {
				celllist.add("");
			}
			// 产品上架状态
			celllist.add(crmPrd.getAddedFlag());
			// CRM产品更新状态
			celllist.add(crmPrd.getUpdFlag());
			mapCrmPrd.put("id", crmPrd.getId());
			mapCrmPrd.put("cell", celllist);
			list.add(mapCrmPrd);
		}
		// 记录数
		long record = 0;
		record = getCRMRecordCount(entity);
		// 页数
		int pageCount = (int) Math.ceil(record / (double) entity.getRows());
		map.put("rows", list);
		map.put("page", entity.getPage());
		map.put("total", pageCount);
		map.put("records", record);
		return map;
	}

	/**
	 * 获取CRM列表记录数
	 * 
	 * @param entity
	 * @return
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-5-28 下午02:13:10 LastModified:
	 *          History: </pre>
	 */
	public Long getCRMRecordCount(CRMProduct entity) {
		return (Long) super.getCommonDao().selectObject("crmproduct.selectCount", entity);
	}

	/**
	 * 按条件查询一条CRM产品信息
	 */
	public CRMProduct getCrmPrdById(CRMProduct entity) {
		if (entity.getId() == null)
			return null;
		return (CRMProduct) getCommonDao().selectObject("crmproduct.selectCRMByOne", entity);
	}

	/**
	 * CRM产品拉取（打开上架页面）
	 * 
	 * @param entity
	 * @return
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-5-28 下午08:10:20 LastModified:
	 *          History: </pre>
	 */
	public Map<String, Object> getCrmPrd(Product entity) {
		Map<String, Object> map = new Hashtable<String, Object>();
		// 加载数据字典中的验证规则
		Dictionary dictionary = new Dictionary();
		dictionary.setDictSortValue(Constant.DICT_TYPE_PRODUCTSTRUCTUREVERIFY); // 属性验证规则
		Map<Long, Dictionary> prdVerifyMap = dictionaryService.getDictMap(dictionary);
		if (entity.getPrdType() != null && !entity.getPrdType().equals("")) {
			List<Object> list = new ArrayList<Object>();
			// 创建一个CRM产品用于查询上架产品
			CRMProduct crmp = new CRMProduct();
			// 根据数据库记录表中的CRMid查询
			crmp.setId(entity.getSysCrmId());
			// 根据当前CRM产品类型获取类型值
			crmp = getCrmPrdById(crmp);
			// 将取出来的对象封装成json对象
			JSONObject jsonObject = JSONObject.fromObject(crmp);
			// 查询当前产品属性在属性表中查询对应属性列表
			List<Structure> structureList = null;
			Structure structure = new Structure();
			// 取出对应产品ID的附加属性值
			try {
				// 获取当前产品类型
				structure.setProductTypeId(entity.getPrdType());
				// 当前类型属性list
				structureList = structureService.getListByOrder(structure);
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Structure tempstructure : structureList) {
				Map<String, Object> mapList = new Hashtable<String, Object>();
				// 属性ID
				mapList.put("id", tempstructure.getId());
				// 中文名称
				mapList.put("chineseName", tempstructure.getChineseName());
				// 显示类型（用于生成填入框）
				mapList.put("showType", tempstructure.getShowType());
				// 是否必填
				mapList.put("isRequired", tempstructure.getIsRequired());
				// 是否启用
				mapList.put("isEnabled", tempstructure.getIsEnabled());
				// 下拉框、多选框、单选框的值
				if (tempstructure.getValue() != null) {
					mapList.put("checkValue", tempstructure.getValue());
				} else {
					mapList.put("checkValue", "");
				}
				mapList.put("prdValue", "");
				// 取当前属性表中存在的属性名称
				String name = tempstructure.getEnglishName();
				if (jsonObject.get(name) != null) {
					mapList.put("prdValue", jsonObject.get(name));
				}
				// 数据字典取属性验证信息
				if (prdVerifyMap.get((long) tempstructure.getVerify()) != null) {
					String str = "";
					if (tempstructure.getVerify() == 1) {
						str = String.valueOf(tempstructure.getVerify() + "#" + "1");
					} else {
						str = prdVerifyMap.get((long) tempstructure.getVerify()).getName() + "#" + prdVerifyMap.get((long) tempstructure.getVerify()).getDescription();
					}
					mapList.put("verify", str);
				} else {
					mapList.put("verify", "1#1");
				}
				list.add(mapList);
			}
			map.put("allList", list);
			// list长度用于循环产生属性值
			map.put("listSize", list.size());
			map.put("typeNo", "true");
		} else {
			map.put("typeNo", "false");
		}
		return map;
	}

	/**
	 * CRM产品上架（批量）
	 * 
	 * @param entity
	 * @throws Exception
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-5-29 上午09:07:38 LastModified:
	 *          History: </pre>
	 */
	public String addedByArray(CRMProduct entity) throws Exception {
		// 获取批量上架的IDS
		String[] crmPrdIds = entity.getIds();
		String prdNames = "";
		if (crmPrdIds.length != 0) {
			// 循环Ids，按类型逐条插入
			for (int i = 0; i < crmPrdIds.length; i++) {
				// CRM产品对象
				CRMProduct cPrd = new CRMProduct();
				// 取IDS中产品类型
				cPrd.setId(Long.parseLong(crmPrdIds[i]));
				cPrd = getCrmPrdById(cPrd);
				if (cPrd.getAddedFlag() == 0) {
					// 本系统中产品类别集合
					List<ProductSort> productTypeList = (List<ProductSort>) PluginsConfigCache.getAllProductSortCache();
					for (ProductSort ps : productTypeList) {
						if (ps.getName().equals(cPrd.getProductType())) {
							// 查询当前产品属性在属性表中查询对应属性列表
							List<Structure> structureList = null;
							// 产品属性对象
							Structure structure = new Structure();
							// 按ID获取CRM产品,并将cPrd重新赋值
							cPrd = getCrmPrdById(cPrd);
							// 将CRM产品对象转换为JSON对象
							JSONObject jsonCrmPrd = JSONObject.fromObject(cPrd);
							Product prd = new Product();
							// 产品类型
							prd.setPrdType(Long.parseLong(ps.getValue()));
							// 设置产品名称
							prd.setPrdName(cPrd.getProductName());
							prdNames += "“" + cPrd.getProductName() + "”  ";
							// 设置财汇数据库编号
							prd.setFinchinaSymbol(cPrd.getPad7());
							// 设置产品结束时间
							prd.setProductFinishTime(cPrd.getProductFinishTime());
							// 设置删除状态
							prd.setDelFlag(0);
							// 设置产品来源为CRM
							prd.setPrdFrom(2);
							// 设置CRMID
							prd.setCrmPrdId((long) cPrd.getSerialNo());
							// 设置产品状态为未发布
							prd.setPrdStatus(2);
							// 插入到产品主表
							getCommonDao().insert("product.insert", prd);
							// 更新产品上架状态
							updateCrmPrd(cPrd);
							// 插入后获取产品ID
							Long prdId = getOneByOther(prd).getPrdId();
							// 按照产品类型查找对应属性值
							structure.setProductTypeId(prd.getPrdType());
							// 查询当前产品属性在属性表中查询对应属性列表
							structureList = structureService.getList(structure);
							for (Structure tempstructure : structureList) {
								// 新建一个产品值对象
								ProductValue pv = new ProductValue();
								// 设置产品ID
								pv.setPrdId(prdId);
								// 设置产品属性值
								pv.setPrdAttribute(tempstructure.getId());
								// 设置产品值
								String name = tempstructure.getEnglishName();
								if (jsonCrmPrd.get(name) != null) {
									pv.setPrdValue(jsonCrmPrd.get(name).toString());
									// 产品属性插入到产品值表
									getCommonDao().insert("productvalue.insert", pv);
								}
							}
						}
					}
				}
			}
		}
		return prdNames;
	}

	/**
	 * 通过WebSerivce拉取CRM产品
	 * 
	 * @param entity
	 * @throws Exception
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-5-29 下午01:13:16 LastModified:
	 *          History: </pre>
	 */
	public void pullingCrmProduct(CRMProduct entity)  {
		try{
		WebserviceConfig wsc = WebserviceConfigCache.getCache("crmProductWebService");
		// 获取系统文件配置的URL
		URL url = new URL(wsc.getValue());
		// 调用WebService接口
		CRMProductWebServiceServiceLocator cwssl = new CRMProductWebServiceServiceLocator();
		// 获取当前系统中CRM产品最大ID
		Long serialNo = getLastCRMSerialNo(entity);
		// 执行WebService获取大于当前产品库最大serialNo的产品Array
		serialNo = 380l;
		System.out.println(url);
		CrmProduct[] crmProductArray = cwssl.getCRMProductWebServicePort(url).findProductGtID(serialNo);
		System.out.println("*********************************");
		if (crmProductArray != null) {
			for (int i = 0; i < crmProductArray.length; i++) {
				try {
					CRMProduct cp = new CRMProduct();
					// 获取序号(Long转换成int)
					cp.setSerialNo(crmProductArray[i].getId().intValue());
					// 获取产品名称 % 私募产品名称
					if (crmProductArray[i].getProductName() != null && !crmProductArray[i].getProductName().equals("")) {
						cp.setProductName(crmProductArray[i].getProductName());
					} else if (crmProductArray[i].getFcTrust() != null && crmProductArray[i].getFcTrust() != null && crmProductArray[i].getFcTrust().getTrustName() != null && !crmProductArray[i].getFcTrust().getTrustName().equals("")) {
						cp.setProductName(crmProductArray[i].getFcTrust().getTrustName());
					} else {
						cp.setProductName("");
					}
					// 获取产品提供方
					cp.setSource(crmProductArray[i].getSource());
					// 产品类型
					cp.setProductType(crmProductArray[i].getProductType());
					// 产品信息格式定义(XML格式)
					// 产品信息格式定义类型
					cp.setProductDefType(crmProductArray[i].getProductDefType());
					// 产品特点
					cp.setProductDesc(crmProductArray[i].getProductDesc());
					// 产品年化收益 % Trust24
					if (crmProductArray[i].getProductIncome() != null && !crmProductArray[i].getProductIncome().equals("")) {
						cp.setProductIncome(crmProductArray[i].getProductIncome());
					} else if (crmProductArray[i].getFcTrust() != null && crmProductArray[i].getFcTrust().getTrust24() != null && !crmProductArray[i].getFcTrust().getTrust24().equals("")) {
						cp.setProductIncome(crmProductArray[i].getFcTrust().getTrust24().toString());
					} else {
						cp.setProductIncome("");
					}
					// 上线时间
					cp.setCreateTimeCRM(calendarToString(crmProductArray[i].getCreateTime()));
					// 成立时间(转成String) % 私募成立日
					if (crmProductArray[i].getProductFoundTime() != null && !crmProductArray[i].getProductFoundTime().equals("")) {
						cp.setProductFoundTime(calendarToString(crmProductArray[i].getProductFoundTime()));
					} else if (crmProductArray[i].getFcTrust() != null && crmProductArray[i].getFcTrust().getTrust5() != null && !crmProductArray[i].getFcTrust().getTrust5().equals("")) {
						cp.setProductFoundTime(crmProductArray[i].getFcTrust().getTrust5());
					} else {
						cp.setProductFoundTime(null);
					}
					// 销售状态
					cp.setPurchasePhase(crmProductArray[i].getPurchasePhase());
					// 购买起点，单位万 % Trust19()
					if (crmProductArray[i].getPurchaseStartPoint() != null && !crmProductArray[i].getPurchaseStartPoint().equals("")) {
						cp.setPurchaseStartPoint(crmProductArray[i].getPurchaseStartPoint());
					} else if (crmProductArray[i].getFcTrust() != null && crmProductArray[i].getFcTrust().getTrust19() != null && !crmProductArray[i].getFcTrust().getTrust19().equals("")) {
						cp.setPurchaseStartPoint(crmProductArray[i].getFcTrust().getTrust19());
					} else {
						cp.setPurchaseStartPoint(null);
					}
					// 销售开始时间(转成String) % Trust2()
					if (crmProductArray[i].getSaleStartTime() != null && !crmProductArray[i].getSaleStartTime().equals("")) {
						cp.setSaleStartTime(calendarToString(crmProductArray[i].getSaleStartTime()));
					} else if (crmProductArray[i].getFcTrust() != null && crmProductArray[i].getFcTrust().getTrust2() != null && !crmProductArray[i].getFcTrust().getTrust2().equals("")) {
						cp.setSaleStartTime(crmProductArray[i].getFcTrust().getTrust2());
					} else {
						cp.setSaleStartTime(null);
					}
					// 销售结束时间(转成String) % Trust3()
					if (crmProductArray[i].getSaleEndTime() != null && !crmProductArray[i].getSaleEndTime().equals("")) {
						cp.setSaleEndTime(calendarToString(crmProductArray[i].getSaleEndTime()));
					} else if (crmProductArray[i].getFcTrust() != null && crmProductArray[i].getFcTrust().getTrust3() != null && !crmProductArray[i].getFcTrust().getTrust3().equals("")) {
						cp.setSaleEndTime(crmProductArray[i].getFcTrust().getTrust3());
					} else {
						cp.setSaleEndTime(null);
					}
					// 佣金比例，单位
					cp.setCommisionRate(crmProductArray[i].getCommisionRate());
					// 阶梯佣金比例
					cp.setLadderCommisionRate(crmProductArray[i].getLadderCommisionRate());
					// 实际佣金比例
					cp.setFactCommisionRate(crmProductArray[i].getFactCommisionRate());
					// 产品期限，单位：月 % 私募存续期
					if (crmProductArray[i].getProductTerm() != null && !crmProductArray[i].getProductTerm().equals("")) {
						cp.setProductTerm(crmProductArray[i].getProductTerm());
					} else if (crmProductArray[i].getFcTrust() != null && crmProductArray[i].getFcTrust().getTrust4() != null && crmProductArray[i].getFcTrust().getTrust4().equals("")) {
						cp.setProductTerm(Math.round(crmProductArray[i].getFcTrust().getTrust4() * 12));
					} else {
						cp.setProductTerm(null);
					}
					// 产品结束时间(转成String) % Trust6()
					if (crmProductArray[i].getProductFinishTime() != null && !crmProductArray[i].getProductFinishTime().equals("")) {
						cp.setProductFinishTime(calendarToString(crmProductArray[i].getProductFinishTime()));
					} else if (crmProductArray[i].getFcTrust() != null && crmProductArray[i].getFcTrust().getTrust6() != null && !crmProductArray[i].getFcTrust().getTrust6().equals("")) {
						cp.setProductFinishTime(crmProductArray[i].getFcTrust().getTrust6());
					} else {
						cp.setProductFinishTime(null);
					}
					// 风险收益特征
					cp.setVentureCharacter(crmProductArray[i].getVentureCharacter());
					// 研究部建议
					cp.setProductAdvice(crmProductArray[i].getProductAdvice());
					// 产品状态
					cp.setProductStatus(crmProductArray[i].getProductStatus());
					// 合同文件名
					cp.setPad1(crmProductArray[i].getPad1());
					// 收益分配方式
					cp.setPad2(crmProductArray[i].getPad2());
					// 备用字段3
					cp.setPad3(crmProductArray[i].getPad3());
					// 分配日期
					cp.setPad4(crmProductArray[i].getPad4());
					// 备用字段5
					cp.setPad5(crmProductArray[i].getPad5());
					// 私募开放日 % 私募开放日
					if (crmProductArray[i].getPad6() != null && !crmProductArray[i].getPad6().equals("")) {
						cp.setPad6(crmProductArray[i].getPad6());
					} else if (crmProductArray[i].getFcTrust() != null && crmProductArray[i].getFcTrust().getTrust29() != null && !crmProductArray[i].getFcTrust().getTrust29().equals("")) {
						cp.setPad6(crmProductArray[i].getFcTrust().getTrust29());
					} else {
						cp.setPad6("");
					}
					// 财汇产品代码
					cp.setPad7(crmProductArray[i].getPad7());
					// 产品简称 % trustAName
					if (crmProductArray[i].getPad8() != null && !crmProductArray[i].getPad8().equals("")) {
						cp.setPad8(crmProductArray[i].getPad8());
					} else if (crmProductArray[i].getFcTrust() != null && crmProductArray[i].getFcTrust().getTrustAName() != null && !crmProductArray[i].getFcTrust().getTrustAName().equals("")) {
						cp.setPad8(crmProductArray[i].getFcTrust().getTrustAName());
					} else {
						cp.setPad8(null);
					}
					// 产品同步判断标识
					cp.setPad9(crmProductArray[i].getPad9());
					// 备用字段10
					cp.setPad10(crmProductArray[i].getPad10());
					// 上架标记 0=未上架、1=已上架
					long crmPrdId = (Long) super.getCommonDao().selectObject("crmproduct.selectCountCrmID", cp);
					if (crmPrdId != 0) {
						cp.setAddedFlag(1);
					} else {
						cp.setAddedFlag(0);
					}
					// 更改标记 0=正常、1=更新
					cp.setUpdFlag(0);
					getCommonDao().insert("crmproduct.insert", cp);

					// 插入到财汇数据库
					if (crmProductArray[i].getFcTrust() != null && crmProductArray[i].getFcTrust().getTrustId() != null) {
						TrustProduct trustProduct = new TrustProduct();
						trustProduct.setTrustName(crmProductArray[i].getFcTrust().getTrustName());
						trustProduct.setTrustCode(crmProductArray[i].getFcTrust().getTrustCode());
						trustProduct.setTrustAName(crmProductArray[i].getFcTrust().getTrustAName());
						trustProduct.setSspell(crmProductArray[i].getFcTrust().getSspell());
						trustProduct.setDeclareDate(crmProductArray[i].getFcTrust().getDeclareDate());
						trustProduct.setUpdateDate(crmProductArray[i].getFcTrust().getUpdateDate());
						trustProduct.setTrust22(crmProductArray[i].getFcTrust().getTrust22());
						trustProduct.setTrust31(crmProductArray[i].getFcTrust().getTrust31());
						trustProduct.setTrust28(crmProductArray[i].getFcTrust().getTrust28());
						trustProduct.setType(crmProductArray[i].getFcTrust().getType());
						trustProduct.setTrust30(crmProductArray[i].getFcTrust().getTrust30());
						trustProduct.setIsstat(crmProductArray[i].getFcTrust().getIsstat());
						trustProduct.setTrust16(crmProductArray[i].getFcTrust().getTrust16());
						trustProduct.setTrust17(crmProductArray[i].getFcTrust().getTrust17());
						trustProduct.setTrust32(crmProductArray[i].getFcTrust().getTrust32());
						trustProduct.setTrust1(crmProductArray[i].getFcTrust().getTrust1());
						trustProduct.setTrust23(crmProductArray[i].getFcTrust().getTrust23());
						trustProduct.setTrust2(crmProductArray[i].getFcTrust().getTrust2());
						trustProduct.setTrust3(crmProductArray[i].getFcTrust().getTrust3());
						trustProduct.setTrust27(crmProductArray[i].getFcTrust().getTrust27());
						trustProduct.setTrust4(crmProductArray[i].getFcTrust().getTrust4());
						trustProduct.setTrust29(crmProductArray[i].getFcTrust().getTrust29());
						trustProduct.setTrust34(crmProductArray[i].getFcTrust().getTrust34());
						trustProduct.setTrust5(crmProductArray[i].getFcTrust().getTrust5());
						trustProduct.setTrust6(crmProductArray[i].getFcTrust().getTrust6());
						trustProduct.setTrust26(crmProductArray[i].getFcTrust().getTrust26());
						trustProduct.setTrust38(crmProductArray[i].getFcTrust().getTrust38());
						trustProduct.setTrust7(crmProductArray[i].getFcTrust().getTrust7());
						trustProduct.setTrust41(crmProductArray[i].getFcTrust().getTrust41());
						trustProduct.setTrust9(crmProductArray[i].getFcTrust().getTrust9());
						trustProduct.setTrust8(crmProductArray[i].getFcTrust().getTrust8());
						trustProduct.setTrust18(crmProductArray[i].getFcTrust().getTrust18());
						trustProduct.setTrust24(crmProductArray[i].getFcTrust().getTrust24());
						trustProduct.setTrust37(crmProductArray[i].getFcTrust().getTrust37());
						trustProduct.setTrust25(crmProductArray[i].getFcTrust().getTrust25());
						trustProduct.setTrust10(crmProductArray[i].getFcTrust().getTrust10());
						trustProduct.setTrust11(crmProductArray[i].getFcTrust().getTrust11());
						trustProduct.setTrust12(crmProductArray[i].getFcTrust().getTrust12());
						trustProduct.setTrust21(crmProductArray[i].getFcTrust().getTrust21());
						trustProduct.setTrust19(crmProductArray[i].getFcTrust().getTrust19());
						trustProduct.setTrust13(crmProductArray[i].getFcTrust().getTrust13());
						trustProduct.setTrust20(crmProductArray[i].getFcTrust().getTrust20());
						trustProduct.setTrust39(crmProductArray[i].getFcTrust().getTrust39());
						trustProduct.setTrust40(crmProductArray[i].getFcTrust().getTrust40());
						trustProduct.setTrust14(crmProductArray[i].getFcTrust().getTrust14());
						trustProduct.setTrust35(crmProductArray[i].getFcTrust().getTrust35());
						trustProduct.setTrust15(crmProductArray[i].getFcTrust().getTrust15());
						trustProduct.setTrust33(crmProductArray[i].getFcTrust().getTrust33());
						trustProduct.setTrust36(crmProductArray[i].getFcTrust().getTrust36());
						trustProduct.setMemo(crmProductArray[i].getFcTrust().getMemo());
						trustProduct.setDataSource(crmProductArray[i].getFcTrust().getDataSource());
						trustProduct.setTmstamp(crmProductArray[i].getFcTrust().getTmstamp());
						List<TrustProduct> trustList = getTrustList(trustProduct);
						if (trustList != null && trustList.size() > 0) {
							trustProduct.setCrmId(trustList.get(0).getCrmId() + cp.getSerialNo() + ",");
							getCommonDao().update("trustProduct.update", trustProduct);
						} else {
							trustProduct.setCrmId(cp.getSerialNo() + ",");
							getCommonDao().insert("trustProduct.insert", trustProduct);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Calendar转换成String
	 * 
	 * @param timeValue
	 * @return
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-6-11 上午09:52:03 LastModified:
	 *          History: </pre>
	 */
	public String calendarToString(Calendar timeValue) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if (timeValue == null || timeValue.equals(""))
			return null;
		String dateStr = df.format(timeValue.getTime());
		return dateStr;
	}

	/**
	 * 获取CRM产品表中最后的产品序号serialNo
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-5-30 下午04:56:46 LastModified:
	 *          History: </pre>
	 */
	@SuppressWarnings("unchecked")
	public Long getLastCRMSerialNo(CRMProduct entity) throws Exception {
		List<CRMProduct> list = getCommonDao().selectList("crmproduct.selectLastSerialNo", entity);
		if (list.size() == 0)
			return (long) 0;
		return (long) list.get(0).getSerialNo();
	}

	/**
	 * 删除PDF文件
	 * 
	 * @param productPdf
	 * @return
	 * @throws Exception
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-5-31 下午03:10:07 LastModified:
	 *          History: </pre>
	 */
	public Map<String, Object> fileDel(ProductPdf productPdf, HttpServletRequest request) {
		Map<String, Object> map = new Hashtable<String, Object>();
		// PDF存储路径
		String pdfPath = SystemProperty.getInstance("parameterConfig").getProperty("pdfPath");
		// 资源服务器同步状态
		String openStatus = SystemProperty.getInstance("parameterConfig").getProperty("openStatus");
		if (productPdf.getId() != null) {
			// 删除磁盘中文件
			productPdf = (ProductPdf) getCommonDao().selectObject("productpdf.selectByOne", productPdf);
			if (productPdf.getiPadUrl() != null && !productPdf.getiPadUrl().equals("")) {
				productPdf.setiPadUrl(pdfPath + productPdf.getiPadUrl());
			}
			if (productPdf.getiPhoneUrl() != null && !productPdf.getiPhoneUrl().equals("")) {
				productPdf.setiPadUrl(pdfPath + productPdf.getiPhoneUrl());
			}
			deletePdfFile(productPdf, request);
			if (openStatus.equals("open")) {
				if (productPdf.getiPadUrl() != null && !productPdf.getiPadUrl().equals("")) {
					// 同步到资源库
					String filePath = pdfPath;
					String fileName = productPdf.getiPadUrl();
					String targetURL = SystemProperty.getInstance("parameterConfig").getProperty("appswsUploadURL");
					try {
						HttpClientUtil.deleteFile(filePath, fileName, targetURL + "delete/file");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (productPdf.getiPhoneUrl() != null && !productPdf.getiPhoneUrl().equals("")) {
					// 同步到资源库
					String filePath = pdfPath;
					String fileName = productPdf.getiPhoneUrl();
					String targetURL = SystemProperty.getInstance("parameterConfig").getProperty("appswsUploadURL");
					try {
						HttpClientUtil.deleteFile(filePath, fileName, targetURL + "delete/file");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			// 删除数据库中文件
			getCommonDao().delete("productpdf.delete", productPdf);
			map.put("del", "success");
		} else {
			map.put("del", "fail");
		}
		return map;
	}

	/**
	 * 删除磁盘中PDF文件
	 * 
	 * @param productPdf
	 * @param request
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-6-1 下午04:11:08 LastModified:
	 *          History: </pre>
	 */
	public void deletePdfFile(ProductPdf productPdf, HttpServletRequest request) {
		// PDF存储路径
		String realPath = SystemProperty.getInstance("parameterConfig").getProperty("realPath");
		if (realPath == null) {
			realPath = request.getSession().getServletContext().getRealPath("");
		}
		if (productPdf.getId() != null) {
			File file = new File(realPath + productPdf.getiPadUrl());
			// 路径为文件且不为空则进行删除
			if (file.isFile() && file.exists()) {
				file.delete();
			}
		}
	}

	/**
	 * 删除产品时同时删除PDF文件
	 * 
	 * @param productPdf
	 * @param request
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-6-7 下午03:35:05 LastModified:
	 *          History: </pre>
	 */
	@SuppressWarnings("unchecked")
	public void deletePdfFileByPrdId(ProductPdf productPdf, HttpServletRequest request) {
		String realPath = SystemProperty.getInstance("parameterConfig").getProperty("realPath");
		String pdfPath = SystemProperty.getInstance("parameterConfig").getProperty("pdfPath");
		// 资源服务器同步状态
		String openStatus = SystemProperty.getInstance("parameterConfig").getProperty("openStatus");
		if (productPdf.getPrdId() != null) {
			List<ProductPdf> listPad = getCommonDao().selectList("productpdf.selectByPrdIdIpad", productPdf);
			// 删除IPAD对应文件
			if (listPad != null && listPad.size() > 0) {
				for (ProductPdf entityPad : listPad) {
					if (entityPad.getiPadUrl() != null) {
						File file = new File(realPath + pdfPath + entityPad.getiPadUrl());
						// 路径为文件且不为空则进行删除
						if (file.isFile() && file.exists()) {
							file.delete();
						}
						// 同步到资源库
						if (openStatus.equals("open")) {
							String filePath = pdfPath;
							String fileName = productPdf.getiPadUrl();
							String targetURL = SystemProperty.getInstance("parameterConfig").getProperty("appswsUploadURL");
							try {
								HttpClientUtil.deleteFile(filePath, fileName, targetURL + "delete/file");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			List<ProductPdf> listPhone = getCommonDao().selectList("productpdf.selectByPrdIdIphone", productPdf);
			// 删除IPhone对应文件
			if (listPhone != null && listPhone.size() > 0) {
				for (ProductPdf entityPhone : listPhone) {
					if (entityPhone.getiPhoneUrl() != null) {
						File file = new File(realPath + pdfPath + entityPhone.getiPhoneUrl());
						// 路径为文件且不为空则进行删除
						if (file.isFile() && file.exists()) {
							file.delete();
						}
						// 同步到资源库
						if (openStatus.equals("open")) {
							String filePath = pdfPath;
							String fileName = productPdf.getiPhoneUrl();
							String targetURL = SystemProperty.getInstance("parameterConfig").getProperty("appswsUploadURL");
							try {
								HttpClientUtil.deleteFile(filePath, fileName, targetURL + "delete/file");
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 更新CRM产品上架状态
	 * 
	 * @param entity
	 * @throws Exception
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-6-4 下午03:57:43 LastModified:
	 *          History: </pre>
	 */
	public void updateCrmPrd(CRMProduct entity) throws Exception {
		if (entity.getId() != null) {
			entity.setAddedFlag(1);
			getCommonDao().update("crmproduct.update", entity);
		}
	}

	/**
	 * 根据Crm产品序列号查找产品
	 * 
	 * @param entity
	 * @return
	 * @author haoyuanfu
	 * @version 1.0 </pre> Created on :2012-6-7 下午03:27:29 LastModified:
	 *          History: </pre>
	 */
	public CRMProduct selectCRMBySerialNo(CRMProduct entity) {
		if (entity.getSerialNo() == null)
			return null;
		return (CRMProduct) getCommonDao().selectObject("crmproduct.selectCRMBySerialNo", entity);
	}

	@Override
	public void delete(Product entity) throws Exception {
		super.getCommonDao().delete("product.delete", entity);
	}

	@Override
	public void save(Product entity) throws Exception {
		// TODO Auto-generated method stub

	}

	private Long getTrustCount(TrustProduct entity) throws Exception {
		Object obj = getCommonDao().selectObject("trustProduct.selectCount", entity);
		if (obj instanceof Integer) {
			return (Long) obj;
		} else if (obj instanceof Long) {
			return (Long) obj;
		}
		return 0l;
	}

	public List<TrustProduct> getTrustList(TrustProduct entity) throws Exception {
		return getCommonDao().selectList("trustProduct.selectByTrustCode", entity);
	}

	/**
	 * 列表信息——产品启用
	 */
	public void crmUpdFlag(CRMProduct entity) throws Exception {
		CRMProduct paramProduct = new CRMProduct();
		if (entity != null && entity.getId() != null) {
			paramProduct.setId(entity.getId());
			paramProduct.setUpdFlag(Constant.PRD_PRODUCTUPDFLAG_NORMAL);
			super.getCommonDao().delete("crmproduct.updateUpdFlag", paramProduct);
		}
	}

	/**
	 * 修改产品时更新财汇编号
	 */
	public void updateTrustCode(String trustCode, Long crmId) throws Exception {
		if (trustCode != null && !trustCode.equals("")) {
			TrustProduct tp = new TrustProduct();
			tp.setTrustCode(trustCode);
			if (getTrustCount(tp) == 0) {
				IFCTrustWebService iFCTrustWebService = (new FCTrustWebServiceServiceLocator()).getFCTrustWebServicePort(new URL(WebserviceConfigCache.getCache("fcTrust").getValue()));
				List<String> trustCodesInsert = new ArrayList<String>();
				trustCodesInsert.add(trustCode);
				FcTrust[] fcTrustsInsert = iFCTrustWebService.findFCTrust((String[]) trustCodesInsert.toArray(new String[trustCodesInsert.size()]));
				if (fcTrustsInsert != null && fcTrustsInsert.length > 0) {
					// 查询所有trustCode相关的单位净值数据
					FcTrustNav[] fcTrustNavList = iFCTrustWebService.findFCTrustNav((String[]) trustCodesInsert.toArray(new String[trustCodesInsert.size()]));
					List<FcTrustFQNav> fqNavList = new ArrayList<FcTrustFQNav>();
					// 查询所有trustCode相关的证卷日回报系数表中的数据
					FcTrustFQNav[] fcTrustFQNavList = iFCTrustWebService.findFCTrustFQNav((String[]) trustCodesInsert.toArray(new String[trustCodesInsert.size()]));
					if (fcTrustFQNavList != null && fcTrustFQNavList.length > 0) {
						fqNavList = Arrays.asList(fcTrustFQNavList);
						FcTrust fcTrust = fcTrustsInsert[0];
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
						if (crmId != null && !crmId.toString().equals("")) {
							trustProduct.setCrmId(crmId + ",");
						}
						getCommonDao().insert("trustProduct.insert", trustProduct);
					}
					// 更新pofdata
					if (fcTrustNavList != null && fcTrustNavList.length > 0) {
						List<FcTrustNav> navList = Arrays.asList(fcTrustNavList);
						POFData pofData = CalculationPOFUtil.calculationDetail(trustCode, navList, fqNavList);
						if (pofData != null && pofData.getTrustCode() != null && !pofData.getTrustCode().equals("")) {
							if (this.getRecordCount(pofData) == 0) {
								getCommonDao().save("pofData.insert", pofData);
							} else {
								getCommonDao().update("pofData.update", pofData);
							}
						}
					}
				}
			}

		}
	}

	@SuppressWarnings("unchecked")
	public void synchronousTrustCode() throws Exception {
		// 取cfg_finance_product表中所有财汇编号
		List<CRMProduct> crmProductList = getCommonDao().selectList("crmproduct.selectDifferenceTrustCode");
		if (crmProductList.size() > 0) {
			List<String> trustCodesInsert = new ArrayList<String>();
			Map<String, String> m = new HashMap<String, String>();
			for (int i = 0; i < crmProductList.size(); i++) {
				CRMProduct crmProduct = crmProductList.get(i);
				if (crmProduct != null && crmProduct.getPad7() != null && !crmProduct.getPad7().equals("")) {
					if (trustCodesInsert.contains(crmProduct.getPad7())) {
						String crmIdStr = (String) m.get(crmProduct.getPad7());
						m.put(crmProduct.getPad7(), crmIdStr + crmProduct.getSerialNo() + ",");
					} else {
						trustCodesInsert.add(crmProduct.getPad7());
						m.put(crmProduct.getPad7(), crmProduct.getSerialNo() + ",");
					}
				}
			}
			if (trustCodesInsert.size() > 0) {
				IFCTrustWebService iFCTrustWebService = (new FCTrustWebServiceServiceLocator()).getFCTrustWebServicePort(new URL(WebserviceConfigCache.getCache("fcTrust").getValue()));
				FcTrust[] fcTrustsInsert = iFCTrustWebService.findFCTrust((String[]) trustCodesInsert.toArray(new String[trustCodesInsert.size()]));
				// 查询所有trustCode相关的单位净值数据
				FcTrustNav[] fcTrustNavList = iFCTrustWebService.findFCTrustNav((String[]) trustCodesInsert.toArray(new String[trustCodesInsert.size()]));
				Map<String, List<FcTrustNav>> fcTrustNavMap = new HashMap<String, List<FcTrustNav>>();
				for (int i = 0; i < fcTrustNavList.length; i++) {
					if (fcTrustNavMap.containsKey(fcTrustNavList[i].getTrustCode())) {
						fcTrustNavMap.get(fcTrustNavList[i].getTrustCode()).add(fcTrustNavList[i]);
					} else {
						List<FcTrustNav> tempList = new ArrayList<FcTrustNav>();
						tempList.add(fcTrustNavList[i]);
						fcTrustNavMap.put(fcTrustNavList[i].getTrustCode(), tempList);
					}
				}
				// 查询所有trustCode相关的证卷日回报系数表中的数据
				FcTrustFQNav[] fcTrustFQNavList = iFCTrustWebService.findFCTrustFQNav((String[]) trustCodesInsert.toArray(new String[trustCodesInsert.size()]));
				Map<String, List<FcTrustFQNav>> fcTrustFQNavMap = new HashMap<String, List<FcTrustFQNav>>();
				for (int i = 0; i < fcTrustFQNavList.length; i++) {
					if (fcTrustFQNavMap.containsKey(fcTrustFQNavList[i].getTrustCode())) {
						fcTrustFQNavMap.get(fcTrustFQNavList[i].getTrustCode()).add(fcTrustFQNavList[i]);
					} else {
						List<FcTrustFQNav> tempList = new ArrayList<FcTrustFQNav>();
						tempList.add(fcTrustFQNavList[i]);
						fcTrustFQNavMap.put(fcTrustFQNavList[i].getTrustCode(), tempList);
					}
				}
				for (int i = 0; i < fcTrustsInsert.length; i++) {
					FcTrust fcTrust = fcTrustsInsert[i];
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
					if (m.get(fcTrust.getTrustCode()) != null && !m.get(fcTrust.getTrustCode()).equals("")) {
						trustProduct.setCrmId(m.get(fcTrust.getTrustCode()));
					}
					getCommonDao().insert("trustProduct.insert", trustProduct);
					// 更新pofData
					String trustCode = trustProduct.getTrustCode();
					List<FcTrustNav> navList = fcTrustNavMap.get(trustCode);
					List<FcTrustFQNav> fqNavList = fcTrustFQNavMap.get(trustCode);
					POFData pofData = CalculationPOFUtil.calculationDetail(trustCode, navList, fqNavList);
					if (pofData != null && pofData.getTrustCode() != null && !pofData.getTrustCode().equals("")) {
						if (this.getRecordCount(pofData) == 0) {
							getCommonDao().save("pofData.insert", pofData);
						} else {
							getCommonDao().update("pofData.update", pofData);
						}
					}
				}

			}
		}
	}

	private Long getRecordCount(POFData entity) throws Exception {
		Object obj = getCommonDao().selectObject("pofData.selectCount", entity);
		if (obj instanceof Integer) {
			return (Long) obj;
		} else if (obj instanceof Long) {
			return (Long) obj;
		}
		return 0l;
	}

	/**
	 * 查询产品上架状态
	 */
	public long addedStatus(CRMProduct entity) throws Exception {
		long crmPrdId = (Long) super.getCommonDao().selectObject("crmproduct.selectCountCrmID", entity);
		if (crmPrdId != 0) {
			// 更新产品上架状态
			updateCrmPrd(entity);
		}
		return crmPrdId;
	}

	@SuppressWarnings("unchecked")
	public XSSFWorkbook exportMemberProduct() {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet1 = wb.createSheet("信托产品");
		XSSFSheet sheet2 = wb.createSheet("阳光私募");
		XSSFSheet sheet3 = wb.createSheet("私募股权");
		List<List> list1 = new ArrayList<List>();
		List<List> list2 = new ArrayList<List>();
		List<List> list3 = new ArrayList<List>();
		List titleList = new ArrayList();
		titleList.add("client id");
		titleList.add("产品名称");
		titleList.add("产品类型");
		titleList.add("成交金额（万）");
		titleList.add("购买时间");
		titleList.add("受托人");
		titleList.add("预期年化收益");
		titleList.add("期限（月）");
		titleList.add("预期终止时间");
		list1.add(titleList);
		titleList = new ArrayList();
		titleList.add("client id");
		titleList.add("产品名称");
		titleList.add("产品类型");
		titleList.add("成交金额");
		titleList.add("成交日期");
		titleList.add("购买日单位净值");
		titleList.add("购买份额");
		titleList.add("最新市值");
		titleList.add("收益率");
		titleList.add("最新净值日期");
		titleList.add("最新单位净值");
		titleList.add("管理人");
		titleList.add("基金经理");
		titleList.add("受托人");
		titleList.add("开放日");
		titleList.add("封闭期");
		titleList.add("成立日");
		titleList.add("首次最低认购");
		titleList.add("追加最低认购");
		titleList.add("费率说明");
		titleList.add("期限");
		list2.add(titleList);
		titleList = new ArrayList();
		titleList.add("client id");
		titleList.add("产品名称");
		titleList.add("产品类型");
		titleList.add("成交金额");
		titleList.add("购买日期");
		titleList.add("管理人");
		titleList.add("期限");
		titleList.add("投资起点");
		list3.add(titleList);

		try {
			ICRMTradeRecordProductWebService iCRMTradeRecordProductWebService = (new CRMTradeRecordProductWebServiceServiceLocator()).getCRMTradeRecordProductWebServicePort(new URL(WebserviceConfigCache.getCache("tradeRecordProduct").getValue()));
			IFCTrustWebService iFCTrustWebService = (new FCTrustWebServiceServiceLocator()).getFCTrustWebServicePort(new URL(WebserviceConfigCache.getCache("fcTrust").getValue()));
			CrmTradeRecordProduct[] crmTradeRecordProducts = iCRMTradeRecordProductWebService.findCRMTradeRecordProduct();
			if (crmTradeRecordProducts != null) {
				List<String> productIds = new ArrayList<String>();
				for (CrmTradeRecordProduct crmTradeRecordProduct : crmTradeRecordProducts) {
					if (crmTradeRecordProduct.getProduct() == null || crmTradeRecordProduct.getProduct().equals("")) {
						continue;
					} else {
						if (!productIds.contains(crmTradeRecordProduct.getProduct())) {
							productIds.add(crmTradeRecordProduct.getProduct());
						}
					}
				}
				List<Product> productList = new ArrayList<Product>();
				if (productIds.size() > 0) {
					Product product = new Product();
					product.setIds(productIds.toArray(new String[productIds.size()]));
					productList = this.getCommonDao().selectList("product.selectByIds", product);
				}
				productIds = new ArrayList<String>();
				List<String> symbolList = new ArrayList<String>();
				Map<String, Product> productMap = new HashMap<String, Product>();
				for (Product product : productList) {
					productMap.put(product.getCrmPrdId().toString(), product);
					if (!productIds.contains(product.getPrdId().toString())) {
						productIds.add(product.getPrdId().toString());
					}
					if (product.getFinchinaSymbol() != null && !symbolList.contains(product.getFinchinaSymbol())) {
						symbolList.add(product.getFinchinaSymbol());
					}
				}
				List<POFData> pofDataList = new ArrayList<POFData>();
				if (symbolList.size() > 0) {
					POFData pofData = new POFData();
					pofData.setIds(symbolList.toArray(new String[symbolList.size()]));
					pofDataList = this.getCommonDao().selectList("pofData.selectByTrustCodes", pofData);
				}
				Map<String, POFData> pofDataMap = new HashMap<String, POFData>();
				for (POFData pofData : pofDataList) {
					pofDataMap.put(pofData.getTrustCode(), pofData);
				}
				List<Map<String, String>> attributeList = new ArrayList<Map<String, String>>();
				if (productIds != null && productIds.size() > 0) {
					Product p = new Product();
					p.setIds(productIds.toArray(new String[productIds.size()]));
					attributeList = this.getCommonDao().selectList("product.selectAttributeValueByIds", p);
				}
				Map<String, Map> attributeMap = new HashMap<String, Map>();
				List<String> idFlag = new ArrayList<String>();
				for (Map<String, String> map : attributeList) {
					if (idFlag.contains((String) map.get("prdID"))) {
						attributeMap.get((String) map.get("prdID")).put((String) map.get("prdattribute"), map.get("prdvalue"));
					} else {
						Map m = new HashMap();
						m.put((String) map.get("prdattribute"), map.get("prdvalue"));
						attributeMap.put((String) map.get("prdID"), m);
						idFlag.add((String) map.get("prdID"));
					}
				}
				SystemProperty systemProperty = SystemProperty.getInstance("parameterConfig");
				for (CrmTradeRecordProduct crmTradeRecordProduct : crmTradeRecordProducts) {
					if (crmTradeRecordProduct.getProduct() != null && !crmTradeRecordProduct.getProduct().equals("")) {
						Product product = productMap.get(crmTradeRecordProduct.getProduct());
						if (product != null) {
							List list = new ArrayList();
							if (product.getPrdType() == 1) {
								list.add(crmTradeRecordProduct.getMemberID());
								list.add(product.getPrdName());
								list.add("信托产品PD");
								if (crmTradeRecordProduct.getPurchaseFee() != null) {
									list.add(crmTradeRecordProduct.getPurchaseFee().toString());
								} else {
									list.add("");
								}
								if (crmTradeRecordProduct.getFollowTime() != null) {
									list.add(DateUtil.dateToString(crmTradeRecordProduct.getFollowTime().getTime()));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PD_touziren")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PD_touziren")));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PD_nianshouyi")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PD_nianshouyi")));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PD_qixian")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PD_qixian")));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PD_zhongzhi")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PD_zhongzhi")));
								} else {
									list.add("");
								}
								list1.add(list);
							} else if (product.getPrdType() == 2) {
								list.add(crmTradeRecordProduct.getMemberID());
								list.add(product.getPrdName());
								list.add("阳光私募");
								if (crmTradeRecordProduct.getPurchaseFee() != null) {
									list.add(crmTradeRecordProduct.getPurchaseFee().toString());
								} else {
									list.add("");
								}
								if (crmTradeRecordProduct.getFollowTime() != null) {
									list.add(DateUtil.dateToString(crmTradeRecordProduct.getFollowTime().getTime()));
								} else {
									list.add("");
								}
								float purchaseNav = 0;
								float purchase = 0;
								float marketValue = 0;
								float returnRate = 0;
								String navDate = "";
								String nav = "";
								if (crmTradeRecordProduct.getPurchaseFee() != null && crmTradeRecordProduct.getFollowTime() != null && product.getFinchinaSymbol() != null && product.getFinchinaSymbol().length() > 0) {
									FcTrustNav paramFCTrustNav = new FcTrustNav();
									paramFCTrustNav.setTrustCode(product.getFinchinaSymbol());
									paramFCTrustNav.setNavDate(DateUtil.dateToString(crmTradeRecordProduct.getFollowTime().getTime()));
									FcTrustNav fcTrustNav = iFCTrustWebService.findPurchaseNav(paramFCTrustNav);
									if (fcTrustNav == null) {
										paramFCTrustNav.setQueryPram("1");
										fcTrustNav = iFCTrustWebService.findPurchaseNav(paramFCTrustNav);
									}
									if (fcTrustNav != null && fcTrustNav.getNav() > 0l) {
										purchaseNav = fcTrustNav.getNav();
									}
									if (purchaseNav != 0) {
										purchase = crmTradeRecordProduct.getPurchaseFee() / purchaseNav;
									}
									POFData profit = pofDataMap.get(product.getFinchinaSymbol());
									marketValue = profit != null && profit.getNav() != null ? purchase * profit.getNav() : 0;
									if (purchaseNav != 0) {
										returnRate = profit != null && profit.getNav() != null ? (profit.getNav() / purchaseNav - 1) * 100 : 0;
									}
									navDate = profit != null && profit.getNavDate() != null ? profit.getNavDate().substring(0, 10) : "";
									nav = profit != null && profit.getNav() != null ? String.format("%.4f", profit.getNav()) : "";
								}
								if (purchaseNav != 0) {
									list.add(purchaseNav);
								} else {
									list.add("");
								}
								if (purchase != 0) {
									list.add(String.format("%.4f", purchase) + "万份");
								} else {
									list.add("");
								}
								if (marketValue != 0) {
									list.add(String.format("%.4f", marketValue) + "万");
								} else {
									list.add("");
								}
								if (returnRate != 0) {
									list.add(String.format("%.2f", returnRate) + "%");
								} else {
									list.add("");
								}
								list.add(navDate);
								list.add(nav);
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_guanliren")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_guanliren")));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_jingli")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_jingli")));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_shoutuoren")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_shoutuoren")));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_kaifangri")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_kaifangri")));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_fengbiqi")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_fengbiqi")));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_chengliri")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_chengliri")));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_shoucizuidirengou")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_shoucizuidirengou")));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_zuijiazuidirengou")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_zuijiazuidirengou")));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_feilvshuoming")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_feilvshuoming")));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_qixian")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PS_qixian")));
								} else {
									list.add("");
								}
								list2.add(list);
							} else if (product.getPrdType() == 3) {
								list.add(crmTradeRecordProduct.getMemberID());
								list.add(product.getPrdName());
								list.add("私募股权PE");
								if (crmTradeRecordProduct.getPurchaseFee() != null) {
									list.add(crmTradeRecordProduct.getPurchaseFee().toString());
								} else {
									list.add("");
								}
								if (crmTradeRecordProduct.getFollowTime() != null) {
									list.add(DateUtil.dateToString(crmTradeRecordProduct.getFollowTime().getTime()));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PE_guanliren")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PE_guanliren")));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PE_qixian")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PE_qixian")));
								} else {
									list.add("");
								}
								if (product.getPrdId() != null && attributeMap.get(product.getPrdId().toString()) != null && attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PE_qidian")) != null) {
									list.add((String) attributeMap.get(product.getPrdId().toString()).get(systemProperty.getProperty("PE_qidian")));
								} else {
									list.add("");
								}
								list3.add(list);
							}
						}
					}
				}
			}
			Excel2007WriteHandler excel2007WriteHandler = new Excel2007WriteHandler();
			excel2007WriteHandler.fillData(sheet1, list1);
			excel2007WriteHandler.fillData(sheet2, list2);
			excel2007WriteHandler.fillData(sheet3, list3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wb;
	}

	/**
	 * 更新产品可分享状态
	 * 
	 * @param product
	 * @throws Exception
	 */
	public void updateShared(Product product) throws Exception {
		super.getCommonDao().update("product.update", product);
	}

	/**
	 * 生成产品3G页面
	 * 
	 * @param product
	 * @throws Exception
	 */
	public void generate(Product product, HttpServletRequest request) throws Exception {

		// 查询产品基本信息
		Product prd = (Product) this.getCommonDao().selectObject("product.selectByOne", product);

		if (prd.getPrdId() != null) {
			Structure stc = new Structure();
			// 查询产品属性
			stc.setId(prd.getPrdId());
			// 1:显示在我的产品 2：显示在产品柜台 3：都显示
			stc.setIsShowOnContent(2);
			stc.setIsShowProductCounter(1);
			List<Structure> attributeList = (List<Structure>) structureService.getProductAtrribute(stc);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("productName", prd.getPrdName());
			map.put("productType", productSort.get(prd.getPrdType().toString()));
			map.put("atrribute", attributeList);
			
			String applicationPath = request.getSession().getServletContext().getRealPath("/");
			String templateLocation = applicationPath + "freemarker/";
			String templateName = "product_detail_3g.ftl";
			String generatePath = applicationPath+"upload/";
			String generateFileName = "product/"+product.getPrdId() + ".html";
			// 生成文件
			FreemarkerUtil.analysisTemplate(templateLocation, templateName, generatePath+generateFileName, map);
			// 同步文件
			String host = initialData.getProductRsyncHost();
			String rsynccmd = initialData.getRsyncCmd();
			RsyncUtil.rsyncSingleFile(rsynccmd,generatePath, generateFileName ,host);
		}
	}
	
	/**
	 *  批量上传存续报告
	 *  @author baokai
	 *  Created on :2013-10-24 上午9:58:45
	 */
	public void uploadPDF(Product entity, HttpServletRequest request) throws Exception {
		String pdfFileNameIphone = entity.getPdfFileIPhone();
		pdfFileNameIphone = java.net.URLDecoder.decode(pdfFileNameIphone,"UTF-8"); 
		String[] pdfArray = null;
		if (pdfFileNameIphone != null && !pdfFileNameIphone.equals("")) {
			pdfArray = pdfFileNameIphone.split("@");
		}
		if (entity != null && entity.getIds() != null) {
			for (String id : entity.getIds()) {
				Product p = new Product();
				p.setPrdId(Long.parseLong(id));
				p.setPrdType(Long.parseLong(Constant.PRD_PRODUCTTYPE_SUNSHINEPRIVATEFUNDS));
				if(isSiMu(p)){
					ProductPdf productPdf = new ProductPdf();
					productPdf.setPrdId(Long.parseLong(id));
					if(pdfArray != null && pdfArray.length > 0){
						for (int j = 0; j < pdfArray.length; j++) {
							if(pdfArray[j] != null && !pdfArray[j].equals("")){
								String[] pdfUrlAndName = pdfArray[j].split("#");
								productPdf.setiPhoneFileName(pdfUrlAndName[0]);
								productPdf.setiPhoneUrl(pdfUrlAndName[1]);
//								productPdf.setiPadFileName(pdfUrlAndName[0]);
//								productPdf.setiPadUrl(pdfUrlAndName[1]);
								getCommonDao().insert("productpdf.insert", productPdf);
								productPdf = new ProductPdf();
								productPdf.setPrdId(Long.parseLong(id));
								productPdf.setiPadFileName(pdfUrlAndName[0]);
								productPdf.setiPadUrl(pdfUrlAndName[1]);
								getCommonDao().insert("productpdf.insert", productPdf);
							}
						}
					}
				}
			}
		}
	}
	
	
	/**
	 *  批量删除存续报告
	 *  @author baokai
	 *  Created on :2013-10-24 上午9:59:08
	 */
	public void deletePDF(Product entity, HttpServletRequest request) throws Exception {
		if (entity != null && entity.getIds() != null) {
			for (String id : entity.getIds()) {
				Product p = new Product();
				p.setPrdId(Long.parseLong(id));
				p.setPrdType(Long.parseLong(Constant.PRD_PRODUCTTYPE_SUNSHINEPRIVATEFUNDS));
				if(isSiMu(p)){
					ProductPdf productPdf = new ProductPdf();
					productPdf.setPrdId(Long.parseLong(id));
					getCommonDao().delete("productpdf.deleteiPadPDF", productPdf);
					getCommonDao().delete("productpdf.deleteiPhonePDF", productPdf);
				}
			}
		}
	}
	
	public boolean isSiMu(Product entity) throws Exception {

		return (Long) super.getCommonDao().selectObject("product.selectCountIsSiMu", entity) > 0 ? true : false;
	}
	
}
