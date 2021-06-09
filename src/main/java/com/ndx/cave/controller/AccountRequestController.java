package com.ndx.cave.controller;

import com.ndx.cave.business.service.DateTimeService;
import com.ndx.cave.business.service.MTUserService;
import com.ndx.cave.business.service.NdxModeService;
import com.ndx.cave.data.NdxMode;
import com.ndx.cave.data.entity.AccountRequest;
import com.ndx.cave.data.repository.AccountRequestRepo;
import com.ndx.cave.data.repository.MiniChecksRepo;
import com.ndx.cave.data.repository.OtherSPRepo;
import com.ndx.cave.data.sp_access.DentalGroups;
import com.ndx.cave.data.sp_access.LabUserPair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// This is the account request controller  and it handles the initial submit of any account request
@Controller
public class AccountRequestController {

    private final AccountRequestRepo accountRequestRepo;
    private final MTUserService mtUserService;
    private final NdxModeService ndxModeService;
    private final OtherSPRepo otherSPRepo;
    private final DateTimeService dateTimeService;

    public AccountRequestController(AccountRequestRepo accountRequestRepo, MTUserService mtUserService, NdxModeService ndxModeService, OtherSPRepo otherSPRepo, DateTimeService dateTimeService) {
        this.accountRequestRepo = accountRequestRepo;
        this.mtUserService = mtUserService;
        this.ndxModeService = ndxModeService;
        this.otherSPRepo = otherSPRepo;
        this.dateTimeService = dateTimeService;
    }

    @ModelAttribute("newAccount")
    public AccountRequest getInstance(){


        return new AccountRequest();
    }

// This is tied to the form using the action and object method, it performs the form submission. Action = /request object = newAccount
    @PostMapping("/requestAccount")
    public String requestNewAccount(@ModelAttribute("newAccount") AccountRequest accountRequest, Model model){
        String mtUserCurrent = mtUserService.currentUserName();
        accountRequest.setMtUserName(mtUserCurrent);
        accountRequest.setTimeStamp(dateTimeService.getCurrentDate());
        accountRequest.setLms(0);
        accountRequestRepo.save(accountRequest);
        List<NdxMode> ndxModes = ndxModeService.listModes();
        List<LabUserPair> labUserPairList = otherSPRepo.getLabUserPairList(mtUserCurrent);
        List<DentalGroups> dentalGroups = otherSPRepo.getDentalGroupsByUserName(mtUserCurrent);
        model.addAttribute("dentalGroups", dentalGroups);
        model.addAttribute("labUserPair", labUserPairList);
        model.addAttribute("ndxMode", ndxModes);
        model.addAttribute("sent", "Your request has been sent to the LMS team or processing. Allow a few hours.");
        return "request";
    }


}
