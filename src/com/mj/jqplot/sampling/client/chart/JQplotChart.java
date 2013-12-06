package com.mj.jqplot.sampling.client.chart;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;


public abstract class JQplotChart< OPTION extends JQplotChartOptions > implements
		IsWidget, HasJQplotChartClickHandlers, HasJQplotChartPredrawHandlers {

	protected HandlerManager handlerManager ;
	
	protected SimplePanel container;

	protected String id;

	protected OPTION options;

	protected JavaScriptObject chart;

	private final static int DRAW_DELAY = 500;

	public JQplotChart(String id, OPTION options) {
		this.id = id;
		container = new SimplePanel();
		container.ensureDebugId(id);
		DOM.setElementProperty(asWidget().getElement(), "id", id);
		this.options = options;
		
		handlerManager = new HandlerManager( this ) ;
		
	}

	public OPTION getOptions() {
		return options;
	}

	@Override
	public Widget asWidget() {
		return container;
	}

	public abstract void draw();

	public abstract void reset();

	protected void draw(final JsArray<?> data) {
		(new Timer() {
			@Override
			public void run() {
				if (chart == null) {
					try {
						chart = JQplotChart.this.nativeDraw(id, data,
								options.getJavaScriptObject());
						
					} catch ( Exception e ){
						// for unit testing
					}
				} else {
					try {
						JQplotChart.this.nativeRedraw(id, data,
								options.getJavaScriptObject());
						
					} catch ( Exception e ){
						// for unit testing
					}
				}
				
				afterDraw() ;
			}
		}).schedule(DRAW_DELAY);
	}


	protected native JavaScriptObject nativeDraw(String id, JsArray<?> data,
			JavaScriptObject options) /*-{		    
	    
//	    console.log( JSON.stringify( options ) );
	    
		return $wnd.jQuery.jqplot(id, data, options);
	}-*/;

	protected native void nativeRedraw(String id, JsArray<?> data,
			JavaScriptObject options) /*-{
		$wnd.jQuery.jqplot(id, data, options).replot();
	}-*/;

	@Override
	public HandlerRegistration addChartClickHandler( JQplotChartClickHandler h ) {
		try {
			bindClickEvent("#" + id, this );
		} catch ( Exception e ){
			// for unit testing
		}
		return handlerManager.addHandler( JQplotChartClickEvent.TYPE, h ) ;
	}

	private native void bindClickEvent( String id, JQplotChart<?> chart )/*-{
		$wnd.jQuery(id).bind('jqplotDataClick',	function(ev, seriesIndex, pointIndex, data) {
			chart.@com.mj.jqplot.sampling.client.chart.JQplotChart::fireChartClickEvent(*)(ev, seriesIndex, pointIndex, data);
		});
	}-*/;

	public void fireChartClickEvent( JavaScriptObject ev,int seriesIndex,int pointIndex, JavaScriptObject data ){
		JQplotChartClickEvent e = new JQplotChartClickEvent() ;
		
		e.setNativeEvent( ev ) ;
		e.setPointIndex( pointIndex ) ;
		e.setSeriesIndex( seriesIndex ) ;
		e.setData( data ) ;
		
		fireEvent( e ) ;		
	}

	@Override
	public void fireEvent( GwtEvent< ? > event ) {
		handlerManager.fireEvent( event ) ;
	}
		
	protected void afterDraw(){}
	
	protected static native void setCss( String id, String property, String value )/*-{
		$wnd.jQuery( id ).css ( property, value ) ;
	}-*/;

    @Override
    public HandlerRegistration addChartPredrawHandler( JQplotChartPredrawHandler h ) {
        
        try {
            
            bindPredrawEvent (this);
            
        } catch ( Exception e ){
            // for unit testing
        }
        
        return handlerManager.addHandler( JQplotChartPredrawEvent.TYPE, h ) ;
    }
    
	private final native void bindPredrawEvent( JQplotChart<?> chart )/*-{
        
        
        $wnd.jQuery.jqplot.preDrawHooks.push( function(){
                
            var max = this.axes.xaxis._max;
            var min = this.axes.xaxis._min;
            var currentMax = this.axes.xaxis.max;
            var currentMin = this.axes.xaxis.min;
            
            if( max == null || min == null ||isNaN( max ) || isNaN( min ) ){
                return;
            }
            
            if( isNaN( currentMax ) || isNaN( currentMin ) ){
                currentMax = max;
                currentMin = min;
            }
            
            var cur_hours_range = (currentMax - currentMin)/1000/60/60;
            var hours_range = parseFloat(cur_hours_range);   
           
            if( hours_range > 0 ){
                  if( hours_range<1 ){
                      this.axes.xaxis.tickOptions.formatString='%H:%M:%S';
                  }else if( hours_range <24 ){
                      this.axes.xaxis.tickOptions.formatString='%Hh';
                  }else{
                      this.axes.xaxis.tickOptions.formatString='%d/%m';
                  }                    
            }            

            chart.@com.mj.jqplot.sampling.client.chart.JQplotChart::fireChartPredrawEvent(*)( max, min, currentMax, currentMin );
            
        });
    }-*/;
	
   public void fireChartPredrawEvent( double max, double min, double currentMax, double currentMin ){
        
        JQplotChartPredrawEvent e = new JQplotChartPredrawEvent ( max, min, currentMax, currentMin );
        
        fireEvent( e );
    } 
	
   
   
}
