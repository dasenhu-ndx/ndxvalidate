package com.ndx.ndxvalidate.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "NDX_Validate_TestTable")
@Getter
@Setter
public class TestEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TestName")
    private String testName;

    @Column(name = "LabTestNote")
    private String labTestNote;

    @Column(name = "Doctor")
    private String doctor;

    @Column(name = "Customer")
    private String customer;

    @Column(name = "Mode")
    private int mode;





}
