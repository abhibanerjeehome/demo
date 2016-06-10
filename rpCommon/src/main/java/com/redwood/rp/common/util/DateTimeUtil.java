package com.redwood.rp.common.util;

import java.sql.Timestamp;

import javax.inject.Named;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@Named
public class DateTimeUtil extends XmlAdapter<java.util.Date, java.sql.Timestamp> {

    public java.util.Date marshal(Timestamp sqlDate) throws Exception {
        if(null == sqlDate) {
            return null;
        }
        return new java.util.Date(sqlDate.getTime());
    }

    @Override
    public Timestamp unmarshal(java.util.Date utilDate) throws Exception {
        if(null == utilDate) {
            return null;
        }
        return new java.sql.Timestamp(utilDate.getTime());
    }

}