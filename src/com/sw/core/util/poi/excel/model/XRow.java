package com.sw.core.util.poi.excel.model;

import java.util.ArrayList;
import java.util.List;

public class XRow {
	// 行索引
	private int rowIndex;
	// 每行元素的集合
	private List<XCell> cells = new ArrayList<XCell>();

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getCellsSize() {
		return cells.size();
	}

	public XRow addCell(XCell cell) {
		this.cells.add(cell);
		return this;
	}

	public XCell getCell(int cellIndex) {
		return cells.get(cellIndex);
	}
}
