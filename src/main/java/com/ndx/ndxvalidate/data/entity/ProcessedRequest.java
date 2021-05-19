package com.ndx.ndxvalidate.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Ndx_Validate_Account_Processed")
@Getter
@Setter
public class ProcessedRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProcessedID")
    private Long pId;

    @CreatedDate
    @Column(name = "TimeStamp")
    private Date  timeStamp;

    @OneToOne
    @JoinColumn(name = "RequestID")
    private AccountRequest accountRequest;

    @Column(name = "DoctorID")
    private String newDocId;

    @Column(name = "CustomerID")
    private String newCusId;

    @Column(name = "EDoctorID")
    private String eDocId;

    @Column(name = "ECustomerID")
    private String eCusId;

    @Column(name = "ProcessedBy")
    private String procBy;



}
