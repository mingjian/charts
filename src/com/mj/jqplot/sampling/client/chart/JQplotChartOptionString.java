package com.mj.jqplot.sampling.client.chart;

class JQplotChartOptionString extends JQplotAbstractChartOption< String > {

	protected boolean isRef ;
	
	
    public JQplotChartOptionString (String key, String value) {
        this (key, value, false);
    }
	
	JQplotChartOptionString( String key, String value, boolean isRef ) {
		super( key, value );
		this.isRef = isRef ;
	}

	@Override
	protected String asJson() {
		return isRef ? key + ": " + value : key + ": '" + value + "'" ;
	}

	
	
}
