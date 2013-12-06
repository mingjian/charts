package com.mj.jqplot.sampling.client.chart;

import com.google.gwt.event.shared.EventHandler;


public interface JQplotChartPredrawHandler extends EventHandler {
    
        void onChartPredraw( JQplotChartPredrawEvent event );
}
