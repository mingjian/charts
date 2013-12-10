
package com.mj.jqplot.sampling.client.chart;

import java.util.ArrayList;
import java.util.Iterator;


public class JQplotChartOptionObjects extends JQplotAbstractChartOption<ArrayList<JQplotChartOptionObject>> {

    public JQplotChartOptionObjects (String key) {
        super (key, new ArrayList<JQplotChartOptionObject> ());
    }
    
    public JQplotChartOptionObjects (String key, ArrayList<JQplotChartOptionObject> objects) {
        super (key, objects);
    }
    
    void add( JQplotChartOptionObject obj ){
        value.add (obj);
    }
    
    ArrayList<JQplotChartOptionObject> toValues(){
        return value;
    }
    
    @Override
    protected String asJson () {
        
        StringBuilder sb = new StringBuilder ();
        
        sb.append (key).append (": [");
        
        Iterator<JQplotChartOptionObject> objectIterator = value.iterator ();
        
        JQplotChartOptionObject object;
        
        while (objectIterator.hasNext () ) {
            
            object = objectIterator.next ();
            sb.append ("{" + object.asJson () + "}");
            if (objectIterator.hasNext ()) {
                sb.append (",");
            }
        }
        
        sb.append ("]");
        
        return sb.toString ();
    }
}
