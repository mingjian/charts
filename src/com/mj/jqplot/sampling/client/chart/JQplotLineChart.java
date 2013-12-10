package com.mj.jqplot.sampling.client.chart;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.JsArray;
import com.mj.jqplot.sampling.client.utils.Utils;


public class JQplotLineChart extends JQplotChart< JQplotLineChartOptions > {

	protected JsArray< JQplotLineChartData > series = JsArray.createArray().cast();
	
	protected ArrayList< String > seriesColors = new ArrayList< String >() ;
	
	protected double maxThreshold, minThreshold;
	 
	static final protected Logger logger = Logger.getLogger(""); 
	
	public JQplotLineChart( String id ) {
		
		super( id, new JQplotLineChartOptions() ) ;
	}

	public void addPlot( String dateStr, int value ){
		
		series.push( JQplotLineChartData.create( dateStr, value ) );
	}
	
	public void addPlot( String dateStr, double value ){
		
		series.push( JQplotLineChartData.create( dateStr, value ) );
	}
	
	public void addPlots(String[] dateStr, double[] value ){
		int i = 0;
		for (; i < value.length; i++) {
			
			series.push( JQplotLineChartData.create( dateStr[i], value[i] ) );
			
		}
		
		//seriesColors.add( hexColor ) ;	
		if ( value.length != 0 ) {

			if( Utils.isLessThanHours( dateStr[0], dateStr[i-1], 1 ) ){
				
				setXaxisRender( "$wnd.jQuery.jqplot.DateAxisRenderer", "%H:%M:%S" );
				
			}else if( Utils.isLessThanHours( dateStr[0], dateStr[i-1], 24 ) ) {
				
				setXaxisRender( "$wnd.jQuery.jqplot.DateAxisRenderer", "%Hh" );	
				
			}else {
				
				setXaxisRender( "$wnd.jQuery.jqplot.DateAxisRenderer", "%d/%m" );
				
			}	
			
		}else {
			
			setXaxisRender( "$wnd.jQuery.jqplot.DateAxisRenderer", "%d/%m" );
			
			series.push( null );
			
		}
	}
	
	@Override
	public void reset() {
		series = JsArray.createArray().cast();
		seriesColors.clear() ;
	}
	
	
	@Override
	public void draw() {
		final JsArray< JsArray< JQplotLineChartData > > data = JsArray.createArray().cast();
		data.push( series ) ;
//		options.set( "seriesColors", seriesColors ) ;
		draw( data ) ;
	}
	

	public void enableAnimation( boolean isAnimate){
		options.set( "animate", isAnimate ) ;
		options.set( "animateReplot", isAnimate );
	}
	
	public void enableZoom( boolean isZoom ){
		options.set( "cursor.show", isZoom ) ;
		options.set( "cursor.zoom", isZoom ) ;
		options.set( "cursor.constrainZoomTo", "x") ;
		addChartPredrawHandler ( new JQplotChartPredrawHandler() {
            
            @Override
            public void onChartPredraw (JQplotChartPredrawEvent event) {                
                
                //current range after zoom
                double hours_range = (event.getCurrentMax ()-event.getCurrentMin ())/1000/60/60;
                if ( logger.isLoggable (Level.FINE) ) {
                    logger.log (Level.FINE, "hours range:" + hours_range );
                    
                }
                
            }
        });
	}
	
	public void enableHighlighter( boolean isHighlighter){
		options.set( "highlighter.show", true ) ;
		options.set( "highlighter.tooltipLocation", "n" ) ;
		options.set( "highlighter.tooltipAxes", "xy" ) ;				
		options.set( "highlighter.sizeAdjust", 1 ) ;
	}
	
	public void enableMarker( boolean isMarker){
		options.set( "seriesDefaults.showMarker", isMarker ) ;
	};
	
