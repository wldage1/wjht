package com.sw.plugins.productcenter.product.maintain.controller;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sw.core.common.Constant;
import com.sw.core.common.SystemProperty;
import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.data.entity.ProductSort;
import com.sw.core.initialize.PluginsConfigCache;
import com.sw.core.util.CommonUtil;
import com.sw.plugins.productcenter.attribute.structure.service.StructureService;
import com.sw.plugins.productcenter.product.maintain.entity.CRMProduct;
import com.sw.plugins.productcenter.product.maintain.entity.Product;
import com.sw.plugins.productcenter.product.maintain.entity.ProductPdf;
import com.sw.plugins.productcenter.product.maintain.service.ProductService;
import com.sw.plugins.usercenter.config.dynamic.entity.Dictionary;
import com.sw.plugins.usercenter.config.dynamic.service.DictionaryService;


/**
 *   产品管理，主要应用于产品的增加删除修改列表显示等功能
 *  @author haoyuanfu
 *  @version 1.0
 *  </pre>
 *  Created on :上午09:21:18
 *  LastModified:
 *  History:
 *  </pre>
 */

@Controller  
public class ProductController extends BaseController{  
	
	private static Logger logger = Logger.getLogger(ProductController.class);
	
    @Resource
    private ProductService productService;
    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private StructureService structureService;

    /**
     *  产品管理——产品列表
     *  @param product
     *  @param request
     *  @return
     *  @author haoyuanfu
     *  @version 1.0
     *  </pre>
     *  Created on :2012-5-14 上午10:45:32
     *  LastModified:
     *  History:
     *  </pre>
     */
    @RequestMapping("/productcenter/product/maintain/list")
    public CommonModelAndView list(Product product,HttpServletRequest request){ 

 	   	CommonModelAndView commonModelAndView = new CommonModelAndView(request,product);
 	   	try{
 	   		Dictionary dictionary = new Dictionary();
 	   		//产品状态
	 	    dictionary.setDictSortValue(Constant.PRD_TYPE_PRODUCTSTATUE);
			List<Dictionary> statusList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("statusList", statusList);
			commonModelAndView.addObject("statusStart", Constant.PRD_PRODUCTSTATE_START);
			commonModelAndView.addObject("statusStop", Constant.PRD_PRODUCTSTATE_STOP);
			dictionary = new Dictionary();
			//产品类型下拉框
			dictionary.setDictSortValue(Constant.PRD_TYPE_PRODUCTTYPE);
			List<ProductSort> productTypeList = (List<ProductSort>) PluginsConfigCache.getAllProductSortCache();
			commonModelAndView.addObject("productTypeList", productTypeList);
 	   	}catch(Exception e){
 	   		logger.error(e.getMessage());
 	   	}
 	   	commonModelAndView.addObject("code", product.getC());
 	   	product.setDelFlag(0);
 	   	commonModelAndView.addObject("product", product);
 	   	
        return commonModelAndView;
    }  

