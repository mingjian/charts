package com.mj.jqplot.sampling.client.chart;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsonUtils;


public abstract class JQplotChartOptions {
	
	protected String json ;
	
	protected JQPlotOptionsMap optionsMap ;
	

	public JQplotChartOptions() {
		optionsMap = new JQPlotOptionsMap() ;
	}

	
	public JQplotChartOptions( String json ) {
		this.json = json ;
	}
	
	
	public JavaScriptObject getJavaScriptObject(){
		if( json != null ){
			
			return JsonUtils.unsafeEval( json ) ;
		
		} else {
			
			StringBuilder sb = new StringBuilder() ;
			
			sb.append( "{ " ) ;
			
			Iterator< JQplotAbstractChartOption< ? > > entities = optionsMap.list().iterator() ;
			
			JQplotAbstractChartOption< ? > entity ;
			
			while ( entities.hasNext() ){
			
				entity = entities.next() ;
				sb.append( entity.asJson() ) ;
				if( entities.hasNext() ) sb.append( "," ) ;				
			
			}
			
			sb.append ( "}" ) ;
			
//			System.out.println (sb.toString ());
			
			return JsonUtils.unsafeEval( sb.toString() ) ;
		}
		
	}
	
	public JQplotAbstractChartOption< ? > getOption( String path ){
		
	    if ( !path.contains( "." ) ){
			 
			return optionsMap.get( path );
		
		} else {
			
			JQplotChartOptionObject parent = lookupOrCreateParentOf( path , optionsMap  ) ;
			return parent.get( path.substring( path.lastIndexOf(".") + 1 )  ) ;
			
		}
	}
	
	
	public JQplotAbstractChartOption< ? > get( String key ){
		return optionsMap.get( key ) ;
	}
	
	public void set( String path, boolean value ){
		if ( !path.contains( "." ) ){
			
			add ( new JQplotChartOptionBoolean( path, value ) ) ;
		
		} else {
			
			JQplotChartOptionObject parent = lookupOrCreateParentOf( path , optionsMap  ) ;
			parent.add( new JQplotChartOptionBoolean( 
					path.substring( path.lastIndexOf(".") + 1 ) , value ) ) ;
			
		}
	}
	

	public void set( String path, String value ){
	    
		if ( !path.contains( "." ) ){
			
			add ( new JQplotChartOptionString( path , value, false ) ) ;
		
		} else {
			
			JQplotChartOptionObject parent = lookupOrCreateParentOf( path , optionsMap  ) ;
			parent.add( new JQplotChartOptionString( 
					path.substring( path.lastIndexOf(".") + 1 ) , value, false  ) ) ;
			
		}
	}
	
	public void set( String path, String value, boolean isRef ){
		if ( !path.contains( "." ) ){
			
			add ( new JQplotChartOptionString( path , value, isRef ) ) ;
		
		} else {
			
			JQplotChartOptionObject parent = lookupOrCreateParentOf( path , optionsMap  ) ;
			parent.add( new JQplotChartOptionString( 
					path.substring( path.lastIndexOf(".") + 1 ) , value, isRef  ) ) ;
			
		}
	}

	
	public void set( String path, int value ){
		if ( !path.contains( "." ) ){
			
			add ( new JQplotChartOptionNumber( path , value ) ) ;
		
		} else {
			
			JQplotChartOptionObject parent = lookupOrCreateParentOf( path , optionsMap  ) ;
			parent.add( new JQplotChartOptionNumber( path.substring( path.lastIndexOf(".") + 1 ) , value ) ) ;
			
		}
	}
		
	public void set( String path, double value ){
		if ( !path.contains( "." ) ){
			
			add ( new JQplotChartOptionNumber( path , value ) ) ;
		
		} else {
			
			JQplotChartOptionObject parent = lookupOrCreateParentOf( path , optionsMap  ) ;
			parent.add( new JQplotChartOptionNumber( path.substring( path.lastIndexOf(".") + 1 ) , value ) ) ;
			
		}
	}
	
	public void setJson( String path, String json ){
		if ( !path.contains( "." ) ){
			
			add ( new JQplotChartOptionJson( path , json ) ) ;
		
		} else {
			
			JQplotChartOptionObject parent = lookupOrCreateParentOf( path , optionsMap  ) ;
			parent.add( new JQplotChartOptionJson( 
					path.substring( path.lastIndexOf(".") + 1 ) , json ) ) ;
			
		}
	}
	
	public void set( String path, ArrayList< String > values ){
		if ( !path.contains( "." ) ){
			
			add ( new JQplotChartOptionStringArray( path , values ) ) ;
		
		} else {
			
			JQplotChartOptionObject parent = lookupOrCreateParentOf( path , optionsMap  ) ;
			parent.add( new JQplotChartOptionStringArray( 
					path.substring( path.lastIndexOf(".") + 1 ) , values ) ) ;
			
		}				
	}
	
	public void setObjects( String path, ArrayList<JQplotChartOptionObject> objects){
	    if ( !path.contains( "." ) ){
            
            add ( new JQplotChartOptionObjects( path, objects ) ) ;
        
        } else {
            
            JQplotChartOptionObject parent = lookupOrCreateParentOf( path , optionsMap  ) ;
            parent.add( new JQplotChartOptionObjects( 
                    path.substring( path.lastIndexOf(".") + 1 ) , objects )) ;
            
        }       
	}
	
	
	JQplotChartOptionObject lookupOrCreateParentOf( String path, JQPlotOptionsMap map ){
		
		String[] tokens = path.split( "\\." ) ; 
		
		JQplotChartOptionObject option = ( JQplotChartOptionObject ) map.get( tokens [ 0 ] ) ;
		
		if ( option == null ) {
			option = new JQplotChartOptionObject( tokens [ 0 ] ) ;
			map.add( option ) ;
		}
		
		path = path.substring( path.indexOf( "." ) + 1 ) ;
		
		if ( !path.contains( "." ) ) return option ;
		
		return lookupOrCreateParentOf( path , option.getValue() ) ;

	}

	
	
	void add ( JQplotAbstractChartOption< ? > options ){
		optionsMap.add( options ) ;
	}
	

}
