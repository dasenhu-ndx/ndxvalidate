package com.ndx.ndxvalidate.data.sp_access;

import lombok.Getter;
import lombok.Setter;


public interface PracticeDoc {

     String getWarning();
     String getDoctorNumber();
     String getFirstName();
     String getLastName();
     String getLicenceNumber();
     String getNpiNumber();
     String getCustomerID();

}
