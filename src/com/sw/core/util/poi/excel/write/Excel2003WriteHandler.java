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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * 
 * 功能说明: excel2003写操作处理器 
 * 创建时间: 2012-5-24 下午02:39:25 
 * 最后修改时间: 2012-5-24 下午02:39:25 
 * 版本 1.0
 */
public class Excel2003WriteHandler {

	/**
	 * 日志处理类
	 */
	private static Log logger = LogFactory.getLog(Excel2003WriteHandler.class);

	/**
	 * 构造函数
	 */
	public Excel2003WriteHandler() {

	}

	/**
	 * 
	 * 功能说明: 获取输出流
	 * 
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 */
	public OutputStream getOutPutStream(String filePath)
			throws FileNotFoundException {
		if (filePath == null || "".equals(filePath)) {
			logger.info("filePath路径为空");
			return null;
		}

		return new FileOutputStream(new File(filePath));
	}

	/**
	 * 
	 * 功能说明: 关闭输出流
	 * 
	 * @param os
	 * @throws IOException
	 */
	public void closeStream(OutputStream os) throws IOException {
		if (os != null) {
			os.close();
		} else {
			logger.info("OutputStream关闭失败！");
		}
	}

	/**
	 * 
	 * 功能说明: 获取sheet
	 * 
	 * @param workBook
	 * @return
	 */
	public HSSFSheet getHSSFSheet(HSSFWorkbook workBook) {
		if (workBook == null) {
			logger.info("getHSSFSheet()方法的workBook为Null。");
			return null;
		}
		return workBook.createSheet();
	}

	/**
	 * 
	 * 功能说明: 获取指定名称的sheet
	 * 
	 * @param workBook
	 * @param sheetName
	 * @return
	 */
	public HSSFSheet getHSSFSheet(HSSFWorkbook workBook, String sheetName) {
		if (workBook == null) {
			logger.info("getHSSFSheet()方法的workBook为Null。");
			return null;
		}

		if (workBook == null) {
			logger.info("getHSSFSheet()方法的workBook为Null。");
			return null;
		}

		return workBook.createSheet(sheetName);
	}

	/**
	 * 
	 * 功能说明: 填充数据
	 * 
	 * @param sheet
	 * @param dataList
	 */
	public void fillData(HSSFSheet sheet, List dataList) {
		if (sheet == null) {
			logger.info("fillDataToSheet()方法的sheet为Null。");
			return;
		}
		if (dataList == null || dataList.size() < 1) {
			logger.info("fillDataToSheet()方法的dataList无数据");
			return;
		}
		for (int i = 0; i < dataList.size(); i++) {
			List rowList = (List) dataList.get(i);
			HSSFRow row = sheet.createRow(i);
			for (int j = 0; j < rowList.size(); j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue("" + rowList.get(j));
			}
		}
	}

	/**
	 * 
	 * 功能说明: 向excel写文件
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
	 * 功能说明: 向excel写文件
	 * 
	 * @param filePath
	 * @param sheetName
	 * @param dataList
	 * @throws IOException
	 */
	public void writeData(String filePath, String sheetName, List dataList)
			throws IOException {

		if (filePath == null || "".equals(filePath)) {
			logger.info("writeData()方法的filePath无数据");
		}
		if (dataList == null || dataList.size() < 1) {
			logger.info("writeData()方法的dataList无数据");
		}
		OutputStream os = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		try {
			os = new FileOutputStream(new File(filePath));
			workbook = new HSSFWorkbook();
			if (sheetName == null || "".equals(sheetName)) {
				sheet = getHSSFSheet(workbook);
			} else {
				sheet = getHSSFSheet(workbook, sheetName);
			}

			fillData(sheet, dataList);
			workbook.write(os);
			os.flush();
		} catch (Exception e) {
			logger.info("writeData()方法执行错误。");
			throw new IOException();
		} finally {
			closeStream(os);
		}
	}

