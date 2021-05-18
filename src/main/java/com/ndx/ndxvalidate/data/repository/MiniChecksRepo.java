package com.ndx.ndxvalidate.data.repository;

import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.sp_access.CheckExistingCustomer;
import com.ndx.ndxvalidate.data.sp_access.CheckExistingDoctor;
import com.ndx.ndxvalidate.data.sp_access.CheckSimilarCustomer;
import com.ndx.ndxvalidate.data.sp_access.CheckSimilarDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiniChecksRepo extends JpaRepository<AccountRequest, Long> {

    @Query(nativeQuery = true, value = "EXEC [NDXData].[dbo].[SP_NDXValidateCheckExistingCustomer]" +
            "@LabName = ?1" +
            ",@Address1 = ?2" +
            ",@ExistingCustomerID = ?3" +
            ",@OfficePhone = ?4")
    List<CheckExistingCustomer> getExistingCustomer(String labName, String add1, String customerId, String phone);

    @Query(nativeQuery = true, value = "EXEC [NDXData].[dbo].[SP_NDXValidateCheckExistingDoctor]" +
            "@LabName = ?1" +
            ",@ExistingDoctorNumber = ?2" +
            ",@LastName = ?3" +
            ",@LicenseNumber = ?4" +
            ",@NPINumber = ?5")
    List<CheckExistingDoctor> getExistingDoctor(String labName, String docId, String lName,String licenceNo, String npi);

    @Query(nativeQuery = true, value = "EXEC  [NDXData].[dbo].[SP_NDXValidateCheckSimilarCustomer] " +
            "@LabName = ?1" +
            ",@Address1 = ?2" +
            ",@ZipCode = ?3" +
            ",@OfficePhone = ?4")
    List<CheckSimilarCustomer> getSimilarCustomer(String labName, String add1, String zip, String phone);

    @Query(nativeQuery = true, value = "EXEC [NDXData].[dbo].[SP_NDXValidateCheckSimilarDoctor]" +
            "@LabName = ?1" +
            ",@FirstName = ?2" +
            ",@LastName = ?3" +
            ",@LicenseNumber = ?4" +
            ",@NPINumber = ?5" +
            ",@Address1 = ?6" +
            ",@OfficePhone = ?7"
    )
    List<CheckSimilarDoctor> getSimilarDoctor(String labName, String fName, String lName, String licenceNo, String npi,
                                              String add1, String phone);


}
