package com.mj.jqplot.sampling.client.chart;

import java.util.Collection;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.JsArrayString;

class JQplotChartOptionStringArray extends JQplotAbstractChartOption< Collection< String > > {

	JQplotChartOptionStringArray( String key, Collection< String > values ) {
		super( key, values );
	}

	@Override
	protected String asJson() {
		final JsArrayString stringArray = JsArray.createArray().cast();
		for ( String s : value ){
			stringArray.push( s ) ;
		}
		
		return key + ": " + stringify( stringArray ) ;
	}

	
	protected static native String stringify( JavaScriptObject o ) /*-{
		return $wnd.JSON.stringify(o);
	}-*/;

	
}