	/**
	 * 
	 * 功能说明: 向新的sheet里写数据
	 * 
	 * @param filePath
	 * @param allDataList
	 * @throws IOException
	 */
	public void writeDataToNewSheet(String filePath, List<List> allDataList)
			throws IOException {
		if (allDataList == null || allDataList.size() < 1) {
			logger.info("writeDataToNewSheet()方法的allDataList无数据");
			return;
		}
		if (filePath == null || "".equals(filePath)) {
			logger.info("writeDataToNewSheet()方法的filePath为空");
			return;
		}

		OutputStream os = null;
		try {
			os = getOutPutStream(filePath);
			HSSFWorkbook workbook = new HSSFWorkbook();
			for (int i = 0; i < allDataList.size(); i++) {
				List dataList = allDataList.get(i);
				HSSFSheet sheet = getHSSFSheet(workbook);
				fillData(sheet, dataList);
			}
			workbook.write(os);
			os.flush();
		} catch (Exception e) {
			logger.info("writeData()方法执行错误。");
			throw new IOException();
		} finally {
			closeStream(os);
		}
	}

	/**
	 * 
	 * 功能说明: 向新的sheet里写数据
	 * 
	 * @param filePath
	 * @param dataMap
	 * @throws IOException
	 */
	public void writeDataToNewSheet(String filePath, Map<String, List> dataMap)
			throws IOException {
		if (dataMap == null || dataMap.size() < 1) {
			logger.info("writeDataToNewSheet()方法的dataMap无数据");
			return;
		}
		if (filePath == null || "".equals(filePath)) {
			logger.info("writeDataToNewSheet()方法的filePath为空");
			return;
		}
		OutputStream os = null;
		try {
			os = getOutPutStream(filePath);
			HSSFWorkbook workbook = new HSSFWorkbook();

			Set sheetNameSet = dataMap.keySet();
			Iterator sheetNameIterator = sheetNameSet.iterator();
			while (sheetNameIterator.hasNext()) {
				String sheetName = (String) sheetNameIterator.next();
				List dataList = dataMap.get(sheetName);
				HSSFSheet sheet = getHSSFSheet(workbook, sheetName);
				fillData(sheet, dataList);
			}

			workbook.write(os);
			os.flush();
		} catch (Exception e) {
			logger.info("writeDataToNewSheet()方法执行错误。");
			throw new IOException();
		} finally {
			closeStream(os);
		}

	}

	/**
	 * 
	 * 功能说明: 向指定的sheet追加数据
	 * 
	 * @param filePath
	 * @param sheetIndex
	 * @param dataList
	 * @throws IOException
	 */
	public void appendData(String filePath, int sheetIndex, List dataList)
			throws IOException {

		if (dataList == null || dataList.size() < 1) {
			logger.info("writeDataToNewSheet()方法的dataList无数据");
			return;
		}
		if (filePath == null || "".equals(filePath)) {
			logger.info("writeDataToNewSheet()方法的filePath为空");
			return;
		}

		HSSFRow row = null;
		HSSFCell cell = null;
		FileInputStream is = null;
		POIFSFileSystem fs = null;
		OutputStream os = null;

		try {
			is = new FileInputStream(filePath);
			fs = new POIFSFileSystem(is);
			os = new FileOutputStream(new File(filePath));
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
			for (int i = 0; i < dataList.size(); i++) {
				List rowList = (List) dataList.get(i);
				row = sheet.createRow((short) (sheet.getLastRowNum() + 1));
				for (int j = 0; j < rowList.size(); j++) {
					cell = row.createCell(j);
					cell.setCellValue("" + rowList.get(j));
				}
			}

			workbook.write(os);
			os.flush();

		} catch (Exception e) {
			logger.info("appendData()方法执行错误。");
			throw new IOException();
		} finally {
			os.close();
			is.close();
		}
	}

	/**
	 * 
	 * 功能说明: 向第一个sheet追加数据
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
