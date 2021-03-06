package com.ndx.cave.controller;

import com.ndx.cave.business.service.AdminService;
import com.ndx.cave.business.service.MTUserService;
import com.ndx.cave.data.entity.ProcessedRequest;
import com.ndx.cave.data.repository.ProcessedRequestRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


//This controller handles the pages returning the requests that are already processed
@Controller
public class ProcessedController {

    private final ProcessedRequestRepo processedRequestRepo;
    private final MTUserService mtUserService;
    private final AdminService adminService;

    public ProcessedController(ProcessedRequestRepo processedRequestRepo, MTUserService mtUserService, AdminService adminService) {
        this.processedRequestRepo = processedRequestRepo;
        this.mtUserService = mtUserService;
        this.adminService = adminService;
    }

    @GetMapping("/my/processed/requests")
    public String getMyProcessedRequests(Model model){


        String mtUserName = mtUserService.currentUserName();
        Boolean userStatus = adminService.checkAdminPrivilege(mtUserName);

        List<ProcessedRequest> processedRequests = processedRequestRepo.findAllByProcBy(mtUserName);
        model.addAttribute("processed", processedRequests);
        model.addAttribute("mtu", mtUserName);
        if(userStatus) {
            return "my_processed_requests";
        }else{
            return "no_access";
        }


    }

    @GetMapping("/processed/requests")
    public String getProcessedRequests(Model model){


        String mtUserName = mtUserService.currentUserName();
        Boolean userStatus = adminService.checkAdminPrivilege(mtUserName);

        List<ProcessedRequest> processedRequests = processedRequestRepo.findAll();
        model.addAttribute("processed", processedRequests);
        if(userStatus) {
            return "processed_requests";
        }else{
            return "no_access";
        }


    }


    @GetMapping("/processed/requests/complete")
    public String getProcessedRequestsComplete(Model model){


        String mtUserName = mtUserService.currentUserName();


        List<ProcessedRequest> processedRequests = processedRequestRepo.findAllByAccountRequest_MtUserName(mtUserName);
        model.addAttribute("processed", processedRequests);

        return "user_processed";

    }


}
