package com.ndx.cave.data.repository;

import com.ndx.cave.data.entity.AccountRequest;
import com.ndx.cave.data.sp_access.DentalGroups;
import com.ndx.cave.data.sp_access.LabUserPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtherSPRepo extends JpaRepository<AccountRequest, Long> {

    @Query(nativeQuery = true, value = "exec [NDXData].[dbo].[SP_CAVE_LabUserInfo]" +
            "@UserName = ?1")
    List<LabUserPair> getLabUserPairList(String mtUserName);

    @Query(nativeQuery = true, value = "EXEC [NDXData].[dbo].[SP_CAVE_DentalGroups]" +
            "@UserName = ?1" )
    List<DentalGroups> getDentalGroupsByUserName(String mtUserName);
}
















