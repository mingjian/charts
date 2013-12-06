package com.mj.jqplot.sampling.client.chart;

import com.google.gwt.core.client.JavaScriptObject;

public class JQplotLineChartData extends JavaScriptObject {

	protected JQplotLineChartData() {
	}

	public static native JQplotLineChartData create(String name, int value) /*-{
		columnData = new Array();
		columnData[0] = name;
		columnData[1] = value;
		return columnData;
	}-*/;

	public static native JQplotLineChartData create(String name, double value) /*-{
		columnData = new Array();
		columnData[0] = name;
		columnData[1] = value;
		return columnData;
	}-*/;
	
}
