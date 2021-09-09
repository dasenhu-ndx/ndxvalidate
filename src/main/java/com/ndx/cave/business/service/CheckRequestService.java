package com.ndx.cave.business.service;

import com.ndx.cave.data.entity.AccountRequest;
import com.ndx.cave.data.repository.MiniChecksRepo;
import com.ndx.cave.data.sp_access.CheckExistingCustomer;
import com.ndx.cave.data.sp_access.CheckExistingDoctor;
import com.ndx.cave.data.sp_access.CheckSimilarCustomer;
import com.ndx.cave.data.sp_access.CheckSimilarDoctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckRequestService {

    private final MiniChecksRepo miniChecksRepo;


    public CheckRequestService(MiniChecksRepo miniChecksRepo) {
        this.miniChecksRepo = miniChecksRepo;
    }

    public List<CheckSimilarDoctor>  getSimilarDocList(AccountRequest accountRequest){
        System.out.println(accountRequest.getLabName());
        System.out.println(accountRequest.getFName());
        System.out.println(accountRequest.getLName());
        System.out.println(accountRequest.getLicenseNo());
        System.out.println(accountRequest.getNpi());
        System.out.println(accountRequest.getAdd1());
        System.out.println(accountRequest.getAdd2());
        System.out.println(accountRequest.getPhone());
        return miniChecksRepo.getSimilarDoctor(accountRequest.getLabName(), accountRequest.getFName(), accountRequest.getLName(),
                accountRequest.getLicenseNo(), accountRequest.getNpi(), accountRequest.getAdd1(), accountRequest.getPhone());
    }

    public  List<CheckExistingDoctor> getExistingDocList(AccountRequest accountRequest){

        return miniChecksRepo.getExistingDoctor(accountRequest.getLabName(),accountRequest.getDocId(),
                accountRequest.getLName(),accountRequest.getLicenseNo(),accountRequest.getNpi());
    }

    public  List<CheckExistingCustomer> getExistingCustomer(AccountRequest accountRequest){

        return miniChecksRepo.getExistingCustomer(accountRequest.getLabName(),accountRequest.getAdd1(),
                accountRequest.getCustomerId(), accountRequest.getPhone());

    }
    public  List<CheckSimilarCustomer> getSimilarCustomer(AccountRequest accountRequest){

        return miniChecksRepo.getSimilarCustomer(accountRequest.getLabName(),accountRequest.getAdd1(),
                accountRequest.getCustomerId(), accountRequest.getPhone());

    }
}
