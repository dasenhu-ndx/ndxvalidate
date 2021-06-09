package com.ndx.cave.data.repository;


import com.ndx.cave.data.entity.AccountRequest;
import com.ndx.cave.data.entity.ProcessedRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedRequestRepo extends JpaRepository<ProcessedRequest, Long> {

    List<ProcessedRequest> findAllByAccountRequest_MtUserName(String mtUser);
    List<ProcessedRequest> findAllByProcBy(String procBy);
    ProcessedRequest findProcessedRequestByAccountRequest(AccountRequest accountRequest);
}
