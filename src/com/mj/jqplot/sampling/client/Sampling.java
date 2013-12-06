package com.mj.jqplot.sampling.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.mj.jqplot.sampling.client.chart.JQplotChartPredrawEvent;
import com.mj.jqplot.sampling.client.chart.JQplotChartPredrawHandler;
import com.mj.jqplot.sampling.client.chart.JQplotLineChart;
import com.mj.jqplot.sampling.client.chart.JQplotLineChartOptions;
import com.mj.jqplot.sampling.shared.JQplotData;

public class Sampling implements EntryPoint {

    private static Logger logger= Logger.getLogger ("");
    
    public void onModuleLoad () {
        
        init();
     
    }
    
    public void init(){
                
                
        List<JQplotData> list = new ArrayList<JQplotData> ();        
        list.add (new JQplotData ("20131028000000", 38));
        list.add (new JQplotData ("20131027000000", 28));
        list.add (new JQplotData ("20131026000000", 20));
        list.add (new JQplotData ("20131025000000", 32));
        list.add (new JQplotData ("20131024000000", 33));
        list.add (new JQplotData ("20131023000000", 41));
        

        JQplotLineChart lineChart = new JQplotLineChart ("Sampling");
        FlowPanel mainDiv = new FlowPanel ();
        Label label = new Label ("Chart:");
        mainDiv.add (label);
        mainDiv.add (lineChart);
        RootPanel.get ().add ( mainDiv );
        
        
        lineChart.asWidget().setWidth( "340px" ) ;
        lineChart.asWidget().setHeight( "280px" ) ;        
        JQplotLineChartOptions options = lineChart.getOptions() ;        
        options.set( "title", "<span style=\"top: 5px; left: 100px; right: 100px; position:absolute;" +
                "z-index:100; padding: 2px 15px;border: 1px solid #ddd;background-color: #fff;" +
                "border-radius: 5px; box-shadow: 0px 0px 10px 0px #dddddd;\">" + "Sampling Chart" + "</span>");
        
        options.set( "seriesDefaults.markerOptions.show", true) ;
        options.set( "seriesDefaults.markerOptions.style", "filledCircle") ;
        options.set( "seriesDefaults.markerOptions.size", 5) ;
        
        options.set( "axesDefaults.tickOptions.fontSize", "10px" ) ;
        options.set( "axesDefaults.tickOptions.fontFamily", "arial") ;
        options.set( "axesDefaults.tickOptions.mark", "inside" ) ;

        options.set( "axes.xaxis.renderer", "$wnd.jQuery.jqplot.DateAxisRenderer", true) ;
        options.set( "axes.xaxis.pad", "1.5" ) ;
        options.set( "axes.yaxis.tickOptions.formatString", "%.1f" ) ;
        
        options.set( "highlighter.show", true ) ;
        options.set( "highlighter.tooltipLocation", "n" ) ;
        options.set( "highlighter.tooltipAxes", "xy" ) ;                
        options.set( "highlighter.sizeAdjust", 1 ) ;
        
        options.set( "cursor.show", true) ;
        options.set( "cursor.zoom", true) ;
        options.set( "cursor.constrainZoomTo", "x") ;
        lineChart.addChartPredrawHandler ( new JQplotChartPredrawHandler() {
            
            @Override
            public void onChartPredraw (JQplotChartPredrawEvent event) {                
                
                //current range after zoom
                double hours_range = (event.getCurrentMax ()-event.getCurrentMin ())/1000/60/60;
                logger.log (Level.INFO, "hours range:" + hours_range );
                    
            }
        });
        options.set( "animate", true) ;
        options.set( "animateReplot", true) ;
        
        double[] values = new double[ list.size () ];
        String[] dates = new String[ list.size () ];
        
        int i = 0;
        for (JQplotData data: list) {
            values[i] = data.getValue ();
            dates[i++] = data.getDate ();
            logger.log (Level.INFO,"Date:  " + dates[i-1]);
        }
        lineChart.setHorizontalThresholds (35, 18, "Max", "Min", true);
        lineChart.reset() ;
        lineChart.addPlots( dates, values ) ;
        lineChart.pack( values );
        lineChart.draw() ;
        
        
        
    }
    
}
