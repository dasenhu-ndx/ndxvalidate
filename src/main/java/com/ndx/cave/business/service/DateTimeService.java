package com.ndx.cave.business.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DateTimeService {


    Date currentDate = new Date();

    public Date getCurrentDate(){


        return currentDate;
    }

}
