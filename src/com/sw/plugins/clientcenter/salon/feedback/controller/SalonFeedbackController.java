package com.sw.plugins.clientcenter.salon.feedback.controller;

import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sw.core.common.Constant;
import com.sw.core.controller.BaseController;
import com.sw.core.controller.CommonModelAndView;
import com.sw.core.data.entity.BaseEntity;
import com.sw.core.util.CommonUtil;
import com.sw.plugins.clientcenter.salon.feedback.entity.SalonFeedback;
import com.sw.plugins.clientcenter.salon.feedback.service.SalonFeedbackService;

/**
 * 理财沙龙反馈信息控制器，负责理财沙龙反馈信息删除，查询等功能
 * @author Administrator
 *
 */
@Controller
public class SalonFeedbackController extends BaseController {

	private static Logger logger = Logger.getLogger(SalonFeedbackController.class);
	
	@Resource
	private SalonFeedbackService salonFeedbackService;
	
	/**
     * 理财沙龙反馈信息列表方法
     * @param code
     * @param request
     * @return
     */
	@RequestMapping("/clientcenter/salon/feedback/list")
    public CommonModelAndView list(SalonFeedback salonFeedback,HttpServletRequest request,Map<String,Object> model){ 
		Object obj = new CommonModelAndView().getCurrentStatus(salonFeedback, request);
		if (obj != null){
			if (obj instanceof SalonFeedback){
				salonFeedback = (SalonFeedback)obj;
			}
		} 
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, salonFeedback);
		commonModelAndView.addObject("code", salonFeedback.getC());
		commonModelAndView.addObject("salonFeedback", salonFeedback);
        return commonModelAndView;
    }
	
	/**
     * 理财沙龙反馈信息列表方法
     * @param code
     * @param request
     * @return
     */
	@RequestMapping("/clientcenter/salon/feedback/recycle")
    public CommonModelAndView recycle(SalonFeedback salonFeedback,HttpServletRequest request,Map<String,Object> model){ 
		Object obj = new CommonModelAndView().getCurrentStatus(salonFeedback, request);
		if (obj != null){
			if (obj instanceof SalonFeedback){
				salonFeedback = (SalonFeedback)obj;
			}
		} 
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, salonFeedback);
		commonModelAndView.addObject("code", salonFeedback.getC());
		commonModelAndView.addObject("salonFeedback", salonFeedback);
        return commonModelAndView;
    }
	
	/**
	 * 跳转理财沙龙反馈信息详细页面
	 * @param code
	 * @param salonFeedback
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/salon/feedback/detail")
    public CommonModelAndView detail(SalonFeedback salonFeedback,HttpServletRequest request){
		String code = salonFeedback.getC();
		try {
			salonFeedback = salonFeedbackService.getOneById(salonFeedback);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		salonFeedback.setC(code);
		CommonModelAndView commonModelAndView = new CommonModelAndView(request, salonFeedback); 
		commonModelAndView.addObject("salonFeedback", salonFeedback);
        return commonModelAndView; 
    }
	
	/**
	 * 理财沙龙反馈信息删除功能，json格式
	 * @param consult
	 * @param request
	 * @return
	 */
	@RequestMapping("/clientcenter/salon/feedback/*delete")
    public CommonModelAndView delete(SalonFeedback salonFeedback,HttpServletRequest request){
		//视图名
		String viewName = null;		
		try {
			String title = URLDecoder.decode(salonFeedback.getTitle(), Constant.ENCODING);
			salonFeedback.setTitle(title);
			salonFeedbackService.deleteByArr(salonFeedback);
			viewName = this.SUCCESS;
		} catch (Exception e) {
			viewName = this.ERROR;
			logger.error(e.getMessage());
		}
		CommonModelAndView commonModelAndView = new CommonModelAndView(viewName, salonFeedback, messageSource);
		return commonModelAndView;
    }
	
	/**
	 *  查询理财沙龙反馈信息 返回json格式
	 *  @param consult
	 *  @param request
	 */
	@RequestMapping("/clientcenter/salon/feedback/grid")
	public CommonModelAndView json(SalonFeedback salonFeedback,HttpServletRequest request){
		salonFeedback.setTitle(CommonUtil.convertSearchSign(salonFeedback.getTitle()));
		if(salonFeedback.getPeopleNumber() != null && "".equals(salonFeedback.getPeopleNumber())){
			salonFeedback.setPeopleNumber(Integer.parseInt(CommonUtil.convertSearchSign(salonFeedback.getPeopleNumber().toString())));
		}
		Map<String, Object> map = salonFeedbackService.getGrid(salonFeedback); 
		return new CommonModelAndView("jsonView",map); 
	}
	
	/**
	 *  理财沙龙反馈信息导出
	 *  @param consult
	 *  @param request
	 * @throws Exception 
	 */
	@RequestMapping("/clientcenter/salon/feedback/*export")
	public void export(SalonFeedback salonFeedback,HttpServletRequest request, HttpServletResponse response) throws Exception{
		XSSFWorkbook wb= new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet();
		
		//设置样式
		XSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short)14);
		font.setFontName("宋体");
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setFont(font);
		CellStyle cellStyle1 = wb.createCellStyle();
		cellStyle1.setAlignment(CellStyle.ALIGN_CENTER);
		
		XSSFRow row = null;
		XSSFCell cell1 = null;
		XSSFCell cell2 = null;
		XSSFCell cell3 = null;
		row = sheet.createRow(0);
		sheet.setColumnWidth(0, 15000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 6000);
		cell1 = row.createCell(0);
		cell1.setCellStyle(cellStyle);
		cell1.setCellValue("标题");
		cell2 = row.createCell(1);
		cell2.setCellStyle(cellStyle);
		cell2.setCellValue("参加人数");
		cell3 = row.createCell(2);
		cell3.setCellStyle(cellStyle);
		cell3.setCellValue("手机号");
		List<SalonFeedback> dataList = salonFeedbackService.export(salonFeedback);
		//Short[] columnWidth = {15000, 4000, 8000};
		for (int i = 0; i < dataList.size(); i++) {
			row = sheet.createRow(i+1);
			//sheet.setColumnWidth(i, columnWidth[i]);
			cell1 = row.createCell(0);
			cell1.setCellValue("" + dataList.get(i).getTitle());
			cell2 = row.createCell(1);
			cell2.setCellStyle(cellStyle1);
			cell2.setCellValue("" + dataList.get(i).getPeopleNumber());
			cell3 = row.createCell(2);
			cell3.setCellStyle(cellStyle1);
			cell3.setCellValue("" + dataList.get(i).getPeopleMobilePhone());
		}
		String fileName = "salonFeedBack.xlsx";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);   
		ouputStream.flush();   
		ouputStream.close();
	}
	
	@Override
	public String uploadfile(BaseEntity baseEntity, HttpServletRequest request) {
		return null;
	}

}
