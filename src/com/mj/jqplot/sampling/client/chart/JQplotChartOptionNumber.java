package com.mj.jqplot.sampling.client.chart;

public class JQplotChartOptionNumber extends JQplotAbstractChartOption< Number > {

	public JQplotChartOptionNumber( String key, Number value ) {
		super(key, value);
	}

	@Override
	protected String asJson() {
		return key + ":" + value.toString() ;
	}

}
