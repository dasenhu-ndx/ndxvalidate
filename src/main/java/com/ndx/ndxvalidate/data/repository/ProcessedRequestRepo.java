package com.ndx.ndxvalidate.data.repository;


import com.ndx.ndxvalidate.data.entity.ProcessedRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcessedRequestRepo extends JpaRepository<ProcessedRequest, Long> {

    List<ProcessedRequest> findAllByAccountRequest_MtUserName(String mtUser);
    List<ProcessedRequest> findAllByProcBy(String procBy);
}
