package com.mj.jqplot.sampling.client.chart;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.GwtEvent;


public class JQplotChartClickEvent extends GwtEvent< JQplotChartClickHandler > {

	public final static com.google.gwt.event.shared.GwtEvent.Type< JQplotChartClickHandler > TYPE = 
			new Type< JQplotChartClickHandler > ();
	
	
	private JavaScriptObject nativeEvent ;
	
	private int seriesIndex ;
	
	private int pointIndex ; 
	
	private JavaScriptObject data ;
	
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type< JQplotChartClickHandler > getAssociatedType() {
		return TYPE ;
	}

	
	@Override
	protected void dispatch( JQplotChartClickHandler handler ) {
		handler.onChartClick( this ) ;
	}


	public JavaScriptObject getNativeEvent() {
		return nativeEvent;
	}


	public void setNativeEvent( JavaScriptObject nativeEvent ) {
		this.nativeEvent = nativeEvent;
	}


	public int getSeriesIndex() {
		return seriesIndex;
	}


	public void setSeriesIndex( int seriesIndex ) {
		this.seriesIndex = seriesIndex;
	}


	public int getPointIndex() {
		return pointIndex;
	}


	public void setPointIndex( int pointIndex ) {
		this.pointIndex = pointIndex;
	}


	public JavaScriptObject getData() {
		return data;
	}


	public void setData( JavaScriptObject data ) {
		this.data = data;
	}
	
	
	

}
