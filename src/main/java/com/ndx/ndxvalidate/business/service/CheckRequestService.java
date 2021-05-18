package com.ndx.ndxvalidate.business.service;

import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.repository.MiniChecksRepo;
import com.ndx.ndxvalidate.data.sp_access.CheckExistingCustomer;
import com.ndx.ndxvalidate.data.sp_access.CheckExistingDoctor;
import com.ndx.ndxvalidate.data.sp_access.CheckSimilarCustomer;
import com.ndx.ndxvalidate.data.sp_access.CheckSimilarDoctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckRequestService {

    private final MiniChecksRepo miniChecksRepo;


    public CheckRequestService(MiniChecksRepo miniChecksRepo) {
        this.miniChecksRepo = miniChecksRepo;
    }

    public List<CheckSimilarDoctor>  getSimilarDocList(AccountRequest accountRequest){
        List<CheckSimilarDoctor> checkSimilarDoctor = miniChecksRepo.getSimilarDoctor(accountRequest.getLabName(),accountRequest.getFName(), accountRequest.getLName(),
                accountRequest.getLicenceNo(), accountRequest.getNpi(), accountRequest.getAdd1() + " " +accountRequest.getAdd2(),accountRequest.getPhone()) ;

        return checkSimilarDoctor;
    }

    public  List<CheckExistingDoctor> getExistingDocList(AccountRequest accountRequest){
        List<CheckExistingDoctor>  checkExistingDoctorList = miniChecksRepo.getExistingDoctor(accountRequest.getLabName(),accountRequest.getDocId(),
                accountRequest.getLName(),accountRequest.getLicenceNo(),accountRequest.getNpi());

        return checkExistingDoctorList;
    }

    public  List<CheckExistingCustomer> getExistingCustomer(AccountRequest accountRequest){
        List<CheckExistingCustomer> checkExistingCustomers = miniChecksRepo.getExistingCustomer(accountRequest.getLabName(),accountRequest.getAdd1(),
                accountRequest.getCustomerId(), accountRequest.getPhone());

        return checkExistingCustomers;

    }
    public  List<CheckSimilarCustomer> getSimilarCustomer(AccountRequest accountRequest){
        List<CheckSimilarCustomer> checkSimilarCustomers = miniChecksRepo.getSimilarCustomer(accountRequest.getLabName(),accountRequest.getAdd1(),
                accountRequest.getCustomerId(), accountRequest.getPhone());

        return checkSimilarCustomers;

    }
}
