package com.ndx.cave.controller;

import com.ndx.cave.business.service.EmailService;
import com.ndx.cave.business.service.RequestTransaction;
import com.ndx.cave.data.entity.AccountRequest;
import com.ndx.cave.data.entity.Email;
import com.ndx.cave.data.repository.AccountRequestRepo;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.*;

//The edit account request form updates the form using a restful api to update all fields. this controller  houses the restful endpoint that performs this logic
@RestController
@RequestMapping("/api/request")
public class AccountRequestRestController {
    private  final AccountRequestRepo accountRequestRepo;
    private  final RequestTransaction requestTransaction;
    private final EmailService emailService;

    public AccountRequestRestController(AccountRequestRepo accountRequestRepo, RequestTransaction requestTransaction, EmailService emailService) {
        this.accountRequestRepo = accountRequestRepo;
        this.requestTransaction = requestTransaction;
        this.emailService = emailService;
    }


// Endpoint to update form data
//    api/request/update/{id}
    @PostMapping(value = "/update/{id}", consumes = "application/json")
    public Response postUpdateRequest(@RequestBody AccountRequest accountRequest, @PathVariable(value = "id") Long id){
       requestTransaction.updateRequest(accountRequest, id);
        System.out.println(accountRequest.getFName() + " " + accountRequest.getLName());
        Response response = new Response();
        System.out.println(accountRequest.getFName() + " " + accountRequest.getLName());
        response.setMessage("Request Completed");
        System.out.println(accountRequest.getFName() + " " + accountRequest.getLName());

        return response;

    }

//    Endpoint to update request labName
//    api/request/update/{labName}/{id}
    @PostMapping(value = "/update/{labName}/{id}")
    public Response postUpdateRequestLabName(@PathVariable(value = "labName") String labName, @PathVariable(value = "id") Long id){
       requestTransaction.updateLabName(labName, id);
       String dbName = "";
        if (labName.equalsIgnoreCase("PAIRW-ADL")  || labName.equalsIgnoreCase("PAIRW-IDA")){
            dbName = "PAIRW";
        } else if (labName.equalsIgnoreCase("TXCON") || labName.equalsIgnoreCase("CAHAW") || labName.equalsIgnoreCase("CAHAO") || labName.equalsIgnoreCase("CAHER") || labName.equalsIgnoreCase("CAELS")  ||labName.equalsIgnoreCase("NVLAS")  || labName.equalsIgnoreCase("NYWHI")  ) {
            dbName = "WCDL";
        }
        requestTransaction.updateDBName(dbName, id);
        Response response = new Response();
        response.setMessage("Request Completed");


        return response;

    }

    @PostMapping("/sendEmail/{id}")
    public Response sendEmailWithIssuesM1(@RequestBody Email email, @PathVariable(value = "id") Long id){

        AccountRequest accountRequest = accountRequestRepo.findAccountRequestsByAccId(id);

        emailService.emailServiceSender(accountRequest, email.getMessage());
        Response response = new Response();
        response.setMessage("Request Completed");


        return response;

    }





}
