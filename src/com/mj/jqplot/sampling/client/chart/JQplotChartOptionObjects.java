
package com.mj.jqplot.sampling.client.chart;

import java.util.ArrayList;
import java.util.Collection;


public class JQplotChartOptionObjects extends JQplotAbstractChartOption<Collection<JQplotChartOptionObject>> {

    public JQplotChartOptionObjects (String key) {
        super (key, new ArrayList<JQplotChartOptionObject> ());
    }
    
    void add( JQplotChartOptionObject obj ){
        value.add (obj);
    }
    
    Collection<JQplotChartOptionObject> toValues(){
        return value;
    }
    
    @Override
    protected String asJson () {
        
        StringBuilder sb = new StringBuilder ();
        
        sb.append (key).append (": [{");
        
        for ( JQplotChartOptionObject obj: value ) {
            
            sb.append ( obj.asJson () );
            
            sb.append (",");
        }
        
        sb.append ("}]");
        
        return sb.toString ();
    }
}
