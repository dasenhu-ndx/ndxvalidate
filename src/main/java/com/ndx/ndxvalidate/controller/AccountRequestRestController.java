package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.RequestTransaction;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class AccountRequestRestController {
    private  final AccountRequestRepo accountRequestRepo;
    private  final RequestTransaction requestTransaction;

    public AccountRequestRestController(AccountRequestRepo accountRequestRepo, RequestTransaction requestTransaction) {
        this.accountRequestRepo = accountRequestRepo;
        this.requestTransaction = requestTransaction;
    }

    @PostMapping(value = "/update/{id}")
    public Response postUpdateRequest(@RequestBody AccountRequest accountRequest, @PathVariable(value = "id") Long id){
       requestTransaction.updateRequest(accountRequest, id);
        System.out.println(accountRequest.getFName() + " " + accountRequest.getLName());
        Response response = new Response();
        response.setMessage("Request Completed");


        return response;

    }
    @PostMapping(value = "/update/{labName}/{id}")
    public Response postUpdateRequestLabName(@PathVariable(value = "labName") String labName, @PathVariable(value = "id") Long id){
       requestTransaction.updateLabName(labName, id);

        Response response = new Response();
        response.setMessage("Request Completed");


        return response;

    }

    @GetMapping(value = "/test/api/data")
    public List<AccountRequest> getAllRequests(){
        List<AccountRequest> accountRequests = accountRequestRepo.findAll();

        return accountRequests;
    }



}
