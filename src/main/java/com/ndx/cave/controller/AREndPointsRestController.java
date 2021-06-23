package com.ndx.cave.controller;

import com.ndx.cave.business.service.EmailService;
import com.ndx.cave.business.service.MTUserService;
import com.ndx.cave.data.entity.AccountRequest;
import com.ndx.cave.data.repository.AccountRequestRepo;
import com.ndx.cave.data.repository.EmailSPRepository;
import com.ndx.cave.data.repository.MiniChecksRepo;
import com.ndx.cave.data.sp_access.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/form/endpoints")
public class AREndPointsRestController {

    private final MiniChecksRepo miniChecksRepo;
    private final AccountRequestRepo accountRequestRepo;
    private  final MTUserService mtUserService;
    private  final EmailSPRepository emailSPRepository;
    private  final EmailService emailService;

//    This is the controller that houses all the logic for restfulAPI used to make account request form dynamic

    public AREndPointsRestController(MiniChecksRepo miniChecksRepo, AccountRequestRepo accountRequestRepo, MTUserService mtUserService, EmailSPRepository emailSPRepository, EmailService emailService) {
        this.miniChecksRepo = miniChecksRepo;
        this.accountRequestRepo = accountRequestRepo;
        this.mtUserService = mtUserService;
        this.emailSPRepository = emailSPRepository;
        this.emailService = emailService;
    }

//    End points to get existing customer by Id
//     form/endpoints/{labName}/{customerId}

    @GetMapping("/ec/{labName}/{customerId}")
    public CheckExistingCustomer getExistingCustomerByCustomerId(@PathVariable(name = "labName") String labName,
                                                                 @PathVariable(name = "customerId") String customerId) {

        return miniChecksRepo.getExistingCustomerByCustomerId(labName, customerId);
    }

//    Endpoints to get existing customer by customer id and phone
//     form/endpoints/{labName}/{customerId}/{phone}


    @GetMapping("/ec/{labName}/{customerId}/{phone}")
    public CheckExistingCustomer getExistingCustomerByCustomerIdAndPhone(@PathVariable(name = "labName") String labName,
                                                                         @PathVariable(name = "customerId") String customerId,
                                                                         @PathVariable(name = "phone") String phone) {

        return miniChecksRepo.getExistingCustomerByCustomerIdAndPhone(labName, customerId, phone);
    }

//    Endpoints to get similar customer by address
//    form/endpoints/{labName}/{add1}

    @GetMapping("/sc/{labName}/{add1}")
    public List<CheckSimilarCustomer> getSimilarCustomersByAddress(@PathVariable(name = "labName") String labName,
                                                                   @PathVariable(name = "add1") String add1) {


        return miniChecksRepo.getSimilarCustomerByAddress(labName, add1);
    }

//    EndPoint to get Similar Customers by Address and Phone
//    form/endpoints/{labName}/{add1}/{phone}

    @GetMapping("/sc/{labName}/{add1}/{phone}")
    public List<CheckSimilarCustomer> getSimilarCustomersByAddressAndPhone(@PathVariable(name = "labName") String labName,
                                                                           @PathVariable(name = "add1") String add1,
                                                                           @PathVariable(name = "phone") String phone){

        return miniChecksRepo.getSimilarCustomerByAddressAndPhone(labName,add1, phone);

    }

//    Endpoint to get Similar doctor by doctor name
//    form/endpoints/{labName}/{fName}/{lName}

    @GetMapping("/sd/{labName}/{fName}/{lName}")
    public List<CheckSimilarDoctor> getSimilarDoctorByName(@PathVariable(name = "labName") String labName,
                                                           @PathVariable(name = "fName") String fName,
                                                           @PathVariable(name = "lName") String lName){

        return miniChecksRepo.getSimilarDoctorByName(labName, fName, lName);
    }

//    Endpoint to get similar doctor by doctor name and license no
//    form/endpoints/{labName}/{fName}/{lName}/{licenseNo}


    @GetMapping("/sd/{labName}/{fName}/{lName}/{licenseNo}/{licenseState}")
    public List<CheckSimilarDoctor> getSimilarDoctorByNameAndLicenseNo(@PathVariable(name = "labName") String labName,
                                                           @PathVariable(name = "fName") String fName,@PathVariable(name = "lName") String lName,
                                                                       @PathVariable(name = "licenseNo") String licenseNo,
                                                                       @PathVariable(name = "licenseState") String licenseState){
        licenseNo = licenseNo+ licenseState;

        return miniChecksRepo.getSimilarDoctorNameAndLicense(labName, fName, lName,licenseNo);
    }

//  End point to get existing doctor by  doctor id
//    form/endpoints/{labName}/{docId}

    @GetMapping("/ed/{labName}/{docId}")
    public CheckExistingDoctor getExistingDoctorByDoctorId(@PathVariable(name = "labName") String labName,
                                                           @PathVariable(name = "docId") String docId){

        return miniChecksRepo.getExistingDoctorByDocId(labName, docId);
    }

    @PostMapping("/emailIssues/{id}/{message}")
    public String sendEmailWithIssues(@PathVariable("id") Long id, @PathVariable("message") String message) {

        AccountRequest accountRequest = accountRequestRepo.findAccountRequestsByAccId(id);

        emailService.emailServiceSender(accountRequest, message);

        return "email sent";
    }

    @GetMapping("/sa/{country}/{address1}/{city}/{state}")
    public CheckAddress getStdAddress(@PathVariable(name = "country") String country,
                                      @PathVariable(name = "address1") String address1, @PathVariable(name = "city") String city,
                                      @PathVariable(name = "state") String state) {


        return miniChecksRepo.getStdAddress(country, address1, city, state);
    }

    @GetMapping("/sa/{country}/{address1}/{city}/{state}/{zipCode}")
    public CheckAddress getStdAddress(@PathVariable(name = "country") String country,
                                      @PathVariable(name = "address1") String address1, @PathVariable(name = "city") String city,
                                      @PathVariable(name = "state") String state,
                                      @PathVariable(name = "zipCode") String zipCode) {


        return miniChecksRepo.getStdAddressWZip(country, address1, city, state, zipCode);
    }

    @GetMapping("/NPI/{fName}/{lName}")
    public List<CheckNPI> RequestNPI(@PathVariable(name = "fName") String fName,
                                     @PathVariable(name = "lName") String lName) {

        return miniChecksRepo.RequestNPIWFirstLastName(fName, lName);
    }

    @GetMapping("/NPI/{npi}")
    public List<CheckNPI> RequestNPI(@PathVariable(name = "npi") String npi) {

        return miniChecksRepo.RequestNPI(npi);
    }


}
