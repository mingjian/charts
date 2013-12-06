package com.mj.jqplot.sampling.client.chart;

import java.util.Collection;
import java.util.LinkedHashMap;

class JQPlotOptionsMap {

	private LinkedHashMap< String, JQplotAbstractChartOption< ? > > map ;
	
	
	JQPlotOptionsMap() {
		map = new LinkedHashMap< String, JQplotAbstractChartOption< ? > >() ;
	}
	
	void add( JQplotAbstractChartOption< ? > option ){
		map.put( option.getKey(), option ) ;
	}
	
	JQplotAbstractChartOption< ? > get( String key ){
		return map.get( key ) ;
	}
	
	Collection< JQplotAbstractChartOption< ? > > list() {
		return map.values() ;
	}
	
}
