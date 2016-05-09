package com.sw.core.util.poi.excel.write;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 
 * 功能说明: 写excel2003的处理器 
 * 创建时间: 2012-5-25 上午09:15:45 
 * 最后修改时间: 2012-5-25 上午09:15:45 
 * 版本 1.0
 */
public class Excel2007WriteHandler {

	/**
	 * 日志处理类
	 */
	private static Log logger = LogFactory.getLog(Excel2007WriteHandler.class);

	public Excel2007WriteHandler() {
	}

	/**
	 * 
	 * 功能说明: 得到输出流
	 * 
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 */
	public OutputStream getOutPutStream(String filePath)
			throws FileNotFoundException {

		if (filePath == null || "".equals(filePath)) {
			logger.info("WriteTo2007Excel类的getOutPutStream()方法的filePath的值:"
					+ filePath + "不正确。");
			return null;
		}

		return new FileOutputStream(new File(filePath));
	}

	/**
	 * 
	 * 功能说明: 关闭流
	 * 
	 * @param os
	 * @throws IOException
	 */
	public void closeStream(OutputStream os) throws IOException {
		os.close();
	}

	/**
	 * 
	 * 功能说明: 获取XSSFSheet对象
	 * 
	 * @param workbook
	 * @return
	 */
	public XSSFSheet getXSSFSheet(XSSFWorkbook workbook) {

		if (workbook == null || "".equals(workbook)) {
			logger.info("WriteTo2007Excel类的getXSSFSheet()方法的workbook的值:"
					+ workbook + "不正确。");
			return null;
		}

		return workbook.createSheet();
	}

	/**
	 * 
	 * 功能说明: 根据sheet名称获取sheet
	 * 
	 * @param workbook
	 * @param sheetName
	 * @return
	 */
	public XSSFSheet getXSSFSheet(XSSFWorkbook workbook, String sheetName) {
		if (workbook == null || "".equals(workbook)) {
			logger.info("WriteTo2007Excel类的getXSSFSheet()方法的workbook的值:"
					+ workbook + "不正确。");
			return null;
		}
		return workbook.createSheet(sheetName);
	}

	/**
	 * 
	 * 功能说明: 填充数据
	 * 
	 * @param sheet
	 * @param dataList
	 */
	public void fillData(XSSFSheet sheet, List dataList) {

		if (sheet == null) {
			logger.info("WriteTo2007Excel类的fillDataToSheet()方法的sheet的值:"
					+ sheet + "不正确。");
			return;
		}
		if (dataList == null && dataList.size() < 1) {
			logger
					.info("WriteTo2007Excel类的fillDataToSheet()方法的dataList的值:为空或不正确。");
			return;
		}

		for (int i = 0; i < dataList.size(); i++) {
			List rowList = (List) dataList.get(i);
			XSSFRow row = sheet.createRow(i);
			for (int j = 0; j < rowList.size(); j++) {
				XSSFCell cell = row.createCell(j);
				cell.setCellValue("" + rowList.get(j));
			}
		}

	}