	public void setHorizontalThresholds( double maxThreshold, double minThreshold, String maxThresholdTitle, String minThresholdTitle, boolean isShowToolTip ){
		String showTooltip = "false";
		if (isShowToolTip) {
			showTooltip = "true";
		}
		this.maxThreshold = maxThreshold;
		this.minThreshold = minThreshold;
		
		options.set("canvasOverlay.show", true);
		options.setJson("canvasOverlay.objects", 
				"[{ horizontalLine:{ y: "+maxThreshold+", lineWidth: 2, xOffset:0, color: \"#ff0000\", shadow:false, " +
				"showTooltip: "+showTooltip+", tooltipFormatString: \""+maxThresholdTitle+"=%.*s\", showTooltipPrecision: 0.5, tooltipLocation: \"n\"} }," +
				"{ horizontalLine:{ y: "+minThreshold+", lineWidth: 2, xOffset:0, color: \"#ff0000\", shadow:false, " +
				"showTooltip: "+showTooltip+", tooltipFormatString: \""+minThresholdTitle+"=%.*s\", showTooltipPrecision: 0.5, tooltipLocation: \"n\"} }]");
		
		double margin = (maxThreshold - minThreshold)*0.3 ;				
		options.set( "axes.yaxis.max", maxThreshold + margin ) ;
		options.set( "axes.yaxis.min", minThreshold - margin ) ;
	}
	
	
	
	public void setTitle( String title ){
		options.set( "title", "<span style=\"top: 14px; position:absolute; z-index:100;" +
				"padding: 2px 15px;border: 1px solid #ddd;background-color: #fff;border-radius: 5px;" +
				"box-shadow: 0px 0px 10px 0px #dddddd;\">"+title+"</span>");

	}
	
	public void setYaxisFormat( String format ){
		options.set( "axes.yaxis.tickOptions.formatString", format );
	}
	
	public void setDimension( String height, String width ){

		asWidget().setHeight( height );
		asWidget().setWidth( width );
		
	}
	
	public void setXaxisRender( String render, String format ){
		options.set( "axes.xaxis.renderer", render, true) ;				
		options.set( "axes.xaxis.tickOptions.formatString", format ) ;
		options.set( "axes.xaxis.pad", 2 ) ;
	}
	
	public void setTickRenderAngle( int angle ){
		options.set( "axesDefaults.tickOptions.fontSize", "10px" ) ;
		options.set( "axesDefaults.tickOptions.fontFamily", "arial") ;
		options.set( "axesDefaults.tickRenderer", "$wnd.jQuery.jqplot.CanvasAxisTickRenderer", true ) ;		
		options.set( "axesDefaults.tickOptions.angle", angle ) ;
		
	}

	public void pack( double[] values ) {
	    
		if ( values.length == 0 ) {
			return;
		}
		
		double maxValue = values[0], minValue = values[0];
		
		for (int i = 1; i < values.length; i++) {		
		
			if ( values[i] > values[i-1] && values[i] > maxValue ) {
				
				maxValue = values[i];
				
			}else if ( values[i] < values[i-1] && values[i-1] > maxValue ){
				
				maxValue = values[i-1];
				
			}				
			
			if ( values[i] < values[i-1] && values[i] < minValue ) {
				
				minValue = values[i];
				
			}else if ( values[i-1] < values[i] && values[i-1] < minValue ){
				
				minValue = values[i-1];
				
			}	
		}		
		
		if (  minThreshold < maxThreshold ) {
		    
		    double min = minThreshold, max = maxThreshold;

		    double margin = (max - min)*0.3;
			
			if ( values.length == 0 ) {	
				
				options.set( "axes.yaxis.max", max + margin ) ;
				
				options.set( "axes.yaxis.min", min - margin ) ;
				
			}else {

			    if (maxValue > max) {
                    max = maxValue;
                }
			    
			    if (minValue < min) {
                    min = minValue;
                }
			    
			    margin = (max - min)*0.3;
			    
				options.set( "axes.yaxis.max", max + margin ) ;
				
				options.set( "axes.yaxis.min", min - margin ) ;

				if (logger.isLoggable (Level.FINE)) {
				    logger.log( Level.FINE, "Max bolder:" +  (max + margin) + ",   Min bolder:" +  (min - margin) );
				    
				}
			}
			
		} else {
			
			double margin = (maxValue - minValue) * 0.3;
			
			if (margin == 0) {
			    
			    margin = maxValue > 0? (maxValue - 0)* 0.3 : (0 - maxValue)* 0.3;		    
			    
            }
			
			options.set( "axes.yaxis.max", maxValue + margin ) ;
			
			options.set( "axes.yaxis.min", minValue - margin ) ;
			
			if (logger.isLoggable (Level.FINE)) {			    
			    logger.log( Level.FINE, "Max bolder:" +  ( maxValue + margin ) + ",   Min bolder:" +  ( minValue - margin ) );			               
			}                
		}
	}


  
}
