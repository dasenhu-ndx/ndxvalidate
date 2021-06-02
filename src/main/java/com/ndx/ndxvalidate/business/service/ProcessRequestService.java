package com.ndx.ndxvalidate.business.service;

import com.ndx.ndxvalidate.data.NdxMode;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.entity.ProcessedRequest;
import com.ndx.ndxvalidate.data.repository.ProcessedRequestRepo;
import com.ndx.ndxvalidate.data.repository.RunRequestRepo;
import com.ndx.ndxvalidate.data.sp_access.AccountCreated;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessRequestService {

    private final MTUserService mtUserService;
    private final NdxModeService ndxModeService;
    private final RunRequestRepo runRequestRepo;
    private final ProcessedRequestRepo processedRequestRepo;
    private final DateTimeService dateTimeService;

    public ProcessRequestService(MTUserService mtUserService, NdxModeService ndxModeService, RunRequestRepo runRequestRepo, ProcessedRequestRepo processedRequestRepo, DateTimeService dateTimeService) {
        this.mtUserService = mtUserService;
        this.ndxModeService = ndxModeService;
        this.runRequestRepo = runRequestRepo;
        this.processedRequestRepo = processedRequestRepo;
        this.dateTimeService = dateTimeService;
    }

    public AccountCreated runCheckRequest(AccountRequest accountRequest){

        String labName = accountRequest.getLabName();
        String operator = mtUserService.currentUserName();
        int prospect = 0;
        List<NdxMode> ndxModes = ndxModeService.listModes();
        NdxMode ndxMode = ndxModes.get(accountRequest.getMode());
        String mode = ndxMode.mode;
        int dhs = accountRequest.getDhs();
        String eDocNo = accountRequest.getDocId();
        String eCusNo = accountRequest.getCustomerId();
        String dentalGroup = accountRequest.getDGroup();
        String agdNo = "";
        String pndmNote = "";
        String practiceName = accountRequest.getPName();
        String fName = accountRequest.getFName();
        String lName = accountRequest.getLName();
        String add1 = accountRequest.getAdd1() + " " + accountRequest.getAdd2();
        String city = accountRequest.getCity();
        String state = accountRequest.getState();
        String zip = accountRequest.getZip();
        String phone = accountRequest.getPhone();
        String email = accountRequest.getEmail();
        String licenseNo = accountRequest.getLicenseNo();
        String npi = accountRequest.getNpi();
        String masterAccountId = "";
        String eModelCustomerId = "";
        int useStdAddress = 1;
        String country = "US";
        String corpCamp = "";
        int customerOnly = 0;
        int isLab = 0;
        String userId = accountRequest.getMtUserName();

        runRequestRepo.runCheck(labName,operator,prospect,agdNo,pndmNote,mode,dhs,eDocNo, eCusNo,dentalGroup,practiceName,fName,lName,add1,city,state,zip,phone,email,licenseNo,npi, masterAccountId,eModelCustomerId,useStdAddress,
                country,corpCamp,customerOnly,isLab,userId);


        return runRequestRepo.runAccount(labName,operator,prospect,agdNo,pndmNote,mode,dhs,eDocNo, eCusNo,dentalGroup,practiceName,fName,lName,add1,city,state,zip,phone,email,licenseNo,npi, masterAccountId,eModelCustomerId,useStdAddress,
                country,corpCamp,customerOnly,isLab,userId);

    }

    public void saveProcessedRequest(AccountCreated accountCreated, AccountRequest accountRequest){
        ProcessedRequest processedRequest = new ProcessedRequest();

        processedRequest.setAccountRequest(accountRequest);
        processedRequest.setNewCusId(accountCreated.getCustomerID());
        processedRequest.setNewDocId(accountCreated.getDoctorID());
        processedRequest.setProcBy(accountCreated.getOperator());
        processedRequest.setTimeStamp(dateTimeService.getCurrentDate());
        processedRequestRepo.save(processedRequest);

    }
}
