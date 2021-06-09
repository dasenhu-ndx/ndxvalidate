package com.ndx.cave.controller;

import com.ndx.cave.business.service.NdxModeService;
import com.ndx.cave.business.service.ProcessRequestService;
import com.ndx.cave.data.NdxMode;
import com.ndx.cave.data.entity.AccountRequest;
import com.ndx.cave.data.entity.ProcessedRequest;
import com.ndx.cave.data.repository.AccountRequestRepo;
import com.ndx.cave.data.repository.ProcessedRequestRepo;
import com.ndx.cave.data.sp_access.AccountCreated;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProcessingRequestController {
    private final AccountRequestRepo accountRequestRepo;
    private final ProcessRequestService processRequestService;
    private final NdxModeService ndxModeService;
    private final ProcessedRequestRepo processedRequestRepo;

    public ProcessingRequestController(AccountRequestRepo accountRequestRepo, ProcessRequestService processRequestService, NdxModeService ndxModeService, ProcessedRequestRepo processedRequestRepo) {
        this.accountRequestRepo = accountRequestRepo;
        this.processRequestService = processRequestService;
        this.ndxModeService = ndxModeService;
        this.processedRequestRepo = processedRequestRepo;
    }

    @GetMapping("/process/request/{id}")
    public  String processRequest(@PathVariable(value = "id") Long id, Model model){
        AccountRequest accountRequest = accountRequestRepo.findAccountRequestsByAccId(id);
        accountRequest.setStatus(1);
        accountRequestRepo.save(accountRequest);
        model.addAttribute("accountRequest", accountRequest);
        return "process";
    }

    @GetMapping("/process/run-request/{id}")
    public String processCheckRun(@PathVariable(value = "id") Long id, Model model){
        AccountRequest accountRequest = accountRequestRepo.findAccountRequestsByAccId(id);
        AccountCreated accountCreated = processRequestService.runCheckRequest(accountRequest);
        processRequestService.saveProcessedRequest(accountCreated,accountRequest);
        accountRequest.setLms(1);
        accountRequestRepo.save(accountRequest);
        ProcessedRequest processedRequest = processedRequestRepo.findProcessedRequestByAccountRequest(accountRequest);
        List<NdxMode> ndxModes = ndxModeService.listModes();
        NdxMode ndxMode = ndxModes.get(accountRequest.getMode());
        model.addAttribute("ndxModel", ndxMode);
        model.addAttribute("accountRequest", accountRequest);
        model.addAttribute("accountCreated", processedRequest);

        return "request_complete";

    }
}
