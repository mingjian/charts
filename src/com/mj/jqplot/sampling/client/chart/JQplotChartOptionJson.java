package com.mj.jqplot.sampling.client.chart;

class JQplotChartOptionJson extends JQplotAbstractChartOption< String > {

	protected boolean isRef ;
	
	JQplotChartOptionJson( String key, String jsonValue ) {
		super( key, jsonValue );
	}

	@Override
	protected String asJson() {
		return key + " : " + value ;
	}

	
	
}
