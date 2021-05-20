package com.ndx.ndxvalidate.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Ndx_Validate_Account_Request")
@Getter
@Setter
public class AccountRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountReqId")
    private Long accId;

    @Column(name = "TimeStamp")
    @CreatedDate
    private Date timeStamp;

    @Column(name = "RequestMode")
    private int mode;

    @Column(name = "LabName")
    private String labName;

    @Column(name = "IsRush")
    private String isRush;

    @Column(name = "MT_UserName")
    private String mtUserName;

    @Column(name = "DoctorID")
    private String docId;

    @Column(name = "CustomerID")
    private String customerId;

    @Column(name = "Address1" )
    private String add1;

    @Column(name = "Address2")
    private String add2;

    @Column(name = "City")
    private String city;

    @Column(name = "State")
    private String state;

    @Column(name = "Zip")
    private String zip;

    @Column(name = "FirstName")
    private String fName;

    @Column(name = "LastName")
    private String lName;

    @Column(name = "Email")
    private String email;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "LicenceNumber")
    private String licenceNo;

    @Column(name = "NPINumber")
    private String npi;

    @Column(name = "Notes")
    private String  notes;

    @Column(name = "Status")
    private int status;

    @Column(name = "DentalGroup")
    private String dGroup;

    @Column(name = "PracticeName")
    private String pName;

    @Column(name = "DHS")
    private int dhs;

    @Column(name = "LMSStatus")
    private int lms;
}
