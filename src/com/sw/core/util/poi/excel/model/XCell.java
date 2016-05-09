package com.sw.core.util.poi.excel.model;

public class XCell {

	// 行索引
	private int rowIndex;
	// 列索引
	private String columnIndex;
	// 单元格的值
	private String value;
	// 是否是合并的单元格
	private boolean isSpan;

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public String getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(String columnIndex) {
		this.columnIndex = columnIndex;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isSpan() {
		return isSpan;
	}

	public void setSpan(boolean isSpan) {
		this.isSpan = isSpan;
	}

}
