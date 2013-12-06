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

package com.mj.jqplot.sampling.shared;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;


public class JQplotData implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private double value ;
    
    private String date = "";

    public JQplotData () {
    }
    
    public JQplotData (String date, double value) {
        this.date = date;
        this.value = value;
    }

    public double getValue () {
        return value;
    }

    public void setValue (double value) {
        this.value = value;
    }

    public String getDate () {
        DateTimeFormat dateFormat = 
                DateTimeFormat.getFormat( "yyyyMMddHHmmss" );
        Date datetime = dateFormat.parse( date );
        return DateTimeFormat.getFormat( PredefinedFormat.DATE_TIME_FULL ).format( datetime ) ;
    }

    public void setDate (String date) {
        this.date = date;
    }

}
