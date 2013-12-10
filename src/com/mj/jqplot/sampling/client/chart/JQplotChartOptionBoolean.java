package com.mj.jqplot.sampling.client.chart;

public class JQplotChartOptionBoolean extends JQplotAbstractChartOption< Boolean > {

    public JQplotChartOptionBoolean( String key, boolean value ) {
		super(key, value);
	}

	@Override
	protected String asJson() {
		return key + ":" + value ;
	}
	
	
}
