package com.mj.jqplot.sampling.client.chart;

import com.google.gwt.event.shared.GwtEvent;

public class JQplotChartPredrawEvent extends GwtEvent<JQplotChartPredrawHandler> {

    public static Type<JQplotChartPredrawHandler> TYPE = new Type<JQplotChartPredrawHandler> ();

    private double max;

    private double min;

    private double currentMax;

    private double currentMin;


    public JQplotChartPredrawEvent (double max, double min, double currentMax, double currentMin) {
        this.max = max;
        this.min = min;
        this.currentMax = currentMax;
        this.currentMin = currentMin;
    }


    @Override
    public com.google.gwt.event.shared.GwtEvent.Type<JQplotChartPredrawHandler> getAssociatedType () {

        return TYPE;
    }


    @Override
    protected void dispatch (JQplotChartPredrawHandler handler) {
        handler.onChartPredraw (this);
    }


    public double getMax () {
        return max;
    }


    public void setMax (double max) {
        this.max = max;
    }


    public double getMin () {
        return min;
    }


    public void setMin (double min) {
        this.min = min;
    }


    public double getCurrentMax () {
        return currentMax;
    }


    public void setCurrentMax (double currentMax) {
        this.currentMax = currentMax;
    }


    public double getCurrentMin () {
        return currentMin;
    }


    public void setCurrentMin (double currentMin) {
        this.currentMin = currentMin;
    }

}