	/**
	 * 
	 * 功能说明: 写指定sheet数据
	 * 
	 * @param filePath
	 * @param sheetName
	 * @param dataList
	 * @throws IOException
	 */
	public void writeData(String filePath, String sheetName, List dataList)
			throws IOException {

		if (filePath == null || "".equals(filePath)) {
			logger.info("WriteTo2007Excel类的writeData()方法的filePath为空！");
			return;
		}

		if (dataList == null || dataList.size() < 1) {
			logger.info("WriteTo2007Excel类的writeData()方法的dataList为空！");
			return;
		}

		XSSFSheet sheet = null;
		OutputStream os = null;
		try {
			os = new FileOutputStream(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook();
			if (sheetName == null || "".equals(sheetName)) {
				sheet = getXSSFSheet(workbook);
			} else {
				sheet = getXSSFSheet(workbook, sheetName);
			}

			fillData(sheet, dataList);
			workbook.write(os);
			os.flush();
		} catch (Exception e) {
			logger.info("WriteTo2007Excel类的writeData()方法执行错误！");
			throw new IOException();
		} finally {
			closeStream(os);
		}

	}

	/**
	 * 
	 * 功能说明: 向第一个sheet写数据
	 * 
	 * @param filePath
	 * @param dataList
	 * @throws IOException
	 */
	public void writeData(String filePath, List dataList) throws IOException {
		String sheetName = null;
		this.writeData(filePath, sheetName, dataList);
	}

	/**
	 * 
	 * 功能说明: 向新的sheet中写数据
	 * 
	 * @param filePath
	 * @param dataMap
	 * @throws IOException
	 */
	public void writeDataToNewSheet(String filePath, Map<String, List> dataMap)
			throws IOException {

		if (filePath == null || "".equals(filePath)) {
			logger
					.info("WriteTo2007Excel类的writeDataToNewSheet()方法的filePath为空！");
			return;
		}

		if (dataMap == null || dataMap.size() < 1) {
			logger
					.info("WriteTo2007Excel类的writeToExcelMoreSheet()方法的dataMap为空。");
			return;
		}

		OutputStream os = null;
		XSSFWorkbook workbook = null;
		try {
			os = getOutPutStream(filePath);
			workbook = new XSSFWorkbook();
			Set sheetNameSet = dataMap.keySet();
			Iterator sheetNameIterator = sheetNameSet.iterator();
			while (sheetNameIterator.hasNext()) {
				String sheetName = (String) sheetNameIterator.next();
				List dataList = dataMap.get(sheetName);
				XSSFSheet sheet = getXSSFSheet(workbook, sheetName);
				fillData(sheet, dataList);
			}
			workbook.write(os);
			os.flush();
		} catch (Exception e) {
			logger.info("WriteTo2007Excel类的writeDataToNewSheet()方法执行错误！");
			throw new IOException();
		} finally {
			closeStream(os);
		}

	}

	/**
	 * 
	 * 功能说明: 向新的sheet中写数据
	 * 
	 * @param filePath
	 * @param allDataList
	 * @throws IOException
	 */
	public void writeDataToNewSheet(String filePath, List<List> allDataList)
			throws IOException {
		if (filePath == null || "".equals(filePath)) {
			logger
					.info("WriteTo2007Excel类的writeDataToNewSheet()方法的filePath为空！");
			return;
		}
		if (allDataList == null || allDataList.size() < 1) {
			logger
					.info("WriteTo2007Excel类的writeToExcelMoreSheet()方法的allDataList为空。");
			return;
		}
		OutputStream os = null;
		XSSFWorkbook workbook = null;
		try {
			os = getOutPutStream(filePath);
			workbook = new XSSFWorkbook();
			for (int i = 0; i < allDataList.size(); i++) {
				List dataList = allDataList.get(i);
				XSSFSheet sheet = getXSSFSheet(workbook);
				fillData(sheet, dataList);
			}
			workbook.write(os);
			os.flush();
		} catch (Exception e) {
			logger.info("WriteTo2007Excel类的writeDataToNewSheet()方法执行错误！");
			throw new IOException();
		} finally {
			closeStream(os);
		}
	}

	/**
	 * 
	 * 功能说明: 追加数据
	 * 
	 * @param filePath
	 * @param sheetIndex
	 * @param dataList
	 * @throws IOException
	 */
	public void appendData(String filePath, int sheetIndex, List dataList)
			throws IOException {
		if (filePath == null || "".equals(filePath)) {
			logger.info("WriteTo2007Excel类的appendData()方法的filePath为空！");
			return;
		}
		if (dataList == null || dataList.size() < 1) {
			logger.info("WriteTo2007Excel类的appendData()方法的dataList为空。");
			return;
		}
		XSSFSheet sheet = null;
		XSSFRow row = null;
		XSSFCell cell = null;
		OutputStream os = null;
		FileInputStream is = null;
		try {
			os = new FileOutputStream(new File(filePath));
			is = new FileInputStream(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			sheet = workbook.getSheetAt(sheetIndex);
			for (int i = 0; i < dataList.size(); i++) {
				List rowList = (List) dataList.get(i);
				row = sheet
						.createRow((short) (sheet.getPhysicalNumberOfRows() + 1));
				for (int j = 0; j < rowList.size(); j++) {
					cell = row.createCell(j);
					cell.setCellValue("" + rowList.get(j));
				}
			}

			workbook.write(os);
			os.flush();

		} catch (Exception e) {
			logger.info("WriteTo2007Excel类的appendData()方法执行错误！");
			throw new IOException();
		} finally {
			os.close();
			is.close();
		}

	}

	/**
	 * 
	 * 功能说明: 追加数据
	 * 
	 * @param filePath
	 * @param dataList
	 * @throws IOException
	 */
	public void appendData(String filePath, List dataList) throws IOException {
		int sheetIndex = 0;
		this.appendData(filePath, sheetIndex, dataList);
	}
}
