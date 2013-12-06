package com.mj.jqplot.sampling.client.chart;

abstract class JQplotAbstractChartOption< T > {
	
	protected String key ;
	
	protected T value ;
	
	JQplotAbstractChartOption( String key, T value  ) {
		this.key = key ;
		this.value = value ;
	}
	
	void setValue( T value ) {
		this.value = value;
	}
	
	T getValue() {
		return  value ;
	}
	
	
	String getKey() {
		return key;
	} 
	
	
	protected abstract String asJson() ;
	

}
