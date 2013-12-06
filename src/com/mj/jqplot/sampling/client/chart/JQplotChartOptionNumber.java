package com.mj.jqplot.sampling.client.chart;

class JQplotChartOptionNumber extends JQplotAbstractChartOption< Number > {

	JQplotChartOptionNumber( String key, Number value ) {
		super(key, value);
	}

	@Override
	protected String asJson() {
		return key + ":" + value.toString() ;
	}

}
