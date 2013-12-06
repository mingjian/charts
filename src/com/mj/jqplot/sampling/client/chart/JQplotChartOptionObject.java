package com.mj.jqplot.sampling.client.chart;

import java.util.Iterator;

class JQplotChartOptionObject extends JQplotAbstractChartOption< JQPlotOptionsMap > {

	JQplotChartOptionObject( String key ) {
		super( key, new JQPlotOptionsMap () );
	}

	void add( JQplotAbstractChartOption< ? > entity ) {
		value.add( entity ) ;
	}
	
	JQplotAbstractChartOption< ? > get( String key ){
		return value.get( key ) ;
	}

	@Override
	protected String asJson() {
		
		StringBuilder sb = new StringBuilder() ;
		sb.append( key ).append( ": { " ) ;
		
		Iterator< JQplotAbstractChartOption< ? > > entities = value.list().iterator() ;
		
		JQplotAbstractChartOption< ? > entity ;
		
		while ( entities.hasNext() ){
		
			entity = entities.next() ;
			sb.append( entity.asJson() ) ;
			if( entities.hasNext() ) sb.append( "," ) ;
		
		}
		
		sb.append ( "}" ) ;
		
		return sb.toString() ;
		
	}
	
	
}
