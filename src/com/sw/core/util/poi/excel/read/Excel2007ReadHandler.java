package com.sw.core.util.poi.excel.read;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * 
 * 功能说明: 读取excel2007的处理器 
 * 创建时间: 2012-5-25 下午02:06:14 
 * 最后修改时间: 2012-5-25下午02:06:14 
 * 版本 1.0
 * 
 */
public abstract class Excel2007ReadHandler extends DefaultHandler {

	private SharedStringsTable sst;
	private String lastContents;
	private boolean nextIsString;

	private int sheetIndex = -1;
	private List<String> rowlist = new ArrayList<String>();
	private int curRow = 0;
	private int curCol = 0;
	private static Log logger = LogFactory.getLog(Excel2003ReadHandler.class);

	/**
	 * 
	 * 功能说明: 行操作方法
	 * 
	 * @param sheetIndex
	 * @param curRow
	 * @param rowlist
	 * @throws Exception
	 */
	public abstract void optRows(int sheetIndex, int curRow,
			List<String> rowlist) throws Exception;

	/**
	 * 
	 * 功能说明: 值处理一个sheet文件
	 * 
	 * @param filename
	 * @param sheetId
	 * @throws Exception
	 */
	public void processOneSheet(String fileName, int sheetId) throws Exception {
		if (fileName == null || "".equals(fileName)) {
			logger.info("processOneSheet方法的fileName为空。");
		}

		if (sheetId < 1) {
			sheetId = 1;
		}

		OPCPackage pkg = OPCPackage.open(fileName);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);

		// 根据 rId# 或 rSheet# 查找sheet
		InputStream sheet2 = null;
		try {
			sheet2 = r.getSheet("rId" + sheetId);
			sheetIndex++;
			InputSource sheetSource = new InputSource(sheet2);
			parser.parse(sheetSource);
		} catch (Exception e) {
			logger.info("processOneSheet方法执行错误。");
		} finally {
			sheet2.close();
		}

	}

	/**
	 * 
	 * 功能说明: 遍历整个excel文件
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public void process(String fileName) throws Exception {
		if (fileName == null || "".equals(fileName)) {
			logger.info("process方法的fileName为空。");
		}

		OPCPackage pkg = OPCPackage.open(fileName);
		XSSFReader r = new XSSFReader(pkg);
		SharedStringsTable sst = r.getSharedStringsTable();
		XMLReader parser = fetchSheetParser(sst);

		Iterator<InputStream> sheets = r.getSheetsData();
		while (sheets.hasNext()) {
			curRow = 0;
			sheetIndex++;
			InputStream sheet = null;
			try {
				sheet = sheets.next();
				InputSource sheetSource = new InputSource(sheet);
				parser.parse(sheetSource);
			} catch (Exception e) {
				logger.info("process方法执行错误。");
				throw new Exception();
			} finally {
				sheet.close();
			}
		}
	}

	/**
	 * 
	 * 功能说明:  sheet转化成  XMLReader
	 * @param sst
	 * @return
	 * @throws SAXException
	 */
	public XMLReader fetchSheetParser(SharedStringsTable sst)
			throws SAXException {
		if (sst == null) {
			return null;
		}
		
		XMLReader parser = XMLReaderFactory
				.createXMLReader("org.apache.xerces.parsers.SAXParser");
		this.sst = sst;
		parser.setContentHandler(this);
		return parser;
	}
	/**
	 * 
	 * 功能说明:  开始元素
	 */
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		// c => 单元格
		if (name.equals("c")) {
			// 如果下一个元素是 SST 的索引，则将nextIsString标记为true
			String cellType = attributes.getValue("t");
			if (cellType != null && cellType.equals("s")) {
				nextIsString = true;
			} else {
				nextIsString = false;
			}
		}
		// 置空
		lastContents = "";
	}
	/**
	 * 功能说明:  结束元素
	 */
	public void endElement(String uri, String localName, String name)
			throws SAXException {
		// 根据SST的索引值的到单元格的真正要存储的字符串
		// 这时characters()方法可能会被调用多次
		if (nextIsString) {
			try {
				int idx = Integer.parseInt(lastContents);
				lastContents = new XSSFRichTextString(sst.getEntryAt(idx))
						.toString();
			} catch (Exception e) {

			}
		}

		// t 是数据标签
		// 将单元格内容加入rowlist中，在这之前先去掉字符串前后的空白符
		if (name.equals("v")) {
			String value = lastContents.trim();
			value = value.equals("") ? " " : value;
			rowlist.add(curCol, value);
			curCol++;
		} else if (name.equals("t")) {
			String value = lastContents.trim();
			value = value.equals("") ? " " : value;
			rowlist.add(curCol, value);
			curCol++;
		} else {
			// 如果标签名称为 row ，这说明已到行尾，调用 optRows() 方法
			if (name.equals("row")) {
				try {
					optRows(sheetIndex, curRow, rowlist);
				} catch (Exception e) {
					e.printStackTrace();
				}
				rowlist.clear();
				curRow++;
				curCol = 0;
			}   
		}
	}

	/**
	 * 得到单元格内容的值
	 */
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// 得到单元格内容的值
		lastContents += new String(ch, start, length);
	}

}
