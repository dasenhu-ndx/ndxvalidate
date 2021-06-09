package com.ndx.cave.data.repository;

import com.ndx.cave.data.entity.AccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailSPRepository extends JpaRepository<AccountRequest, Long> {


    @Query(nativeQuery = true, value = "Exec [NDXData].[dbo].[SP_CAVE_Email]" +
            "@LabName = ?1" +
            ",@Message =?2" +
            ",@UserName = ?3" +
            ",@Operator = ?4" +
            ",@Subject = ''")
    void sendNoteONNewAccount(String labName, String message, String mtUserName, String Operator);

}
