package com.ndx.cave.data.repository;

import com.ndx.cave.data.entity.AccountRequest;
import com.ndx.cave.data.sp_access.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiniChecksRepo extends JpaRepository<AccountRequest, Long> {



    @Query(nativeQuery = true, value = "EXEC [NDXData].[dbo].[SP_CAVE_CheckExistingCustomer]" +
            "@LabName = ?1" +
            ",@Address1 = ?2" +
            ",@ExistingCustomerID = ?3" +
            ",@OfficePhone = ?4")
    List<CheckExistingCustomer> getExistingCustomer(String labName, String add1, String customerId, String phone);

    @Query(nativeQuery = true, value = "EXEC [NDXData].[dbo].[SP_CAVE_CheckExistingDoctor]" +
            "@LabName = ?1" +
            ",@ExistingDoctorNumber = ?2" +
            ",@LastName = ?3" +
            ",@LicenseNumber = ?4" +
            ",@NPINumber = ?5")
    List<CheckExistingDoctor> getExistingDoctor(String labName, String docId, String lName,String licenseNo, String npi);

    @Query(nativeQuery = true, value = "EXEC  [NDXData].[dbo].[SP_CAVE_CheckSimilarCustomer] " +
            "@LabName = ?1" +
            ",@Address1 = ?2" +
            ",@ZipCode = ?3" +
            ",@OfficePhone = ?4")
    List<CheckSimilarCustomer> getSimilarCustomer(String labName, String add1, String zip, String phone);

    @Query(nativeQuery = true, value = "EXEC [NDXData].[dbo].[SP_CAVE_CheckSimilarDoctor]" +
            "@LabName = ?1" +
            ",@FirstName = ?2" +
            ",@LastName = ?3" +
            ",@LicenseNumber = ?4" +
            ",@NPINumber = ?5" +
            ",@Address1 = ?6" +
            ",@OfficePhone = ?7"
    )
    List<CheckSimilarDoctor> getSimilarDoctor(String labName, String fName, String lName, String licenseNo, String npi,
                                              String add1, String phone);

// Start form check endpoints here

    @Query(nativeQuery = true, value = "EXEC [NDXData].[dbo].[SP_CAVE_CheckExistingCustomer]" +
            "@LabName = ?1" +
            ",@Address1 = ''" +
            ",@ExistingCustomerID = ?2" +
            ",@OfficePhone = ''")
    CheckExistingCustomer getExistingCustomerByCustomerId(String labName, String customerId);

    @Query(nativeQuery = true, value = "EXEC [NDXData].[dbo].[SP_CAVE_CheckExistingCustomer]" +
            "@LabName = ?1" +
            ",@Address1 = ''" +
            ",@ExistingCustomerID = ?2" +
            ",@OfficePhone = ?3")
    CheckExistingCustomer getExistingCustomerByCustomerIdAndPhone(String labName, String customerId, String phone);


    @Query(nativeQuery = true, value = "EXEC  [NDXData].[dbo].[SP_CAVE_CheckSimilarCustomer] " +
            "@LabName = ?1" +
            ",@Address1 = ?2" +
            ",@ZipCode = ''" +
            ",@OfficePhone = ''")
    List<CheckSimilarCustomer> getSimilarCustomerByAddress(String labName, String add1);

    @Query(nativeQuery = true, value = "EXEC  [NDXData].[dbo].[SP_CAVE_CheckSimilarCustomer] " +
            "@LabName = ?1" +
            ",@Address1 = ?2" +
            ",@ZipCode = ''" +
            ",@OfficePhone = ?3")
    List<CheckSimilarCustomer> getSimilarCustomerByAddressAndPhone(String labName, String add1, String phone);

    @Query(nativeQuery = true, value = "EXEC [NDXData].[dbo].[SP_CAVE_CheckSimilarDoctor]" +
            "@LabName = ?1" +
            ",@FirstName = ?2" +
            ",@LastName = ?3" +
            ",@LicenseNumber = ''" +
            ",@NPINumber = ''" +
            ",@Address1 = ''" +
            ",@OfficePhone = ''"
    )
    List<CheckSimilarDoctor> getSimilarDoctorByName(String labName, String fName, String lName);

    @Query(nativeQuery = true, value = "EXEC [NDXData].[dbo].[SP_CAVE_CheckSimilarDoctor]" +
            "@LabName = ?1" +
            ",@FirstName = ?2" +
            ",@LastName = ?3" +
            ",@LicenseNumber = ''" +
            ",@NPINumber = ''" +
            ",@Address1 = ''" +
            ",@OfficePhone = ''"
    )
    List<CheckSimilarDoctor> getSimilarDoctorNameAndLicense(String labName, String fName, String lName, String licenseNo);

    @Query(nativeQuery = true, value = "EXEC [NDXData].[dbo].[SP_CAVE_CheckExistingDoctor]" +
            "@LabName = ?1" +
            ",@ExistingDoctorNumber = ?2" +
            ",@LastName = ''" +
            ",@LicenseNumber = ''" +
            ",@NPINumber = ''")
    CheckExistingDoctor getExistingDoctorByDocId(String labName, String docId);




}
