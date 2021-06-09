package com.ndx.cave.data.repository;

import com.ndx.cave.data.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

    Admin findAdminByMtUserName(String mtUserName);

}
