package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.AdminService;
import com.ndx.ndxvalidate.business.service.CheckRequestService;
import com.ndx.ndxvalidate.business.service.MTUserService;
import com.ndx.ndxvalidate.business.service.NdxModeService;
import com.ndx.ndxvalidate.data.NdxMode;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.entity.Email;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import com.ndx.ndxvalidate.data.repository.EmailSPRepository;
import com.ndx.ndxvalidate.data.sp_access.CheckExistingCustomer;
import com.ndx.ndxvalidate.data.sp_access.CheckExistingDoctor;
import com.ndx.ndxvalidate.data.sp_access.CheckSimilarCustomer;
import com.ndx.ndxvalidate.data.sp_access.CheckSimilarDoctor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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


    public CheckingRequestController(CheckRequestService checkRequestService, AccountRequestRepo accountRequestRepo, NdxModeService ndxModeService, MTUserService mtUserService, AdminService adminService, EmailSPRepository emailSPRepository) {
        this.checkRequestService = checkRequestService;
        this.accountRequestRepo = accountRequestRepo;
        this.ndxModeService = ndxModeService;
        this.mtUserService = mtUserService;
        this.adminService = adminService;
        this.emailSPRepository = emailSPRepository;
    }

    @ModelAttribute("newEmail")
    public Email getInstanceEmail(){


        return new Email();
    }


//    All four checks are controlled by the below method
    @GetMapping("/check/request/{id}")
    public String checkRequest(@PathVariable(value = "id") Long id, Model model){
        AccountRequest accountRequest = accountRequestRepo.findAccountRequestsByAccId(id);
        List<CheckSimilarDoctor> checkSimilarDoctor = checkRequestService.getSimilarDocList(accountRequest);
        List<CheckExistingDoctor> checkExistingDoctor = checkRequestService.getExistingDocList(accountRequest);
        List<CheckExistingCustomer> checkExistingCustomers = checkRequestService.getExistingCustomer(accountRequest);
        List<CheckSimilarCustomer> checkSimilarCustomers = checkRequestService.getSimilarCustomer(accountRequest);
        List<NdxMode> ndxModes = ndxModeService.listModes();
        NdxMode ndxMode = ndxModes.get(accountRequest.getMode());
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