	/**
	 *  产品列表——页面列表加载列表显示
	 *  @param product
	 *  @param nodeid
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-14 上午10:49:05
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/grid")
	public CommonModelAndView json(Product product,String nodeid,HttpServletRequest request){
		product.setPrdName(CommonUtil.convertSearchSign(product.getPrdName()));
		Map<String, Object> map = productService.getGrid(product); 
		return new CommonModelAndView("jsonView",map);
	}
    	
	 /**
	 *  跳转到创建产品页面
	 *  @param product
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-14 下午01:16:46
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/create")
	   public CommonModelAndView create(Product product,HttpServletRequest request){
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,product);
		commonModelAndView.addObject("product",product);
		try {
			Dictionary dictionary = new Dictionary();
			//产品类型下拉框
			dictionary.setDictSortValue(Constant.PRD_TYPE_PRODUCTTYPE);
			List<ProductSort> productTypeList = (List<ProductSort>) PluginsConfigCache.getAllProductSortCache();
			commonModelAndView.addObject("productTypeList", productTypeList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		//CommonModelAndView commonModelAndView = new CommonModelAndView(request,product);
	       return commonModelAndView; 
	   }  
	
	/**
	 *  跳转到修改页面
	 *  @param product
	 *  @param request
	 *  @param model
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-15 下午01:01:17
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/modify")
	   public CommonModelAndView modify(Product product,HttpServletRequest request,Map<String,Object> model){
			String code = product.getC();
			if(product.getId()!=null)
				product.setPrdId(product.getId());
			if (product.getPrdId()!= null){
				try {
					product = productService.getOneById(product);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}	
			product.setC(code);
			product.setId(product.getPrdId());
			// 同步服务器url
			String synURL = SystemProperty.getInstance("parameterConfig").getProperty("uploadUrl") ;
			//文件存放路径
			String filePath = SystemProperty.getInstance("parameterConfig").getProperty("pdfPath") ;
			product.setFilePath(filePath);
			product.setSynURL(synURL);
			CommonModelAndView commonModelAndView = new CommonModelAndView(request,product);
			try {
				Dictionary dictionary = new Dictionary();
				//产品类型下拉框
				dictionary.setDictSortValue(Constant.PRD_TYPE_PRODUCTTYPE);  
				List<ProductSort> productTypeList = (List<ProductSort>) PluginsConfigCache.getAllProductSortCache();
				commonModelAndView.addObject("productTypeList", productTypeList);
				//启用状态
				dictionary.setDictSortValue(Constant.PRD_TYPE_PRODUCTSTATUE);
				List<Dictionary> productStatueList = dictionaryService.getList(dictionary);
				commonModelAndView.addObject("productStatueList", productStatueList);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			model.put("product", product);
	       return commonModelAndView; 
	   } 	
	
	/**
	 *   创建和修改后保存产品信息
	 *  @param product
	 *  @param result
	 *  @param model
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-14 下午01:26:24
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping(value="/productcenter/product/maintain/save",method = RequestMethod.POST)
	public CommonModelAndView save(@Valid Product product,BindingResult result,Map<String,Object> model, HttpServletRequest request) {
		if (result.hasErrors()) {
			return new CommonModelAndView(product);
		}
		//视图名
		String viewName = null;
		try {
			if(product != null && product.getPrdId() != null){
				productService.update(product);
				viewName = this.SUCCESS;
			}else{
				boolean b = productService.saveProduct(product,request);
				if(b){
					viewName = this.SUCCESS;
				}else{
					viewName = this.ERROR;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,product,messageSource);
		return commonModelAndView;		
	}
	
	@RequestMapping(value="/productcenter/product/maintain/shared",method = RequestMethod.POST)
	public CommonModelAndView shared(Product product,HttpServletRequest request) {
		//视图名
		String viewName = null;
		try {
			if(product != null && product.getPrdId() != null){
				Product prd = new Product();
				prd.setPrdId(product.getPrdId());
				if(product.getShared() != null && product.getShared() == 1){
					prd.setShared(0);
				}else{
					prd.setShared(1);
				}
				productService.updateShared(prd);
				viewName = this.SUCCESS;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,product,messageSource);
		return commonModelAndView;		
	}
	
	@RequestMapping(value="/productcenter/product/maintain/generate",method = RequestMethod.POST)
	public CommonModelAndView generate(Product product,HttpServletRequest request) {
		//视图名
		String viewName = null;
		try {
			if(product != null && product.getPrdId() != null){
				productService.generate(product,request);
				viewName = this.SUCCESS;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			viewName = this.ERROR;
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,product,messageSource);
		return commonModelAndView;		
	}
	
	/**
	 *  删除到回收站
	 *  @param product
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-16 上午10:05:08
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/recyclerdelete")
    public CommonModelAndView recyclerdelete(Product product,HttpServletRequest request){
		//视图名
		String viewName = "";		
		try {
			//按照IDS批量置删除标记为1
			productService.deleteByArr(product);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, product, messageSource);
		return commonModelAndView;		
    }  
	
	 /**
	 *  回收站列表
	 *  @param product
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-16 上午10:04:49
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/recycler")
	    public CommonModelAndView recycler(Product product,HttpServletRequest request){ 
	        Object obj = new CommonModelAndView().getCurrentStatus(product, request);
			if (obj != null){
				if (obj instanceof Product){
					product = (Product)obj;
				}
			} 
			product.setDelFlag(1);
			CommonModelAndView commonModelAndView = new CommonModelAndView(request,product);
	    	commonModelAndView.addObject("code", product.getC());
	        return commonModelAndView;
	    }  
	 
	 
	 /**
	 *  回收站——删除产品
	 *  @param product
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-16 上午09:46:00
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/delete")
	    public CommonModelAndView delete(Product product,HttpServletRequest request){
			String viewName = null;		
			try {
				productService.delete(product,request);
				viewName = this.SUCCESS;
			} catch (Exception e) {
				viewName = this.ERROR;
				logger.error(e.getMessage());
			}
			CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,product,messageSource);
			return commonModelAndView;					
	    }  
	 
	/**
	 * 回收站——产品还原
	 *  @param product
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-16 上午10:04:18
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/restore")
    public CommonModelAndView restore(Product product,HttpServletRequest request){
		String viewName = null;		
		try {
			productService.restore(product);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,product,messageSource);
		return commonModelAndView;					
    }  
	
	/**
	 *  查看详情页面
	 *  @param product
	 *  @param request
	 *  @param model
	 *  @return
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-23 上午09:23:29
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/detail")
	   public CommonModelAndView detail(Product product,HttpServletRequest request,Map<String,Object> model){
		String code = product.getC();
		if(product.getId()!=null)
			product.setPrdId(product.getId());
		if (product.getPrdId()!= null){
			try {
				product = productService.getOneById(product);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}	
		product.setC(code);
		product.setId(product.getPrdId());
		// 同步服务器url
		String synURL = SystemProperty.getInstance("parameterConfig").getProperty("uploadUrl") ;
		//文件存放路径
		String filePath = SystemProperty.getInstance("parameterConfig").getProperty("pdfPath") ;
		product.setFilePath(filePath);
		product.setSynURL(synURL);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,product);
		try {
			Dictionary dictionary = new Dictionary();
			//产品类型下拉框
			dictionary.setDictSortValue(Constant.PRD_TYPE_PRODUCTTYPE);  
			List<ProductSort> productTypeList = (List<ProductSort>) PluginsConfigCache.getAllProductSortCache();
			commonModelAndView.addObject("productTypeList", productTypeList);
			//启用状态
			dictionary.setDictSortValue(Constant.PRD_TYPE_PRODUCTSTATUE);
			List<Dictionary> productStatueList = dictionaryService.getList(dictionary);
			commonModelAndView.addObject("productStatueList", productStatueList);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		model.put("product", product);
       return commonModelAndView; 
	   } 	
	
	/**
	 *  列表页面——产品启用
	 *  @param product
	 *  @param request
	 *  @return
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-23 上午09:23:13
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/start")
    public CommonModelAndView start(Product product,HttpServletRequest request){
		String viewName = null;		
		try {
			productService.start(product);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,product,messageSource);
		return commonModelAndView;					
    }
	

	/**
	 *  获对应产品属性列表json
	 *  @param product
	 *  @param nodeid
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-18 下午12:41:26
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/getAttributeList")
	public CommonModelAndView getAttributeJson(Product product,String nodeid,HttpServletRequest request){
		Map<String, Object> map = productService.getAttribute(product);
		return new CommonModelAndView("jsonView",map);
	}
	
	/**
	 *  产品修改——加载属性字段和已经填入的值
	 *  @param product
	 *  @param nodeid
	 *  @param request
	 *  @return
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-23 上午09:21:31
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/getAttrAndVal")
	public CommonModelAndView getAttrAndValJson(Product product,String nodeid,HttpServletRequest request){
		Map<String, Object> map = productService.getPrdAndAttrById(product);
		return new CommonModelAndView("jsonView",map);
	}
	
	/**
	 * 列表页面—— 加载查询条件
	 *  @param product
	 *  @param request
	 *  @return
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-23 上午09:22:06
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/getSelect")
	public CommonModelAndView getSelectJson(Product product,HttpServletRequest request){
		Map<String, Object> map = productService.getSelectByArrt(product);
		return new CommonModelAndView("jsonView",map);
	}
	
	@RequestMapping("/productcenter/product/maintain/uploadfile")
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		return productService.upload(baseEntity, request);
	}
    
	/**
	 *  列表页面——产品未发布
	 *  @param product
	 *  @param request
	 *  @return
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-23 上午09:22:37
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/stop")
    public CommonModelAndView stop(Product product,HttpServletRequest request){
		String viewName = null;		
		try {
			productService.stop(product);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,product,messageSource);
		return commonModelAndView;					
    }
	
	/**
	 *  删除PDF数据库记录
	 *  @param productPdf
	 *  @param nodeid
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-31 下午03:16:22
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/fileDel")
	public CommonModelAndView fileDel(ProductPdf productPdf,String nodeid,HttpServletRequest request){
		Map<String, Object> map = productService.fileDel(productPdf,request); 
		return new CommonModelAndView("jsonView",map);
	}
	
	/**
	 *  删除上传后未保存的PDF文件
	 *  @param productPdf
	 *  @param nodeid
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-6-4 上午09:20:13
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/deletePdfFile")
	public CommonModelAndView deletePdfFile(ProductPdf productPdf,String nodeid,HttpServletRequest request){
		if(productPdf!=null && productPdf.getId()!=null){
			// PDF存储路径
			String pdfPath = SystemProperty.getInstance("parameterConfig").getProperty("pdfPath") ;
			productPdf.setiPadUrl(pdfPath+productPdf.getId()+".pdf");
			productService.deletePdfFile(productPdf, request);
		}
		Map<String, Object> map =  new Hashtable<String, Object>();
		return new CommonModelAndView("jsonView",map);
	}
    
	/*************************************************CRM拉取*********************************************************/
	
	
	/**
	 *  CRM产品列表跳转页面
	 *  @param product
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-25 上午11:28:57
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/crmlist")
    public CommonModelAndView crmlist(CRMProduct crmProduct,HttpServletRequest request){
		CommonModelAndView commonModelAndView = new CommonModelAndView(request,crmProduct);
		try{
		Dictionary dictionary = new Dictionary();

		//产品类型下拉框
		dictionary.setDictSortValue(Constant.PRD_TYPE_PRODUCTTYPE);
		List<ProductSort> productTypeList = (List<ProductSort>) PluginsConfigCache.getAllProductSortCache();
		commonModelAndView.addObject("productTypeList", productTypeList);
	   	}catch(Exception e){
	   		logger.error(e.getMessage());
	   	}
 	   	commonModelAndView.addObject("code", crmProduct.getC());
 	   	commonModelAndView.addObject("crmProduct", crmProduct);
        return commonModelAndView;
    }  
	
	/**
	 *  拉取CRM产品
	 *  @param crmProduct
	 *  @param request
	 *  @return
	 *  @author Administrator
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-30 下午04:50:16
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/pulling")
    public CommonModelAndView pulling(CRMProduct crmProduct,HttpServletRequest request){
		String viewName = null;		
		try{
			//执行通过webservice拉取CRM产品库产品
			productService.pullingCrmProduct(crmProduct);
			viewName = this.SUCCESS;
		}catch(Exception e){
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,crmProduct,messageSource);
		return commonModelAndView;					
    }  
 
	
	/**
	 *  CRM产品库列表
	 *  @param crmProduct
	 *  @param nodeid
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-28 下午02:09:18
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/crmgrid")
	public CommonModelAndView crmJson(CRMProduct crmProduct,String nodeid,HttpServletRequest request){
		crmProduct.setProductName(CommonUtil.convertSearchSign(crmProduct.getProductName()));
		crmProduct.setPurchasePhase(CommonUtil.convertSearchSign(crmProduct.getPurchasePhase()));
		crmProduct.setProductIncome(CommonUtil.convertSearchSign(crmProduct.getProductIncome()));
//		crmProduct.setProductTerm(Integer.parseInt(CommonUtil.convertSearchSign(crmProduct.getProductIncome())));
		Map<String, Object> map = productService.getCrmGrid(crmProduct); 
		return new CommonModelAndView("jsonView",map);
	}

	/**
	 *  加载CRM产品属性对应值
	 *  @param product
	 *  @param nodeid
	 *  @param request
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-28 下午08:36:19
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/getCrmPrd")
	public CommonModelAndView getCrmPrdJson(Product product,String nodeid,HttpServletRequest request){
		Map<String, Object> map = productService.getCrmPrd(product); 
		return new CommonModelAndView("jsonView",map);
	}
	
	/**
	 *  CRM产品详细页面
	 *  @param crmProduct
	 *  @param product
	 *  @param request
	 *  @param model
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-7-31 下午01:46:33
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/crmdetails")
	   public CommonModelAndView crmdetails(CRMProduct crmProduct,Product product,HttpServletRequest request,Map<String,Object> model){
			String code = crmProduct.getC();
			if (crmProduct.getId()!= null){
				try {
					crmProduct = productService.getCrmPrdById(crmProduct);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}	
			crmProduct.setC(code);
			CommonModelAndView commonModelAndView = new CommonModelAndView(request,crmProduct);
			model.put("crmProduct",crmProduct);
	       return commonModelAndView; 
	   } 	
	
	/**
	 *  跳转到单个CRM产品上架界面
	 *  @param crmProduct
	 *  @param product
	 *  @param request
	 *  @param model
	 *  @return
	 *  @author haoyuanfu
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-28 下午08:35:55
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/crmadd")
	   public CommonModelAndView crmAdd(CRMProduct crmProduct,Product product,HttpServletRequest request,Map<String,Object> model){
			String code = crmProduct.getC();
			if (crmProduct.getId()!= null){
				try {
					crmProduct = productService.getCrmPrdById(crmProduct);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}	
			product.setC(code);
			CommonModelAndView commonModelAndView = new CommonModelAndView(request,product);
			long addedStatus = 0;
			try {
				Dictionary dictionary = new Dictionary();
				//产品类型列表
				List<ProductSort> productTypeList = (List<ProductSort>) PluginsConfigCache.getAllProductSortCache();
				commonModelAndView.addObject("productTypeList", productTypeList);
				//获取在本系统中的CRM对应产品类型
				for(ProductSort ps:productTypeList){
					if(ps.getName().equals(crmProduct.getProductType())){
						//产品类型
						product.setPrdType(Long.parseLong(ps.getValue()));
					}
				}
				product.setPrdTypeChn(crmProduct.getProductType());
				//产品名称
				product.setPrdName(crmProduct.getProductName());
				//产品在CRM中的编号
				product.setCrmPrdId((long)crmProduct.getSerialNo());
				//产品在CRM表中的ID
				product.setSysCrmId(crmProduct.getId());
				//产品在CRM表中的财汇数据编号
				product.setFinchinaSymbol(crmProduct.getPad7());
				//产品在CRM表中的产品结束时间
				product.setProductFinishTime(crmProduct.getProductFinishTime());
				//发布状态
				dictionary.setDictSortValue(Constant.PRD_TYPE_PRODUCTSTATUE);
				
				List<Dictionary> productStatueList = dictionaryService.getList(dictionary);
				commonModelAndView.addObject("productStatueList", productStatueList);
				//产品上架状态
				addedStatus = productService.addedStatus(crmProduct);
				
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			model.put("product",product);
			model.put("addedStatus",addedStatus);
			
	       return commonModelAndView; 
	   } 	
	
//	
	@RequestMapping("/productcenter/product/maintain/addedby")
    public CommonModelAndView addedby(CRMProduct crmProduct,HttpServletRequest request){
		//视图名
		String viewName = "";		
		try {
			//按照IDS批量上架Crm产品
			String names = productService.addedByArray(crmProduct);
			crmProduct.setPad10(names);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, crmProduct, messageSource);
		return commonModelAndView;		
    }  
	
	@RequestMapping("/productcenter/product/maintain/crmupd")
    public CommonModelAndView crmupd(CRMProduct crmProduct,HttpServletRequest request){
		String viewName = null;		
		try {
			productService.crmUpdFlag(crmProduct);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,crmProduct,messageSource);
		return commonModelAndView;					
    }
	
	/**
	 *  同步财汇数据
	 *  @param crmProduct
	 *  @param request
	 *  @return
	 *  @author baokai
	 *  @version 1.0
	 *  </pre>
	 *  Created on :2012-5-30 下午04:50:16
	 *  LastModified:
	 *  History:
	 *  </pre>
	 */
	@RequestMapping("/productcenter/product/maintain/synchronousTrustCode")
    public CommonModelAndView synchronousTrustCode(CRMProduct crmProduct,HttpServletRequest request){
		String viewName = null;		
		try{
			productService.synchronousTrustCode();
			viewName = this.SUCCESS;
		}catch(Exception e){
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,crmProduct,messageSource);
		return commonModelAndView;					
    }  
	
