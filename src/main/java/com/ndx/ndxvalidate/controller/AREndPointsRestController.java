package com.ndx.ndxvalidate.controller;

import com.ndx.ndxvalidate.data.repository.MiniChecksRepo;
import com.ndx.ndxvalidate.data.sp_access.CheckExistingCustomer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/form/endpoints")
public class AREndPointsRestController {

    private final MiniChecksRepo miniChecksRepo;


    public AREndPointsRestController(MiniChecksRepo miniChecksRepo) {
        this.miniChecksRepo = miniChecksRepo;
    }

    @GetMapping("/{labName}/{customerId}")
    public CheckExistingCustomer getExistingCustomerByCustomerId(@PathVariable(name = "labName") String labName,
                                                                  @PathVariable(name = "customerId") String customerId){

        CheckExistingCustomer existingCustomer = miniChecksRepo.getExistingCustomerByCustomerId(labName, customerId);

        return existingCustomer;
    }

    @GetMapping("/{labName}/{customerId}/{phone}")
    public CheckExistingCustomer getExistingCustomerByCustomerIdAndPhone(@PathVariable(name = "labName") String labName,
                                                                  @PathVariable(name = "customerId") String customerId,
                                                                         @PathVariable(name = "phone") String phone){

        CheckExistingCustomer existingCustomer = miniChecksRepo.getExistingCustomerByCustomerIdAndPhone(labName, customerId, phone);

        return existingCustomer;
    }

}
