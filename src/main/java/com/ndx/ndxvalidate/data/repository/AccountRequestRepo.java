package com.ndx.ndxvalidate.data.repository;


import com.ndx.ndxvalidate.data.entity.AccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.List;

public interface AccountRequestRepo extends JpaRepository<AccountRequest, Long> {


    List<AccountRequest> findAccountRequestsByMtUserName(String mtUserName);


}
