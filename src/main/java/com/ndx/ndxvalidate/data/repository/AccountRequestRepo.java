package com.ndx.ndxvalidate.data.repository;


import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.sp_access.LabUserPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public interface AccountRequestRepo extends JpaRepository<AccountRequest, Long> {

    List<AccountRequest> findAccountRequestsByLabNameInAndStatus(List<String> labName, int status);



    @Query("select ar.labName from AccountRequest ar where ar.mtUserName = ?1  ")
    List<String> findLabs(String mtUserName);



    AccountRequest findAccountRequestsByAccId(Long id);
    List<AccountRequest> findAllByLms(int lms);


    List<AccountRequest> findAccountRequestsByStatus(int status);

    @Query("UPDATE AccountRequest  ar set ar.dbName = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateDBName(String dbName, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.labName = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateLabName(String labName, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.mode = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateMode(int mode, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.isRush = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateRush(String isRush, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.docId = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateDocId(String docId, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.customerId = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateCustomerId(String customerId, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.add1 = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateAdd1(String add1, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.add2 = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateAdd2(String add2, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.city = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateCity(String city, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.state = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateState(String state, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.zip = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateZip(String zip, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.fName = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateFName(String fName, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.lName = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateLName(String lName, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.email = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateEmail(String email, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.phone = ?1 where ar.accId = ?2 ")
    @Modifying
    void updatePhone(String phone, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.licenseNo = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateLicenseNo(String licenseNo, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.npi = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateNPI(String npi, Long accId);


    @Query("UPDATE AccountRequest  ar set ar.notes = ?1 where ar.accId = ?2 ")
    @Modifying
    void updateNotes(String notes, Long accId);


}
