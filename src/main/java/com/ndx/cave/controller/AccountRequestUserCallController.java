package com.ndx.cave.controller;

import com.ndx.cave.business.service.MTUserService;
import com.ndx.cave.data.entity.AccountRequest;
import com.ndx.cave.data.entity.Admin;
import com.ndx.cave.data.repository.AccountRequestRepo;
import com.ndx.cave.data.repository.AdminRepo;
import com.ndx.cave.data.repository.OtherSPRepo;
import com.ndx.cave.data.sp_access.DentalGroups;
import com.ndx.cave.data.sp_access.LabUserPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//This controller handles getting account request form data that has already been submitted.

@Controller
public class AccountRequestUserCallController {

    private final OtherSPRepo otherSPRepo;
    private final AccountRequestRepo accountRequestRepo;
    private final MTUserService mtUserService;
    private final AdminRepo adminRepo;

    @Autowired
    public AccountRequestUserCallController(OtherSPRepo otherSPRepo,AccountRequestRepo accountRequestRepo, MTUserService mtUserService, AdminRepo adminRepo) {
        this.otherSPRepo = otherSPRepo;
        this.accountRequestRepo = accountRequestRepo;
        this.mtUserService = mtUserService;
        this.adminRepo = adminRepo;
    }

//    This maps open requests to the users dashboard

    @GetMapping("/dashboard")
    public String getDashboardVariables(Model model) {
        String mtUserName = mtUserService.currentUserName();
        List<String> Labs = accountRequestRepo.findLabs(mtUserName);

        List<AccountRequest> accountRequests = accountRequestRepo.findAccountRequestsByLabNameInAndStatus(Labs, 0);




        Admin admin = adminRepo.findAdminByMtUserName(mtUserName);

        model.addAttribute("admin", admin);


        model.addAttribute("requests", accountRequests);

        return "dashboard";
    }

//    This handles edits made to the  existing account request form

    @GetMapping("/edit_request/{id}")
    public String editRequest(@PathVariable(value = "id") Long id, Model model) {
        List<LabUserPair> labUserPairList = otherSPRepo.getLabUserPairList(mtUserService.currentUserName());
        List<DentalGroups> dentalGroups = otherSPRepo.getDentalGroupsByUserName(mtUserService.currentUserName());
        AccountRequest accountRequest = accountRequestRepo.findAccountRequestsByAccId(id);

        model.addAttribute("dentalGroups", dentalGroups);
        model.addAttribute("labUserPair", labUserPairList);
        model.addAttribute("accRequest", accountRequest);
        return "edit_request";
    }



}