package com.ndx.ndxvalidate.data.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Ndx_Validate_LMS_Team")
@Getter
@Setter
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MemberId")
    private Long adminId;

    @Column(name = "FirstName")
    private String fName;

    @Column(name = "LastName")
    private String lName;

    @Column(name = "Email")
    private String email;

    @Column(name = "MTUserName")
    private String mtUserName;

    @Column(name = "RoleType")
    private String role;

    @Column(name = "Status")
    private int status;

}
