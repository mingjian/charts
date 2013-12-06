
package com.mj.jqplot.sampling.client.chart;


public class JQplotChartThresholds {
    
    private double minThreshold, maxThreshold;
    
    private String color;
    
    public JQplotChartThresholds () {
    
    }

    public double getMaxThreshold () {
        return maxThreshold;
    }

    public void setMaxThreshold (double maxThreshold) {
        this.maxThreshold = maxThreshold;
    }

    public double getMinThreshold () {
        return minThreshold;
    }

    public void setMinThreshold (double minThreshold) {
        this.minThreshold = minThreshold;
    }

    public String getColor () {
        return color;
    }

    public void setColor (String color) {
        this.color = color;
    }
    
    
    
}
