package com.ndx.ndxvalidate.data.repository;

import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.sp_access.AccountCreated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RunRequestRepo extends JpaRepository<AccountRequest, Long> {


    @Query(nativeQuery = true, value = "EXEC [NDXData].[dbo].[SP_NDXValidateNewAccountCreation]" +
            " @LabName = ?1" +
            ",@Operator= ?2" +
            ",@Prospect= ?3" +
            ",@AGDNumber= ?4" +
            ",@PDMNotes= ?5" +
            ",@Mode= ?6" +
            ",@DHS= ?7" +
            ",@ExistingDoctorNumber= ?8" +
            ",@ExistingCustomerID= ?9" +
            ",@DentalGroup= ?10" +
            ",@PracticeName= ?11" +
            ",@FirstName= ?12" +
            ",@LastName= ?13" +
            ",@Address1= ?14" +
            ",@City= ?15" +
            ",@State= ?16" +
            ",@ZipCode= ?17" +
            ",@OfficePhone= ?18" +
            ",@Email=?19" +
            ",@LicenseNumber= ?20" +
            ",@NPINumber= ?21" +
            ",@MasterAccountID= ?22" +
            ",@eModelCustomerID= ?23" +
            ",@UseStdAddress= ?24" +
            ",@Country= ?25" +
            ",@CorporateCampaign= ?26" +
            ",@CustomerOnly= ?27" +
            ",@IsLab= ?28" +
            ",@Verify= 'CHECK'" +
            ",@UserID= ?29")
    void runCheck(String labName, String operator, int prospect, String agdnNumber, String pdmnNotes,
                  String mode, int dhs, String docId, String customerId, String dentalGroup, String practiceName,
                  String fName, String lName, String add1, String city, String state, String zip, String phone,
                  String email, String licenseNo, String npi, String masterAccNo, String modelCustomerId, int useStreetAdd, String country,
                  String corpCamp, int customerOnly, int isLab, String mtUserName);


    @Query(nativeQuery = true, value = "EXEC [NDXData].[dbo].[SP_NDXValidateNewAccountCreation]" +
            " @LabName = ?1" +
            ",@Operator= ?2" +
            ",@Prospect= ?3" +
            ",@AGDNumber= ?4" +
            ",@PDMNotes= ?5" +
            ",@Mode= ?6" +
            ",@DHS= ?7" +
            ",@ExistingDoctorNumber= ?8" +
            ",@ExistingCustomerID= ?9" +
            ",@DentalGroup= ?10" +
            ",@PracticeName= ?11" +
            ",@FirstName= ?12" +
            ",@LastName= ?13" +
            ",@Address1= ?14" +
            ",@City= ?15" +
            ",@State= ?16" +
            ",@ZipCode= ?17" +
            ",@OfficePhone= ?18" +
            ",@Email=?19" +
            ",@LicenseNumber= ?20" +
            ",@NPINumber= ?21" +
            ",@MasterAccountID= ?22" +
            ",@eModelCustomerID= ?23" +
            ",@UseStdAddress= ?24" +
            ",@Country= ?25" +
            ",@CorporateCampaign= ?26" +
            ",@CustomerOnly= ?27" +
            ",@IsLab= ?28" +
            ",@Verify= 'RUN'" +
            ",@UserID= ?29")
    AccountCreated runAccount(String labName, String operator, int prospect, String agdnNumber, String pdmnNotes,
                              String mode, int dhs, String docId, String customerId, String dentalGroup, String practiceName,
                              String fName, String lName, String add1, String city, String state, String zip, String phone,
                              String email, String licenseNo, String npi, String masterAccNo, String modelCustomerId, int useStreetAdd, String country,
                              String corpCamp, int customerOnly, int isLab, String mtUserName);


}