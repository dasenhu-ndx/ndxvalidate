package com.ndx.ndxvalidate.data.repository;


import com.ndx.ndxvalidate.data.entity.AccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.List;

public interface AccountRequestRepo extends JpaRepository<AccountRequest, Long> {


    List<AccountRequest> findAccountRequestsByMtUserName(String mtUserName);
    List<AccountRequest> findAccountRequestsByMtUserNameAndStatus(String mtUserName, int status);
    AccountRequest findAccountRequestsByAccId(Long id);
   List<AccountRequest> findAllByLms(int lms);

    List<AccountRequest> findAccountRequestsByStatus(int status);

    @Query("UPDATE AccountRequest  ar set ar.labName = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateLabName(String labName, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.mode = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateMode(int mode, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.isRush = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateRush(String isRush, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.docId = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateDocId(String docId, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.customerId = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateCustomerId(String customerId, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.add1 = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateAdd1(String add1, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.add2 = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateAdd2(String add2, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.city = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateCity(String city, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.state = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateState(String state, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.zip = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateZip(String zip, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.fName = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateFName(String fName, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.lName = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateLName(String lName, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.email = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateEmail(String email, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.phone = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updatePhone(String phone, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.licenceNo = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateLicenceNo(String licenceNo, Long accId);

    @Query("UPDATE AccountRequest  ar set ar.npi = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateNPI(String npi, Long accId);


    @Query("UPDATE AccountRequest  ar set ar.notes = ?1 where ar.accId = ?2 ")
    @Modifying
    public void updateNotes(String notes, Long accId);


}
