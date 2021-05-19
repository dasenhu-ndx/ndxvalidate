package com.ndx.ndxvalidate.data.sp_access;


public interface CheckExistingCustomer {

    String getWarning();
    String getCustomerID();
    String getAddress1();
    String getZipCode();
    String getOfficePhone();
    String getCity();
    String getState();
    String getDentalGroup();
    String getPracticeName();
    String getCreatedBy();
    String getCreateDate();


}
