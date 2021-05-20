package com.ndx.ndxvalidate.business.service;


import com.ndx.ndxvalidate.data.entity.Admin;
import com.ndx.ndxvalidate.data.repository.AdminRepo;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepo adminRepo;


    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Boolean checkAdminPrivilege(String mtUserName){

        Admin admin = adminRepo.findAdminByMtUserName(mtUserName);

        return admin != null;
    }
}
