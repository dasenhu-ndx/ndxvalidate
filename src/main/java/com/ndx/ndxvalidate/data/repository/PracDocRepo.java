package com.ndx.ndxvalidate.data.repository;

import com.ndx.ndxvalidate.data.entity.TestEntity;
import com.ndx.ndxvalidate.data.sp_access.PracticeDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracDocRepo extends JpaRepository<TestEntity, Long> {


    @Query(nativeQuery = true, value = "exec [NDXData].[dbo].[SP_NDXValidateCheckSimilarDoctor]" +
            "@LabName = 'MOFEN'" +
            ",@FirstName = 'Benjamin'" +
            ",@LastName = 'Aanderud'" +
            ",@LicenseNumber = ''" +
            ",@NPINumber = ''" +
            ",@ExistingCustomerID = ''" +
            ",@Address1 = ''" +
            ",@OfficePhone = ''")
    List<PracticeDoc> getAllNow();

    @Query(nativeQuery = true, value = "exec [NDXData].[dbo].[SP_NDXValidateCheckSimilarDoctor]" +
            "@LabName = ?1"+
            ",@FirstName = ?2" +
            ",@LastName = ?3" +
            ",@LicenseNumber = ''" +
            ",@NPINumber = ''" +
            ",@ExistingCustomerID = ''" +
            ",@Address1 = ''" +
            ",@OfficePhone = ''")
    List<PracticeDoc> getAllByFirstNameLabLastName(String labName, String firstName, String lastName);
}
