package com.ndx.ndxvalidate.business.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class DateTimeService {


    Date currentDate = new Date();

    public Date getCurrentDate(){


        return currentDate;
    }

}
