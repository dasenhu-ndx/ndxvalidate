package com.ndx.ndxvalidate.data.repository;

import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.sp_access.LabUserPair;
import com.ndx.ndxvalidate.data.sp_access.PracticeDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtherSPRepo extends JpaRepository<AccountRequest, Long> {

    @Query(nativeQuery = true, value = "exec [NDXData].[dbo].[SP_NDXValidateLabUserInfo]" +
            "@UserName = ?1")
    List<LabUserPair> getLabUserPairList(String mtUserName);
}
