
package com.mj.jqplot.sampling.client.chart;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasJQplotChartClickHandlers extends HasHandlers {

	HandlerRegistration addChartClickHandler ( JQplotChartClickHandler h ) ;
	

}