	@RequestMapping("/productcenter/product/maintain/export_member_product")
    public void exportMemberProduct(CRMProduct crmProduct,HttpServletRequest request, HttpServletResponse response){
		OutputStream ouputStream = null;
		try {
			XSSFWorkbook wb = this.productService.exportMemberProduct();
			Date date = new Date(System.currentTimeMillis());
		    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
			String fileName = java.net.URLEncoder.encode("成交客户存续产品信息", "UTF-8")+"("+df.format(date)+").xlsx";  
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName);
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);
		} catch (Exception e){
			e.printStackTrace();
		}finally{  
	         try{  
	        	 ouputStream.flush();  
	        	 ouputStream.close();  
	         }catch (Exception e){
	        	e.printStackTrace();
	        }  
	    }  
    }  
	
	/**
	 *  批量上传存续报告
	 *  @author baokai
	 *  Created on :2013-10-24 上午9:57:32
	 */
	@RequestMapping("/productcenter/product/maintain/upload_pdf")
	public CommonModelAndView uploadPDF(Product product,HttpServletRequest request){
		String viewName = null;		
		try {
			productService.uploadPDF(product,request);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,product,messageSource);
		return commonModelAndView;					
    }  
	
	/**
	 *  批量删除存续报告
	 *  @author baokai
	 *  Created on :2013-10-24 上午9:57:46
	 */
	@RequestMapping("/productcenter/product/maintain/delete_pdf")
	public CommonModelAndView deletePDF(Product product,HttpServletRequest request){
		String viewName = null;		
		try {
			productService.deletePDF(product,request);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName,product,messageSource);
		return commonModelAndView;					
    }  
}
