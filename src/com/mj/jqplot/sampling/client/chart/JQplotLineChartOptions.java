package com.mj.jqplot.sampling.client.chart;




public class JQplotLineChartOptions extends JQplotChartOptions {

	JQplotLineChartOptions() {
		set( "grid.background", "#F7F8E0" ) ;
		set( "grid.shadow", false  ) ;
		set( "grid.borderWidth", 0 ) ;
//		set( "grid.gridLineWidth", 2 );
//		set( "grid.drawGridlines", true );
//		set( "gridLineColor", "rgb(235,235,235)" );
		set( "seriesDefaults.rendererOptions.smooth", true );
	}
	
}
