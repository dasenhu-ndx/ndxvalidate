package com.ndx.cave.controller;

import com.ndx.cave.business.service.AdminService;
import com.ndx.cave.business.service.CheckRequestService;
import com.ndx.cave.business.service.MTUserService;
import com.ndx.cave.business.service.NdxModeService;
import com.ndx.cave.data.NdxMode;
import com.ndx.cave.data.entity.AccountRequest;
import com.ndx.cave.data.entity.Email;
import com.ndx.cave.data.repository.AccountRequestRepo;
import com.ndx.cave.data.repository.EmailSPRepository;
import com.ndx.cave.data.repository.OtherSPRepo;
import com.ndx.cave.data.sp_access.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//This controller processes the check page for the lms team to check requests
@Controller
public class CheckingRequestController {

    private final CheckRequestService checkRequestService;
    private final AccountRequestRepo accountRequestRepo;
    private  final NdxModeService ndxModeService;
    private final MTUserService mtUserService;
    private final AdminService adminService;
    private final EmailSPRepository emailSPRepository;
    private final OtherSPRepo otherSPRepo;

    public CheckingRequestController(CheckRequestService checkRequestService, AccountRequestRepo accountRequestRepo, NdxModeService ndxModeService, MTUserService mtUserService, AdminService adminService, EmailSPRepository emailSPRepository, OtherSPRepo otherSPRepo) {
        this.checkRequestService = checkRequestService;
        this.accountRequestRepo = accountRequestRepo;
        this.ndxModeService = ndxModeService;
        this.mtUserService = mtUserService;
        this.adminService = adminService;
        this.emailSPRepository = emailSPRepository;
        this.otherSPRepo = otherSPRepo;
    }

    @ModelAttribute("newEmail")
    public Email getInstanceEmail(){


        return new Email();
    }


//    All four checks are controlled by the below method
    @GetMapping("/check/request/{id}")
    public String checkRequest(@PathVariable(value = "id") Long id, Model model){
        String mtUserCurrent = mtUserService.currentUserName();
        AccountRequest accountRequest = accountRequestRepo.findAccountRequestsByAccId(id);
        accountRequest.setMtUserName(mtUserCurrent);
        List<CheckSimilarDoctor> checkSimilarDoctor = checkRequestService.getSimilarDocList(accountRequest);
        List<CheckExistingDoctor> checkExistingDoctor = checkRequestService.getExistingDocList(accountRequest);
        List<CheckExistingCustomer> checkExistingCustomers = checkRequestService.getExistingCustomer(accountRequest);
        List<CheckSimilarCustomer> checkSimilarCustomers = checkRequestService.getSimilarCustomer(accountRequest);
        List<LabUserPair> labUserPairList = otherSPRepo.getLabUserPairList(mtUserCurrent);
        List<NdxMode> ndxModes = ndxModeService.listModes();
        NdxMode ndxMode = ndxModes.get(accountRequest.getMode());
        model.addAttribute("labUserPair", labUserPairList);
        model.addAttribute("accountRequest", accountRequest);
        model.addAttribute("accId", id);
        model.addAttribute("ndxMode", ndxMode);
        model.addAttribute("similarDoc", checkSimilarDoctor);
        model.addAttribute("existingDoc", checkExistingDoctor);
        model.addAttribute("similarCus", checkSimilarCustomers);
        model.addAttribute("existingCus", checkExistingCustomers);


        String mtUserName = mtUserService.currentUserName();
        Boolean userStatus = adminService.checkAdminPrivilege(mtUserName);


        System.out.println(userStatus);
        if(userStatus) {
            return "check_request";
        }else{
            return "no_access";
        }

    }

}
