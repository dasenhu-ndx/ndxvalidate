package com.ndx.cave.business.service;

import com.ndx.cave.data.entity.AccountRequest;
import com.ndx.cave.data.repository.AccountRequestRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RequestTransaction {

    private final AccountRequestRepo accountRequestRepo;

    public RequestTransaction(AccountRequestRepo accountRequestRepo) {
        this.accountRequestRepo = accountRequestRepo;
    }

    public void updateRequest(AccountRequest accountRequest, long id ){
        accountRequestRepo.updateLabName(accountRequest.getLabName(), id);
        System.out.println(accountRequest.getDGroup());
        System.out.println(accountRequest.getFName());
        accountRequestRepo.updateDentalGroup(accountRequest.getDGroup(), id);
        accountRequestRepo.updatePracticeName(accountRequest.getPName(), id);
        accountRequestRepo.updateMode(accountRequest.getMode(), id);
        accountRequestRepo.updateRush(accountRequest.getIsRush(), id);
        accountRequestRepo.updateDocId(accountRequest.getDocId(), id);
        accountRequestRepo.updateCustomerId(accountRequest.getCustomerId(), id);
        accountRequestRepo.updateAdd1(accountRequest.getAdd1(), id);
        accountRequestRepo.updateAdd2(accountRequest.getAdd2(), id);
        accountRequestRepo.updateCity(accountRequest.getCity(), id);
        accountRequestRepo.updateState(accountRequest.getState(), id);
        accountRequestRepo.updateZip(accountRequest.getZip(), id);
        accountRequestRepo.updateFName(accountRequest.getFName(), id);
        accountRequestRepo.updateLName(accountRequest.getLName(), id);
        accountRequestRepo.updateEmail(accountRequest.getEmail(), id);
        accountRequestRepo.updatePhone(accountRequest.getPhone(), id);
        accountRequestRepo.updateLicenseNo(accountRequest.getLicenseNo(), id);
        accountRequestRepo.updateNPI(accountRequest.getNpi(), id);
        accountRequestRepo.updateNotes(accountRequest.getNotes(), id);
        accountRequestRepo.updateDBName(accountRequest.getDbName(), id);
    }

    public void updateLabName(String labName, long id){
        accountRequestRepo.updateLabName(labName, id);
    }

    public void updateDBName(String dbName, Long id) {
        accountRequestRepo.updateDBName(dbName, id);
    }
}
