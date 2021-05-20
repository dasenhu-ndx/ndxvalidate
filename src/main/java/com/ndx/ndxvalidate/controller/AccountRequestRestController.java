package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.business.service.RequestTransaction;
import com.ndx.ndxvalidate.data.entity.AccountRequest;
import com.ndx.ndxvalidate.data.repository.AccountRequestRepo;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//The edit account request form updates the form using a restful api to update all fields. this controller  houses the restful endpoint that performs this logic
@RestController
@RequestMapping("/api/request")
public class AccountRequestRestController {
    private  final AccountRequestRepo accountRequestRepo;
    private  final RequestTransaction requestTransaction;

    public AccountRequestRestController(AccountRequestRepo accountRequestRepo, RequestTransaction requestTransaction) {
        this.accountRequestRepo = accountRequestRepo;
        this.requestTransaction = requestTransaction;
    }


// Endpoint to update form data
//    api/request/update/{id}
    @PostMapping(value = "/update/{id}")
    public Response postUpdateRequest(@RequestBody AccountRequest accountRequest, @PathVariable(value = "id") Long id){
       requestTransaction.updateRequest(accountRequest, id);
        System.out.println(accountRequest.getFName() + " " + accountRequest.getLName());
        Response response = new Response();
        response.setMessage("Request Completed");


        return response;

    }

//    Endpoint to update request labName
//    api/request/update/{labName}/{id}
    @PostMapping(value = "/update/{labName}/{id}")
    public Response postUpdateRequestLabName(@PathVariable(value = "labName") String labName, @PathVariable(value = "id") Long id){
       requestTransaction.updateLabName(labName, id);

        Response response = new Response();
        response.setMessage("Request Completed");


        return response;

    }




}
