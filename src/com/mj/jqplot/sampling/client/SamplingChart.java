//-------------------------------------------------------------------------
// Copyright (c) 2006-2013 GEMALTO group. All Rights Reserved.
//
// This software is the confidential and proprietary information of
// GEMALTO.
//
//  Project name:  Sensorlogic Admin Portal
//
//  Platform    :  Java virtual machine
//  Language    :  JAVA 6.0
//
//  Original author: mingli
//
//-------------------------------------------------------------------------
// GEMALTO MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
// THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
// TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
// PARTICULAR PURPOSE, OR NON-INFRINGEMENT. GEMALTO SHALL NOT BE
// LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
// MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
//
// THIS SOFTWARE IS NOT DESIGNED OR INTENDED FOR USE OR RESALE AS ON-LINE
// CONTROL EQUIPMENT IN HAZARDOUS ENVIRONMENTS REQUIRING FAIL-SAFE
// PERFORMANCE, SUCH AS IN THE OPERATION OF NUCLEAR FACILITIES, AIRCRAFT
// NAVIGATION OR COMMUNICATION SYSTEMS, AIR TRAFFIC CONTROL, DIRECT LIFE
// SUPPORT MACHINES, OR WEAPONS SYSTEMS, IN WHICH THE FAILURE OF THE
// SOFTWARE COULD LEAD DIRECTLY TO DEATH, PERSONAL INJURY, OR SEVERE
// PHYSICAL OR ENVIRONMENTAL DAMAGE ("HIGH RISK ACTIVITIES"). GEMALTO
// SPECIFICALLY DISCLAIMS ANY EXPRESS OR IMPLIED WARRANTY OF FITNESS FOR
// HIGH RISK ACTIVITIES.
//-------------------------------------------------------------------------

package com.mj.jqplot.sampling.client;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.AbstractHasData;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;
import com.mj.jqplot.sampling.client.chart.JQplotChartPredrawEvent;
import com.mj.jqplot.sampling.client.chart.JQplotChartPredrawHandler;
import com.mj.jqplot.sampling.client.chart.JQplotLineChart;
import com.mj.jqplot.sampling.client.chart.JQplotLineChartOptions;
import com.mj.jqplot.sampling.shared.JQplotData;

public class SamplingChart extends AbstractHasData<JQplotData>{

    protected static Logger logger = Logger.getLogger ("");
    
    //Jqplot chart data sampling.    
    private JQplotLineChart lineChart ;    
    
    private String[] dates;
    
    private double[] values;
    
    public SamplingChart (String id) {
        super( Document.get().createDivElement() , 1000,  new ProvidesKey<JQplotData>() {
            public Object getKey( JQplotData data ) {
                return data.getDate ();
            }
        } ) ;
        
        getElement().setId( id ) ;
        
        lineChart = new JQplotLineChart( id ) ;
        
        lineChart.asWidget().setWidth( "340px" ) ;
        lineChart.asWidget().setHeight( "280px" ) ;
        
        JQplotLineChartOptions options = lineChart.getOptions() ;
        
        options.set( "title", "<span style=\"top: 20px; left: 100px; right: 100px; position:absolute;" +
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
    }

    /**
     * Method documentation to be filled
     * TODO
     *
     * @return
     */
    @Override
    protected boolean dependsOnSelection () {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Method documentation to be filled
     * TODO
     *
     * @return
     */
    @Override
    protected Element getChildContainer() {
        return lineChart.asWidget().getElement() ;
    }



    /**
     * Method documentation to be filled
     * TODO
     *
     * @return
     */
    @Override
    protected Element getKeyboardSelectedElement () {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Method documentation to be filled
     * TODO
     *
     * @return
     */
    @Override
    protected boolean isKeyboardNavigationSuppressed () {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Method documentation to be filled
     * TODO
     *
     * @param sb
     * @param datas
     * @param start
     * @param selectionModel
     */
    @Override
    protected void renderRowValues (SafeHtmlBuilder sb, List<JQplotData> datas, int start,
            SelectionModel<? super JQplotData> selectionModel) {
        
        logger.log (Level.INFO, "Rendering the chart");
        
        values = new double[ datas.size () ];
        dates = new String[ datas.size () ];
        
        int i = 0;
        for (JQplotData data: datas) {
            values[i] = data.getValue ();
            dates[i++] = data.getDate ();
        }
        
        lineChart.reset() ;
        lineChart.addPlots( dates, values ) ;
        lineChart.pack( values );
        lineChart.draw() ;
        
        
    }

    /**
     * Method documentation to be filled
     * TODO
     *
     * @return
     */
    @Override
    protected boolean resetFocusOnCell () {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Method documentation to be filled
     * TODO
     *
     * @param index
     * @param selected
     * @param stealFocus
     */
    @Override
    protected void setKeyboardSelected (int index, boolean selected, boolean stealFocus) {
        // TODO Auto-generated method stub
        
    }
}
