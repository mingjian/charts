package com.mj.jqplot.sampling.client.chart;

class JQplotChartOptionBoolean extends JQplotAbstractChartOption< Boolean > {

	JQplotChartOptionBoolean( String key, boolean value ) {
		super(key, value);
	}

	@Override
	protected String asJson() {
		return key + ":" + value ;
	}
	
	
}
